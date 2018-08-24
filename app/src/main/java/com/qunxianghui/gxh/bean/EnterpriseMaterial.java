package com.qunxianghui.gxh.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EnterpriseMaterial {

    public int code;
    public String msg;
    public EnterpriseMaterialBean data;

    public static class EnterpriseMaterialBean {
        public List<CompanyAdvert> companyAdList;

        public static class CompanyAdvert implements Serializable {
            public int id;
            public String title;
            //用户企业id
            public int member_id;
            //广告类型：1大图通栏 2名片广告 3通栏广告 4二维码 5QQ广告 6贴片广告 7店铺广告 8图文广告
            public int ad_type;
//            public int showTime;
            public int linksEnterprise;
            //  广告位置：1顶部广告，2底部广告，3互推广告
            public int position;
            //operate:通栏操作（1-跳转链接 2-拨打电话 3-联系QQ 4-展示海报 5-展示二维码））
            public int operate;
            public String operate_value;

            public String link;
            public String images;
//            0-禁用，1-正常
            public int status = 1;
            public Settings settings = new Settings();
            public String ctime;
            public String mtime;
//            是否轮播：0不轮播，1轮播
            public int is_slide;
            public int is_float;
            public boolean isSelect;

            public static class Settings implements Serializable {
                //通栏操作（1-跳转链接 2-拨打电话 3-联系QQ 4-展示海报 5-展示二维码
                @SerializedName("linktype")
                public int operate = 1;
                //链接地址
                public String link;
                //手机号码
                public String mobile;
                //qq
                public String qq;
                //链接至企业（0-不链接 1-链接）
                public int is_link;
                //广告标语
                public String slogan;
                //广告介绍
                public String intro;
                //海报或二维码图片路径
                public String pgn_url;
                //产品名称
                public String product_name;
                //产品价格
                public String product_price;
                //商品链接
                public String product_url;
                //店铺名称
                public String store_name;
                //店铺图片路径
                public String store_url;
                //跳转时间(3,4,5)
                public String time;
                //是否轮播（0-不轮播 1-轮播)
                public String is_slide;
                public String is_float;
                public String name;
            }
        }
    }
}
