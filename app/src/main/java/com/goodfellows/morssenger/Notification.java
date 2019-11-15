package com.goodfellows.morssenger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notification extends AppCompatActivity {

    // id for the channel (SOS)
    private String channelID = "morssenger";
    // id for the notification
    private int notificationID = 111000111;

    // send notification, String message
    public void sendNotification(String message, String contact){

        // first create a channel for the notification
        createChannel();

        // create the notification with the builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,channelID);
        mBuilder.setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Message from " + contact)
                .setContentText(message);


        // need to have a class to send the view to before implementing this bit
//        Intent intent = new Intent(Notification.this, NotificationActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("message", "one message");
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this,
//                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(pendingIntent);


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(notificationID, mBuilder.build());

    }

    private void createChannel(){
        // Create the NotificationChannel with message for name
        NotificationChannel channel = new NotificationChannel(channelID, "message", NotificationManager.IMPORTANCE_HIGH);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
