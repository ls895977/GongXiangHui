package com.gongxianghui.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gongxianghui.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("SqliteHelpter", "数据库的创建");
        String sql = "create table person(_id integer Primary Key autoincrement,name varchar(20),age integer ,sex varchar(20))";
        db.execSQL(sql);
    }

    /**
     * 数据库更新
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("sqliteHelpter", "数据库更新");

    }

    /**
     * 添加person到数据库
     */
    public void addPerson(Person person) {
        Log.e("sqliteHelpter", "插入");
        final SQLiteDatabase db = getWritableDatabase(); //以读写的形式打开数据库
        db.execSQL("insert into person(name,age,sex)values(" + String.format("'%s'", person.getName()) + ","
                + person.getAge() + ","
                + String.format("'%s'", person.getSex()) + ")");
        db.close();
    }

    /**
     * 更新person
     */

    public  void updataPerson(Person person){
        Log.e("SqliteHelper", "更新");
         SQLiteDatabase db = getWritableDatabase();

        String sql="update person set name="
                + String.format("'%s'",person.getName())
                +",age="+person.getAge()
                +",sex="+ String.format("'%s'",person.getSex())
                +"where _id="+person.get_id();
        Log.e("updatePerson", sql);

        db.execSQL(sql);  //更新数据库
        db.close();
    }
    /**
     * 删除Person
     */

    public  void delectPerson(int _id){
        Log.e("SqliteHelpte","删除");
        final SQLiteDatabase db = getWritableDatabase();
        String sql="_id=?";
        String wheres[]={String.valueOf(_id)};
        db.delete("person",sql,wheres);  //数据库删除
        db.close();
    }

    /**
     * 查询所有的Person
     */

    public List<Person> queryAllPersion() {
        List<Person> list = new ArrayList<>();
        final SQLiteDatabase db = getReadableDatabase();//以只读的方式打开数据库
        String sql = "select * from person";
        final Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            final int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            final String name = cursor.getString(cursor.getColumnIndex("name"));
            final int age = cursor.getInt(cursor.getColumnIndex("age"));
            final String sex = cursor.getString(cursor.getColumnIndex("sex"));
            final Person person = new Person();
            person.set_id(_id);
            person.setName(name);
            person.setSex(sex);
            person.setAge(age);
            System.out.println("---sex=" + sex);
            list.add(person);

        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 根据id查询person
     */
    public Person queryPersonById(int _id) {
        Person person = null;
        final SQLiteDatabase db = getReadableDatabase();//以只读的方式打开数据库
        String[] colums = {"_id", "name", "age", "sex"};
        String selection = "_id=?";
        String[] selectionArgs = {String.valueOf(_id)};
        final Cursor cursor = db.query("person", colums, selection, selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            person = new Person();
            person.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
        }
        return person;
    }


}
