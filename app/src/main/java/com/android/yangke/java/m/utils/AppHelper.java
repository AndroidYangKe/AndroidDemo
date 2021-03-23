package com.android.yangke.java.m.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.android.yangke.java.v.BaseApp;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   :
 */
public class AppHelper {
    /**
     * @param context     context
     * @param packageName 应用报名
     * @return true 标志安装了此 APP，false 反之
     */
    private static final String INSTALLED = "installed";//APP 已安装

    public static boolean appIsInstalled(final Context context, final String packageName, Dialog dialog) {
        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            final PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            if (packageInfos != null) {
                for (PackageInfo packageInfo : packageInfos) {
                    if (packageInfo.packageName.equals(packageName)) {
                        return INSTALLED;
                    }
                }
            }
            return "";
        });
        if (dialog != null) {
            dialog.dismiss();
        }
        try {
            if (INSTALLED.equals(future.get())) {
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * app版本号，如1.0.0
     */
    public static String getAppVersionName() {
        String packageName = BaseApp.mApp.getPackageName();
        if (TextUtils.isEmpty(packageName)) return "";
        try {
            PackageManager pm = BaseApp.mApp.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return null == pi ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

}
