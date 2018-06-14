package com.example.sumitbobade.demopushnotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.media.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sumitbobade on 06/04/18.
 */

public class MessegingService extends FirebaseMessagingService
{

    public String msg;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {

        msg=remoteMessage.getNotification().getBody();

        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Intent intent=new Intent("com.example.sumitbobade.demopushnotify_myIntent");
        intent.putExtra("msg",msg);
        localBroadcastManager.sendBroadcast(intent);
        showNotification(remoteMessage.getNotification().getBody());
        Log.i("Recived Message : ",""+msg);
    }



    public void showNotification(String message)
    {
        PendingIntent pi=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);

        Notification notification=new Notification.Builder(this)
                                        .setSmallIcon(R.mipmap.notify_icon)
                                        .setContentTitle("My title")
                                        .setContentText(message)
                                        .setContentIntent(pi)
                                        .setAutoCancel(true)
                                        .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
       notificationManager.notify(0,notification);

    }

}
