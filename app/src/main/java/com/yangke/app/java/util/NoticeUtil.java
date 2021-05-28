package com.yangke.app.java.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.yangke.app.java.MainActivity;
import com.yangke.app.java.R;

public class NoticeUtil {

    public static void showNotifycation(Context ctx) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx, "123");
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //请求id不能一样否则多条通知的非首条通知点击会失效
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx
                , (int) System.currentTimeMillis() / 1000
                , intent
                , PendingIntent.FLAG_ONE_SHOT);
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.ic_launcher_background);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContentTitle("title")
                .setContentText("messageBody")
                .setAutoCancel(true)
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("123", "channelName", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        //通知id不能一样否则通知栏只会出现一条通知
        notificationManager.notify(1000, notificationBuilder.build());
    }
}
