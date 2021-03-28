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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yangke.java.R;
import com.android.yangke.java.m.adapter.HistoryAdapter;
import com.android.yangke.java.m.utils.DrawableUtil;
import com.android.yangke.java.m.utils.SPUtil;
import com.android.yangke.java.p.search.SearchHistoryPresenter;
import com.android.yangke.java.v.search.SearchResultActivity;
import com.android.yangke.java.v.widget.ClearEditText;

import java.util.Arrays;

/**
 * author : yangke on 2021/3/23
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 首页
 */
public class HomeFragment extends Fragment implements View.OnKeyListener {

    private ClearEditText mSearchEdit;        //搜索框
    private SearchHistoryPresenter mPresenter;//历史记录Presenter
    private RecyclerView mRcy;                //搜索历史记录
    private HistoryAdapter mHistoryAdapter;   //历史搜索适配器
    private final int mMaxHistorySize = 3;    //最大历史搜索数量

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
        mSearchEdit.setBackground(DrawableUtil.getDrawable("#00000000", "#F2F2F2", 1, 8));
        mSearchEdit.setOnKeyListener(this);
        mRcy = view.findViewById(R.id.history_rcy);
        mPresenter = new SearchHistoryPresenter();

        initList();

        initClick();
    }

    private void initList() {
        mHistoryAdapter = new HistoryAdapter(R.layout.item_history);
        mHistoryAdapter.setAnimationEnable(true);
        mRcy.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcy.setAdapter(mHistoryAdapter);
    }

    private void initClick() {
        mHistoryAdapter.setOnItemClickListener((adapter, view1, position) -> {
            String searchKey = mHistoryAdapter.getData().get(position);
            SPUtil.getString(getContext(), searchKey);
            SearchResultActivity.start(getContext(), searchKey);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] list = mPresenter.getHistoryList(getContext(), mMaxHistorySize);
        if (null == list || list.length <= 0) {
            return;
        }
        mHistoryAdapter.setList(Arrays.asList(list));
        if (mMaxHistorySize < list.length) {
            View cleanHistory = LayoutInflater.from(getContext()).inflate(R.layout.footer_clean_history, null);
            cleanHistory.setOnClickListener(v -> {
                mPresenter.cleanHistory(getContext());
                mHistoryAdapter.setList(null);
                mHistoryAdapter.removeAllFooterView();
            });
            mHistoryAdapter.removeAllFooterView();
            mHistoryAdapter.addFooterView(cleanHistory);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && event.getAction() == KeyEvent.ACTION_UP) {
            String searchKey = mSearchEdit.getText().toString().trim();
            if (TextUtils.isEmpty(searchKey)) {
                mSearchEdit.setShakeAnimation();

            } else {
                mPresenter.save(searchKey, getContext());
                SearchResultActivity.start(getContext(), searchKey);
            }
            return true;
        }
        return false;
    }

}