package com.example.demo.initdata;

import com.example.demo.dao.*;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 这个类主要是用来初始化后台数据
 */
@RestController
@RequestMapping("/book")
public class InitDataController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookBossRepository bookBossRepository;

    @Autowired
    BookBossPictureRepository bookBossPictureRepository;

    @Autowired
    BossRepository bossRepository;

    /**
     * 这个是初始化数据用的，用之前要先建好数据库good，成功则返回1，否则返回0
     * @return
     */
    @RequestMapping("/initData")
    public int initData() {

        Book book = new Book();
        //书籍就作者和出版社变了就行
        for (int i=0; i<=9; i++){
            for (int j=0; j<=14; j++) {
                for (int k=0; k<=9; k++){
                    book = ConstantInit.getOneBook();
                    book.setWritter(ConstantInit.writterList.get(j));
                    book.setPress(ConstantInit.pressList.get(k));
                    bookRepository.save(book);
                }
            }
        }

        int bossid=1;
        //填充bookboss表
        for (int i=1; i<=1500; i++) {
            BookBoss bookBoss = new BookBoss();
            BookBossPicture bookBossPicture = new BookBossPicture();
            if (bossid > 15)
                bossid=1;

            bookBoss.setBookid(i);
            bookBoss.setBossid(bossid);
            Random random = new Random();
            bookBoss.setPrice(random.nextFloat()*100);
            bookBoss.setSellnumber(random.nextInt(30));
            bookBossRepository.save(bookBoss);
            bookBossPicture.setBookid(i);
            bookBossPicture.setBossid(bossid);
            for (int k=0; k<=4; k++) {
                bookBossPicture.setPictureurl(ConstantInit.getOnePictureUrl());
                bookBossPictureRepository.save(bookBossPicture);
            }
            bossid++;
        }


        //填充boss表

        int i=1;
        List<Boss> bosses = new ArrayList<>();
        while (i<=15){
            Boss boss = new Boss();
            boss.setId(i);
            boss.setBossName(ConstantInit.bossNameList.get(i-1));
            boss.setBossPicture(ConstantInit.bookPictureList.get(i-1));
            bosses.add(boss);
            bossRepository.save(bosses);
            i++;
        }


        return 1;
    }

}