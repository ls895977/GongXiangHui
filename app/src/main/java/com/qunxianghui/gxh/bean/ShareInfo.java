package com.qunxianghui.gxh.bean;

public class ShareInfo {

    public int code;
    public String message;
    public ShareInfoBean data;

    public static class ShareInfoBean{
        public String uuid;
        public String title;
        public String imgUrl;
        public String url;
        public String desc;
        public String data_uuid;

    }
}
