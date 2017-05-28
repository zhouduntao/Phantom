package com.tony.phantom.hook;

import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;

import com.tony.phantom.global.Constant;
import com.tony.phantom.util.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/27.
 */

public class HCallbackHook implements Handler.Callback{

    private static final String TAG = HCallbackHook.class.getName();

    Handler mH;
    public HCallbackHook(Handler h) {
        mH = h;
    }

    @Override
    public boolean handleMessage(Message msg) {




        LogUtils.d(TAG, "handleMessage " + "");
        if (msg.what == 100){
            Object obj = msg.obj;
//            ActivityClientRecord
            try {
                Class<?> aClass = Class.forName("android.app.ActivityThread$ActivityClientRecord");
                Field intentField = aClass.getDeclaredField("intent");
                intentField.setAccessible(true);
                Intent intent = (Intent)intentField.get(obj);
                ComponentName targetComponentName = intent.getParcelableExtra(Constant.Key.TARGET_COMPONNET);
                if (targetComponentName != null){
                    intent.setComponent(targetComponentName);
//                    String name = targetComponentName.getClassName();
//                    try {
//                        Object o = Class.forName(name).newInstance();
//                        LogUtils.d(TAG, "handleMessage targetComponentName: " + o);
//                    } catch (InstantiationException e) {
//                        e.printStackTrace();
//                    }
//                    return true;
                }
                LogUtils.d(TAG, "handleMessage targetComponentName: " + targetComponentName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mH.handleMessage(msg);
        return true;
//        return false;
    }
}
