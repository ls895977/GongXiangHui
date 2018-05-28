package com.qunxianghui.gxh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 作者：李标
 * 日期  2017/12/15 11:30
 * 邮箱：Lb_android@163.com
 * 模块：Gson 实现类
 * 描述: 1. 2018年2月26日09:24:03 添加备注
 */

public class GsonUtils {
    /**
     * 通用解析json的方法
     *
     * @param jsonResult json数据
     * @param cls        实体对象
     * @param <T>        泛型
     * @return 返回的实体对象
     */
    private static Gson gson = null, gsonBuilder = null;

    public static <T> T jsonFromJson(String jsonResult, Class<T> cls) {
        return getNewGson().fromJson(jsonResult, cls);//第一个参数是:json数据,第二个参数是:实体类
    }

    /**
     * 解析集合类型json的方法
     *
     * @param jsonResult json数据
     * @param tp         new TypeToken<List<T>>(){}.getType()
     * @param <T>        返回的集合对象
     * @return
     */
    public static <T> T jsonTypeTokenFromJson(String jsonResult, Type tp) {
        return getNewGson().fromJson(jsonResult, tp);//第一个参数是:json数据,第二个参数是:实体类
    }

    /***
     * 通用实体类转换成json格式的方法
     * @param cls 转换的对象
     * @param <T>
     * @return
     */
    public static <T> String jsonToJson(Class<T> cls) {
        return getNewGson().toJson(cls);   //参数是:实体类
    }

    public static String simpleMapToJsonStr(LinkedHashMap<String, String> params) {
        return getNewGsonBuilder().toJson(params);
    }

    public static Gson getNewGson() {
        return gson == null ? gson = new Gson() : gson;
    }

    /***
     *serializeNulls()是GsonBuilder提供的一种配置，当字段值为空或null时，依然对该字段进行转换
     * @return
     */
    private static Gson getNewGsonBuilder() {
        return gsonBuilder == null ? gsonBuilder = new GsonBuilder().enableComplexMapKeySerialization().create() : gsonBuilder;
    }
}
