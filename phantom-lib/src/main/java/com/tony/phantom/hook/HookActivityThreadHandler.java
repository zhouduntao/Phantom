package com.tony.phantom.hook;


import android.os.Handler;
import android.os.Message;

import com.tony.phantom.util.LogUtils;

/**
 * @author zhouduntao
 */

public class HookActivityThreadHandler extends Handler {

    private static final String TAG = HookActivityThreadHandler.class.getName();

    private Handler mH;

    public HookActivityThreadHandler(Handler h) {
        mH = h;
    }

    @Override
    public void handleMessage(Message msg) {
//        if (msg.what == 100) {
//            return;
//        }
        LogUtils.d(TAG, "handleMessage " + "");

        mH.handleMessage(msg);
    }
}
