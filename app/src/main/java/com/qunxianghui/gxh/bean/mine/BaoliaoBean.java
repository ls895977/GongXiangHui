package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class BaoliaoBean implements Serializable {

    /**
     * code : 0
     * message : 获取成功
     * data : [{"id":201,"member_id":1000316,"title":"叫姐姐","content":"那你呢","ctime":"1小时前","ip":"113.215.160.215","status":"未审核","images":[]},{"id":200,"member_id":1000316,"title":"很膨胀","content":"膨胀啦辣辣","ctime":"2小时前","ip":"115.192.223.109","status":"未审核","images":[]},{"id":196,"member_id":1000316,"title":"cvcv","content":"ggg","ctime":"5小时前","ip":"113.215.160.215","status":"未审核","images":[]}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 201
         * member_id : 1000316
         * title : 叫姐姐
         * content : 那你呢
         * ctime : 1小时前
         * ip : 113.215.160.215
         * status : 未审核
         * images : []
         */

        private int id;
        private int member_id;
        private String title;
        private String content;
        private String ctime;
        private String ip;
        private String status;
        private List<String> images;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
