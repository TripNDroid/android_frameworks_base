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
 * 
 */

package com.android.internal.util.td;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.ActivityOptions;
import android.app.IActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.HapticFeedbackConstants;
import android.view.IWindowManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.android.internal.telephony.PhoneConstants;
import com.android.internal.util.td.ActionConstants.Defaults;
import com.android.internal.util.td.Config.ActionConfig;
import com.android.internal.util.td.Config.ButtonConfig;
import com.android.internal.R;

public final class TDActionUtils {
    public static final String ANDROIDNS = "http://schemas.android.com/apk/res/android";
    public static final String PACKAGE_SYSTEMUI = "com.android.systemui";
    public static final String PACKAGE_ANDROID = "android";
    public static final String FORMAT_NONE = "none";
    public static final String FORMAT_FLOAT = "float";

    public static final String ID = "id";
    public static final String DIMEN = "dimen";
    public static final String DIMEN_PIXEL = "dimen_pixel";
    public static final String FLOAT = "float";
    public static final String INT = "integer";
    public static final String COLOR = "color";
    public static final String BOOL = "bool";
    public static final String STRING = "string";
    public static final String ANIM = "anim";

    // 10 inch tablets
    public static boolean isXLargeScreen() {
        int screenLayout = Resources.getSystem().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    // 7 inch "phablets" i.e. grouper
    public static boolean isLargeScreen() {
        int screenLayout = Resources.getSystem().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    // normal phones
    public static boolean isNormalScreen() {
        int screenLayout = Resources.getSystem().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenLayout == Configuration.SCREENLAYOUT_SIZE_NORMAL;
    }

    public static boolean isLandscape(Context context) {
        return Configuration.ORIENTATION_LANDSCAPE
                == context.getResources().getConfiguration().orientation;
    }

    public static boolean navigationBarCanMove() {
        return Resources.getSystem().getConfiguration().smallestScreenWidthDp < 600;
    }

    public static boolean hasNavbarByDefault(Context context) {
        boolean needsNav = (Boolean)getValue(context, "config_showNavigationBar", BOOL, PACKAGE_ANDROID);
        String navBarOverride = SystemProperties.get("qemu.hw.mainkeys");
        if ("1".equals(navBarOverride)) {
            needsNav = false;
        } else if ("0".equals(navBarOverride)) {
            needsNav = true;
        }
        return needsNav;
    }

    public static boolean deviceSupportsLte(Context ctx) {
        final TelephonyManager tm = (TelephonyManager)
                ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLteOnCdmaMode() == PhoneConstants.LTE_ON_CDMA_TRUE;
    }

    public static boolean deviceSupportsDdsSupported(Context context) {
        TelephonyManager tm = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.isMultiSimEnabled()
                && tm.getMultiSimConfiguration() == TelephonyManager.MultiSimVariants.DSDA;
    }

    public static boolean deviceSupportsMobileData(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        return cm.isNetworkSupported(ConnectivityManager.TYPE_MOBILE);
    }

    public static boolean deviceSupportsBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static boolean deviceSupportsNfc(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_NFC);
    }

    public static boolean deviceSupportsFlashLight(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(
                Context.CAMERA_SERVICE);
        try {
            String[] ids = cameraManager.getCameraIdList();
            for (String id : ids) {
                CameraCharacteristics c = cameraManager.getCameraCharacteristics(id);
                Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                if (flashAvailable != null
                        && flashAvailable
                        && lensFacing != null
                        && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    return true;
                }
            }
        } 
        catch (CameraAccessException | AssertionError e) {
      }
      return false;
    }

    public static boolean deviceSupportsCompass(Context context) {
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        return sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null
                && sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null;
    }

    public static boolean deviceSupportsDoze(Context context) {
        String name = (String) getValue(context, "config_dozeComponent",
                STRING, PACKAGE_ANDROID);
        return !TextUtils.isEmpty(name);
    }

    public static boolean killForegroundApp(Context context, int userId) {
        try {
            return killForegroundAppInternal(context, userId);
        } catch (RemoteException e) {
            Log.e("TDActionUtils", "Could not kill foreground app");
        }
        return false;
    }

    private static boolean killForegroundAppInternal(Context context, int userId)
            throws RemoteException {
        try {
            final Intent intent = new Intent(Intent.ACTION_MAIN);
            String defaultHomePackage = "null";
            intent.addCategory(Intent.CATEGORY_HOME);
            final ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);

            if (res.activityInfo != null && !res.activityInfo.packageName.equals("android")) {
                defaultHomePackage = res.activityInfo.packageName;
            }

            IActivityManager am = ActivityManagerNative.getDefault();
            List<ActivityManager.RunningAppProcessInfo> apps = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appInfo : apps) {
                int uid = appInfo.uid;
                if (uid >= Process.FIRST_APPLICATION_UID && uid <= Process.LAST_APPLICATION_UID
                        && appInfo.importance ==
                        ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    if (appInfo.pkgList != null && (appInfo.pkgList.length > 0)) {
                        for (String pkg : appInfo.pkgList) {
                            if (!pkg.equals("com.android.systemui")) {
                                am.forceStopPackage(pkg, UserHandle.USER_CURRENT);
                                return true;
                            }
                        }
                    } else {
                        Process.killProcess(appInfo.pid);
                        return true;
                    }
                }
            }
        } catch (RemoteException remoteException) {
            // Do nothing; just let it go.
        }
        return false;
    }

    /* utility to iterate a viewgroup and return a list of child views */
    public static ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    /* utility to iterate a viewgroup and return a list of child views of type */
    public static <T extends View> ArrayList<T> getAllChildren(View root, Class<T> returnType) {
        if (!(root instanceof ViewGroup)) {
            ArrayList<T> viewArrayList = new ArrayList<T>();
            try {
                viewArrayList.add(returnType.cast(root));
            } catch (Exception e) {
                // handle all exceptions the same and silently fail
            }
            return viewArrayList;
        }
        ArrayList<T> result = new ArrayList<T>();
        ViewGroup vg = (ViewGroup) root;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            ArrayList<T> viewArrayList = new ArrayList<T>();
            try {
                viewArrayList.add(returnType.cast(root));
            } catch (Exception e) {
                // handle all exceptions the same and silently fail
            }
            viewArrayList.addAll(getAllChildren(child, returnType));
            result.addAll(viewArrayList);
        }
        return result;
    }

    public static void resolveAndUpdateButtonActions(Context ctx, Defaults defaults) {
        if (ctx == null || defaults == null) {
            return;
        }
        boolean configChanged = false;
        final PackageManager pm = ctx.getPackageManager();
        ArrayList<ButtonConfig> configs = Config.getConfig(ctx, defaults);
        ArrayList<ButtonConfig> buttonsToChange = new ArrayList<ButtonConfig>();
        buttonsToChange.addAll(configs);
        for (int h = 0; h < configs.size(); h++) {
            ButtonConfig button = configs.get(h);
            for (int i = 0; i < 3; i++) {
                ActionConfig action = button.getActionConfig(i);
                final String task = action.getAction();
                if (task.startsWith(ActionHandler.SYSTEM_PREFIX)) {
                    continue;
                }
                String resolvedName = getFriendlyNameForUri(ctx, task);
                if (resolvedName == null || TextUtils.equals(resolvedName, task)) {
                    // if resolved name is null or the full raw intent string is
                    // returned, we were unable to resolve
                    configChanged = true;
                    ActionConfig newAction = new ActionConfig(ctx,
                            ActionHandler.SYSTEMUI_TASK_NO_ACTION, action.getIconUri());
                    ButtonConfig newButton = buttonsToChange.get(h);
                    newButton.setActionConfig(newAction, i);
                    buttonsToChange.remove(h);
                    buttonsToChange.add(h, newButton);
                }
            }
        }
        if (configChanged) {
            Config.setConfig(ctx, defaults, buttonsToChange);
        }
    }

    public static Intent getIntent(String uri) {
        if (uri == null || uri.startsWith(ActionHandler.SYSTEM_PREFIX)) {
            return null;
        }

        Intent intent = null;
        try {
            intent = Intent.parseUri(uri, 0);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return intent;
    }

    public static Object getValue(Context context, String resName, String resType, String pkg) {
        return getValue(context, resName, resType, null, pkg);
    }

    public static Object getValue(Context context, String resName, String resType, String format,
            String pkg) {
        Resources res = getResourcesForPackage(context, pkg);
        String tmp;
        if (resType.equals(DIMEN_PIXEL)) {
            tmp = DIMEN;
        } else {
            tmp = resType;
        }
        int id = res.getIdentifier(resName, tmp, pkg);
        if (format != null) { // standard res
            TypedValue typedVal = new TypedValue();
            res.getValue(id, typedVal, true);
            if (format.equals(FORMAT_FLOAT)) {
                return Float.valueOf(typedVal.getFloat());
            }
        } else { // typed values
            if (resType.equals(ID)) {
                return Integer.valueOf(id);
            } else if (resType.equals(DIMEN)) {
                return Float.valueOf(res.getDimension(id));
            } else if (resType.equals(DIMEN_PIXEL)) {
                return Integer.valueOf(res.getDimensionPixelSize(id));
            } else if (resType.equals(FLOAT)) {
                return Float.valueOf(res.getFloat(id));
            } else if (resType.equals(INT)) {
                return Integer.valueOf(res.getInteger(id));
            } else if (resType.equals(COLOR)) {
                int rawColor = res.getColor(id);
                return Integer.valueOf(Color.argb(Color.alpha(rawColor), Color.red(rawColor),
                        Color.green(rawColor), Color.blue(rawColor)));
            } else if (resType.equals(BOOL)) {
                return Boolean.valueOf(res.getBoolean(id));
            } else if (resType.equals(STRING)) {
                return String.valueOf(res.getString(id));
            }
        }
        return null;
    }

    public static void putValue(String key, Object val, String type, Bundle b) {
        if (type.equals(ID) || type.equals(DIMEN_PIXEL) || type.equals(INT) || type.equals(COLOR)) {
            b.putInt(key, (Integer) val);
        } else if (type.equals(FLOAT) || type.equals(DIMEN)) {
            b.putFloat(key, (Float) val);
        } else if (type.equals(BOOL)) {
            b.putBoolean(key, (Boolean) val);
        } else if (type.equals(STRING)) {
            b.putString(key, (String) val);
        }
    }

    public static int getIdentifier(Context context, String resName, String resType, String pkg) {
        try {
            Resources res = context.getPackageManager()
                    .getResourcesForApplication(pkg);
            int ident = res.getIdentifier(resName, resType, pkg);
            return ident;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getString(Context context, String resName, String pkg) {
        return (String) getValue(context, resName, STRING, null, pkg);
    }

    public static boolean getBoolean(Context context, String resName, String pkg) {
        return (Boolean) getValue(context, resName, BOOL, null, pkg);
    }

    public static int getInt(Context context, String resName, String pkg) {
        return (Integer) getValue(context, resName, INT, null, pkg);
    }

    public static int getColor(Context context, String resName, String pkg) {        
        return (Integer) getValue(context, resName, COLOR, null, pkg);
    }

    public static int getId(Context context, String resName, String pkg) {
        return (Integer) getValue(context, resName, ID, null, pkg);
    }

    public static float getDimen(Context context, String resName, String pkg) {
        return (Float) getValue(context, resName, DIMEN, null, pkg);
    }

    public static int getDimenPixelSize(Context context, String resName, String pkg) {
        return (Integer) getValue(context, resName, DIMEN_PIXEL, null, pkg);
    }

    public static String getFriendlyActivityName(PackageManager pm, Intent intent,
            boolean labelOnly) {
        ActivityInfo ai = intent.resolveActivityInfo(pm, PackageManager.GET_ACTIVITIES);
        String friendlyName = null;
        if (ai != null) {
            friendlyName = ai.loadLabel(pm).toString();
            if (friendlyName == null && !labelOnly) {
                friendlyName = ai.name;
            }
        }
        return friendlyName != null || labelOnly ? friendlyName : intent.toUri(0);
    }

    public static String getFriendlyShortcutName(PackageManager pm, Intent intent) {
        String activityName = getFriendlyActivityName(pm, intent, true);
        String name = intent.getStringExtra(Intent.EXTRA_SHORTCUT_NAME);

        if (activityName != null && name != null) {
            return activityName + ": " + name;
        }
        return name != null ? name : intent.toUri(0);
    }

    public static String getFriendlyNameForUri(Context ctx, String uri) {
        if (uri == null) {
            return null;
        }
        if (uri.startsWith(ActionHandler.SYSTEM_PREFIX)) {
            for (int i = 0; i < ActionHandler.systemActions.length; i++) {
                if (ActionHandler.systemActions[i].mAction.equals(uri)) {
                    return getString(ctx, ActionHandler.systemActions[i].mLabelRes,
                            ActionHandler.systemActions[i].mResPackage);
                }
            }
        } else {
            try {
                Intent intent = Intent.parseUri(uri, 0);
                if (Intent.ACTION_MAIN.equals(intent.getAction())) {
                    return getFriendlyActivityName(ctx.getPackageManager(), intent, false);
                }
                return getFriendlyShortcutName(ctx.getPackageManager(), intent);
            } catch (URISyntaxException e) {
            }
        }
        return uri;
    }

    public static Resources getResourcesForPackage(Context ctx, String pkg) {
        try {
            Resources res = ctx.getPackageManager()
                    .getResourcesForApplication(pkg);
            return res;
        } catch (Exception e) {
            return ctx.getResources();
        }
    }
}
