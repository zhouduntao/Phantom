package com.tony.phantom.help.packageParserCompat;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageParser;

import com.tony.phantom.framework.PluginManager;

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

    public static ActivityInfo getActivityInfo() {
        String pluginPath = PluginManager.get().getPluginPath();
        PackageParser.Package aPackage = PackageParserCompat.parsePackage(new File(pluginPath), 0);
        PackageParser.Activity activity = aPackage.activities.get(0);
        return activity.info;
    }

}
