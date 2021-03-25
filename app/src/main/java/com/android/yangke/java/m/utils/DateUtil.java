package com.android.yangke.java.m.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author : yangke on 2021/3/25
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 时间相关管理类
 */
public class DateUtil {
    private static final String PASS_DATE = "2021-04-29 00:00:00"; //软件过期时间

    /**
     * 判断是否超时
     */
    public static boolean dateIsPass() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = new Date(System.currentTimeMillis());
        Date endTime = null;
        try {
            startTime = ft.parse(ft.format(startTime));
            endTime = ft.parse(PASS_DATE);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        boolean pass = currentDate.after(end);
        LogUtil.e("失效过期为：" + PASS_DATE);
        return pass;
    }
}