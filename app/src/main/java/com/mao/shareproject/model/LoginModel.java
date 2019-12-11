package com.mao.shareproject.model;

import com.mao.shareproject.base.netdata.BaseObjectBean;
import com.mao.shareproject.base.retrofit.ApiClient;
import com.mao.shareproject.base.retrofit.ApiStores;
import com.mao.shareproject.bean.User;
import com.mao.shareproject.contract.LoginContract;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<BaseObjectBean<User>> login(String phone, String code, String user_type) {
        return ApiClient.getmRetrofit().create(ApiStores.class).login(phone, code, user_type);
    }


}
