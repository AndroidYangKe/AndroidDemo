package com.android.yangke.java.v.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * author : yangke on 2021/3/25
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 倒计时View
 */
public class TimerView extends androidx.appcompat.widget.AppCompatTextView {

    private Timer mTimer;

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public Timer configTimer(int time) {
        if (null == mTimer) {
            mTimer = new Timer(time, 1000);
        }
        return mTimer;
    }

    public void cancel() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public class Timer extends android.os.CountDownTimer {
        ClickListener l; //点击回调

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public Timer withClickListener(ClickListener c) {
            l = c;
            setOnClickListener(v -> l.click());
            return this;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String value = millisUntilFinished / 1000 + "丨跳过";
            setText(value);
        }

        @Override
        public void onFinish() {
            l.click();
//            setText("0丨跳过");
        }
    }

    /**
     * View点击回调
     */
    public interface ClickListener {
        void click();
    }
}
