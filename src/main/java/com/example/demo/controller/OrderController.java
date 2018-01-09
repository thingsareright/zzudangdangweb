package com.example.demo.controller;

import com.example.demo.bean.GoodsInfo;
import com.example.demo.bean.OrderCart;
import com.example.demo.bean.ShopCart;
import com.example.demo.bean.StoreInfo;
import com.example.demo.dao.*;
import com.example.demo.dao.entity.*;
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
@RequestMapping("/Order")
public class OrderController {

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

    @RequestMapping("/forOrder")
    public OrderCart requestForShopCart(@RequestParam(name = "phone", defaultValue = "0") String phone){
        OrderCart orderCart = new OrderCart();

        //先判断参数是否合法
        if (phone.equals("0") || !CheckInputUtils.checkTel(phone))
            return orderCart;

        List<Order> orderList = new ArrayList<>();
        orderList = orderRepository.findAllByPhoneOrderByBossid(phone);

        List<StoreInfo> groups = new ArrayList<>(); //组元素的列表
        Map<Integer, List<GoodsInfo>> childs = new HashMap<>(); //子元素的列表

        //笨且低效的方法
        //先填充groups
        if (orderList == null)
            return orderCart;
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

        orderCart.setGroups(groups);
        orderCart.setChilds(childs);

        return orderCart;
    }

    @RequestMapping("/insertOrder")
    public int insertShopCart(@RequestParam(name = "phone", defaultValue = "0")String phone,
                              @RequestParam(name = "bookid", defaultValue = "0")int bookid,
                              @RequestParam(name = "bossid", defaultValue = "0")int bossid,
                              @RequestParam(name = "num", defaultValue = "0") int num) {
        if (!CheckInputUtils.checkTel(phone) || bookid == 0 || bossid == 0)
            return 0;

        Book book = bookRepository.findById(bookid);
        BookBoss bookBoss = bookBossRepository.findByBookidAndBossid(bookid, bossid);
        BookBossPicture bookBossPicture = bookBossPictureRepository.findTopByBookidAndBossid(bookid, bossid);
        Boss boss = bossRepository.findById(bossid);

        //先判断服务器里是不是有且仅有这么一条记录
        List<Order> orderList = orderRepository.findAllByPhoneAndBookidAndBossid(phone, bookid, bossid);
        if (orderList != null & orderList.size() == 1){
            orderList.get(0).setNumber(orderList.get(0).getNumber() + 1);
            orderList.get(0).setBossname(boss.getBossName());
            orderList.get(0).setBosspicture(boss.getBossPicture());
            orderList.get(0).setPrice(bookBoss.getPrice());
            orderList.get(0).setBookname(book.getBookname());
            orderList.get(0).setBookpicture(bookBossPicture.getPictureurl());
            orderRepository.save(orderList.get(0));
        } else {
            //否则则选择插入
            Order order = new Order();
            order.setBookid(bookid);
            order.setBossid(bossid);
            order.setPhone(phone);



            order.setNumber(1);
            order.setBossname(boss.getBossName());
            order.setBosspicture(boss.getBossPicture());
            order.setPrice(bookBoss.getPrice());
            order.setBookname(book.getBookname());
            order.setBookpicture(bookBossPicture.getPictureurl());
            orderRepository.save(order);
        }

        return 1;
    }
}
