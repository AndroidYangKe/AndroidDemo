package com.yangke.app.m.helper;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.yangke.app.java.R;

/**
 * author : yangke on 2021/3/18
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : SnackBar辅助展示类
 */
public class SnackBarUtil {
    public static final int Info = 1;
    public static final int Confirm = 2;
    public static final int Warning = 3;
    public static final int Alert = 4;

    private static int red = 0xfff44336;
    private static int green = 0xff4caf50;
    private static int blue = 0xff2195f3;
    private static int orange = 0xffffc107;

    /**
     * 短显示SnackBar，自定义颜色
     */
    public static Snackbar snackBarShort(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackBarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    public static Snackbar snackBarShort(View view, String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
    }

    /**
     * 长显示SnackBar，自定义颜色
     */
    public static Snackbar snackBarLong(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackBarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 自定义时长显示SnackBar，自定义颜色
     */
    public static Snackbar snackBar(View view, String message, int duration, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackBarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 短显示SnackBar，可选预设类型
     */
    public static Snackbar snackBarShort(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 长显示SnackBar，可选预设类型
     */
    public static Snackbar snackBarLong(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 自定义时常显示SnackBar，可选预设类型
     */
    public static Snackbar snackBar(View view, String message, int duration, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 选择预设类型
     */
    private static void switchType(Snackbar snackbar, int type) {
        switch (type) {
            case Info:
                setSnackBarColor(snackbar, blue);
                break;
            case Confirm:
                setSnackBarColor(snackbar, green);
                break;
            case Warning:
                setSnackBarColor(snackbar, orange);
                break;
            case Alert:
                setSnackBarColor(snackbar, red);
                break;
        }
    }

    /**
     * 设置SnackBar背景颜色
     */
    public static void setSnackBarColor(Snackbar snackbar, int backgroundColor) {
        View view = snackbar.getView();
        if (null != view) {
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置SnackBar文字和背景颜色
     */
    public static void setSnackBarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if (null != view) {
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

}