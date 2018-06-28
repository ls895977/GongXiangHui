package com.qunxianghui.gxh.bean.mine;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThirdStepCityBean implements Serializable {
    public int code;
    public String message;
    public ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean  implements IPickerViewData {

        public int id;
        public String name;
        public int pid;
        public List<CityBean> sub;

        @Override
        public String getPickerViewText() {
            return name;
        }

        public static class CityBean {

            public int id;
            public String pid;
            public String name;
            public String pinyin;
            public List<AreaBean> sub;

            public static class AreaBean {
                /**
                 * id : 1
                 * pid : 20001
                 * name : 朝阳
                 */

                public int id;
                public String pid;
                public String name;

            }
        }
    }

}
