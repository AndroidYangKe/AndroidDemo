package com.android.yangke.java.v.base;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.yangke.java.R;
import com.android.yangke.java.v.widget.MultiStatusView;

/**
 * author : yangke on 2021/3/19
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   :
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected MultiStatusView mStateView;   //状态View;可用于展示空视图、加载中、无数据、网络错误页面；
    private TextView mTitle;                //标题文本
    private ImageView mBack, mTitleLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止页面横屏
    }

    @Override
    public void setContentView(int layoutResID) {
        View content = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(content);
    }

    /**
     * 禁止app内px单位跟随手机系统字体大小拉伸
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1) {
            resources.getConfiguration().fontScale = 1;
            resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
        }
        return resources;
    }

    @Override
    public void setContentView(View v) {
        FrameLayout content = findViewById(R.id.base_content_fl);
        content.removeAllViewsInLayout();
        content.addView(v);
        mStateView = findViewById(R.id.base_state_view);
        init();
    }

    private void init() {
        initTitle();
        initData();
        initView();
    }

    protected abstract void initView();

    protected abstract void initData();

    private void initTitle() {
        mTitleLine = findViewById(R.id.base_title_bottom_line);
        mTitle = findViewById(R.id.base_title_center_tv);
        mBack = findViewById(R.id.base_title_back_iv);
        mBack.setOnClickListener(leftBtnOnClickListener);
    }

    protected void hideTitle() {
        findViewById(R.id.base_title_parent_rtl).setVisibility(View.GONE);
    }

    protected void setTitleBarBg(int color) {
        findViewById(R.id.base_title_parent_rtl).setBackgroundColor(getResources().getColor(color));
    }

    protected void setTitle(String str) {
        mTitle.setText(str);
    }

    protected void onBackClick() {
        finish();
    }

    protected void setBackIcon(int res) {
        mBack.setBackgroundResource(res);
    }

    protected void setTitleTextColor(int color) {
        mTitle.setTextColor(getResources().getColor(color));
    }

    protected void showTitleLine() {
        mTitleLine.setVisibility(View.VISIBLE);
    }

    protected View.OnClickListener leftBtnOnClickListener = v -> onBackClick();

    protected void stateBarDark() {
        setBackIcon(R.drawable.back_ic_black);
        setTitleTextColor(R.color.c323232);
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyBoard() {
        try {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示loading视图；与showDataView，showEmptyView，showNetwork等进行状态切换；
     *
     * @param isFirst 是否是首次展示loading，默认首次展示时背景为透明；一般页面首次大白都传true；网络失败也传true；
     */
    public void switch2Loading(boolean isFirst) {
        mStateView.showLoadingView(isFirst);
    }

    /**
     * 展示网络错误视图；与showDataView，showEmptyView，showNetwork等进行状态切换；
     */
    public void switch2NetworkError() {
        mStateView.showNetworkErrorView();
    }

    /**
     * 展示视图页面；与showDataView，showEmptyView，showNetwork等进行状态切换；
     * 例：首页默认加载时为空白页加loading状态，当网络请求成功时调用此函数，展示有数据的视图
     */
    public void switch2DataView() {
        mStateView.showDataView();
    }

}