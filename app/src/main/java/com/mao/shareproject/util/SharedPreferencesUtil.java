package com.mao.shareproject.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mao.shareproject.MyApplication;

import static android.provider.Contacts.SettingsColumns.KEY;

public class SharedPreferencesUtil {

    /**
     * SharedPreferences 对象
     */
    private static SharedPreferences sharedPreferences;

    private static final String FILE_PREFIX="com.mao.shareproject_";


    /**
     * 获取应用application的Context
     * @return
     */
    private static Context getContext(){
        return MyApplication.applicationContext;
    }



    public static <T> void saveData( String key, T value){
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(FILE_PREFIX+key,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (value instanceof String){
            edit.putString(key, (String) value);
        }else if (value instanceof Boolean){
            edit.putBoolean(key, (Boolean) value);
        }else if (value instanceof Integer){
            edit.putInt(key, (Integer) value);
        }
        edit.apply();
    }

    /**
     * 获取string值
     * @param key
     * @return
     */
  public static String getString(String key){
    SharedPreferences sharedPreferences = getContext().getSharedPreferences(FILE_PREFIX+KEY , Context.MODE_PRIVATE);
      String mString = sharedPreferences.getString(key,null);
      return mString;
  }


  public static boolean  getBoolean(String key){
     sharedPreferences = getContext().getSharedPreferences(FILE_PREFIX+key,Context.MODE_PRIVATE);
     return sharedPreferences.getBoolean(key,false);
  }








}
