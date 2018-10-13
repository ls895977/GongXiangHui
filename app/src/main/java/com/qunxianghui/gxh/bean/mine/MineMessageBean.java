package com.qunxianghui.gxh.bean.mine;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MineMessageBean implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;

    public MineMessageBean(int itemType) {
        this.itemType = itemType;
    }

    /**
     * code : 200
     * message : 请求成功
     * data : [{"id":43,"type":7,"time":"3分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"comment_reply":"不错","uuid":1521866884,"detail":{"images":"","uuid":1521866512,"title":"哈哈","content":"会突然让他"}},{"id":42,"type":4,"time":"8分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"comment_reply":"不错","uuid":1521866881,"detail":{"images":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20181013/9c6a7c4b4e779cd0163f7405dcacf5e88422b80e.png","uuid":1521866880,"title":"不错的键盘","content":"对滴内容"}},{"id":41,"type":2,"time":"8分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"uuid":1521866880,"detail":{"images":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20181013/9c6a7c4b4e779cd0163f7405dcacf5e88422b80e.png","uuid":1521866880,"title":"不错的键盘","content":"对滴内容"}},{"id":28,"type":1,"time":"16小时前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"uuid":1521866856,"detail":{"images":"","uuid":1521866856,"title":"","content":"啦啦啦啦啦啦"}}]
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

    @Override
    public int getItemType() {
        return itemType;
    }

    public static class DataBean {
        /**
         * id : 43
         * type : 7
         * time : 3分钟前
         * member_avatar :
         * member_nick : 131****9507
         * member_id : 1000350
         * comment_reply : 不错
         * uuid : 1521866884
         * detail : {"images":"","uuid":1521866512,"title":"哈哈","content":"会突然让他"}
         */

        private int id;
        private int type;
        private String time;
        private String member_avatar;
        private String member_nick;
        private int member_id;
        private String comment_reply;
        private int uuid;
        private DetailBean detail;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getMember_nick() {
            return member_nick;
        }

        public void setMember_nick(String member_nick) {
            this.member_nick = member_nick;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getComment_reply() {
            return comment_reply;
        }

        public void setComment_reply(String comment_reply) {
            this.comment_reply = comment_reply;
        }

        public int getUuid() {
            return uuid;
        }

        public void setUuid(int uuid) {
            this.uuid = uuid;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * images :
             * uuid : 1521866512
             * title : 哈哈
             * content : 会突然让他
             */

            private String images;
            private int uuid;
            private String title;
            private String content;

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getUuid() {
                return uuid;
            }

            public void setUuid(int uuid) {
                this.uuid = uuid;
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
        }
    }
}
