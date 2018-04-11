package com.gongxianghui.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/12 20:12.
 */

public class CarDetailBean implements Serializable {

    /**
     * errno : 0
     * data : [{"addr":"杭州市庆春路58号","age":5,"brand":18,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png","brandName":"奥迪","carName":"奥迪R8","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":2,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.278001,"longitude":120.168069,"mileage":50000,"model":9,"modelName":"R8","name":"奥迪 S8 2016款 4.0 TFSI quattro","onShelfTime":"2017-11-10 14:41:55","orderCount":"23","owner":5,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"ZZB","pics":"http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png","plateNo":"浙AK136A","price":2000,"receivePercent":0.25,"remark":"测试车辆","seatNum":2,"series":13,"seriesName":"R8","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市五常街道","age":6,"brand":21,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/NISSAN.png","brandName":"日产","carName":"日产GTR","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":1,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.278001,"longitude":120.168069,"mileage":30001,"model":11,"modelName":"GTR","name":"测试车辆","onShelfTime":"2017-11-10 14:45:08","orderCount":"10","owner":4,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"刘备","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg","plateNo":"沪C1CU76","price":1000,"receivePercent":0,"remark":"测试车辆","seatNum":2,"series":16,"seriesName":"GTR","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京,温州","useType":1},{"addr":"杭州市庆春路58号","age":18,"brand":27,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Aston%20Martin.png","brandName":"阿斯顿马丁","carName":"阿斯顿马丁V8","collectCount":0,"createTime":"2017-10-24 15:31:33","deposit":22,"distance":0,"engineLiter":"V6","id":18,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":43,"modelName":"V8软顶敞篷","name":"V8软顶敞篷","onShelfTime":"2017-10-24 15:31:33","orderCount":"11","owner":0,"ownerAvatar":"","ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg","plateNo":"19","price":7800,"receivePercent":1,"remark":"21","seatNum":2,"series":36,"seriesName":"V8","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1},{"addr":"浙江省杭州市滨江区网商路","age":321,"brand":28,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Maserati.png","brandName":"玛莎拉蒂","carName":"玛莎拉蒂GranTurismo","collectCount":0,"createTime":"2017-10-24 15:33:49","deposit":213,"distance":0,"engineLiter":"V6","id":19,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":45,"modelName":"GranTurismo（GT）","name":"GranTurismo","onShelfTime":"2017-10-24 15:33:49","orderCount":"4","owner":0,"ownerAvatar":"","ownerName":"","pics":"http://living.cto1024.com/2c53b85e3266599dafb344741dc9b6c2.jpg","plateNo":"213","price":9000,"receivePercent":1,"remark":"213","seatNum":4,"series":37,"seriesName":"GranTurismo","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1},{"addr":"浙江省杭州市滨江区网商路","age":321,"brand":29,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Rolls-Royce.png","brandName":"劳斯莱斯","carName":"劳斯莱斯幻影","collectCount":0,"createTime":"2017-10-24 15:36:17","deposit":123,"distance":0,"engineLiter":"V6","id":20,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":48,"modelName":"幻影","name":"幻影","onShelfTime":"2017-10-24 15:36:17","orderCount":"0","owner":3213,"ownerAvatar":"","ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/2015070910584626759574802.jpg","plateNo":"132","price":1000,"receivePercent":1,"remark":"213","seatNum":4,"series":40,"seriesName":"幻影","status":1,"transmissionCase":4,"useCity":"重庆,杭州,北京","useType":1},{"addr":"浙江省杭州市滨江区网商路","age":321,"brand":30,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Bentley.png","brandName":"宾利","carName":"宾利慕尚","collectCount":0,"createTime":"2017-10-24 15:36:25","deposit":123,"distance":0,"engineLiter":"V6","id":21,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":52,"modelName":"慕尚","name":"慕尚","onShelfTime":"2017-10-24 15:36:25","orderCount":"4","owner":3213,"ownerAvatar":"","ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/960a304e251f95ca9d79c59bc3177f3e6709520a.jpg","plateNo":"132","price":1150,"receivePercent":1,"remark":"213","seatNum":4,"series":44,"seriesName":"慕尚","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市庆春路58号","age":0,"brand":24,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Ferrari.png","brandName":"法拉利","carName":"法拉利LaFerrari","collectCount":0,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":5,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":26,"modelName":"LaFerrari(拉法)","name":"至尊宝CAR4","onShelfTime":"2017-10-12 11:59:03","orderCount":"0","owner":10,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"Happy","pics":"http://living.cto1024.com/2c53b85e3266599dafb344741dc9b6c2.jpg","plateNo":"浙AK237A","price":4000,"receivePercent":1,"remark":"测试车辆","seatNum":2,"series":24,"seriesName":"LaFerrari","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市庆春路58号","age":0,"brand":24,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Ferrari.png","brandName":"法拉利","carName":"法拉利California","collectCount":0,"createTime":"2017-10-20 17:57:11","deposit":123,"distance":0,"engineLiter":"V6","id":6,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":32,"modelName":"California T（加州）","name":"至尊宝CAR5","onShelfTime":"2017-10-23 15:13:25","orderCount":"1","owner":4,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"刘备","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/W020150112350498585893.jpg","plateNo":"浙AK157A","price":4600,"receivePercent":1,"remark":"测试车辆A","seatNum":2,"series":28,"seriesName":"California","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市庆春路58号","age":0,"brand":25,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/McLaren.png","brandName":"迈凯伦","carName":"迈凯伦570","collectCount":0,"createTime":"2017-10-24 15:25:09","deposit":213,"distance":0,"engineLiter":"V6","id":16,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":37,"modelName":"570S","name":"570S","onShelfTime":"2017-10-24 15:25:09","orderCount":"0","owner":0,"ownerAvatar":"","ownerName":"","pics":"http://living.cto1024.com/2c53b85e3266599dafb344741dc9b6c2.jpg","plateNo":"231","price":5200,"receivePercent":1,"remark":"231","seatNum":2,"series":32,"seriesName":"570","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市庆春路58号","age":0,"brand":26,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Porsche.png","brandName":"保时捷","carName":"保时捷911","collectCount":0,"createTime":"2017-10-24 15:28:03","deposit":22,"distance":0,"engineLiter":"V6","id":17,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":39,"modelName":"911软顶敞篷","name":"911软顶敞篷","onShelfTime":"2017-10-24 15:28:03","orderCount":"1","owner":0,"ownerAvatar":"","ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/3264x2448-161710_10191392970630147_55808efe09bfafe98f2676361dc820a6.jpg","plateNo":"19","price":6200,"receivePercent":1,"remark":"21","seatNum":2,"series":33,"seriesName":"911","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1}]
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

    public static class DataBean implements Serializable {
        /**
         * addr : 杭州市庆春路58号
         * age : 5
         * brand : 18
         * brandLogo : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png
         * brandName : 奥迪
         * carName : 奥迪R8
         * collectCount : 1
         * createTime : 2017-10-12 11:59:03
         * deposit : 20000
         * distance : 0
         * engineLiter : V6
         * id : 2
         * imgs : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg
         * latitude : 30.278001
         * longitude : 120.168069
         * mileage : 50000
         * model : 9
         * modelName : R8
         * name : 奥迪 S8 2016款 4.0 TFSI quattro
         * onShelfTime : 2017-11-10 14:41:55
         * orderCount : 23
         * owner : 5
         * ownerAvatar : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg
         * ownerName : ZZB
         * pics : http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png
         * plateNo : 浙AK136A
         * price : 2000
         * receivePercent : 0.25
         * remark : 测试车辆
         * seatNum : 2
         * series : 13
         * seriesName : R8
         * status : 1
         * transmissionCase : 0
         * useCity : 重庆,杭州,北京
         * useType : 1
         */

