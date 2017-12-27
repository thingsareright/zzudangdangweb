package com.example.demo.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 实现用户验证码的类，可用于发送验证码也可用于验证验证码
 */
@Entity
public class UserToken {

    private String phone; //电话号码
    private String token;   //短信验证码的内容

    public UserToken(String phone, String token) {
        this.phone = phone;
        this.token = token;
    }

    public UserToken() {
    }

    @Id
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
