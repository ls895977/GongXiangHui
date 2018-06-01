package com.qunxianghui.gxh.bean.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WelcomAdverBean {

    /**
     * code : 0
     * message :
     * data : {"image":"http://api.qunxianghui.com.cn/upload/sys/image/c0/4648f9e18b88fa997b5beb93cb9cb8.jpg"}
     */

    private int code;
    private String message;
    private DataBean data;

    public static WelcomAdverBean objectFromData(String str) {

        return new Gson().fromJson(str, WelcomAdverBean.class);
    }

    public static List<WelcomAdverBean> arrayWelcomAdverBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<WelcomAdverBean>>() {
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * image : http://api.qunxianghui.com.cn/upload/sys/image/c0/4648f9e18b88fa997b5beb93cb9cb8.jpg
         */

        private String image;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
