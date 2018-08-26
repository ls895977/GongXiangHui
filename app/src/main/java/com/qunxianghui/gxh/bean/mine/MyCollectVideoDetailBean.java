package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class MyCollectVideoDetailBean implements Serializable {


    /**
     * code : 200
     * msg : 请求成功
     * data : {"detail":{"id":499,"uuid":1521863942,"channel_id":0,"member_id":1000010,"cate_id":1,"title":"菊姐","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png","description":"头像","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":"2018-08-20 15:18:16","ip":"115.192.223.11","status":1,"view_cnt":30,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"member_name":"臭居居的汇归来","member_avatar":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180817/bc2da3e494897ea5de79b2bc85e3e18b.jpeg","share_pic":"http://api.qunxianghui.com.cnhttp://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png","share_desc":"头像"},"comment":[],"person":{"like_info":0,"collect_res":0,"follow":0},"rand_data":[{"id":372,"uuid":1521862006,"channel_id":0,"member_id":1000212,"cate_id":0,"title":"韩语学习","picurl":"http://api.qunxianghui.com/upload/video/20180813/a283f9f306e6f40b619e8bdff7acfba9.jpg","description":"韩语学习","video_url":"http://api.qunxianghui.com/upload/video/20180813/fb9a9c3127aab412ef31308980fc9a57.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180609/e87f546ca9bd619cc9d4927a5e9097f2.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528520502,"ip":"113.215.161.0","status":1,"view_cnt":5,"source":"","images":"","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521862006.html"},{"id":390,"uuid":1521862183,"channel_id":0,"member_id":1000113,"cate_id":0,"title":"花儿","picurl":"http://api.qunxianghui.com/upload/video/20180813/a283f9f306e6f40b619e8bdff7acfba9.jpg","description":"","video_url":"http://api.qunxianghui.com/upload/video/20180813/fb9a9c3127aab412ef31308980fc9a57.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180610/4cc83dafbb0254ed235c8914eecee6f2.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528614169,"ip":"60.186.177.234","status":1,"view_cnt":3,"source":"","images":"","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521862183.html"},{"id":454,"uuid":1521863245,"channel_id":0,"member_id":1000027,"cate_id":1,"title":"今年年内","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180815/5b190259da4078ea245ec2b13e6bd6a4f6769d79","description":"九记牛腩呢","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/2176b7e82d76ea375a6a0b7194bdba52616c46d0","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/2176b7e82d76ea375a6a0b7194bdba52616c46d0\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1534300852,"ip":"192.168.100.6","status":1,"view_cnt":0,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521863245.html"},{"id":472,"uuid":1521863514,"channel_id":0,"member_id":1000029,"cate_id":1,"title":"123","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180815/8cab72034e25fe45080163ec2f4643c839f18646.jpg","description":"123","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/6c12183da9f229e4f46ddf90a7aeb2fc375d3bd8.mp4","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/6c12183da9f229e4f46ddf90a7aeb2fc375d3bd8.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1534330736,"ip":"192.168.100.6","status":1,"view_cnt":1,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521863514.html"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detail : {"id":499,"uuid":1521863942,"channel_id":0,"member_id":1000010,"cate_id":1,"title":"菊姐","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png","description":"头像","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":"2018-08-20 15:18:16","ip":"115.192.223.11","status":1,"view_cnt":30,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"member_name":"臭居居的汇归来","member_avatar":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180817/bc2da3e494897ea5de79b2bc85e3e18b.jpeg","share_pic":"http://api.qunxianghui.com.cnhttp://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png","share_desc":"头像"}
         * comment : []
         * person : {"like_info":0,"collect_res":0,"follow":0}
         * rand_data : [{"id":372,"uuid":1521862006,"channel_id":0,"member_id":1000212,"cate_id":0,"title":"韩语学习","picurl":"http://api.qunxianghui.com/upload/video/20180813/a283f9f306e6f40b619e8bdff7acfba9.jpg","description":"韩语学习","video_url":"http://api.qunxianghui.com/upload/video/20180813/fb9a9c3127aab412ef31308980fc9a57.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180609/e87f546ca9bd619cc9d4927a5e9097f2.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528520502,"ip":"113.215.161.0","status":1,"view_cnt":5,"source":"","images":"","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521862006.html"},{"id":390,"uuid":1521862183,"channel_id":0,"member_id":1000113,"cate_id":0,"title":"花儿","picurl":"http://api.qunxianghui.com/upload/video/20180813/a283f9f306e6f40b619e8bdff7acfba9.jpg","description":"","video_url":"http://api.qunxianghui.com/upload/video/20180813/fb9a9c3127aab412ef31308980fc9a57.mp4","content":"<iframe height=\"298\" src=\"http://api.qunxianghui.com.cn/upload/video/20180610/4cc83dafbb0254ed235c8914eecee6f2.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1528614169,"ip":"60.186.177.234","status":1,"view_cnt":3,"source":"","images":"","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521862183.html"},{"id":454,"uuid":1521863245,"channel_id":0,"member_id":1000027,"cate_id":1,"title":"今年年内","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180815/5b190259da4078ea245ec2b13e6bd6a4f6769d79","description":"九记牛腩呢","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/2176b7e82d76ea375a6a0b7194bdba52616c46d0","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/2176b7e82d76ea375a6a0b7194bdba52616c46d0\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1534300852,"ip":"192.168.100.6","status":1,"view_cnt":0,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521863245.html"},{"id":472,"uuid":1521863514,"channel_id":0,"member_id":1000029,"cate_id":1,"title":"123","picurl":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180815/8cab72034e25fe45080163ec2f4643c839f18646.jpg","description":"123","video_url":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/6c12183da9f229e4f46ddf90a7aeb2fc375d3bd8.mp4","content":"<iframe height=\"298\" src=\"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180815/6c12183da9f229e4f46ddf90a7aeb2fc375d3bd8.mp4\" frameborder=\"0\" width=\"100%\"><\/iframe>","like_cnt":0,"comment_cnt":0,"ctime":1534330736,"ip":"192.168.100.6","status":1,"view_cnt":1,"source":"0","images":"0","forward_cnt":0,"share_cnt":0,"channel_name":"本地","url":"http://fx.test.gongxianghui.net/detail/index/id/1521863514.html"}]
         */

        private DetailBean detail;
        private PersonBean person;
        private List<?> comment;
        private List<RandDataBean> rand_data;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public PersonBean getPerson() {
            return person;
        }

        public void setPerson(PersonBean person) {
            this.person = person;
        }

        public List<?> getComment() {
            return comment;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
        }

        public List<RandDataBean> getRand_data() {
            return rand_data;
        }

        public void setRand_data(List<RandDataBean> rand_data) {
            this.rand_data = rand_data;
        }

        public static class DetailBean {
            /**
             * id : 499
             * uuid : 1521863942
             * channel_id : 0
             * member_id : 1000010
             * cate_id : 1
             * title : 菊姐
             * picurl : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png
             * description : 头像
             * video_url : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4
             * content : <iframe height="298" src="http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180820/e734caeea90fdb9e82a38f9479e9ef215ee488e7.mp4" frameborder="0" width="100%"></iframe>
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 2018-08-20 15:18:16
             * ip : 115.192.223.11
             * status : 1
             * view_cnt : 30
             * source : 0
             * images : 0
             * forward_cnt : 0
             * share_cnt : 0
             * member_name : 臭居居的汇归来
             * member_avatar : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180817/bc2da3e494897ea5de79b2bc85e3e18b.jpeg
             * share_pic : http://api.qunxianghui.com.cnhttp://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180820/e232cb755a42d93a0d061d5174f395d493b43adc.png
             * share_desc : 头像
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
            private String ctime;
            private String ip;
            private int status;
            private int view_cnt;
            private String source;
            private String images;
            private int forward_cnt;
            private int share_cnt;
            private String member_name;
            private String member_avatar;
            private String share_pic;
            private String share_desc;

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

            public String getShare_pic() {
                return share_pic;
            }

            public void setShare_pic(String share_pic) {
                this.share_pic = share_pic;
            }

            public String getShare_desc() {
                return share_desc;
            }

            public void setShare_desc(String share_desc) {
                this.share_desc = share_desc;
            }
        }

        public static class PersonBean {
            /**
             * like_info : 0
             * collect_res : 0
             * follow : 0
             */

            private int like_info;
            private int collect_res;
            private int follow;

            public int getLike_info() {
                return like_info;
            }

            public void setLike_info(int like_info) {
                this.like_info = like_info;
            }

            public int getCollect_res() {
                return collect_res;
            }

            public void setCollect_res(int collect_res) {
                this.collect_res = collect_res;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }
        }

        public static class RandDataBean {
            /**
             * id : 372
             * uuid : 1521862006
             * channel_id : 0
             * member_id : 1000212
             * cate_id : 0
             * title : 韩语学习
             * picurl : http://api.qunxianghui.com/upload/video/20180813/a283f9f306e6f40b619e8bdff7acfba9.jpg
             * description : 韩语学习
             * video_url : http://api.qunxianghui.com/upload/video/20180813/fb9a9c3127aab412ef31308980fc9a57.mp4
             * content : <iframe height="298" src="http://api.qunxianghui.com.cn/upload/video/20180609/e87f546ca9bd619cc9d4927a5e9097f2.mp4" frameborder="0" width="100%"></iframe>
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 1528520502
             * ip : 113.215.161.0
             * status : 1
             * view_cnt : 5
             * source :
             * images :
             * forward_cnt : 0
             * share_cnt : 0
             * channel_name : 本地
             * url : http://fx.test.gongxianghui.net/detail/index/id/1521862006.html
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
            private String channel_name;
            private String url;

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

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
