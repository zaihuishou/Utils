package com.criuse.tan.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by zhiqiang on 8/27/15.
 */
public class NetWorkUtil {
    public final static String NETWORK_TYPE_WIFI = "WIFI";
    public final static String NETWORK_TYPE_2G = "2G";
    public final static String NETWORK_TYPE_3G = "3G";
    public final static String NETWORK_TYPE_LTE = "4G";
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

    protected static NetworkInfo getNetWorkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }
}
