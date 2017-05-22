package com.tony.phantom.hook;

import android.os.Handler;
import android.os.Message;

import com.tony.phantom.util.LogUtils;

import java.lang.reflect.Field;

/**
 * @author create by zhouduntao on 2017/5/21 0:10
 */
public class HookHandlerCallback implements Handler.Callback{
    
    private static final String TAG = HookHandlerCallback.class.getName();

    Handler mH;
    public HookHandlerCallback(Handler h) {
        mH = h;
    }

    @Override
    public boolean handleMessage(Message msg) {
        LogUtils.d(TAG, "handleMessage " + "");
        if (msg.what == 100){
            Object obj = msg.obj;
//            ActivityClientRecord
            try {
                Class<?> aClass = Class.forName("android.app.ActivityClientRecord");
                Field intent = aClass.getDeclaredField("intent");
                intent.setAccessible(true);
                Object o = intent.get(obj);
                
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
