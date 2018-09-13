package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeVideoChannel;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.ui.fragments.homeFragment.fragments.HomeVideoListFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.PermissionPageUtils;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;

import static com.qunxianghui.gxh.ui.fragments.locationFragment.LocationFragment.CITY_SELECT_RESULT_FRAG;


/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity {

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.iv_video_more_columns)
    ImageView mIvVideoMoreColumns;
    @BindView(R.id.home_video_viewpager)
    ViewPager mHomeVideoViewpager;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_home_video_search)
    LinearLayout llHomeVideoSearch;

    private ArrayList<ChannelItem> userChannelList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    public final static int VIDEO_CHANNELREQUEST = 1; // 请求码
    public final static int VIDEO_CHANNELRESULT = 10; // 返回码

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initData() {
        OkGo.<HomeVideoChannel>post(Constant.VIDEO_SUB_URL).execute(new JsonCallback<HomeVideoChannel>() {
            @Override
            public void onSuccess(Response<HomeVideoChannel> response) {
                if (response.body().code == 200 && response.body().data != null) {
                    List<ChannelItem> datas = response.body().data;
                    setFragments(datas);
                    userChannelList.addAll(datas);
                    setViewpager();
                } else {
                    asyncShowToast(HttpStatusUtil.getStatusMsg(response.body().msg));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currcity = SPUtils.getLocation("currcity");
        mTvAddress.setText(currcity);
    }

    private void setFragments(List<ChannelItem> datas) {
        mTitles = new String[datas.size()];
        HomeVideoListFragment newFragment;
        Bundle bundle;
        for (int i = 0; i < datas.size(); i++) {
            ChannelItem dataBean = datas.get(i);
            newFragment = new HomeVideoListFragment();
            bundle = new Bundle();
            bundle.putInt("channel_id", dataBean.id);
            newFragment.setArguments(bundle);
            mTitles[i] = dataBean.channelName;
            mFragments.add(newFragment);
        }
    }

    private void setViewpager() {
        MineTabViewPagerAdapter tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mHomeVideoViewpager.setAdapter(tabViewPagerAdapter);
        mSlidingTabLayout.setViewPager(mHomeVideoViewpager);
        mHomeVideoViewpager.setOffscreenPageLimit(mFragments.size() - 1);
    }

    @Override
    protected void initListeners() {
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mHomeVideoViewpager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_address, R.id.iv_video_more_columns, R.id.ll_home_video_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_home_video_search:
                toActivity(SearchVideoActivity.class);
                break;
            case R.id.tv_address:
                if (Build.VERSION.SDK_INT >= 23) {
                    boolean hasLocationPermission =
                            ContextCompat.checkSelfPermission(HomeVideoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                    if (!hasLocationPermission) {
                        if (HomeVideoActivity.this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {//说明被拒绝过，需要解释原因
                            HomeVideoActivity.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0x0011);
                        } else {//没有权限
                            showMessageOKCancel();
                        }
                    } else {
                        startActivityForResult(new Intent(HomeVideoActivity.this, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                    }
                } else {
                    startActivityForResult(new Intent(HomeVideoActivity.this, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                }
                break;
            case R.id.iv_video_more_columns:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                } else {
                    toActivityWithResult(HomeVideoChannelActivity.class, 0x0011);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0x0011) {
            startActivityForResult(new Intent(HomeVideoActivity.this, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showMessageOKCancel() {
        new AlertDialog.Builder(HomeVideoActivity.this)
                .setMessage("请求定位权限")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PermissionPageUtils(HomeVideoActivity.this).jumpPermissionPage();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(HomeVideoActivity.this, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == VIDEO_CHANNELRESULT) {
            userChannelList.clear();
            userChannelList.addAll(DragAdapter.channelList);
            DragAdapter.channelList.clear();
            DragAdapter.channelList = null;
            mFragments.clear();
            setFragments(userChannelList);
            setViewpager();
        } else if (requestCode == 0x0011 && resultCode == 0x0022) {

        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
