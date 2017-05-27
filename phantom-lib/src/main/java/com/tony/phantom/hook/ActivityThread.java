package com.tony.phantom.hook;

import android.app.Instrumentation;
import android.os.Handler;
import android.util.ArrayMap;

import com.tony.phantom.reflect.RefClass;
import com.tony.phantom.reflect.RefMethod;
import com.tony.phantom.reflect.RefObject;
import com.tony.phantom.reflect.RefStaticMethod;

import java.util.Map;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class ActivityThread {

    public static Class<?> TYPE = RefClass.load(ActivityThread.class, "android.app.ActivityThread");

    public static RefStaticMethod currentActivityThread;
    public static RefObject<Instrumentation> mInstrumentation;
    public static RefObject<Handler> mH;
    public static RefObject<ArrayMap> mResourcePackages;
    public static RefObject<Map> mPackages;
    public static RefMethod getPackageInfoNoCheck;

    public static class H {
        public static Class<?> TYPE = RefClass.load(H.class, "android.os.Handler");
        public static RefObject<Handler.Callback> mCallback;
    }

}
