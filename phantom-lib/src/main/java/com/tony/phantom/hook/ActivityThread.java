package com.tony.phantom.hook;

import android.app.Instrumentation;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.util.ArrayMap;

import com.tony.phantom.reflect.RefMethod;
import com.tony.phantom.reflect.RefObject;
import com.tony.phantom.reflect.RefUtil;

import java.util.Map;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class ActivityThread {

    private static String hookClassName = "android.app.ActivityThread";
    public static Class<?> Type = RefUtil.getClass(hookClassName);

    public static RefMethod currentActivityThread;
    public static Object sCurrentActivityThread;
    public static RefObject<Instrumentation> mInstrumentation;
    public static RefObject<Handler> mH;
    public static RefObject<ArrayMap> mResourcePackages;
    public static RefObject<Map> mPackages;
    public static RefMethod getPackageInfoNoCheck;


    static {
        currentActivityThread = new RefMethod(Type, "currentActivityThread");
        sCurrentActivityThread = currentActivityThread.invoke(null);
        mInstrumentation = new RefObject(Type, "mInstrumentation", sCurrentActivityThread);
        mH = new RefObject(Type, "mH", sCurrentActivityThread);
        mPackages = new RefObject(Type, "mPackages", sCurrentActivityThread);
        getPackageInfoNoCheck = new RefMethod(Type, "getPackageInfoNoCheck", ApplicationInfo.class, CompatibilityInfo.Type);
    }
}
