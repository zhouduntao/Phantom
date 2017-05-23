package com.tony.phantom.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class RefUtil {

    private Class mClass;
    private Method mMethod;
    private Field mField;

    public static Class getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object newInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static RefUtil on(Field field) {
        return new RefUtil(field);
    }

    public static RefUtil on(String className) {
        return new RefUtil(RefUtil.getClass(className));
    }

    public static RefUtil on(Class<?> clazz) {
        return new RefUtil(clazz);
    }

    public static RefUtil on(Method method) {
        return new RefUtil(method);
    }


    private RefUtil(Class<?> clazz) {
        mClass = clazz;
    }

    private RefUtil(Method method) {
        mMethod = method;
    }

    private RefUtil(Field field) {
        mField = field;
    }

    public RefUtil method(String methodName, Class<?>... parameterTypes) {
        try {
            mMethod = mClass.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            try {
                mMethod = mClass.getDeclaredMethod(methodName, parameterTypes);
                mMethod.setAccessible(true);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }
        return this;
    }

    public Method getMethod(String methodName, Class<?>... parameterTypes) {
        method(methodName, parameterTypes);
        return mMethod;
    }

    public RefUtil field(String fieldName) {
        try {
            mField = mClass.getField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            try {
                mField = mClass.getDeclaredField(fieldName);
                mField.setAccessible(true);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
        return this;
    }

    public Field getField(String fieldName) {
        field(fieldName);
        return mField;
    }

    public Object get(Object obj) {
        try {
            return mField.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Object object, Object value) {
        try {
            mField.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object invoke(Object receiver, Object... args) {
        try {
            return mMethod.invoke(receiver, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getObject(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
