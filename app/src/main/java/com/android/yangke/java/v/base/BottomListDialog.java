package com.android.yangke.java.v.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yangke.java.R;
import com.android.yangke.java.m.utils.DrawableUtil;
import com.android.yangke.java.m.utils.PageKey;
import com.android.yangke.java.m.vo.Amount;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * author : yangke on 2021/3/24
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 底部ListFragment
 * <p>
 * 用法：ItemListDialogFragment.newInstance(2).show(getSupportFragmentManager(), "dialog");
 */
public class BottomListDialog extends BaseBottomSheetDialogFragment {

    /**
     * @param selectedPos 默认选中位置
     */
    public static BottomListDialog newInstance(int selectedPos) {
        BottomListDialog fragment = new BottomListDialog();
        Bundle args = new Bundle();
        args.putInt(PageKey.SELECT_POS, selectedPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog, container, false);
        view.setBackground(DrawableUtil.getDrawable("#ffffff", new float[]{12, 12, 12, 12, 0, 0, 0, 0,}));
        return view;
    }

    private int mSelectedPos = 0;          //默认选中位置
    private Amount mSelectedItem; //选中的Item

    /**
     * @return 选中的Item
     */
    public Amount getSelectedItem() {
        return mSelectedItem;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mSelectedPos = getArguments().getInt(PageKey.SELECT_POS);

        RecyclerView rcy = (RecyclerView) view;
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemAdapter adapter = new ItemAdapter(R.layout.item_amount);
        rcy.setAdapter(adapter);

        ArrayList<Amount> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            boolean selected = false;
            if (mSelectedPos == i) {
                selected = true;
            }
            list.add(new Amount(i + "", selected));
        }

        adapter.setList(list);
        adapter.setOnItemClickListener((adapter1, view1, position) -> {
//                if (pos == position) { //做非关闭列表，单选
//                    return;
//                }
//
//                list.get(pos).selected = false;
//                adapter.notifyItemChanged(pos);
//                pos = position;
//                list.get(pos).selected = true;
//                adapter.notifyItemChanged(pos);
//                dismiss();
            if (mSelectedPos < list.size()) {
                mSelectedItem = list.get(mSelectedPos);
                dismiss();
            }
        });
    }

    private static class ItemAdapter extends BaseQuickAdapter<Amount, BaseViewHolder> {
        public ItemAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, Amount s) {
            baseViewHolder.setText(R.id.text, s.amount)
                    .setBackgroundColor(R.id.text, getContext().getResources().getColor(s.selected ? R.color.purple_700 : R.color.white))
            ;
        }
    }

}