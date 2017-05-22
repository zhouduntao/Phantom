package com.tony.phantom.hook;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.tony.phantom.framework.ProxyActivity;
import com.tony.phantom.global.Constant;
import com.tony.phantom.util.LogUtils;

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

//            ComponentName component = new ComponentName("com.tony.phantom", "com.tony.phantom.ProxyActivity");
//            intent.setComponent(null);
//            intent.setComponent(component);

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
}
