package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineIssueVideoBean  implements Serializable{

    /**
     * code : 0
     * message :
     * data : [{"uuid":1521860051,"model":"Video","province_id":0,"city_id":0,"area_id":0,"member_id":1000175,"status":1,"ctime":"3天前","etime":1527757351,"listorder":0,"deled":0,"id":235,"channel_id":0,"title":"每日勤奋的工作","picurl":"http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ip":"183.157.80.222","view_cnt":2,"source":"","images":["http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg"],"forward_cnt":0,"share_cnt":0,"info":{"id":235,"uuid":1521860051,"channel_id":0,"member_id":1000175,"title":"每日勤奋的工作","picurl":"http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1527757351,"ip":"183.157.80.222","status":1,"view_cnt":2,"source":"","images":"","forward_cnt":0,"share_cnt":0},"newctime":"2018-05-31 17:02:31"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MineIssueVideoBean objectFromData(String str) {

        return new Gson().fromJson(str, MineIssueVideoBean.class);
    }

    public static List<MineIssueVideoBean> arrayMineIssueVideoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineIssueVideoBean>>() {
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
         * uuid : 1521860051
         * model : Video
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * member_id : 1000175
         * status : 1
         * ctime : 3天前
         * etime : 1527757351
         * listorder : 0
         * deled : 0
         * id : 235
         * channel_id : 0
         * title : 每日勤奋的工作
         * picurl : http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg
         * description :
         * video_url : http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4
         * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4" frameborder="0" width="100%"></iframe>
         * like_cnt : 0
         * comment_cnt : 0
         * ip : 183.157.80.222
         * view_cnt : 2
         * source :
         * images : ["http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg"]
         * forward_cnt : 0
         * share_cnt : 0
         * info : {"id":235,"uuid":1521860051,"channel_id":0,"member_id":1000175,"title":"每日勤奋的工作","picurl":"http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg","description":"","video_url":"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1527757351,"ip":"183.157.80.222","status":1,"view_cnt":2,"source":"","images":"","forward_cnt":0,"share_cnt":0}
         * newctime : 2018-05-31 17:02:31
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
        private int channel_id;
        private String title;
        private String picurl;
        private String description;
        private String video_url;
        private String content;
        private int like_cnt;
        private int comment_cnt;
        private String ip;
        private int view_cnt;
        private String source;
        private int forward_cnt;
        private int share_cnt;
        private InfoBean info;
        private String newctime;
        private List<String> images;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private boolean isChecked;
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

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
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

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
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

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
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
             * id : 235
             * uuid : 1521860051
             * channel_id : 0
             * member_id : 1000175
             * title : 每日勤奋的工作
             * picurl : http://api.qunxianghui.com.cn/upload/video/20180531/f867ea6d191c182708153eb2cb8954b1.jpg
             * description :
             * video_url : http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4
             * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180531/1624fa3380bea17a8381784df0d4485f.mp4" frameborder="0" width="100%"></iframe>
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 1527757351
             * ip : 183.157.80.222
             * status : 1
             * view_cnt : 2
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
    }
}
