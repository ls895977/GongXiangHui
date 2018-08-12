package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CompanySetBean implements Serializable {
    private int code;
    private String message;
    private DataBean data;

    public static CompanySetBean objectFromData(String str) {

        return new Gson().fromJson(str, CompanySetBean.class);
    }

    public static List<CompanySetBean> arrayCompanySetBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CompanySetBean>>() {
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
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
        private int ctime;
        private int view_cnt;
        private int status;
        private int push_id;
        private int change_ad_id;
        private String company_trade_name;
        private String province_name;
        private String city_name;
        private String area_name;
        private List<ProvinceListBean> province_list;
        private List<CityListBean> city_list;
        private List<AreaListBean> area_list;
        private List<CateBean> cate;
        private List<String> images_img;
        private List<String> images_arr;

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

        public String getCompany_trade_name() {
            return company_trade_name;
        }

        public void setCompany_trade_name(String company_trade_name) {
            this.company_trade_name = company_trade_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public List<ProvinceListBean> getProvince_list() {
            return province_list;
        }

        public void setProvince_list(List<ProvinceListBean> province_list) {
            this.province_list = province_list;
        }

        public List<CityListBean> getCity_list() {
            return city_list;
        }

        public void setCity_list(List<CityListBean> city_list) {
            this.city_list = city_list;
        }

        public List<AreaListBean> getArea_list() {
            return area_list;
        }

        public void setArea_list(List<AreaListBean> area_list) {
            this.area_list = area_list;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<String> getImages_img() {
            return images_img;
        }

        public void setImages_img(List<String> images_img) {
            this.images_img = images_img;
        }

        public List<String> getImages_arr() {
            return images_arr;
        }

        public void setImages_arr(List<String> images_arr) {
            this.images_arr = images_arr;
        }

        public static class ProvinceListBean {
            /**
             * provinceid : 1
             * provincename : 直辖市
             * displayorder : 1
             */

            private int provinceid;
            private String provincename;
            private int displayorder;

            public static ProvinceListBean objectFromData(String str) {

                return new Gson().fromJson(str, ProvinceListBean.class);
            }

            public static List<ProvinceListBean> arrayProvinceListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ProvinceListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(int provinceid) {
                this.provinceid = provinceid;
            }

            public String getProvincename() {
                return provincename;
            }

            public void setProvincename(String provincename) {
                this.provincename = provincename;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }
        }

        public static class CityListBean {
            /**
             * cityid : 5
             * provinceid : 2
             * cityname : 广州
             * displayorder : 0
             * citypinyin : guangzhou
             */

            private int cityid;
            private int provinceid;
            private String cityname;
            private int displayorder;
            private String citypinyin;

            public static CityListBean objectFromData(String str) {

                return new Gson().fromJson(str, CityListBean.class);
            }

            public static List<CityListBean> arrayCityListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<CityListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getCityid() {
                return cityid;
            }

            public void setCityid(int cityid) {
                this.cityid = cityid;
            }

            public int getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(int provinceid) {
                this.provinceid = provinceid;
            }

            public String getCityname() {
                return cityname;
            }

            public void setCityname(String cityname) {
                this.cityname = cityname;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }

            public String getCitypinyin() {
                return citypinyin;
            }

            public void setCitypinyin(String citypinyin) {
                this.citypinyin = citypinyin;
            }
        }

        public static class AreaListBean {
            /**
             * areaid : 248
             * areaname : 湘桥
             * cityid : 23
             * displayorder : 1
             */

            private int areaid;
            private String areaname;
            private int cityid;
            private int displayorder;

            public static AreaListBean objectFromData(String str) {

                return new Gson().fromJson(str, AreaListBean.class);
            }

            public static List<AreaListBean> arrayAreaListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<AreaListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getAreaid() {
                return areaid;
            }

            public void setAreaid(int areaid) {
                this.areaid = areaid;
            }

            public String getAreaname() {
                return areaname;
            }

            public void setAreaname(String areaname) {
                this.areaname = areaname;
            }

            public int getCityid() {
                return cityid;
            }

            public void setCityid(int cityid) {
                this.cityid = cityid;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }
        }

        public static class CateBean {
            /**
             * id : 6
             * pid : 0
             * cate_name : 家政服务
             * listorder : 1
             * picurl :
             * status : 1
             */

            private int id;
            private int pid;
            private String cate_name;
            private int listorder;
            private String picurl;
            private int status;

            public static CateBean objectFromData(String str) {

                return new Gson().fromJson(str, CateBean.class);
            }

            public static List<CateBean> arrayCateBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<CateBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

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

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
