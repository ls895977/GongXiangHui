package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoChannel;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.activity.LocalServiceChannelActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
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
                    asyncShowToast(HttpStatusUtil.getStatusMsg(response.body().msg));
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
            mTitles[i] = dataBean.name;
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
        } else if (resultCode == POST_CHANNELRESULT) {
            userChannelList.clear();
            userChannelList.addAll(DragAdapter.channelList);
            DragAdapter.channelList.clear();
            DragAdapter.channelList = null;
            fragments.clear();
            setFragments(userChannelList);
            setViewpager();

        }
    }

    @OnClick({R.id.tv_localcircle_location, R.id.iv_more_columns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_localcircle_location:
                startActivityForResult(new Intent(mActivity, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
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
}

