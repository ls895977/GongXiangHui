package com.qunxianghui.gxh.bean.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeLunBoBean {

    /**
     * code : 0
     * message :
     * data : [{"aid":17,"title":"权威媒体报道","pid":14,"typeid":5,"province_id":3,"city_id":26,"area_id":265,"url":"","introduce":"","hits":279,"ctime":1526452740,"etime":1527076478,"starttime":1526400000,"endtime":1589558400,"stat":0,"note":"","text_name":"","text_url":"","text_title":"","text_style":"","image_src":"http://api.qunxianghui.com.cn/upload/sys/image/e7/e16280a717c038d45e57bfccd81d17.png","image_url":"http://www.qunxianghui.cn/","listorder":0,"status":1},{"aid":16,"title":"招商会结束片","pid":14,"typeid":5,"province_id":3,"city_id":26,"area_id":265,"url":"","introduce":"","hits":326,"ctime":1526113089,"etime":1527008561,"starttime":1526659200,"endtime":1620748800,"stat":1,"note":"","text_name":"招商会结束片","text_url":"http://www.qunxianghui.cn","text_title":"","text_style":"","image_src":"http://api.qunxianghui.com.cn/upload/sys/image/cd/709b992a19561fe341ba2fd04c2945.png","image_url":"http://www.qunxianghui.cn","listorder":1,"status":1},{"aid":1,"title":"网站首页图片轮播1","pid":14,"typeid":5,"province_id":3,"city_id":26,"area_id":265,"url":"http://www.destoon.com/","introduce":"","hits":0,"ctime":1519394912,"etime":1527008521,"starttime":1262275200,"endtime":1577808000,"stat":0,"note":"","text_name":"","text_url":"http://www.qunxianghui.cn","text_title":"","text_style":"","image_src":"http://api.qunxianghui.com.cn/upload/sys/image/bf/528563f71e63854b619ece91534db3.jpg","image_url":"http://www.qunxianghui.cn","listorder":2,"status":1},{"aid":2,"title":"网站首页图片轮播2","pid":14,"typeid":5,"province_id":3,"city_id":26,"area_id":265,"url":"http://www.destoon.com/","introduce":"","hits":0,"ctime":1519394912,"etime":1527008497,"starttime":1262275200,"endtime":1577808000,"stat":0,"note":"","text_name":"","text_url":"http://www.qunxianghui.cn","text_title":"","text_style":"","image_src":"http://api.qunxianghui.com.cn/upload/sys/image/4f/d4420d97e8b4250e089a4c46372e85.jpg","image_url":"http://www.qunxianghui.cn","listorder":2,"status":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static HomeLunBoBean objectFromData(String str) {

        return new Gson().fromJson(str, HomeLunBoBean.class);
    }

    public static List<HomeLunBoBean> arrayHomeLunBoFromData(String str) {

        Type listType = new TypeToken<ArrayList<HomeLunBoBean>>() {
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
         * aid : 17
         * title : 权威媒体报道
         * pid : 14
         * typeid : 5
         * province_id : 3
         * city_id : 26
         * area_id : 265
         * url :
         * introduce :
         * hits : 279
         * ctime : 1526452740
         * etime : 1527076478
         * starttime : 1526400000
         * endtime : 1589558400
         * stat : 0
         * note :
         * text_name :
         * text_url :
         * text_title :
         * text_style :
         * image_src : http://api.qunxianghui.com.cn/upload/sys/image/e7/e16280a717c038d45e57bfccd81d17.png
         * image_url : http://www.qunxianghui.cn/
         * listorder : 0
         * status : 1
         */

        private int aid;
        private String title;
        private int pid;
        private int typeid;
        private int province_id;
        private int city_id;
        private int area_id;
        private String url;
        private String introduce;
        private int hits;
        private int ctime;
        private int etime;
        private int starttime;
        private int endtime;
        private int stat;
        private String note;
        private String text_name;
        private String text_url;
        private String text_title;
        private String text_style;
        private String image_src;
        private String image_url;
        private int listorder;
        private int status;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getEtime() {
            return etime;
        }

        public void setEtime(int etime) {
            this.etime = etime;
        }

        public int getStarttime() {
            return starttime;
        }

        public void setStarttime(int starttime) {
            this.starttime = starttime;
        }

        public int getEndtime() {
            return endtime;
        }

        public void setEndtime(int endtime) {
            this.endtime = endtime;
        }

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getText_name() {
            return text_name;
        }

        public void setText_name(String text_name) {
            this.text_name = text_name;
        }

        public String getText_url() {
            return text_url;
        }

        public void setText_url(String text_url) {
            this.text_url = text_url;
        }

        public String getText_title() {
            return text_title;
        }

        public void setText_title(String text_title) {
            this.text_title = text_title;
        }

        public String getText_style() {
            return text_style;
        }

        public void setText_style(String text_style) {
            this.text_style = text_style;
        }

        public String getImage_src() {
            return image_src;
        }

        public void setImage_src(String image_src) {
            this.image_src = image_src;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
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
    }
}
