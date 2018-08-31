package com.qunxianghui.gxh.ui.fragments.homeFragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CityInfo;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ChannelActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.SearchActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


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
    private ClipboardManager mClipboardManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor(){
        Window window = mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }

    @Override
    public void initViews(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //定位
                setLocation();
            } else {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 10010);
            }
        } else {
            setLocation();

        }
    }

    private void setLocation() {
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

    @Override
    public void initData() {
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        //频道列表（用户订阅的频道）
        OkGo.<String>post(Constant.CHANNEL_GETLIST)
                .execute(new JsonCallback<String>() {
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
            userChannelList.clear();
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
        String currcity = SPUtils.getLocation("currcity");
        mTvHomeLocation.setText(currcity);
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mViewPager.setAdapter(new NewsFragmentPagerAdapter(getChildFragmentManager(), userChannelList));
        mTopSlidingLayout.setViewPager(mViewPager);
    }

    @OnClick({R.id.ll_home_location, R.id.ll_home_search, R.id.iv_more_columns, R.id.iv_home_paste_artical})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_location:
                startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
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
            case R.id.iv_home_paste_artical:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                } else {
                    clipArticalData();
                }
                break;
        }
    }

    /*粘贴文章*/
    private void clipArticalData() {
        //粘贴板有数据并且是文本
        if (mClipboardManager.hasPrimaryClip() && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            final ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
            final String text = (String) item.getText();

            OkGo.<CommonBean>post(Constant.PAST_ARTICAL_URL)
                    .params("url", text)
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(Response<CommonBean> response) {
                            int code = response.body().code;
                            String msg = response.body().msg;
                            if (code == 0) {
                                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                                intent.putExtra("url", text);
                                startActivity(intent);
                            } else {
                                asyncShowToast(msg);
                            }
                        }

                        @Override
                        public void onError(Response<CommonBean> response) {
                            super.onError(response);
                            asyncShowToast(response.message());
                        }
                    });
    } else
        {
            asyncShowToast("您复制的不是文章链接");

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
                    mTvHomeLocation.setText(SPUtils.getLocation("currcity"));
                    break;
                }
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                String cityName = aMapLocation.getDistrict();
                String city = SPUtils.getLocation("currcity");
                if (!TextUtils.isEmpty(city)) {
                    mTvHomeLocation.setText(city);
                } else {
                    mTvHomeLocation.setText(cityName);
                    requestCityInfo(aMapLocation.getLatitude(), aMapLocation.getLongitude(), cityName);
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
                    }
                });
    }

    /*sp存储一些信息*/
    private void saveLocationData(String cityCode, String areaId, String cityName) {
        SPUtils.saveLocation("X-cityId", cityCode);
        SPUtils.saveLocation("X-areaId", areaId);
        SPUtils.saveLocation("currcity", cityName);
        OkGo.getInstance().getCommonHeaders().put("X-cityId", cityCode);
        OkGo.getInstance().getCommonHeaders().put("X-areaId", areaId);
    }

}
