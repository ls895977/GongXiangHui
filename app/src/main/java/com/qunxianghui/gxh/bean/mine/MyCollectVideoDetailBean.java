package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;

public class MyCollectVideoDetailBean implements Serializable {


    /**
     * code : 0
     * message :
     * data : {"id":1158,"uuid":1521870158,"channel_id":0,"member_id":1000057,"title":"不知所措","picurl":"http://api.qunxianghui.com.cn/upload/video/20180704/f73be35daa2e76475013aaa4234c54e4.png","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180704/a72c772a86b0dfb40727511e4728c7a1.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180704/a72c772a86b0dfb40727511e4728c7a1.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":"2018-07-04 14:57:32","ip":"113.215.161.166","status":1,"view_cnt":2,"source":"","images":"","forward_cnt":0,"share_cnt":0,"has_collect":true,"url":"http://fx.qunxianghui.com.cn/detail/index/id/1521870158.html","bendi":"","imgUrl":"http://api.qunxianghui.com.cn/theme/default/static/image/logo.png","desc":""}
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

    public static class DataBean {
        /**
         * id : 1158
         * uuid : 1521870158
         * channel_id : 0
         * member_id : 1000057
         * title : 不知所措
         * picurl : http://api.qunxianghui.com.cn/upload/video/20180704/f73be35daa2e76475013aaa4234c54e4.png
         * description :
         * video_url : http://api.qunxianghui.com.cn/upload/video/20180704/a72c772a86b0dfb40727511e4728c7a1.mp4
         * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180704/a72c772a86b0dfb40727511e4728c7a1.mp4" frameborder="0" width="100%"></iframe>
         * like_cnt : 0
         * comment_cnt : 0
         * ctime : 2018-07-04 14:57:32
         * ip : 113.215.161.166
         * status : 1
         * view_cnt : 2
         * source :
         * images :
         * forward_cnt : 0
         * share_cnt : 0
         * has_collect : true
         * url : http://fx.qunxianghui.com.cn/detail/index/id/1521870158.html
         * bendi :
         * imgUrl : http://api.qunxianghui.com.cn/theme/default/static/image/logo.png
         * desc :
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
        private int like_cnt;
        private int comment_cnt;
        private String ctime;
        private String ip;
        private int status;
        private int view_cnt;
        private String source;
        private String images;
        private int forward_cnt;
        private int share_cnt;
        private boolean has_collect;
        private String url;
        private String bendi;
        private String imgUrl;
        private String desc;

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
            return title;
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

        public boolean isHas_collect() {
            return has_collect;
        }

        public void setHas_collect(boolean has_collect) {
            this.has_collect = has_collect;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBendi() {
            return bendi;
        }

        public void setBendi(String bendi) {
            this.bendi = bendi;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
