package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class AddAdvanceBean implements Serializable {


    /**
     * code : 200
     * msg :
     * data : [{"aboutus_id":48,"title":"第","describe":"我是一个发疯了减肥是垃圾分类是","image_array":["http://api.qunxianghui.com/upload/aboutus/20180805/806183561e673722581b401b9006cf6c.png","http://api.qunxianghui.com/upload/aboutus/20180805/a92fadfc96658b21bcf2301d94da1887.png"]}]
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
         * aboutus_id : 48
         * title : 第
         * describe : 我是一个发疯了减肥是垃圾分类是
         * image_array : ["http://api.qunxianghui.com/upload/aboutus/20180805/806183561e673722581b401b9006cf6c.png","http://api.qunxianghui.com/upload/aboutus/20180805/a92fadfc96658b21bcf2301d94da1887.png"]
         */

        private int aboutus_id;
        private String title;
        private String describe;
        private List<String> image_array;

        public int getAboutus_id() {
            return aboutus_id;
        }

        public void setAboutus_id(int aboutus_id) {
            this.aboutus_id = aboutus_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public List<String> getImage_array() {
            return image_array;
        }

        public void setImage_array(List<String> image_array) {
            this.image_array = image_array;
        }
    }
}
