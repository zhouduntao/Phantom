package com.tony.phantom.reflect;

import com.tony.phantom.reflect.base.BaseRefMethod;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/27.
 */

public class RefStaticMethod<T> extends BaseRefMethod {

    public RefStaticMethod(Class<?> tartClass, Field field) {
        super(tartClass, field);
    }

    public T call(Object... args) {
        return (T) RefUtil.on(mMethod).invoke(args);
    }
}
