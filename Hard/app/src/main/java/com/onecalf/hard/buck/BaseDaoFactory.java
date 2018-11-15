package com.onecalf.hard.buck;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class BaseDaoFactory {
    private String sqliteDatabasePath;

    private SQLiteDatabase sqlLiteDatabase;

    public BaseDaoFactory(){
        sqliteDatabasePath = Environment.getDataDirectory().getAbsolutePath() + "user.db";
        openDatabase();
    }

    private void openDatabase() {
        this.sqlLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
    }

//    public synchronized <T extends BaseDao<M> , M > T getDataHelper(Class<T> clazz,Class<M>){
//          BaseDao baseDao = null;
//
//        try {
//            baseDao = clazz.newInstance();
////            baseDao.init();
//
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }

}









