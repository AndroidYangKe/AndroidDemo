package com.android.yangke.java.v.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.yangke.java.R;
import com.android.yangke.java.m.adapter.SearchResultAdapter;
import com.android.yangke.java.m.network.ErrorModule;
import com.android.yangke.java.m.utils.AppUtil;
import com.android.yangke.java.m.utils.ClipboardUtil;
import com.android.yangke.java.m.utils.DateUtil;
import com.android.yangke.java.m.utils.PageKey;
import com.android.yangke.java.m.utils.PageRouter;
import com.android.yangke.java.m.utils.PermissionUtil;
import com.android.yangke.java.m.utils.SnackBarUtil;
import com.android.yangke.java.m.vo.AccountManager;
import com.android.yangke.java.m.vo.SearchResult;
import com.android.yangke.java.p.search.SearchResultPresenter;
import com.android.yangke.java.v.MainActivity;
import com.android.yangke.java.v.base.BaseActivity;
import com.android.yangke.java.v.base.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 关键字搜索结果页面
 */
public class SearchResultActivity extends BaseActivity implements IBaseView {
    private SearchResultPresenter mPresenter;  //Presenter
    private int mPageNum = 1;            //当前搜索页
    private RecyclerView mRcy;           //RecycleView
    private SearchResultAdapter mAdapter;//RecycleView主列表适配器
    private String mSearchKey;           //搜索的关键字
    private final ArrayList<SearchResult> mList = new ArrayList<>(); //列表数据
    private SwipeRefreshLayout mSwipeRefresh; //下拉刷新View

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setTitle("搜索结果");
    }

    @Override
    protected void initView() {
        mSwipeRefresh = findViewById(R.id.search_result_swipe_refresh);
        mSwipeRefresh.setRefreshing(true);
        mRcy = findViewById(R.id.search_result_rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SearchResultAdapter(R.layout.item_search_result);
        mAdapter.setAnimationEnable(true);
        mRcy.setAdapter(mAdapter);

        configRefreshAndLoadMore();
    }

    private void configRefreshAndLoadMore() {
        mSwipeRefresh.setOnRefreshListener(() -> {
            mPageNum = 1;
            mSwipeRefresh.setRefreshing(true);
            // 这里的作用是防止下拉刷新的时候还可以上拉加载
            mAdapter.getLoadMoreModule().setEnableLoadMore(false);
            mPresenter.search(mSearchKey, mPageNum);
        });

        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            mSwipeRefresh.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);
            mPresenter.search(mSearchKey, mPageNum);
        });

        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    @Override
    protected void initData() {
        mPresenter = new SearchResultPresenter();
        mPresenter.attachView(this);

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        mSearchKey = intent.getStringExtra(PageKey.SEARCH);
        mPresenter.search(mSearchKey, mPageNum);
    }


    @Override
    public void onSuccess(String flag, Object obj, String str) {
        if(obj instanceof List) {
            List<SearchResult> searchResults = (List<SearchResult>) obj;
            mSwipeRefresh.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);

            if (ErrorModule.PARSE_ERROR.equals(flag)) {
                mStateView.showEmptyView();
                callAuthor("数据解析错误了，请联系作者进行更新");
                return;
            }

            if (mPageNum == 1 && (searchResults == null || searchResults.isEmpty())) {
                mStateView.showEmptyView();
                return;
            }
            updateList(searchResults, str);
            listClick();
            switch2DataView();
            //mPage累加必须放在列表更新后，不然会影响更新逻辑
            mPageNum++;
        } else {
            switch2DataView();

            if (!PermissionUtil.hasPermission(this, PermissionUtil.WRITE)) {
                SnackBarUtil.snackBar(mRcy, PermissionUtil.WRITE_HINT, 3000).setAction("给予", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PermissionUtil.retryRequestPermissions(SearchResultActivity.this, PermissionUtil.WRITE);
                    }
                }).show();
                return;
            }

            ClipboardUtil.copyText(SearchResultActivity.this, str);
            if (!AppUtil.appIsInstalled(SearchResultActivity.this, "com.xunlei.downloadprovider", null)) {
                SnackBarUtil.snackBarLong(mRcy, "迅雷没有安装或版本过低，链接已复制到剪切板").show();
                return;
            }
            PageRouter.action2Thunder(SearchResultActivity.this);
        }
    }

    private void callAuthor(String msg) {
        SnackBarUtil.snackBarLong(mRcy, msg).setAction("联系作者", v -> {
            ClipboardUtil.copyText(getBaseContext(), AccountManager.QQ);
            SnackBarUtil.snackBarLong(v, "已成功复制作者QQ，可直接与其联系").show();
        }).show();
    }

    private void updateList(List<SearchResult> searchResults, String pageStr) {
        if (mPageNum == 1) {
            mList.clear();
            mList.addAll(searchResults);
            mAdapter.setList(searchResults);
        } else {
            mList.addAll(searchResults);
            mAdapter.addData(searchResults);
        }
        updateLoadMoreView(pageStr);
    }

    private void listClick() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(DateUtil.dateIsPass()){
                callAuthor("软件过期，请联系作者进行更新");
                return;
            }
            String href = mList.get(position).href;
            switch2Loading(false);
            mPresenter.requestGetHref(href);
        });
    }

    private void updateLoadMoreView(String pageStr) {
        int page = Integer.parseInt(pageStr);
        if (mPageNum >= page) { //没有下一页
            mAdapter.getLoadMoreModule().loadMoreEnd();

        } else {
            mAdapter.getLoadMoreModule().loadMoreComplete();
        }

    }

    @Override
    public void onFailed(String flag, Object obj) {
        showErrorView();
    }

    private void showErrorView() {
        mStateView.showNetworkErrorView();
        mStateView.addNetworkErrorListener(() -> {
            mStateView.showLoadingView(false);
            mPresenter.search(mSearchKey, mPageNum);
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
        intent.putExtra(PageKey.SEARCH, TextUtils.isEmpty(searchKey) ? "" : searchKey);
        ctx.startActivity(intent);
    }
}