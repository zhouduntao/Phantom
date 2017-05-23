package com.tony.phantom.hook;

import com.tony.phantom.util.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class HookIActivityManager implements InvocationHandler {

    private static final String TAG = HookIActivityManager.class.getName();
    private Object mObject;

    public HookIActivityManager(Object object) {
        mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtils.d(TAG,"invoke " + method.getName());
        return method.invoke(mObject,args);
//        return RefUtil.on(method).invoke(proxy, args);
    }
}
