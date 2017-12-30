package com.example.demo.controller;

import com.example.demo.bean.UserInfo;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import com.example.demo.util.CheckInputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody User user){

        userRepository.saveAndFlush(user);

    }

    @RequestMapping("/changePassword/{phone}/{password}")
    public void changePassword(@PathVariable("phone") String phone,
                                @PathVariable("password") String password){
        userRepository.changePassword(phone,password);

    }

    @RequestMapping("/registerUser/{phone}/{password}")
    public void registerUser(@PathVariable("phone") String phone,
                               @PathVariable("password") String password){
        User user = new User(phone,password);
        userRepository.save(user);

    }

    /**
     * 用户向用户返回用户非机密信息
     * @param phone 用户ID
     * @return  除了密码之外的用户信息
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                @RequestParam(name = "password", defaultValue = "0") String password){
        UserInfo userInfo = new UserInfo();
        User user = new User();

        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0")) {
            return userInfo;
        }

        user = userRepository.findByPhoneAndPassword(phone,password);
        if (user == null)
            return userInfo;

        userInfo.setEmail(user.getEmail());
        userInfo.setHead(user.getHead());
        userInfo.setIntroduce(user.getIntroduce());
        userInfo.setNickname(user.getNickname());
        userInfo.setPhone(user.getPhone());
        userInfo.setQq(user.getQq());
        userInfo.setWechat(user.getWechat());
        userInfo.setSex(user.getSex());
        return userInfo;
    }

    /**
     * 下面的几个路径都是用来更改用户普通信息的
     */

    //更改用户的头像，涉及到图片上传问题，以后再弄     TODO

    /**
     * 这个路径用于修改用户自我介绍
     * @param phone 用户手机
     * @param password 用户密码
     * @param introduce 用户自我介绍
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserIntroduce")
    public String updateUserIntroduce(@RequestParam(name = "phone", defaultValue = "0") String phone,
                               @RequestParam(name = "password", defaultValue = "0") String password,
                               @RequestParam(name = "introduce",defaultValue = "0") String introduce){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0") || introduce.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setIntroduce(introduce);
        userRepository.save(user);
        return "1";
    }

    /**
     * 这个路径用于修改邮箱
     * @param phone 用户手机
     * @param password 用户密码
     * @param email 用户新邮箱
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserEmail")
    public String updateUserEmail(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                  @RequestParam(name = "password", defaultValue = "0") String password,
                                  @RequestParam(name = "email",defaultValue = "0") String email){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0") || email.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setEmail(email);
        userRepository.save(user);
        return "1";
    }

    /**
     * 这个路径用于修改用户昵称
     * @param phone 用户手机
     * @param password 用户密码
     * @param nickname 用户新昵称
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserNickname")
    public String updateUserNickname(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                  @RequestParam(name = "password", defaultValue = "0") String password,
                                  @RequestParam(name = "nickname",defaultValue = "0") String nickname){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0") || nickname.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setNickname(nickname);
        userRepository.save(user);
        return "1";
    }

    /**
     * 这个路径用于修改QQ
     * @param phone 用户手机
     * @param password 用户密码
     * @param qq 用户新QQ号
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserQQ")
    public String updateUserQQ(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                     @RequestParam(name = "password", defaultValue = "0") String password,
                                     @RequestParam(name = "qq",defaultValue = "0") String qq){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0") || qq.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setQq(qq);
        userRepository.save(user);
        return "1";
    }


    /**
     * 这个路径用于修改微信号
     * @param phone 用户手机
     * @param password 用户密码
     * @param wechat 用户新微信号
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserWeChat")
    public String updateUserWeChat(@RequestParam(name = "phone", defaultValue = "0") String phone,
                               @RequestParam(name = "password", defaultValue = "0") String password,
                               @RequestParam(name = "wechat",defaultValue = "0") String wechat){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0") || wechat.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setWechat(wechat);
        userRepository.save(user);
        return "1";
    }

    /**
     * 这个路径用于修改性别
     * @param phone 用户手机
     * @param password 用户密码
     * @param sex 用户新微信号
     * @return 返回1表示修改成功，返回0表示修改失败
     */
    @RequestMapping("/updateUserSex")
    public String updateUserSex(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                   @RequestParam(name = "password", defaultValue = "0") String password,
                                   @RequestParam(name = "sex",defaultValue = "0") int sex){
        if (!CheckInputUtils.checkTel(phone) || phone.equals("0") || password.equals("0")) {
            return "0";
        }

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null)
            return "0";

        user.setSex(sex==0?0:1);
        userRepository.save(user);
        return "1";
    }
}
