package com.android.yangke.java.v;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 本项目Application基类
 */
public class BaseApp extends Application {

    public static BaseApp mApp;
    public static Handler sHandler = new Handler(Looper.getMainLooper()); //全局唯一Handler

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

}
