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

package com.android.internal.util.td;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class TDSystemReceiver extends BroadcastReceiver {

    protected abstract void onSecureReceive(Context context, Intent intent);

    protected boolean onExemptBroadcast(Context context, String packageName) {
        return false;
    }

    @Override
    public final void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        if (isBroadcastFromSystem(context)) {
            onSecureReceive(context, intent);
        }
    }

    private boolean isBroadcastFromSystem(Context context) {
        String packageName = context.getPackageName();
        if (packageName == null
                && android.os.Process.SYSTEM_UID == context.getApplicationInfo().uid) {
            packageName = "android";
        }
        if (packageName == null) {
            return false;
        }
        if (packageName.equals("com.android.systemui")
                || packageName.equals("com.android.keyguard")
                || packageName.equals("com.android.settings")
                || packageName.equals("android")
                || context.getApplicationInfo().uid == android.os.Process.SYSTEM_UID) {
            return true;
        }
        if (onExemptBroadcast(context, packageName)) {
            return true;
        }
        return false;
    }
}
