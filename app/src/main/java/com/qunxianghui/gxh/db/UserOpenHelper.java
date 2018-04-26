package com.qunxianghui.gxh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserOpenHelper extends SQLiteOpenHelper {
    public UserOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "SqliteTest.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table if not exists t_user(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR(255)," +
                "password VARCHAR(255)," +
                "isDel INTEGER DEFAULT 0" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
