package com.mao.shareproject.base.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.shareproject.base.mvp.BasePresenter;
import com.mao.shareproject.base.mvp.BaseView;

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity
        implements BaseView {


    protected P mPresenter;

    protected  abstract  P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }
}
