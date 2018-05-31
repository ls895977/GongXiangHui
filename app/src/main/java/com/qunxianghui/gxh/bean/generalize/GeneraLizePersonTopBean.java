package com.qunxianghui.gxh.bean.generalize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneraLizePersonTopBean {

    /**
     * code : 0
     * message : 查询成功
     * data : {"click_cnt":"0","view_cnt":"98","forward_cnt":"2","share_cnt":"2","click_rate":"0.00%","ad_prize":"0.00"}
     */

    private int code;
    private String message;
    private DataBean data;

    public static GeneraLizePersonTopBean objectFromData(String str) {

        return new Gson().fromJson(str, GeneraLizePersonTopBean.class);
    }

    public static List<GeneraLizePersonTopBean> arrayGeneraLizePersonTopBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GeneraLizePersonTopBean>>() {
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
         * click_cnt : 0
         * view_cnt : 98
         * forward_cnt : 2
         * share_cnt : 2
         * click_rate : 0.00%
         * ad_prize : 0.00
         */

        private String click_cnt;
        private String view_cnt;
        private String forward_cnt;
        private String share_cnt;
        private String click_rate;
        private String ad_prize;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getClick_cnt() {
            return click_cnt;
        }

        public void setClick_cnt(String click_cnt) {
            this.click_cnt = click_cnt;
        }

        public String getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(String view_cnt) {
            this.view_cnt = view_cnt;
        }

        public String getForward_cnt() {
            return forward_cnt;
        }

        public void setForward_cnt(String forward_cnt) {
            this.forward_cnt = forward_cnt;
        }

        public String getShare_cnt() {
            return share_cnt;
        }

        public void setShare_cnt(String share_cnt) {
            this.share_cnt = share_cnt;
        }

        public String getClick_rate() {
            return click_rate;
        }

        public void setClick_rate(String click_rate) {
            this.click_rate = click_rate;
        }

        public String getAd_prize() {
            return ad_prize;
        }

        public void setAd_prize(String ad_prize) {
            this.ad_prize = ad_prize;
        }
    }
}
