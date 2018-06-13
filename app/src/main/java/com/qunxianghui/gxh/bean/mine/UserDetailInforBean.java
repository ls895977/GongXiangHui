package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDetailInforBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"id":1000001,"level_id":1,"nick":"群享汇","username":"群享汇","member_name":"群享汇","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5acada226deb9.jpeg","level_info":{"id":1,"name":"注册会员","intro":"","default":1,"expire":0,"status":1,"ctime":1524712832,"mtime":1491966814},"follow":""}
     */

    private int code;
    private String message;
    private DataBean data;

    public static UserDetailInforBean objectFromData(String str) {

        return new Gson().fromJson(str, UserDetailInforBean.class);
    }

    public static List<UserDetailInforBean> arrayUserDetailInforBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserDetailInforBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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
         * id : 1000001
         * level_id : 1
         * nick : 群享汇
         * username : 群享汇
         * member_name : 群享汇
         * member_avatar : http://api.qunxianghui.com.cn/upload/posts/5acada226deb9.jpeg
         * level_info : {"id":1,"name":"注册会员","intro":"","default":1,"expire":0,"status":1,"ctime":1524712832,"mtime":1491966814}
         * follow :
         */

        private int id;
        private int level_id;
        private String nick;
        private String username;
        private String member_name;
        private String member_avatar;
        private LevelInfoBean level_info;
        private String follow;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

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

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public LevelInfoBean getLevel_info() {
            return level_info;
        }

        public void setLevel_info(LevelInfoBean level_info) {
            this.level_info = level_info;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public static class LevelInfoBean {
            /**
             * id : 1
             * name : 注册会员
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

            public static LevelInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, LevelInfoBean.class);
            }

            public static List<LevelInfoBean> arrayLevelInfoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<LevelInfoBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

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
    }
}
