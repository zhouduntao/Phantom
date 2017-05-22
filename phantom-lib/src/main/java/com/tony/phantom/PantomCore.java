package com.tony.phantom;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import com.tony.phantom.hook.HookHandlerCallback;
import com.tony.phantom.hook.InstrumentationDelegate;
import com.tony.phantom.util.LogUtils;
import com.tony.phantom.util.SingletonUtils;

import java.io.File;
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
        return sInstance = SingletonUtils.checkSingleton(PantomCore.class, sInstance);
    }

    public  void attach(Context context) {
        sContext = context;
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method method = activityThreadClass.getMethod("currentActivityThread");
            Object activityThread = method.invoke(null);

            Method insMethod = activityThreadClass.getMethod("getInstrumentation");
            Object instrumentation = insMethod.invoke(activityThread);

            Field instrumentationF = activityThreadClass.getDeclaredField("mInstrumentation");
            InstrumentationDelegate delegate = new InstrumentationDelegate((Instrumentation) instrumentation);
            instrumentationF.setAccessible(true);
            instrumentationF.set(activityThread,delegate);


            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Field callbackField = Handler.class.getDeclaredField("mCallback");
            callbackField.setAccessible(true);

            Object mH = mHField.get(activityThread);
            callbackField.set(mH,new HookHandlerCallback((Handler) mH));

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

    public void install(String packageName,String pluginPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent){
        File pluginFile = new File(pluginPath);
        if (!pluginFile.exists()){
            Log.e(TAG,"install " + "file not exists");
        }
        PackageManager pm = sContext.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        Log.d(TAG,"install " + packageInfo.toString());
//        DexClassLoader loader = new DexClassLoader(dexPath,optimizedDirectory,librarySearchPath,parent);
//        try {
//            Class<?> aClass = loader.loadClass("com.tony.testplugin.MainActivity");
//
//            PackageManager pm = sContext.getPackageManager();
//            Resources res = pm.getResourcesForApplication(packageName);
////            Resources res = pm.getResourcesForApplication("com.tony.testplugin");
//            int identifier = res.getIdentifier("app_name", "string", packageName);
//            String string = res.getString(identifier);
//            Log.d(TAG,"install " + string);
//        }  catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

}
