package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyCollectPostBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":427,"data_uuid":1521859989,"member_id":1000175,"ctime":"1天前","info":{"id":2693,"uuid":1521859989,"channel_id":35,"member_id":1000000,"title":"经典语录：虽然很辛苦，但是努力过真好","picurl":"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg","images":"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg","linkurl":"","description":"","content":"1、在一切变好之前，我们总要经历一些不开心的日子，这段日子也许很长，也许只是一觉醒来。有时候，选择快乐，更需要勇气。 <br />\r\n<br />\r\n2、愿你三冬暖，愿你春不寒； 愿你天黑有灯，下雨有伞； 愿你一路上，有良人相伴。<br />\r\n<br />\r\n3、无需匆忙，不要将就，缘分到了，就一定会在一起。 <br />\r\n<br />\r\n4、什么是一份好工作：一不影响生活作息，二不影响家庭团聚，三能养家糊口。<br />\r\n<br />\r\n5、走不通的路就回头，爱而不得的人就放手，得不到的热情就适可而止，别把一厢情愿当成满腔孤勇，也别把厌烦当成欲擒故纵 。<br />\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg\" alt=\"\" />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n6、被人理解是幸运的，但不被理解未必不幸。一个把自己的价值完全寄托于他人的理解上面的人往往并无价值。 <br />\r\n<br />\r\n7、 生命中总有那么一段时光，充满不安，可是除了勇敢面对，我们别无选择。 <br />\r\n<br />\r\n8、人生最好的三个词：久别重逢 失而复得 虚惊一场 <br />\r\n<br />\r\n9、人有一个最特别的弱点就是：在意别人如何看待自己。这一程，希望你活得烈马青葱，不为他人的目光所累。<br />\r\n<br />\r\n10、海上月是天上月，眼前人是心上人。 向来心是看客心，奈何人是剧中人。<br />\r\n<br />\r\n11、下雨了，才知道谁会给你送伞；遇事了，才知道谁对你真心。有些人，只会锦上添花，不会雪中送炭；有些人，只会火上浇油，不会坦诚相待。 <br />\r\n<br />\r\n12、人生本来就是一场即兴演出，没有做不成的梦，只有不早醒的人。 <br />\r\n<br />\r\n13、到底有多少人到现在还是不明白，人和人之间想要保持长久舒适的关系，靠的是共性和吸引。而不是压迫，捆绑，奉承，和一味的付出以及道德式的自我感动。 <br />\r\n<br />\r\n14、幸福就是健康的身体加上糊涂的记性。 <br />\r\n<br />\r\n15、每个人都有阴暗面、虚伪面，有什么好奇怪的，对不同的人不同态度，喜欢就交流，不喜就陌路。 <br />\r\n<br />\r\n16、一忆羞开一阙词，一念凋零一韵诗。一语含香一窈窕，一颦色舞一华池。一夜千般一世界，一日百感一交织。一抹红颜一妩媚，一笑回眸一片痴。<br />\r\n<br />\r\n17、道理都懂，但该怨的还是会怨，该骂的还是会骂，该哭的也还是会哭，毕竟心里的难受不是道理所能释怀的。<br />\r\n<br />\r\n18、人，其实不需要太多的东西，只要健康的活着，真诚的爱着，也不失为一种富有。 <br />\r\n<br />\r\n19、成长也许就是越来越沉默，就是将哭声调成静音的过程，把情绪收到别人看不到的地方，一个人学会坚强。<br />\r\n<br />\r\n20、一直努力着，适应着这个世界的温度，不管是季节还是人心。<br />","like_cnt":0,"comment_cnt":0,"view_cnt":2780,"ctime":1527749740,"ip":"113.215.161.106","status":1,"source":"今日头条","tags":"","forward_cnt":3,"share_cnt":3},"images":["http://api.qunxianghui.com.cn/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg"],"member":{"member_name":"共享云","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5acae2d635da1.jpeg","follow":""},"newctime":"1天前"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MyCollectPostBean objectFromData(String str) {

        return new Gson().fromJson(str, MyCollectPostBean.class);
    }

    public static List<MyCollectPostBean> arrayMyCollectPostBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyCollectPostBean>>() {
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
         * id : 427
         * data_uuid : 1521859989
         * member_id : 1000175
         * ctime : 1天前
         * info : {"id":2693,"uuid":1521859989,"channel_id":35,"member_id":1000000,"title":"经典语录：虽然很辛苦，但是努力过真好","picurl":"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg","images":"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg","linkurl":"","description":"","content":"1、在一切变好之前，我们总要经历一些不开心的日子，这段日子也许很长，也许只是一觉醒来。有时候，选择快乐，更需要勇气。 <br />\r\n<br />\r\n2、愿你三冬暖，愿你春不寒； 愿你天黑有灯，下雨有伞； 愿你一路上，有良人相伴。<br />\r\n<br />\r\n3、无需匆忙，不要将就，缘分到了，就一定会在一起。 <br />\r\n<br />\r\n4、什么是一份好工作：一不影响生活作息，二不影响家庭团聚，三能养家糊口。<br />\r\n<br />\r\n5、走不通的路就回头，爱而不得的人就放手，得不到的热情就适可而止，别把一厢情愿当成满腔孤勇，也别把厌烦当成欲擒故纵 。<br />\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg\" alt=\"\" />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n6、被人理解是幸运的，但不被理解未必不幸。一个把自己的价值完全寄托于他人的理解上面的人往往并无价值。 <br />\r\n<br />\r\n7、 生命中总有那么一段时光，充满不安，可是除了勇敢面对，我们别无选择。 <br />\r\n<br />\r\n8、人生最好的三个词：久别重逢 失而复得 虚惊一场 <br />\r\n<br />\r\n9、人有一个最特别的弱点就是：在意别人如何看待自己。这一程，希望你活得烈马青葱，不为他人的目光所累。<br />\r\n<br />\r\n10、海上月是天上月，眼前人是心上人。 向来心是看客心，奈何人是剧中人。<br />\r\n<br />\r\n11、下雨了，才知道谁会给你送伞；遇事了，才知道谁对你真心。有些人，只会锦上添花，不会雪中送炭；有些人，只会火上浇油，不会坦诚相待。 <br />\r\n<br />\r\n12、人生本来就是一场即兴演出，没有做不成的梦，只有不早醒的人。 <br />\r\n<br />\r\n13、到底有多少人到现在还是不明白，人和人之间想要保持长久舒适的关系，靠的是共性和吸引。而不是压迫，捆绑，奉承，和一味的付出以及道德式的自我感动。 <br />\r\n<br />\r\n14、幸福就是健康的身体加上糊涂的记性。 <br />\r\n<br />\r\n15、每个人都有阴暗面、虚伪面，有什么好奇怪的，对不同的人不同态度，喜欢就交流，不喜就陌路。 <br />\r\n<br />\r\n16、一忆羞开一阙词，一念凋零一韵诗。一语含香一窈窕，一颦色舞一华池。一夜千般一世界，一日百感一交织。一抹红颜一妩媚，一笑回眸一片痴。<br />\r\n<br />\r\n17、道理都懂，但该怨的还是会怨，该骂的还是会骂，该哭的也还是会哭，毕竟心里的难受不是道理所能释怀的。<br />\r\n<br />\r\n18、人，其实不需要太多的东西，只要健康的活着，真诚的爱着，也不失为一种富有。 <br />\r\n<br />\r\n19、成长也许就是越来越沉默，就是将哭声调成静音的过程，把情绪收到别人看不到的地方，一个人学会坚强。<br />\r\n<br />\r\n20、一直努力着，适应着这个世界的温度，不管是季节还是人心。<br />","like_cnt":0,"comment_cnt":0,"view_cnt":2780,"ctime":1527749740,"ip":"113.215.161.106","status":1,"source":"今日头条","tags":"","forward_cnt":3,"share_cnt":3}
         * images : ["http://api.qunxianghui.com.cn/upload/sys/image/ab/e1baec8965d34eb16c1dcb3c8d0620.jpg"]
         * member : {"member_name":"共享云","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5acae2d635da1.jpeg","follow":""}
         * newctime : 1天前
         */

        private int id;
        private int data_uuid;
        private int member_id;
        private String ctime;
        private InfoBean info;
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
            private int id;
            private int uuid;
            private int channel_id;
            private int member_id;
            private String title;
            private String picurl;
            private String images;
            private String linkurl;
            private String description;
            private String content;
            private int like_cnt;
            private int comment_cnt;
            private int view_cnt;
            private int ctime;
            private String ip;
            private int status;
            private String source;
            private String tags;
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

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
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
        }

        public static class MemberBean {
            /**
             * member_name : 共享云
             * member_avatar : http://api.qunxianghui.com.cn/upload/posts/5acae2d635da1.jpeg
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
