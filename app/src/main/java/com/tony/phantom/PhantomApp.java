package com.tony.phantom;

import android.app.Application;
import android.content.Context;

import com.tony.phantom.framework.PluginManager;
import com.tony.phantom.util.LogUtils;

/**
 * @author Tony
 * @version 1.0
 *          <p><strong>Features draft description.主要功能介绍<></p>
 * @since 2017/5/8 17:56
 */
public class PhantomApp extends Application {
    private static final String TAG = PhantomApp.class.getName();


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        LogUtils.d(TAG, "attachBaseContext");
        PhantomCore.get().attach(base);
//
        String optimizedDirectory = getFilesDir().getAbsolutePath();
        PhantomCore.get().install(PluginManager.get().getPluginPkgName(), PluginManager.get().getPluginPath(), optimizedDirectory, "", getClassLoader());
    }
}
