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

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.os.Looper;

public class DsFocus extends DsBase
{
    private static final String TAG = "DsFocus";

    protected IDsEvents dsListener_ = null;
    protected IDsVisualizerEvents visualizerListener_ = null;
    protected IDsProfileNameEvents profileNameListener_ = null;
    protected IDsLegacyClientEvents legacyClientListener_ = null;

    protected int bandCount_ = 20;

    protected float[] gains_ = null;
    protected float[] excitations_ = null;

    public DsFocus() {
        super.setConnectionInfo(DsAccess.ACCESS_FOCUS);
    }

    public void registerVisualizer(IDsVisualizerEvents listener) {
        int error = -6;     
        try {
            if (bandCount_ == 0) {
                Log.e(TAG, "graphic equalizer band count NOT initialized yet.");
                throw new RuntimeException("Exception in registerVisualizer");
            }
            else if (listener != null) {
                if(visualizerListener_ == null) {
                    if (gains_ == null)
                        gains_ = new float[bandCount_];
                    if (excitations_ == null)
                        excitations_ = new float[bandCount_];
                
                    iDs_.iRegisterVisualizerData(clientHandle_);
                    visualizerListener_ = listener;
                }
                error = 0;
            }
            else {
                error = -1;
            }
        }
        catch(Exception e) {
            handleException(e, "registerVisualizer");
        }
        convertErrorCodeToException(error);
    }

    public void unregisterVisualizer()
    {
        try
        {
            iDs_.iUnregisterVisualizerData(clientHandle_);
            visualizerListener_ = null;
            gains_ = null;
            excitations_ = null;
        }
        catch(Exception e)
        {
            handleException(e, "unregisterVisualizer");
        }
    }

    public void registerDsEvents(IDsEvents listener)
    {
        int error = -6;
                    
        try
        {
            if (listener != null)
            {
                iDs_.iRegisterCallback(clientHandle_, callbacks_, 2);
                dsListener_ = listener;
                error = 0;
            }
            else
            {
                error = -1;
            }
        }
        catch(Exception e)
        {
            handleException(e, "registerDsEvents");
        }
        convertErrorCodeToException(error);
    }

    public void unregisterDsEvents()
    {
        try
        {
            iDs_.iUnregisterCallback(clientHandle_, callbacks_, 2);
            dsListener_ = null;
        }
        catch(Exception e)
        {
            handleException(e, "unregisterDsEvents");
        }
    }

    /**
     * Turn on or off DS audio processing.
     *
     * This method may fail to set the state of DS.
     * As such, the caller should verify the return code of the function before
     * making any updates to the UI.
     *
     */
    public int setState(boolean on) throws DsAccessException
    {
        // Default return value if nothing is acheived.
        int error = 1;
        try
        {
            error = iDs_.iSetState(clientHandle_, 0, on);
        }
        catch(Exception e)
        {
            handleException(e, "setState");
        }
        if (error == -5)
        {
            throw (new DsAccessException("Exception: access right."));
        }
        else
        {
            convertErrorCodeToException(error);
        }
        return error;
    }

    public int getState()
    {
        int ret_val = -1;
        int[] paramInt = new int[1];
        int error = 0;
        try
        {
            error = iDs_.iGetState(0, paramInt);
        }
        catch(Exception e)
        {
            handleException(e, "getState");
        }
        convertErrorCodeToException(error);
        ret_val = paramInt[0];
        return ret_val;
    }

    /**
     * Set the values of a specific audio processing parameter for the current selected profile.
     *
     * @param paramId The id of the parameter.
     * @param values The values of the parameter.
     *
     * @throws DsAccessException if there is an access right required..
     *
     * @return 0 - Success.
     *         or other warnings defined in DsConstants.java.
     */
    public int setParameter(int paramId, int[] values) throws DsAccessException
    {
        int error = 0;
        try
        {
            int[] paramInt = new int[1];
            error = iDs_.iGetProfile(0, paramInt);
            if (error == 0)
            {
                int profile = paramInt[0];
                error = iDs_.iSetParameter(clientHandle_, 0, profile, paramId, values);
            }
        }
        catch(Exception e)
        {
            handleException(e, "setParameter");
        }
        if (error == -5)
        {
            throw (new DsAccessException("Exception: access right."));
        }
        else
        {
            convertErrorCodeToException(error);
        }
        return error;
    }

