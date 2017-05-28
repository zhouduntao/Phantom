package com.demo.tony.testplugindemo;

import android.app.Application;
import android.content.Context;

/**
 * @author create by zhouduntao on 2017/5/28 15:17
 */
public class PluginApp extends Application {

    private static final String TAG = PluginApp.class.getName();
    
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, " onCreate " + "");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LogUtils.d(TAG, "attachBaseContext " + "");
    }
}
