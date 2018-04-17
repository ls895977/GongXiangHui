package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/11 16:16.
 */

public class MainPageBean implements Serializable {
    private static final long serialVersionUID = 7551910832788545721L;

    private int errno;
    private DataBean data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<ActivityBean> activity;
        private List<MarqueeBean> marquee;
        private List<CarListBean> carList;
        private List<MessageBean> message;
        private List<BrandBean> brand;
        private List<NewCarListBean> newCarList;

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<MarqueeBean> getMarquee() {
            return marquee;
        }

        public void setMarquee(List<MarqueeBean> marquee) {
            this.marquee = marquee;
        }

        public List<CarListBean> getCarList() {
            return carList;
        }

        public void setCarList(List<CarListBean> carList) {
            this.carList = carList;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }

        public List<BrandBean> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandBean> brand) {
            this.brand = brand;
        }

        public List<NewCarListBean> getNewCarList() {
            return newCarList;
        }

        public void setNewCarList(List<NewCarListBean> newCarList) {
            this.newCarList = newCarList;
        }

        public static class ActivityBean implements Serializable {
            /**
             * carId : 4
             * carName : 兰博基尼Huracan
             * id : 19
             * picUrl : http://living.cto1024.com/74ef1608e2278cf6394613b382af21b6.jpg
             * price : 0.01
             * time1 : 2017-11-01 10:24:27
             * time2 : 2017-11-29 10:24:31
             * title : 豪车共享正式启动,前十天优惠体验,速来!
             */

            private int carId;
            private String carName;
            private int id;
            private String picUrl;
            private double price;
            private String time1;
            private String time2;
            private String title;

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getTime1() {
                return time1;
            }

            public void setTime1(String time1) {
                this.time1 = time1;
            }

            public String getTime2() {
                return time2;
            }

            public void setTime2(String time2) {
                this.time2 = time2;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class MarqueeBean implements Serializable {
            /**
             * content : http://app.zzbcar.com/marquee/00000a-20171103102015.htm
             * id : 10
             * picUrl : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/20171103102515_656.jpg
             * sort : 0
             * title : 测试修改
             */

            private String content;
            private int id;
            private String picUrl;
            private int sort;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class CarListBean implements Serializable {
            /**
             * addr : 杭州市庆春路58号
             * age : 5
             * brand : 18
             * brandName : 奥迪
             * carName : 奥迪R8
             * collectCount : 0
             * deposit : 20000
             * distance : 0
             * id : 2
             * imgs :
             * latitude : 30.278001
             * longitude : 120.168069
             * mileage : 50000
             * model : 9
             * modelName : R8
             * name : 奥迪 S8 2016款 4.0 TFSI quattro
             * onShelfTime : 2017-10-20 15:31:58
             * orderCount : 4
             * owner : 5
             * ownerName : ZZB
             * pics : http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png
             * plateNo : 浙AK136A
             * price : 0.01
             * receivePercent : 0
             * seatNum : 2
             * series : 13
             * seriesName : R8
             * transmissionCase : 0
             * useCity :
             * useType : 2
             */

            private String addr;
            private int age;
            private int brand;
            private String brandName;
            private String carName;
            private int collectCount;
            private int deposit;
            private int distance;
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

        public static class MessageBean implements Serializable {
            /**
             * content : http://app.zzbcar.com/message/000023-20171103102402.htm
             * id : 35
             * pushStatus : 1
             * pushTime : 2017-11-03 10:24:02
             * subTitle : subtitle
             * title : 测试推送是否成功
             */

            private String content;
            private int id;
            private int pushStatus;
            private String pushTime;
            private String subTitle;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPushStatus() {
                return pushStatus;
            }

            public void setPushStatus(int pushStatus) {
                this.pushStatus = pushStatus;
            }

            public String getPushTime() {
                return pushTime;
            }

            public void setPushTime(String pushTime) {
                this.pushTime = pushTime;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class BrandBean implements Serializable {
            /**
             * id : 18
             * logo : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png
             * name : 奥迪
             * nameEn : Audi
             * otherName :
             */

            private int id;
            private String logo;
            private String name;
            private String nameEn;
            private String otherName;

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

        public static class NewCarListBean implements Serializable {
            /**
             * addr : 浙江省杭州市滨江区网商路
             * age : 321
             * brand : 30
             * brandName : 宾利
             * carName : 宾利慕尚
             * collectCount : 0
             * deposit : 123
             * distance : 0
             * id : 21
             * imgs :
             * latitude : 30.154682
             * longitude : 120.444372
             * mileage : 0
             * model : 52
             * modelName : 慕尚
             * name : 慕尚
             * onShelfTime : 2017-10-24 15:36:25
             * orderCount : 4
             * owner : 3213
             * ownerName :
             * pics : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/960a304e251f95ca9d79c59bc3177f3e6709520a.jpg
             * plateNo : 132
             * price : 0.01
             * receivePercent : 0
             * seatNum : 4
             * series : 44
             * seriesName : 慕尚
             * transmissionCase : 1
             * useCity :
             * useType : 0
             */

            private String addr;
            private int age;
            private int brand;
            private String brandName;
            private String carName;
            private int collectCount;
            private int deposit;
            private int distance;
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
}
