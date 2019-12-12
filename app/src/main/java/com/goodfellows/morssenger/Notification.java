package com.goodfellows.morssenger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {

    // ID for the channel (SOS)
    private String channelID = "morssenger";
    // ID for the notification
    private int notificationID = 111000111;

    // Send notification, String message
    public void sendNotification(Context context){

        // First create a channel for the notification
        createChannel(context);

        // Create the notification with the builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,channelID);
        mBuilder.setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Morssenger Message")
                .setContentText("Your received a message");


        //creates the intent to send the user to the messagesActivity once the
        // notification is pressed
        Intent intent = new Intent(context, MessagesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message", "one message");

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);


        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(context);

        // NotificationID allows you to update the notification later on.
        mNotificationManager.notify(notificationID, mBuilder.build());

    }

    private void createChannel(Context context){
        // Create the NotificationChannel with message for name
        NotificationChannel channel = new NotificationChannel(channelID, "message", NotificationManager.IMPORTANCE_HIGH);

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
