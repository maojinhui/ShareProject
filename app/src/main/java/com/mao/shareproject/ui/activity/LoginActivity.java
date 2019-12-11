package com.mao.shareproject.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mao.shareproject.R;
import com.mao.shareproject.base.ui.BaseActivity;
import com.mao.shareproject.contract.LoginContract;
import com.mao.shareproject.presenter.LoginPresenter;
import com.mao.shareproject.ui.view.DispatchView;
import com.mao.shareproject.ui.view.DispatchView2;
import com.mao.shareproject.ui.view.DispatchViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.login_group)
    DispatchViewGroup loginGroup;
    @BindView(R.id.dispatchview1)
    DispatchView dispatchview1;
    @BindView(R.id.dispatchview2)
    DispatchView2 dispatchview2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        loginGroup.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return false;
//            }
//        });
//
//
//        dispatchview1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("maomao","activity ontouch dispatchview1");
//
//                return false;
//            }
//        });

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        mPresenter.login("18510190638", "111111", "1");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("maomao","LoginActivity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("maomao","LoginActivity onTouchEvent");
        return super.onTouchEvent(event);
    }
}
