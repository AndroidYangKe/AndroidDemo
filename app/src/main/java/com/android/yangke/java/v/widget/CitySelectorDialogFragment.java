package com.android.yangke.java.v.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yangke.java.R;
import com.android.yangke.java.m.adapter.SelectorAdapter;
import com.android.yangke.java.m.utils.DrawableUtil;

import java.util.Arrays;

/**
 * author : yangke on 2021/3/18
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 城市选择DialogFragment
 * 示例：new CitySelectorDialogFragment(new CitySelectorDialogFragment.ConfirmListener() {
 *
 * @Override public void confirm(String str) {
 * ToastUtil.show(str);
 * }
 * }).show(getSupportFragmentManager(), "");
 */
public class CitySelectorDialogFragment extends DialogFragment {

    private SelectorAdapter mAdapter;
    private final ConfirmListener mConfirmListener;

    public CitySelectorDialogFragment(ConfirmListener mConfirmListener) {
        this.mConfirmListener = mConfirmListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Dialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_city_selector, container, false);
        view.setBackground(DrawableUtil.getDrawable("#ffffff", new float[]{12, 12, 12, 12, 0, 0, 0, 0,}));
        view.findViewById(R.id.cancel).setOnClickListener(v -> dismiss());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != getDialog()) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams attr = window.getAttributes();
            attr.gravity = Gravity.BOTTOM;
            attr.width = WindowManager.LayoutParams.MATCH_PARENT;
            attr.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attr);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//这步是必须的，否则设置的动画无效
            window.setDimAmount(0.3f);
            setCancelable(false);
            window.setWindowAnimations(R.style.citySelector);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRcy(view);

        updateRcyData();
    }

    /**
     * 城市选中监听器
     */
    public interface ConfirmListener {
        void confirm(String str);
    }

    private void initRcy(@NonNull View view) {
        RecyclerView rcy = view.findViewById(R.id.rcy);
        mAdapter = new SelectorAdapter(R.layout.item_selector, rcy);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(mAdapter);

        view.findViewById(R.id.confirm).setOnClickListener(v -> {
            dismiss();
            if (null != mConfirmListener) {
                mConfirmListener.confirm(mAdapter.getSelectedItemData(rcy));
            }
        });
    }

    private void updateRcyData() {
        String[] city = {"", "北京", "上海", "广州", "深圳", "郑州", "重庆", "杭州", "武汉", "西安", "济南", "云南", "合肥", "大理", "无锡", ""};
        mAdapter.setList(Arrays.asList(city));
    }
}