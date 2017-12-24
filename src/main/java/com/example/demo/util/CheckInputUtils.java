package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/19 0019.
 * 这个类主要用于做一些输入检查
 */

public class CheckInputUtils {
    /**
     * 正则表达
     * 手机号码由11位数字组成，
     * 匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     *
     * @return
     */
    public static Boolean checkTel(String tel){

        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(tel);
        return m.matches();


    }

    //检查密码是否合法：非空，[6,16]位数字，字母，下划线组成。符合格式返回true，否则返回false。
    public static Boolean checkPassword(String password){
        byte[] passByte= password.getBytes();
        if (passByte.length < 6 || passByte.length > 16)
            return false;
        for (byte oneByte : passByte) {
            //如果是合法字符，那么flag为真，这些范围值都是ASCII码值
            Boolean flag = oneByte >= 0x30 || oneByte <= 0x39 || oneByte >= 0x61
                    || oneByte <= 0x7a || oneByte >= 0x41 || oneByte <= 0x5a
                    || oneByte == 95;
            if (!flag)
                return false;
        }
        return true;
    }

    //判断普通输入内容是否合法：非空，无SQL注入。符合格式返回true，否则返回false。
    public static Boolean checkCommonInput(String input){
        //TODO
        return null;
    }
}
