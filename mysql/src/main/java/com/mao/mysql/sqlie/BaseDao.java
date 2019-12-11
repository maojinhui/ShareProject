package com.mao.mysql.sqlie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mao.mysql.annotations.DbField;
import com.mao.mysql.annotations.DbTable;
import com.mao.mysql.annotations.Index;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseDao<T> implements IBaseDao<T> {

    public static final String TAG = "maomao";

    /**
     * 要插入的数据库class对象
     */
    private Class<T> tClass;

    /**
     * 操作数据库的接口
     * 操作db文件的
     */
    private SQLiteDatabase sqLiteDatabase;

    /**
     * 数据库表名
     */
    private String dbName;


    private  boolean initFlag = false;

    /**
     * 针对每个dao是单例的
     *
     * @param tClass
     * @param sqLiteDatabase
     * @return
     */
    public  synchronized boolean init(Class<T> tClass,SQLiteDatabase sqLiteDatabase){
        if(!initFlag){
            this.tClass=tClass;
            this.sqLiteDatabase=sqLiteDatabase;
            if(!sqLiteDatabase.isOpen()){
                return false;
            }
            if(!autoCreate()){
                return false;
            }
            initFlag=true;
        }
        initCacheMap();

        return false;
    }

    /**
     * 初始化映射关系
     */
    private void initCacheMap() {

        /**
         * 情况1 版本升级后字段名和对象属性不对应需要查一次表
         */
        cacheMap = new HashMap<>();

        String sql = "select * from "+this.dbName+ " limit 1,0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        String[] columnNames = cursor.getColumnNames();
        Field[] fields = tClass.getDeclaredFields();
        for (String columnName : columnNames){

            Field targetfield = null;
            for (Field field : fields){
                if (field.getAnnotation(DbField.class).value().equals(columnName)){
                    targetfield = field;
                    break;
                }
            }
            if(targetfield!=null){
                cacheMap.put(columnName,targetfield);
            }
        }

        cursor.close();
    }


    /**
     * 自动建表方法
     * @return
     */
    private boolean autoCreate() {
        StringBuffer stringBuffer = new StringBuffer();
        dbName =  tClass.getAnnotation(DbTable.class).value();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(dbName+" ( ");
        Field[] fields = tClass.getDeclaredFields();
        for (Field f : fields) {
            String fieldValue = f.getAnnotation(DbField.class).value();
            String indexValue = f.getAnnotation(Index.class).value();

            Class<?> type = f.getType();
            if(type == String.class){
                stringBuffer.append( fieldValue +" TEXT ,");
            }else if(type == Integer.class){
                stringBuffer.append( fieldValue +" INTEGER ,");
            }else if(type == Long.class){
                stringBuffer.append( fieldValue +" BIGINT  ,");
            }else if(type == byte[].class){
                stringBuffer.append( fieldValue +" BLOB  ,");
            }
            else {
                /**
                 * 某一个类型不支持
                 */
                continue;
            }
        }
        if(stringBuffer.charAt(stringBuffer.length()-1) == ','){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        stringBuffer.append(" ) ");

        Log.d(TAG , stringBuffer.toString());

        try {
            sqLiteDatabase.execSQL(stringBuffer.toString());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * 将表字段 bean 成员变量 映射
     *
     */
    private HashMap<String,Field> cacheMap;



    @Override
    public Long insert(T entity) {
        ContentValues contentValues = getValues(entity);
        long insert = sqLiteDatabase.insert(dbName, null, contentValues);
        return insert;
    }

    private ContentValues getValues(T entity) {
        ContentValues contentValues = new ContentValues();
        Iterator<Map.Entry<String, Field>> iterator = cacheMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Field> fieldEntry = iterator.next();
            //成员变量
            Field field = fieldEntry.getValue();
            //数据库中的字段
            String key = fieldEntry.getKey();

            field.setAccessible(true);
            try {
                Object o = field.get(entity);
                Class<?> type = field.getType();
                if (type == String.class){
                    String value = (String) o;
                    contentValues.put(key,value);
                }else if(type == Integer.class){
                    Integer integer = (Integer) o;
                    contentValues.put(key,integer);
                }else if(type==Double.class){
                    Double d = (Double) o;
                    contentValues.put(key,d);


                }else if(type == byte[].class){
                    byte[] bytes = (byte[]) o;
                    contentValues.put(key,bytes);
                }else{
                    continue;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }

        return contentValues;
    }


}
