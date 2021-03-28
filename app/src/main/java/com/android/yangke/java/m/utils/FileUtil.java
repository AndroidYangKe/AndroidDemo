package com.android.yangke.java.m.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.android.yangke.java.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * author : yangke on 2021/3/25
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 对文件相关操作机进行封装
 */
public class FileUtil {

    private static int insertSize = 0;

    /**
     * 将Bitmap保存到系统图库
     */
    public static synchronized void saveToSystemGallery(Context ctx, Bitmap bmp, String fileName) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LogUtil.e("文件写入失败！");
            e.printStackTrace();
        }

        try {//把文件插入到系统图库
            if (insertSize == 0 || insertSize == 1) {
                MediaStore.Images.Media.insertImage(ctx.getContentResolver(),
                        file.getAbsolutePath(), fileName, null);

                // 最后通知图库更新
                ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
            }
            insertSize++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtil.e("文件插入系统失败！");
        }
    }

    /**
     * 保存阿里、微信支付二维码图
     */
    public static void savePayBitmapToLocal(Context ctx) {
        if (PermissionUtil.hasPermission(ctx, PermissionUtil.WRITE)) {
            Bitmap bitmapWeChat = ((BitmapDrawable) ctx.getResources().getDrawable(R.drawable.zan_wechat)).getBitmap();
            saveToSystemGallery(ctx, bitmapWeChat, "wechat_zan.jpg");

            Bitmap bitmapAli = ((BitmapDrawable) ctx.getResources().getDrawable(R.drawable.zan_ali)).getBitmap();
            saveToSystemGallery(ctx, bitmapAli, "ali_zan.jpg");
        }
    }
}
