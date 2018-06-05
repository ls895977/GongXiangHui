package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineCollectPostBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":470,"data_uuid":1521860141,"member_id":1000175,"ctime":"3天前","info":{"id":907,"uuid":1521860141,"member_id":1000175,"title":"","content":"多么亲戚的领导，生日快乐哦","like_cnt":0,"comment_cnt":1,"ctime":1527823909,"ip":"113.215.161.106","status":0,"images":"http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg"},"images":["http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg"],"member":{"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","follow":""},"newctime":"3天前"},{"id":469,"data_uuid":1521859491,"member_id":1000175,"ctime":"6天前","info":{"id":783,"uuid":1521859491,"member_id":1000175,"title":"","content":"开心一刻","like_cnt":1,"comment_cnt":0,"ctime":1527560096,"ip":"183.157.81.215","status":0,"images":"/upload/images/20180529/e7d30e9df165c35f99a34a578d1a6049.jpg"},"images":["http://api.qunxianghui.com.cn/upload/images/20180529/e7d30e9df165c35f99a34a578d1a6049.jpg"],"member":{"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","follow":""},"newctime":"6天前"},{"id":441,"data_uuid":1521860322,"member_id":1000175,"ctime":"2天前","info":{"id":935,"uuid":1521860322,"member_id":1000126,"title":"","content":"让阅读成为一种艺术！","like_cnt":1,"comment_cnt":0,"ctime":1527857741,"ip":"124.160.214.78","status":0,"images":"/upload/images/20180601/9a5ea6abbed7070e10edd48331d3253a.jpg,/upload/images/20180601/ec05f46e77ec8ee7afb13b92add404b6.jpg,/upload/images/20180601/12bdae9c6da2469c4c971b77cc507d04.jpg"},"images":["http://api.qunxianghui.com.cn/upload/images/20180601/9a5ea6abbed7070e10edd48331d3253a.jpg","http://api.qunxianghui.com.cn/upload/images/20180601/ec05f46e77ec8ee7afb13b92add404b6.jpg","http://api.qunxianghui.com.cn/upload/images/20180601/12bdae9c6da2469c4c971b77cc507d04.jpg"],"member":{"member_name":"不忘初心","member_avatar":"","follow":""},"newctime":"2天前"},{"id":440,"data_uuid":1521860336,"member_id":1000175,"ctime":"2天前","info":{"id":942,"uuid":1521860336,"member_id":1000055,"title":"","content":"我真的睡觉了，","like_cnt":0,"comment_cnt":0,"ctime":1527868389,"ip":"112.17.239.81","status":0,"images":"/upload/images/20180601/55d8ae994bd86de63a27914ce457d879.jpg,/upload/images/20180601/577271a95b9be2a2100c2b02c2519fdd.jpg"},"images":["http://api.qunxianghui.com.cn/upload/images/20180601/55d8ae994bd86de63a27914ce457d879.jpg","http://api.qunxianghui.com.cn/upload/images/20180601/577271a95b9be2a2100c2b02c2519fdd.jpg"],"member":{"member_name":"考拉","member_avatar":"","follow":""},"newctime":"2天前"},{"id":438,"data_uuid":1521860352,"member_id":1000175,"ctime":"2天前","info":{"id":948,"uuid":1521860352,"member_id":1000036,"title":"","content":"网上最新的六一小马甲","like_cnt":0,"comment_cnt":0,"ctime":1527900318,"ip":"113.57.182.192","status":0,"images":"/upload/images/20180602/6453399dc02779a139813864bddc394e.jpg"},"images":["http://api.qunxianghui.com.cn/upload/images/20180602/6453399dc02779a139813864bddc394e.jpg"],"member":{"member_name":"陌上花开","member_avatar":"","follow":""},"newctime":"2天前"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineCollectPostBean objectFromData(String str) {

        return new Gson().fromJson(str, MineCollectPostBean.class);
    }

    public static List<MineCollectPostBean> arrayMineCollectPostBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineCollectPostBean>>() {
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
         * id : 470
         * data_uuid : 1521860141
         * member_id : 1000175
         * ctime : 3天前
         * info : {"id":907,"uuid":1521860141,"member_id":1000175,"title":"","content":"多么亲戚的领导，生日快乐哦","like_cnt":0,"comment_cnt":1,"ctime":1527823909,"ip":"113.215.161.106","status":0,"images":"http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg"}
         * images : ["http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg"]
         * member : {"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","follow":""}
         * newctime : 3天前
         */

        private int id;
        private int data_uuid;
        private int member_id;
        private String ctime;
        private InfoBean info;
        private MemberBean member;
        private String newctime;
        private List<String> images;

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

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public String getNewctime() {
            return newctime;
        }

        public void setNewctime(String newctime) {
            this.newctime = newctime;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class InfoBean {
            /**
             * id : 907
             * uuid : 1521860141
             * member_id : 1000175
             * title :
             * content : 多么亲戚的领导，生日快乐哦
             * like_cnt : 0
             * comment_cnt : 1
             * ctime : 1527823909
             * ip : 113.215.161.106
             * status : 0
             * images : http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg
             */

            private int id;
            private int uuid;
            private int member_id;
            private String title;
            private String content;
            private int like_cnt;
            private int comment_cnt;
            private int ctime;
            private String ip;
            private int status;
            private String images;

            public static InfoBean objectFromData(String str) {

                return new Gson().fromJson(str, InfoBean.class);
            }

            public static List<InfoBean> arrayInfoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<InfoBean>>() {
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

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
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

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }

        public static class MemberBean {
            /**
             * member_name : 臭居居的归来
             * member_avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
             * follow :
             */

            private String member_name;
            private String member_avatar;
            private String follow;

            public static MemberBean objectFromData(String str) {

                return new Gson().fromJson(str, MemberBean.class);
            }

            public static List<MemberBean> arrayMemberBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<MemberBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
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

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
            }
        }
    }
}
