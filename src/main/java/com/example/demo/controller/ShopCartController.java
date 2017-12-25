package com.example.demo.controller;

import com.example.demo.bean.GoodsInfo;
import com.example.demo.bean.ShopCart;
import com.example.demo.bean.StoreInfo;
import com.example.demo.dao.*;
import com.example.demo.dao.entity.Order;
import com.example.demo.util.CheckInputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/forShopCart")
    public ShopCart requestForShopCart(@RequestParam(name = "phone", defaultValue = "0") String phone){
        ShopCart shopCart = new ShopCart();

        //先判断参数是否合法
        if (phone.equals("0") || !CheckInputUtils.checkTel(phone))
            return shopCart;

        List<Order> orderList = new ArrayList<>();
        orderList = orderRepository.findAllByPhoneOrderByBossid(phone);

        List<StoreInfo> groups = new ArrayList<>(); //组元素的列表
        Map<Integer, List<GoodsInfo>> childs = new HashMap<>(); //子元素的列表

        //笨且低效的方法
        //先填充groups
        if (orderList == null)
            return shopCart;
        for (int i=0; i<orderList.size(); i++) {
            StoreInfo storeInfo = new StoreInfo( orderList.get(i).getBossid(),
                    orderList.get(i).getBookname(), orderList.get(i).getBosspicture());
            if (!groups.contains(storeInfo)){
                groups.add(storeInfo);
            }
        }
        //紧接着再填充GoodsInfo
        for (int i=0; i<groups.size(); i++) {
            StoreInfo storeInfo = groups.get(i);
            List<GoodsInfo> goodsInfoList = new ArrayList<>();
            for (int j=0; j<orderList.size(); j++) {
                if ((orderList.get(j).getBossid()) == (storeInfo.getId())){
                    goodsInfoList.add(
                            new GoodsInfo(orderList.get(j).getBookid(), orderList.get(j).getBookname(), orderList.get(j).getPrice(),
                                    orderList.get(j).getPrice() + new Random().nextInt(1500)/100, ""+1555 + new Random().nextInt(3000), orderList.get(j).getBookpicture(), orderList.get(j).getNumber(),0));
                }
                childs.put(storeInfo.getId(), goodsInfoList);
            }
        }

        shopCart.setGroups(groups);
        shopCart.setChilds(childs);

        return shopCart;
    }
}
