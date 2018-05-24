package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class LoginBean implements Serializable {


    /**
     * code : 0
     * message : 登录成功
     * data : {"id":1000175,"level_id":1,"sex":0,"address":"aaa","nick":"yuanmeng","username":"","mobile":13116779507,"email":"","avatar":"","company_id":0,"company_info":[],
     * "accessTokenInfo":{"access_token":"Skqfj1yc2U5rhn7JOzYgCZH959wEdL01","expires_time":1527675416,"client":{"app_key":"100","id":1000175,"mobile":13116779507,"deviceId":""}}}
     */

    private int code;
    private String message;
    /**
     * id : 1000175
     * level_id : 1
     * sex : 0
     * address : aaa
     * nick : yuanmeng
     * username :
     * mobile : 13116779507
     * email :
     * avatar :
     * company_id : 0
     * company_info : []
     * accessTokenInfo : {"access_token":"Skqfj1yc2U5rhn7JOzYgCZH959wEdL01","expires_time":1527675416,"client":{"app_key":"100","id":1000175,"mobile":13116779507,"deviceId":""}}
     */

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
        /**
         * access_token : Skqfj1yc2U5rhn7JOzYgCZH959wEdL01
         * expires_time : 1527675416
         * client : {"app_key":"100","id":1000175,"mobile":13116779507,"deviceId":""}
         */

        private AccessTokenInfoBean accessTokenInfo;
        private List<?> company_info;

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

        public List<?> getCompany_info() {
            return company_info;
        }

        public void setCompany_info(List<?> company_info) {
            this.company_info = company_info;
        }

        public static class AccessTokenInfoBean {
            private String access_token;
            private int expires_time;
            /**
             * app_key : 100
             * id : 1000175
             * mobile : 13116779507
             * deviceId :
             */

            private ClientBean client;

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public int getExpires_time() {
                return expires_time;
            }

            public void setExpires_time(int expires_time) {
                this.expires_time = expires_time;
            }

            public ClientBean getClient() {
                return client;
            }

            public void setClient(ClientBean client) {
                this.client = client;
            }

            public static class ClientBean {
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

                @Override
                public String toString() {
                    return "ClientBean{" + "app_key='" + app_key + '\'' + ", id=" + id + ", mobile=" + mobile + ", deviceId='" + deviceId + '\'' + '}';
                }
            }

            @Override
            public String toString() {
                return "AccessTokenInfoBean{" + "access_token='" + access_token + '\'' + ", expires_time=" + expires_time + ", client=" + client + '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "id=" + id + ", level_id=" + level_id + ", sex=" + sex + ", address='" + address + '\'' + ", nick='" + nick + '\'' + ", username='" + username + '\'' + ", mobile="
                    + mobile + ", email='" + email + '\'' + ", avatar='" + avatar + '\'' + ", company_id=" + company_id + ", accessTokenInfo=" + accessTokenInfo + ", company_info=" + company_info +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
