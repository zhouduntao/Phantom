package com.tony.phantom.hook;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.tony.phantom.util.LogUtils;

/**
 * Created by zhouduntao on 2017/5/8.
 */

public class InstrumentationDelegate extends Instrumentation {

    private static final String TAG = InstrumentationDelegate.class.getName();

    private Instrumentation mBase;

    public InstrumentationDelegate(Instrumentation base) {
        mBase = base;
    }

//    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
//                                                            Intent intent, int requestCode, Bundle options) {
//
//        try {
//            LogUtils.d(TAG, "execStartActivity you a be hook");
//            Class<?> clazz = Instrumentation.class;
//            Method execStartActivity = clazz.getDeclaredMethod("execStartActivity");
//            execStartActivity.setAccessible(true);
//
//            mInstrumentation.newActivity(
//                    cl, component.getClassName(), r.intent);
//
//            return (ActivityResult) execStartActivity.invoke(mBase, who, contextThread, token, target, intent, requestCode, options);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
         LogUtils.d(TAG, "newActivity you a be hook");
        LogUtils.d(TAG, "newActivity " + "className " + className);
        if (className.equals("com.tony.phantom.framework.ProxyActivity")){
            className = "com.tony.phantom.MainActivity";
        }
        return super.newActivity(cl, className, intent);
    }
}
