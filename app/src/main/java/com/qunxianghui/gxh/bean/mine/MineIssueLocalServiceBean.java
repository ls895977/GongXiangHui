package com.qunxianghui.gxh.bean.mine;

import java.util.List;

public class MineIssueLocalServiceBean {

    /**
     * code : 200
     * msg : 获取数据成功
     * data : [{"id":19,"member_id":1000038,"company_name":"杭州思丹莱培训学校","description":"中小学学科培优、辅导选择思丹莱","company_trade":"65","tel":"4008584452","mobile":"18506825451","qq":"5632486548","province_id":3,"city_id":26,"area_id":267,"address":"蓝桥景苑","images":"/upload/posts/5ad01d9ce17a3.jpeg","linkname":"陈老师","content":"思丹莱培训学校，是杭州市教育局和民政局批准备案的中小学校外优质培训机构。是杭州市AAAA级单位，每年有多达3000余人次的学生在这里培训，强大的师资力量和严格的教学管理，60%以上的学生先后进入各自心仪的重优高。就保送生而言，几年来，近500名保送生经我校培训后，全部进入各重点高中，赢得社会的广泛认可和高度评价。","ctime":"2018-04-13 11:11:04","view_cnt":11,"status":1,"push_id":0,"change_ad_id":0,"cate_name":"教育培训/家教机构/IT"}]
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
         * id : 19
         * member_id : 1000038
         * company_name : 杭州思丹莱培训学校
         * description : 中小学学科培优、辅导选择思丹莱
         * company_trade : 65
         * tel : 4008584452
         * mobile : 18506825451
         * qq : 5632486548
         * province_id : 3
         * city_id : 26
         * area_id : 267
         * address : 蓝桥景苑
         * images : /upload/posts/5ad01d9ce17a3.jpeg
         * linkname : 陈老师
         * content : 思丹莱培训学校，是杭州市教育局和民政局批准备案的中小学校外优质培训机构。是杭州市AAAA级单位，每年有多达3000余人次的学生在这里培训，强大的师资力量和严格的教学管理，60%以上的学生先后进入各自心仪的重优高。就保送生而言，几年来，近500名保送生经我校培训后，全部进入各重点高中，赢得社会的广泛认可和高度评价。
         * ctime : 2018-04-13 11:11:04
         * view_cnt : 11
         * status : 1
         * push_id : 0
         * change_ad_id : 0
         * cate_name : 教育培训/家教机构/IT
         */

        private int id;
        private int member_id;
        private String company_name;
        private String description;
        private String company_trade;
        private String tel;
        private String mobile;
        private String qq;
        private int province_id;
        private int city_id;
        private int area_id;
        private String address;
        private String images;
        private String linkname;
        private String content;
        private String ctime;
        private int view_cnt;
        private int status;
        private int push_id;
        private int change_ad_id;
        private String cate_name;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
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

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCompany_trade() {
            return company_trade;
        }

        public void setCompany_trade(String company_trade) {
            this.company_trade = company_trade;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
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

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPush_id() {
            return push_id;
        }

        public void setPush_id(int push_id) {
            this.push_id = push_id;
        }

        public int getChange_ad_id() {
            return change_ad_id;
        }

        public void setChange_ad_id(int change_ad_id) {
            this.change_ad_id = change_ad_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
