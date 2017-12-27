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
    //TODO 因为还没和九商讨，所以还没有实现全部属性


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
}
