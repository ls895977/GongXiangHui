package com.qunxianghui.gxh.ui.fragments.homeFragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.orhanobut.hawk.Hawk;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CityInfo;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.bean.home.PasteBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.observer.NewChannelEvent;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.HomeChannelActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.SearchActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.PermissionPageUtils;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private boolean mIsFirst = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor() {
        Window window = mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }

    @Override
    protected void setStatusBarTextColor() {
        StatusBarColorUtil.setStatusTextColor(false, mActivity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void updateChannel(NewChannelEvent event) {
        if (event != null && event.mList != null) {
            userChannelList = event.mList;
            initFragment();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initViews(View view) {
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //定位
                setLocation();
            } else {
                HomeFragment.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0x0011);
            }
        } else {
            setLocation();
        }
    }

    private void setLocation() {
        mlocationClient = new AMapLocationClient(mActivity);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setNeedAddress(true);
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

    @Override
    public void initData() {
        //频道列表（用户订阅的频道）
        if (!LoginMsgHelper.isLogin() && Hawk.get("USER_CHANNEL") != null && !TextUtils.isEmpty(Hawk.get("USER_CHANNEL").toString())) {
            ArrayList<ChannelItem> user_channel = Hawk.get("USER_CHANNEL", new ArrayList<ChannelItem>());
            if (!user_channel.isEmpty()) {
                userChannelList = user_channel;
                initFragment();
                return;
            }
        }
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
                if (Build.VERSION.SDK_INT >= 23) {
                    boolean hasLocationPermission =
                            ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                    if (!hasLocationPermission) {
                        if (HomeFragment.this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {//说明被拒绝过，需要解释原因
                            HomeFragment.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0x0011);
                        } else {//没有权限
                            showMessageOKCancel();
                        }
                    } else {
                        startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                    }
                } else {
                    startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                }
                break;
            case R.id.ll_home_search:
                toActivity(SearchActivity.class);
                break;
            case R.id.iv_more_columns:
                Intent intent = new Intent(getActivity(), HomeChannelActivity.class);
                intent.putExtra("dataSelected", userChannelList);
                startActivity(intent);
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
            final String text = item.getText().toString();
            OkGo.<PasteBean>post(Constant.PAST_ARTICAL_URL)
                    .params("url", text)
                    .execute(new JsonCallback<PasteBean>() {
                        @Override
                        public void onSuccess(Response<PasteBean> response) {
                            int code = response.body().code;
                            String msg = response.body().message;
                            if (code == 0) {
                                PasteBean.PasteDataBean data = response.body().data;
                                Intent intent = new Intent(getActivity(), AddAdvertActivity.class);
                                intent.putExtra("url", text);
                                intent.putExtra("uuid", data.uuid);
                                intent.putExtra("isPaste", true);
                                startActivity(intent);
                            } else {
                                asyncShowToast(msg);
                            }
                        }

                        @Override
                        public void onError(Response<PasteBean> response) {
                            super.onError(response);
                            asyncShowToast("服务器忙..");
                        }
                    });
        } else {
            asyncShowToast("您复制的不是文章链接");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
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
//                if (!TextUtils.isEmpty(city)) {
//                    mTvHomeLocation.setText(city);
//                } else {
                    mTvHomeLocation.setText(cityName);
                    requestCityInfo(aMapLocation.getLatitude(), aMapLocation.getLongitude(), cityName);
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "locations Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!mIsFirst && requestCode == 0x0011) {
            startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
        }
        mIsFirst = false;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showMessageOKCancel() {
        new AlertDialog.Builder(mActivity)
                .setMessage("请求定位权限")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PermissionPageUtils(mActivity).jumpPermissionPage();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                    }
                })
                .create()
                .show();

    }
}
