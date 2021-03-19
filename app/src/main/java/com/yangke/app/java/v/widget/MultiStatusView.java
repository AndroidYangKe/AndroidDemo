package com.yangke.app.java.v.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yangke.app.java.R;

/**
 * author : yangke on 2021/3/2
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 多种状态切换View；例：加载中、加载失败、空视图；
 */
public class MultiStatusView extends FrameLayout {
    private TextView mTitle, mDesc, mTryAgain;
    private ImageView mIcon;
    private View mContentParent, mRootLayout, mLoadingParent;
    private View mLoadingView;
    private Animation mRotateAnim;

    public MultiStatusView(Context context) {
        super(context);
        init(context);
    }

    public MultiStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.main_state_view, this, true);
        mLoadingParent = findViewById(R.id.multi_view_loading_root);
        mLoadingView = findViewById(R.id.progress_bar_common);

        mRootLayout = findViewById(R.id.state_view_root);
        mContentParent = findViewById(R.id.main_content_parent_ll);
        mIcon = findViewById(R.id.main_icon_iv);
        mTitle = findViewById(R.id.main_title_tv);
        mDesc = findViewById(R.id.main_desc_tv);
        mTryAgain = findViewById(R.id.main_try_again_tv);
    }

    public void addNetworkErrorListener(NetworkErrorListener lis) {
        if (lis != null) {
            showNetworkErrorView();
            mTryAgain.setOnClickListener(v -> lis.tryAgain());
        }
    }

    /**
     * 展示正常视图
     */
    public void showDataView() {
        resetView();
        mRootLayout.setBackgroundColor(Color.WHITE);
        mContentParent.setVisibility(View.VISIBLE);
        setVisibility(View.GONE);
    }

    /**
     * 展示网络错误
     */
    public void showNetworkErrorView() {
        resetView();
        mRootLayout.setBackgroundColor(Color.WHITE);
        mDesc.setVisibility(View.VISIBLE);
        mDesc.setText(R.string.network_error_desc);
        mTryAgain.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.network_error);
        mIcon.setBackgroundResource(R.drawable.network_error);
    }

    /**
     * 展示空视图
     */
    public void showEmptyView() {
        resetView();
        mRootLayout.setBackgroundColor(Color.WHITE);
        mDesc.setVisibility(View.VISIBLE);
        mTryAgain.setVisibility(View.GONE);
        mIcon.setBackgroundResource(R.drawable.no_data);
        mTitle.setText(R.string.no_data_title);
        mDesc.setText(R.string.no_data_desc);
    }

    public void showEmptyView(String desc) {
        resetView();
        mRootLayout.setBackgroundColor(Color.WHITE);
        mDesc.setVisibility(View.INVISIBLE);
        mTryAgain.setVisibility(View.GONE);
        mIcon.setBackgroundResource(R.drawable.no_data);
        mTitle.setText(desc);
    }

    /**
     * 展示loading
     *
     * @param isFirstLoad 标识是否是首次展示loading
     */
    public void showLoadingView(boolean isFirstLoad) {
        resetView();
        //首次展示loading
        if (isFirstLoad) {
            mRootLayout.setBackgroundColor(Color.WHITE);
            mContentParent.setVisibility(View.GONE);
            mLoadingParent.setVisibility(View.VISIBLE);
            loadingAnimationStart();

            //非首次展示loading
        } else {
            loadingAnimationStart();
            mRootLayout.setBackgroundColor(Color.TRANSPARENT);
            mContentParent.setVisibility(View.GONE);
            mLoadingParent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 重置视图状态
     */
    private void resetView() {
        setVisibility(View.VISIBLE);
        mContentParent.setVisibility(View.VISIBLE);
        mLoadingParent.setVisibility(View.GONE);
        loadingAnimationCancel();
    }

    private void loadingAnimationStart() {
        mRotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.roatate_loading);
        mLoadingView.startAnimation(mRotateAnim);
    }

    private void loadingAnimationCancel() {
        if (null != mRotateAnim) {
            mRotateAnim.cancel();
        }
    }

    public interface NetworkErrorListener {
        void tryAgain();
    }
}
