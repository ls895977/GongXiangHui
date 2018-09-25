package com.qunxianghui.gxh.greendao;

/**
 * @author: lujialei
 * @date: 2018/9/25
 * @describe:
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.qunxianghui.gxh.greendao.gen.DaoMaster;

/**
 * 封装的数据库管理类
 */
public class MyDatabaseHelper extends DaoMaster.OpenHelper {

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // 当数据库更新时进行的操作
                break;
        }
    }
}
