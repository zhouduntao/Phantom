package com.tony.phantom.help.packageParserCompat;

import android.content.pm.PackageParser;

import java.io.File;

/**
 * Created by zhouduntao on 2017/5/26.
 */

public interface ParserAble {
    PackageParser.Package parsePackage(File packageFile, int flags);
}
