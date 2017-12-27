package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 此控制器用于登录，注册，忘记密码
* */

@Controller
@RequestMapping("/Login")
public class LoginController {


    @Autowired
    UserRepository userRepository;

    @RequestMapping("/checkIn/{username}/{password}")
    @ResponseBody
    public User checkIn(@PathVariable String username,@PathVariable("password") String password){

        User user = userRepository.findByPhoneAndPassword(username,password);

        if(user!=null){
            return user;
        }

        return null;
    }

    @RequestMapping("/registerUser/{username}/{password}")
    public void registerUser(@PathVariable String username,@PathVariable("password") String password){

        User user = new User(username,password);
        userRepository.save(user);

    }

    @RequestMapping("/forgetPassword/{username}")
    @ResponseBody
    public String forgetPassword(@PathVariable String username){

        String password = userRepository.findPasswordByPhone(username);

        if(password!=null){
            return password;
        }

        return null;
    }

}
