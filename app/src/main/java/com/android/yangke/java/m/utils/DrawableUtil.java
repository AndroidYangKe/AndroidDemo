package com.android.yangke.java.m.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.android.yangke.java.v.BaseApp;

/**
 * author : yangke on 2021/3/18
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : Drawable辅助类；例：生成圆角图片
 */
public class DrawableUtil {

    /**
     * @param solidColor 颜色
     * @param radius     圆角
     * @return 生成上下左右圆角Drawable；
     */
    public static GradientDrawable getDrawable(String solidColor, float radius) {
        return getDrawable(solidColor, "", 0, radius);
    }

    /**
     * @param solidColor 颜色
     * @param radius     圆角
     * @return 产生shape类型的drawable，支持左上、右上、左下，右下；
     * 例：new float[]{1, 1, 2, 2, 3, 3 , 4, 4,}，其中“1,1”标识左上角圆角为1；“2,2”标识右上圆角为2；
     * “3,3”左下圆角为3；“4,4”右下圆角为4；
     */
    public static GradientDrawable getDrawable(String solidColor, float[] radius) {
        GradientDrawable drawable = new GradientDrawable();
        int size = DisplayUtil.dip2px(BaseApp.mApp, 1);
        drawable.setColor(Color.parseColor(solidColor));
        for (int i = 0; i < radius.length; i++) {
            radius[i] = radius[i] * size;
        }
        drawable.setCornerRadii(radius);
        return drawable;
    }

    /**
     * 产生shape类型的drawable
     */
    public static GradientDrawable getDrawable(String solidColor, String strokeColor, float strokeWidth, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        int size = DisplayUtil.dip2px(BaseApp.mApp, 1);
        drawable.setColor(Color.parseColor(solidColor));
        if (strokeWidth != 0) {
            drawable.setStroke(DisplayUtil.dip2px(BaseApp.mApp, strokeWidth), Color.parseColor(strokeColor));
        }
        drawable.setCornerRadius(size * radius);
        return drawable;
    }

}
