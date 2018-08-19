package com.qunxianghui.gxh.bean;

import java.util.List;

public class AddAdvert {

    public int code;
    public String msg;
    public AddAdvertBean data;

    public static class AddAdvertBean{

        public List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> top;
        public List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> bottom;

    }
}
