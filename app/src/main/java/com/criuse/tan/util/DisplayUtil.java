package com.criuse.tan.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by zhiqiang on 8/24/15.
 */
public class DisplayUtil {
    public static final int UNIT_PX = 0;
    public static final int UNIT_DIP = 1;
    public static final int UNIT_SP = 2;
    public static final int UNIT_PT = 3;
    public static final int UNIT_IN = 4;
    public static final int UNIT_MM = 5;
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
        return applyDimension(UNIT_DIP, dip, dm);
    }

    /**
     * px 2 dip
     *
     * @param activity
     * @param px
     * @return
     */
    public static float getPx2Dip(Activity activity, float px) {
        initDm(activity);
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
            case UNIT_PX:
                return value;
            case UNIT_DIP:
                return value * metrics.density;
            case UNIT_SP:
                return value * metrics.scaledDensity;
            case UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case UNIT_IN:
                return value * metrics.xdpi;
            case UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }

    /**
     * Returns the visibility for this view.
     *
     * @param v
     * @return
     */
    public static boolean isViewShow(View v) {
        return v.getVisibility() == View.VISIBLE;
    }
}
