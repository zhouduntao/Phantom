package com.tony.phantom.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by zhouduntao on 2017/5/26.
 */

public class RefClass {

    public static HashMap<Class<?>, Constructor<?>> sRefTypeMap = new HashMap();

    static {
        try {
            sRefTypeMap.put(RefObject.class, RefObject.class.getConstructor(Class.class, Field.class));
            sRefTypeMap.put(RefMethod.class, RefMethod.class.getConstructor(Class.class, Field.class));
            sRefTypeMap.put(RefStaticMethod.class, RefStaticMethod.class.getConstructor(Class.class, Field.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public static Class load(Class proxyClass, String targetClassName) {
        Class targetClass = RefUtil.getClass(targetClassName);
        Field[] fields = proxyClass.getFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            Constructor<?> constructor = sRefTypeMap.get(type);
            if (constructor != null) {
                try {
                    field.set(null, constructor.newInstance(targetClass, field));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return targetClass;
    }
}
