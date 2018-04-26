package com.qunxianghui.gxh.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.qunxianghui.gxh.bean.home.User;

import java.util.ArrayList;


public class UserDao {
    private Context context;

    private UserOpenHelper userOpenHelper;//创建dbhelper对象
    private SQLiteDatabase sqliteDatabase;
    public UserDao(Context context) {  //定义构造函数
        //初始化DBOpenHelper对象
        userOpenHelper = new UserOpenHelper(context,null,null,0);

    }

    /**
     * 增加数据
     * @param username
     * @param password
     */
    public void dbInsert(String username,String password){
        sqliteDatabase= userOpenHelper.getWritableDatabase();
        String sql="insert into t_user(username,password,isDel) values(?,?,0)";
        Object bindArgs[]=new Object[]{username,password};
        sqliteDatabase.execSQL(sql,bindArgs);
    }

    public void  dbUpdatePassword(String username,String newPassword){
        sqliteDatabase= userOpenHelper.getWritableDatabase();
        String sql="update t_user set password=? where username=? and isDel=0";
        Object bindArgs[]=new Object[]{newPassword,username};
        sqliteDatabase.execSQL(sql,bindArgs);

    }

    /**
     * 查询用户的数量
     *
     * @param
     * @return
     */

    public int dbGetUserSize(){
       sqliteDatabase= userOpenHelper.getWritableDatabase();
       String sql="select count(*) from t_user where isDel=0";
         Cursor cursor = sqliteDatabase.rawQuery(sql, null);
        if(cursor.moveToNext()){
            //判断cusor中是否有数据
            return cursor.getInt(0); //返回总记录数

        }
        return 0;  //如果没有数据 则返回0
    }
    public User dbQueryOneByUsername(String username) {
        sqliteDatabase = userOpenHelper.getWritableDatabase();
        String sql = "select * from t_user where username=? and isDel=0";
        String[] selectActionArgs = new String[]{username};
        Cursor cursor = sqliteDatabase.rawQuery(sql, selectActionArgs);
        if (cursor.moveToNext()) {
            //判断curson中是否有数据
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            return user; //返回总记录数

        }
        return null;
    }

public ArrayList<User> dbQueryAll(){
        ArrayList<User> userArrayList=new ArrayList<User>();
         sqliteDatabase= userOpenHelper.getWritableDatabase();
           String sql="select * from t_user where isDel=0";
    final Cursor cursor = sqliteDatabase.rawQuery(sql, null);
    for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
        if(cursor.getInt(cursor.getColumnIndex("isDel"))!=1){
           User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            userArrayList.add(user);
        }
    }

    return  userArrayList;

}

public void dbDeleteUser(int id){
        sqliteDatabase= userOpenHelper.getWritableDatabase();
        String sql="update t_user set isDel=1 where id=?";
        Object bindArgs[]=new Object[]{id};
        sqliteDatabase.execSQL(sql,bindArgs);
}

}
