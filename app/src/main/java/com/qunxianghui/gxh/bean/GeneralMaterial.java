package com.qunxianghui.gxh.bean;

import java.util.List;

public class GeneralMaterial {

    public int code;
    public String msg;
    public GeneralMaterialBean data;

    public static class GeneralMaterialBean {
        public List<Cate> cate;
        public List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> generalAdList;

        public static class Cate {
            public int id;
            public String cate_name;
        }
    }

}
