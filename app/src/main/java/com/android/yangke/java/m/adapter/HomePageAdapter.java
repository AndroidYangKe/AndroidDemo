package com.android.yangke.java.m.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author : yangke on 2021/3/2
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主页面ViewPage适配器
 */
public class HomePageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList;

    public HomePageAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> mFragmentList) {
        super(fm, behavior);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
