package com.tony.phantom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tony.phantom.util.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d(TAG,"app context :" + getApplicationContext());
    }

    public void startActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, TestPluginActivity.class);
        startActivity(intent);
    }
}
