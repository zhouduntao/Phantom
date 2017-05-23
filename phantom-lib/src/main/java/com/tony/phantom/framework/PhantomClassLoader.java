package com.tony.phantom.framework;

import dalvik.system.DexClassLoader;

/**
 * Created by zhouduntao on 2017/5/23.
 */

public class PhantomClassLoader extends DexClassLoader {
    public PhantomClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}
