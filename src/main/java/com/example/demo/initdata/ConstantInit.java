package com.example.demo.initdata;

import com.example.demo.dao.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 这个类用来存储一些初始化信息
 */
public class ConstantInit {

    //初始化书名时用的数组
    public static List<String> bookNameList = new ArrayList<>();
    //初始化作者时用的数组
    public static List<String> writterList = new ArrayList<>();
    //初始化出版社时用的数组
    public static List<String> pressList = new ArrayList<>();
    //一组图片链接
    public static List<String> bookPictureList = new ArrayList<>();
    //一组ISBN
    public static List<String> ISBNList = new ArrayList<>();
    //商家
    public static List<String> bossNameList = new ArrayList<>();

    static {
        bossNameList.add("华章科技");
        bossNameList.add("文轩网旗舰店");
        bossNameList.add("当当自营");
        bossNameList.add("笔墨");
        bossNameList.add("熊猫君旗舰店");

        bossNameList.add("布克旗舰店");
        bossNameList.add("万一点");
        bossNameList.add("拐角店");
        bossNameList.add("人生如梦主题书店");
        bossNameList.add("海洋书店");

        bossNameList.add("我的一生书店");
        bossNameList.add("时光书店");
        bossNameList.add("喜宝专卖店");
        bossNameList.add("日漫踪迹旗舰店");
        bossNameList.add("呀呀呀");
    }

    static {
        bookNameList.add("《高性能MySQL》");
        bookNameList.add("《深入浅出MySQL》");
        bookNameList.add("《MySQL数据库从入门到精通》");
        bookNameList.add("《MySQL必知必会》");
        bookNameList.add("《MySQL运维内参》");

        bookNameList.add("《MySQL技术内幕》");
        bookNameList.add("《php和MySQL》");
        bookNameList.add("《MySQL DBA修炼之道》");
        bookNameList.add("《MySQL管理之道》");
        bookNameList.add("《MySQL入门很简单》");
    }

    static {
        writterList.add("施瓦茨");
        writterList.add("扎伊采夫");
        writterList.add("唐汉明");
        writterList.add("王飞飞");
        writterList.add("福塔");

        writterList.add("周彦伟");
        writterList.add("江成耀");
        writterList.add("Robin ");
        writterList.add("Nixon查理斯");
        writterList.add("贝尔");

        writterList.add("威利");
        writterList.add("汤姆森");
        writterList.add("陈效勇");
        writterList.add("何春");
        writterList.add("李坤");
    }

    static {
        pressList.add("商务印书馆");
        pressList.add("中国铁道出版社");
        pressList.add("中国工业出版社");
        pressList.add("清华大学出版社");
        pressList.add("燕京大学出版社");

        pressList.add("图灵出版社");
        pressList.add("同济大学出版社");
        pressList.add("郑州大学出版社");
        pressList.add("中国电力出版社");
        pressList.add("滨海出版社");
    }

    static {
        bookPictureList.add("http://img3m0.ddimg.cn/80/13/23214590-1_b_6.jpg");
        bookPictureList.add("http://img3m7.ddimg.cn/72/10/23399217-1_b_0.jpg");
        bookPictureList.add("http://img3m1.ddimg.cn/95/21/23450621-1_b_0.jpg");
        bookPictureList.add("http://img3m4.ddimg.cn/35/29/20426804-1_b_0.jpg");
        bookPictureList.add("http://img3m3.ddimg.cn/64/30/25077853-1_b_3.jpg");

        bookPictureList.add("http://img3m5.ddimg.cn/5/29/23831285-1_b_2.jpg");
        bookPictureList.add("http://img3m9.ddimg.cn/93/16/23255589-1_b_1.jpg");
        bookPictureList.add("http://img3m4.ddimg.cn/52/13/23779654-1_b_1.jpg");
        bookPictureList.add("http://img3m0.ddimg.cn/5/5/24194120-1_b_3.jpg");
        bookPictureList.add("http://img3m9.ddimg.cn/79/15/24032329-1_b_6.jpg");

        bookPictureList.add("http://img3m5.ddimg.cn/5/29/23831285-1_b_2.jpg");
        bookPictureList.add("http://img3m9.ddimg.cn/93/16/23255589-1_b_1.jpg");
        bookPictureList.add("http://img3m4.ddimg.cn/52/13/23779654-1_b_1.jpg");
        bookPictureList.add("http://img3m0.ddimg.cn/5/5/24194120-1_b_3.jpg");
        bookPictureList.add("http://img3m9.ddimg.cn/79/15/24032329-1_b_6.jpg");

    }

    static {
        ISBNList.add("1235647894512");
        ISBNList.add("HDKJASHDF4512");
        ISBNList.add("HFKJAIHJNKDJI");
        ISBNList.add("IOTORIJHKLNFK");
        ISBNList.add("YORUWEIODLKJF");

        ISBNList.add("456FHJKD6SDF6");
        ISBNList.add("GFDGHDJKLLKGO");
        ISBNList.add("7849HKJR8EJKK");
        ISBNList.add("JWEJKRJKLKR89");
        ISBNList.add("894923HJKFNSN");
    }



    public static String getOneBookName() {
        Random random = new Random();
        int s = random.nextInt(1000)%10;
        return bookNameList.get(s);
    }


    public static String getOneWritter() {
        Random random = new Random();
        int s = random.nextInt(1000)%15;
        return writterList.get(s);
    }

    public static String getOnePress() {
        Random random = new Random();
        int s = random.nextInt(1000)%10;
        return pressList.get(s);
    }

    public static String getOnePictureUrl() {
        Random random = new Random();
        int s = random.nextInt(1000)%10;
        return bookPictureList.get(s);
    }

    public static String getOneBossName() {
        Random random = new Random();
        int s = random.nextInt(1000)%15;
        return bossNameList.get(s);
    }

    public static String getOneISBN() {
        Random random = new Random();
        int s = random.nextInt(1000)%10;
        return ISBNList.get(s);
    }

    public static int getOneFormat() {
        Random random = new Random();
        int s = random.nextInt(2) + 15;
        return s;
    }

    public static int getOnePaper() {
        return 0;
    }

    public static Book getOneBook(){
        Random random = new Random();
        int s = random.nextInt(1000)%10;

        Book book = new Book();

        book.setBookname(getOneBookName());
        book.setPress(getOnePress());
        book.setVersion(random.nextInt(5) + 1);
        book.setISBN(getOneISBN());
        book.setFormat(getOneFormat());
        book.setPaper(ConstantInit.getOnePaper());
        book.setBookpackage(ConstantInit.getOneBookPackage());
        book.setSellernumber(ConstantInit.getOneSellerumber());
        book.setPresstime(ConstantInit.getOnePressTime());
        book.setScore(s);
        return book;
    }

    public static int getOneBookPackage() {
        Random random = new Random();
        int s = random.nextInt(2) + 0;
        return s;
    }

    public static int getOneSellerumber() {
        Random random = new Random();
        int s = random.nextInt(200);
        return s;
    }

    public static int getOnePressTime() {
        Random random = new Random();
        int s = random.nextInt(20160215);
        return s;
    }

    public static float getOneScore() {
        Random random = new Random();
        float s = random.nextFloat()*100;
        return s;
    }
}
