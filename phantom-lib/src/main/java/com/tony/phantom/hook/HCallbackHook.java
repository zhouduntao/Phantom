package com.tony.phantom.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.tony.phantom.global.Constant;
import com.tony.phantom.util.LogUtils;


/**
 * Created by zhouduntao on 2017/5/27.
 */

public class HCallbackHook implements Handler.Callback {

    private static final String TAG = HCallbackHook.class.getName();

    Handler mH;

    public HCallbackHook(Handler h) {
        mH = h;
    }

    @Override
    public boolean handleMessage(Message msg) {

        LogUtils.d(TAG, "handleMessage " + "");
        if (msg.what == 100) {
            Object r = msg.obj;

            Intent intent = ActivityThread.ActivityClientRecord.intent.get(r);
            ComponentName targetComponentName = intent.getParcelableExtra(Constant.Key.TARGET_COMPONNET);
            if (targetComponentName != null) {
                intent.setComponent(targetComponentName);
            }

            ActivityThread.ActivityClientRecord.activityInfo.get(r);

            LogUtils.d(TAG, "handleMessage targetComponentName: " + targetComponentName);
        }
        mH.handleMessage(msg);
        return true;
    }
}
