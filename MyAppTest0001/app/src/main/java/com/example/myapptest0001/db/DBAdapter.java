package com.example.myapptest0001.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DBOpenHelper dbOpenHelper;

    public DBAdapter(Context _context){
        context = _context;
    }

    public SQLiteDatabase open(String dbName){
        dbOpenHelper = new DBOpenHelper(context,dbName,null,1);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            db = dbOpenHelper.getReadableDatabase();
        }
        return db;
    }
    public void close(){
        if (db != null){
            db.close();
            db = null;
        }
    }
}
