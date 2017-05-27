package com.tony.phantom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tony.phantom.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testPlugin.apk";


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
        String optimizedDirectory = getFilesDir().getAbsolutePath();
        PantomCore.get().install("com.tony.testplugin", pluginPath, optimizedDirectory, "", getClassLoader());

//        PackageParser.Package aPackage = PackageParserCompat.parsePackage(new File(pluginPath), 0);

    }

    @Override
    public void setTheme(@StyleRes int resid) {
        super.setTheme(resid);
        LogUtils.d(TAG, "setTheme " + resid);
    }

}
