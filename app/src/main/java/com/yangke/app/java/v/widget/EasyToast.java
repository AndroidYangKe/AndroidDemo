package com.yangke.app.java.v.widget;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yangke.app.java.v.BaseApp;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : Toast管理类
 * <p>
 * 默认使用方式：EasyToast.DEFAULT.show("Hello EasyToast");
 * <p>
 * 自定义方式：EasyToast.newBuilder(R.layout.toast_custom, R.id.toast_tv) .setDuration(3500) .setGravity(Gravity.BOTTOM, 00, 0) .build().show("~ public class EasyToast ~");});
 */
public class EasyToast {
    private final Builder mBuilder;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static Toast mToast;
    public static EasyToast DEFAULT = new Builder(true).build();

    public static Builder newBuilder(int layoutId, int tvId) {
        return new Builder(layoutId, tvId);
    }

    private EasyToast(Builder builder) {
        this.mBuilder = builder;
    }

    public void show(int resId) {
        show(BaseApp.mApp.getString(resId));
    }

    /**
     * 将msg以Toast形式展示给用户; 支持在任意线程调用并展示
     */
    public void show(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                showInternal(msg);
            } else {
                mainHandler.post(() -> showInternal(msg));
            }
        }
    }

    @SuppressLint("ShowToast")
    private void showInternal(String msg) {
        //解决Toast多次弹出
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }

        if (mBuilder.defaultToast) { //默认Toast
            mToast = Toast.makeText(BaseApp.mApp, "", mBuilder.duration);
            mToast.setText(msg);

        } else {                 //自定义Toast
            View mToastView = LayoutInflater.from(BaseApp.mApp).inflate(mBuilder.layoutId, null);
            TextView tv = mToastView.findViewById(mBuilder.toastTvId);
            mToast = new Toast(BaseApp.mApp);
            mToast.setView(mToastView);
            mToast.setDuration(mBuilder.duration);
            if (mBuilder.gravity != 0) {
                mToast.setGravity(mBuilder.gravity, mBuilder.offsetX, mBuilder.offsetY);
            }
            if (null != tv) {
                tv.setText(msg);
            }
        }

        mToast.show();
    }

    /**
     * Toast辅助类，链式调用主要起保存信息作用
     */
    public static class Builder {
        private boolean defaultToast;
        private int layoutId;
        private int toastTvId;

        private Builder(boolean isDefault) {
            this.defaultToast = isDefault;
        }

        public Builder(int layoutId, int tvId) {
            this.layoutId = layoutId;
            this.toastTvId = tvId;
        }

        private int duration = Toast.LENGTH_SHORT;
        private int gravity = 0;
        private int offsetX = 0;
        private int offsetY = 0;

        public Builder setGravity(int gravity, int offsetX, int offsetY) {
            this.gravity = gravity;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            return this;
        }

        /**
         * @param duration 默认duration最大支持3.5s、最小2s；默认为毫秒值
         */
        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public EasyToast build() {
            return new EasyToast(this);
        }
    }

}
