package com.android.yangke.java.v.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.yangke.java.R;
import com.android.yangke.java.m.utils.DrawableHelper;
import com.android.yangke.java.v.search.SearchResultActivity;
import com.android.yangke.java.v.widget.ClearEditText;

/**
 * author : yangke on 2021/3/23
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 首页
 */
public class HomeFragment extends Fragment implements View.OnKeyListener {

    private ClearEditText mSearchEdit; //搜索框

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchEdit = view.findViewById(R.id.search_edit);
        mSearchEdit.setBackground(DrawableHelper.getDrawable("#00000000", "#F2F2F2", 1, 8));
        mSearchEdit.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && event.getAction() == KeyEvent.ACTION_UP) {
            String searchKey = mSearchEdit.getText().toString().trim();
            if (TextUtils.isEmpty(searchKey)) {
                mSearchEdit.setShakeAnimation();

            } else {
                SearchResultActivity.start(getContext(), searchKey);
            }
            return true;
        }
        return false;
    }
}