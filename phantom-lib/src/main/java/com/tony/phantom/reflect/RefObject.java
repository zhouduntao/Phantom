package com.tony.phantom.reflect;

import java.lang.reflect.Field;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefObject<T> implements Refable {

    private T mObject;
    private Field mFiled;

    public RefObject(String className, String fieldName, Object Obj) {
        this(RefUtil.getClass(className), fieldName, Obj);
    }

    public RefObject(Class<?> clazz, String fieldName, Object Obj) {
        mFiled = RefUtil.on(clazz).getField(fieldName);
        if (Obj != null){
            mObject = (T) RefUtil.on(mFiled).get(Obj);
        }
    }

    public Field getFiled(){
        return mFiled;
    }

    public T get() {
        return mObject;
    }

    public void set(Object object,Object value) {
        try {
            mFiled.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
