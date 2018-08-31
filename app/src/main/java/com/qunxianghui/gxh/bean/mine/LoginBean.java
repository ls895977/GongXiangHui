package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
     * id : 1000175
     * level_id : 1
     * sex : 1
     * address : rrrffcc
     * nick : 臭居居的归来
     * username :
     * mobile : 13116779507
     * email :
     * avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
     * company_id : 0
     * company_info : []
     * accessTokenInfo : {"access_token":"97lDzZ6v8T4Q1KB8Am7qLcY3orS4XaJj","expires_time":1528178295,"client":{"app_key":"100","id":1000175,"mobile":13116779507,"deviceId":""}}
     */

    private int id;
    private int level_id;
    private int sex;
    private String address;
    private String nick;
    private String username;
    private long mobile;
    private String email;
    private String avatar;
    private int company_id;
    private AccessTokenInfoBean accessTokenInfo;
    private CompanyInfoBean company_info;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public AccessTokenInfoBean getAccessTokenInfo() {
        return accessTokenInfo;
    }

    public void setAccessTokenInfo(AccessTokenInfoBean accessTokenInfo) {
        this.accessTokenInfo = accessTokenInfo;
    }

    public CompanyInfoBean getCompany_info() {

        return company_info;
    }

    public void setCompany_info(CompanyInfoBean company_info) {

        this.company_info = company_info;
    }


    public static class CompanyInfoBean {

        /**
         * id : 64
         * member_id : 1000122
         * company_name : 杭州共享汇信息技术有限公司
         * description :
         * company_trade : 6
         * tel :
         * mobile :
         * qq :
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * address :
         * images :
         * linkname :
         * content :
         * ctime : 1525858395
         * view_cnt : 0
         * status : 0
         * push_id : 1
         * change_ad_id : 320
         */

        private int id;
        private int member_id;
        private String company_name;
        private String description;
        private String company_trade;
        private String tel;
        private String mobile;
        private String qq;
        private int province_id;
        private int city_id;
        private int area_id;
        private String address;
        private String images;
        private String linkname;
        private String content;
        private int ctime;
        private int view_cnt;
        private int status;
        private int push_id;
        private int change_ad_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCompany_trade() {
            return company_trade;
        }

        public void setCompany_trade(String company_trade) {
            this.company_trade = company_trade;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPush_id() {
            return push_id;
        }

        public void setPush_id(int push_id) {
            this.push_id = push_id;
        }

        public int getChange_ad_id() {
            return change_ad_id;
        }

        public void setChange_ad_id(int change_ad_id) {
            this.change_ad_id = change_ad_id;
        }

        @Override
        public String toString() {
            return "CompanyInfoBean{" + "id=" + id + ", member_id=" + member_id + ", company_name='" + company_name + '\'' + ", description='" + description + '\'' + ", company_trade='" + company_trade + '\'' + ", tel='" + tel + '\'' + ", mobile='" + mobile + '\'' + ", qq='" + qq + '\'' + ", province_id=" + province_id + ", city_id=" + city_id + ", area_id=" + area_id + ", address='" + address + '\'' + ", images='" + images + '\'' + ", linkname='" + linkname + '\'' + ", content='" + content + '\'' + ", ctime=" + ctime + ", view_cnt=" + view_cnt + ", status=" + status + ", push_id=" + push_id + ", change_ad_id=" + change_ad_id + '}';
        }
    }


    public static class AccessTokenInfoBean {
        /**
         * access_token : 97lDzZ6v8T4Q1KB8Am7qLcY3orS4XaJj
         * expires_time : 1528178295
         * client : {"app_key":"100","id":1000175,"mobile":13116779507,"deviceId":""}
         */

        private String access_token;
        private String expires_time;
        private ClientBean client;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires_time() {
            return expires_time;
        }

        public void setExpires_time(String expires_time) {
            this.expires_time = expires_time;
        }

        public ClientBean getClient() {
            return client;
        }

        public void setClient(ClientBean client) {
            this.client = client;
        }

        public static class ClientBean {
            /**
             * app_key : 100
             * id : 1000175
             * mobile : 13116779507
             * deviceId :
             */

            private String app_key;
            private int id;
            private long mobile;
            private String deviceId;

            public String getApp_key() {
                return app_key;
            }

            public void setApp_key(String app_key) {
                this.app_key = app_key;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getMobile() {
                return mobile;
            }

            public void setMobile(long mobile) {
                this.mobile = mobile;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }
        }

        @Override
        public String toString() {
            return "AccessTokenInfoBean{" + "access_token='" + access_token + '\'' + ", expires_time=" + expires_time + ", client=" + client + '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" + "id=" + id + ", level_id=" + level_id + ", sex=" + sex + ", address='" + address + '\'' + ", nick='" + nick + '\'' + ", username='" + username + '\'' + ", mobile=" + mobile + ", email='" + email + '\'' + ", avatar='" + avatar + '\'' + ", company_id=" + company_id + ", accessTokenInfo=" + accessTokenInfo + ", company_info=" + company_info + '}';
    }
}
