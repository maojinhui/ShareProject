package com.mao.shareproject.contract;

import com.mao.shareproject.base.mvp.BaseView;
import com.mao.shareproject.base.netdata.BaseObjectBean;
import com.mao.shareproject.bean.User;

import io.reactivex.Observable;

public interface LoginContract {
    interface Model {
        Observable<BaseObjectBean<User>> login(String phone , String code , String user_type);
    }

    interface View extends BaseView {
    }

    interface Presenter {
        void login(String phone ,String code ,String user_type);
    }
}
