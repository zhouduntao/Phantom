package com.tony.phantom.reflect;

import com.tony.phantom.reflect.base.BaseRefObject;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefObject<T> extends BaseRefObject {


    public RefObject(Class<?> clazz, Field filed) {
        super(clazz, filed);
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
