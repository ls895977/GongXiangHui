package com.qunxianghui.gxh.bean.mine;

import com.contrarywind.interfaces.IPickerViewData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ThirdStepCityBean implements Serializable,IPickerViewData {
    private int code;
    private String message;
    private List<DataBean> data;

    public static ThirdStepCityBean objectFromData(String str) {

        return new Gson().fromJson(str, ThirdStepCityBean.class);
    }

    public static List<ThirdStepCityBean> arrayThirdStepCityBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ThirdStepCityBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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

    @Override
    public String getPickerViewText() {
        return null;
    }


    public static class DataBean  {

        private int id;
        private String name;
        private int pid;
        private List<SubBeanX> sub;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

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
            private int id;
            private String pid;
            private String name;
            private String pinyin;
            private List<SubBean> sub;

            public static SubBeanX objectFromData(String str) {

                return new Gson().fromJson(str, SubBeanX.class);
            }

            public static List<SubBeanX> arraySubBeanXFromData(String str) {

                Type listType = new TypeToken<ArrayList<SubBeanX>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

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

                public static SubBean objectFromData(String str) {

                    return new Gson().fromJson(str, SubBean.class);
                }

                public static List<SubBean> arraySubBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<SubBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

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
