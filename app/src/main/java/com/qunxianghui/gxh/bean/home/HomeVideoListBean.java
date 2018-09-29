package com.qunxianghui.gxh.bean.home;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

public class HomeVideoListBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"list":[{"id":170,"uuid":1521857057,"channel_id":0,"member_id":1000055,"title":"我想要的爱情模样","picurl":"http://api.qunxianghui.com.cn/upload/video/20180515/66c3e197584e5345cf91c9d71a2b5003.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180515/137688eaef692f9ca32632f5125837fd.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180515/137688eaef692f9ca32632f5125837fd.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":1,"comment_cnt":0,"ctime":1526364456,"ip":"113.215.160.218","status":1,"view_cnt":11,"source":"","images":"","forward_cnt":3,"share_cnt":3,"member_name":"考拉","member_avatar":"","url":"http://fx.qunxianghui.com.cn/detail/index/id/1521857057.html","follow":""},{"id":169,"uuid":1521856991,"channel_id":0,"member_id":1000000,"title":"中国商业区域互联网发展高峰论坛暨群享汇互联网平台线上发布会在杭州隆重召开-","picurl":"http://api.qunxianghui.com.cn/upload/sys/image/d8/6936b17aa3f6ba7d1f7c170992ad8a.JPG","description":"","video_url":"","content":"<iframe height=\"500\" width=\"100%\" src=\"http://player.youku.com/embed/XMzYwMTkxMjg3Ng==\" frameborder=\"0\" 'allowfullscreen'=\"\">\r\n\t<\/iframe>","like_cnt":2,"comment_cnt":0,"ctime":1526351566,"ip":"113.215.160.218","status":1,"view_cnt":25,"source":"","images":"","forward_cnt":0,"share_cnt":0,"member_name":"共享云","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5acae2d635da1.jpeg","url":"http://fx.qunxianghui.com.cn/detail/index/id/1521856991.html","follow":""},{"id":168,"uuid":1521856974,"channel_id":0,"member_id":1000000,"title":"群享汇互联网平台发布会\u2014\u2014杭州电视台报道","picurl":"http://api.qunxianghui.com.cn/upload/sys/image/3e/aa8446a53cdc8e6f0160d922367b83.JPG","description":"","video_url":"","content":"<iframe frameborder=\"0\" width=\"100%\" height=\"450\" src=\"https://v.qq.com/iframe/player.html?vid=f0652mi27ds&tiny=0&auto=0\">\r\n<\/iframe>","like_cnt":1,"comment_cnt":0,"ctime":1526349398,"ip":"113.215.160.218","status":1,"view_cnt":35,"source":"","images":"","forward_cnt":1,"share_cnt":1,"member_name":"共享云","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5acae2d635da1.jpeg","url":"http://fx.qunxianghui.com.cn/detail/index/id/1521856974.html","follow":""},{"id":167,"uuid":1521856746,"channel_id":0,"member_id":1000055,"title":"你说去哪儿呢？","picurl":"http://api.qunxianghui.com.cn/upload/video/20180512/2c71acdc6d079e11aee9b907390e2c01.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180512/16f785e2972870d88ab351e7b00589fd.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180512/16f785e2972870d88ab351e7b00589fd.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":1,"comment_cnt":0,"ctime":1526103597,"ip":"113.215.160.218","status":1,"view_cnt":14,"source":"","images":"","forward_cnt":0,"share_cnt":0,"member_name":"考拉","member_avatar":"","url":"http://fx.qunxianghui.com.cn/detail/index/id/1521856746.html","follow":""},{"id":166,"uuid":1521856530,"channel_id":0,"member_id":1000011,"title":"漂亮，鱼养得不错","picurl":"http://api.qunxianghui.com.cn/upload/video/20180509/d37ec23c76bd963cc42085276ef8668d.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180509/9025fba966ba430a4b519e460997cf2a.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180509/9025fba966ba430a4b519e460997cf2a.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":2,"ctime":1525848725,"ip":"115.206.63.210","status":1,"view_cnt":33,"source":"","images":"","forward_cnt":0,"share_cnt":0,"member_name":"赖德林","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180509/68596c8db79be6bcd3303532ce3b0479.png","url":"http://fx.qunxianghui.com.cn/detail/index/id/1521856530.html","follow":""}]}
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

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 170
             * uuid : 1521857057
             * channel_id : 0
             * member_id : 1000055
             * title : 我想要的爱情模样
             * picurl : http://api.qunxianghui.com.cn/upload/video/20180515/66c3e197584e5345cf91c9d71a2b5003.jpg
             * description :
             * video_url : http://api.qunxianghui.com.cn/upload/video/20180515/137688eaef692f9ca32632f5125837fd.mp4
             * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180515/137688eaef692f9ca32632f5125837fd.mp4" frameborder="0" width="100%"></iframe>
             * like_cnt : 1
             * comment_cnt : 0
             * ctime : 1526364456
             * ip : 113.215.160.218
             * status : 1
             * view_cnt : 11
             * source :
             * images :
             * forward_cnt : 3
             * share_cnt : 3
             * member_name : 考拉
             * member_avatar :
             * url : http://fx.qunxianghui.com.cn/detail/index/id/1521857057.html
             * follow :
             */

            private int id;
            private int uuid;
            private int channel_id;
            private int member_id;
            private String title;
            private String picurl;
            private String description;
            private String video_url;
            private String content;
            private String like_cnt;
            private String comment_cnt;
            private int ctime;
            private String ip;
            private int status;
            private int view_cnt;
            private String source;
            private String images;
            private int forward_cnt;
            private int share_cnt;
            private String member_name;
            private String member_avatar;
            private String url;
            private String follow;
            private int is_like;

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
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

            public int getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(int channel_id) {
                this.channel_id = channel_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getTitle() {
                if (TextUtils.isEmpty(title)) return "";
                else return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLike_cnt() {
                return like_cnt;
            }

            public void setLike_cnt(String like_cnt) {
                this.like_cnt = like_cnt;
            }

            public String getComment_cnt() {
                return comment_cnt;
            }

            public void setComment_cnt(String comment_cnt) {
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

            public int getView_cnt() {
                return view_cnt;
            }

            public void setView_cnt(int view_cnt) {
                this.view_cnt = view_cnt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getForward_cnt() {
                return forward_cnt;
            }

            public void setForward_cnt(int forward_cnt) {
                this.forward_cnt = forward_cnt;
            }

            public int getShare_cnt() {
                return share_cnt;
            }

            public void setShare_cnt(int share_cnt) {
                this.share_cnt = share_cnt;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getFollow() {
                return follow;
            }

            @Override
            public String toString() {
                return "{" +
                        "id=" + id +
                        ", uuid=" + uuid +
                        ", channel_id=" + channel_id +
                        ", member_id=" + member_id +
                        ", title='" + title + '\'' +
                        ", picurl='" + picurl + '\'' +
                        ", description='" + description + '\'' +
                        ", video_url='" + video_url + '\'' +
                        ", content='" + content + '\'' +
                        ", like_cnt='" + like_cnt + '\'' +
                        ", comment_cnt='" + comment_cnt + '\'' +
                        ", ctime=" + ctime +
                        ", ip='" + ip + '\'' +
                        ", status=" + status +
                        ", view_cnt=" + view_cnt +
                        ", source='" + source + '\'' +
                        ", images='" + images + '\'' +
                        ", forward_cnt=" + forward_cnt +
                        ", share_cnt=" + share_cnt +
                        ", member_name='" + member_name + '\'' +
                        ", member_avatar='" + member_avatar + '\'' +
                        ", url='" + url + '\'' +
                        ", follow='" + follow + '\'' +
                        ", is_like=" + is_like +
                        '}';
            }

            public void setFollow(String follow) {

                this.follow = follow;
            }
        }
    }
}
