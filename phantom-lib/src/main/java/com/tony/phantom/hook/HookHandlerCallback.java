package com.tony.phantom.hook;

import android.os.Handler;
import android.os.Message;

import com.tony.phantom.util.LogUtils;

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
        mH.handleMessage(msg);
        return true;
//        return false;
    }
}
