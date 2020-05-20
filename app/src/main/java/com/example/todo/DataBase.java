package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    Context context;
    private Object topshiriq;

    public DataBase(Context context) {
        super(context, "TODO_topshiriq", null,1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase DataBase) {
    DataBase.execSQL("CREATE TABLE jadval(id INTEGER PRIMARY KEY AUTOINCREMENT, topshiriqlar VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long jadvalgaYozish(String topshiriq) {
        ContentValues natija = new ContentValues();
        natija.put("topshiriqlar",  topshiriq);
        SQLiteDatabase baza = getWritableDatabase();
        long nat = baza.insert( "jadval", null, natija);
        return nat;
    }
    public Cursor oqishjadval(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jadval",null);
        return cursor;
    }
    public boolean ochirishjadval(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("jadval","id="+id,null);
        return true;
    }
    public boolean ozgarrishjadval(String id,String top){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("topshiriqlar",top);
        db.update("jadval",cv,"id="+id,null);
        return true;
    }
}
