package com.qunxianghui.gxh.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.bean.AddressBean;
import com.qunxianghui.gxh.config.Constant;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityPickerutil {
    public static List<AddressBean.DataBean> items = new ArrayList<>();

    public static List<String> items1 = new ArrayList<>();

    public static List<List<String>> items2 = new ArrayList<>();

    public static List<List<String>> citys = new ArrayList<>();

    public static List<List<List<String>>> items3 = new ArrayList<>();

    private static List<String> cities = new ArrayList<>();

    private static Context mContext;

    public static void initDatas(final Context context) {
        mContext = context;
        getAddressData(mContext);
    }

    private static void getAddressData(Context mContext) {
        OkGo.<String>post(Constant.FETCH_COUNTRY_ADRESS)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body().toString())) {
                            parseAddressData(response.body());
                            return;
                        }
                    }
                });
    }

    private static void parseAddressData(String body) {
        JSONObject jsonObject = null;
        try {
            AddressBean addressBean = JSON.parseObject(body, AddressBean.class);
            items = addressBean.getData();
            setList();

        } catch (Exception e) {

        }
    }


    private static void setList() {
        if (items == null || items.isEmpty()) {
            return;
        }
        for (int i = 0; i < items.size(); i++) {//遍历省份
            List<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<String>> areaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < items.get(i).getSub().size(); c++) {//遍历该省份的所有城市
                String CityName = items.get(i).getSub().get(c).getName();
                cityList.add(CityName);//添加城市
                cities.add(CityName);
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (items.get(i).getSub().get(c).getSub()== null
                        || items.get(i).getSub().get(c).getSub().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int j = 0; j < items.get(i).getSub().get(c).getSub().size(); j++) {
                        City_AreaList.add(items.get(i).getSub().get(c).getSub().get(j).getName());
                    }
                }
                areaList.add(City_AreaList);//添加该省所有地区数据
            }

            items1.add(items.get(i).getName());
            /**
             * 添加城市数据
             */
            items2.add(cityList);
            /**
             * 添加地区数据
             */
            items3.add(areaList);
        }
    }


}
