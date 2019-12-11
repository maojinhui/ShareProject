package com.mao.shareproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mao.shareproject.adapter.WelcomePagerAdapter;
import com.mao.shareproject.adapter.transformer.WelcomPagerTransformer;
import com.mao.shareproject.base.mvp.BasePresenter;
import com.mao.shareproject.base.mvp.BaseView;
import com.mao.shareproject.base.ui.BaseActivity;
import com.mao.shareproject.enums.SharedPreferencesFileKey;
import com.mao.shareproject.ui.activity.LoginActivity;
import com.mao.shareproject.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.welcome_viewpager)
    ViewPager welcomeViewpager;


    private WelcomePagerAdapter welcomePagerAdapter;

    WelcomPagerTransformer welcomPagerTransformer;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        boolean b = SharedPreferencesUtil.getBoolean(SharedPreferencesFileKey.FIRST_ENTRY.getFileName());
        setWelcomeViewpager();
        Window window = getWindow();
        View decorView = window.getDecorView();


    }


    private void setWelcomeViewpager() {
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.welcome1);
        data.add(R.mipmap.welcome2);
        data.add(R.mipmap.welcome1);

        welcomePagerAdapter = new WelcomePagerAdapter(data, this);
        //设置pager的适配代码
        welcomeViewpager.setAdapter(welcomePagerAdapter);
        //可以添加多个监听
        welcomeViewpager.addOnPageChangeListener(this);
        //只能添加一个固定的监听
//        welcomeViewpager.setOnPageChangeListener(this);
        welcomPagerTransformer = new WelcomPagerTransformer();
        welcomeViewpager.setPageTransformer(false, welcomPagerTransformer);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        showToast("select position " + position);
    }


    boolean flag = false;

    @Override
    public void onPageScrollStateChanged(int state) {

        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING://拖拽状态
                flag = false;
                break;
            case ViewPager.SCROLL_STATE_IDLE://自动滑动状态
                break;
            case ViewPager.SCROLL_STATE_SETTLING://静止状态

                break;
            default:
                break;
        }


    }



    /*********** 利用activity事件分发机制实现viewpager滑动最后一页左滑进入登录ACTIVITY *******************/
    /**
     * @param ev
     * @return
     */

    float startX = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (welcomeViewpager.getCurrentItem()!=2){
            return super.dispatchTouchEvent(ev);
        }
        float minDistance = 20;

        float endX = 0;
        float distance = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                Log.d("maomao","action_down："+startX);
                break;
            case MotionEvent.ACTION_MOVE:
                endX = ev.getX();
                distance = endX - startX;
                Log.d("maomao","滑动距离："+endX+"-"+startX);
                if (distance<minDistance){
                    return onTouchEvent(ev);
                }else{
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;

        }

//        if (welcomeViewpager.getCurrentItem() == 2) {
//            return onTouchEvent(ev);
//        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean isStart= false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!isStart){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
            isStart=true;
            finish();
        }
        return super.onTouchEvent(event);
    }



}
