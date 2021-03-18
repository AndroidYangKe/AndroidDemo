package com.yangke.app.java.v;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yangke.app.java.R;
import com.yangke.app.java.m.adapter.SelectorAdapter;

import java.util.Arrays;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends AppCompatActivity {

    private SelectorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRcy();

        updateRcyData();
    }

    private void initRcy() {
        RecyclerView rcy = findViewById(R.id.rcy);
        mAdapter = new SelectorAdapter(R.layout.item_selector, rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(mAdapter);
    }

    private void updateRcyData() {
        String[] city = {"北京", "上海", "广州", "深圳", "郑州", "重庆", "杭州", "武汉", "西安", "济南", "云南", "合肥", "大理", "无锡"};
        mAdapter.setList(Arrays.asList(city));
    }
}