package com.tony.phantom.reflect.base;

import com.tony.phantom.annotation.MethodParameter;
import com.tony.phantom.exception.PhantomReflectException;
import com.tony.phantom.reflect.RefUtil;
import com.tony.phantom.reflect.Refable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhouduntao on 2017/5/27.
 */

public class BaseRefMethod implements Refable {

    public Method mMethod;

    public BaseRefMethod(Class<?> tartClass, Field field) {
        boolean hasMethodParameter = field.isAnnotationPresent(MethodParameter.class);
        Class<?>[] parameterTypes = null;
        if (hasMethodParameter) {
            parameterTypes = field.getAnnotation(MethodParameter.class).value();
        }

        mMethod = RefUtil.on(tartClass).getMethod(field.getName(), parameterTypes);
        checkNull(mMethod, field.getName());
    }

    private void checkNull(Object method, String name) {
        if (mMethod == null) {
            new PhantomReflectException("can'n find method:" + name);
        }
    }
}
