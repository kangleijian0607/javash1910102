package com.atguigu.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Asce
 * @create 2020-02-10 14:55
 * 响应类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppResponse<T> {
    //响应状态码:响应成功还是失败
    private String code;
    //响应描述
    private String message;
    //响应数据
    private T data;

    //业务bean 成功和失败的响应
    public static <T> AppResponse<T> ok(T data){
        return new AppResponse<T>("00000","success",data);
    }

    public static <T> AppResponse<T> fail(String code,String message){
        return new AppResponse<T>(code,message,null);
    }
}
