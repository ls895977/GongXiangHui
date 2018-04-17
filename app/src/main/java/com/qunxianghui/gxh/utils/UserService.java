package com.qunxianghui.gxh.utils;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class UserService {

//
//    private final DatabaseHelper dbHelpter;
//
//    public UserService(Context context) {
//        dbHelpter = new DatabaseHelper(context);
//    }
//
//    public boolean login(String username, String password) {
//        SQLiteDatabase sdb = dbHelpter.getReadableDatabase();
//        String sql = "select * from user where username=? and password=?";
//        Cursor cursor = sdb.rawQuery(sql, new String[]{username, password});
//
//        if (cursor.moveToFirst() == true) {
//            cursor.close();
//        }
//        return true;
//    }
//
//    public boolean register(UserBean userBean) {
//        SQLiteDatabase sdb = dbHelpter.getReadableDatabase();
//        String sql = "insert into user(username,password) values(?,?)";
//        Object object[] = {userBean.getUsername(), userBean.getPassword()};
//        sdb.execSQL(sql, object);
//        return true;
//    }
}
