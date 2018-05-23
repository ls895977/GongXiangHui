package com.qunxianghui.gxh.bean.mine;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MineUserBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"id":1000030,"level_id":1,"nick":"圆梦","username":"","mobile":17757149916,"email":"","money":"0.00","frozen_money":"0.00","income":"0.00","expend":"0.00","exper":0,"integral":0,"frozen_integral":0,"sex":0,"avatar":"","last_login_ip":"36.23.83.4","last_login_time":1526468803,"login_count":0,"expire_time":"2019-05-21","status":1,"ctime":1523186162,"level_2":0,"level_3":0,"level_4":0,"level_5":0,"remark":"","isactive":0,"active_time":0,"agency_id":0,"company_name":"","company_intro":"","company_trade":"","region_name":"","address":"西湖区","channel_ids":"","province_id":0,"city_id":0,"area_id":0,"last_captcha":0,"serarch_key_word":"","device_id":"","level_info":{"id":1,"name":"企业会员","intro":"","default":1,"expire":0,"status":1,"ctime":1524712832,"mtime":1491966814},"like_cnt":3,"posts_cnt":3,"comment_cnt":0,"company_id":1000122,"company_info":{"id":64,"member_id":1000122,"company_name":"杭州共享汇信息技术有限公司","description":"","company_trade":"6","tel":"","mobile":"","qq":"","province_id":0,"city_id":0,"area_id":0,"address":"","images":"","linkname":"","content":"","ctime":1525858395,"view_cnt":0,"status":0,"push_id":1,"change_ad_id":320},"staff":1}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1000030
         * level_id : 1
         * nick : 圆梦
         * username :
         * mobile : 17757149916
         * email :
         * money : 0.00
         * frozen_money : 0.00
         * income : 0.00
         * expend : 0.00
         * exper : 0
         * integral : 0
         * frozen_integral : 0
         * sex : 0
         * avatar :
         * last_login_ip : 36.23.83.4
         * last_login_time : 1526468803
         * login_count : 0
         * expire_time : 2019-05-21
         * status : 1
         * ctime : 1523186162
         * level_2 : 0
         * level_3 : 0
         * level_4 : 0
         * level_5 : 0
         * remark :
         * isactive : 0
         * active_time : 0
         * agency_id : 0
         * company_name :
         * company_intro :
         * company_trade :
         * region_name :
         * address : 西湖区
         * channel_ids :
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * last_captcha : 0
         * serarch_key_word :
         * device_id :
         * level_info : {"id":1,"name":"企业会员","intro":"","default":1,"expire":0,"status":1,"ctime":1524712832,"mtime":1491966814}
         * like_cnt : 3
         * posts_cnt : 3
         * comment_cnt : 0
         * company_id : 1000122
         * company_info : {"id":64,"member_id":1000122,"company_name":"杭州共享汇信息技术有限公司","description":"","company_trade":"6","tel":"","mobile":"","qq":"","province_id":0,"city_id":0,"area_id":0,"address":"","images":"","linkname":"","content":"","ctime":1525858395,"view_cnt":0,"status":0,"push_id":1,"change_ad_id":320}
         * staff : 1
         */

        private int id;
        private int level_id;
        private String nick;
        private String username;
        private long mobile;
        private String email;
        private String money;
        private String frozen_money;
        private String income;
        private String expend;
        private int exper;
        private int integral;
        private int frozen_integral;
        private int sex;
        private String avatar;
        private String last_login_ip;
        private int last_login_time;
        private int login_count;
        private String expire_time;
        private int status;
        private int ctime;
        private int level_2;
        private int level_3;
        private int level_4;
        private int level_5;
        private String remark;
        private int isactive;
        private int active_time;
        private int agency_id;
        private String company_name;
        private String company_intro;
        private String company_trade;
        private String region_name;
        private String address;
        private String channel_ids;
        private int province_id;
        private int city_id;
        private int area_id;
        private int last_captcha;
        private String serarch_key_word;
        private String device_id;
        private LevelInfoBean level_info;
        private int like_cnt;
        private int posts_cnt;
        private int comment_cnt;
        private int company_id;
        private CompanyInfoBean company_info;
        private int staff;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getExpend() {
            return expend;
        }

        public void setExpend(String expend) {
            this.expend = expend;
        }

        public int getExper() {
            return exper;
        }

        public void setExper(int exper) {
            this.exper = exper;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getFrozen_integral() {
            return frozen_integral;
        }

        public void setFrozen_integral(int frozen_integral) {
            this.frozen_integral = frozen_integral;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public int getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(int last_login_time) {
            this.last_login_time = last_login_time;
        }

        public int getLogin_count() {
            return login_count;
        }

        public void setLogin_count(int login_count) {
            this.login_count = login_count;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getLevel_2() {
            return level_2;
        }

        public void setLevel_2(int level_2) {
            this.level_2 = level_2;
        }

        public int getLevel_3() {
            return level_3;
        }

        public void setLevel_3(int level_3) {
            this.level_3 = level_3;
        }

        public int getLevel_4() {
            return level_4;
        }

        public void setLevel_4(int level_4) {
            this.level_4 = level_4;
        }

        public int getLevel_5() {
            return level_5;
        }

        public void setLevel_5(int level_5) {
            this.level_5 = level_5;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIsactive() {
            return isactive;
        }

        public void setIsactive(int isactive) {
            this.isactive = isactive;
        }

        public int getActive_time() {
            return active_time;
        }

        public void setActive_time(int active_time) {
            this.active_time = active_time;
        }

        public int getAgency_id() {
            return agency_id;
        }

        public void setAgency_id(int agency_id) {
            this.agency_id = agency_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_intro() {
            return company_intro;
        }

        public void setCompany_intro(String company_intro) {
            this.company_intro = company_intro;
        }

        public String getCompany_trade() {
            return company_trade;
        }

        public void setCompany_trade(String company_trade) {
            this.company_trade = company_trade;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getChannel_ids() {
            return channel_ids;
        }

        public void setChannel_ids(String channel_ids) {
            this.channel_ids = channel_ids;
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

        public int getLast_captcha() {
            return last_captcha;
        }

        public void setLast_captcha(int last_captcha) {
            this.last_captcha = last_captcha;
        }

        public String getSerarch_key_word() {
            return serarch_key_word;
        }

        public void setSerarch_key_word(String serarch_key_word) {
            this.serarch_key_word = serarch_key_word;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public LevelInfoBean getLevel_info() {
            return level_info;
        }

        public void setLevel_info(LevelInfoBean level_info) {
            this.level_info = level_info;
        }

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }

        public int getPosts_cnt() {
            return posts_cnt;
        }

        public void setPosts_cnt(int posts_cnt) {
            this.posts_cnt = posts_cnt;
        }

        public int getComment_cnt() {
            return comment_cnt;
        }

        public void setComment_cnt(int comment_cnt) {
            this.comment_cnt = comment_cnt;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public CompanyInfoBean getCompany_info() {
            return company_info;
        }

        public void setCompany_info(CompanyInfoBean company_info) {
            this.company_info = company_info;
        }

        public int getStaff() {
            return staff;
        }

        public void setStaff(int staff) {
            this.staff = staff;
        }

        public static class LevelInfoBean {
            /**
             * id : 1
             * name : 企业会员
             * intro :
             * default : 1
             * expire : 0
             * status : 1
             * ctime : 1524712832
             * mtime : 1491966814
             */

            private int id;
            private String name;
            private String intro;
            @SerializedName("default")
            private int defaultX;
            private int expire;
            private int status;
            private int ctime;
            private int mtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(int defaultX) {
                this.defaultX = defaultX;
            }

            public int getExpire() {
                return expire;
            }

            public void setExpire(int expire) {
                this.expire = expire;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getMtime() {
                return mtime;
            }

            public void setMtime(int mtime) {
                this.mtime = mtime;
            }
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
        }
    }
}
