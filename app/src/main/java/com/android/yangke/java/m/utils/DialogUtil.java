package com.android.yangke.java.m.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.android.yangke.java.R;
import com.android.yangke.java.v.base.BaseActivity;

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
                .setNegativeButton("取消", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    listener.negativeClick();
                }).setPositiveButton("赞赏", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            listener.positiveClick();
        });
        AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ctx.getResources().getColor(R.color.c757575));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ctx.getResources().getColor(R.color.purple_200));
    }

    /**
     * 展示隐私权限说明弹框
     */
    public static void showPolicyDialog(BaseActivity ctx, IButtonListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(false);
        builder.setTitle("用户使用协议")
                .setMessage("1、一切移动客户端用户在下载并浏览本APP软件时均被视为已经仔细阅读本条款并完全同意，您的使用行为将被视为对本声明全部内容的认可。\n\n2、本APP所展示的数据来源均来自互联网爬取，如果侵犯了第三方知识产权或其他权利，请及时联系作者进行删除。\n\n3、APP内的行为均由用户触发，本APP不对其真实性、准确性、合法性进行校验，均由信息发布人和使用者负责，本APP不承担相应责任。")
                .setNegativeButton("拒绝", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ctx.finish();
                }).setPositiveButton("同意", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            listener.positiveClick();
        });
        AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ctx.getResources().getColor(R.color.c757575));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ctx.getResources().getColor(R.color.purple_200));
    }

    public interface IButtonListener {
        void positiveClick();

        void negativeClick();
    }
}
