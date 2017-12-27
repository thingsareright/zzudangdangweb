package com.example.demo.util;

/**
 * Created by Administrator on 2017/10/25 0025.
 * 字符串工具类
 */
public class StrUtil {

    /**
     * 用于生成四位随机验证码
     * @return
     */
    public static String getRandomCode()
    {
        //定义一个空字符串
        StringBuilder result = new StringBuilder();

        for(int i=0;i<4; ++i)
        {
            //生成一个0到9之间的int类型整数
            int intVal = (int)(Math.random()*9);
            //强制转换（char）intVal 将对应的数值转换为对应的字符
            //将字符进行拼接
            result = result.append(intVal);
        }
        return result.toString();
    }

}
