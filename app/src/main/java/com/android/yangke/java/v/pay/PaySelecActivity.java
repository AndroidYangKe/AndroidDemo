package com.android.yangke.java.v.pay;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.yangke.java.R;
import com.android.yangke.java.m.utils.FileUtil;
import com.android.yangke.java.m.utils.PageKey;
import com.android.yangke.java.m.utils.PageRouter;
import com.android.yangke.java.m.utils.PermissionUtil;
import com.android.yangke.java.m.utils.SnackBarUtil;
import com.android.yangke.java.v.base.BaseActivity;

/**
 * author : yangke on 2021/3/28
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 支付选择页面
 */
public class PaySelecActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_selec);
        setTitle("支持作者");
    }

    @Override
    protected void initView() {
        findViewById(R.id.wechat_zan_tv).setOnClickListener(this);
        findViewById(R.id.alipay_zan_tv).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (!PermissionUtil.hasPermission(this, PermissionUtil.WRITE)) {
            SnackBarUtil.snackBar(mStateView, PermissionUtil.WRITE_HINT, 5000).setAction("给予", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PermissionUtil.retryRequestPermissions(PaySelecActivity.this, PermissionUtil.WRITE);
                }
            }).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PageKey.RequestCode.WRITE_SD == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FileUtil.savePayBitmapToLocal(PaySelecActivity.this);

            } else {
                SnackBarUtil.snackBarLong(getWindow().getDecorView(), "无法吊起二维码支付，权限申请失败!");
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        PermissionUtil.retryRequestPermissions(this, PermissionUtil.WRITE);

        if (id == R.id.wechat_zan_tv) {
            PageRouter.payScan(this, "1");

        } else if (id == R.id.alipay_zan_tv) {
            PageRouter.payScan(this, "2");

        }
    }
}