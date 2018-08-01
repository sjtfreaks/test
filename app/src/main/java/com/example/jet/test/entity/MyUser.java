package com.example.jet.test.entity;
import cn.bmob.v3.BmobUser;

/**
 * project name:TestEsc
 * package name:com.ff2333.www.testesc.entity
 * file name:MyUser
 * Created by Administrator on 2018-03-14.
 */
public class MyUser extends BmobUser{
    private String pass;
    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
