package com.mao.shareproject.base.retrofit;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract  class ApiCallback<M> extends DisposableObserver<M> {


    protected abstract void onSuccess(M model);

    protected abstract void onFailure(String msg);

    protected abstract void onFinish();



    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.d("maomao",code+"=="+msg);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
