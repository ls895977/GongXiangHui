package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyIssueDiscloseBean implements Serializable {

    /**
     * code : 0
     * message : 获取成功
     * data : [{"id":138,"member_id":1000175,"title":"第一张有图片的","content":"不是很多","ctime":"刚刚","ip":"113.215.161.231","status":"未审核","images":["http://api.qunxianghui.com.cn/upload/images/20180604/f7d8c772a6291bab302a4aa31a122239.png","http://api.qunxianghui.com.cn/upload/images/20180604/8ae3228d1d4772cd223d7ee6a3f77c21.png"]},{"id":136,"member_id":1000175,"title":"啦啦啦啦","content":"啦啦啦啦啦啦","ctime":"2天前","ip":"27.219.158.128","status":"未审核","images":[]},{"id":111,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":110,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":109,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":108,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":107,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":106,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":105,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":104,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":103,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":102,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":101,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":100,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":99,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":98,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":97,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":96,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":95,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":94,"member_id":1000175,"title":"aa","content":"aa\ndfseds","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":93,"member_id":1000175,"title":"aa","content":"aa\ndfseds","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":92,"member_id":1000175,"title":"aa","content":"aa\ndfseds","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":91,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":90,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":89,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":88,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":87,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":86,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":85,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":84,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":83,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]},{"id":82,"member_id":1000175,"title":"aa","content":"aa","ctime":"4天前","ip":"183.157.81.215","status":"未审核","images":[]}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static MyIssueDiscloseBean objectFromData(String str) {

        return new Gson().fromJson(str, MyIssueDiscloseBean.class);
    }

    public static List<MyIssueDiscloseBean> arrayMyIssueDiscloseFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyIssueDiscloseBean>>() {
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
         * id : 138
         * member_id : 1000175
         * title : 第一张有图片的
         * content : 不是很多
         * ctime : 刚刚
         * ip : 113.215.161.231
         * status : 未审核
         * images : ["http://api.qunxianghui.com.cn/upload/images/20180604/f7d8c772a6291bab302a4aa31a122239.png","http://api.qunxianghui.com.cn/upload/images/20180604/8ae3228d1d4772cd223d7ee6a3f77c21.png"]
         */

        private int id;
        private int member_id;
        private String title;
        private String content;
        private String ctime;
        private String ip;
        private String status;
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
