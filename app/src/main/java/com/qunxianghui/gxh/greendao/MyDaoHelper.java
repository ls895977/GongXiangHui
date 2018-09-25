package com.qunxianghui.gxh.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.qunxianghui.gxh.greendao.gen.DaoMaster;
import com.qunxianghui.gxh.greendao.gen.DaoSession;
import com.qunxianghui.gxh.greendao.gen.NewsEntityDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/9/25
 * @describe:
 */


public class MyDaoHelper {
    private static MyDaoHelper instance;
    private NewsEntityDao dao;

    private MyDaoHelper(Context context) {
        try {
            MyDatabaseHelper helper = new MyDatabaseHelper(context, "news-db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            DaoSession session = daoMaster.newSession();
            dao = session.getNewsEntityDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyDaoHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MyDaoHelper(context);
        }
        return instance;
    }

    public void addData(NewsEntity bean) {
        if (dao != null && bean != null) {
            dao.insertOrReplace(bean);
        }
    }

    public void deleteData(long id) {
        if (dao != null && !TextUtils.isEmpty(id + "")) {
            dao.deleteByKey(id);
        }
    }

    public NewsEntity getDataById(long id) {
        if (dao != null && !TextUtils.isEmpty(id + "")) {
            return dao.load(id);
        }
        return null;
    }

    public List<NewsEntity> getAllData() {
        if (dao != null) {
            return dao.loadAll();
        }
        return null;
    }

    public long getTotalCount() {
        if (dao == null) {
            return 0;
        }
        QueryBuilder<NewsEntity> qb = dao.queryBuilder();
        return qb.buildCount().count();
    }

    public void deleteAll() {
        if (dao != null) {
            dao.deleteAll();
        }
    }
}
