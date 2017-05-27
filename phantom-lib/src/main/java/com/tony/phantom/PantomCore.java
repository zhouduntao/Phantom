package com.tony.phantom;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;

import com.tony.phantom.hook.ActivityThread;
import com.tony.phantom.hook.HCallbackHook;
import com.tony.phantom.hook.HookIActivityManager;
import com.tony.phantom.hook.InstrumentationDelegate;
import com.tony.phantom.util.LogUtils;
import com.tony.phantom.util.SingletonUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;


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
    private Object mLoadApk;
    private Object mMainThread;


    public static PantomCore get() {
        return sInstance = SingletonUtils.checkSingleton(PantomCore.class, sInstance);
    }

    public void attach(Context context) {
        sContext = context;

        Object mainThread = getMainThread();
//
        Instrumentation instrumentation = ActivityThread.mInstrumentation.get(mainThread);

        InstrumentationDelegate delegate = new InstrumentationDelegate(instrumentation);

        ActivityThread.mInstrumentation.set(mainThread, delegate);

        Handler mH = ActivityThread.mH.get(mainThread);

        ActivityThread.H.mCallback.set(mH, new HCallbackHook(mH));

        //replace resource


        LogUtils.d(TAG, "instrumentation :" + instrumentation);

    }

    public void install(String packageName, String pluginPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        File pluginFile = new File(pluginPath);
        if (!pluginFile.exists()) {
            Log.e(TAG, "install " + "file not exists");
        }
        PackageManager pm = sContext.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        Log.d(TAG, "install " + packageInfo.toString());

//        hookLoadApk(pluginPath, optimizedDirectory, librarySearchPath, parent);

        try {
            patchClassLoader(parent, new File(pluginPath), new File(optimizedDirectory));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//        try {
//            Class<?> loadClass = loader.loadClass("com.tony.testplugin.MainActivity");
//            Object o = loadClass.newInstance();
//            Log.d(TAG, "install " + o.toString());


        Resources res = createResources(pluginPath);
//            Resources res = pm.getResourcesForApplication("com.tony.testplugin");
        int identifier = res.getIdentifier("app_name", "string", packageName);
        String string = res.getString(identifier);
        Log.d(TAG, "install " + string);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        try {
//            ContextImpl.mResources.getFiled().set(sContext, res);
//            Resources resources = sContext.getResources();
//            LogUtils.d(TAG, "install " + "resources " + resources);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        hoams();
    }

    public void hoams() {

        Class<?> activityManagerNativeClass = null;
        try {
            activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");


            // 获取 gDefault 这个字段, 想办法替换它
            Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
            gDefaultField.setAccessible(true);
            Object gDefault = gDefaultField.get(null);

            // 4.x以上的gDefault是一个 android.util.Singleton对象; 我们取出这个单例里面的字段
            Class<?> singleton = Class.forName("android.util.Singleton");
            Field mInstanceField = singleton.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);

            // ActivityManagerNative 的gDefault对象里面原始的 IActivityManager对象
            Object rawIActivityManager = mInstanceField.get(gDefault);

            // 创建一个这个对象的代理对象, 然后替换这个字段, 让我们的代理对象帮忙干活
            Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{iActivityManagerInterface}, new HookIActivityManager(rawIActivityManager));
            mInstanceField.set(gDefault, proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public static void patchClassLoader(ClassLoader cl, File apkFile, File optDexFile)
            throws IllegalAccessException, NoSuchMethodException, IOException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 获取 BaseDexClassLoader : pathList
        Field pathListField = DexClassLoader.class.getSuperclass().getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathListObj = pathListField.get(cl);

        // 获取 PathList: Element[] dexElements
        Field dexElementArray = pathListObj.getClass().getDeclaredField("dexElements");
        dexElementArray.setAccessible(true);
        Object[] dexElements = (Object[]) dexElementArray.get(pathListObj);

        // Element 类型
        Class<?> elementClass = dexElements.getClass().getComponentType();

        // 创建一个数组, 用来替换原始的数组
        Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);

        // 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
        Constructor<?> constructor = elementClass.getConstructor(File.class, boolean.class, File.class, DexFile.class);
        Object o = constructor.newInstance(apkFile, false, apkFile, DexFile.loadDex(apkFile.getCanonicalPath(), optDexFile.getAbsolutePath(), 0));

        Object[] toAddElementArray = new Object[]{o};
        // 把原始的elements复制进去
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
        // 插件的那个element复制进去
        System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);

        // 替换
        dexElementArray.set(pathListObj, newElements);
    }

//    private void hookLoadApk(String pluginPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
//        //0 全部信息
//        Object parsePackage = PackageParser.parsePackage.invoke(RefUtil.newInstance(PackageParser.Type), new File(pluginPath), 0);
//
//        ApplicationInfo applicationInfo = (ApplicationInfo) PackageParser.generateApplicationInfo.invoke(null, parsePackage, 0, PackageUserState.newInstance());
//        applicationInfo.sourceDir = pluginPath;
//        applicationInfo.publicSourceDir = pluginPath;
//
//        Object loadApk = ActivityThread.getPackageInfoNoCheck.invoke(ActivityThread.sCurrentActivityThread, applicationInfo, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO.get());
//        Log.d(TAG, "loadApk " + loadApk.toString());
//
//        mLoadApk = loadApk;
////
//        PhantomClassLoader loader = new PhantomClassLoader(pluginPath, optimizedDirectory, librarySearchPath, parent);
//        Field aLoadedApkmClassLoaderFiled = LoadedApk.mClassLoader.getFiled();
//        RefUtil.on(aLoadedApkmClassLoaderFiled).set(loadApk, loader);
//
//        Map map = ActivityThread.mPackages.get();
//        map.put(applicationInfo.packageName, new WeakReference<>(loadApk));
//        map.put("com.tony.phantom", new WeakReference<>(loadApk));
//    }

    public AssetManager createAssetManager(String pluginPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            Object invoke = addAssetPath.invoke(am, pluginPath);
            LogUtils.d(TAG, "createAssetManager " + invoke.toString());
            return am;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Resources createResources(String pluginPath) {
        AssetManager am = createAssetManager(pluginPath);
        Resources superRes = sContext.getResources();
        return new Resources(am, superRes.getDisplayMetrics(), superRes.getConfiguration());
    }

    public Object getMainThread() {
        if (mMainThread == null) {
            mMainThread = ActivityThread.currentActivityThread.call();
        }
        return mMainThread;
    }

}
