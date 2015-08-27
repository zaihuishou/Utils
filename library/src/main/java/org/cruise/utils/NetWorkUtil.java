package org.cruise.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by zhiqiang on 8/27/15.
 */
public class NetWorkUtil extends BaseUtil {
    public final static String NETWORK_TYPE_WIFI = "WIFI";
    public final static String NETWORK_TYPE_2G = "2G";
    public final static String NETWORK_TYPE_3G = "3G";
    public final static String NETWORK_TYPE_LTE = "4G";

    private NetWorkUtil() {
        super();
    }

    /**
     * UnKnow type
     */
    public final static String NETWORK_TYPE_UNKNOW = "unknow";

    /**
     * whether network connectivity exists and it is possible to establish
     *
     * @param context
     * @return state of NewWork
     */
    public static boolean isNetWorkConnected(Context context) {
        NetworkInfo networkInfo = getNetWorkInfo(context);
        return networkInfo == null ? false : networkInfo.isConnected();
    }

    /**
     * 判断是否为wifi连接
     *
     * @param context 上下文
     * @return
     */
    public static boolean isWifi(Context context) {
        return NETWORK_TYPE_WIFI.equals(getNetWorkType(context));
    }

    /**
     * @param context
     * @return NewWork type
     */
    public static String getNetWorkType(Context context) {
        NetworkInfo netWorkInfo = getNetWorkInfo(context);
        if (netWorkInfo == null) return NETWORK_TYPE_UNKNOW;
        String type = NETWORK_TYPE_UNKNOW;
        switch (netWorkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                type = NETWORK_TYPE_WIFI;
                break;
            case ConnectivityManager.TYPE_MOBILE:
                switch (netWorkInfo.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS: //联通2g
                    case TelephonyManager.NETWORK_TYPE_CDMA: //电信2g
                    case TelephonyManager.NETWORK_TYPE_EDGE: //移动2g
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        type = NETWORK_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EVDO_A: //电信3g
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        type = NETWORK_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        type = NETWORK_TYPE_LTE;
                        break;
                }
                break;
        }
        return type;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    protected static NetworkInfo getNetWorkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }
}
