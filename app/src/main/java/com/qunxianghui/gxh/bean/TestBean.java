package com.qunxianghui.gxh.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestBean {

    /**
     * code : 1
     * data : 33
     */

    private int code;
    private Object data;

    public static TestBean objectFromData(String str) {

        return new Gson().fromJson(str, TestBean.class);
    }

    public static List<TestBean> arrayTestBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TestBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