    /**
     * Get the values of a specific audio processing parameter for the current selected profile.
     *
     * @param paramId The id of the parameter.
     *
     * @return The values of the parameter .
     */
    public int[] getParameter(int paramId) {
        int[] ret_vals = null;
        int[] paramInt = new int[1];
        int error = 0;
        try {
            error = iDs_.iGetParamLength(paramId, paramInt);
            if(error == 0) {
                int[] profileInt = new int[1];
                error = iDs_.iGetProfile(0, profileInt);
                if (error == 0) {
                    int profile = profileInt[0];
                    ret_vals = new int[paramInt[0]];
                    error = iDs_.iGetParameter(0, profile, paramId, ret_vals);
                }
            }
        }
        catch(Exception e) {
            handleException(e, "getParameter");
        }
        convertErrorCodeToException(error);
        return ret_vals;
    }

    public int getIeqPresetCount() {
        int ret_val = 0;
        int[] paramInt = new int[1];
        int error = 0;
        try {
            error = iDs_.iGetIeqPresetCount(0, paramInt);
        }
        catch(Exception e) {
            handleException(e, "getIeqPresetCount");
        }
        convertErrorCodeToException(error);
        ret_val = paramInt[0];
        return ret_val;
    }

    public int setIeqPreset(int preset) throws DsAccessException {
        int error = 0;
        try {
            error = iDs_.iSetIeqPreset(clientHandle_, 0, preset);
        }
        catch(Exception e) {
            handleException(e, "setIeqPreset");
        }
        if (error == -5) {
            throw (new DsAccessException("Exception: access right."));
        }
        else {
            convertErrorCodeToException(error);
        }
        return error;
    }

    /**
     * Get the active intelligent equalizer preset.
     *
     * @return The index of active intelligent equalizer preset.
     */
    public int getIeqPreset() {
        int ret_val = 0;
        int[] paramInt = new int[1];
        int error = 0;
        try
        {
            error = iDs_.iGetIeqPreset(0, paramInt);
        }
        catch(Exception e)
        {
            handleException(e, "getIeqPreset");
        }
        convertErrorCodeToException(error);
        ret_val = paramInt[0];
        return ret_val;
    }

    /**
     * Query the number of DS profile.
     *
     * @return The total number of profile.
     */
    public int getProfileCount() {
        int ret_val = 0;
        int[] paramInt = new int[1];
        int error = 0;
        try {
            error = iDs_.iGetProfileCount(0, paramInt);
        }
        catch(Exception e) {
            handleException(e, "getProfileCount");
        }
        convertErrorCodeToException(error);
        ret_val = paramInt[0];
        return ret_val;
    }

    public int setProfile(int profile) throws DsAccessException
    {
        int error = 0;
        try
        {
            error = iDs_.iSetProfile(clientHandle_, 0, profile);
        }
        catch(Exception e)
        {
            handleException(e, "setProfile");
        }
        if (error == -5)
        {
            throw (new DsAccessException("Exception: access right."));
        }
        else
        {
            convertErrorCodeToException(error);
        }
        return error;
    }

    public int getProfile()
    {
        int ret_val = 0;
        int[] paramInt = new int[1];
        int error = 0;
        try
        {
            error = iDs_.iGetProfile(0, paramInt);
        }
        catch(Exception e)
        {
            handleException(e, "getProfile");
        }
        convertErrorCodeToException(error);
        ret_val = paramInt[0];
        return ret_val;
    }

    public boolean isProfileSettingsModified(int profile)
    {
        boolean ret_val = false;
        boolean[] paramBoolean = new boolean[1];
        int error = 0;
        try
        {
            error = iDs_.iGetProfileModified( 0, profile, paramBoolean);
        }
        catch(Exception e)
        {
            handleException(e, "isProfileSettingsModified");
        }
        convertErrorCodeToException(error);
        ret_val = paramBoolean[0];
        return ret_val;
    }

    public int resetProfile(int profile) throws DsAccessException
    {
        int error = 0;
        try
        {
            error = iDs_.iResetProfile(clientHandle_, 0, profile);
        }
        catch(Exception e)
        {
            handleException(e, "setProfile");
        }
        if (error == -5)
        {
            throw (new DsAccessException("Exception: access right."));
        }
        else
        {
            convertErrorCodeToException(error);
        }
        return error;
    }

