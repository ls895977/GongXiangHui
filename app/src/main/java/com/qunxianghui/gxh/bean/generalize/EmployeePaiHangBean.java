package com.qunxianghui.gxh.bean.generalize;

import java.io.Serializable;
import java.util.List;

public class EmployeePaiHangBean implements Serializable {

    public int code;
    public String msg;
    public EmployeePaiHang data;

    public static class EmployeePaiHang {

        public MyData my_data;
        public List<DataBean> staff_list;

        public static class MyData {
            public String member_name;
            public String member_avatar;
            public String cnt;
            public String ranking;
        }

        public static class DataBean {
            public int id;
            public int member_id;
            public int company_id;
            public int activecode;
            public int staff_id;
            public int ctime;
            public int status;
            public int expires_in;
            public String remark;
            public int active_time;
            public int area_limit;
            public String member_name;
            public String member_avatar;
            public String cnt;
        }

    }
}
