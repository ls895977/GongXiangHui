package com.qunxianghui.gxh.bean.home;

import java.util.List;

public class HomeVideoSortBean {

    /**
     * code : 200
     * msg : 请求成功
     * data : [{"id":1,"pid":0,"cate_name":"搞笑","listorder":0,"status":1,"ctime":0},{"id":2,"pid":0,"cate_name":"体育","listorder":0,"status":1,"ctime":0},{"id":3,"pid":0,"cate_name":"健美","listorder":0,"status":1,"ctime":0},{"id":4,"pid":0,"cate_name":"学习","listorder":0,"status":1,"ctime":0}]
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
         * id : 1
         * pid : 0
         * cate_name : 搞笑
         * listorder : 0
         * status : 1
         * ctime : 0
         */

        private int id;
        private int pid;
        private String cate_name;
        private int listorder;
        private int status;
        private int ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public int getListorder() {
            return listorder;
        }

        public void setListorder(int listorder) {
            this.listorder = listorder;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }
    }
}