    public boolean isMonoSpeaker()
    {
        boolean ret_val = false;
        boolean[] paramBoolean = new boolean[1];
        int error = 0;
        try
        {
            error = iDs_.iGetMonoSpeaker(paramBoolean);
        }
        catch(Exception e)
        {
            handleException(e, "isMonoSpeaker");
        }
        convertErrorCodeToException(error);
        ret_val = paramBoolean[0];
        return ret_val;
    }

    public int requestAccessRight() {
        int error = -6;
        try {
            error = super.requestAccessRight(DsAccess.ACCESS_FOCUS);
        }
        catch(Exception e) {
            handleException(e, "requestAccessRight");
        }
        return error;
    }

    public int abandonAccessRight() {
        int error = -6;
        try {
            error = super.abandonAccessRight(DsAccess.ACCESS_FOCUS);
        }
        catch(Exception e) {
            handleException(e, "abandonAccessRight");
        }
        return error;
    }

    public int checkAccessRight() {
        int ret_val = -1;
        try {
            ret_val = super.checkAccessRight(DsAccess.ACCESS_FOCUS);
        }
        catch(Exception e) {
            handleException(e, "checkAccessRight");
        }
        return ret_val;
    }

    public int getAvailableAccessRight() {
        return DsAccess.ACCESS_FOCUS;
    }

    protected void setConnectionInfo(int access) {
        super.connectionBridge_ = access;
    }

    /**
     * Unbinds from the DsService.
     * After this call, no DS methods should be called on this object except for registerClient.
     * This method must be called when an application no-longer needs to communicate with the DsService
     * or make any further DS method calls.
     * Service will continue to run in the background.
     * All settings changes done by App will be lost after unregister.
     */
    public void unregisterClient() {
        DsLog.log1(TAG, "unregisterClient");
        if(iDs_ != null) {
            try {
                iDs_.iUnregisterVisualizerData(clientHandle_);
                visualizerListener_ = null;
                gains_ = null;
                excitations_ = null;
                iDs_.iUnregisterCallback(clientHandle_, callbacks_, 2);
                dsListener_ = null;
            }
            catch(Exception e) {
                handleException(e, "unregisterClient");
            }
        }
        super.unregisterClient();
    }

    /**
     * The events which will trigger the callbacks.
     *
     */
    protected IDsCallbacks callbacks_ = new IDsCallbacks.Stub() {
        /**
         * This is called by the remote service to tell us about new effect on/off state if any change.
         * Note that IPC calls are dispatched through a thread pool running in each process, so the code
         * executing here will NOT be running in our main thread like most other things -- so, to update
         * the UI, we need to use a Handler to hop over there.
         */
        public void onDsOn(boolean on) {
            DsLog.log2(TAG, "event onDsOn()");
            int status = on ? 1: 0;
            handler_.sendMessage(handler_.obtainMessage(1, status, 0));
        }

        public void onDsSuspended(boolean isSuspended) {
            DsLog.log2(TAG, "event onDsSuspended()");
            int status = isSuspended ? 1: 0;
            handler_.sendMessage(handler_.obtainMessage(6, status, 0));
        }

        public void onProfileSelected(int profile) {
            DsLog.log2(TAG, "event onProfileSelected()");
            handler_.sendMessage(handler_.obtainMessage(2, profile, 0));
        }

        public void onProfileSettingsChanged(int profile) {
            DsLog.log2(TAG, "event onProfileSettingsChanged()");
            handler_.sendMessage(handler_.obtainMessage(3, profile, 0));
        }

        public void onVisualizerUpdated(float[] gains, float[]excitations) {
            DsLog.log3(TAG, "event onVisualizerUpdated()");

            System.arraycopy(gains, 0, gains_, 0, bandCount_);
            System.arraycopy(excitations, 0, excitations_, 0, bandCount_);
            handler_.sendMessage(handler_.obtainMessage(4, 0, 0));
        }

        public void onVisualizerSuspended(boolean isSuspended) {
            DsLog.log2(TAG, "event onVisualizerSuspended()");
            int status = isSuspended ? 1 : 0;
            handler_.sendMessage(handler_.obtainMessage(5, status, 0));
        }

        public void onAccessForceReleased(String app, int type) {
            DsLog.log2(TAG, "event onAccessForceReleased()");
            handler_.sendMessage(handler_.obtainMessage(7, type, 0, app));
        }

        public void onAccessAvailable() {
            DsLog.log2(TAG, "event onAccessAvailable()");
            handler_.sendMessage(handler_.obtainMessage(8, 0, 0));
        }

        //This callback method is a synchronized call
        public boolean onAccessRequested(String app, int type) {
            DsLog.log2(TAG, "event onAccessRequested()");
            boolean value = false;
            if(accessListener_ != null) {
                value = accessListener_.onAccessRequested(app, type);
            }
            return value;
        }

        public void onProfileNameChanged(int profile, String name) {
            DsLog.log2(TAG, "event onProfileNameChanged()");
            handler_.sendMessage(handler_.obtainMessage(10, profile, 0, name));
        }

        //This callback method is a synchronized call
        public boolean onLegacyClientSetting() {
            DsLog.log2(TAG, "event onLegacyClientSetting()");
            boolean value = false;
			if(legacyClientListener_ != null)
			{
            	value = legacyClientListener_.onLegacyClientSetting();
			}
            return value;
        }
    };

