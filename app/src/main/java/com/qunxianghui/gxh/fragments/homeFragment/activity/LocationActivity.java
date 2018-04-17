package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.TreeRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.CityBean;
import com.qunxianghui.gxh.item.ItemHelperFactory;
import com.qunxianghui.gxh.item.ProvinceItemParent;
import com.qunxianghui.gxh.item.TreeItem;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity implements View.OnClickListener, AMapLocationListener {
    @BindView(R.id.recycler_home_location)
    RecyclerView recyclerHomeLocation;
    @BindView(R.id.tv_home_preent_location)
    TextView tv_home_preent_location;

    TitleBuilder titleBuilder;


    public AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_home_location;
    }

    @Override
    protected void initViews() {
        recyclerHomeLocation.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerHomeLocation.setItemAnimator(new DefaultItemAnimator());
        recyclerHomeLocation.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                outRect.top = 10;
                if (view.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标 第几列
                    if (spanIndex != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                        outRect.right = 5;
                    }

                }
            }
        });

//        List<CityBean> cityBean = (List<CityBean>) GsonUtil.parseJsonWithGson(mActivity.getResources().getString(R.string.location),CityBean.class );
        List<CityBean> cityBean = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList((List) cityBean, ProvinceItemParent.class, null);
        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();

        treeRecyclerAdapter.setDatas(treeItemList);
        recyclerHomeLocation.setAdapter(treeRecyclerAdapter);
    }

    @Override
    protected void initDatas() {
        titleBuilder = new TitleBuilder(this);
        titleBuilder.setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("当前地区-定位中");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLocationChanged(final AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
//                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                amapLocation.getLatitude();//获取纬度
//                amapLocation.getLongitude();//获取经度
//                amapLocation.getAccuracy();//获取精度信息
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date(amapLocation.getTime());
//                df.format(date);//定位时间
//                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                amapLocation.getCountry();//国家信息
//                amapLocation.getProvince();//省信息
//                amapLocation.getCity();//城市信息
//                amapLocation.getDistrict();//城区信息
//                amapLocation.getStreet();//街道信息
//                amapLocation.getStreetNum();//街道门牌号信息
//                amapLocation.getCityCode();//城市编码
//                amapLocation.getAdCode();//地区编码

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_home_preent_location.setText(amapLocation.getDistrict());
                        titleBuilder.setTitleText("当前地区-" + amapLocation.getDistrict());
                    }
                });

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }

        mlocationClient.stopLocation();
    }
}
