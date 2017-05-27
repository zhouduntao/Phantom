package com.tony.phantom.reflect;

import com.tony.phantom.reflect.base.BaseRefMethod;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefMethod<T> extends BaseRefMethod{

    public RefMethod(Class<?> tartClass, Field field) {
        super(tartClass, field);
    }

    public T call(Object receiver, Object... args) {
        return (T) RefUtil.on(mMethod).invoke(receiver, args);
    }

}
