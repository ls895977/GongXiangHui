package com.gongxianghui.fragments.generalizeFragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment implements View.OnClickListener {


    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;


    @BindView(R.id.tv_location)
    TextView tvLocation;
    Unbinder unbinder;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalize;
    }

    @Override
    public void initDatas() {
        if (PermissionsUtil.hasPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            mlocationClient = new AMapLocationClient(mActivity);
            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息
                            amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                            amapLocation.getLatitude();//获取纬度
                            amapLocation.getLongitude();//获取经度
                            amapLocation.getAccuracy();//获取精度信息
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            df.format(date);//定位时间
                            amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                            amapLocation.getCountry();//国家信息
                            amapLocation.getProvince();//省信息
                            amapLocation.getCity();//城市信息
                            amapLocation.getDistrict();//城区信息
                            amapLocation.getStreet();//街道信息
                            amapLocation.getStreetNum();//街道门牌号信息
                            amapLocation.getCityCode();//城市编码
                            amapLocation.getAdCode();//地区编码

tvLocation.setText("位置是"+amapLocation);
                        } else {
                            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError","location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                        }
                    }
                }
            });
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
//设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
            mlocationClient.startLocation();


        } else {
            PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    Toast.makeText(mActivity, "允许访问您的位置", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    Toast.makeText(mActivity, "拒绝访问位置信息", Toast.LENGTH_SHORT).show();
                }
            }, new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
        }


    }

    @Override
    public void initViews(View view) {

    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
