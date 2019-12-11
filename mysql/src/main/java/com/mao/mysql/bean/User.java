package com.mao.mysql.bean;


import com.mao.mysql.annotations.DbField;
import com.mao.mysql.annotations.DbTable;
import com.mao.mysql.annotations.Index;

@DbTable("maomao_user")
public class User {



    @DbField("u_name")
    @Index("name")
    private String name;

    @DbField("u_age")
    private int age;

    @DbField("u_header")
    private byte[] img;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
