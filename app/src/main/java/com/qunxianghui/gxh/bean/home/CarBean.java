package com.qunxianghui.gxh.bean.home;

import java.util.List;

/**
 * Created by Admin on 2017/11/1.
 */

public class CarBean {


    /**
     * errno : 0
     * data : [{"createTime":"2017-10-25 11:01:19","id":18,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png","name":"奥迪","nameEn":"Audi","otherName":""},{"createTime":"2017-10-25 11:01:17","id":19,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/BMW.png","name":"宝马","nameEn":"BMW","otherName":""},{"createTime":"2017-10-25 11:01:14","id":20,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/TOYOTA.png","name":"丰田","nameEn":"TOYOTA","otherName":""},{"createTime":"2017-10-25 10:59:58","id":21,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/NISSAN.png","name":"日产","nameEn":"NISSAN","otherName":""},{"createTime":"2017-10-25 10:59:55","id":22,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Benz.png","name":"奔驰","nameEn":"Benz","otherName":""},{"createTime":"2017-10-25 10:59:53","id":23,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Lamborghini.png","name":"兰博基尼","nameEn":"Lamborghini","otherName":""},{"createTime":"2017-10-25 10:59:50","id":24,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Ferrari.png","name":"法拉利","nameEn":"Ferrari","otherName":""},{"createTime":"2017-10-25 10:59:48","id":25,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/McLaren.png","name":"迈凯伦","nameEn":"McLaren","otherName":""},{"createTime":"2017-10-25 10:59:46","id":26,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Porsche.png","name":"保时捷","nameEn":"Porsche","otherName":""},{"createTime":"2017-10-25 10:59:44","id":27,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Aston%20Martin.png","name":"阿斯顿马丁","nameEn":"Aston Martin","otherName":""},{"createTime":"2017-10-25 10:59:41","id":28,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Maserati.png","name":"玛莎拉蒂","nameEn":"Maserati","otherName":""},{"createTime":"2017-10-25 10:58:14","id":29,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Rolls-Royce.png","name":"劳斯莱斯","nameEn":"Rolls-Royce","otherName":""},{"createTime":"2017-10-25 10:58:12","id":30,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Bentley.png","name":"宾利","nameEn":"Bentley","otherName":""},{"createTime":"2017-10-25 10:58:10","id":31,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/G.PATTON.png","name":"乔治巴顿","nameEn":"G.PATTON","otherName":""},{"createTime":"2017-10-25 10:58:07","id":32,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/BUICK.png","name":"别克","nameEn":"BUICK","otherName":""},{"createTime":"2017-10-25 10:58:04","id":33,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/BRABUS.png","name":"巴博斯","nameEn":"BRABUS","otherName":""},{"createTime":"2017-10-25 10:58:01","id":34,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Bugatti.png","name":"布加迪","nameEn":"Bugatti","otherName":""},{"createTime":"2017-10-25 11:32:23","id":35,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/GTR.png","name":"日产GT-R","nameEn":"GTR","otherName":""},{"createTime":"2017-10-25 11:36:06","id":36,"logo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Maybach.png","name":"迈巴赫","nameEn":"Maybach","otherName":""}]
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
         * createTime : 2017-10-25 11:01:19
         * id : 18
         * logo : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png
         * name : 奥迪
         * nameEn : Audi
         * otherName :
         */

        private String createTime;
        private int id;
        private String logo;
        private String name;
        private String nameEn;
        private String otherName;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getOtherName() {
            return otherName;
        }

        public void setOtherName(String otherName) {
            this.otherName = otherName;
        }
    }
}
