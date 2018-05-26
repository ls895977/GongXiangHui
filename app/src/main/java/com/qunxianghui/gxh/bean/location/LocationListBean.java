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
     * data : {"list":[{"id":704,"uuid":1521859105,"member_id":1000108,"title":"","content":"好心情","like_cnt":0,"comment_cnt":0,"ctime":"4分钟前","ip":"115.206.63.85","status":0,"images":["http://api.qunxianghui.com.cn/upload/posts/5b08e1ecef313.jpg"],"member_name":"我狂故我拽","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180516/cfc1b7c745701142a1d9d09c7b3be475.png","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":703,"uuid":1521859085,"member_id":1000049,"title":"","content":"在路上","like_cnt":0,"comment_cnt":0,"ctime":"1小时前","ip":"183.156.124.181","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180526/d051307fdf575f81fe12c56d99df94e0.jpg"],"member_name":"龙","member_avatar":"","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":702,"uuid":1521859057,"member_id":1000054,"title":"","content":"生活的美好在于点点滴滴都用心～","like_cnt":1,"comment_cnt":1,"ctime":"2小时前","ip":"113.215.160.164","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180526/9128e5d07d1a55ffd24dd09162ec83b4.jpg","http://api.qunxianghui.com.cn/upload/images/20180526/0c4aff55a1e5d9f599819416bd53c4ca.jpg","http://api.qunxianghui.com.cn/upload/images/20180526/d9aaf8ff524901a065bdd1806db89d98.jpg"],"member_name":"神仙姐姐ah","member_avatar":"","comment_res":[{"id":565,"comment_id":0,"pid":0,"uuid":1521859102,"data_uuid":1521859057,"member_id":1000053,"content":"好漂亮","ctime":1527307081,"ip":"113.215.160.164","status":1,"read":0,"member_name":"钱朵朵","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5adafd9c51a02.jpg","address":"浙江杭州","comment_delete":""}],"comment_num":"true","collect":"","click_like":[{"id":969,"data_uuid":1521859057,"member_id":1000108,"unlike":0,"member_name":"我狂故我拽"}],"like_info_res":"","delete":"","client_id":0},{"id":701,"uuid":1521859055,"member_id":1000160,"title":"","content":"挺好","like_cnt":0,"comment_cnt":0,"ctime":"2小时前","ip":"112.17.237.223","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180526/35c2f2d144be00cadca980e6149b77d8.jpg"],"member_name":"某时某刻某人","member_avatar":"","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"","client_id":0},{"id":700,"uuid":1521859053,"member_id":1000065,"title":"","content":"啤酒2块钱一瓶，4个瓶盖换一瓶，2个空瓶换一瓶，问10块钱能喝几瓶","like_cnt":1,"comment_cnt":0,"ctime":"2小时前","ip":"223.104.247.85","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180526/a916897a88e3cfa22b9fbd1a74d6b137.jpg"],"member_name":"碗碗","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180509/fa989a61e962e42073fef7ab14affe7b.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":965,"data_uuid":1521859053,"member_id":1000108,"unlike":0,"member_name":"我狂故我拽"}],"like_info_res":"","delete":"","client_id":0}]}
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
             * id : 704
             * uuid : 1521859105
             * member_id : 1000108
             * title :
             * content : 好心情
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 4分钟前
             * ip : 115.206.63.85
             * status : 0
             * images : ["http://api.qunxianghui.com.cn/upload/posts/5b08e1ecef313.jpg"]
             * member_name : 我狂故我拽
             * member_avatar : http://api.qunxianghui.com.cn/upload/images/20180516/cfc1b7c745701142a1d9d09c7b3be475.png
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
            private List<String> images;
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
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
