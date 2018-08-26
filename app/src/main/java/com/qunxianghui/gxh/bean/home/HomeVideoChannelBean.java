package com.qunxianghui.gxh.bean.home;

import com.qunxianghui.gxh.db.ChannelItem;

import java.util.ArrayList;

public class HomeVideoChannelBean {
    /**
     * code : 200
     * msg : 请求成功
     * data : {"added":[{"id":0,"cate_name":"实时"}],"others":[{"id":1,"pid":0,"cate_name":"搞笑","listorder":0,"status":1,"ctime":0},{"id":2,"pid":0,"cate_name":"体育","listorder":0,"status":1,"ctime":0},{"id":3,"pid":0,"cate_name":"健美","listorder":0,"status":1,"ctime":0},{"id":4,"pid":0,"cate_name":"学习","listorder":0,"status":1,"ctime":0}]}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        public ArrayList<ChannelItem> added;
        public ArrayList<ChannelItem> others;

//        public static class ChannelBean {
//            /**
//             * id : 1
//             * pid : 0
//             * cate_name : 搞笑
//             * listorder : 0
//             * status : 1
//             * ctime : 0
//             */
//            public int id;
//            public int pid;
//            public String cate_name;
//            public int listorder;
//            public int status;
//            public int ctime;
//        }
    }
}
