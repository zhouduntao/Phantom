package com.demo.tony.testplugindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        LayoutInflater from = LayoutInflater.from(this);
        Context context = from.getContext();
        Log.d(TAG, " onCreate: R R.layout.activity_main_test" + R.layout.activity_main_test);
        Log.d(TAG, " onCreate: R app name " + getString(R.string.app_name));
        Log.d(TAG, " onCreate: getResources " + getResources());

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    public void onclick(View view) {
//        Button button = (Button) view;
//        button.setText(getResources().getString(R.string.app_name));
        startActivity(new Intent(this,Main2Activity.class));
    }
}
