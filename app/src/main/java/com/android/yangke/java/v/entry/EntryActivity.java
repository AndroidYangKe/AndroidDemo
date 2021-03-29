package com.android.yangke.java.v.entry;

import android.os.Bundle;
import android.view.View;

import com.android.yangke.java.BuildConfig;
import com.android.yangke.java.R;
import com.android.yangke.java.m.utils.DialogUtil;
import com.android.yangke.java.m.utils.PageRouter;
import com.android.yangke.java.m.utils.SPUtil;
import com.android.yangke.java.v.MainActivity;
import com.android.yangke.java.v.base.BaseActivity;
import com.android.yangke.java.v.widget.TimerView;

/**
 * author : yangke on 2021/3/25
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 开屏页，整个应用入口
 */
public class EntryActivity extends BaseActivity {

    private TimerView mSkipView; //跳过按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        hideTitle();
    }

    @Override
    protected void initView() {
        mSkipView = findViewById(R.id.splash_txt_skip);
        boolean showAgreePolicy = SPUtil.getBoolean(this, "agree_policy");
        if (!showAgreePolicy) { //未同意App协议
            mSkipView.setVisibility(View.GONE);
            DialogUtil.showPolicyDialog(this, new DialogUtil.IButtonListener() {
                @Override
                public void positiveClick() {
                    SPUtil.putBoolean(EntryActivity.this, "agree_policy", true);
                    toMainPage();
                }

                @Override
                public void negativeClick() {
                }
            });
            return;
        }

        toMainPage();
    }

    @Override
    protected void initData() {

    }

    private void toMainPage() {
        mSkipView.configTimer(BuildConfig.DEBUG ? 0 : 3000)
                .withClickListener(() -> {
                    mSkipView.cancel();
                    PageRouter.start(EntryActivity.this, MainActivity.class);
                    overridePendingTransition(R.anim.entry_activity_fade, R.anim.entry_activity_exit);
                    finish();
                }).start();

    }
}
