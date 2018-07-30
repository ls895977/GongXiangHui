package com.qunxianghui.gxh.bean.generalize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneraPersonStaticBean implements Serializable {

    private int code;
    private String message;
    private List<DataBean> data;

    public static GeneraPersonStaticBean objectFromData(String str) {

        return new Gson().fromJson(str, GeneraPersonStaticBean.class);
    }

    public static List<GeneraPersonStaticBean> arrayGeneraPersonStaticBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GeneraPersonStaticBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        public int id;
        public int uuid;
        public int data_uuid;
        public int member_id;
        public int like_cnt;
        public int comment_cnt;
        public String ctime;
        public String ip;
        public int status;
        public String click_cnt;
        public String view_cnt;
        public String forward_cnt;
        public String share_cnt;
        public String swiper1;
        public String swiper2;
        public String swiper3;
        public String swiper4;
        public int channel_id;
        public String title;
        public String picurl;
        public String linkurl;
        public String description;
        public String content;
        public String source;
        public String tags;
        public String url;
        public String video_url;
        public List<String> images;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }
    }
}
