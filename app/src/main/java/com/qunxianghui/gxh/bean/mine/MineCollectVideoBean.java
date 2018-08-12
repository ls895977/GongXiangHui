package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineCollectVideoBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":302,"data_uuid":1521857456,"member_id":1000010,"ctime":"22天前","info":{"id":171,"uuid":1521857456,"channel_id":0,"member_id":1000053,"title":"我们毕业了","picurl":"http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg","description":"我们毕业了","video_url":"http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":2,"comment_cnt":1,"ctime":1526550752,"ip":"113.215.160.218","status":1,"view_cnt":62,"source":"","images":"","forward_cnt":0,"share_cnt":0},"picurl":"http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg","images":["http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg"],"member":{"member_name":"钱朵朵","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5adafd9c51a02.jpg","follow":""},"newctime":"22天前"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineCollectVideoBean objectFromData(String str) {

        return new Gson().fromJson(str, MineCollectVideoBean.class);
    }

    public static List<MineCollectVideoBean> arrayMineCollectVideoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineCollectVideoBean>>() {
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
         * id : 302
         * data_uuid : 1521857456
         * member_id : 1000010
         * ctime : 22天前
         * info : {"id":171,"uuid":1521857456,"channel_id":0,"member_id":1000053,"title":"我们毕业了","picurl":"http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg","description":"我们毕业了","video_url":"http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":2,"comment_cnt":1,"ctime":1526550752,"ip":"113.215.160.218","status":1,"view_cnt":62,"source":"","images":"","forward_cnt":0,"share_cnt":0}
         * picurl : http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg
         * images : ["http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg"]
         * member : {"member_name":"钱朵朵","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5adafd9c51a02.jpg","follow":""}
         * newctime : 22天前
         */

        private int id;
        private int data_uuid;
        private int member_id;
        private String ctime;
        private InfoBean info;
        private String picurl;
        private MemberBean member;
        private String newctime;
        private List<String> images;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

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

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
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
             * id : 171
             * uuid : 1521857456
             * channel_id : 0
             * member_id : 1000053
             * title : 我们毕业了
             * picurl : http://api.qunxianghui.com.cn/upload/video/20180517/46cec231fef958a7f75e0511ab916789.jpg
             * description : 我们毕业了
             * video_url : http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4
             * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180517/c799a519829152337b6575ddf2d9267a.mp4" frameborder="0" width="100%"></iframe>
             * like_cnt : 2
             * comment_cnt : 1
             * ctime : 1526550752
             * ip : 113.215.160.218
             * status : 1
             * view_cnt : 62
             * source :
             * images :
             * forward_cnt : 0
             * share_cnt : 0
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
            private int ctime;
            private String ip;
            private int status;
            private int view_cnt;
            private String source;
            private String images;
            private int forward_cnt;
            private int share_cnt;

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

        public static class MemberBean {
            /**
             * member_name : 钱朵朵
             * member_avatar : http://api.qunxianghui.com.cn/upload/posts/5adafd9c51a02.jpg
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
