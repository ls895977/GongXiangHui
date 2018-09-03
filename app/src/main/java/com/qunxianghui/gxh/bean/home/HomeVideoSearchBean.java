package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

public class HomeVideoSearchBean  implements Serializable{

    /**
     * code : 200
     * msg : 请求成功
     * data : [{"id":528,"uuid":1521864325,"channel_id":0,"member_id":1000028,"cate_id":1,"title":"法国进口","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180822/06f1b54661ec01a95340e31a7f72b7c8310f1c4e.png","description":"","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180822/80956ee62d6ca0760ac3eda0d09c630539c939d1.mp4","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180822/80956ee62d6ca0760ac3eda0d09c630539c939d1.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":3,"comment_cnt":0,"ctime":1534936052,"ip":"223.104.246.159","status":1,"view_cnt":20,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"member_name":"先生q","member_avatar":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180824/1ef8c1918eebd5d3b25196168364b3f7.jpeg","follow":"true","is_like":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 528
         * uuid : 1521864325
         * channel_id : 0
         * member_id : 1000028
         * cate_id : 1
         * title : 法国进口
         * picurl : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180822/06f1b54661ec01a95340e31a7f72b7c8310f1c4e.png
         * description :
         * video_url : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180822/80956ee62d6ca0760ac3eda0d09c630539c939d1.mp4
         * content : <iframe height="298" src="http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180822/80956ee62d6ca0760ac3eda0d09c630539c939d1.mp4" frameborder="0" width="100%"></iframe>
         * like_cnt : 3
         * comment_cnt : 0
         * ctime : 1534936052
         * ip : 223.104.246.159
         * status : 1
         * view_cnt : 20
         * source : 0
         * images : 0
         * forward_cnt : 0
         * share_cnt : 0
         * member_name : 先生q
         * member_avatar : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180824/1ef8c1918eebd5d3b25196168364b3f7.jpeg
         * follow : true
         * is_like : 1
         */

        private int id;
        private int uuid;
        private int channel_id;
        private int member_id;
        private int cate_id;
        private String title;
        private String picurl;
        private String description;
        private String video_url;
        private String content;
        private int like_cnt;
        private int comment_cnt;
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
        private String follow;
        private int is_like;

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

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
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

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }
    }
}
