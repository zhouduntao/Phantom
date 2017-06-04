package com.tony.phantom.hook;

import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.tony.phantom.reflect.RefClass;
import com.tony.phantom.reflect.RefMethod;
import com.tony.phantom.reflect.RefObject;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class ContextImpl {

    public static Class<?> TYPE = RefClass.load(ContextImpl.class, "android.app.ContextImpl");
    public static RefObject<Resources> mResources;
    public static RefObject<PackageManager> mPackageManager;
    public static RefObject<String> mBasePackageName;
    public static RefMethod<PackageManager> getPackageManager;

}
