package com.example.adminshopserver.Utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.example.adminshopserver.R;

public class NotificationHelper extends ContextWrapper {

    private static final String ALI_CHANNEL_ID="com.example.drinkshopserver.ALI";
    private static final String ALI_CHANNEL_NAME="SHOP";

    private NotificationManager notificationManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            createChannel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel aliChannel=new NotificationChannel(ALI_CHANNEL_ID,ALI_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        aliChannel.enableLights(false);
        aliChannel.enableVibration(true);
        aliChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(aliChannel);
    }

    public NotificationManager getManager() {
        if (notificationManager==null)
            notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getDrinkShopNotification(String title,
                                                         String message,
                                                         Uri soundUri)
    {
    return new Notification.Builder(getApplicationContext(),ALI_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setSound(soundUri)
            .setAutoCancel(true);
    }


}
