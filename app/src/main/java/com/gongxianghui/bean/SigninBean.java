package com.gongxianghui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/10 16:02.
 */

public class SigninBean implements Serializable {
    private static final long serialVersionUID = -7574926208944529152L;


    /**
     * errno : 0
     * data : {"member":{"authFailMsg":"","authStatus":0,"avatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","gender":0,"id":13,"idCard":"","mobile":"15133625934","name":"","nickName":"","qq":"","qqOpenId":"","status":0,"wxOpenId":"ojHZd04TajmkZVzt4oo6-V5Lj7xU","wxPubOpenId":""},"sysdata":{"city":[{"id":1,"name":"重庆市"},{"id":2,"name":"杭州市"},{"id":3,"name":"北京市"},{"id":4,"name":"上海市"},{"id":5,"name":"武汉市"},{"id":6,"name":"宁波市"},{"id":7,"name":"成都市"},{"id":8,"name":"三亚市"},{"id":9,"name":"苏州市"},{"id":10,"name":"温州市"}],"now":1511763599,"trafficDeposit":0},"token":"4eef2fdc2a2f4cacbe4efd6e8e7a8305"}
     * message : 请求成功
     */

    private int errno;
    private DataBean data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * member : {"authFailMsg":"","authStatus":0,"avatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","gender":0,"id":13,"idCard":"","mobile":"15133625934","name":"","nickName":"","qq":"","qqOpenId":"","status":0,"wxOpenId":"ojHZd04TajmkZVzt4oo6-V5Lj7xU","wxPubOpenId":""}
         * sysdata : {"city":[{"id":1,"name":"重庆市"},{"id":2,"name":"杭州市"},{"id":3,"name":"北京市"},{"id":4,"name":"上海市"},{"id":5,"name":"武汉市"},{"id":6,"name":"宁波市"},{"id":7,"name":"成都市"},{"id":8,"name":"三亚市"},{"id":9,"name":"苏州市"},{"id":10,"name":"温州市"}],"now":1511763599,"trafficDeposit":0}
         * token : 4eef2fdc2a2f4cacbe4efd6e8e7a8305
         */

        private MemberBean member;
        private SysdataBean sysdata;
        private String token;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public SysdataBean getSysdata() {
            return sysdata;
        }

        public void setSysdata(SysdataBean sysdata) {
            this.sysdata = sysdata;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class MemberBean {
            /**
             * authFailMsg :
             * authStatus : 0
             * avatar : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg
             * gender : 0
             * id : 13
             * idCard :
             * mobile : 15133625934
             * name :
             * nickName :
             * qq :
             * qqOpenId :
             * status : 0
             * wxOpenId : ojHZd04TajmkZVzt4oo6-V5Lj7xU
             * wxPubOpenId :
             */

            private String authFailMsg;
            private int authStatus;
            private String avatar;
            private int gender;
            private int id;
            private String idCard;
            private String mobile;
            private String name;
            private String nickName;
            private String qq;
            private String qqOpenId;
            private int status;
            private String wxOpenId;
            private String wxPubOpenId;

            public String getAuthFailMsg() {
                return authFailMsg;
            }

            public void setAuthFailMsg(String authFailMsg) {
                this.authFailMsg = authFailMsg;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getQqOpenId() {
                return qqOpenId;
            }

            public void setQqOpenId(String qqOpenId) {
                this.qqOpenId = qqOpenId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(String wxOpenId) {
                this.wxOpenId = wxOpenId;
            }

            public String getWxPubOpenId() {
                return wxPubOpenId;
            }

            public void setWxPubOpenId(String wxPubOpenId) {
                this.wxPubOpenId = wxPubOpenId;
            }
        }

        public static class SysdataBean {
            /**
             * city : [{"id":1,"name":"重庆市"},{"id":2,"name":"杭州市"},{"id":3,"name":"北京市"},{"id":4,"name":"上海市"},{"id":5,"name":"武汉市"},{"id":6,"name":"宁波市"},{"id":7,"name":"成都市"},{"id":8,"name":"三亚市"},{"id":9,"name":"苏州市"},{"id":10,"name":"温州市"}]
             * now : 1511763599
             * trafficDeposit : 0
             */

            private int now;
            private double trafficDeposit;
            private List<CityBean> city;

            public int getNow() {
                return now;
            }

            public void setNow(int now) {
                this.now = now;
            }

            public double getTrafficDeposit() {
                return trafficDeposit;
            }

            public void setTrafficDeposit(int trafficDeposit) {
                this.trafficDeposit = trafficDeposit;
            }

            public List<CityBean> getCity() {
                return city;
            }

            public void setCity(List<CityBean> city) {
                this.city = city;
            }

            public static class CityBean {
                /**
                 * id : 1
                 * name : 重庆市
                 */

                private int id;
                private String name;

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
            }
        }
    }
}
