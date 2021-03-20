package com.yangke.app.java.v;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yangke.app.java.R;
import com.yangke.app.java.m.network.Api;
import com.yangke.app.java.m.network.RetrofitManager;
import com.yangke.app.java.m.utils.EasyLog;
import com.yangke.app.java.m.utils.ToastUtil;
import com.yangke.app.java.m.vo.HttpResult;
import com.yangke.app.java.p.search.SearchPresenter;
import com.yangke.app.java.v.base.BaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends BaseActivity {

    private EditText mSearchEdit; //搜索框
    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mSearchEdit = findViewById(R.id.search_edit);
    }

    @Override
    protected void initData() {
        mPresenter = new SearchPresenter();
        mPresenter.attachView(this);

        String searchKey = "唐人街探案";
        searchKey = "唐人街探案";
        mPresenter.search(searchKey, 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void doClick(View v) {
        ToastUtil.show("str");
    }
}