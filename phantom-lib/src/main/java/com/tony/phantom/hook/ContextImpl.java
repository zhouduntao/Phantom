package com.tony.phantom.hook;

import com.tony.phantom.reflect.RefClass;
import com.tony.phantom.reflect.RefObject;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class ContextImpl {

    public static Class<?> TYPE = RefClass.load(ActivityThread.class, "android.app.ContextImpl");
    public static RefObject mResources;

}
