package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;



public class FireSearchBean implements Serializable {

    private static final long serialVersionUID = 6975411803819068357L;


    /**
     * errno : 0
     * data : [{"modelId":48,"name":"劳斯莱斯幻影"},{"modelId":45,"name":"玛莎拉蒂GranTurismo（GT）"},{"modelId":26,"name":"法拉利LaFerrari(拉法)"},{"modelId":32,"name":"法拉利California T（加州）"},{"modelId":37,"name":"迈凯伦570S"},{"modelId":39,"name":"保时捷911软顶敞篷"}]
     * message : 请求成功
     */

    private int errno;
    private String message;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
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
         * modelId : 48
         * name : 劳斯莱斯幻影
         */

        private int modelId;
        private String name;

        public int getModelId() {
            return modelId;
        }

        public void setModelId(int modelId) {
            this.modelId = modelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
