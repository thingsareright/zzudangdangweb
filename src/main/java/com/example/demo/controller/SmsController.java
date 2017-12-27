package com.example.demo.controller;

import com.example.demo.dao.UserTokenRepository;
import com.example.demo.dao.entity.UserToken;
import com.example.demo.util.CheckInputUtils;
import com.example.demo.util.SmsUtil;
import com.example.demo.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类主要用来处理短信验证码的发送和验证
 * 因为引入了腾讯云的短信验证码第三方库
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    UserTokenRepository userTokenRepository;


    /**
     * 用户向某个手机号发送短信验证码
     * @param phone  传过来的手机号
     * @return   返回0表示短信验证码发送失败，返回1表示短信验证码发送成功
     */
    @RequestMapping("/getToken")
    public int getToken(@RequestParam(name = "phone",defaultValue = "0") String phone) {
        //首先检查手机号是否符合我们的要求
        if (!CheckInputUtils.checkTel(phone)) {
            //如果手机号不满足要求，则返回0
            return 0;
        }

        //首先生成一个由四位数字组成的随机字符串
        String code = StrUtil.getRandomCode();
        try {
            String result = SmsUtil.sendgetCode(phone, code, "5");
            UserToken userToken = new UserToken(phone, code);
            userTokenRepository.save(userToken);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 这个映射路径用来查看用户返回的验证码是否与服务器数据库的验证码相同
     * @param phone   手机号码
     * @param usercode   用户请求参数里的验证码
     * @return
     */
    @RequestMapping("/verifyTokenCode")
    public int verifyTokenCode(@RequestParam(name = "phone", defaultValue = "0") String phone,
                                @RequestParam(name = "usercode", defaultValue = "0") String usercode) {
        //首先检查手机号是否符合我们的要求
        if (!CheckInputUtils.checkTel(phone)) {
            //如果手机号不满足要求，则返回0
            return 0;
        }

        String serverCode = userTokenRepository.findTopByPhone(phone).getToken();  //数据库里该手机号对应的验证码
        if (serverCode == null)
            return 0;

        if (serverCode.equals(usercode))
            return 1;

        return 0;
    }


}
