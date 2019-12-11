package com.mao.mysql;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.mao.mysql.bean.User;
import com.mao.mysql.sqlie.BaseDao;
import com.mao.mysql.sqlie.IBaseDao;

public class BaseDaoFactory {

    private static String TAG = "maomao";

    private  static  BaseDaoFactory factory = new BaseDaoFactory();

    private static  String path = "data/data/com_mao_shareproject/ab.db";

    SQLiteDatabase sqLiteDatabase;

    public static BaseDaoFactory getInstance(){
        return factory;
    }

    private BaseDaoFactory(){
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path , null);
    }


    public <T> BaseDao<T> getBaseDao(Class<T> tClass){
        BaseDao baseDao = new BaseDao();
        baseDao.init(User.class, sqLiteDatabase);
        return baseDao;
    }






}
