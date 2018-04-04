package com.gongxianghui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gongxianghui.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "user.db";

    static int dbVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))");
        db.execSQL("create table records (id integer primary key autoincrement,name verchar(200))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
