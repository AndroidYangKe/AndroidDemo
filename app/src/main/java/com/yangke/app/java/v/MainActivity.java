package com.yangke.app.java.v;

import android.os.Bundle;
import android.view.View;

import com.yangke.app.java.R;
import com.yangke.app.java.m.utils.ToastUtil;
import com.yangke.app.java.v.base.BaseActivity;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    public void doClick(View v) {
        ToastUtil.show("str");
    }
}