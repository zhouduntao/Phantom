package com.tony.phantom.framework;

import android.os.Environment;

import com.tony.phantom.util.LogUtils;
import com.tony.phantom.util.SingletonUtils;
import com.tony.phantom.util.Toaster;

import java.io.File;

/**
 * @author create by zhouduntao on 2017/5/28 15:21
 */
public class PluginManager {

    private static final String TAG = PluginManager.class.getName();

    private static PluginManager sInstance;
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testplugindemo-debug.apk";
    private String pluginPkgName = "com.demo.tony.testplugindemo";
    private String mHostPackageName = "com.tony.phantom";

    public static PluginManager get() {
        return sInstance = SingletonUtils.checkSingleton(PluginManager.class, sInstance);
    }

    public String getPluginPath() {
        File fileDir = new File(Environment.getExternalStorageDirectory().getPath() + "/phantom/");
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }

        File[] files = fileDir.listFiles();
        if (files.length == 0){
            Toaster.show("no plugin");
            LogUtils.d(TAG,"getPluginPath " + "no apk");
        }

        pluginPath = files[0].getAbsolutePath();
        return pluginPath;
    }

    public String getPluginPkgName() {
        return pluginPkgName;
    }

    public String getHostPackageName() {
        return mHostPackageName;
    }
}
