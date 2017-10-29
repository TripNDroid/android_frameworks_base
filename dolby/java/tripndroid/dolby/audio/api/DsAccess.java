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

public class DsAccess {
    public static final int ACCESS_FOCUS = 1;
    public static final int ACCESS_GLOBAL = 2;
    public static final int ACCESS_NONE = 0;
    public static final int ACCESS_TUNING = 4;
    public static final int ERROR_ACCESS_AREADY_GRANTED = -1;
    public static final int ERROR_ACCESS_NOT_AGREED = -4;
    public static final int ERROR_ACCESS_NOT_PERMITTED = -2;
    public static final int ERROR_ACCESS_NO_AUDIOFOCUS = -3;
    public static final int NONE_APP_GRANTED = 0;
    public static final int OTHER_APP_GRANTED = 1;
    public static final int THIS_APP_GRANTED = 2;
}
