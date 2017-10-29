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

package tripndroid.dolby.audio.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import tripndroid.dolby.audio.api.IDs.Stub;

class DsBase {
    private static final String TAG = "DsBase";
    private static final int VERSION = 1;
    protected DsClientInfo DsClientInfo_ = null;
    protected IDsAccessEvents accessListener_ = null;
    protected int clientHandle_ = 0;
    protected int connectionBridge_ = 0;
    protected ServiceConnection connection_ = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            DsLog.log1("DsBase", "ServiceConnection.onServiceConnected()");
            DsBase.this.iDs_ = Stub.asInterface(service);
            DsBase.this.clientHandle_ = hashCode();
            try {
                DsBase.this.iDs_.iRegisterDeathHandler(DsBase.this.clientHandle_, DsBase.this.deathHandler_);
                DsBase.this.iDs_.iRegisterDsAccess(DsBase.this.clientHandle_, DsBase.this.DsClientInfo_);
            } catch (Exception e) {
                DsBase.this.handleException(e, "iRegisterDsAccess");
            }
            if (DsBase.this.accessListener_ != null) {
                DsBase.this.accessListener_.onClientConnected();
            }
            DsLog.log3("DsBase", "CONNECTED: DsService");
        }

        public void onServiceDisconnected(ComponentName className) {
            DsLog.log1("DsBase", "ServiceConnection.onServiceDisconnected()");
            if (DsBase.this.accessListener_ != null) {
                DsBase.this.accessListener_.onClientDisconnected();
            }
            DsBase.this.iDs_ = null;
            DsLog.log3("DsBase", "/ServiceConnection.onServiceDisconnected()");
        }
    };
    protected Context context_ = null;
    private IDsDeathHandler deathHandler_ = new IDsDeathHandler.Stub() {
        public void onClientDied() {
        }
    };
    protected IDs iDs_ = null;

    DsBase() {
    }

    protected void convertErrorCodeToException(int errorCode) {
        if (errorCode < 0) {
            Log.d("DsBase", "convertErrorCodeToException " + errorCode);
            switch (errorCode) {
                case -6:
                    throw new RuntimeException("Exception: unknown problem.");
                case -4:
                    throw new RuntimeException("Exception: can not load tunings.");
                case -3:
                    throw new RuntimeException("Exception: can not load settings.");
                case -2:
                    throw new RuntimeException("Exception: DS not running.");
                case -1:
                    throw new IllegalArgumentException("Exception: invalid argument.");
                default:
                    return;
            }
        }
    }

    protected void handleException(Exception e, String methodName) {
        String msg = "Exception in " + methodName;
        Log.e("DsBase", msg + " " + e.toString());
        e.printStackTrace();
        throw new RuntimeException(msg, e);
    }

    protected void setConnectionInfo(int access) {
        this.connectionBridge_ = access;
    }

    public boolean registerClient(Context context, IDsAccessEvents listener) {
        DsLog.log1("DsBase", "registerClient");
        int error = -6;
        if (listener == null || context == null) {
            error = -1;
        } else {
            try {
                this.context_ = context;
                this.accessListener_ = listener;
                this.DsClientInfo_ = new DsClientInfo();
                this.DsClientInfo_.setPackageName(this.context_.getPackageName());
                this.DsClientInfo_.setConnectionBridge(this.connectionBridge_);
                error = 0;
            } catch (Exception e) {
                handleException(e, "registerClient");
            }
        }
        convertErrorCodeToException(error);
        if (context != null) {
            Intent bindIntent = DsCommon.getServiceIntent(context);
            if (bindIntent != null) {
                return context.bindService(bindIntent, this.connection_, 1);
            }
        }
        return false;
    }

    public void unregisterClient() {
        DsLog.log1("DsBase", "unregisterClient");
        if (this.iDs_ != null) {
            try {
                this.iDs_.iUnregisterDsAccess(this.clientHandle_);
                this.iDs_.iUnregisterDeathHandler(this.clientHandle_, this.deathHandler_);
            } catch (Exception e) {
                handleException(e, "unregisterClient");
            }
            if (this.context_ != null) {
                this.context_.unbindService(this.connection_);
                this.context_ = null;
            }
            this.iDs_ = null;
        }
        this.DsClientInfo_ = null;
        this.accessListener_ = null;
    }

    public String getDsVersion() {
        String version = "";
        String[] paramString = new String[1];
        int error = 0;
        try {
            error = this.iDs_.iGetDsServiceVersion(paramString);
        } catch (Exception e) {
            handleException(e, "getDsVersion");
        }
        convertErrorCodeToException(error);
        return paramString[0];
    }

    public int getApiVersion() {
        return 1;
    }

    public int requestAccessRight(int accessRight) {
        int error = 0;
        try {
            error = this.iDs_.iRequestAccessRight(this.clientHandle_, accessRight);
        } catch (Exception e) {
            handleException(e, "requestAccessRight");
        }
        return error;
    }

    public int abandonAccessRight(int accessRight) {
        int error = 0;
        try {
            error = this.iDs_.iAbandonAccessRight(this.clientHandle_, accessRight);
        } catch (Exception e) {
            handleException(e, "abandonAccessRight");
        }
        return error;
    }

    public int getAvailableAccessRight() {
        return -1;
    }

    public int checkAccessRight(int accessRight) {
        int[] paramInt = new int[1];
        int error = 0;
        try {
            error = this.iDs_.iCheckAccessRight(this.clientHandle_, accessRight, paramInt);
        } catch (Exception e) {
            handleException(e, "checkAccessRight");
        }
        convertErrorCodeToException(error);
        return paramInt[0];
    }
}
