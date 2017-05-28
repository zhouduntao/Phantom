package com.tony.phantom.hook;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

import com.tony.phantom.PhantomCore;
import com.tony.phantom.framework.ProxyActivity;
import com.tony.phantom.global.Constant;
import com.tony.phantom.util.LogUtils;
import com.tony.phantomframework.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhouduntao on 2017/5/8.
 */

public class InstrumentationDelegate extends Instrumentation {

    private static final String TAG = InstrumentationDelegate.class.getName();

    private Instrumentation mBase;

    public InstrumentationDelegate(Instrumentation base) {
        mBase = base;
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                                            Intent intent, int requestCode, Bundle options) {

        try {
            LogUtils.d(TAG, "execStartActivity you a be hook");
            Class<?> clazz = Instrumentation.class;

            ComponentName targetComponent = intent.getComponent();
            intent.putExtra(Constant.Key.TARGET_COMPONNET, targetComponent);

            ComponentName proxyComponnet = new ComponentName(who, ProxyActivity.class.getName());
            intent.setComponent(proxyComponnet);

            Method execStartActivity =
                    clazz.getDeclaredMethod("execStartActivity",
                            Context.class, IBinder.class, IBinder.class,
                            Activity.class, Intent.class, int.class,
                            Bundle.class);
            execStartActivity.setAccessible(true);

            return (ActivityResult) execStartActivity.invoke(mBase, who, contextThread, token, target, intent, requestCode, options);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        LogUtils.d(TAG, "newActivity you a be hook");
        LogUtils.d(TAG, "newActivity " + "className " + className);
//        if (className.equals("com.tony.phantom.framework.ProxyActivity")){
//            className = "com.tony.phantom.MainActivity";
//        }
        return super.newActivity(cl, className, intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        Context context = activity;
        context = ((ContextWrapper) context).getBaseContext();
        ContextImpl.mPackageManager.set(context, null);
        ContextImpl.getPackageManager.call(context);
        PackageManager manager = context.getPackageManager();

        Resources resourse = PhantomCore.get().getResourse();


        LogUtils.d(TAG, "callActivityOnCreate " + "isProxyProcess: " + PhantomCore.get().isProxyProcess());
        LogUtils.d(TAG, "callActivityOnCreate " + "isProxyProcess: " + PhantomCore.get().getCurrentProcess());
//        if (PhantomCore.get().isProxyProcess()) {
        try {
            String name = context.getResources().getResourceName(R.string.app_name);
            LogUtils.d(TAG, "callActivityOnCreate " + "name: " + name);
        } catch (Exception e) {
            LogUtils.d(TAG, "callActivityOnCreate " + "null");
        }
//        try {
//            Field resources = AppCompatActivity.class.getDeclaredField("mResources");
//            resources.setAccessible(true);
//            resources.set(activity,resourse);
//
//
//        } catch (NoSuchFieldException e) {
//
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }



        LogUtils.d(TAG, "callActivityOnCreate getResources before hook " + context.getResources());
        LogUtils.d(TAG, "callActivityOnCreate getResources before hook activity " + activity.getResources());
//        ContextImpl.mResources.set(context, resourse);
//        LogUtils.d(TAG, "callActivityOnCreate getResources after hook " + context.getResources());
//        LogUtils.d(TAG, "callActivityOnCreate getResources after hook activity" + activity.getResources());

        ComponentName relative = ComponentName.createRelative(context, "com.tony.phantom.MainActivity");
        try {
            manager.getActivityInfo(relative, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        super.callActivityOnCreate(activity, icicle);

    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        super.callActivityOnCreate(activity, icicle, persistentState);
    }
}
