package com.android.yangke.java.m.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

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

    /**
     * 将Bitmap保存到系统图库
     */
    public static void saveToSystemGallery(Context ctx, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "wechat_zan.jpg";
        File file = new File(appDir, fileName);
        if(file.exists()) {
            return;
        }
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
            MediaStore.Images.Media.insertImage(ctx.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtil.e("文件插入系统失败！");
        }
        // 最后通知图库更新
        ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }
}
