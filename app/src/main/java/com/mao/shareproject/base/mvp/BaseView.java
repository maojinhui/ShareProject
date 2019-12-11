package com.mao.shareproject.base.mvp;

public interface BaseView {

    void showLoading();

    void showLoading(String msg);

    void dismissLoading();

    void showToast(String msg);

    void showToast(int resId);


}
