package com.tony.phantom.help.packageParserCompat;

import android.content.pm.PackageParser;

import com.tony.phantom.annotation.MethodParameter;
import com.tony.phantom.reflect.RefClass;
import com.tony.phantom.reflect.RefMethod;
import com.tony.phantom.reflect.RefUtil;

import java.io.File;
import java.lang.reflect.Constructor;

/**
 * Created by zhouduntao on 2017/5/26.
 */

public class PackageParser23 implements ParserAble {

    public static Class<?> TYPE = RefClass.load(PackageParser23.class, "android.content.pm.PackageParser");
    @MethodParameter({File.class, int.class})
    public static RefMethod parsePackage;
    public static Constructor<?> constructor = RefUtil.getConstructor(TYPE);
    @Override
    public PackageParser.Package parsePackage(File packageFile, int flags) {
        return (PackageParser.Package) parsePackage.call(RefUtil.newInstance(constructor), packageFile, 0);
    }
}
