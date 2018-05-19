package com.qunxianghui.gxh.bean.home;

import java.io.Serializable;
import java.util.List;

public class HomeAirBean implements Serializable {

    /**
     * code : 0
     * message : 获取城市成功
     * data : {"wendu":"21","forecast":[{"date":"19日星期六","high":"高温 26℃","low":"低温 21℃","sunrise":"05:04","sunset":"18:48","aqi":46,"day":{"windPower":"3-4级","windDirection":"东风","bgImage":"day.bg.zhenyu","smImage":"day.sm.zhenyu","notice":"阵雨来袭，出门记得带伞","type":3,"weather":"阵雨"},"night":{"windPower":"3-4级","windDirection":"东风","bgImage":"night.bg.zhenyu","smImage":"night.sm.zhenyu","notice":"夜间有阵雨，关好门窗哦","type":3,"weather":"阵雨"},"fx":"东风 3-4级","type":"阵雨"},{"date":"20日星期日","high":"高温 26℃","low":"低温 20℃","sunrise":"05:04","sunset":"18:49","aqi":37,"day":{"windPower":"3-4级","windDirection":"北风","bgImage":"day.bg.zhenyu","smImage":"day.sm.zhenyu","notice":"阵雨来袭，出门记得带伞","type":3,"weather":"阵雨"},"night":{"windPower":"3-4级","windDirection":"北风","bgImage":"night.bg.yin","smImage":"night.sm.yin","notice":"今晚虽阴，月光洒满你的梦","type":34,"weather":"阴"},"fx":"北风 3-4级","type":"阵雨"},{"date":"21日星期一","high":"高温 27℃","low":"低温 20℃","sunrise":"05:03","sunset":"18:49","aqi":46,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.duoyun","smImage":"day.sm.duoyun","notice":"阴晴之间，谨防紫外线侵扰","type":2,"weather":"多云"},"night":{"windPower":"<3级","windDirection":"北风","bgImage":"night.bg.duoyun","smImage":"night.sm.duoyun","notice":"夜晚多云，愿你梦里有月光","type":2,"weather":"多云"},"fx":"北风 <3级","type":"多云"},{"date":"22日星期二","high":"高温 30℃","low":"低温 18℃","sunrise":"05:03","sunset":"18:50","aqi":57,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.duoyun","smImage":"day.sm.duoyun","notice":"阴晴之间，谨防紫外线侵扰","type":2,"weather":"多云"},"night":{"windPower":"<3级","windDirection":"北风","bgImage":"night.bg.zhenyu","smImage":"night.sm.zhenyu","notice":"夜间有阵雨，关好门窗哦","type":3,"weather":"阵雨"},"fx":"北风 <3级","type":"多云"},{"date":"23日星期三","high":"高温 27℃","low":"低温 16℃","sunrise":"05:02","sunset":"18:51","aqi":68,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.yin","smImage":"day.sm.yin","notice":"不要被阴云遮挡住好心情","type":34,"weather":"阴"},"night":{"windPower":"<3级","windDirection":"南风","bgImage":"night.bg.duoyun","smImage":"night.sm.duoyun","notice":"夜晚多云，愿你梦里有月光","type":2,"weather":"多云"},"fx":"北风 <3级","type":"阴"}]}
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
        /**
         * wendu : 21
         * forecast : [{"date":"19日星期六","high":"高温 26℃","low":"低温 21℃","sunrise":"05:04","sunset":"18:48","aqi":46,"day":{"windPower":"3-4级","windDirection":"东风","bgImage":"day.bg.zhenyu","smImage":"day.sm.zhenyu","notice":"阵雨来袭，出门记得带伞","type":3,"weather":"阵雨"},"night":{"windPower":"3-4级","windDirection":"东风","bgImage":"night.bg.zhenyu","smImage":"night.sm.zhenyu","notice":"夜间有阵雨，关好门窗哦","type":3,"weather":"阵雨"},"fx":"东风 3-4级","type":"阵雨"},{"date":"20日星期日","high":"高温 26℃","low":"低温 20℃","sunrise":"05:04","sunset":"18:49","aqi":37,"day":{"windPower":"3-4级","windDirection":"北风","bgImage":"day.bg.zhenyu","smImage":"day.sm.zhenyu","notice":"阵雨来袭，出门记得带伞","type":3,"weather":"阵雨"},"night":{"windPower":"3-4级","windDirection":"北风","bgImage":"night.bg.yin","smImage":"night.sm.yin","notice":"今晚虽阴，月光洒满你的梦","type":34,"weather":"阴"},"fx":"北风 3-4级","type":"阵雨"},{"date":"21日星期一","high":"高温 27℃","low":"低温 20℃","sunrise":"05:03","sunset":"18:49","aqi":46,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.duoyun","smImage":"day.sm.duoyun","notice":"阴晴之间，谨防紫外线侵扰","type":2,"weather":"多云"},"night":{"windPower":"<3级","windDirection":"北风","bgImage":"night.bg.duoyun","smImage":"night.sm.duoyun","notice":"夜晚多云，愿你梦里有月光","type":2,"weather":"多云"},"fx":"北风 <3级","type":"多云"},{"date":"22日星期二","high":"高温 30℃","low":"低温 18℃","sunrise":"05:03","sunset":"18:50","aqi":57,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.duoyun","smImage":"day.sm.duoyun","notice":"阴晴之间，谨防紫外线侵扰","type":2,"weather":"多云"},"night":{"windPower":"<3级","windDirection":"北风","bgImage":"night.bg.zhenyu","smImage":"night.sm.zhenyu","notice":"夜间有阵雨，关好门窗哦","type":3,"weather":"阵雨"},"fx":"北风 <3级","type":"多云"},{"date":"23日星期三","high":"高温 27℃","low":"低温 16℃","sunrise":"05:02","sunset":"18:51","aqi":68,"day":{"windPower":"<3级","windDirection":"北风","bgImage":"day.bg.yin","smImage":"day.sm.yin","notice":"不要被阴云遮挡住好心情","type":34,"weather":"阴"},"night":{"windPower":"<3级","windDirection":"南风","bgImage":"night.bg.duoyun","smImage":"night.sm.duoyun","notice":"夜晚多云，愿你梦里有月光","type":2,"weather":"多云"},"fx":"北风 <3级","type":"阴"}]
         */

