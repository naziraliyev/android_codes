package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBase extends SQLiteOpenHelper {
    Context context;
    private Object task;

    public DataBase(Context context) {
        super(context, "TODO_task", null,1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase DataBase) {
    DataBase.execSQL("CREATE TABLE Table_Todo(id INTEGER PRIMARY KEY AUTOINCREMENT, Todo_task VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long WriteTable(String taskWrite) {
        ContentValues natija = new ContentValues();
        natija.put("Todo_task",  taskWrite);
        SQLiteDatabase Data = getWritableDatabase();
        long nat = Data.insert( "Table_Todo", null, natija);
        return nat;
    }
    public Cursor ReadTable(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Table_Todo",null);
        return cursor;
    }
    public boolean DeleteTable(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Table_Todo","id="+id,null);
        return true;
    }
    public boolean UpdateTable(String id,String task1){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Todo_task",task1);
        db.update("Table_Todo",cv,"id="+id,null);
        return true;
    }
}
