package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;


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
        mTvAddress.setText(TextUtils.isEmpty(currcity) ? SPUtils.getLocation("X-cityName") : currcity);
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
            mTitles[i] = dataBean.name;
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

    @OnClick({R.id.iv_back, R.id.tv_address, R.id.iv_video_more_columns,R.id.ll_home_video_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_home_video_search:
                toActivity(SearchVideoActivity.class);
                break;
            case R.id.tv_address:
                toActivity(LocationActivity.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == VIDEO_CHANNELRESULT) {
            userChannelList.clear();
            userChannelList.addAll(DragAdapter.channelList);
            DragAdapter.channelList.clear();
            DragAdapter.channelList = null;
            mFragments.clear();
            setFragments(userChannelList);
            setViewpager();
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
