package com.qunxianghui.gxh.bean.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProvinceBean implements Serializable {


    /**
     * code : 0
     * message : 获取省份成功
     * data : [{"provinceid":1,"provincename":"直辖市","displayorder":1},{"provinceid":2,"provincename":"广东","displayorder":0},{"provinceid":3,"provincename":"浙江","displayorder":0},{"provinceid":4,"provincename":"安徽","displayorder":0},{"provinceid":5,"provincename":"福建","displayorder":0},{"provinceid":6,"provincename":"甘肃","displayorder":0},{"provinceid":7,"provincename":"广西","displayorder":0},{"provinceid":8,"provincename":"贵州","displayorder":0},{"provinceid":9,"provincename":"海南","displayorder":0},{"provinceid":10,"provincename":"河北","displayorder":0},{"provinceid":11,"provincename":"河南","displayorder":0},{"provinceid":12,"provincename":"黑龙江","displayorder":0},{"provinceid":13,"provincename":"湖北","displayorder":0},{"provinceid":14,"provincename":"湖南","displayorder":0},{"provinceid":15,"provincename":"吉林","displayorder":0},{"provinceid":16,"provincename":"江苏","displayorder":0},{"provinceid":17,"provincename":"江西","displayorder":0},{"provinceid":18,"provincename":"辽宁","displayorder":0},{"provinceid":19,"provincename":"内蒙古","displayorder":0},{"provinceid":20,"provincename":"宁夏","displayorder":0},{"provinceid":21,"provincename":"青海","displayorder":0},{"provinceid":22,"provincename":"山东","displayorder":0},{"provinceid":23,"provincename":"山西","displayorder":0},{"provinceid":24,"provincename":"陕西","displayorder":0},{"provinceid":25,"provincename":"四川","displayorder":0},{"provinceid":26,"provincename":"西藏","displayorder":0},{"provinceid":28,"provincename":"云南","displayorder":0},{"provinceid":35,"provincename":"新疆","displayorder":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static ProvinceBean objectFromData(String str) {

        return new Gson().fromJson(str, ProvinceBean.class);
    }

    public static List<ProvinceBean> arrayProvinceBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ProvinceBean>>() {
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
        /**
         * provinceid : 1
         * provincename : 直辖市
         * displayorder : 1
         */

        private int provinceid;
        private String provincename;
        private int displayorder;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(int provinceid) {
            this.provinceid = provinceid;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public int getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(int displayorder) {
            this.displayorder = displayorder;
        }
    }
}
