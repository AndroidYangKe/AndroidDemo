package com.android.yangke.java.m.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.yangke.java.R;
import com.android.yangke.java.m.vo.SearchResult;

import org.jetbrains.annotations.NotNull;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 搜索结果页主列表适配器
 */
public class SearchResultAdapter extends BaseQuickAdapter<SearchResult, BaseViewHolder>
        implements LoadMoreModule {

    public SearchResultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SearchResult searchResult) {
        baseViewHolder.setText(R.id.search_result_title_tv, searchResult.searchName)
                .setText(R.id.search_result_file_size_tv, searchResult.fileSize)
                .setText(R.id.search_result_create_time_tv, searchResult.createDate);
    }
}
