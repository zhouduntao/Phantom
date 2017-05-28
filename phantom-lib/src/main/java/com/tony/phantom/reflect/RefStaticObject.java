package com.tony.phantom.reflect;

import com.tony.phantom.reflect.base.BaseRefObject;

import java.lang.reflect.Field;

/**
 * @author create by zhouduntao on 2017/5/28 10:49
 */
public class RefStaticObject<T> extends BaseRefObject {

    public RefStaticObject(Class<?> clazz, Field filed) {
        super(clazz, filed);
    }

    public T get() {
        try {
            return (T) mFiled.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Object value) {
        try {
            mFiled.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
