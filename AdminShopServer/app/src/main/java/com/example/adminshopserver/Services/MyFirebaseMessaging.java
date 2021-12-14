package com.example.adminshopserver.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.example.adminshopserver.R;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Utils.Common;
import com.example.adminshopserver.Utils.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        updateTokenToServer(token);
    }
    private void updateTokenToServer(String token) {
        IDrinkShopApi mService= Common.getApi();
        mService.updateToken("server_app",token,"1")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MyFirebaseMessaging.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MyFirebaseMessaging.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData()!=null)
        {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                sendNotificationAPI26(remoteMessage);
            else
                sendNotifcation(remoteMessage);
        }
    }


    private void sendNotifcation(RemoteMessage remoteMessage) {
        //GET INFORMATION FROM MESSAGE
        Map<String,String> data=remoteMessage.getData();
        String title=data.get("title");
        String message=data.get("message");

        Uri defultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defultSoundUri);


        NotificationManager noti=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        noti.notify(new Random().nextInt(),builder.build());
    }

    private void sendNotificationAPI26(RemoteMessage remoteMessage) {
        //From Api level 26 , we need implement Notification Channel
        Map<String,String> data=remoteMessage.getData();
        String title=data.get("title");
        String message=data.get("massage");

        NotificationHelper helper;
        Notification.Builder builder;

        Uri defultSoundUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        helper=new NotificationHelper(this);
        builder=helper.getDrinkShopNotification(title,message,defultSoundUri);

        helper.getManager().notify(new Random().nextInt(),builder.build());

    }

}
