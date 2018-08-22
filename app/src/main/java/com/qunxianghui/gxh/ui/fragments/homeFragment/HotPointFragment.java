package com.qunxianghui.gxh.ui.fragments.homeFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinGridAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.home.HomeLunBoBean;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.BianMinServiceActivity;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.HomeAirActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.HomeVideoActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.GlideImageLoader;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.CustomLoadMoreView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotPointFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.sw)
    SwipeRefreshLayout mSw;
    private Banner viewpagerHome;
    private RecyclerView grid_home_navigator;
    //首页导航的坐标匹配
    private int[] images = {R.mipmap.home_top_tianqi, R.mipmap.home_top_video, R.mipmap.home_top_life_circle
            , R.mipmap.home_top_saler, R.mipmap.home_top_bian_min,};
    private String[] iconName = {"天气", "视频汇", "本地服务", "精选", "便民"};
    private HomeItemListAdapter homeItemListAdapter;
    private List<HomeNewListBean> dataList = new ArrayList<>();
    private int mCount = 0;
    private int mChannelId = 0;
    private TextView mhomeLocalLocation;
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot_point;
    }

    @SuppressLint("NewApi")
    @Override
    public void initViews(View view) {
        if (getArguments() != null) {
            mChannelId = getArguments().getInt("channel_id");
        }
        homeItemListAdapter = new HomeItemListAdapter();
        homeItemListAdapter.setNewData(dataList);
        if (mChannelId == -1) {
            View headerNavigator = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_navigator, mRv, false);
            grid_home_navigator = headerNavigator.findViewById(R.id.grid_home_navigator);
            View headerVp = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_viewpager, mRv, false);
            viewpagerHome = headerVp.findViewById(R.id.viewpager_home);
            //加載首頁那个导航图//加载首页轮播图
            initGuideAndBanner();
            homeItemListAdapter.addHeaderView(headerNavigator);
            homeItemListAdapter.addHeaderView(headerVp, 1);
        }
    }
    @Override
    public void initData() {
        //首页新闻数据
        OkGo.<CommonResponse<List<HomeNewListBean>>>get(Constant.HOME_NEWS_LIST_URL)
                .params("limit", 12)
                .params("skip", mCount)
                .params("channel_id", mChannelId)
                .execute(new JsonCallback<CommonResponse<List<HomeNewListBean>>>() {
                    @Override
                    public void onSuccess(Response<CommonResponse<List<HomeNewListBean>>> response) {
                        setData(response);
                    }
                });
    }
    /**
     * 首页下拉刷新 新的接口
     */
    private void homePullRefresh() {
        OkGo.<CommonResponse<List<HomeNewListBean>>>post(Constant.HOME_PULL_REFRESH_URL)
                .params("channel_id", mChannelId)
                .execute(new JsonCallback<CommonResponse<List<HomeNewListBean>>>() {
                    @Override
                    public void onSuccess(Response<CommonResponse<List<HomeNewListBean>>> response) {
                        mSw.setRefreshing(false);
                        setData(response);
                        Display display = mActivity.getWindowManager().getDefaultDisplay();
                        int height = display.getHeight();
                        Toast toast = Toast.makeText(mActivity, "已经为你推荐了" + dataList.size() + "条新闻", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, height / 8);
                        toast.show();
                    }
                });
    }

    private void setData(Response<CommonResponse<List<HomeNewListBean>>> response){
        if (response.body().code == 0) {
            List<HomeNewListBean> list = response.body().data;
            if (list == null || list.size() == 0) {
                homeItemListAdapter.loadMoreEnd();
            } else {
                if (mCount == 0) {
                    dataList.clear();
                }
                dataList.addAll(list);
                int total = dataList.size();
                if (mCount + 10 <= total) {
                    mCount += 10;
                    homeItemListAdapter.loadMoreComplete();
                } else {
                    homeItemListAdapter.loadMoreEnd();
                }
            }
        } else {
            homeItemListAdapter.loadMoreEnd();
        }
        homeItemListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListeners() {
        homeItemListAdapter.setLoadMoreView(new CustomLoadMoreView());
        homeItemListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
            }
        }, mRv);
        mSw.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSw.setColorSchemeResources(R.color.tab_color, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount = 0;
                //首页下拉刷新
                homePullRefresh();
            }
        });
//        //设置加载出来看的动画
        homeItemListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRv.setAdapter(homeItemListAdapter);
        //对列表设置点击事件
        homeItemListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeNewListBean homeNewListBean = dataList.get(position);
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", Constant.HOME_NEWS_DETAIL_URL);
                intent.putExtra("uuid", homeNewListBean.getUuid());
                intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN,""));
                intent.putExtra("id", homeNewListBean.getId());
                intent.putExtra("title", homeNewListBean.getTitle());
                startActivity(intent);
            }
        });
    }

    private void initGuideAndBanner() {
        BianMinGridAdapter homegridNavigator = new BianMinGridAdapter(mActivity, images, iconName);
        grid_home_navigator.setAdapter(homegridNavigator);
        homegridNavigator.setOnClickListener(new BianMinGridAdapter.OnItemClickListener() {
            @Override
            public void onpicItemClick(int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        //跳转天气
                        toActivity(HomeAirActivity.class);
                        break;
                    case 1:
                        //跳转视频
                        toActivity(HomeVideoActivity.class);
                        break;
                    case 2:
                        //跳转生活圈
                        intent = new Intent(mActivity,ProtocolActivity.class);
                        intent.putExtra("url", Constant.HOME_LOCAL_SERVICE_URL);
                        intent.putExtra("tag",1);
                        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN,""));
                        mActivity.startActivity(intent);
                        break;
                    case 3:
                        //跳转优惠
                        intent = new Intent(mActivity,ProtocolActivity.class);
                        intent.putExtra("url", Constant.HOME_GOOD_SELECT_URL);
                        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN,""));
                        intent.putExtra("tag",1);
                        mActivity.startActivity(intent);
                        break;
                    case 4:
                        //跳转便民
                        toActivity(BianMinServiceActivity.class);
                        break;
                }
            }
        });

        OkGo.<String>get(Constant.HOME_PAGE_LUNBO_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseHomeLunBoPager(response.body());
            }
        });
    }

    private void parseHomeLunBoPager(String body) {
        HomeLunBoBean homeLunBoBean = GsonUtils.jsonFromJson(body, HomeLunBoBean.class);
        if (homeLunBoBean.getCode() == 0) {
            final List<HomeLunBoBean.DataBean> lunboData = homeLunBoBean.getData();
            //轮播图的标题
            //轮播图的图片
            List<String> imags = new ArrayList<>();
            String image_src;
            for (int i = 0; i < lunboData.size(); i++) {
                image_src = lunboData.get(i).getImage_src();  //图片
//                //轮播图跳转的url
                imags.add(image_src);
            }
            viewpagerHome.setImages(imags).setBannerStyle(BannerConfig.NOT_INDICATOR)
                    .setDelayTime(3000)
                    .setBannerAnimation(Transformer.Tablet)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Intent intent = new Intent(mActivity, ProtocolActivity.class);
                            intent.putExtra("url", lunboData.get(position).getImage_url());
                            startActivity(intent);
                        }
                    })
                    .start();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    mhomeLocalLocation.setText(SPUtils.getLocation("currcity"));
                    break;

                }
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
