package com.example.todo;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class norification extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @SuppressWarnings(value = "static-access")
    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent,startId);
        String title = "title";
        String massage = "massage";
        new MainActivity().Notify(title,massage);
    }
}
