package com.qunxianghui.gxh.bean.generalize;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class EmployeePaiHangBean implements Serializable {

    private int code;
    private String message;
    private List<DataBean> data;

    public static EmployeePaiHangBean objectFromData(String str) {

        return new Gson().fromJson(str, EmployeePaiHangBean.class);
    }

    public static List<EmployeePaiHangBean> arrayEmployeePaiHangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<EmployeePaiHangBean>>() {
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
         * id : 978
         * member_id : 1000121
         * company_id : 1000122
         * activecode : 50057126001100098
         * staff_id : 1000012
         * ctime : 1525867180
         * status : 1
         * expires_in : 1746720000
         * remark : 朱总
         * active_time : 1525948017
         * member_name : 无邪
         * member_avatar :
         * cnt : 3829
         */

        private int id;
        private int member_id;
        private int company_id;
        private String activecode;
        private int staff_id;
        private int ctime;
        private int status;
        private int expires_in;
        private String remark;
        private int active_time;
        private String member_name;
        private String member_avatar;
        private String cnt;

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

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getActivecode() {
            return activecode;
        }

        public void setActivecode(String activecode) {
            this.activecode = activecode;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getActive_time() {
            return active_time;
        }

        public void setActive_time(int active_time) {
            this.active_time = active_time;
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

        public String getCnt() {
            return cnt;
        }

        public void setCnt(String cnt) {
            this.cnt = cnt;
        }
    }
}