        private String addr;
        private int age;
        private int brand;
        private String brandLogo;
        private String brandName;
        private String carName;
        private int collectCount;
        private String createTime;
        private int deposit;
        private int distance;
        private String engineLiter;
        private int id;
        private String imgs;
        private double latitude;
        private double longitude;
        private int mileage;
        private int model;
        private String modelName;
        private String name;
        private String onShelfTime;
        private String orderCount;
        private int owner;
        private String ownerAvatar;
        private String ownerName;
        private String pics;
        private String plateNo;
        private double price;
        private double receivePercent;
        private String remark;
        private int seatNum;
        private int series;
        private String seriesName;
        private int status;
        private int transmissionCase;
        private String useCity;
        private int useType;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getBrand() {
            return brand;
        }

        public void setBrand(int brand) {
            this.brand = brand;
        }

        public String getBrandLogo() {
            return brandLogo;
        }

        public void setBrandLogo(String brandLogo) {
            this.brandLogo = brandLogo;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getEngineLiter() {
            return engineLiter;
        }

        public void setEngineLiter(String engineLiter) {
            this.engineLiter = engineLiter;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public int getModel() {
            return model;
        }

        public void setModel(int model) {
            this.model = model;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOnShelfTime() {
            return onShelfTime;
        }

        public void setOnShelfTime(String onShelfTime) {
            this.onShelfTime = onShelfTime;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public String getOwnerAvatar() {
            return ownerAvatar;
        }

        public void setOwnerAvatar(String ownerAvatar) {
            this.ownerAvatar = ownerAvatar;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public double getReceivePercent() {
            return receivePercent;
        }

        public void setReceivePercent(double receivePercent) {
            this.receivePercent = receivePercent;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSeatNum() {
            return seatNum;
        }

        public void setSeatNum(int seatNum) {
            this.seatNum = seatNum;
        }

        public int getSeries() {
            return series;
        }

        public void setSeries(int series) {
            this.series = series;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTransmissionCase() {
            return transmissionCase;
        }

        public void setTransmissionCase(int transmissionCase) {
            this.transmissionCase = transmissionCase;
        }

        public String getUseCity() {
            return useCity;
        }

        public void setUseCity(String useCity) {
            this.useCity = useCity;
        }

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }
    }
}
