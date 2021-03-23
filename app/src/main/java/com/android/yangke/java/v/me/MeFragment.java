package com.android.yangke.java.v.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.yangke.java.R;
import com.android.yangke.java.m.utils.AppHelper;
import com.android.yangke.java.m.utils.ClipboardTool;
import com.android.yangke.java.m.utils.PageRouter;
import com.android.yangke.java.v.base.LazyFragment;
import com.android.yangke.java.v.wxapi.WXEntryActivity;
import com.google.android.material.snackbar.Snackbar;

/**
 * author: yangke on 2018/5/20
 * weChat: ACE_5200125
 * email : 211yangke@gmail.com
 * desc  : 我
 */
public class MeFragment extends LazyFragment implements View.OnClickListener {

    private TextView mTitle;
    private TextView mVersionCode;
    private LinearLayout mLLPersonalMsg;
    private LinearLayout mLLYouHui;
    private LinearLayout mLLFaPiao;
    private TextView mTvAccount;
    private TextView mTvMsg;
    private TextView mFree;             //剩余次数
    private TextView mTvAuthorMsg;

    public static void snakeBar(View v, String hint) {
        Snackbar.make(v, hint, Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = view.findViewById(R.id.me_tv_title);
        mVersionCode = view.findViewById(R.id.mt_tv_versionCode);
        mLLPersonalMsg = view.findViewById(R.id.me_ll_personal_msg);
        mLLYouHui = view.findViewById(R.id.me_ll_youhui);
        mLLFaPiao = view.findViewById(R.id.me_ll_fapiao);
        view.findViewById(R.id.share_tv).setOnClickListener(this);
        mTvAccount = view.findViewById(R.id.me_tv_account);
        mTvMsg = view.findViewById(R.id.me_tv_qq_flock);
        view.findViewById(R.id.me_software_desc_tv).setOnClickListener(this);
        mFree = view.findViewById(R.id.me_txt_free);
        mTvAuthorMsg = view.findViewById(R.id.me_txt_author);

        mVersionCode.setText("版本V" + AppHelper.getAppVersionName());
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_ll_personal_msg://作者个人中心
//                RxActivityTool.skipActivity(getActivity(), AuthorActivity.class);
                break;
            case R.id.me_tv_guanyu://关于作者
//                RxActivityTool.skipActivity(getActivity(), AboutAuthorActivity.class);
                break;
            case R.id.me_tv_qq://作者QQ
                ClipboardTool.copyText(getContext(), "QQ：1551121393");
                snakeBar(v, "您已成功复制作者QQ");
                break;
            case R.id.me_ll_youhui://
                break;
            case R.id.me_ll_fapiao:
                break;
            case R.id.me_ll_free://剩余次数
                break;
            case R.id.share_tv://推荐有奖
                PageRouter.start(getActivity(), WXEntryActivity.class);
                break;
            case R.id.me_tv_account://账户安全
                break;
            case R.id.me_tv_qq_flock://QQ 群
                ClipboardTool.copyText(getContext(), "692699158");
                break;
            case R.id.me_software_desc_tv://免责条款
                PageRouter.start(getActivity(), SoftwareDescActivity.class);
                break;
        }
    }
}
