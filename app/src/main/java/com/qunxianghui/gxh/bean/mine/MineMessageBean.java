package com.qunxianghui.gxh.bean.mine;

import java.util.List;

public class MineMessageBean {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public int itemType;

    public MineMessageBean(int itemType) {
        this.itemType = itemType;
    }

    /**
     * code : 200
     * message : 请求成功
     * data : [{"id":43,"type":7,"time":"3分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"comment_reply":"不错","uuid":1521866884,"detail":{"images":"","uuid":1521866512,"title":"哈哈","content":"会突然让他"}},{"id":42,"type":4,"time":"8分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"comment_reply":"不错","uuid":1521866881,"detail":{"images":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20181013/9c6a7c4b4e779cd0163f7405dcacf5e88422b80e.png","uuid":1521866880,"title":"不错的键盘","content":"对滴内容"}},{"id":41,"type":2,"time":"8分钟前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"uuid":1521866880,"detail":{"images":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20181013/9c6a7c4b4e779cd0163f7405dcacf5e88422b80e.png","uuid":1521866880,"title":"不错的键盘","content":"对滴内容"}},{"id":28,"type":1,"time":"16小时前","member_avatar":"","member_nick":"131****9507","member_id":1000350,"uuid":1521866856,"detail":{"images":"","uuid":1521866856,"title":"","content":"啦啦啦啦啦啦"}}]
     */

    public int code;
    public String message;
    public List<DataBean> data;

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

        public int id;
        public int detail_deleted;
        public int comment_deleted;
        public int type;
        public String time;
        public String member_avatar;
        public String member_nick;
        public int member_id;
        public String comment_reply;
        public int uuid;
        public DetailBean detail;

        public static class DetailBean {
            /**
             * images :
             * uuid : 1521866512
             * title : 哈哈
             * content : 会突然让他
             */

            public String images;
            public int uuid;
            public String title;
            public String content;
        }
    }
}
