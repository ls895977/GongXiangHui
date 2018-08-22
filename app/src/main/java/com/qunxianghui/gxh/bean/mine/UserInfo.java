package com.qunxianghui.gxh.bean.mine;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo {

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean implements Serializable {

        public int id;
        public int level_id;
        public String nick;
        public String username;
        public String mobile;
        public String email;
        public String money;
        public String frozen_money;
        public String income;
        public String expend;
        public String exper;
        public int integral;
        public int frozen_integral;
        public int sex;
        public String avatar;
        public String last_login_ip;
        public int last_login_time;
        public int login_count;
        public String expire_time;
        public int status;
        public int ctime;
        public int level_2;
        public int level_3;
        public int level_4;
        public int level_5;
        public String remark;
        public int isactive;
        public int active_time;
        public int agency_id;
        public String company_name;
        public String company_intro;
        public String company_trade;
        public String duty;
        public String region_name;
        public String address;
        public String self_introduction;
        public String channel_ids;
        public String posts_ids;
        public String video_ids;
        public int province_id;
        public int city_id;
        public int area_id;
        public int last_captcha;
        public String serarch_key_word;
        public String device_id;
        public String active_device_id;
        public LevelInfoBean level_info;
        public String collect_cnt;
        public String follow_cnt;
        public String be_follow_cnt;
        public String comment_cnt;
        public String company_id;
        public CompanyInfoBean company_info;
        public AgencyInfoBean agency_info;
        public String staff;

        public static class LevelInfoBean implements Serializable {
            public int id;
            public String name;
            public String intro;
            @SerializedName("default")
            public String defaultX;
            public String expire;
            public int status;
            public int ctime;
            public int mtime;
        }

        public static class CompanyInfoBean implements Serializable {
            public int id;
            public int member_id;
            public String company_name;
            public String description;
            public String company_trade;
            public String tel;
            public String mobile;
            public String qq;
            public int province_id;
            public int city_id;
            public int area_id;
            public String address;
            public String images;
            public String linkname;
            public String content;
            public int ctime;
            public String view_cnt;
            public int status;
            public String push_id;
            public String change_ad_id;
        }

        public static class AgencyInfoBean implements Serializable {
            public String id;
            public String username;
            public int agency_id;
            public String mobile;
        }
    }
}
