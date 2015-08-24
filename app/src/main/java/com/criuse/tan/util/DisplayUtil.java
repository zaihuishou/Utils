package com.criuse.tan.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by zhiqiang on 8/24/15.
 */
public class DisplayUtil {
    public static final int COMPLEX_UNIT_PX = 0;
    public static final int COMPLEX_UNIT_DIP = 1;
    public static final int COMPLEX_UNIT_SP = 2;
    public static final int COMPLEX_UNIT_PT = 3;
    public static final int COMPLEX_UNIT_IN = 4;
    public static final int COMPLEX_UNIT_MM = 5;
    private static DisplayMetrics dm;

    /**
     * dip 2 px
     *
     * @param activity
     * @param dip
     * @return px value
     */
    public static float getDip2Px(Activity activity, float dip) {
        initDm(activity);
        return applyDimension(COMPLEX_UNIT_DIP, dip, dm);
    }

    /**
     * px 2 dip
     *
     * @param activity
     * @param px
     * @return
     */
    public static float getPx2Dip(Activity activity, float px) {
        return px / dm.density;
    }

    private static void initDm(Activity activity) {
        if (dm == null) {
            dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
    }

    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case COMPLEX_UNIT_PX:
                return value;
            case COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }
}
