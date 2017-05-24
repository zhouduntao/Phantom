package com.tony.phantom;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.tony.phantom.util.LogUtils;

/**
 * @author Tony
 * @version 1.0
 *          <p><strong>Features draft description.主要功能介绍<></p>
 * @since 2017/5/8 17:56
 */
public class PhantomApp extends Application {
    private static final String TAG = PhantomApp.class.getName();
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testPlugin.apk";

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LogUtils.d(TAG, "attachBaseContext");
        PantomCore.get().attach(base);

//        String optimizedDirectory = getFilesDir().getAbsolutePath();
//        PantomCore.get().install("com.tony.testplugin", pluginPath, optimizedDirectory, "", getClassLoader());
    }
}
