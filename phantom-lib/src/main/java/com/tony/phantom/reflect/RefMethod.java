package com.tony.phantom.reflect;

import java.lang.reflect.Method;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefMethod<T> implements Refable {

    Method mMethod;

    public RefMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        mMethod = RefUtil.on(clazz).getMethod(methodName, parameterTypes);
    }

    public RefMethod(String className, String methodName, Class<?>... parameterTypes) {
        this(RefUtil.getClass(className), methodName, parameterTypes);
    }

    public T invoke(Object receiver, Object... args) {
        return (T) RefUtil.on(mMethod).invoke(receiver, args);
    }

}
