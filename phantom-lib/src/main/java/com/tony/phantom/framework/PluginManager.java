package com.tony.phantom.framework;

import android.os.Environment;

import com.tony.phantom.util.SingletonUtils;

/**
 * @author create by zhouduntao on 2017/5/28 15:21
 */
public class PluginManager {

    private static PluginManager sInstance;
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testplugindemo-debug.apk";
    private String pluginPkgName = "com.demo.tony.testplugindemo";
    private String mHostPackageName = "com.tony.phantom";

    public static PluginManager get() {
        return sInstance = SingletonUtils.checkSingleton(PluginManager.class, sInstance);
    }

    public String getPluginPath() {
        return pluginPath;
    }

    public String getPluginPkgName() {
        return pluginPkgName;
    }

    public String getHostPackageName() {
        return mHostPackageName;
    }
}
