package com.qunxianghui.gxh.fragments.homeFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.BianMinServiceActivity;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinGridAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter1;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.home.HomeLunBoBean;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.fragments.homeFragment.activity.AbleNewSearchActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeAirActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeVideoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GlideImageLoader;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.CustomLoadMoreView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionGen;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotPointFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.swip)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview_list)
    RecyclerView recyclerviewList;
    @BindView(R.id.ll_home_paste_artical)
    LinearLayout llHomePasteArtical;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<MoreTypeBean> mDatas;
    Unbinder unbinder;
    private Banner viewpagerHome;
    private ClipboardManager mClipboardManager;
    private RecyclerView grid_home_navigator;
    //首页导航的坐标匹配
    private int[] images = {R.mipmap.home_top_tianqi, R.mipmap.home_top_video, R.mipmap.home_top_life_circle
            , R.mipmap.home_top_saler, R.mipmap.home_top_bian_min,};
    private String[] iconName = {"天气", "视频汇", "本地服务", "精选", "便民"};

    private HomeItemListAdapter1 homeItemListAdapter1;
    private View headerNavigator;
    private View footer;
    private View headerVp;
    private List<HomeNewListBean> dataList = new ArrayList<>();
    private int count = 0;
    private int total = 0;
    private int mChannelId = 0;
    private TextView mhomeLocalLocation;
    private View localLocationView;
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
    @SuppressLint("NewApi")
    @Override
    public void initViews(View view) {
        if (getArguments() != null) {
            mChannelId = getArguments().getInt("channel_id");
        }
        recyclerviewList.setLayoutManager(new LinearLayoutManager(mActivity));
        footer = LayoutInflater.from(mActivity).inflate(R.layout.layout_footer, recyclerviewList, false);
        if (mChannelId == -1) {
            headerNavigator = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_navigator, recyclerviewList, false);
            grid_home_navigator = headerNavigator.findViewById(R.id.grid_home_navigator);
            headerVp = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_viewpager, recyclerviewList, false);
            viewpagerHome = headerVp.findViewById(R.id.viewpager_home);
            //加載首頁那个导航图
            initGridHomeNavigator();
            //加载首页轮播图
            initLunBoShow();
        } else if (mChannelId == 0) {
            localLocationView = LayoutInflater.from(mActivity).inflate(R.layout.home_local_location, null);
            mhomeLocalLocation = localLocationView.findViewById(R.id.tv_home_local_location);
            mhomeLocalLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, AbleNewSearchActivity.class);
                    startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                }
            });
        }
        View empty = LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, recyclerviewList, false);
        //这句是调取粘贴的系统服务
        mClipboardManager = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        PermissionGen.needPermission(HotPointFragment.this, 105,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
        );
    }
    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        //请求数据
        parseData();
        homeItemListAdapter1 = new HomeItemListAdapter1();
        homeItemListAdapter1.setNewData(dataList);
        if (mChannelId == -1) {
            homeItemListAdapter1.addHeaderView(headerNavigator);
            homeItemListAdapter1.addHeaderView(headerVp, 1);
        } else if (mChannelId == 0) {
            homeItemListAdapter1.addHeaderView(localLocationView);
        }
        homeItemListAdapter1.addFooterView(footer);
        //上拉加载更多哦
        homeItemListAdapter1.setLoadMoreView(new CustomLoadMoreView());
        homeItemListAdapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parseData();
                    }
                }, 1000);

            }
        }, recyclerviewList);

        //下来刷新
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 开始刷新，设置当前为刷新状态
                        swipeRefreshLayout.setRefreshing(true);
                        count = 0;
//                        parseData();
                        //首页下拉刷新
                        HomePullRefresh();
                    }

                }, 1000);
                Display display = mActivity.getWindowManager().getDefaultDisplay();
                int height = display.getHeight();
                Toast toast = Toast.makeText(mActivity, "已经为你推荐了" + dataList.size() + "条新闻", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, height/7);
                toast.show();
            }
        });
