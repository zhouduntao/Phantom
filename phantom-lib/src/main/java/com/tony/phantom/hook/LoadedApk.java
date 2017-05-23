package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefObject;
import com.tony.phantom.reflect.RefUtil;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class LoadedApk {

    private static String hookClassName = "android.app.LoadedApk";
    public static Class<?> Type = RefUtil.getClass(hookClassName);
    public static RefObject<ClassLoader> mClassLoader;

    static {
        mClassLoader = new RefObject(Type, "mClassLoader", null);
    }

}
