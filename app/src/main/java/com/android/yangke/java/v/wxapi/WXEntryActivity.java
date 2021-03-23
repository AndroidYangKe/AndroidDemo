package com.android.yangke.java.v.wxapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.yangke.java.R;
import com.android.yangke.java.m.network.UrlManager;
import com.android.yangke.java.m.share.WeChatShareTools;
import com.android.yangke.java.m.share.WechatShareModel;
import com.android.yangke.java.m.utils.ClipboardTool;
import com.android.yangke.java.m.utils.EasyLog;
import com.android.yangke.java.m.utils.ImageUtil;
import com.android.yangke.java.m.utils.PageKey;
import com.android.yangke.java.m.utils.SPUtil;
import com.android.yangke.java.m.utils.ToastUtil;
import com.android.yangke.java.v.base.BaseActivity;
import com.android.yangke.java.v.widget.ShareDialog;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * author: yangke on 6/6/18.
 * weChat: ACE_5200125
 * email : 211yangke@gmail.com
 * desc  : 分享APP得福利
 */
public class WXEntryActivity extends BaseActivity implements View.OnClickListener, IWXAPIEventHandler {
    public static final String KEY_SHARE_COUNT = "share_count";
    private static final String WX_APP_ID = "wxcff97bee31f78c1f";
    private static final int SHARE_SUCCESS_AVAILABLE = 15;//单次分享成功可获取的免费次数，15就行
    TextView mTxtShareCount;
    TextView mTxtFreeCount;
    private ShareDialog mShareDialog;
    private IWXAPI mWxAPI;
    private String APP_SHARE_URL = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        setTitle(getString(R.string.share_title));
    }

    @Override
    protected void initData() {
        APP_SHARE_URL = TextUtils.isEmpty(SPUtil.getString(this, PageKey.APK_URL))
                ? UrlManager.APK_URL
                : SPUtil.getString(this, PageKey.APK_URL);

        WeChatShareTools.init(this, WX_APP_ID);

        //固定写法
        mWxAPI = WeChatShareTools.getIwxapi();
        mWxAPI.handleIntent(getIntent(), this);
    }

    @Override
    protected void initView() {
        mTxtShareCount = findViewById(R.id.share_txt_share_count);
        mTxtFreeCount = findViewById(R.id.share_txt_free_count);

        iniShareCountFreeCount();
    }

    public void click(View v) {
        switch (v.getId()) {
            //奖励规则
            case R.id.share_txt_reward:
                break;
            //分享app
            case R.id.share_txt_share_app:
                showShareDialog();
                break;
            //我的邀请二维码
            case R.id.share_txt_share_qr:
                showQRCodeView();
                break;
        }
    }

    private void showQRCodeView() {
        mStateView.showLoadingView(true);
    }

    private void showShareDialog() {
        mShareDialog = new ShareDialog(this, 0);
        mShareDialog.mWeiChatFriend.setOnClickListener(this);
        mShareDialog.mWeibo.setOnClickListener(this);
        mShareDialog.mWeichat.setOnClickListener(this);
        mShareDialog.mCopyHref.setOnClickListener(this);
        mShareDialog.setWindowAnimations(R.style.DialogEnterOut1);
        mShareDialog.show();
    }

    @Override
    public void onClick(View v) {
        mShareDialog.dismiss();
        switch (v.getId()) {
            case R.id.share_txt_copy_href:
                ClipboardTool.copyText(this, APP_SHARE_URL);
                ToastUtil.show("复制成功");
                break;
            case R.id.share_txt_weibo:
                ToastUtil.show("研发奋力抢修中，敬请期待...");
                break;
            //微信朋友圈
            case R.id.share_txt_weichat:
                weChatShare(WeChatShareTools.SharePlace.Zone);
                break;
            //微信好友
            case R.id.share_txt_weichat_friend:
                weChatShare(WeChatShareTools.SharePlace.Friend);
                break;
        }
    }

    private void weChatShare(WeChatShareTools.SharePlace sharePlace) {
        if (!WeChatShareTools.isWXAppInstalled()) {
            ToastUtil.show("微信客户端没有安装或版本过低");
            return;
        }
        String title = getString(R.string.share_search_title);
        String description = getString(R.string.share_search_description);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        byte[] bitmapByte = ImageUtil.bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG);
        WechatShareModel mWechatShareModel = new WechatShareModel(APP_SHARE_URL, title, description, bitmapByte);
        WeChatShareTools.shareURL(mWechatShareModel, sharePlace);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //固定写法
        setIntent(intent);
        mWxAPI.handleIntent(intent, this);
        iniShareCountFreeCount();
    }

    /**
     * 初始化分享次数，免费次数
     */
    private void iniShareCountFreeCount() {
        int shareCountTemp = SPUtil.getInt(this, KEY_SHARE_COUNT);
        int shareCount = shareCountTemp == -1 ? 0 : shareCountTemp;
        mTxtShareCount.setText("累计分享" + shareCount + "次");
        mTxtFreeCount.setText("已获得免费次数" + shareCount * SHARE_SUCCESS_AVAILABLE + "次");
    }

    @Override
    public void onReq(BaseReq baseReq) {
        EasyLog.e("~~~onReq~~~");
    }

    /**
     * 微信请求后的响应回调
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                int usedCount = SPUtil.getInt(this, PageKey.USED_COUNT);
                int currentFreeCount = getCurrentFreeCount();
                if (usedCount == -1) {
                    ToastUtil.show("请先试用软件，现在免费次数很足呢");
                    return;
                }
                int available = currentFreeCount + SHARE_SUCCESS_AVAILABLE;
                //对分享获取免费次数进行控制
                if (available > 130) {
                    ToastUtil.show("您已通过分享获得最大体验次数");
                    return;
                }
                handleShareCount();
                String shareSuccessHint = "分享成功，可使用剩余次数" + SHARE_SUCCESS_AVAILABLE + "次";
                ToastUtil.show(shareSuccessHint);
                SPUtil.putInt(this, PageKey.ALL_COUNT, available);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtil.show(getString(R.string.share_cancel));
                break;
        }
    }

    /**
     * 分享成功后记录分享次数
     */
    private void handleShareCount() {
        int shareCountTemp = SPUtil.getInt(this, KEY_SHARE_COUNT);
        int shareCount = (shareCountTemp == -1 ? 0 : shareCountTemp);
        shareCount++;
        SPUtil.putInt(this, KEY_SHARE_COUNT, shareCount);
    }

    /*
     * 剩余免费次数
     */
    private int getCurrentFreeCount() {
        return SPUtil.getInt(this, PageKey.ALL_COUNT);
    }
}
