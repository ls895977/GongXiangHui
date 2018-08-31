package com.qunxianghui.gxh.bean.location;

public class ReplyCommentResponseBean {


    /**
     * code : 0
     * msg : 评论添加成功
     * data : {"uuid":"1521863667","com_one_res":{"id":1092,"comment_id":0,"pid":0,"uuid":1521863675,"data_uuid":1521863667,"member_id":1000304,"content":"ii","ctime":1534499929,"ip":"118.114.82.194","status":1,"read":0,"member_name":"173****3627","member_avatar":"","address":false}}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : 1521863667
         * com_one_res : {"id":1092,"comment_id":0,"pid":0,"uuid":1521863675,"data_uuid":1521863667,"member_id":1000304,"content":"ii","ctime":1534499929,"ip":"118.114.82.194","status":1,"read":0,"member_name":"173****3627","member_avatar":"","address":false}
         */

        private String uuid;
        private CommentBean com_one_res;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public CommentBean getCom_one_res() {
            return com_one_res;
        }

        public void setCom_one_res(CommentBean com_one_res) {
            this.com_one_res = com_one_res;
        }

        public static class ComOneResBean {

            /**
             * id : 1219
             * comment_id : 1127
             * pid : 0
             * uuid : 1521863821
             * data_uuid : 1521863726
             * member_id : 1000304
             * content : jp
             * ctime : 1534578152
             * ip : 118.114.82.194
             * status : 1
             * read : 0
             * member_name : 173****3627
             * member_avatar : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180817/a5d8fb828161e5b550cbe48115d05f64.jpeg
             * member_reply_name : 臭居居的归来
             */

            private int id;
            private int comment_id;
            private int pid;
            private int uuid;
            private int data_uuid;
            private int member_id;
            private String content;
            private int ctime;
            private String ip;
            private int status;
            private int read;
            private String member_name;
            private String member_avatar;
            private String member_reply_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getUuid() {
                return uuid;
            }

            public void setUuid(int uuid) {
                this.uuid = uuid;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getRead() {
                return read;
            }

            public void setRead(int read) {
                this.read = read;
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

            public String getMember_reply_name() {
                return member_reply_name;
            }

            public void setMember_reply_name(String member_reply_name) {
                this.member_reply_name = member_reply_name;
            }
        }
    }
}
