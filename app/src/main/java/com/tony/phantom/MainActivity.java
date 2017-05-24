package com.tony.phantom;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tony.phantom.util.LogUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testPlugin.apk";

    private Object mAndroidManifestActivity;
    private Object AndroidManifestActivity_theme;
    public String apkPath = Environment.getExternalStorageDirectory() + "/phantom/testPlugin.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d(TAG, "app context :" + getApplicationContext());
    }

    public void startActivity(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.tony.testplugin", "com.tony.testplugin.MainActivity");
//        intent.setClassName(getPackageName(), TestPluginActivity.class.getName());
//        intent.setClassName("com.tony.phantom", "com.tony.phantom.TestPluginActivity");
//        intent.setClass(this,TestPluginActivity.class);
        startActivity(intent);
    }

    public void installPlugin(View view) {
//        String optimizedDirectory = getFilesDir().getAbsolutePath();
//        PantomCore.get().install("com.tony.testplugin", pluginPath, optimizedDirectory, "", getClassLoader());
        click(view);
    }

    public void click(View view) {

//        com.android.internal.R.styleable.AndroidManifestApplication

        try {
            Class<?> aClass = Class.forName("com.android.internal.R$styleable");
            Field application = aClass.getField("AndroidManifestActivity");
            mAndroidManifestActivity = application.get(null);

            Field theme = aClass.getField("AndroidManifestActivity_theme");
            AndroidManifestActivity_theme = theme.get(null);


            Log.d("text", "test");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        parsePackage(new File(apkPath), 0);
    }

    public void parsePackage(File apkFile, int flags) {
        try {
            AssetManager assets = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            Object cookie = addAssetPath.invoke(assets, apkFile.getAbsolutePath());

            Resources res = new Resources(assets, getResources().getDisplayMetrics(), null);
            XmlResourceParser parser = assets.openXmlResourceParser((Integer) cookie, "AndroidManifest.xml");

            parseApplication(null, res, parser, 0, 0, null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseApplication(Package owner, Resources res, XmlResourceParser parser,
                                 int flags, int splitIndex, String[] outError) {
        int type;
        try {
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT) {
                String targetName = parser.getName();
                if ("activity".equals(targetName)) {
                    parseActivity(owner, res, parser, flags, outError, false,
                            true);
                }

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {

        }

    }

    @Override
    public void setTheme(@StyleRes int resid) {
        super.setTheme(resid);
        LogUtils.d(TAG, "setTheme " + resid);
    }

    public void parseActivity(Package owner, Resources res,
                              XmlResourceParser parser, int flags, String[] outError,
                              boolean receiver, boolean hardwareAccelerated) {
        TypedArray sa = res.obtainAttributes(parser, (int[]) mAndroidManifestActivity);
        int id = sa.getResourceId((Integer) AndroidManifestActivity_theme, 0);
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String name = parser.getAttributeName(i);
            String value = parser.getAttributeValue(i);
            Log.d("parseActivity", "value " + value);
        }

        String text = parser.getText();

        Log.d("parseActivity", "id " + id);

    }
}
