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
     * 跳转到微信扫一扫
     */
    public static void toWeChatScan(Activity ctx) {
        try {
            Uri uri = Uri.parse("weixin://dl/scan");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            ctx.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show("无法跳转到微信，请检查您是否安装了微信！");
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
