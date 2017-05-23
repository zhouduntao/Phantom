package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefObject;
import com.tony.phantom.reflect.RefUtil;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class ContextImpl {
    private static String hookClassName = "android.app.ContextImpl";
    public static Class<?> Type = RefUtil.getClass(hookClassName);

    public static RefObject mResources;

    static {
        mResources = new RefObject(Type, "mResources", null);
    }
}
