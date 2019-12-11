package com.mao.shareproject.base.netdata;

import java.util.List;

public class BaseArrayBean<T> {
    /**
     * status : 1
     * msg : 获取成功
     * result : [] 数组
     */

    private int stat;
    private String msg;
    private List<T> data;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return data;
    }

    public void setResult(List<T> result) {
        this.data = result;
    }


}
