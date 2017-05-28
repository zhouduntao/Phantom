package com.tony.phantom.reflect.base;

import com.tony.phantom.exception.PhantomReflectException;
import com.tony.phantom.reflect.RefUtil;
import com.tony.phantom.reflect.Refable;

import java.lang.reflect.Field;

/**
 * @author create by zhouduntao on 2017/5/28 10:47
 */
public class BaseRefObject implements Refable {

    public Field mFiled;

    public BaseRefObject(Class<?> clazz, Field filed) {
        mFiled = RefUtil.on(clazz).getField(filed.getName());
        if (mFiled == null) {
            new PhantomReflectException("can'n find field:" + filed.getName());
        }
    }

    public Field getFiled() {
        return mFiled;
    }


}
