package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineMessageCommentBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"uuid":1521861107,"model":"Posts","province_id":0,"city_id":0,"area_id":0,"member_id":1000027,"status":1,"ctime":"18小时前","etime":1528186803,"listorder":0,"deled":0,"id":679,"title":"","content":"Iiii","like_cnt":0,"comment_cnt":4,"ip":"183.158.241.237","images":"http://api.qunxianghui.com.cn/upload/posts/5b1647af38770.jpeg","comment_id":0,"pid":0,"data_uuid":1521861004,"read":0,"posts_content":"哈哈","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"盖世嘤雄","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg","reply_member_id":1000027},{"uuid":1521861106,"model":"Posts","province_id":0,"city_id":0,"area_id":0,"member_id":1000027,"status":1,"ctime":"18小时前","etime":1528186803,"listorder":0,"deled":0,"id":678,"title":"","content":"Juju","like_cnt":0,"comment_cnt":4,"ip":"183.158.241.237","images":"http://api.qunxianghui.com.cn/upload/posts/5b1647af38770.jpeg","comment_id":0,"pid":0,"data_uuid":1521861004,"read":0,"posts_content":"哈哈","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"盖世嘤雄","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg","reply_member_id":1000027},{"uuid":1521861105,"model":"Posts","province_id":0,"city_id":0,"area_id":0,"member_id":1000027,"status":1,"ctime":"18小时前","etime":1528186803,"listorder":0,"deled":0,"id":677,"title":"","content":"","like_cnt":0,"comment_cnt":4,"ip":"183.158.241.237","images":"http://api.qunxianghui.com.cn/upload/posts/5b1647af38770.jpeg","comment_id":0,"pid":0,"data_uuid":1521861004,"read":0,"posts_content":"哈哈","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"盖世嘤雄","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg","reply_member_id":1000027},{"uuid":1521861104,"model":"Posts","province_id":0,"city_id":0,"area_id":0,"member_id":1000027,"status":1,"ctime":"18小时前","etime":1528186803,"listorder":0,"deled":0,"id":676,"title":"","content":"Hhggg","like_cnt":0,"comment_cnt":4,"ip":"183.158.241.237","images":"http://api.qunxianghui.com.cn/upload/posts/5b1647af38770.jpeg","comment_id":0,"pid":0,"data_uuid":1521861004,"read":0,"posts_content":"哈哈","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"盖世嘤雄","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg","reply_member_id":1000027},{"uuid":1521860730,"model":"Posts","province_id":0,"city_id":0,"area_id":0,"member_id":1000175,"status":1,"ctime":"4天前","etime":1527823909,"listorder":0,"deled":0,"id":643,"title":"","content":"嗯","like_cnt":1,"comment_cnt":1,"ip":"113.215.161.231","images":"http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg","comment_id":0,"pid":0,"data_uuid":1521860141,"read":1,"posts_content":"多么亲戚的领导，生日快乐哦","posts_member_name":"臭居居的归来","posts_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","prosts_member_id":1000175,"location":"浙江杭州","reply_member_name":"臭居居的归来","reply_member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","reply_member_id":1000175}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineMessageCommentBean objectFromData(String str) {

        return new Gson().fromJson(str, MineMessageCommentBean.class);
    }

    public static List<MineMessageCommentBean> arrayMineMessageCommentBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineMessageCommentBean>>() {
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
         * uuid : 1521861107
         * model : Posts
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * member_id : 1000027
         * status : 1
         * ctime : 18小时前
         * etime : 1528186803
         * listorder : 0
         * deled : 0
         * id : 679
         * title :
         * content : Iiii
         * like_cnt : 0
         * comment_cnt : 4
         * ip : 183.158.241.237
         * images : http://api.qunxianghui.com.cn/upload/posts/5b1647af38770.jpeg
         * comment_id : 0
         * pid : 0
         * data_uuid : 1521861004
         * read : 0
         * posts_content : 哈哈
         * posts_member_name : 臭居居的归来
         * posts_member_avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
         * prosts_member_id : 1000175
         * location : 浙江杭州
         * reply_member_name : 盖世嘤雄
         * reply_member_avatar : http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg
         * reply_member_id : 1000027
         */

        private int uuid;
        private String model;
        private int province_id;
        private int city_id;
        private int area_id;
        private int member_id;
        private int status;
        private String ctime;
        private int etime;
        private int listorder;
        private int deled;
        private int id;
        private String title;
        private String content;
        private int like_cnt;
        private int comment_cnt;
        private String ip;
        private String images;
        private int comment_id;
        private int pid;
        private int data_uuid;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public int getEtime() {
            return etime;
        }

        public void setEtime(int etime) {
            this.etime = etime;
        }

        public int getListorder() {
            return listorder;
        }

        public void setListorder(int listorder) {
            this.listorder = listorder;
        }

        public int getDeled() {
            return deled;
        }

        public void setDeled(int deled) {
            this.deled = deled;
        }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }

        public int getComment_cnt() {
            return comment_cnt;
        }

        public void setComment_cnt(int comment_cnt) {
            this.comment_cnt = comment_cnt;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
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
