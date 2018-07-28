package com.qunxianghui.gxh.ui.fragments.homeFragment;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.db.ChannelManage;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.AbleNewSearchActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ChannelActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.SearchActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeFragment extends BaseFragment implements AMapLocationListener {

    @BindView(R.id.tv_home_location)
    TextView mTvHomeLocation;
    @BindView(R.id.home_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_more_columns)
    ImageView mIvMoreColumns;
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mTopSlidingLayout;

    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    private int REQUEST_CODE_SCAN = 111;
    private ArrayList<ChannelItem> userChannelList = new ArrayList<>();
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    public void initViews(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //定位
                mlocationClient = new AMapLocationClient(mActivity);
                //初始化定位参数
                mLocationOption = new AMapLocationClientOption();
                //设置返回地址信息，默认为true
                mLocationOption.setNeedAddress(true);
                //设置定位监听
                mlocationClient.setLocationListener(this);
                //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //设置定位间隔,单位毫秒,默认为2000ms
                mLocationOption.setInterval(2000);
                //设置定位参数
                mlocationClient.setLocationOption(mLocationOption);
                mlocationClient.startLocation();
            } else {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 10010);

            }
        } else {
            //定位
            mlocationClient = new AMapLocationClient(mActivity);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置返回地址信息，默认为true
            mLocationOption.setNeedAddress(true);
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void initData() {
        //频道列表（用户订阅的频道）
        OkGo.<String>post(Constant.CHANNEL_GETLIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                if (HttpStatusUtil.getStatus(json)) {
                    setChannelData(json);
                    initFragment();
                } else {
                    ToastUtils.showShortToast(getContext(), HttpStatusUtil.getStatusMsg(json));
                }
            }
        });
    }

    @Override
    protected void initListeners() {
        mTopSlidingLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * ==================频道列表（用户订阅的频道）=====================
     */
    private void setChannelData(String body) {
        ChannelGetallBean bean = GsonUtil.parseJsonWithGson(body, ChannelGetallBean.class);
        if (null != bean) {
            List<ChannelGetallBean.DataBean> datas = bean.getData();
            for (int i = 0; i < datas.size(); i++) {
                ChannelGetallBean.DataBean dataBean = datas.get(i);
                ChannelItem item = new ChannelItem(dataBean.getChannel_id(), dataBean.getChannel_name(), i, 1);
                userChannelList.add(item);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvHomeLocation.setText(mActivity.getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments.clear();//清空
        int count = userChannelList.size();
        HotPointFragment newFragment;
        Bundle bundle;
        for (int i = 0; i < count; i++) {
            newFragment = new HotPointFragment();
            bundle = new Bundle();
            bundle.putInt("channel_id", userChannelList.get(i).getId());
            newFragment.setArguments(bundle);
            fragments.add(newFragment);
        }
        mViewPager.setAdapter(new NewsFragmentPagerAdapter(getChildFragmentManager(), fragments, userChannelList));
        mTopSlidingLayout.setViewPager(mViewPager);
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).getUserChannel());
    }

    @OnClick({R.id.ll_home_location, R.id.ll_home_search, R.id.iv_more_columns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_location:
                //                toActivity(LocationActivity.class);
                startActivityForResult(new Intent(mActivity, AbleNewSearchActivity.class), CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.ll_home_search:
                toActivity(SearchActivity.class);
                break;
            case R.id.iv_more_columns:
                Intent intent_channel = new Intent(mActivity.getApplicationContext(), ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ChannelActivity.USER_CHANNEL, (userChannelList));
                intent_channel.putExtras(bundle);
                startActivityForResult(intent_channel, CHANNELREQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    userChannelList = DragAdapter.channelList;
                    initFragment();
                }
                break;
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                    mTvHomeLocation.setText(city);
                    break;
                }
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                String currCity = aMapLocation.getDistrict();
                String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                if (!TextUtils.isEmpty(city)) {
                    mTvHomeLocation.setText(city);
                } else {
                    mTvHomeLocation.setText(currCity);
                    String cityCode = aMapLocation.getCityCode();
                    String adCode = aMapLocation.getAdCode();
                    SaveLocationData(cityCode, adCode);
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "locations Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
        mlocationClient.stopLocation();
    }

    /*sp存储一些信息*/
    private void SaveLocationData(String cityCode, String adCode) {
        SharedPreferences location = getActivity().getSharedPreferences("location", 0);
        SharedPreferences.Editor editor = location.edit();
        editor.putString("X-cityId", cityCode).putString("X-areaId", adCode).apply();
    }

}