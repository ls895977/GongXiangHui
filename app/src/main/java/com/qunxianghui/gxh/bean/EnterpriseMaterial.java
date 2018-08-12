package com.qunxianghui.gxh.bean;

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
            public int showTime;
            public int linksEnterprise;
            //  广告位置：1顶部广告，2底部广告，3互推广告
            public int position;
            //operate:通栏操作（1-跳转链接 2-拨打电话 3-联系QQ 4-展示海报 5-展示二维码））
            public int operate;
            public String operate_value;

            public String link;
            public String images;
            public int status = 1;
            public Settings settings;
            public String ctime;
            public String mtime;
            public int is_slide;
            public boolean isSelect;

            public static class Settings implements Serializable {
                public String name;
                public String intro;
            }
        }
    }
}
