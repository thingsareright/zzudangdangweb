package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody User user){

        userRepository.saveAndFlush(user);

    }
}
