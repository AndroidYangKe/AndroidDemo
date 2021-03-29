package com.android.yangke.java.v;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.yangke.java.R;
import com.android.yangke.java.m.adapter.HomePageAdapter;
import com.android.yangke.java.m.utils.AppUtil;
import com.android.yangke.java.m.utils.FileUtil;
import com.android.yangke.java.m.utils.PermissionUtil;
import com.android.yangke.java.m.utils.SnackBarUtil;
import com.android.yangke.java.v.base.BaseActivity;
import com.android.yangke.java.v.home.HomeFragment;
import com.android.yangke.java.v.me.MeFragment;
import com.android.yangke.java.v.pay.PaySelecActivity;

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
    private ViewPager2 mHomeViewPager;         //Fragment container
    public CoordinatorLayout mCoordinator;    //CoordinatorLayout，特意为snakeBar指定位置

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
        mCoordinator = findViewById(R.id.coordinator);

        if (!PermissionUtil.hasPermission(this, PermissionUtil.WRITE)) {
            SnackBarUtil.snackBar(mCoordinator, PermissionUtil.WRITE_HINT, 7000).setAction("给予", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PermissionUtil.retryRequestPermissions(MainActivity.this, PermissionUtil.WRITE);
                }
            }).show();
        }

        FileUtil.savePayBitmapToLocal(this);
    }

    @Override
    public void onBackPressed() {
        AppUtil.doubleClickZan(this);
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