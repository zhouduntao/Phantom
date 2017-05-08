package com.tony.phantom;

import android.app.Instrumentation;
import android.content.Context;

import com.tony.phantom.hook.InstrumentationDelegate;
import com.tony.phantom.util.LogUtils;
import com.tony.phantom.util.SingletonUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouduntao
 * @version 1.0
 *          <p><strong>des<></p>
 * @since 2017/5/8 19:16
 */
public class PantomCore {

    private static final String TAG = PantomCore.class.getName();
    public static Context sContext;
    private static PantomCore sInstance;


    public static PantomCore get() {
        return SingletonUtils.checkSingleton(PantomCore.class, sInstance);
    }

    public static void install(Context context) {
        sContext = context.getApplicationContext();
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            Method method = clazz.getMethod("currentActivityThread");
            Object activityThread = method.invoke(null);

            Method insMethod = clazz.getMethod("getInstrumentation");
            Object instrumentation = insMethod.invoke(activityThread);

            Field instrumentationF = clazz.getDeclaredField("mInstrumentation");
            InstrumentationDelegate delegate = new InstrumentationDelegate((Instrumentation) instrumentation);
            instrumentationF.setAccessible(true);
            instrumentationF.set(activityThread,delegate);

            LogUtils.d(TAG, "activitythread :" + activityThread);
            LogUtils.d(TAG, "instrumentation :" + instrumentation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
