package com.tony.phantom.util;

/**
 * @author zhouduntao
 * @version 1.0
 *          <p><strong>des<></p>
 * @since 2017/5/8 19:20
 */
public class SingletonUtils {

    public static <T> T checkSingleton(Class<T> clazz, T obj) {
        if (obj == null) {
            synchronized (SingletonUtils.class) {
                if (obj == null) {
                    try {
                        obj = clazz.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
}
