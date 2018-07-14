package com.qunxianghui.gxh.fragments.homeFragment;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.ScanActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.db.ChannelManage;
import com.qunxianghui.gxh.fragments.homeFragment.activity.AbleNewSearchActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ChannelActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.SearchActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.ColumnHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener, AMapLocationListener {
    private static HomeFragment homeFragment;
    @BindView(R.id.ib_home_camera)
    TextView ibHomeCamera;
    @BindView(R.id.ib_home_search)
    ImageButton ibHomeSearch;
    @BindView(R.id.ib_home_scan)
    ImageButton ibHomeScan;
    //    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    //TabLayout标签
    private ColumnHorizontalScrollView mColumnHorizontalScrollView; // 自定义HorizontalScrollView
    private LinearLayout mRadioGroup_content; // 每个标题
    private LinearLayout ll_more_columns; // 右边+号的父布局
    private ImageView button_more_columns; // 标题右边的+号
    private RelativeLayout rl_column; // +号左边的布局：包括HorizontalScrollView和左右阴影部分
    public ImageView shade_left; // 左阴影部分
    public ImageView shade_right; // 右阴影部分
    private int columnSelectIndex = 0; // 当前选中的栏目索引
    private int mItemWidth = 0; // Item宽度：每个标题的宽度
    private int mScreenWidth = 0; // 屏幕宽度
    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    private int REQUEST_CODE_SCAN = 111;
    // tab集合：HorizontalScrollView的数据源
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    Unbinder unbinder;
    private String cityCode;
    private String adCode;
    private String cityinfo;
    private int REQUEST_PERMISSION_CAMERA_CODE=10010;

    @Override
    public int getLayoutId() {
        mScreenWidth = Utils.getWindowsWidth(mActivity);
        mItemWidth = mScreenWidth / 7; // 一个Item宽度为屏幕的1/7
        return R.layout.home_layout;
    }
    @Override
    public void initDatas() {
        //频道列表（用户订阅的频道）
        OkGo.<String>post(Constant.CHANNEL_GETLIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                if (HttpStatusUtil.getStatus(json)) {
                    setChannelData(json);
                    setChangelView();
                } else {
                    ToastUtils.showShortToast(getContext(), HttpStatusUtil.getStatusMsg(json));
                }
            }
        });

        // + 号监听
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logger.d("onClick-->:" + userChannelList.toString());
                Intent intent_channel = new Intent(mActivity.getApplicationContext(), ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ChannelActivity.USER_CHANNEL, (userChannelList));
                intent_channel.putExtras(bundle);
                startActivityForResult(intent_channel, CHANNELREQUEST);
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
    public void initViews(View view) {
        mColumnHorizontalScrollView = mActivity.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = mActivity.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = mActivity.findViewById(R.id.ll_more_columns);
        rl_column = mActivity.findViewById(R.id.rl_column);
        button_more_columns = mActivity.findViewById(R.id.button_more_columns);
        shade_left = mActivity.findViewById(R.id.shade_left);
        shade_right = mActivity.findViewById(R.id.shade_right);
        mViewPager = mActivity.findViewById(R.id.home_view_pager);
        //一进来进行定位
        RequestHomeLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        tvHomeLocation.setText(mActivity.getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
    }

    private void RequestHomeLocation() {

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
            }else {
              ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_CAMERA_CODE);

            }
        }else {
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
    private void setChangelView() {
//        initColumnData();
        initTabColumn();
        initFragment();
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
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(mActivity, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(mActivity);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            // 单击监听
            columnTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(mActivity.getApplicationContext(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).getUserChannel());
    }
    @Override
    protected void initListeners() {
        ibHomeCamera.setOnClickListener(this);
        ibHomeSearch.setOnClickListener(this);
        ibHomeScan.setOnClickListener(this);
        tvHomeLocation.setOnClickListener(this);
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
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_home_camera:            //爆料
                if (!LoginMsgHelper.isLogin(getContext())) {
                    toActivity(LoginActivity.class);
                    mActivity.finish();
                    return;
                }
                toActivity(BaoLiaoActivity.class);
                break;
            case R.id.ib_home_scan:            //扫描二维码
                toActivity(ScanActivity.class);
                break;
            case R.id.ib_home_search:          //搜索
                toActivity(SearchActivity.class);
                break;
            case R.id.tv_home_location:
//                toActivity(LocationActivity.class);
                Intent intent = new Intent(mActivity, AbleNewSearchActivity.class);
                startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    userChannelList = DragAdapter.channelList;
                    setChangelView();
                }
                break;
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                    tvHomeLocation.setText(city);
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
                    tvHomeLocation.setText(city);
                } else {
                    tvHomeLocation.setText(currCity);
                    cityCode = aMapLocation.getCityCode();
                    adCode = aMapLocation.getAdCode();
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
        editor.putString("X-cityId", cityCode);
        editor.putString("X-areaId", adCode);
        editor.commit();
    }

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }
}
