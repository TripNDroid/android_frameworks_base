/*
 * Copyright (C) 2017 TripNDroid Mobile Engineering
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tripndroid.dolby.audio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import tripndroid.dolby.audio.api.DsCommon;
import tripndroid.dolby.audio.api.DsLog;
import tripndroid.dolby.audio.api.DsParams;
import tripndroid.dolby.audio.api.IDs;
import tripndroid.dolby.audio.api.IDsCallbacks;
import tripndroid.dolby.audio.api.IDsCallbacks.Stub;
import tripndroid.dolby.audio.api.IDsDeathHandler;

public class DsClient {
    private static final int INT_OFF = 0;
    private static final int INT_ON = 1;
    private static final String TAG = "DsClient";
    private static Object lock_ = new Object();
    private static final String[] profileParams_ = new String[]{"geon", "deon", "dvle", "vdhe", "vspe"};
    private IDsClientEvents activityListener_ = null;
    private IDsCallbacks callbacks_ = new Stub() {
        public void onDsOn(boolean on) {
            DsLog.log2("DsClient", "event onDsOn()");
            DsClient.this.handler_.sendMessage(DsClient.this.handler_.obtainMessage(1, on ? 1 : 0, 0));
        }

        public void onProfileSelected(int profile) {
            DsLog.log2("DsClient", "event onProfileSelected()");
            DsClient.this.handler_.sendMessage(DsClient.this.handler_.obtainMessage(2, profile, 0));
        }

        public void onDsSuspended(boolean isSuspended) {
        }

        public void onProfileSettingsChanged(int profile) {
        }

        public void onVisualizerUpdated(float[] gains, float[] excitations) {
        }

        public void onVisualizerSuspended(boolean isSuspended) {
        }

        public void onAccessForceReleased(String app, int type) {
        }

        public void onAccessAvailable() {
        }

        public boolean onAccessRequested(String app, int type) {
            return true;
        }

        public void onProfileNameChanged(int profile, String name) {
        }

        public boolean onLegacyClientSetting() {
            return false;
        }
    };

    private ServiceConnection connection_ = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            DsLog.log1("DsClient", "ServiceConnection.onServiceConnected()");
            DsClient.this.ds_ = IDs.Stub.asInterface(service);
            try {
                DsClient.this.ds_.iRegisterDeathHandler(hashCode(), DsClient.this.deathHandler_);
                DsClient.this.ds_.iRegisterCallback(hashCode(), DsClient.this.callbacks_, 1);
                DsLog.log1("DsClient", "ServiceConnection.onServiceConnected()");
            } catch (Exception e) {
                Log.e("DsClient", "onServiceConnected failed");
            }
            if (DsClient.this.activityListener_ != null) {
                DsClient.this.activityListener_.onClientConnected();
            }
            DsLog.log3("DsClient", "CONNECTED: DSService");
        }

        public void onServiceDisconnected(ComponentName className) {
            DsLog.log1("DsClient", "ServiceConnection.onServiceDisconnected()");
            if (DsClient.this.activityListener_ != null) {
                DsClient.this.activityListener_.onClientDisconnected();
            }
            DsClient.this.ds_ = null;
            DsLog.log3("DsClient", "/ServiceConnection.onServiceDisconnected()");
        }
    };

    private IDsDeathHandler deathHandler_ = new IDsDeathHandler.Stub() {
        public void onClientDied() {
        }
    };

    private IDs ds_ = null;
    private Handler handler_ = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    DsLog.log1("DsClient", "handleMessage(DS_STATUS_CHANGED_MSG): isOn = " + msg.arg1);
                    boolean isOn = msg.arg1 != 0;
                    if (DsClient.this.activityListener_ != null) {
                        DsClient.this.activityListener_.onDsOn(isOn);
                        return;
                    }
                    return;
                case 2:
                    DsLog.log1("DsClient", "handleMessage(PROFILE_SELECTED_MSG): profile = " + msg.arg1);
                    if (DsClient.this.activityListener_ != null) {
                        DsClient.this.activityListener_.onProfileSelected(msg.arg1);
                        return;
                    }
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    };

    private void translateErrorCodeToExceptions(int errorCode) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RuntimeException {
        if (errorCode < 0) {
            switch (errorCode) {
                case -2:
                    throw new DeadObjectException();
                case -1:
                    throw new IllegalArgumentException();
                case 3:
                    throw new IllegalStateException();
                case 4:
                    throw new UnsupportedOperationException();
                default:
                    throw new RuntimeException();
            }
        }
    }

    public void setDsOn(boolean on) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (this.ds_ != null) {
            synchronized (lock_) {
                try {
                    translateErrorCodeToExceptions(this.ds_.iSetState(this.connection_.hashCode(), 0, on));
                } catch (RemoteException e) {
                    Log.e("DsClient", "RemoteException in setDsOn");
                    throw e;
                } catch (NullPointerException e2) {
                    Log.e("DsClient", "NullPointerException in setDsOn");
                    e2.printStackTrace();
                    throw e2;
                } catch (Exception e3) {
                    Log.e("DsClient", e3.toString() + " in setDsOn");
                    e3.printStackTrace();
                    throw new RuntimeException("Exception in setDsOn");
                }
            }
            return;
        }
        throw new NullPointerException("NullPointerException in setDsOn");
    }

    public int setDsOnChecked(boolean on) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (this.ds_ != null) {
            int error;
            synchronized (lock_) {
                try {
                    error = this.ds_.iSetState(this.connection_.hashCode(), 0, on);
                    translateErrorCodeToExceptions(error);
                } catch (RemoteException e) {
                    Log.e("DsClient", "RemoteException in setDsOnChecked");
                    throw e;
                } catch (NullPointerException e2) {
                    Log.e("DsClient", "NullPointerException in setDsOnChecked");
                    e2.printStackTrace();
                    throw e2;
                } catch (Exception e3) {
                    Log.e("DsClient", e3.toString() + " in setDsOnChecked");
                    e3.printStackTrace();
                    throw new RuntimeException("Exception in setDsOnChecked");
                }
            }
            return error;
        }
        throw new NullPointerException("NullPointerException in setDsOnChecked");
    }

    public boolean getDsOn() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (this.ds_ != null) {
            try {
                int[] paramInt = new int[1];
                int error = this.ds_.iGetState(0, paramInt);
                if (error == 0) {
                    return paramInt[0] == 1;
                } else {
                    translateErrorCodeToExceptions(error);
                    return false;
                }
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getDsOn");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getDsOn");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getDsOn");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getDsOn");
            }
        }
        throw new NullPointerException("NullPointerException in getDsOn");
    }

    public int getProfileCount() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        int value = 0;
        if (this.ds_ != null) {
            try {
                int[] paramInt = new int[1];
                int error = this.ds_.iGetProfileCount(0, paramInt);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                } else {
                    value = paramInt[0];
                }
                return value;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getProfileCount");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getProfileCount");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getProfileCount");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getProfileCount");
            }
        }
        throw new NullPointerException("NullPointerException in getProfileCount");
    }

    public String[] getProfileNames() {
        String[] names = new String[6];
        for (int i = 0; i < 6; i++) {
            names[i] = DsCommon.PROFILE_NAMES[i];
        }
        return names;
    }

    public int getSelectedProfile() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        int value = 0;
        if (this.ds_ != null) {
            try {
                int[] paramInt = new int[1];
                int error = this.ds_.iGetProfile(0, paramInt);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                } else {
                    value = paramInt[0];
                }
                return value;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getSelectedProfile");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getSelectedProfile");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getSelectedProfile");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getSelectedProfile");
            }
        }
        throw new NullPointerException("NullPointerException in getSelectedProfile");
    }

    public void setSelectedProfile(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (profile < 0 || profile > 5) {
            throw new IllegalArgumentException("invalid profile");
        } else if (this.ds_ != null) {
            synchronized (lock_) {
                try {
                    int error = this.ds_.iSetProfile(this.connection_.hashCode(), 0, profile);
                    if (error != 0) {
                        translateErrorCodeToExceptions(error);
                    }
                } catch (RemoteException e) {
                    Log.e("DsClient", "RemoteException in setSelectedProfile");
                    throw e;
                } catch (NullPointerException e2) {
                    Log.e("DsClient", "NullPointerException in setSelectedProfile");
                    e2.printStackTrace();
                    throw e2;
                } catch (Exception e3) {
                    Log.e("DsClient", e3.toString() + " in setSelectedProfile");
                    e3.printStackTrace();
                    throw new RuntimeException("Exception in setSelectedProfile");
                }
            }
        } else {
            throw new NullPointerException("NullPointerException in setSelectedProfile");
        }
    }

    public void setProfileSettings(int profile, DsClientSettings settings) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (profile < 0 || profile > 5) {
            throw new IllegalArgumentException("invalid profile");
        } else if (settings == null) {
            throw new IllegalArgumentException("invalid settings");
        } else if (this.ds_ != null) {
            synchronized (lock_) {
                try {
                    int[] value = new int[1];
                    for (String param : profileParams_) {
                        DsParams dsParam = DsParams.FromString(param);
                        if (dsParam == null) {
                            throw new RuntimeException("Exception in setProfileSettings, invalid profile parameters");
                        }
                        int paramId = dsParam.toInt();
                        int i;
                        if (param.equals("geon")) {
                            if (settings.getGeqOn()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            value[0] = i;
                        } else if (param.equals("deon")) {
                            if (settings.getDialogEnhancerOn()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            value[0] = i;
                        } else if (param.equals("dvle")) {
                            if (settings.getVolumeLevellerOn()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            value[0] = i;
                        } else if (param.equals("vdhe")) {
                            value[0] = settings.getHeadphoneVirtualizerOn() ? 1 : 0;
                        } else if (param.equals("vspe")) {
                            value[0] = settings.getSpeakerVirtualizerOn() ? 1 : 0;
                        }
                        int error = this.ds_.iSetParameter(this.connection_.hashCode(), 0, profile, paramId, value);
                        if (error != 0) {
                            translateErrorCodeToExceptions(error);
                        }
                    }
                } catch (RemoteException e) {
                    Log.e("DsClient", "RemoteException in setProfileSettings");
                    throw e;
                } catch (NullPointerException e2) {
                    Log.e("DsClient", "NullPointerException in setProfileSettings");
                    e2.printStackTrace();
                    throw e2;
                } catch (Exception e3) {
                    Log.e("DsClient", e3.toString() + " in setProfileSettings");
                    e3.printStackTrace();
                    throw new RuntimeException("Exception in setProfileSettings");
                }
            }
        } else {
            throw new NullPointerException("NullPointerException in setProfileSettings");
        }
    }

    public DsClientSettings getProfileSettings(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (this.ds_ != null) {
            try {
                DsClientSettings clientSettings = new DsClientSettings();
                int[] paramInt = new int[1];
                boolean[] value = new boolean[5];
                int index = 0;
                for (String param : profileParams_) {
                    DsParams dsParam = DsParams.FromString(param);
                    if (dsParam != null) {
                        int error = this.ds_.iGetParameter(0, profile, dsParam.toInt(), paramInt);
                        if (error != 0) {
                            translateErrorCodeToExceptions(error);
                        }
                        value[index] = paramInt[0] != 0;
                        index++;
                    }
                }
                clientSettings.setGeqOn(value[0]);
                clientSettings.setDialogEnhancerOn(value[1]);
                clientSettings.setVolumeLevellerOn(value[2]);
                clientSettings.setHeadphoneVirtualizerOn(value[3]);
                clientSettings.setSpeakerVirtualizerOn(value[4]);
                DsClientSettings settings = clientSettings;
                return clientSettings;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getProfileSettings");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getProfileSettings");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getProfileSetting");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getProfileSettings");
            }
        }
        throw new NullPointerException("NullPointerException in getProfileSettings");
    }

    public void resetProfile(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (profile < 0 || profile > 5) {
            throw new IllegalArgumentException("invalid profile");
        } else if (this.ds_ != null) {
            synchronized (lock_) {
                try {
                    int error = this.ds_.iResetProfile(this.connection_.hashCode(), 0, profile);
                    if (error != 0) {
                        translateErrorCodeToExceptions(error);
                    }
                } catch (RemoteException e) {
                    Log.e("DsClient", "RemoteException in resetProfile");
                    throw e;
                } catch (NullPointerException e2) {
                    Log.e("DsClient", "NullPointerException in resetProfile");
                    e2.printStackTrace();
                    throw e2;
                } catch (Exception e3) {
                    Log.e("DsClient", e3.toString() + " in resetProfile");
                    e3.printStackTrace();
                    throw new RuntimeException("Exception in resetProfile");
                }
            }
        } else {
            throw new NullPointerException("NullPointerException in resetProfile");
        }
    }

    public String getDsApVersion() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        String version = "";
        if (this.ds_ != null) {
            try {
                String[] paramString = new String[1];
                int error = this.ds_.iGetDapLibraryVersion(paramString);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                } else {
                    version = paramString[0];
                }
                return version;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getDsApVersion");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getDsApVersion");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getDsApVersion");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getDsApVersion");
            }
        }
        throw new NullPointerException("NullPointerException in getDsApVersion");
    }

    public String getDsVersion() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        String version = "";
        if (this.ds_ != null) {
            try {
                String[] paramString = new String[1];
                int error = this.ds_.iGetDsServiceVersion(paramString);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                } else {
                    version = paramString[0];
                }
                return version;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getDsVersion");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getDsVersion");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getDsVersion");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getDsVersion");
            }
        }
        throw new NullPointerException("NullPointerException in getDsVersion");
    }

    public void setDsApParam(String param, int[] values) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        if (this.ds_ != null) {
            try {
                DsParams dsParam = DsParams.FromString(param);
                if (dsParam == null) {
                    throw new IllegalArgumentException();
                }
                int paramId = dsParam.toInt();
                int[] paramInt = new int[1];
                int error = this.ds_.iGetProfile(0, paramInt);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                    return;
                }
                error = this.ds_.iSetParameter(this.connection_.hashCode(), 0, paramInt[0], paramId, values);
                if (error != 0) {
                    translateErrorCodeToExceptions(error);
                    return;
                }
                return;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in setDsApParam");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in setDsApParam");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in setDsApParam");
                e3.printStackTrace();
                throw new RuntimeException("Exception in setDsApParam");
            }
        }
        throw new NullPointerException("NullPointerException in setDsApParam");
    }

    public int[] getDsApParam(String param) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        int[] values = null;
        if (this.ds_ != null) {
            try {
                int[] paramInt = new int[1];
                DsParams dsParam = DsParams.FromString(param);
                if (dsParam == null) {
                    throw new IllegalArgumentException();
                }
                int paramId = dsParam.toInt();
                if (this.ds_.iGetParamLength(paramId, paramInt) == 0) {
                    int[] profileInt = new int[1];
                    int error = this.ds_.iGetProfile(0, profileInt);
                    if (error != 0) {
                        translateErrorCodeToExceptions(error);
                    } else {
                        values = new int[paramInt[0]];
                        error = this.ds_.iGetParameter(0, profileInt[0], paramId, values);
                        if (error != 0) {
                            translateErrorCodeToExceptions(error);
                        }
                    }
                }
                return values;
            } catch (RemoteException e) {
                Log.e("DsClient", "RemoteException in getDsApParam");
                throw e;
            } catch (NullPointerException e2) {
                Log.e("DsClient", "NullPointerException in getDsApParam");
                e2.printStackTrace();
                throw e2;
            } catch (Exception e3) {
                Log.e("DsClient", e3.toString() + " in getDsApParam");
                e3.printStackTrace();
                throw new RuntimeException("Exception in getDsApParam");
            }
        }
        throw new NullPointerException("NullPointerException in getDsApParam");
    }

    public void setEventListener(IDsClientEvents listener) {
        if (listener != null) {
            this.activityListener_ = listener;
        }
    }

    public boolean bindDsService(Context context) {
        DsLog.log1("DsClient", "bindDsService()");
        Intent bindIntent = DsCommon.getServiceIntent(context);
        if (bindIntent != null) {
            return context.bindService(bindIntent, this.connection_, 1);
        }
        return false;
    }

    public void unBindDsService(Context context) {
        DsLog.log1("DsClient", "unBindDsService()");
        if (this.ds_ != null) {
            try {
                this.ds_.iUnregisterCallback(this.connection_.hashCode(), this.callbacks_, 1);
                this.ds_.iUnregisterDeathHandler(hashCode(), this.deathHandler_);
            } catch (RemoteException e) {
                Log.e("DsClient", "Remote Exception in unBindFromRemoteRunningService");
            }
            context.unbindService(this.connection_);
            this.ds_ = null;
            this.activityListener_ = null;
        }
    }

    public void setNonPersistentMode(boolean on) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in setNonPersistentMode");
    }

    public int getBandCount() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in getBandCount");
    }

    public int[] getBandFrequencies() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in getBandFrequencies");
    }

    public void setProfileName(int profile, String name) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in setProfileName");
    }

    public boolean isMonoSpeaker() throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in isMonoSpeaker");
    }

    public void setIeqPreset(int profile, int preset) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in setIeqPreset");
    }

    public int getIeqPreset(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in getIeqPreset");
    }

    public boolean isProfileModified(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in isProfileModified");
    }

    public boolean isProfileNameModified(int profile) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in isProfileNameModified");
    }

    public void setGeq(int profile, int preset, float[] geqBandGains) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in setGeq");
    }

    public float[] getGeq(int profile, int preset) throws IllegalArgumentException, DeadObjectException, IllegalStateException, UnsupportedOperationException, RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in getGeq");
    }

    public void registerDsApParamEvents(IDsApParamEvents listener) throws RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in registerDsApParamEvents");
    }

    public void unregisterDsApParamEvents() throws RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in unregisterDsApParamEvents");
    }

    public void registerVisualizer(IDsVisualizerEvents listener) throws RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in registerVisualizer");
    }

    public void unregisterVisualizer() throws RemoteException, NullPointerException, RuntimeException {
        throw new RuntimeException("Exception in unregisterVisualizer");
    }

    public static float getGeqBandGainLowerBound() {
        throw new RuntimeException("Exception in getGeqBandGainLowerBound");
    }

    public static float getGeqBandGainUpperBound() {
        throw new RuntimeException("Exception in getGeqBandGainUpperBound");
    }
}
