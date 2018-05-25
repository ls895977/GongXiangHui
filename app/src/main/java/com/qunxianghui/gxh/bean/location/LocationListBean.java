package com.qunxianghui.gxh.bean.location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LocationListBean implements Serializable {


    /**
     * code : 0
     * message :
     * data : {"list":[{"id":642,"uuid":1521858832,"member_id":1000025,"title":"","content":"生活不是勉为其难的应付，需要调适，丰富，柔软，推进。总之一句话，好好的活着。","like_cnt":0,"comment_cnt":0,"ctime":"12分钟前","ip":"113.215.160.164","status":0,"images":[],"member_name":"群享汇","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5aca11fe1b58a.jpeg","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":641,"uuid":1521858830,"member_id":1000025,"title":"","content":"生命里不见固执的我，便是跳出了这是非的漩涡。","like_cnt":0,"comment_cnt":0,"ctime":"13分钟前","ip":"113.215.160.164","status":0,"images":[],"member_name":"群享汇","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5aca11fe1b58a.jpeg","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":640,"uuid":1521858823,"member_id":1000009,"title":"","content":"一键发布","like_cnt":0,"comment_cnt":0,"ctime":"30分钟前","ip":"113.215.160.164","status":0,"images":[],"member_name":"陈公子_璐","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180517/90ff8c669d402cbdc00a8289c92929d3.png","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":639,"uuid":1521858817,"member_id":1000065,"title":"","content":"毕业答辩","like_cnt":1,"comment_cnt":0,"ctime":"1小时前","ip":"112.17.235.178","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180525/5333ab94f62047e86cc013f8022ef53d.jpg","http://api.qunxianghui.com.cn/upload/images/20180525/64b92902d79ed25704661727577f7f28.jpg"],"member_name":"碗碗","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180509/fa989a61e962e42073fef7ab14affe7b.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":871,"data_uuid":1521858817,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}],"like_info_res":"","delete":"","client_id":0},{"id":638,"uuid":1521858813,"member_id":1000043,"title":"","content":"学无止境","like_cnt":0,"comment_cnt":0,"ctime":"1小时前","ip":"113.215.160.164","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180525/f076b18fb65480cf6aa8061ff89f4a35.jpg","http://api.qunxianghui.com.cn/upload/images/20180525/1323d9e73ff12714bfacb93a769a4906.jpg"],"member_name":"蔡╓〞＝㈠o(*￣▽￣*)ブ","member_avatar":"","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public static LocationListBean objectFromData(String str) {

        return new Gson().fromJson(str, LocationListBean.class);
    }

    public static List<LocationListBean> arrayLocationListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LocationListBean>>() {
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
        private List<ListBean> list;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 642
             * uuid : 1521858832
             * member_id : 1000025
             * title :
             * content : 生活不是勉为其难的应付，需要调适，丰富，柔软，推进。总之一句话，好好的活着。
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 12分钟前
             * ip : 113.215.160.164
             * status : 0
             * images : []
             * member_name : 群享汇
             * member_avatar : http://api.qunxianghui.com.cn/upload/posts/5aca11fe1b58a.jpeg
             * comment_res : []
             * comment_num :
             * collect :
             * click_like :
             * like_info_res :
             * delete :
             * client_id : 0
             */

            private int id;
            private int uuid;
            private int member_id;
            private String title;
            private String content;
            private int like_cnt;
            private int comment_cnt;
            private String ctime;
            private String ip;
            private int status;
            private String member_name;
            private String member_avatar;
            private String comment_num;
            private String collect;
            private String click_like;
            private String like_info_res;
            private String delete;
            private int client_id;
            private List<?> images;
            private List<?> comment_res;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public static List<ListBean> arrayListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUuid() {
                return uuid;
            }

            public void setUuid(int uuid) {
                this.uuid = uuid;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
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

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getCollect() {
                return collect;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

            public String getClick_like() {
                return click_like;
            }

            public void setClick_like(String click_like) {
                this.click_like = click_like;
            }

            public String getLike_info_res() {
                return like_info_res;
            }

            public void setLike_info_res(String like_info_res) {
                this.like_info_res = like_info_res;
            }

            public String getDelete() {
                return delete;
            }

            public void setDelete(String delete) {
                this.delete = delete;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public List<?> getComment_res() {
                return comment_res;
            }

            public void setComment_res(List<?> comment_res) {
                this.comment_res = comment_res;
            }
        }
    }
}
