package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/15 15:42.
 */

public class QueryBean implements Serializable {

    private static final long serialVersionUID = -2621066358894903298L;
    /**
     * errno : 0
     * data : [{"addr":"杭州市庆春路58号","age":18,"brand":27,"brandName":"阿斯顿马丁","carName":"阿斯顿马丁V8","collectCount":0,"createTime":"2017-10-24 15:31:33","deposit":22,"distance":0,"engineLiter":"V6","id":18,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":43,"modelName":"V8软顶敞篷","name":"V8软顶敞篷","onShelfTime":"2017-10-24 15:31:33","orderCount":"2","owner":0,"ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg","plateNo":"19","price":0.01,"receivePercent":1,"seatNum":2,"series":36,"seriesName":"V8","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1}]
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
         * addr : 杭州市庆春路58号
         * age : 18
         * brand : 27
         * brandName : 阿斯顿马丁
         * carName : 阿斯顿马丁V8
         * collectCount : 0
         * createTime : 2017-10-24 15:31:33
         * deposit : 22
         * distance : 0
         * engineLiter : V6
         * id : 18
         * imgs : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg
         * latitude : 30.154682
         * longitude : 120.444372
         * mileage : 0
         * model : 43
         * modelName : V8软顶敞篷
         * name : V8软顶敞篷
         * onShelfTime : 2017-10-24 15:31:33
         * orderCount : 2
         * owner : 0
         * ownerName :
         * pics : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg
         * plateNo : 19
         * price : 0.01
         * receivePercent : 1
         * seatNum : 2
         * series : 36
         * seriesName : V8
         * status : 1
         * transmissionCase : 1
         * useCity : 重庆,杭州,北京
         * useType : 1
         */

        private String addr;
        private int age;
        private int brand;
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
        private String ownerName;
        private String pics;
        private String plateNo;
        private double price;
        private double receivePercent;
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

        public void setPrice(double price) {
            this.price = price;
        }

        public double getReceivePercent() {
            return receivePercent;
        }

        public void setReceivePercent(double receivePercent) {
            this.receivePercent = receivePercent;
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
