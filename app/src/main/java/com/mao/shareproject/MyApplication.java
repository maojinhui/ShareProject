package com.mao.shareproject;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {


    public  static  Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this.getBaseContext();
        List<String> arrayList =new ArrayList<>();
        arrayList.add(null);

    }





}
