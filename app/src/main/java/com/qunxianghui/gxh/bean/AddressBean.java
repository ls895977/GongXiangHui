package com.qunxianghui.gxh.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2018/6/16.
 */

public class AddressBean implements Serializable {

    private int code;

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

    private String message;

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 直辖市
         * pid : 0
         * sub : [{"id":1,"pid":"10001","name":"北京","pinyin":"beijing","sub":[{"id":1,"pid":"20001","name":"朝阳"},{"id":2,"pid":"20001","name":"海淀"},{"id":3,"pid":"20001","name":"东城"},{"id":4,"pid":"20001","name":"西城"},{"id":5,"pid":"20001","name":"崇文"},{"id":6,"pid":"20001","name":"宣武"},{"id":7,"pid":"20001","name":"丰台"},{"id":8,"pid":"20001","name":"通州"},{"id":9,"pid":"20001","name":"石景山"},{"id":10,"pid":"20001","name":"房山"},{"id":11,"pid":"20001","name":"昌平"},{"id":12,"pid":"20001","name":"大兴"},{"id":13,"pid":"20001","name":"顺义"},{"id":14,"pid":"20001","name":"密云"},{"id":15,"pid":"20001","name":"怀柔"},{"id":16,"pid":"20001","name":"延庆"},{"id":17,"pid":"20001","name":"平谷"},{"id":18,"pid":"20001","name":"门头沟"},{"id":19,"pid":"20001","name":"燕郊"},{"id":20,"pid":"20001","name":"北京周边"}]},{"id":4,"pid":"10001","name":"重庆","pinyin":"chongqing","sub":[{"id":55,"pid":"20004","name":"江北"},{"id":56,"pid":"20004","name":"万州"},{"id":57,"pid":"20004","name":"渝中"},{"id":58,"pid":"20004","name":"九龙坡"},{"id":59,"pid":"20004","name":"涪陵"},{"id":60,"pid":"20004","name":"长寿"},{"id":61,"pid":"20004","name":"沙坪坝"},{"id":62,"pid":"20004","name":"合川"},{"id":63,"pid":"20004","name":"南岸"},{"id":64,"pid":"20004","name":"渝北"},{"id":65,"pid":"20004","name":"巴南"},{"id":66,"pid":"20004","name":"北碚"},{"id":67,"pid":"20004","name":"大渡口"},{"id":68,"pid":"20004","name":"永川"},{"id":69,"pid":"20004","name":"两江新区"},{"id":70,"pid":"20004","name":"璧山"},{"id":71,"pid":"20004","name":"石柱"},{"id":72,"pid":"20004","name":"重庆周边"}]},{"id":2,"pid":"10001","name":"上海","pinyin":"shanghai","sub":[{"id":21,"pid":"20002","name":"黄浦"},{"id":22,"pid":"20002","name":"卢湾"},{"id":23,"pid":"20002","name":"静安"},{"id":24,"pid":"20002","name":"徐汇"},{"id":25,"pid":"20002","name":"浦东"},{"id":26,"pid":"20002","name":"长宁"},{"id":27,"pid":"20002","name":"虹口"},{"id":28,"pid":"20002","name":"杨浦"},{"id":29,"pid":"20002","name":"普陀"},{"id":30,"pid":"20002","name":"闸北"},{"id":31,"pid":"20002","name":"闵行"},{"id":32,"pid":"20002","name":"宝山"},{"id":33,"pid":"20002","name":"嘉定"},{"id":34,"pid":"20002","name":"青浦"},{"id":35,"pid":"20002","name":"奉贤"},{"id":36,"pid":"20002","name":"南汇"},{"id":37,"pid":"20002","name":"崇明"},{"id":38,"pid":"20002","name":"金山"},{"id":39,"pid":"20002","name":"松江"},{"id":40,"pid":"20002","name":"上海周边"}]},{"id":3,"pid":"10001","name":"天津","pinyin":"tianjin","sub":[{"id":41,"pid":"20003","name":"南开"},{"id":42,"pid":"20003","name":"河西"},{"id":43,"pid":"20003","name":"河东"},{"id":44,"pid":"20003","name":"和平"},{"id":45,"pid":"20003","name":"河北"},{"id":46,"pid":"20003","name":"红桥"},{"id":47,"pid":"20003","name":"塘沽"},{"id":48,"pid":"20003","name":"东丽"},{"id":49,"pid":"20003","name":"西青"},{"id":50,"pid":"20003","name":"北辰"},{"id":51,"pid":"20003","name":"津南"},{"id":52,"pid":"20003","name":"开发区"},{"id":53,"pid":"20003","name":"大港"},{"id":54,"pid":"20003","name":"天津周边"}]}]
         */

        private int id;
        private String name;
        private int pid;
        private List<SubBeanX> sub;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public List<SubBeanX> getSub() {
            return sub;
        }

        public void setSub(List<SubBeanX> sub) {
            this.sub = sub;
        }

        public static class SubBeanX {
            /**
             * id : 1
             * pid : 10001
             * name : 北京
             * pinyin : beijing
             * sub : [{"id":1,"pid":"20001","name":"朝阳"},{"id":2,"pid":"20001","name":"海淀"},{"id":3,"pid":"20001","name":"东城"},{"id":4,"pid":"20001","name":"西城"},{"id":5,"pid":"20001","name":"崇文"},{"id":6,"pid":"20001","name":"宣武"},{"id":7,"pid":"20001","name":"丰台"},{"id":8,"pid":"20001","name":"通州"},{"id":9,"pid":"20001","name":"石景山"},{"id":10,"pid":"20001","name":"房山"},{"id":11,"pid":"20001","name":"昌平"},{"id":12,"pid":"20001","name":"大兴"},{"id":13,"pid":"20001","name":"顺义"},{"id":14,"pid":"20001","name":"密云"},{"id":15,"pid":"20001","name":"怀柔"},{"id":16,"pid":"20001","name":"延庆"},{"id":17,"pid":"20001","name":"平谷"},{"id":18,"pid":"20001","name":"门头沟"},{"id":19,"pid":"20001","name":"燕郊"},{"id":20,"pid":"20001","name":"北京周边"}]
             */

            private int id;
            private String pid;
            private String name;
            private String pinyin;
            private List<SubBean> sub;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<SubBean> getSub() {
                return sub;
            }

            public void setSub(List<SubBean> sub) {
                this.sub = sub;
            }

            public static class SubBean {
                /**
                 * id : 1
                 * pid : 20001
                 * name : 朝阳
                 */

                private int id;
                private String pid;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
