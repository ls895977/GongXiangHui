package com.qunxianghui.gxh.bean.home;

import java.util.List;

/**
 * @author 龙涛
 * @time 2018/5/18  11:15
 * @desc 获取全部频道实体类
 */
public class ChannelGetallBean {

    /**
     * code : 0
     * message :
     * data : [{"channel_id":1,"channel_name":"科技"},{"channel_id":2,"channel_name":"教育"},{"channel_id":3,"channel_name":"健康"},{"channel_id":17,"channel_name":"旅游"},{"channel_id":29,
     * "channel_name":"互联网"},{"channel_id":32,"channel_name":"新闻"},{"channel_id":33,"channel_name":"体育"},{"channel_id":34,"channel_name":"建筑"},{"channel_id":35,"channel_name":"美文"},
     * {"channel_id":36,"channel_name":"历史"},{"channel_id":38,"channel_name":"财经"},{"channel_id":39,"channel_name":"国际"},{"channel_id":40,"channel_name":"汽车"},{"channel_id":41,"channel_name":"美食"},
     * {"channel_id":42,"channel_name":"军事"},{"channel_id":43,"channel_name":"家居"},{"channel_id":44,"channel_name":"社会"},{"channel_id":45,"channel_name":"生活"},{"channel_id":46,"channel_name":"养生"},
     * {"channel_id":47,"channel_name":"探索"}]
     */

    private int code;
    private String message;
    /**
     * channel_id : 1
     * channel_name : 科技
     */

    private List<DataBean> data;

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
        private int channel_id;
        private String channel_name;

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public String getChannel_name() {
            return channel_name;
        }

        public void setChannel_name(String channel_name) {
            this.channel_name = channel_name;
        }
    }
}
