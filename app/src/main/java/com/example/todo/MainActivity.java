package com.example.todo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import static com.example.todo.R.id.button1;
import static com.example.todo.R.id.edtText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    NotificationCompat.Builder builder;
    NotificationManager manager;
    private int notification_id;
    private RemoteViews remoteViews;
    Notification newNotification;
    private Context contextnote;

    ListView listView;
    Button Addone;
    public static EditText task_edit;
    DataBase db;
    List<Tasks> tasksList;
    ArrayList<String> alltask;

    private Context context;
    private String title;
    private String massage;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addone = findViewById(button1);
        task_edit = findViewById(edtText);
        listView = findViewById(R.id.listView);
        alltask = new ArrayList<>();
        this.context = context;
        this.title = title;
        this.massage = massage;


        contextnote = this;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setImageViewResource(R.id.icon_notify, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.text_view_notification, "You have new massege");

        notification_id = (int) System.currentTimeMillis();
        Intent button_intent = new Intent("button_clicked");
        button_intent.putExtra("id", notification_id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(contextnote, 125, button_intent, 0);
        remoteViews.setOnClickPendingIntent(button1, pendingIntent);

        db = new DataBase(MainActivity.this);

        //final ArrayList<String> list = new ArrayList<>();
        tasksList = new ArrayList<>();
        Cursor cursor = db.ReadTable();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String task_edit = cursor.getString(1);
                Tasks tasks = new Tasks(id, task_edit);
                tasksList.add(tasks);
            }
        }

        CustomAdapter adapter = new CustomAdapter(tasksList, this);

        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        Addone.setOnClickListener(new View.OnClickListener() {
            private Object String;

            @Override
            public void onClick(View v) {



            }

        });

    }

    @Override
    public void onClick(View v) {

    }

    public void notify(String title, String massage) {
        Intent intent = new Intent("com.example.todo.MainActivity");
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        Notification notification = builder.build();

        notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm);
        notification.defaults |= Notification.DEFAULT_SOUND;
        long[] vibrate = {0, 100, 200, 300};

        notification.vibrate = vibrate;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        builder.setAutoCancel(true);
        builder.setTicker("this is tcker text");
        builder.setContentTitle("Notification");
        builder.setContentText("new message");
        builder.setSmallIcon(R.drawable.ic_chat_notify);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("you have new information ");
        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmsound);

        builder.build();
        newNotification = builder.getNotification();
        manager.notify(11, newNotification);



    }

}


