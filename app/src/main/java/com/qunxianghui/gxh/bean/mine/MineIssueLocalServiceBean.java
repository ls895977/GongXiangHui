package com.qunxianghui.gxh.bean.mine;

import java.util.List;

public class MineIssueLocalServiceBean {

    /**
     * code : 200
     * msg : 获取数据成功
     * data : [{"id":201,"member_id":1000010,"cate_id":31,"title":"yyyy","price":"52.00","province_id":2,"city_id":23,"area_id":251,"address":"西湖","content":"考虑考虑","images":null,"linkname":"赵龙涛","sex":1,"mobile":"13843854188","ctime":"2018-08-30 17:57:10","view_cnt":null,"status":null,"listorder":null,"image_url":null,"cate_name":"亲子"},{"id":77,"member_id":1000010,"cate_id":148,"title":"强盛登山杖折叠碳素超轻超短越野行走健走杖外锁伸缩5节户外爬山","price":"95.00","province_id":3,"city_id":31,"area_id":320,"address":"翡翠园","content":"<p><br/><\/p><p>强盛登山杖折叠碳素超轻 超短越野行走健走杖外锁伸缩5节户外爬山 可放入背包，超轻配外袋，收藏送护膝杖尖套<\/p><p><br/><\/p>","images":"/upload/sys/image/2e/35180f307baf311f6078af9ce325bc.jpg","linkname":"力强","sex":1,"mobile":"15774423369","ctime":"2018-04-14 15:20:54","view_cnt":45,"status":1,"listorder":0,"image_url":"","cate_name":"登山杖"},{"id":75,"member_id":1000010,"cate_id":140,"title":"国标纯铜芯RVV护套线2 3 4二芯0.5 0.75 1软1.5平方2.5电缆电源线","price":"95.00","province_id":2,"city_id":5,"area_id":73,"address":"稻香园南区","content":"国标纯铜芯RVV护套线2 3 4二芯0.5 0.75 1软1.5平方2.5电缆电源线","images":"/upload/sys/image/61/0ac6375371483091f27f11b7468000.jpg","linkname":"王国成","sex":1,"mobile":"17826332119","ctime":"2018-04-13 17:27:07","view_cnt":8,"status":1,"listorder":0,"image_url":"","cate_name":"电线"},{"id":73,"member_id":1000010,"cate_id":147,"title":"定制笔记本文具记事本A5加厚小清新大学生日记本子办公学生用商务","price":"8.50","province_id":3,"city_id":26,"area_id":265,"address":"天地世嘉","content":"定制笔记本文具记事本A5加厚小清新大学生日记本子办公学生用商务\r\n","images":"/upload/sys/image/20/13246a09a7574258438fe24e63d83a.jpg","linkname":"晓光","sex":1,"mobile":"13295877552","ctime":"2018-04-13 17:19:23","view_cnt":1,"status":1,"listorder":0,"image_url":"","cate_name":"办公本"},{"id":68,"member_id":1000010,"cate_id":143,"title":"飞利浦CORD118固定电话机座机电话 家用座式有线坐机办公商务固话","price":"79.00","province_id":3,"city_id":29,"area_id":302,"address":"星光国际广场","content":"飞利浦CORD118固定电话机座机电话 家用座式有线坐机办公商务固话\r\n","images":"/upload/sys/image/18/929d55c73a38e19d81b9d6eaf4814b.jpg","linkname":"张不道","sex":1,"mobile":"17855233263","ctime":"2018-04-13 17:08:21","view_cnt":8,"status":1,"listorder":0,"image_url":"","cate_name":"电话"},{"id":63,"member_id":1000010,"cate_id":164,"title":"[耐摔大号]瑞可遥控飞机航拍无人机玩具四轴飞行器男孩儿童直升机","price":"168.00","province_id":2,"city_id":6,"area_id":89,"address":"景瑞御蓝湾","content":"[耐摔大号]瑞可遥控飞机航拍无人机玩具四轴飞行器男孩儿童直升机","images":"/upload/sys/image/51/50689df8cea9c784ec93e7a77dd94c.jpg","linkname":"王梦","sex":1,"mobile":"13900202254","ctime":"2018-04-13 16:56:01","view_cnt":4,"status":1,"listorder":0,"image_url":"","cate_name":"遥控飞机"}]
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
         * id : 201
         * member_id : 1000010
         * cate_id : 31
         * title : yyyy
         * price : 52.00
         * province_id : 2
         * city_id : 23
         * area_id : 251
         * address : 西湖
         * content : 考虑考虑
         * images : null
         * linkname : 赵龙涛
         * sex : 1
         * mobile : 13843854188
         * ctime : 2018-08-30 17:57:10
         * view_cnt : null
         * status : null
         * listorder : null
         * image_url : null
         * cate_name : 亲子
         */

        private int id;
        private int member_id;
        private int cate_id;
        private String title;
        private String price;
        private int province_id;
        private int city_id;
        private int area_id;
        private String address;
        private String content;
        private Object images;
        private String linkname;
        private int sex;
        private String mobile;
        private String ctime;
        private Object view_cnt;
        private Object status;
        private Object listorder;
        private Object image_url;
        private String cate_name;

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

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public Object getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(Object view_cnt) {
            this.view_cnt = view_cnt;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getListorder() {
            return listorder;
        }

        public void setListorder(Object listorder) {
            this.listorder = listorder;
        }

        public Object getImage_url() {
            return image_url;
        }

        public void setImage_url(Object image_url) {
            this.image_url = image_url;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
