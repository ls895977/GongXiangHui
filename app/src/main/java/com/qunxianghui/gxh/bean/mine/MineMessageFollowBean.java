package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineMessageFollowBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"uuid":1521860730,"id":643,"comment_id":0,"pid":0,"data_uuid":1521860141,"member_id":1000175,"content":"嗯","ctime":"4天前","ip":"113.215.161.231","status":1,"read":1,"posts_content":"多么亲戚的领导，生日快乐哦","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"臭居居的归来","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","reply_member_id":1000175},{"uuid":1521860140,"id":602,"comment_id":0,"pid":0,"data_uuid":1521860138,"member_id":1000175,"content":"生日快乐","ctime":"5天前","ip":"183.157.80.222","status":1,"read":1,"posts_content":"节日快乐","posts_member_name":"犟犟","posts_member_avatar":"","prosts_member_id":1000037,"location":"浙江杭州","reply_member_name":"臭居居的归来","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","reply_member_id":1000175}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineMessageFollowBean objectFromData(String str) {

        return new Gson().fromJson(str, MineMessageFollowBean.class);
    }

    public static List<MineMessageFollowBean> arrayMineMessageFollowBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineMessageFollowBean>>() {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : 1521860730
         * id : 643
         * comment_id : 0
         * pid : 0
         * data_uuid : 1521860141
         * member_id : 1000175
         * content : 嗯
         * ctime : 4天前
         * ip : 113.215.161.231
         * status : 1
         * read : 1
         * posts_content : 多么亲戚的领导，生日快乐哦
         * posts_member_name : 臭居居的归来
         * posts_member_avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
         * prosts_member_id : 1000175
         * location : 浙江杭州
         * reply_member_name : 臭居居的归来
         * reply_member_avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
         * reply_member_id : 1000175
         */

        private int uuid;
        private int id;
        private int comment_id;
        private int pid;
        private int data_uuid;
        private int member_id;
        private String content;
        private String ctime;
        private String ip;
        private int status;
        private int read;
        private String posts_content;
        private String posts_member_name;
        private String posts_member_avatar;
        private int prosts_member_id;
        private String location;
        private String reply_member_name;
        private String reply_member_avatar;
        private int reply_member_id;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getUuid() {
            return uuid;
        }

        public void setUuid(int uuid) {
            this.uuid = uuid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getData_uuid() {
            return data_uuid;
        }

        public void setData_uuid(int data_uuid) {
            this.data_uuid = data_uuid;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public String getPosts_content() {
            return posts_content;
        }

        public void setPosts_content(String posts_content) {
            this.posts_content = posts_content;
        }

        public String getPosts_member_name() {
            return posts_member_name;
        }

        public void setPosts_member_name(String posts_member_name) {
            this.posts_member_name = posts_member_name;
        }

        public String getPosts_member_avatar() {
            return posts_member_avatar;
        }

        public void setPosts_member_avatar(String posts_member_avatar) {
            this.posts_member_avatar = posts_member_avatar;
        }

        public int getProsts_member_id() {
            return prosts_member_id;
        }

        public void setProsts_member_id(int prosts_member_id) {
            this.prosts_member_id = prosts_member_id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getReply_member_name() {
            return reply_member_name;
        }

        public void setReply_member_name(String reply_member_name) {
            this.reply_member_name = reply_member_name;
        }

        public String getReply_member_avatar() {
            return reply_member_avatar;
        }

        public void setReply_member_avatar(String reply_member_avatar) {
            this.reply_member_avatar = reply_member_avatar;
        }

        public int getReply_member_id() {
            return reply_member_id;
        }

        public void setReply_member_id(int reply_member_id) {
            this.reply_member_id = reply_member_id;
        }
    }
}
