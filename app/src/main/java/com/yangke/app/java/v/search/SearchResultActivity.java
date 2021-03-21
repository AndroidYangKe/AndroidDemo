package com.yangke.app.java.v.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangke.app.java.R;
import com.yangke.app.java.m.adapter.SearchResultAdapter;
import com.yangke.app.java.m.network.ErrorModule;
import com.yangke.app.java.m.utils.AppHelper;
import com.yangke.app.java.m.utils.ClipboardTool;
import com.yangke.app.java.m.utils.PageKey;
import com.yangke.app.java.m.utils.PageRouter;
import com.yangke.app.java.m.utils.SnackBarUtil;
import com.yangke.app.java.m.vo.SearchResult;
import com.yangke.app.java.p.search.SearchPresenter;
import com.yangke.app.java.v.base.BaseActivity;
import com.yangke.app.java.v.base.IBaseView;
import com.yangke.app.java.v.widget.MultiStatusView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 关键字搜索结果页面
 */
public class SearchResultActivity extends BaseActivity implements IBaseView<List<SearchResult>> {
    private SearchPresenter mPresenter;  //Presenter
    private int mPageNum = 1;            //当前搜索页
    private RecyclerView mRcy;           //RecycleView
    private SearchResultAdapter mAdapter;//RecycleView主列表适配器
    private String mSearchKey;           //搜索的关键字
    private ArrayList<SearchResult> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        hideTitle();
    }

    @Override
    protected void initView() {
        mRcy = findViewById(R.id.search_result_rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SearchResultAdapter(R.layout.item_search_result);
        mRcy.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = new SearchPresenter();
        mPresenter.attachView(this);

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        mSearchKey = intent.getStringExtra(PageKey.SEARCH_KEY);
        mStateView.showLoadingView(true);
        mPresenter.search("bbbbbbbbbbbbbbb", mPageNum);
    }


    @Override
    public void onSuccess(String flag, List<SearchResult> searchResults) {
        if(ErrorModule.PARSE_ERROR.equals(flag)) {
            SnackBarUtil.snackBarShort(mRcy, "数据解析错误了，请联系作者进行更新").show();
            mStateView.showNetworkErrorView();
            return;
        }

        if (searchResults == null || searchResults.isEmpty()) {
            mStateView.showEmptyView();
            return;
        }

        mList.clear();
        mList.addAll(searchResults);
        mAdapter.setList(searchResults);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String href = mList.get(position).href;
                ClipboardTool.copyText(SearchResultActivity.this, href);
                if (!AppHelper.appIsInstalled(SearchResultActivity.this, "com.xunlei.downloadprovider", null)) {
                    SnackBarUtil.snackBarShort(mRcy, "迅雷没有安装或版本过低，链接已复制到剪切板").show();
                    return;
                }
                PageRouter.action2Thunder(SearchResultActivity.this);
            }
        });
        switch2DataView();
    }

    @Override
    public void onFailed(String flag, Object obj) {
        mStateView.showNetworkErrorView();
        mStateView.addNetworkErrorListener(new MultiStatusView.NetworkErrorListener() {
            @Override
            public void tryAgain() {
                mPresenter.search(mSearchKey, mPageNum);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * @param ctx       context
     * @param searchKey 搜索的关键字
     */
    public static void start(Context ctx, String searchKey) {
        Intent intent = new Intent(ctx, SearchResultActivity.class);
        intent.putExtra(PageKey.SEARCH_KEY, TextUtils.isEmpty(searchKey) ? "" : searchKey);
        ctx.startActivity(intent);
    }
}