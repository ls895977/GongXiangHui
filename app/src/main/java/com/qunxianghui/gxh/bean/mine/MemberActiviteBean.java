package com.qunxianghui.gxh.bean.mine;

public class MemberActiviteBean {

    /**
     * code : 0
     * msg : 操作成功
     * data : {"id":1000310,"level_id":1,"sex":1,"address":"西园路","nick":"shangah","username":"shnaghai","mobile":17630900413,"email":"545034502@qq.com","avatar":"","company_id":1000318,"company_info":{"id":105,"member_id":1000318,"company_name":"茂源房产","description":null,"company_trade":"2","tel":null,"mobile":null,"qq":null,"province_id":null,"city_id":null,"area_id":null,"address":null,"images":null,"linkname":null,"content":null,"ctime":1534855735,"view_cnt":null,"status":1,"push_id":null,"change_ad_id":null},"accessTokenInfo":{"access_token":"XhxGLYED72o6izr2md6CBPwUVRWQSf3I","expires_time":"","client":{"app_key":"100","id":1000310,"mobile":17630900413,"deviceId":"008796755566460"}}}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1000310
         * level_id : 1
         * sex : 1
         * address : 西园路
         * nick : shangah
         * username : shnaghai
         * mobile : 17630900413
         * email : 545034502@qq.com
         * avatar :
         * company_id : 1000318
         * company_info : {"id":105,"member_id":1000318,"company_name":"茂源房产","description":null,"company_trade":"2","tel":null,"mobile":null,"qq":null,"province_id":null,"city_id":null,"area_id":null,"address":null,"images":null,"linkname":null,"content":null,"ctime":1534855735,"view_cnt":null,"status":1,"push_id":null,"change_ad_id":null}
         * accessTokenInfo : {"access_token":"XhxGLYED72o6izr2md6CBPwUVRWQSf3I","expires_time":"","client":{"app_key":"100","id":1000310,"mobile":17630900413,"deviceId":"008796755566460"}}
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
        private CompanyInfoBean company_info;
        private AccessTokenInfoBean accessTokenInfo;

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

        public CompanyInfoBean getCompany_info() {
            return company_info;
        }

        public void setCompany_info(CompanyInfoBean company_info) {
            this.company_info = company_info;
        }

        public AccessTokenInfoBean getAccessTokenInfo() {
            return accessTokenInfo;
        }

        public void setAccessTokenInfo(AccessTokenInfoBean accessTokenInfo) {
            this.accessTokenInfo = accessTokenInfo;
        }

        public static class CompanyInfoBean {
            /**
             * id : 105
             * member_id : 1000318
             * company_name : 茂源房产
             * description : null
             * company_trade : 2
             * tel : null
             * mobile : null
             * qq : null
             * province_id : null
             * city_id : null
             * area_id : null
             * address : null
             * images : null
             * linkname : null
             * content : null
             * ctime : 1534855735
             * view_cnt : null
             * status : 1
             * push_id : null
             * change_ad_id : null
             */

            private int id;
            private int member_id;
            private String company_name;
            private Object description;
            private String company_trade;
            private Object tel;
            private Object mobile;
            private Object qq;
            private Object province_id;
            private Object city_id;
            private Object area_id;
            private Object address;
            private Object images;
            private Object linkname;
            private Object content;
            private int ctime;
            private Object view_cnt;
            private int status;
            private Object push_id;
            private Object change_ad_id;

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

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getCompany_trade() {
                return company_trade;
            }

            public void setCompany_trade(String company_trade) {
                this.company_trade = company_trade;
            }

            public Object getTel() {
                return tel;
            }

            public void setTel(Object tel) {
                this.tel = tel;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getQq() {
                return qq;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public Object getProvince_id() {
                return province_id;
            }

            public void setProvince_id(Object province_id) {
                this.province_id = province_id;
            }

            public Object getCity_id() {
                return city_id;
            }

            public void setCity_id(Object city_id) {
                this.city_id = city_id;
            }

            public Object getArea_id() {
                return area_id;
            }

            public void setArea_id(Object area_id) {
                this.area_id = area_id;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getImages() {
                return images;
            }

            public void setImages(Object images) {
                this.images = images;
            }

            public Object getLinkname() {
                return linkname;
            }

            public void setLinkname(Object linkname) {
                this.linkname = linkname;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public Object getView_cnt() {
                return view_cnt;
            }

            public void setView_cnt(Object view_cnt) {
                this.view_cnt = view_cnt;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getPush_id() {
                return push_id;
            }

            public void setPush_id(Object push_id) {
                this.push_id = push_id;
            }

            public Object getChange_ad_id() {
                return change_ad_id;
            }

            public void setChange_ad_id(Object change_ad_id) {
                this.change_ad_id = change_ad_id;
            }
        }

        public static class AccessTokenInfoBean {
            /**
             * access_token : XhxGLYED72o6izr2md6CBPwUVRWQSf3I
             * expires_time :
             * client : {"app_key":"100","id":1000310,"mobile":17630900413,"deviceId":"008796755566460"}
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
                 * id : 1000310
                 * mobile : 17630900413
                 * deviceId : 008796755566460
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
        }
    }
}
