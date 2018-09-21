package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoChannel;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.observer.PublishBiaoliao;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.activity.LocalServiceChannelActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.PermissionPageUtils;
import com.qunxianghui.gxh.utils.SPUtils;

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

public class LocationFragment extends BaseFragment {
    @BindView(R.id.tv_localcircle_location)
    TextView mTvLocalcircleLocation;
    @BindView(R.id.slidingLocationTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.local_view_pager)
    ViewPager mLocalViewPager;

    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    private ArrayList<ChannelItem> userChannelList = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private String[] mTitles;
    public final static int POST_CHANNELRESULT = 100; // 返回码

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (LocationActivity.sLocationCanChange) {
//            LocationActivity.sLocationCanChange = false;
//            for (int i = 0; i < fragments.size(); i++) {
//                ((LocationDetailFragment) fragments.get(i)).mIsChange = true;
//            }
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(PublishBiaoliao publishBiaoliao) {
        initData();
    }

    @Override
    public void initData() {
        OkGo.<HomeVideoChannel>post(Constant.LOCAL_POST_SUB_URL).execute(new JsonCallback<HomeVideoChannel>() {
            @Override
            public void onSuccess(Response<HomeVideoChannel> response) {
                if (response.body().code == 200 && response.body().data != null) {
                    fragments.clear();
                    List<ChannelItem> datas = response.body().data;
                    setFragments(datas);
                    setViewpager();
                    userChannelList.addAll(datas);
                } else {
                    asyncShowToast(HttpStatusUtil.getStatusMsg(response.body().message));
                }
            }
        });
    }

    private void setFragments(List<ChannelItem> datas) {
        mTitles = new String[datas.size()];
        LocationDetailFragment locationDetailFragment;
        Bundle bundle;
        for (int i = 0; i < datas.size(); i++) {
            ChannelItem dataBean = datas.get(i);
            locationDetailFragment = new LocationDetailFragment();
            bundle = new Bundle();
            bundle.putInt("channel_id", dataBean.id);
            locationDetailFragment.setArguments(bundle);
            mTitles[i] = dataBean.channelName;
            fragments.add(locationDetailFragment);
        }
    }

    private void setViewpager() {
        MineTabViewPagerAdapter mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getChildFragmentManager(), fragments, mTitles);
        mLocalViewPager.setAdapter(mineTabViewPagerAdapter);
        mSlidingTabLayout.setViewPager(mLocalViewPager);
        mLocalViewPager.setOffscreenPageLimit(fragments.size() - 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        String currcity = SPUtils.getLocation("currcity");
        mTvLocalcircleLocation.setText(currcity);
        if (LocationActivity.sLocationCanChange) {
            LocationActivity.sLocationCanChange = false;
            initData();
        }
    }

    @Override
    protected void initListeners() {
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mLocalViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CITY_SELECT_RESULT_FRAG && resultCode == RESULT_OK) {
            mTvLocalcircleLocation.setText(SPUtils.getLocation("currcity"));
        } else if (requestCode == 0x0011 && resultCode == 0x0022) {
            userChannelList = Hawk.get("USER_VIDEO_CHANNEL", new ArrayList<ChannelItem>());
            fragments.clear();
            setFragments(userChannelList);
            setViewpager();

        }
    }

    @OnClick({R.id.tv_localcircle_location, R.id.iv_more_columns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_localcircle_location:
                if (Build.VERSION.SDK_INT >= 23) {
                    boolean hasLocationPermission =
                            ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                    if (!hasLocationPermission) {
                        if (LocationFragment.this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {//说明被拒绝过，需要解释原因
                            LocationFragment.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0x0011);
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
            case R.id.iv_more_columns:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                } else {
                    toActivityWithResult(LocalServiceChannelActivity.class, 0x0011);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0x0011) {
            startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

