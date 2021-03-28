package com.android.yangke.java.m.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import androidx.appcompat.app.AlertDialog;

/**
 * author : yangke on 2021/3/28
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 对dialog的简单封装
 */
public class DialogUtil {

    /**
     * 展示赞赏弹框
     */
    public static void showVoteDialog(Context ctx, IButtonListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(false);
        builder.setTitle("软件舒心吗？")
                .setMessage("您的支持是作者更新的最大动力")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        listener.negativeClick();
                    }
                }).setPositiveButton("赞赏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                listener.positiveClick();
            }
        });
        AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#757575"));
    }

    public interface IButtonListener {
        void positiveClick();
        void negativeClick();
    }
}
