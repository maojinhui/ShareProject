package com.mao.shareproject.base.netdata;

import com.google.common.base.MoreObjects;

public class BaseObjectBean<T> {

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */

    private int stat;
    private String msg;
    private T data;

    public int getErrorCode() {
        return stat;
    }

    public void setErrorCode(int errorCode) {
        this.stat = errorCode;
    }

    public String getErrorMsg() {
        return msg;
    }

    public void setErrorMsg(String errorMsg) {
        this.msg = errorMsg;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("stat", stat)
                .add("msg", msg)
                .add("result", data)
                .toString();
    }
}
