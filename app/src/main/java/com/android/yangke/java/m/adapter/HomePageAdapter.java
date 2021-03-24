package com.android.yangke.java.m.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * author : yangke on 2021/3/2
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主页面ViewPage适配器
 */
public class HomePageAdapter extends FragmentStateAdapter {
    private final List<Fragment> mFragmentList;

    public HomePageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> mFragmentList) {
        super(fragmentActivity);
        this.mFragmentList = mFragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
