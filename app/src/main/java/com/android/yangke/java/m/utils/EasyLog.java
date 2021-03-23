package com.android.yangke.java.m.utils;

import android.text.TextUtils;
import android.util.Log;

import com.android.yangke.java.BuildConfig;

/**
 * author : yangke on 2021/3/18
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 日志打印辅助
 */
public class EasyLog {

    /**
     * 输出错误级别日志
     */
    public static void e(String str) {
        if(BuildConfig.DEBUG && !TextUtils.isEmpty(str)) {
            Log.e("feng", "----------"+str);
        }
    }
}
