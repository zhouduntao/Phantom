package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefMethod;
import com.tony.phantom.reflect.RefUtil;

import java.io.File;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class PackageParser {

    private static String hookClassName = "android.content.pm.PackageParser";
    public static Class<?> Type = RefUtil.getClass(hookClassName);
    public static RefMethod parsePackage;
    public static RefMethod generateApplicationInfo;

    static {
        parsePackage = new RefMethod(Type, "parsePackage", File.class, int.class);
        generateApplicationInfo =
                new RefMethod(Type, "generateApplicationInfo",
                        Package.Type, int.class, PackageUserState.Type);
    }

    public static class Package {
        private static String hookClassName = "android.content.pm.PackageParser$Package";
        public static Class<?> Type = RefUtil.getClass(hookClassName);
    }

}
