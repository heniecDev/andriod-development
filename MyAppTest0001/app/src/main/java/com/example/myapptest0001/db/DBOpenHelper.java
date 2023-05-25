package com.example.myapptest0001.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private String[] DB_CREATE = {
            "create table if not exists user"
                    + "(id integer primary key autoincrement,name text unique not null,"
                    + "passwd text,sex text,edu text,hobby);",
            "create table if not exists person"
                    + "(id integer primary key autoincrement,name text unique not null,"
                    +"phone text not null,classify text,address text, imgPath Integer);"
    };

    public DBOpenHelper(Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE[0]);
        db.execSQL(DB_CREATE[1]);

        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS person");
        //db.execSQL("DROP TABLE IF EXISTS foruminfo");
        onCreate(db);
    }
}