//        //设置加载出来看的动画
        homeItemListAdapter1.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerviewList.setAdapter(homeItemListAdapter1);
    }
    /**
     * 首页下拉刷新 新的接口
     */
    private void HomePullRefresh() {
        OkGo.<LzyResponse<List<HomeNewListBean>>>post(Constant.HOME_PULL_REFRESH_URL)
                .params("channel_id", mChannelId)
                .execute(new DialogCallback<LzyResponse<List<HomeNewListBean>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<HomeNewListBean>>> response) {
                        if (response.body().code==0) {
                            swipeRefreshLayout.setRefreshing(false);
                            List<HomeNewListBean> list = response.body().data;
                            if (list == null || list.size() == 0) {
                                homeItemListAdapter1.loadMoreEnd();
                                return;
                            } else {
                                dataList.clear();
                                dataList.addAll(list);
                                total = dataList.size();
                                if (count + 10 <= total) {
                                    count += 10;
                                    homeItemListAdapter1.loadMoreComplete();
                                } else {
                                    homeItemListAdapter1.loadMoreEnd();
                                }
                            }
                            homeItemListAdapter1.notifyDataSetChanged();
                        }
                    }
                });
    }

    /**
     * 加载轮播图
     */
    private void initLunBoShow() {
        OkGo.<String>get(Constant.HOME_PAGE_LUNBO_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseHomeLunBoPager(response.body());
            }
        });
    }
    private void parseHomeLunBoPager(String body) {
        final HomeLunBoBean homeLunBoBean = GsonUtils.jsonFromJson(body, HomeLunBoBean.class);

        if (homeLunBoBean.getCode() == 0) {
            final List<HomeLunBoBean.DataBean> lunboData = homeLunBoBean.getData();
            //轮播图的标题
            List<String> titles = new ArrayList<>();
            //轮播图的图片
            List<String> imags = new ArrayList<>();
            String image_src;
            String title;
            for (int i = 0; i < lunboData.size(); i++) {
                image_src = lunboData.get(i).getImage_src();  //图片
                title = lunboData.get(i).getTitle();        //title
//                //轮播图跳转的url
//                image_url = lunboData.get(i).getImage_url();
                imags.add(image_src);
                titles.add(title);
            }
            viewpagerHome.setImages(imags).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    .setBannerTitles(titles)
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
    private void parseData() {
        //首页新闻数据
        OkGo.<LzyResponse<List<HomeNewListBean>>>get(Constant.HOME_NEWS_LIST_URL)
                .params("limit", 12)
                .params("skip", count)
                .params("channel_id", mChannelId)
                .execute(new DialogCallback<LzyResponse<List<HomeNewListBean>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<HomeNewListBean>>> response) {
                        if (response.body().code==0) {
                            swipeRefreshLayout.setRefreshing(false);
                            List<HomeNewListBean> list = response.body().data;
                            if (list == null || list.size() == 0) {
                                homeItemListAdapter1.loadMoreEnd();
                                return;
                            } else {
                                if (count == 0) {
                                    dataList.clear();
                                }
                                dataList.addAll(list);
                                total = dataList.size();
                                if (count + 10 <= total) {
                                    count += 10;
                                    homeItemListAdapter1.loadMoreComplete();
                                } else {
                                    homeItemListAdapter1.loadMoreEnd();
                                }
                            }
                            homeItemListAdapter1.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initGridHomeNavigator() {
        BianMinGridAdapter homegridNavigator = new BianMinGridAdapter(mActivity, images, iconName);
        grid_home_navigator.setAdapter(homegridNavigator);
        homegridNavigator.setOnClickListener(new BianMinGridAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
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
//                        toActivity(CompanySetActivity.class);
                        Log.e(TAG, "...................本地服务怎么找不到");
                        intent = new Intent(mActivity, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.BenDiService);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳转优惠
                        intent = new Intent(mActivity, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.YouXuan);
                        startActivity(intent);
                        break;
                    case 4:
                        //跳转便民
                        toActivity(BianMinServiceActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    protected void initListeners() {
        llHomePasteArtical.setOnClickListener(this);
        //对列表设置点击事件
        homeItemListAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String url = dataList.get(position).getUrl();
                final int uuid = dataList.get(position).getUuid();
                final int id = dataList.get(position).getId();
                final String title = dataList.get(position).getTitle();

                final Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("uuid", uuid);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
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
    public void onDestroy() {
        super.onDestroy();
        mActivity.finish();
    }

    /**
     * 粘贴文章的处理
     *
     * @param v
     */
    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_paste_artical:
                if (!LoginMsgHelper.isLogin(getContext())) {
                    toActivity(LoginActivity.class);
                    mActivity.finish();
                    return;
                }
                //粘贴板有数据并且是文本
                if (mClipboardManager.hasPrimaryClip() && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    final ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
                    final CharSequence text = item.getText();
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("url", text);
                    startActivity(intent);
                } else {
                    asyncShowToast("没有找到粘贴的内容");
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    mhomeLocalLocation.setText(getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
                    break;

                }
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
