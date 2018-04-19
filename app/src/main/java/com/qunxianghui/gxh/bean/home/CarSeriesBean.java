package com.qunxianghui.gxh.bean.home;

import java.util.List;

/**
 * Created by Admin on 2017/11/3.
 */

public class CarSeriesBean {

    /**
     * errno : 0
     * data : [{"brandId":18,"createTime":"2017-10-25 16:17:50","id":13,"importType":0,"name":"R8"},{"brandId":19,"createTime":"2017-10-25 16:17:48","id":14,"importType":0,"name":"I8"},{"brandId":20,"createTime":"2017-10-25 16:17:47","id":15,"importType":0,"name":"埃尔法"},{"brandId":21,"createTime":"2017-10-25 16:17:45","id":16,"importType":0,"name":"GTR"},{"brandId":22,"createTime":"2017-10-25 16:17:42","id":17,"importType":0,"name":"G系"},{"brandId":22,"createTime":"2017-10-25 16:17:40","id":18,"importType":0,"name":"唯雅诺"},{"brandId":22,"createTime":"2017-10-25 16:17:38","id":19,"importType":0,"name":"SL"},{"brandId":22,"createTime":"2017-10-25 16:17:36","id":20,"importType":0,"name":"威霆"},{"brandId":22,"createTime":"2017-10-25 16:17:34","id":21,"importType":0,"name":"AMG-GT"},{"brandId":23,"createTime":"2017-10-25 16:17:33","id":22,"importType":0,"name":"Aventador"},{"brandId":23,"createTime":"2017-10-25 16:17:31","id":23,"importType":0,"name":"Huracan"},{"brandId":24,"createTime":"2017-10-25 16:17:29","id":24,"importType":0,"name":"LaFerrari"},{"brandId":24,"createTime":"2017-10-25 16:17:25","id":25,"importType":0,"name":"488"},{"brandId":24,"createTime":"2017-10-25 16:17:24","id":26,"importType":0,"name":"458"},{"brandId":24,"createTime":"2017-10-25 16:17:22","id":27,"importType":0,"name":"F12"},{"brandId":24,"createTime":"2017-10-25 16:17:20","id":28,"importType":0,"name":"California"},{"brandId":24,"createTime":"2017-10-25 16:17:18","id":29,"importType":0,"name":"F430"},{"brandId":25,"createTime":"2017-10-25 16:17:17","id":30,"importType":0,"name":"P1"},{"brandId":25,"createTime":"2017-10-25 16:17:15","id":31,"importType":0,"name":"650"},{"brandId":25,"createTime":"2017-10-25 16:17:13","id":32,"importType":0,"name":"570"},{"brandId":26,"createTime":"2017-10-25 16:17:11","id":33,"importType":0,"name":"911"},{"brandId":26,"createTime":"2017-10-25 16:17:09","id":34,"importType":0,"name":"Panamera"},{"brandId":27,"createTime":"2017-10-25 16:17:06","id":35,"importType":0,"name":"Rapide"},{"brandId":27,"createTime":"2017-10-25 16:17:04","id":36,"importType":0,"name":"V8"},{"brandId":28,"createTime":"2017-10-25 16:17:01","id":37,"importType":0,"name":"GranTurismo"},{"brandId":28,"createTime":"2017-10-25 16:16:57","id":38,"importType":0,"name":"总裁"},{"brandId":28,"createTime":"2017-10-25 16:16:54","id":39,"importType":0,"name":"Ghibli"},{"brandId":29,"createTime":"2017-10-25 16:16:52","id":40,"importType":0,"name":"幻影"},{"brandId":29,"createTime":"2017-10-25 16:16:48","id":41,"importType":0,"name":"魅影"},{"brandId":29,"createTime":"2017-10-25 16:16:46","id":42,"importType":0,"name":"曜影"},{"brandId":29,"createTime":"2017-10-25 16:16:44","id":43,"importType":0,"name":"古斯特"},{"brandId":30,"createTime":"2017-10-25 16:16:42","id":44,"importType":0,"name":"慕尚"},{"brandId":30,"createTime":"2017-10-25 16:16:40","id":45,"importType":0,"name":"飞驰"},{"brandId":30,"createTime":"2017-10-25 16:16:38","id":46,"importType":0,"name":"欧陆"},{"brandId":31,"createTime":"2017-10-25 16:16:35","id":47,"importType":0,"name":"乔治巴顿"},{"brandId":32,"createTime":"2017-10-25 16:16:32","id":48,"importType":0,"name":"GL8"},{"brandId":33,"createTime":"2017-10-25 16:16:17","id":49,"importType":0,"name":"G500"},{"brandId":34,"createTime":"2017-10-25 16:16:14","id":50,"importType":0,"name":"布加迪威航（威龙）"}]
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
         * brandId : 18
         * createTime : 2017-10-25 16:17:50
         * id : 13
         * importType : 0
         * name : R8
         */

        private int brandId;
        private String createTime;
        private int id;
        private int importType;
        private String name;

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

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

        public int getImportType() {
            return importType;
        }

        public void setImportType(int importType) {
            this.importType = importType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
