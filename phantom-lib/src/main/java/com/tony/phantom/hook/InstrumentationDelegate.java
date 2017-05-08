package com.tony.phantom.hook;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

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
            Method execStartActivity = clazz.getDeclaredMethod("execStartActivity");
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

}
