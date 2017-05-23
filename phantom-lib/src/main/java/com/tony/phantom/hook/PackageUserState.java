package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefUtil;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class PackageUserState {
    private static String hookClassName = "android.content.pm.PackageUserState";
    public static Class<?> Type = RefUtil.getClass(hookClassName);

    public static Object newInstance(){
        return RefUtil.newInstance(Type);
    }
}
