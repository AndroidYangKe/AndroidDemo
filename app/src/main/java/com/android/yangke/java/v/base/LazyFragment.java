package com.android.yangke.java.v.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author : yangke on 2021/3/2
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 实现懒加载(仅延迟加载数据)--即当前fragment可见时才加载数据
 */
public abstract class LazyFragment extends Fragment {
    //Fragment当前状态是否可见
    public boolean mIsFragmentVisible;
    //View是否初始化完成
    public boolean mIsViewPrepared;
    //是否数据加载过
    protected boolean isLoaded;
    //是否正在加载中
    protected boolean mIsLoading = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
    }

    /**
     * 可见时执行的动作
     */
    protected void onVisible() {
        mIsFragmentVisible = true;
        onLazyLoad();
    }

    /**
     * 不可见时执行的动作
     */
    protected void onInvisible() {
        mIsFragmentVisible = false;
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */

    protected abstract void onLazyLoad();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

}
