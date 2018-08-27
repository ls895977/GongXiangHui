package com.qunxianghui.gxh.bean.generalize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneralizeCompanyStaticsBean implements Serializable {

    private int code;
    private String message;
    private DataBean data;

    public static GeneralizeCompanyStaticsBean objectFromData(String str) {

        return new Gson().fromJson(str, GeneralizeCompanyStaticsBean.class);
    }

    public static List<GeneralizeCompanyStaticsBean> arrayGeneralizeCompanyStaticsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GeneralizeCompanyStaticsBean>>() {
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
         * click_cnt : 909
         * view_cnt : 13319
         * forward_cnt : 58
         * share_cnt : 58
         * article_cnt : 717
         * click_rate : 6.82%
         * forward_rate : 0.44%
         * staff_cnt : 57
         * ad_prize : 181.80
         */

        public String click_cnt;
        public String view_cnt;
        public String forward_cnt;
        public String share_cnt;
        public String article_cnt;
        public String click_rate;
        public String forward_rate;
        public String staff_cnt;
        public String ad_prize;
        public String company_name;

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

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
