package com.criuse.tan.app;

import android.app.Application;

import com.criuse.tan.R;
import cruise.library.util.LogUtil;

/**
 * Created by zhiqiang on 8/27/15.
 */
public class MyApplicaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogUtil();
    }

    protected void initLogUtil() {
        LogUtil.isDebug = false;
        LogUtil.TAG = getString(R.string.app_name);
    }
}
