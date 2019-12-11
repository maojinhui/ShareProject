package com.mao.shareproject.bean;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class User {


    /**
     * registip : 192.168.0.114
     * unionid : oJN_N5vbEN8SwJxrlaJ5ccVant-M
     * city : Chaoyang
     * loginType : 1
     * openid : oM82-5rfbj8CP6_oQFbjh-ReJynU
     * accessToken : 0f10fdb8d7cd4364b700a12784dc536a
     * province : Beijing
     * createTime : 1563450268000
     * phone : 18510190638
     * checkState : 1
     * name : 毛毛虫🐛
     * id : 66193256a95111e9bd2c00163e0542e2
     * state : 1
     * userType : [3,1,2]
     * thirdToken : 26_DXYkFHK5-iPMPelU7Ab1y7inY_9ufYAF7Z16CyMP7CyFW8ZHq8aROYyoHKnExN97sdWYZ1NhIrLhx6Dvs81haI1MbUlDTzOtWy3LwCCrvbo
     * thirdType : 1
     * headerimg : http://www.shuidiandian.com.cn/server/user/img/default.png
     */

    private String registip;
    private String unionid;
    private String city;
    private int loginType;
    private String openid;
    private String accessToken;
    private String province;
    private long createTime;
    private String phone;
    private int checkState;
    private String name;
    private String id;
    private int state;
    private String userType;
    private String thirdToken;
    private int thirdType;
    private String headerimg;

    public String getRegistip() {
        return registip;
    }

    public void setRegistip(String registip) {
        this.registip = registip;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getThirdToken() {
        return thirdToken;
    }

    public void setThirdToken(String thirdToken) {
        this.thirdToken = thirdToken;
    }

    public int getThirdType() {
        return thirdType;
    }

    public void setThirdType(int thirdType) {
        this.thirdType = thirdType;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("registip", registip)
                .add("unionid", unionid)
                .add("city", city)
                .add("loginType", loginType)
                .add("openid", openid)
                .add("accessToken", accessToken)
                .add("province", province)
                .add("createTime", createTime)
                .add("phone", phone)
                .add("checkState", checkState)
                .add("name", name)
                .add("id", id)
                .add("state", state)
                .add("userType", userType)
                .add("thirdToken", thirdToken)
                .add("thirdType", thirdType)
                .add("headerimg", headerimg)
                .toString();
    }
}
