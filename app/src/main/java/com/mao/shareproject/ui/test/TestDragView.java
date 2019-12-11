package com.mao.shareproject.ui.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.mao.mysql.BaseDaoFactory;
import com.mao.mysql.bean.User;
import com.mao.mysql.sqlie.BaseDao;
import com.mao.shareproject.R;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestDragView extends Activity {

    String TAG = "maomao";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgrag);
//        BaseDao<User> baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
//        User user = new User();
//        user.setName("123456");
//        user.setAge(10);
//        long l = baseDao.insert(user);
//        Log.d(TAG,""+l);

        WindowManager windowManager = getWindowManager();
//        getSystemService("Windowma")

        HashMap map ;
        ConcurrentHashMap map1;
//        map1.put()

    }

    class MyAsyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    }

}