    protected Handler handler_ = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    DsLog.log1(TAG, "handleMessage(DS_STATUS_CHANGED_MSG): isOn = " + msg.arg1);

                    // we can directly call the cbk in the main thread
                    if (dsListener_ != null)
                    {
                        boolean isOn = (msg.arg1 == 0) ? false : true;
                        dsListener_.onDsOn(isOn);
                    }
                    break;

                case 2:
                    DsLog.log1(TAG, "handleMessage(PROFILE_SELECTED_MSG): profile = " + msg.arg1);

                    if (dsListener_ != null)
                    {
                        dsListener_.onProfileSelected(msg.arg1);
                    }
                    break;

                case 3:
                    DsLog.log1(TAG, "handleMessage(PROFILE_SETTINGS_CHANGED_MSG): profile = " + msg.arg1);

                    if (dsListener_ != null)
                    {
                        dsListener_.onProfileSettingsChanged(msg.arg1);
                    }
                    break;

                case 6:
                    DsLog.log1(TAG, "handleMessage(DS_STATUS_SUSPENDED_MSG): profile = " + msg.arg1);

                    if (dsListener_ != null)
                    {
                        boolean isSuspended = (msg.arg1 == 0) ? false : true;
                        dsListener_.onDsSuspended(isSuspended);
                    }
                    break;

                case 4:
                    DsLog.log3(TAG, "handleMessage(VISUALIZER_UPDATED_MSG):");

                    if (visualizerListener_ != null)
                    {
                        visualizerListener_.onVisualizerUpdate(excitations_, gains_);
                    }
                    break;

                case 5:
                    DsLog.log2(TAG, "handleMessage(VISUALIZER_SUSPENDED_MSG): isSuspended = " + msg.arg1);

                    if (visualizerListener_ != null)
                    {
                        boolean isSuspended = (msg.arg1 == 0) ? false : true;
                        visualizerListener_.onVisualizerSuspended(isSuspended);
                    }
                    break;

                case 7:
                    DsLog.log1(TAG, "handleMessage(ACCESS_RELEASED_MSG): app = " + msg.obj + " type = " + msg.arg1);

                    if (accessListener_ != null)
                    {
                        accessListener_.onAccessForceReleased((String)msg.obj, msg.arg1);
                    }
                    break;

                case 8:
                    DsLog.log1(TAG, "handleMessage(ACCESS_AVAILABLE_MSG)");

                    if (accessListener_ != null)
                    {
                        accessListener_.onAccessAvailable();
                    }
                    break;
                    
                case 10:
                    DsLog.log1(TAG, "handleMessage(PROFILE_NAME_CHANGED_MSG): profile = " + msg.arg1 + " name =" + msg.obj);

                    if (profileNameListener_!= null)
                    {
                        profileNameListener_.onProfileNameChanged(msg.arg1, (String)msg.obj);
                    }
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    };
}
