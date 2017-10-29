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

import android.util.Log;

public class DsLog {

    public static final int DEFAULT_LOG_LEVEL = 1;
    public static final int LOG_LEVEL_0 = 0;
    public static final int LOG_LEVEL_1 = 1;
    public static final int LOG_LEVEL_2 = 2;
    public static final int LOG_LEVEL_3 = 3;

    public static void log1(String tag, String content) {
        Log.i(tag, content);
    }

    public static void log2(String tag, String content) {
    }

    public static void log3(String tag, String content) {
    }
}
