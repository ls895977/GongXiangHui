package com.qunxianghui.gxh.bean.generalize;

import java.io.Serializable;
import java.util.List;

public class GeneraLizeCompanyPushBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"company_list":[{"id":78,"member_id":1000233,"be_member_id":1000233,"member_ad_id":0,"show_count":1,"company_name":"群享汇有限公司","description":"","company_trade":["IT"],"tel":"","mobile":"","qq":"","province_id":0,"city_id":0,"area_id":0,"address":"","images":"","linkname":"","content":"","ctime":1527926290,"view_cnt":4,"status":1,"push_id":0,"change_ad_id":447}]}
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CompanyListBean> company_list;

        public List<CompanyListBean> getCompany_list() {
            return company_list;
        }

        public void setCompany_list(List<CompanyListBean> company_list) {
            this.company_list = company_list;
        }

        public static class CompanyListBean {
            /**
             * id : 78
             * member_id : 1000233
             * be_member_id : 1000233
             * member_ad_id : 0
             * show_count : 1
             * company_name : 群享汇有限公司
             * description :
             * company_trade : ["IT"]
             * tel :
             * mobile :
             * qq :
             * province_id : 0
             * city_id : 0
             * area_id : 0
             * address :
             * images :
             * linkname :
             * content :
             * ctime : 1527926290
             * view_cnt : 4
             * status : 1
             * push_id : 0
             * change_ad_id : 447
             */

            private int id;
            private int member_id;
            private int be_member_id;
            private int member_ad_id;
            private int show_count;
            private String company_name;
            private String description;
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
            private int ctime;
            private int view_cnt;
            private int status;
            private int push_id;
            private int change_ad_id;
            private List<String> company_trade;

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

            public int getBe_member_id() {
                return be_member_id;
            }

            public void setBe_member_id(int be_member_id) {
                this.be_member_id = be_member_id;
            }

            public int getMember_ad_id() {
                return member_ad_id;
            }

            public void setMember_ad_id(int member_ad_id) {
                this.member_ad_id = member_ad_id;
            }

            public int getShow_count() {
                return show_count;
            }

            public void setShow_count(int show_count) {
                this.show_count = show_count;
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

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
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

            public List<String> getCompany_trade() {
                return company_trade;
            }

            public void setCompany_trade(List<String> company_trade) {
                this.company_trade = company_trade;
            }
        }
    }
}
