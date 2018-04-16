package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class HomeSeachLocationActivity extends BaseActivity {
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_setaddress)
    EditText etSetaddress;
    @BindView(R.id.tv_setaddress_cancel)
    TextView tvSetaddressCancel;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.lv_setaddress)
    ListView lvSetaddress;
    @BindView(R.id.tv_setaddress_content)
    TextView tvSetaddressContent;
    @BindView(R.id.cd_setaddress)
    CardView cdSetaddress;
    @BindView(R.id.fl_setaddress)
    FrameLayout flSetaddress;
    private AMapLocationClient mLocationClient;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        private String locationDetail;
        private String address;
        private double longitude;
        private double latitude;

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            int locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
            String error = "";
            if (!TextUtils.isEmpty(error)) {
                if (mLocationClient != null)
                    mLocationClient.stopLocation();
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                return;
            }

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    //获取纬度
                    latitude = amapLocation.getLatitude();
                    //获取经度
                    longitude = amapLocation.getLongitude();
                    //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    address = amapLocation.getAddress();
                    locationDetail = amapLocation.getLocationDetail();
                    tvSetaddressContent.setText(address + locationDetail);
                    if (mLocationClient.isStarted()) {
                        // 获得位置之后停止定位
                        mLocationClient.stopLocation();
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }


    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_seach_location;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        initLocation();

    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

//关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消监听函数
        if (mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
            mLocationClient.unRegisterLocationListener(mLocationListener);
        }

    }
}
