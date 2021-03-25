package com.android.yangke.java.v;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.yangke.java.R;
import com.android.yangke.java.m.adapter.HomePageAdapter;
import com.android.yangke.java.v.base.BaseActivity;
import com.android.yangke.java.v.home.HomeFragment;
import com.android.yangke.java.v.me.MeFragment;

import java.util.ArrayList;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ArrayList<Fragment> mFragmentList; //Fragment list
    private HomePageAdapter mPageAdapter;      //Fragment list adapter
    private ViewPager2 mHomeViewPager;          //Fragment container

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideTitle();
    }

    @Override
    protected void initView() {
        mPageAdapter = new HomePageAdapter(this, mFragmentList);

        mHomeViewPager = findViewById(R.id.view_pager_home);
        mHomeViewPager.setAdapter(mPageAdapter);
        mHomeViewPager.setCurrentItem(0);
        mHomeViewPager.setUserInputEnabled(false);
        ((RadioGroup) findViewById(R.id.rg_bottom)).setOnCheckedChangeListener(this);

    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(HomeFragment.newInstance(""));
        mFragmentList.add(new MeFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_bottom_home:
                mHomeViewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_bottom_live:
                mHomeViewPager.setCurrentItem(1, false);
                break;
        }
    }
}