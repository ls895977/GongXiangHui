package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineMessageSystemBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":3,"go_id":"0","content":"哈哈哈哈哈","ctime":"43天前","read":0},{"id":5,"go_id":"0","content":"所有人","ctime":"41天前","read":0}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineMessageSystemBean objectFromData(String str) {

        return new Gson().fromJson(str, MineMessageSystemBean.class);
    }

    public static List<MineMessageSystemBean> arrayMineMessageSystemBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineMessageSystemBean>>() {
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
         * id : 3
         * go_id : 0
         * content : 哈哈哈哈哈
         * ctime : 43天前
         * read : 0
         */

        private int id;
        private String go_id;
        private String content;
        private String ctime;
        private int read;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGo_id() {
            return go_id;
        }

        public void setGo_id(String go_id) {
            this.go_id = go_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }
    }
}
