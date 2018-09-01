package com.qunxianghui.gxh.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.qunxianghui.gxh.adapter.homeAdapter.SearchFragmentAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强
 * @time 2018/5/28  17:40
 * @desc 搜索实体类
 */
public class SearchBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"type":"1","list":[{"id":2376,"uuid":1521859427,"channel_id":43,"member_id":1000000,"title":"老婆把新房装的超级少女心，尤其是客厅太唯美，看的我好尴尬",
     * "picurl":"/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg","images":["http://api.qunxianghui.com.cn/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg"],"linkurl":"",
     * "description":"",
     * "content":"我们的新家装修是老婆一手操办的，因为刚好我们新开了个公司也在装修，所以只好分工合作，一人负责一边了。其实当初有考虑找装修公司来做，这样老婆也不会太累，但是老婆不同意，一是她自己对装修我们自己的家还挺有动力和兴趣的，二是觉得材料还是自己买用起来安心一点，最终我们找了靠谱的公司半包做的。我们家是一个小两层的户型，一楼客厅厨房，二楼卧室。当我看到装修好的新家的时候我的内心是拒绝的，也不是不好看，主要是太少女心爆棚了，你们说我一个堂堂七尺男儿，天天生活在一个粉嫩嫩的环境里叫什么事啊，都不敢叫朋友来家里玩了\u2026\u2026偏偏看着满心欢喜的老婆还的使劲夸她能干\u2026\u2026<br />\r\n<br />\r\n这是进门的玄关处，同志们，请注意墙上的壁纸，带花纹就算了，还是超级可爱的猴子花纹\u2026\u2026<br />\r\n<br />\r\n<p>\r\n\t<img src=\"/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg\" alt=\"\" />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n小碎花加蕾丝边的餐桌<br />\r\n<br />\r\n这电视墙兼职不忍直视了，分分钟有种少女变身的感觉有木有\u2026\u2026<br />","like_cnt":0,"comment_cnt":0,"view_cnt":450,"ctime":"2018-05-28 16:46:22","ip":"183.157.81.215","status":1,"source":"今日头条","tags":"","forward_cnt":0,"share_cnt":0,"url":"http://fx.qunxianghui.com.cn/detail/index/id/1521859427.html","channel_name":"家居","type":"1"}]}
     */

    private int code;
    private String message;
    /**
     * type : 1
     * list : [{"id":2376,"uuid":1521859427,"channel_id":43,"member_id":1000000,"title":"老婆把新房装的超级少女心，尤其是客厅太唯美，看的我好尴尬","picurl":"/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg",
     * "images":["http://api.qunxianghui.com.cn/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg"],"linkurl":"","description":"",
     * "content":"我们的新家装修是老婆一手操办的，因为刚好我们新开了个公司也在装修，所以只好分工合作，一人负责一边了。其实当初有考虑找装修公司来做，这样老婆也不会太累，但是老婆不同意，一是她自己对装修我们自己的家还挺有动力和兴趣的，二是觉得材料还是自己买用起来安心一点，最终我们找了靠谱的公司半包做的。我们家是一个小两层的户型，一楼客厅厨房，二楼卧室。当我看到装修好的新家的时候我的内心是拒绝的，也不是不好看，主要是太少女心爆棚了，你们说我一个堂堂七尺男儿，天天生活在一个粉嫩嫩的环境里叫什么事啊，都不敢叫朋友来家里玩了\u2026\u2026偏偏看着满心欢喜的老婆还的使劲夸她能干\u2026\u2026<br />\r\n<br />\r\n这是进门的玄关处，同志们，请注意墙上的壁纸，带花纹就算了，还是超级可爱的猴子花纹\u2026\u2026<br />\r\n<br />\r\n<p>\r\n\t<img src=\"/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg\" alt=\"\" />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n小碎花加蕾丝边的餐桌<br />\r\n<br />\r\n这电视墙兼职不忍直视了，分分钟有种少女变身的感觉有木有\u2026\u2026<br />","like_cnt":0,"comment_cnt":0,"view_cnt":450,"ctime":"2018-05-28 16:46:22","ip":"183.157.81.215","status":1,"source":"今日头条","tags":"","forward_cnt":0,"share_cnt":0,"url":"http://fx.qunxianghui.com.cn/detail/index/id/1521859427.html","channel_name":"家居","type":"1"}]
     */

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

    public static class DataBean implements Serializable {
        private String type;
        /**
         * id : 2376
         * uuid : 1521859427
         * channel_id : 43
         * member_id : 1000000
         * title : 老婆把新房装的超级少女心，尤其是客厅太唯美，看的我好尴尬
         * picurl : /upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg
         * images : ["http://api.qunxianghui.com.cn/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg"]
         * linkurl :
         * description :
         * content :
         * 我们的新家装修是老婆一手操办的，因为刚好我们新开了个公司也在装修，所以只好分工合作，一人负责一边了。其实当初有考虑找装修公司来做，这样老婆也不会太累，但是老婆不同意，一是她自己对装修我们自己的家还挺有动力和兴趣的，二是觉得材料还是自己买用起来安心一点，最终我们找了靠谱的公司半包做的。我们家是一个小两层的户型，一楼客厅厨房，二楼卧室。当我看到装修好的新家的时候我的内心是拒绝的，也不是不好看，主要是太少女心爆棚了，你们说我一个堂堂七尺男儿，天天生活在一个粉嫩嫩的环境里叫什么事啊，都不敢叫朋友来家里玩了……偏偏看着满心欢喜的老婆还的使劲夸她能干……<br />
         * <br />
         * 这是进门的玄关处，同志们，请注意墙上的壁纸，带花纹就算了，还是超级可爱的猴子花纹……<br />
         * <br />
         * <p>
         * <img src="/upload/sys/image/3b/56520b90ca31802a97fee35848caf4.jpg" alt="" />
         * </p>
         * <p>
         * <br />
         * </p>
         * 小碎花加蕾丝边的餐桌<br />
         * <br />
         * 这电视墙兼职不忍直视了，分分钟有种少女变身的感觉有木有……<br />
         * like_cnt : 0
         * comment_cnt : 0
         * view_cnt : 450
         * ctime : 2018-05-28 16:46:22
         * ip : 183.157.81.215
         * status : 1
         * source : 今日头条
         * tags :
         * forward_cnt : 0
         * share_cnt : 0
         * url : http://fx.qunxianghui.com.cn/detail/index/id/1521859427.html
         * channel_name : 家居
         * type : 1
         */

        private List<ListBean> list;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable, MultiItemEntity {
            private int id;
            private int uuid;
            private int channel_id;
            private int member_id;
            private String title;
            private String picurl;
            private String linkurl;
            private String description;
            private String content;
            private int like_cnt;
            private int comment_cnt;
            private int view_cnt;
            private String ctime;
            private String ip;
            private int status;
            private String source;
            private String tags;
            private int forward_cnt;
            private int share_cnt;
            private String url;
            private String channel_name;
            private String type;
            private ArrayList<String> images;

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

            public String getLinkurl() {
                return linkurl;
            }

            public void setLinkurl(String linkurl) {
                this.linkurl = linkurl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public int getView_cnt() {
                return view_cnt;
            }

            public void setView_cnt(int view_cnt) {
                this.view_cnt = view_cnt;
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

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public ArrayList<String> getImages() {
                return images;
            }

            public void setImages(ArrayList<String> images) {
                this.images = images;
            }

            @Override
            public String toString() {
                return "ListBean{" + "id=" + id + ", uuid=" + uuid + ", channel_id=" + channel_id + ", member_id=" + member_id + ", title='" + title + '\'' + ", picurl='" + picurl + '\'' + ", " +
                        "linkurl='" + linkurl + '\'' + ", description='" + description + '\'' + ", content='" + content + '\'' + ", like_cnt=" + like_cnt + ", comment_cnt=" + comment_cnt + ", " +
                        "view_cnt=" + view_cnt + ", ctime='" + ctime + '\'' + ", ip='" + ip + '\'' + ", status=" + status + ", source='" + source + '\'' + ", tags='" + tags + '\'' + ", " +
                        "forward_cnt=" + forward_cnt + ", share_cnt=" + share_cnt + ", url='" + url + '\'' + ", channel_name='" + channel_name + '\'' + ", type='" + type + '\'' + ", images=" +
                        images + '}';
            }

            @Override
            public int getItemType() {

                switch (images.size()) {

                    case 0:
                        return SearchFragmentAdapter.TYPE_0;
                    case 1:
                        return SearchFragmentAdapter.TYPE_1;
                    case 2:
                        return SearchFragmentAdapter.TYPE_2;
                    case 3:
                        return SearchFragmentAdapter.TYPE_3;
                }
                return SearchFragmentAdapter.TYPE_0;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "type='" + type + '\'' + ", list=" + list + '}';
        }
    }

    @Override
    public String toString() {
        return "SearchBean{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
