package com.android.yangke.java.v.me;

import android.os.Bundle;

import com.android.yangke.java.R;
import com.android.yangke.java.v.base.BaseActivity;

/**
 * author : yangke on 2021/3/23
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 软件说明
 */
public class SoftwareDescActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_desc);
        setTitle(getString(R.string.software_desc_title));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}