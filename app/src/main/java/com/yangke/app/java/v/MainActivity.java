package com.yangke.app.java.v;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yangke.app.java.R;
import com.yangke.app.java.m.utils.ToastUtil;
import com.yangke.app.java.v.base.CitySelectorDialogFragment;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View v) {
        new CitySelectorDialogFragment(new CitySelectorDialogFragment.ConfirmListener() {
            @Override
            public void confirm(String str) {
                ToastUtil.show(str);
            }
        }).show(getSupportFragmentManager(), "");
    }
}