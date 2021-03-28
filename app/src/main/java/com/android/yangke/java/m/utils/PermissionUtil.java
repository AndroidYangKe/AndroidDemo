package com.android.yangke.java.m.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * author : yangke on 2021/3/28
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   :
 */
public class PermissionUtil {
    public static final String WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE; //写sd卡
    public static final String WRITE_HINT = "为了保证文件可正常下载和缓存，请基于App文件存储权限";
    /**
     * 没有权限调用权限，有什么也不干
     */
    public static void retryRequestPermissions(Activity ctx, String str) {
        if (!hasPermission(ctx, str)) {
            ActivityCompat.requestPermissions(ctx, new String[]{PermissionUtil.WRITE}, 1);
        }
    }

    /**
     * 校验是否有某权限
     */
    public static boolean hasPermission(Context ctx, String str) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(ctx, str)) {
            return true;
        }
        return false;
    }
}
