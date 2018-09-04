package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.TreeRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.Address;
import com.qunxianghui.gxh.bean.CityInfo;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.item.AreaItem;
import com.qunxianghui.gxh.item.ItemHelperFactory;
import com.qunxianghui.gxh.item.TreeRecyclerType;
import com.qunxianghui.gxh.utils.SPUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity implements AMapLocationListener {

    @BindView(R.id.tv_current_city)
    TextView mTvCurrentCity;
    @BindView(R.id.recyclerview_location_city)
    RecyclerView recyclerviewLocationCity;
    @BindView(R.id.ll_bg_load)
    View mLoadView;

    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND);
    private AMapLocationClient mLocationClient;
    AMapLocationClientOption mLocationOption = null;
    public static boolean sIsChangeArea;
    private String mCurrentCity;
    private Double mLng;
    private Double mLag;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected void initViews() {
        mLoadView.setVisibility(View.VISIBLE);
        recyclerviewLocationCity.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerviewLocationCity.setAdapter(treeRecyclerAdapter);
        mTvCurrentCity.setText(SPUtils.getLocation("currcity"));
    }

    @Override
    protected void initData() {
        requestAbleLocation();
        OkGo.<Address>post(Constant.FETCH_COUNTRY_ADRESS).execute(new JsonCallback<Address>() {
            @Override
            public void onSuccess(Response<Address> response) {
                mLoadView.setVisibility(View.GONE);
                treeRecyclerAdapter.getItemManager().replaceAllItem(ItemHelperFactory.createItems(response.body().data, null));
            }

            @Override
            public void onError(Response<Address> response) {
                super.onError(response);
                mLoadView.setVisibility(View.GONE);
            }
        });
        Callback callback = new Callback();
        callback.mre = new WeakReference<>(this);
        AreaItem.sCallback = callback;
    }

    @OnClick({R.id.iv_back, R.id.tv_current_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_current_address:
                break;
        }
    }

    public void callback(ProvinceBean.CityBean.AreasBean areasBean) {
        saveLocationData(areasBean.city_id, areasBean.areaId, areasBean.areaName);
        mTvCurrentCity.setText(areasBean.areaName);
        finish();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mCurrentCity = aMapLocation.getDistrict();
                mLag = aMapLocation.getLatitude();
                mLng = aMapLocation.getLongitude();
                String city = SPUtils.getLocation("currcity");
                if (TextUtils.isEmpty(mCurrentCity)) {
                    mTvCurrentCity.setText(city);
                } else {
                    mTvCurrentCity.setText(mCurrentCity);
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "locations Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
        mLocationClient.stopLocation();
    }

    private void requestAbleLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                setLocation();
            } else {
                ActivityCompat.requestPermissions(LocationActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100010);
            }
        } else {
            setLocation();
        }
    }

    private void setLocation() {
        //定位
        mLocationClient = new AMapLocationClient(mContext);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位监听
        mLocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @OnClick(R.id.tv_current_address)
    public void onViewClicked() {
        mTvCurrentCity.setText(mCurrentCity);
        requestCityInfo(mLag, mLng, mCurrentCity);
    }

    private void requestCityInfo(Double lat, Double lng, final String cityName) {
        OkGo.<CityInfo>get(Constant.GET_CITY_INFO)
                .params("lat", lat)
                .params("lng", lng)
                .execute(new JsonCallback<CityInfo>() {
                    @Override
                    public void onSuccess(Response<CityInfo> response) {
                        if (response.body().code == 0) {
                            String cityId = response.body().data.cityInfo.cityid;
                            String areaId = response.body().data.cityInfo.areaid;
                            saveLocationData(cityId, areaId, cityName);
                        }
                        finish();
                    }
                });
    }

    /*sp存储一些信息*/
    private void saveLocationData(String cityCode, String areaId, String cityName) {
        sIsChangeArea = true;
        OkGo.getInstance().getCommonHeaders().put("X-cityId", cityCode);
        OkGo.getInstance().getCommonHeaders().put("X-areaId", areaId);

        SPUtils.saveLocation("X-cityId", cityCode);
        SPUtils.saveLocation("X-areaId", areaId);
        SPUtils.saveLocation("currcity", cityName);
    }

    public static class Callback implements AreaItem.Callback {

        private WeakReference<LocationActivity> mre;

        @Override
        public void callback(ProvinceBean.CityBean.AreasBean areasBean) {
            if (mre != null && mre.get() != null) {
                mre.get().callback(areasBean);
            }
        }
    }

    @Override
    protected void onDestroy() {
        AreaItem.sCallback = null;
        super.onDestroy();
    }
}
