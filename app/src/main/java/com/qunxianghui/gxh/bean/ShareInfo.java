package com.qunxianghui.gxh.bean;

public class ShareInfo {

    public int code;
    public String msg;
    public ShareInfoBean data;

    public static class ShareInfoBean{
        public String uuid;
        public String title;
        public String imgUrl;
        public String url;
        public String desc;

    }
}
