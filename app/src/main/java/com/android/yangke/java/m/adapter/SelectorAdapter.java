package com.android.yangke.java.m.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.yangke.java.R;
import com.android.yangke.java.v.BaseApp;

import org.jetbrains.annotations.NotNull;

/**
 * author : yangke on 2021/3/18
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 选择View适配器；
 */
public class SelectorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private final int mTextSelectorColor = BaseApp.mApp.getResources().getColor(R.color.purple_500);
    private final int mTextNormalColor = BaseApp.mApp.getResources().getColor(R.color.black);
    private final LinearSnapHelper mSnapHelper = new LinearSnapHelper();
    private final int mSelectTextSize = 16;

    public SelectorAdapter(int layoutResId, RecyclerView rcy) {
        super(layoutResId);

        updateSelectorView(rcy);
    }

    /**
     * 获取选中View对应的数据
     */
    public String getSelectedItemData(RecyclerView rcy) {
        View snapView = mSnapHelper.findSnapView(rcy.getLayoutManager());
        if (null != snapView) {
            return getData().get(rcy.getChildAdapterPosition(snapView));
        }
        return "";
    }

    /**
     * 根据滚动状态更新选中、未选中城市颜色
     */
    private void updateSelectorView(RecyclerView rcy) {
        mSnapHelper.attachToRecyclerView(rcy);
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView rcy, int newState) {
                super.onScrollStateChanged(rcy, newState);
                View v = mSnapHelper.findSnapView(rcy.getLayoutManager());
                if (null == v) {
                    return;
                }

                TextView selectorTv = v.findViewById(R.id.selector_tv);
                //停止滚动重新设置选中颜色
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    selectorTv.setTextColor(mTextSelectorColor);
                    selectorTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    selectorTv.setTextSize(mSelectTextSize);

                } else {
                    selectorTv.setTextColor(mTextNormalColor);
                    selectorTv.setTextSize(14);
                    selectorTv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
            }
        });
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String s) {
        holder.setText(R.id.selector_tv, s);
        int position = getItemPosition(s);
        int SELECTED_POSITION = 1;//默认选中位置
        if (SELECTED_POSITION == position) {
            TextView tv = holder.findView(R.id.selector_tv);
            assert tv != null;
            tv.setTextColor(mTextSelectorColor);
            tv.setTextSize(mSelectTextSize);
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }
}
