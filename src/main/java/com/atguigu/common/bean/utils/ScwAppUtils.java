package com.atguigu.common.bean.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Asce
 * @create 2020-02-10 15:43
 */
public class ScwAppUtils {
    //2.将java对象专为json字符串存到redis中的方法
    public static <T> void saveObj2Redis(T t, StringRedisTemplate stringRedisTemplate, String token) {
        //将对象转为json字符串
        String jsonString = JSON.toJSONString(t);
        //stringRedisTemplate
        stringRedisTemplate.opsForValue().set(token, jsonString);
    }

    //3.将redis中的json字符串读取java代码中专为java对象
    public static <T> T getJsonStr2Obj(Class<T> type, StringRedisTemplate stringRedisTemplate, String token) {
        //获取redis中指定键对应的json字符串
        String jsonStr = stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        //将json转为java对象
        T t = JSON.parseObject(jsonStr, type);
        return t;
    }

    //1.正则验证手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2 = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";// 验证手机号
        if (StringUtils.isNotBlank(str)) {
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

}
