package com.abin.lee.spring.boot2.elasticsearch.common.base;

import com.abin.lee.spring.boot2.elasticsearch.common.util.GsonUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by abin on 2019/7/13.
 */
public class BaseExtends {

    public static String success(){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 0);
        response.put("errmsg", "");
        response.put("data", new Object());
        return GsonUtil.toJson(response);
    }

    public static String success(String msg){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 0);
        response.put("errmsg", msg);
        response.put("data", new Object());
        return GsonUtil.toJson(response);
    }

    public static String success(String msg, Object data){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 0);
        response.put("errmsg", msg);
        response.put("data", data);
        return GsonUtil.toJson(response);
    }


    public static String success(Object data){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 0);
        response.put("errmsg", "");
        response.put("data", data);
        return GsonUtil.toJson(response);
    }

    public static String error(){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 1);
        response.put("errmsg", "");
        response.put("data", new Object());
        return GsonUtil.toJson(response);
    }

    public static String error(String msg){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 1);
        response.put("errmsg", msg);
        response.put("data", new Object());
        return GsonUtil.toJson(response);
    }

    public static String error(String msg, Object data){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 1);
        response.put("errmsg", msg);
        response.put("data", data);
        return GsonUtil.toJson(response);
    }

    public static String error(Object data){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", 1);
        response.put("errmsg", "");
        response.put("data", data);
        return GsonUtil.toJson(response);
    }


    public static String result(Integer errno, String msg, Object data){
        Map<String, Object> response = Maps.newHashMap();
        response.put("errno", errno);
        response.put("errmsg", msg);
        response.put("data", data);
        return GsonUtil.toJson(response);
    }






}