        private String wendu;
        private List<ForecastBean> forecast;

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class ForecastBean {
            /**
             * date : 19日星期六
             * high : 高温 26℃
             * low : 低温 21℃
             * sunrise : 05:04
             * sunset : 18:48
             * aqi : 46
             * day : {"windPower":"3-4级","windDirection":"东风","bgImage":"day.bg.zhenyu","smImage":"day.sm.zhenyu","notice":"阵雨来袭，出门记得带伞","type":3,"weather":"阵雨"}
             * night : {"windPower":"3-4级","windDirection":"东风","bgImage":"night.bg.zhenyu","smImage":"night.sm.zhenyu","notice":"夜间有阵雨，关好门窗哦","type":3,"weather":"阵雨"}
             * fx : 东风 3-4级
             * type : 阵雨
             */

            private String date;
            private String high;
            private String low;
            private String sunrise;
            private String sunset;
            private int aqi;
            private DayBean day;
            private NightBean night;
            private String fx;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public DayBean getDay() {
                return day;
            }

            public void setDay(DayBean day) {
                this.day = day;
            }

            public NightBean getNight() {
                return night;
            }

            public void setNight(NightBean night) {
                this.night = night;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class DayBean {
                /**
                 * windPower : 3-4级
                 * windDirection : 东风
                 * bgImage : day.bg.zhenyu
                 * smImage : day.sm.zhenyu
                 * notice : 阵雨来袭，出门记得带伞
                 * type : 3
                 * weather : 阵雨
                 */

                private String windPower;
                private String windDirection;
                private String bgImage;
                private String smImage;
                private String notice;
                private int type;
                private String weather;

                public String getWindPower() {
                    return windPower;
                }

                public void setWindPower(String windPower) {
                    this.windPower = windPower;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }

                public String getBgImage() {
                    return bgImage;
                }

                public void setBgImage(String bgImage) {
                    this.bgImage = bgImage;
                }

                public String getSmImage() {
                    return smImage;
                }

                public void setSmImage(String smImage) {
                    this.smImage = smImage;
                }

                public String getNotice() {
                    return notice;
                }

                public void setNotice(String notice) {
                    this.notice = notice;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }
            }

            public static class NightBean {
                /**
                 * windPower : 3-4级
                 * windDirection : 东风
                 * bgImage : night.bg.zhenyu
                 * smImage : night.sm.zhenyu
                 * notice : 夜间有阵雨，关好门窗哦
                 * type : 3
                 * weather : 阵雨
                 */

                private String windPower;
                private String windDirection;
                private String bgImage;
                private String smImage;
                private String notice;
                private int type;
                private String weather;

                public String getWindPower() {
                    return windPower;
                }

                public void setWindPower(String windPower) {
                    this.windPower = windPower;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }

                public String getBgImage() {
                    return bgImage;
                }

                public void setBgImage(String bgImage) {
                    this.bgImage = bgImage;
                }

                public String getSmImage() {
                    return smImage;
                }

                public void setSmImage(String smImage) {
                    this.smImage = smImage;
                }

                public String getNotice() {
                    return notice;
                }

                public void setNotice(String notice) {
                    this.notice = notice;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }
            }
        }
    }
}
