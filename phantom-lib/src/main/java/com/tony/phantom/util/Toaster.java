package com.tony.phantom.util;

import android.widget.Toast;

import com.tony.phantom.PhantomCore;

/**
 * Created by zhouduntao on 2017/6/20.
 */

public class Toaster {

    private static final String TAG = Toaster.class.getName();

    public static void show(String msg){
        if (msg == null) msg = "null";
        Toast.makeText(PhantomCore.getContext(), "", Toast.LENGTH_SHORT).show();
    }
}
