package com.example.demo.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户表，包含用于基本信息
 */
@Entity
public class User {

    @Id
    private String phone;       //用户手机号，这是用户的唯一标识
    private String password;    //用户密码
    private int sex;            //用户性别，1位男，0为女
    private String head;        //用户的头像地址
    private String nickname;    //用户昵称
    private String introduce;   //个人介绍
    private String qq;  //QQ号
    private String wechat;  //微信号
    private String email;  //邮箱号



    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
