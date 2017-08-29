package com.changxiao.utilscollectiondemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * DeviceInfo utils used for application.
 * <p>
 * Created by Chang.Xiao on 2016/7/1.
 *
 * @version 1.0
 */
public class DeviceInfo {

    private static Context mContext;
    private static String mVersion;
    private static String mVersionName;
    private static String mDeviceID;

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * system type
     *
     * @return android
     */
    public static String getOSName() {
        return "android";
    }

    /**
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getOSCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * @return
     */
    public static String getDeviceName() {
        return Build.MANUFACTURER + "_" + Build.PRODUCT;
    }

    /**
     * 手机的OS版本号：4.1.2
     *
     * @return
     */
    public static String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @return
     */
    public static String getVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 设备的DeviceId,如果获取不到，就将该手机的Wifi Mac地址作为唯一识别码
     *
     * @return
     */
    public static String getDeviceID() {
        if (null == mDeviceID) {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            mDeviceID = tm.getDeviceId();
            if (mDeviceID == null || mDeviceID.trim().length() == 0) {
                WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                mDeviceID = info.getMacAddress();
            }
        }
        return null == mDeviceID ? "" : mDeviceID;
    }

    /**
     * @return 0 -获取失败 >0 -版本号
     */
    public static PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            pi = mContext.getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 手机版本号
     *
     * @return versionCode
     */
    public static String getClientVersion() {
        if (null == mVersion) {
            try {
                mVersion = String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null == mVersion ? "" : mVersion;
    }

    /**
     * 手机的版本描述信息
     *
     * @return versionName
     */
    public static String getClientVersionName() {
        if (null == mVersionName) {
            try {
                mVersionName = String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null == mVersionName ? "" : mVersionName;
    }

    /**
     * 得到分辨率
     *
     * @return
     */
    public static String getResolution() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width + "*" + height;
    }

    /**
     *
     *
     * @return
     */
    public static int getDeviceHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     *
     *
     * @return
     */
    public static int getDeviceWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static Point getScreenSize() {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(out);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            out.set(width, height);
        }
        return out;
    }

    public static int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    //	/**
    //	 * 40063电信插卡机
    //	 *
    //	 * @return
    //	 */
    //	public static String getIMEI(Context context) {
    //		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    //		return telephonyManager.getDeviceId();
    //	}

    /**
     * Gps是否可用
     *
     * @return
     */
    public static boolean isGpsEnable() {
        LocationManager locationManager = ((LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE));
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
