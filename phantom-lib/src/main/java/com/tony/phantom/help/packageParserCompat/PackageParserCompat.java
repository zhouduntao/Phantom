package com.tony.phantom.help.packageParserCompat;

import android.content.pm.PackageParser;

import java.io.File;

/**
 * Created by zhouduntao on 2017/5/27.
 */

public class PackageParserCompat {

    public static PackageParser.Package parsePackage(File packageFile, int flags) {
        PackageParser.Package pkg = null;
        pkg = new PackageParser23().parsePackage(packageFile, flags);
        return pkg;
    }

}
