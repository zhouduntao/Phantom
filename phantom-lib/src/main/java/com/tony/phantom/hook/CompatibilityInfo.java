package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefObject;
import com.tony.phantom.reflect.RefUtil;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class CompatibilityInfo {
    private static String hookClassName = "android.content.res.CompatibilityInfo";
    public static Class<?> Type = RefUtil.getClass(hookClassName);
    public static RefObject DEFAULT_COMPATIBILITY_INFO;

    static {
        DEFAULT_COMPATIBILITY_INFO = new RefObject(Type, "DEFAULT_COMPATIBILITY_INFO", null);
    }
}
