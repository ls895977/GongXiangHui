package com.qunxianghui.gxh.bean;

public class CityInfo {

    public int code;
    public String message;
    public CityInfoBean data;

    public static class CityInfoBean{

        public CityInfoInner cityInfo;

        public static class CityInfoInner{

            public String areaid;
            public String areaname;
            public String cityid;
            public String displayorder;
            public String id;
            public String city;
            public String pinyin;

        }
    }

}
