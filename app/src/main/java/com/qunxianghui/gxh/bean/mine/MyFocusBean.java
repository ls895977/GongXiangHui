package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class MyFocusBean implements Serializable {

    /**
     * code : 200
     * msg : 获取数据成功
     * data : [{"id":275,"member_id":1000010,"be_member_id":1000302,"member_name":"汉化版","member_avatar":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180816/255b3b7acbc5dfe9da0b51bdf9d8d206.jpeg","member_remark":"兔兔","level_id":1,"level_type":"1","follow_type":1},{"id":271,"member_id":1000010,"be_member_id":1000093,"member_name":"180****2445","member_avatar":"","member_remark":"","level_id":1,"level_type":"0","follow_type":1},{"id":270,"member_id":1000010,"be_member_id":1000027,"member_name":"小雷真是","member_avatar":"http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180816/4d9b03c97551b60f1b1cde55b5fc5bb6.jpeg","member_remark":"aaa","level_id":1,"level_type":"1","follow_type":0},{"id":202,"member_id":1000010,"be_member_id":1000029,"member_name":"飙分狂人","member_avatar":"http://api.test.gongxianghui.net/upload/posts/5ad597cd5ac2e.jpg","member_remark":"","level_id":1,"level_type":"1","follow_type":0},{"id":197,"member_id":1000010,"be_member_id":1000033,"member_name":"中国好吃货","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180519/de960748b264dfe49d7c0586721c27f7.png","member_remark":"","level_id":1,"level_type":"1","follow_type":0}]
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
         * id : 275
         * member_id : 1000010
         * be_member_id : 1000302
         * member_name : 汉化版
         * member_avatar : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180816/255b3b7acbc5dfe9da0b51bdf9d8d206.jpeg
         * member_remark : 兔兔
         * level_id : 1
         * level_type : 1
         * follow_type : 1
         */

        private int id;
        private int member_id;
        private int be_member_id;
        private String member_name;
        private String member_avatar;
        private String member_remark;
        private int level_id;
        private String level_type;
        private int follow_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getBe_member_id() {
            return be_member_id;
        }

        public void setBe_member_id(int be_member_id) {
            this.be_member_id = be_member_id;
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

        public String getMember_remark() {
            return member_remark;
        }

        public void setMember_remark(String member_remark) {
            this.member_remark = member_remark;
        }

        public int getLevel_id() {
            return level_id;
        }

        public void setLevel_id(int level_id) {
            this.level_id = level_id;
        }

        public String getLevel_type() {
            return level_type;
        }

        public void setLevel_type(String level_type) {
            this.level_type = level_type;
        }

        public int getFollow_type() {
            return follow_type;
        }

        public void setFollow_type(int follow_type) {
            this.follow_type = follow_type;
        }
    }
}
