package com.tony.phantom.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tony.phantom.util.LogUtils;
import com.tony.phantomframework.R;

public class ProxyActivity extends AppCompatActivity {

    private static final String TAG = ProxyActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        LogUtils.d(TAG,"onCreate");
        LogUtils.d(TAG,"app context :" + getApplicationContext());
    }
}
