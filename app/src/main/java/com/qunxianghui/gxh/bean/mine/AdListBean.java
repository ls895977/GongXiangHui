package com.qunxianghui.gxh.bean.mine;

/**
 * Created by user on 2018/6/11.
 */

import java.util.List;

public class AdListBean {

    public int code;
    public String message;
    public List<DataBean> data;

    public static class DataBean {

        public int id;
        public String title;
        public int member_id;
        public int ad_type;
        public int position;
        public String link;
        public String images;
        public int status;
        public SettingsBean settings;
        public int ctime;
        public int is_slide;

        public static class SettingsBean {

            public String name;
            public String mobile;
            public String address;

            public String intro;

            public String nick;
            public String qq;

        }
    }
}
