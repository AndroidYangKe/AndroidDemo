package com.yangke.app.java;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yangke.app.java.util.CalendarProviderUtil;
import com.yangke.app.java.widget.EasyToast;
import com.yangke.app.java.widget.TagView;

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

        CalendarProviderUtil.addEvent(this);


        TagView tagView = findViewById(R.id.tagView);
        String[] list = {"tag1", "ZhangFei", "HuangGai", "GuanYu", "LiuBei", "ZhuGeLiang"};
        for (String str : list) {
            TextView tv = new TextView(this);
            tv.setTextSize(10);
            tv.setTextColor(Color.parseColor("#AFA282"));
            tv.setText(str);
            tv.setBackgroundResource(R.drawable.re_loan_tag_bg);
            tagView.addView(tv);
            tv.setOnClickListener(v -> {
                EasyToast.DEFAULT.show(tv.getText().toString());
            });
        }

    }
}