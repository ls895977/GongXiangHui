package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineFansBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":161,"member_id":1000027,"be_member_id":1000175,"member_name":"盖世嘤雄","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180523/daf49ea727aece0771a1a5b71725a6f6.png","member_remark":""}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineFansBean objectFromData(String str) {

        return new Gson().fromJson(str, MineFansBean.class);
    }

    public static List<MineFansBean> arrayMineFansBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineFansBean>>() {
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
         * id : 161
         * member_id : 1000027
         * be_member_id : 1000175
         * member_name : 盖世嘤雄
         * member_avatar : http://api.qunxianghui.com.cn/upload/images/20180523/daf49ea727aece0771a1a5b71725a6f6.png
         * member_remark :
         */

        private int id;
        private int member_id;
        private int be_member_id;
        private String member_name;
        private String member_avatar;
        private String member_remark;

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

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getBe_member_id() {
            return be_member_id;
        }

        public void setBe_member_id(int be_member_id) {
            this.be_member_id = be_member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getMember_remark() {
            return member_remark;
        }

        public void setMember_remark(String member_remark) {
            this.member_remark = member_remark;
        }
    }
}
