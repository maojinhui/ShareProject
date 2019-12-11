package com.mao.shareproject.presenter;

import android.util.Log;

import com.mao.shareproject.base.mvp.BasePresenter;
import com.mao.shareproject.base.netdata.BaseObjectBean;
import com.mao.shareproject.base.retrofit.ApiCallback;
import com.mao.shareproject.bean.User;
import com.mao.shareproject.contract.LoginContract;
import com.mao.shareproject.model.LoginModel;


public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {


    private LoginModel loginModel;



    public LoginPresenter(LoginContract.View view){
        loginModel = new LoginModel();
        attachView(view);
    }


    @Override
    public void login(String phone, String code, String user_type) {
        if (!isAttached()){
            return;
        }

        mView.get().showToast("开始请求网络");
        addSubscription(loginModel.login(phone, code, user_type),
                new ApiCallback<BaseObjectBean<User>>() {

                    @Override
                    public void onSuccess(BaseObjectBean<User> model) {
                        Log.d("maomao","data:"+model.getResult().toString());
                        mView.get().showToast("数据获取成功");
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                }
        );




    }



}
