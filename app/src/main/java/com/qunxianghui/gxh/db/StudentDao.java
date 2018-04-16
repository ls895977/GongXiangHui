package com.qunxianghui.gxh.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 2018/4/5.
 */

public class StudentDao {
    Context context;
    DatabaseHelper dbHelpter;

    public StudentDao(Context context) {
        this.context = context;
        dbHelpter=new DatabaseHelper(context,"student.db",null,1);
    }
    //往user表中插入数据方法
    public void insert(String num,String name){
        //获取数据库写的权限 一般都是更新
        SQLiteDatabase sqLiteDatabase = dbHelpter.getWritableDatabase();
       String sql="insert into student(num,name)values(?,?)";
       sqLiteDatabase.execSQL(sql,new String[]{num,name});

//        ContentValues values = new ContentValues();
//        values.put("num",num);
//        values.put("name",name);
//        sqLiteDatabase.insert("student",null,values);
//        sqLiteDatabase.close();

    }

    //查询数据库的方法，使用数据库读数据可靠权限时候 不能用sqLiteDatabase.close();

    public Cursor query(String num,String name){
        SQLiteDatabase sqLiteDatabase = dbHelpter.getReadableDatabase();
        String sql="select*from student where num=?and name=?";
        return  sqLiteDatabase.rawQuery(sql,new String[]{num,name});

    }
}


