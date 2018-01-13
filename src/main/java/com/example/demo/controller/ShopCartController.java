package com.example.demo.controller;

import com.example.demo.bean.GoodsInfo;
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
    CartRepository cartRepository;

    @RequestMapping("/forShopCart")
    public ShopCart requestForShopCart(@RequestParam(name = "phone", defaultValue = "0") String phone){
        ShopCart shopCart = new ShopCart();

        //先判断参数是否合法
        if (phone.equals("0") || !CheckInputUtils.checkTel(phone))
            return shopCart;

        List<Cart> cartList = new ArrayList<>();
        cartList = cartRepository.findAllByPhoneOrderByBossid(phone);

        List<StoreInfo> groups = new ArrayList<>(); //组元素的列表
        Map<Integer, List<GoodsInfo>> childs = new HashMap<>(); //子元素的列表

        //笨且低效的方法
        //先填充groups
        if (cartList == null)
            return shopCart;
        for (int i=0; i<cartList.size(); i++) {
            StoreInfo storeInfo = new StoreInfo( cartList.get(i).getBossid(),
                    cartList.get(i).getBossname(), cartList.get(i).getBosspicture());
            if (!groups.contains(storeInfo)){
                groups.add(storeInfo);
            }
        }
        //紧接着再填充GoodsInfo
        for (int i=0; i<groups.size(); i++) {
            StoreInfo storeInfo = groups.get(i);
            List<GoodsInfo> goodsInfoList = new ArrayList<>();
            for (int j=0; j<cartList.size(); j++) {
                if ((cartList.get(j).getBossid()) == (storeInfo.getId())){
                    goodsInfoList.add(
                            new GoodsInfo(cartList.get(j).getBookid(), cartList.get(j).getBookname(), cartList.get(j).getPrice(),
                                    cartList.get(j).getPrice() + new Random().nextInt(1500)/100, ""+1555 + new Random().nextInt(3000), cartList.get(j).getBookpicture(), cartList.get(j).getNumber(),0));
                }
                childs.put(storeInfo.getId(), goodsInfoList);
            }
        }

        shopCart.setGroups(groups);
        shopCart.setChilds(childs);

        return shopCart;
    }

    @RequestMapping("/insertShopCart")
    public int insertShopCart(@RequestParam(name = "phone", defaultValue = "0")String phone,
                              @RequestParam(name = "bookid", defaultValue = "0")int bookid,
                              @RequestParam(name = "bossid", defaultValue = "0")int bossid,
                              @RequestParam(name = "num", defaultValue = "1") int num) {
        if (!CheckInputUtils.checkTel(phone) || bookid == 0 || bossid == 0)
            return 0;

        //添加一条数据到数据库
        if (addRrecord(phone, bookid, bossid, num))
            return 1;

        return 0;
    }

    /**
     * 添加一条数据到数据库，如果原来有，则进行Number上的累加并更新
     */
    public boolean addRrecord(String phone, int bookid, int bossid, int num) {

        try {
            Book book = bookRepository.findById(bookid);
            BookBoss bookBoss = bookBossRepository.findByBookidAndBossid(bookid, bossid);
            BookBossPicture bookBossPicture = bookBossPictureRepository.findTopByBookidAndBossid(bookid, bossid);
            Boss boss = bossRepository.findById(bossid);

            //先判断服务器里是不是有且仅有这么一条记录
            List<Cart> cartList = cartRepository.findAllByPhoneAndBookidAndBossid(phone, bookid, bossid);
            if (cartList != null & cartList.size() == 1){
                cartList.get(0).setNumber(cartList.get(0).getNumber() + num);
                cartList.get(0).setBossname(boss.getBossName());
                cartList.get(0).setBosspicture(boss.getBossPicture());
                cartList.get(0).setPrice(bookBoss.getPrice());
                cartList.get(0).setBookname(book.getBookname());
                cartList.get(0).setBookpicture(bookBossPicture.getPictureurl());
                cartRepository.save(cartList.get(0));
            } else {
                //否则则选择插入
                Cart cart = new Cart();
                cart.setBookid(bookid);
                cart.setBossid(bossid);
                cart.setPhone(phone);



                cart.setNumber(1);
                cart.setBossname(boss.getBossName());
                cart.setBosspicture(boss.getBossPicture());
                cart.setPrice(bookBoss.getPrice());
                cart.setBookname(book.getBookname());
                cart.setBookpicture(bookBossPicture.getPictureurl());
                cartRepository.save(cart);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
