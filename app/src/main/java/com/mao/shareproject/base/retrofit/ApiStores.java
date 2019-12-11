package com.mao.shareproject.base.retrofit;


import com.mao.shareproject.base.netdata.BaseObjectBean;
import com.mao.shareproject.bean.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiStores {

    /**
     * 登录注册统一接口
     *
     * @param userName
     * @param code
     * @param user_type
     * @return
     */
    @FormUrlEncoded
    @POST("user/signUpIn.do")
    Observable<BaseObjectBean<User>> login(@Field(value = "phone") String userName, @Field(value = "code") String code,
                                   @Field(value = "user_type") String user_type);


    @FormUrlEncoded
    Call<User> login();


}
