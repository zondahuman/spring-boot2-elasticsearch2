package com.abin.lee.spring.boot2.elasticsearch.common.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by abin on 2019/7/13.
 */
public class GsonUtil {
    private static Gson gson = null ;
    static {
        gson = new Gson();
    }

    public static String toJson(Object obj){
        String result = gson.toJson(obj);
        return result;
    }

    public static Object toJson(String json, Type objectType){
        Object result = gson.fromJson(json, objectType);
        return result;
    }



}
