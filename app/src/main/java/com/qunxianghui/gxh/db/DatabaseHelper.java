package com.qunxianghui.gxh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "user.db";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))");
        db.execSQL("create table student(id integer primary key autoincrement,num varchar(20),name varchar(20))");



    }

    /**
     * 数据库更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("sqliteHelpter", "数据库更新");
    }

/**
 * 添加student 到数据库
 */


}
