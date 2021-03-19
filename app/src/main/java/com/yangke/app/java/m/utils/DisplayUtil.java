package com.yangke.app.java.m.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕尺寸工具类
 * 通过ctx.getResources().getDisplayMetrics()方法可能获取不到屏幕实际尺寸，具体可参考
 */
public class DisplayUtil {
    public static int dip2px(Context context, float dipValue) {
        return dpToPx(dipValue, getDensity(context));
    }

    public static int px2Dp(Context context, float dipValue) {
        return pxToDp(dipValue, getDensity(context));
    }

    public static int getScreenWidth(Context ctx) {
        DisplayMetrics displayMetrics = getDisplayMetrics(ctx);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context ctx) {
        DisplayMetrics displayMetrics = getDisplayMetrics(ctx);
        return displayMetrics.heightPixels;
    }

    public static float getDensity(Context ctx) {
        DisplayMetrics displayMetrics = getDisplayMetrics(ctx);
        return displayMetrics.density;
    }

    public static DisplayMetrics getDisplayMetrics(Context ctx) {
        return ctx.getResources().getDisplayMetrics();
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param scale（DisplayMetrics类中属性density）
     */
    private static int pxToDp(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param scale（DisplayMetrics类中属性density）
     */
    private static int dpToPx(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度(方法有很多种)
     */
    public static int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}