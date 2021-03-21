package com.yangke.app.java.m.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

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

}
