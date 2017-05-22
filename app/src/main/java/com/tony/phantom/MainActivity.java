package com.tony.phantom;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tony.phantom.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private String pluginPath = Environment.getExternalStorageDirectory().getPath() + "/phantom/testPlugin.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d(TAG,"app context :" + getApplicationContext());
    }

    public void startActivity(View view) {
//        Intent intent = new Intent();
//        intent.setClassName("com.tony.testplugin", "TestPluginActivity");
//        startActivity(intent);
        PackageManager manager = getPackageManager();
//        manager.getApplicationInfo()
        String optimizedDirectory = getFilesDir().getAbsolutePath();
        PantomCore.get().install("com.tony.testplugin",pluginPath,optimizedDirectory,"",getClassLoader());
    }
}
