package com.tony.phantom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tony.phantom.framework.PluginManager;
import com.tony.phantom.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d(TAG, "onCreate: R " + R.layout.activity_main);
        LogUtils.d(TAG, "onCreate app context :" + getApplicationContext());
        LogUtils.d(TAG, "onCreate getResources " + getResources());
    }

    public void startActivity(View view) {
        Intent intent = new Intent();
        intent.setClassName(PluginManager.get().getPluginPkgName(), PluginManager.get().getPluginPkgName() + ".MainActivity");

//        intent.setClassName(getPackageName(), TestPluginActivity.class.getName());
//        intent.setClassName("com.tony.phantom", "com.tony.phantom.TestPluginActivity");
//        intent.setClass(this,TestPluginActivity.class);
        startActivity(intent);
    }

    public void installPlugin(View view) {
//        PackageParser.Package aPackage = PackageParserCompat.parsePackage(new File(pluginPath), 0);

    }

    @Override
    public void setTheme(@StyleRes int resid) {
        super.setTheme(resid);
        LogUtils.d(TAG, "setTheme " + resid);
    }

}
