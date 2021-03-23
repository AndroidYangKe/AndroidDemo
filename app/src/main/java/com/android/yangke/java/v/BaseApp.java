package com.android.yangke.java.v;

import android.app.Application;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 本项目Application基类
 */
public class BaseApp extends Application {

    public static BaseApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

}
