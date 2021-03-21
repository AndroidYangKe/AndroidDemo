package com.yangke.app.java.v;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.yangke.app.java.R;
import com.yangke.app.java.v.base.BaseActivity;
import com.yangke.app.java.v.search.SearchResultActivity;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends BaseActivity {

    private EditText mSearchEdit; //搜索框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideTitle();
    }

    @Override
    protected void initView() {
        mSearchEdit = findViewById(R.id.search_edit);
        mSearchEdit.setOnClickListener(v -> {
            String searchKey = mSearchEdit.getText().toString().trim();
            if (!TextUtils.isEmpty(searchKey)) {
                SearchResultActivity.start(this, searchKey);
            }
        });

        findViewById(R.id.go).setOnClickListener(v -> {
            String searchKey = mSearchEdit.getText().toString().trim();
            SearchResultActivity.start(this, searchKey);
        });
    }

    @Override
    protected void initData() {
    }
}