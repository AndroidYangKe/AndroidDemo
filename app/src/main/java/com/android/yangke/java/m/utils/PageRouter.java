package com.android.yangke.java.m.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 页面打开管理工具类，所有的页面打开都应配置在此
 */
public class PageRouter {
    /**
     * 启动到迅雷
     *
     * @param context
     */
    public static void action2Thunder(Context context) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName("com.xunlei.downloadprovider", "com.xunlei.downloadprovider.launch.LaunchActivity");
        intent.setComponent(cmp);
        context.startActivity(intent);
    }

    /**
     * 跳转到支付页扫一扫
     * @param payType 支付类型； weChatPay=1；alipay=2；
     */
    public static void payScan(Activity ctx, String payType) {
        switch (payType) {
            case "1":   //微信支付
                weChatPay(ctx);
                break;

            case "2":   //阿里支付
                alipay(ctx);
                break;
        }
    }

    private static void weChatPay(Activity act) {
        try {
            Intent intent = act.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            act.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show("无法吊起微信，请检查您是否安装了微信！");
            e.printStackTrace();
        }
    }

    private static void alipay(Activity act) {
        try {
            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            act.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show("无法吊起支付，请检查您是否安装了支付宝！");
        }
    }

    /**
     * 启动到指定页面
     *
     * @param clazz 待跳转页面Class
     */
    public static void start(Context ctx, Class<?> clazz) {
        Intent intent = new Intent(ctx, clazz);
        ctx.startActivity(intent);
    }

}
