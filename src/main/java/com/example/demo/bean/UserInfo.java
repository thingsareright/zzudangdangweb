package com.example.demo.bean;
/**
 * 这个类用来向用户返回除了用户密码之外的其他信息
 */
public class UserInfo {
    private String phone;       //用户手机号，这是用户的唯一标识
    private String head;        //用户的头像地址
    private String nickname;    //用户昵称
    private String introduce;   //个人介绍
    private String qq;  //QQ号
    private String wechat;  //微信号
    private String email;  //邮箱号

    public UserInfo() {
    }

    public UserInfo(String phone, String head, String nickname, String introduce, String qq, String wechat, String email) {
        this.phone = phone;
        this.head = head;
        this.nickname = nickname;
        this.introduce = introduce;
        this.qq = qq;
        this.wechat = wechat;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
