package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

public class GuessBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"uuid":1521856615,"title":"坚守的岁月，是一种缘"},{"uuid":1521857668,"title":"谷歌AI通过图灵测试：我们为什么要让机器像人一样？"},{"uuid":1521857987,"title":"南海出现小意外，越南拉拢俄罗斯开采石油，中方立场很强硬"}]
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

    public static class DataBean {
        /**
         * uuid : 1521856615
         * title : 坚守的岁月，是一种缘
         */

        private int uuid;
        private String title;

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
    }
}
