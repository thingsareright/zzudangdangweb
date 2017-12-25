package com.example.demo.controller;

import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类用来处理有关购物车的请求
 */

@RestController
@RequestMapping("/ShopCart")
public class ShopCartController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookBossRepository bookBossRepository;

    @Autowired
    BookBossPictureRepository bookBossPictureRepository;

    @Autowired
    BossRepository bossRepository;

    @Autowired
    UserTokenRepository userTokenRepository;


}
