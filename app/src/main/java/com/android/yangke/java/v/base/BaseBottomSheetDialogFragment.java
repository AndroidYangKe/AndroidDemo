package com.android.yangke.java.v.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.yangke.java.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * author : yangke on 2021/3/24
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 对BottomSheetDialogFragment的封装，支持子类自定义背景色，阴影透明度，弹框高度
 */
public class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Theme，才能显示出layout中诸如圆角的布局；
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
    }

    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//        dialog.setCancelable(false);      //不可滑动取消
        //把windows的默认背景颜色去掉，不然圆角显示不见
        Window window = dialog.getWindow();
        window.findViewById(R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attr = window.getAttributes();
        attr.dimAmount = configDimAmount();
        window.setAttributes(attr);
        FrameLayout frameLayout = dialog.getDelegate().findViewById(R.id.design_bottom_sheet); //获取dialog的根部局
        if (null != frameLayout) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.height = getPeekHeight();
            frameLayout.setLayoutParams(layoutParams);              //修改弹窗的最大高度，不允许上滑（默认可以上滑）
            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(frameLayout);
            behavior.setPeekHeight(getPeekHeight());                //peekHeight即弹窗的最大高度
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);  // 初始为展开状态
        }
    }

    /**
     * 设置弹框的透明度；0标识全透明、1完全不透明
     */
    protected float configDimAmount() {
        return 0.8f;
    }

    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     */
    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        return peekHeight - peekHeight / 3; //设置弹窗高度为屏幕高度的3/4
    }

}
