package com.onecalf.hard.buck;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.onecalf.hard.buck.annotion.DbFiled;
import com.onecalf.hard.buck.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseDao<T> implements IBaseDao<T>{

    /**
     * 持有数据库操作类的引用
     */
    private SQLiteDatabase database;

    private Class<T> entityClass;

    private boolean isInit = false;


    private String tableName;

    private Map<String,Field> cacheMap;



    public synchronized boolean init(Class<T> entity,SQLiteDatabase sqLiteDatabase){
        if(!isInit){
            entityClass = entity;
            database = sqLiteDatabase;

            //通过反射获取表名
            tableName = entity.getAnnotation(DbTable.class).value();


            //数据库对象没有打开
            if(!sqLiteDatabase.isOpen()){
                return false;
            }

            //建表失败
            if(!autoCreateTable()){
                return false;
            }

            isInit = true;
        }

        return isInit;
    }

    // create table if not exists usser2(tb_name TEXT,tb_password BIGINT)
    private boolean autoCreateTable() {
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(tableName + " ( ");

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields){
            Class type = field.getType();
            String typeName = field.getAnnotation(DbFiled.class).value();

            if(type == String.class){
                sb.append(typeName + " TEXT,");
            }else if(type == Double.class){
                sb.append(typeName + " DOUBLE,");
            }else if(type == Integer.class){
                sb.append(typeName + " INTEGER,");
            }else if(type == Long.class){
                sb.append(typeName + " BIGINT,");
            }else if(type == byte[].class){
                sb.append(typeName + " BLOB,");
            }else if(type == int.class){
                sb.append(typeName + " INTEGER,");
            }else {
                //不支持的类型
                continue;
            }
        }


        if(sb.charAt(sb.length() - 1) == ','){
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(")");

        Log.e("zhj","sql=" + sb.toString());

        try {
            this.database.execSQL(sb.toString());
            initCacheMap();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void initCacheMap(){

        //映射关系
        /**
         * 情况1 其它开发者，更改了表结构
         * 情况2 版本升级了，最新版本在某个表中删除了一个字段，由于数据库版本没有，导致插入这个对象时，产生崩溃
         */

        cacheMap = new HashMap<>();

        //查一次空表
        String sql = "select * from " + this.tableName + " limit 1,0";
        Cursor cursor = database.rawQuery(sql,null);
        String[] colmunNames = cursor.getColumnNames();
        Field[] colmunFields = entityClass.getDeclaredFields();

        for (String colmunName : colmunNames){
            for (Field field :colmunFields ){
                if(colmunName.equals(field.getAnnotation(DbFiled.class).value())){
                    cacheMap.put(colmunName,field);
                    break;
                }
            }
        }

        cursor.close();
    }


    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int update(T entity, T where) {
        return 0;
    }
}