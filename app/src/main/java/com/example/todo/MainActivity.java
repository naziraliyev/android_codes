package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    ListView listView;
    Button Addone;
    public static EditText topshiriq;
    DataBase db;
    List<Topshiriqlar> topshiriqlarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addone = findViewById(R.id.button1);
        topshiriq = findViewById(R.id.edtText);
        listView = findViewById(R.id.listView);
        db = new DataBase(MainActivity.this);

        //final ArrayList<String> list = new ArrayList<>();
        topshiriqlarList = new ArrayList<>();
        Cursor cursor = db.oqishjadval();
        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String top = cursor.getString(1);
                Topshiriqlar topshiriqlar = new Topshiriqlar(id,top);
                topshiriqlarList.add(topshiriqlar);
            }
        }

        CustomAdapter adapter = new CustomAdapter(topshiriqlarList,this);

        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        Addone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String top = topshiriq.getText().toString();
                if (top.equals("")){

                }else {
                long javob = db.jadvalgaYozish(top);
                Topshiriqlar topshiriqlar = new Topshiriqlar(String.valueOf(javob),top);
                topshiriqlarList.add(topshiriqlar);
                listView.setAdapter(new CustomAdapter(topshiriqlarList,MainActivity.this));
            }
            }
        });
        listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }



    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id == R.id.button1){
            Intent intent = new Intent(MainActivity.this,CustomAdapter.class);
            startActivity(intent);
        }
        else {

        }

    }
}
