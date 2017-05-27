package com.tony.phantom.reflect;

import com.tony.phantom.exception.PhantomReflectException;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefObject<T> implements Refable {

    private Field mFiled;

    public RefObject(Class<?> clazz, Field filed) {
        mFiled = RefUtil.on(clazz).getField(filed.getName());
        if (mFiled == null) {
            new PhantomReflectException("can'n find field:" + filed.getName());
        }
    }

    public Field getFiled() {
        return mFiled;
    }

    public T get(Object obj) {
        try {
            return (T) mFiled.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Object object, Object value) {
        try {
            mFiled.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
