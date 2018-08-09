package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

public class HomeVideoSearchBean  implements Serializable{

    /**
     * code : 200
     * msg : 请求成功
     * data : [{"id":227,"uuid":1521859739,"channel_id":0,"member_id":1000052,"cate_id":0,"title":"尼泊尔大金钢","picurl":"http://api.qunxianghui.com.cn/upload/video/20180530/5818801cb06e228c76352e80f0690a65.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180530/fe507bda1802f331ebabe2675da6a526.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180530/fe507bda1802f331ebabe2675da6a526.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1527657305,"ip":"113.215.161.106","status":1,"view_cnt":6,"source":"","images":"","forward_cnt":0,"share_cnt":0},{"id":213,"uuid":1521859251,"channel_id":0,"member_id":1000052,"cate_id":0,"title":"后悔当初对面没买下来，这么大的露台可以变成大花园","picurl":"http://api.qunxianghui.com.cn/upload/video/20180527/0bee67b0e2b90837cf5d31cce6cb36d5.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180527/bfe55ce07ede9b81d9b501306c73fe20.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180527/bfe55ce07ede9b81d9b501306c73fe20.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1527385784,"ip":"218.0.238.36","status":1,"view_cnt":3,"source":"","images":"","forward_cnt":0,"share_cnt":0},{"id":292,"uuid":1521861009,"channel_id":0,"member_id":1000113,"cate_id":0,"title":"身临其境","picurl":"http://api.qunxianghui.com.cn/upload/video/20180605/6672506155ade4a9590090c730a4e856.jpg","description":"身临其境","video_url":"http://api.qunxianghui.com.cn/upload/video/20180605/60e2ee36958dd2f897ed04954728b187.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180605/60e2ee36958dd2f897ed04954728b187.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528187712,"ip":"112.17.237.74","status":1,"view_cnt":7,"source":"","images":"","forward_cnt":0,"share_cnt":0},{"id":303,"uuid":1521861258,"channel_id":0,"member_id":1000212,"cate_id":0,"title":"在哪？","picurl":"http://api.qunxianghui.com.cn/upload/video/20180606/e2aace4b30f582721f80c2873711ff91.jpg","description":"在哪？","video_url":"http://api.qunxianghui.com.cn/upload/video/20180606/047ecc06d13c44a9368816ca5751e74f.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180606/047ecc06d13c44a9368816ca5751e74f.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528277325,"ip":"125.118.63.177","status":1,"view_cnt":1,"source":"","images":"","forward_cnt":0,"share_cnt":0},{"id":274,"uuid":1521860662,"channel_id":0,"member_id":1000099,"cate_id":0,"title":"爆笑","picurl":"http://api.qunxianghui.com.cn/upload/video/20180604/665419ee6792642de9b1f6004f252d1b.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180604/f63ea9c6f1b1440b41bc4849f975d8b7.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180604/f63ea9c6f1b1440b41bc4849f975d8b7.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528079044,"ip":"183.158.241.237","status":1,"view_cnt":2,"source":"","images":"","forward_cnt":0,"share_cnt":0},{"id":278,"uuid":1521860807,"channel_id":0,"member_id":1000089,"cate_id":0,"title":"广场舞的新标准","picurl":"http://api.qunxianghui.com.cn/upload/video/20180604/f3cb1e30b9dd8d1bf6e73af426e0a5b5.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180604/551b3988d3d9038558637553494a251c.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180604/551b3988d3d9038558637553494a251c.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528106556,"ip":"113.215.161.231","status":1,"view_cnt":1,"source":"","images":"","forward_cnt":0,"share_cnt":0}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 227
         * uuid : 1521859739
         * channel_id : 0
         * member_id : 1000052
         * cate_id : 0
         * title : 尼泊尔大金钢
         * picurl : http://api.qunxianghui.com.cn/upload/video/20180530/5818801cb06e228c76352e80f0690a65.jpg
         * description :
         * video_url : http://api.qunxianghui.com.cn/upload/video/20180530/fe507bda1802f331ebabe2675da6a526.mp4
         * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180530/fe507bda1802f331ebabe2675da6a526.mp4" frameborder="0" width="100%"></iframe>
         * like_cnt : 0
         * comment_cnt : 0
         * ctime : 1527657305
         * ip : 113.215.161.106
         * status : 1
         * view_cnt : 6
         * source :
         * images :
         * forward_cnt : 0
         * share_cnt : 0
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
    }
}
