package com.qunxianghui.gxh.bean.mine;

/**
 * Created by user on 2018/6/11.
 */

import java.util.List;

public class AdListBean {

    /**
     * code : 0
     * message : 查询成功。
     * data : [{"id":581,"title":"","member_id":1000010,"ad_type":2,"position":0,"link":"","images":"http://api.qunxianghui.com.cn/upload/images/20180611/1baf38f94c369e3712d2daf6e740a336.png","status":0,"settings":{"name":"赵龙涛","mobile":"13116779507","address":"无敌铁拳"},"ctime":0,"is_slide":0}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 581
         * title :
         * member_id : 1000010
         * ad_type : 2
         * position : 0
         * link :
         * images : http://api.qunxianghui.com.cn/upload/images/20180611/1baf38f94c369e3712d2daf6e740a336.png
         * status : 0
         * settings : {"name":"赵龙涛","mobile":"13116779507","address":"无敌铁拳"}
         * ctime : 0
         * is_slide : 0
         */

        private int id;
        private String title;
        private int member_id;
        private int ad_type;
        private int position;
        private String link;
        private String images;
        private int status;
        private SettingsBean settings;
        private int ctime;
        private int is_slide;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getAd_type() {
            return ad_type;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public SettingsBean getSettings() {
            return settings;
        }

        public void setSettings(SettingsBean settings) {
            this.settings = settings;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getIs_slide() {
            return is_slide;
        }

        public void setIs_slide(int is_slide) {
            this.is_slide = is_slide;
        }

        public static class SettingsBean {
            /**
             * name : 赵龙涛
             * mobile : 13116779507
             * address : 无敌铁拳
             */

            private String name;
            private String mobile;
            private String address;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
