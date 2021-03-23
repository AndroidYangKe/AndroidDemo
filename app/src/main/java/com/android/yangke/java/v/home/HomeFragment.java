package com.android.yangke.java.v.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.yangke.java.R;
import com.android.yangke.java.v.search.SearchResultActivity;

/**
 * author : yangke on 2021/3/23
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 首页
 */
public class HomeFragment extends Fragment {

    private String mParam1;
    private EditText mSearchEdit; //搜索框

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("ARG_PARAM1");
        }
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
        mSearchEdit.setOnClickListener(v -> {
            String searchKey = mSearchEdit.getText().toString().trim();
            if (!TextUtils.isEmpty(searchKey)) {
                SearchResultActivity.start(getContext(), searchKey);
            }
        });

        view.findViewById(R.id.go).setOnClickListener(v -> {
            String searchKey = mSearchEdit.getText().toString().trim();
            SearchResultActivity.start(getContext(), searchKey);
        });
    }
}