package com.example.todo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class norification extends AppCompatActivity {
    Button button_notifiy;

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
   private RemoteViews remoteViews;
   private Context context;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(),R.layout.notification);

        remoteViews.setImageViewResource(R.id.icon_notify,R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.text_view,"You have new information");

        notification_id = (int) System.currentTimeMillis();
        Intent button_intent = new Intent("button clicked");
        button_intent.putExtra("id",notification_id);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context,96, button_intent,0);
        remoteViews.setOnClickPendingIntent(R.id.button,pendingIntent);
        button_notifiy = findViewById(R.id.button);
        button_notifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notification_intent = new Intent(context,MainActivity.class);
                PendingIntent  pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

                builder = new NotificationCompat.Builder(context);
                        builder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true).setCustomBigContentView(remoteViews).setContentIntent(pendingIntent);
                notificationManager.notify(notification_id,builder.build());


            }
        });

    }


}
