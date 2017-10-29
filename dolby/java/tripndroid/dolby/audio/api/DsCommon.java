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
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.List;

public class DsCommon {
    public static final int ACCESS_AVAILABLE_MSG = 8;
    public static final int ACCESS_RELEASED_MSG = 7;
    public static final int ACCESS_REQUESTED_MSG = 9;
    public static final String CMDINIT = "init";
    public static final String CMDNAME = "cmd";
    public static final String CMDONOFF = "on off";
    public static final int CMDSELECTGAME = 2;
    public static final int CMDSELECTMOVIE = 0;
    public static final int CMDSELECTMUSIC = 1;
    public static final int CMDSELECTPRESET1 = 4;
    public static final int CMDSELECTPRESET2 = 5;
    public static final int CMDSELECTVOICE = 3;
    public static final int CODE_DS_OFF = 16;
    public static final int CODE_DS_ON = 17;
    public static final int CODE_LAUNCH_APP = 48;
    public static final int CODE_SET_PROFILE_0 = 32;
    public static final int CODE_SET_PROFILE_1 = 33;
    public static final int CODE_SET_PROFILE_2 = 34;
    public static final int CODE_SET_PROFILE_3 = 35;
    public static final int CODE_SET_PROFILE_4 = 36;
    public static final int CODE_SET_PROFILE_5 = 37;
    public static final int DAP1_PARAM_ID_END = 143;
    public static final int DAP1_PARAM_ID_START = 100;
    public static final int DAP_CPDP_PARAM_ID_END = 248;
    public static final int DAP_CPDP_PARAM_ID_START = 200;
    public static final int DS_ACCESS_LOCK_NOT_AVAILABLE = -5;
    public static final int DS_ALREADY_INITIALIZED = 2;
    public static final int DS_AUDIOFOCUS_ABANDON = 2;
    public static final int DS_AUDIOFOCUS_GAIN = 0;
    public static final int DS_AUDIOFOCUS_LOSS = 1;
    public static final int DS_CANNOT_LOAD_SETTINGS = -3;
    public static final int DS_CANNOT_LOAD_TUNINGS = -4;
    public static final int DS_CLIENT_VER_ONE = 1;
    public static final int DS_CLIENT_VER_TWO = 2;
    public static final int DS_INVALID_ARGUMENT = -1;
    public static final int DS_NOT_INITIALIZED = 3;
    public static final int DS_NOT_RUNNING = -2;
    public static final int DS_NO_ERROR = 0;
    public static final int DS_OUT_OF_BOUNDS = 1;
    public static final int DS_PROFILE_NAME_AND_SETTINGS_MODIFIED = 3;
    public static final int DS_PROFILE_NAME_MODIFIED = 2;
    public static final int DS_PROFILE_NOT_MODIFIED = 0;
    public static final int DS_PROFILE_SETTINGS_MODIFIED = 1;
    public static final int DS_SETTING_NOT_PERMITTED = 4;
    public static final int DS_STATUS_CHANGED_MSG = 1;
    public static final int DS_STATUS_SUSPENDED_MSG = 6;
    public static final int DS_UNKNOWN_ERROR = -6;
    public static final String[][] GEQ_NAMES_XML;
    public static final String[] IEQ_PRESET_NAMES = new String[]{"Off", "Open", "Rich", "Focused", "Bright", "Balanced", "Warm"};
    public static final String[] IEQ_PRESET_NAMES_XML = new String[]{"ieq_off", "ieq_open", "ieq_rich", "ieq_focused", "ieq_bright", "ieq_balanced", "ieq_warm"};
    public static final String INIT_ACTION = "com.dolby.ds.srvcmd.init";
    public static final String LAUNCH_DOLBY_APP_ACTION = "com.dolby.ds.srvcmd.launchapp";
    public static final int LEGACY_CLIENT_SETTING_MSG = 11;
    public static final String ONOFF_ACTION = "com.dolby.ds.srvcmd.toggleonoff";
    public static final String[] PROFILE_NAMES = new String[]{"Movie", "Music", "Game", "Voice", "Custom 1", "Custom 2"};
    public static final String[] PROFILE_NAMES_XML = new String[]{"movie", "music", "game", "voice", "user1", "user2"};
    public static final int PROFILE_NAME_CHANGED_MSG = 10;
    public static final int PROFILE_SELECTED_MSG = 2;
    public static final int PROFILE_SETTINGS_CHANGED_MSG = 3;
    public static final String SELECTPROFILE_ACTION = "com.dolby.ds.srvcmd.select";
    public static final int VISUALIZER_SUSPENDED_MSG = 5;
    public static final int VISUALIZER_UPDATED_MSG = 4;
    public static final String WIDGET_CLASS = "widget class";

    private static final String[][] r0;

    static {
        r0 = new String[6][];
        r0[0] = new String[]{"geq_movie_off", "geq_movie_open", "geq_movie_rich", "geq_movie_focused", "geq_movie_bright", "geq_movie_balanced", "geq_movie_warm"};
        r0[1] = new String[]{"geq_music_off", "geq_music_open", "geq_music_rich", "geq_music_focused", "geq_music_bright", "geq_music_balanced", "geq_music_warm"};
        r0[2] = new String[]{"geq_game_off", "geq_game_open", "geq_game_rich", "geq_game_focused", "geq_game_bright", "geq_game_balanced", "geq_game_warm"};
        r0[3] = new String[]{"geq_voice_off", "geq_voice_open", "geq_voice_rich", "geq_voice_focused", "geq_voice_bright", "geq_voice_balanced", "geq_voice_warm"};
        r0[4] = new String[]{"geq_user1_off", "geq_user1_open", "geq_user1_rich", "geq_user1_focused", "geq_user1_bright", "geq_user1_balanced", "geq_user1_warm"};
        r0[5] = new String[]{"geq_user2_off", "geq_user2_open", "geq_user2_rich", "geq_user2_focused", "geq_user2_bright", "geq_user2_balanced", "geq_user2_warm"};
        GEQ_NAMES_XML = r0;
    }

    public DsCommon() {
        super();
    }

    public static Intent getServiceIntent(Context context) {
        Intent bindIntent = new Intent(IDs.class.getName());
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(bindIntent, 0);
        if (resolveInfos == null) {
            Log.e("DsCommon", "getServiceIntent() resolveInfos=null");
            return null;
        } else if (resolveInfos.size() != 1) {
            Log.e("DsCommon", "getServiceIntent() resolveInfos.size() = " + resolveInfos.size());
            return null;
        } else {
            ResolveInfo serviceInfo = (ResolveInfo) resolveInfos.get(0);
            bindIntent.setComponent(new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name));
            return bindIntent;
        }
    }
}
