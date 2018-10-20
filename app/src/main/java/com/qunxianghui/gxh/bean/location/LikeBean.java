package com.qunxianghui.gxh.bean.location;

public class LikeBean {

    public int code;
    public String message;
    public DataBean data;

    public static class DataBean {
        public String uuid;
        public String like_one_id;
        public LikeDataBean like_one_res;

        public String like_num;
        public String unlike;

        public static class LikeDataBean {
            public String member_name;
            public String unlike;
        }
    }


}
