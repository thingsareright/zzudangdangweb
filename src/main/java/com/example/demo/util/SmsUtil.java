package com.example.demo.util;

import com.github.qcloudsms.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/24 0024.
 * 这个类用于进一步封装腾讯云的接口，注意我的模板的参数都是只有两个参数
 */
public class SmsUtil {

    //下面是一些必要的变量设置，这是机密，不能外传
    private static final int APPID =1400045100;
    private static final String APPKEY = "138423efdb3e0dcb8669e0d1c6abc493";
    private static final int SENDCODE =49912;       //腾讯云验证码模板


    /**
     * 封装发送验证码短信的方法，供SmsController调用 ，调用成功返回字符串"1"，否则返回字符串"0"
     * 第二个参数是验证码，第三个参数是时限 TODO 服务器控制时限的功能还没实现
     * @param tel
     * @param param1
     * @param param2
     * @return
     */
    public static String sendgetCode(String tel, String param1, String param2){
        if (!CheckInputUtils.checkTel(tel)){
            //如果手机号不符合国家标准，那么就不发信息了呗
            return "0";
        }
        SmsSingleSender sender = null;
        try {
            sender = new SmsSingleSender(APPID,APPKEY);
            ArrayList<String> params = new ArrayList<String>();
            params.add(param1);
            params.add(param2);
            SmsSingleSenderResult   result = sender.sendWithParam("86", tel, SENDCODE, params,"","","");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "0";
    }



}
