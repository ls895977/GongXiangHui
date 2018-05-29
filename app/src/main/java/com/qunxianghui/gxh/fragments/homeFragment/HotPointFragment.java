package com.qunxianghui.gxh.fragments.homeFragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.BianMinServiceActivity;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinGridAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter1;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeLunBoBean;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeAirActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeVideoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.GlideImageLoader;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotPointFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.recyclerview_list)
    RecyclerView recyclerviewList;
    @BindView(R.id.ll_home_paste_artical)
    LinearLayout llHomePasteArtical;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<MoreTypeBean> mDatas;
    Unbinder unbinder;
    private Banner viewpagerHome;
    private int[] icons = {R.mipmap.ic_test_0, R.mipmap.ic_test_1, R.mipmap.ic_test_2, R.mipmap.ic_test_3, R.mipmap.ic_test_0, R.mipmap.ic_test_1, R.mipmap.ic_test_2, R.mipmap.ic_test_3};
    private ClipboardManager mClipboardManager;
    private GridView grid_home_navigator;
    //首页导航的坐标匹配
    private int[] images = {R.mipmap.home_top_tianqi, R.mipmap.home_top_video, R.mipmap.home_top_life_circle
            , R.mipmap.home_top_saler, R.mipmap.home_top_bian_min,};
    private String[] iconName = {"天气", "视频", "本地服务", "优选", "便民"};


    private HomeItemListAdapter homeItemListAdapter;


    private HomeItemListAdapter1 homeItemListAdapter1;
    private View headerNavigator;
    private View footer;
    private View headerVp;
    private int mCurrentCounter;
    private List<HomeNewListBean.DataBean> dataList;
    private int count = 0;
    private String image_url;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();


        //首页新闻数据
        OkGo.<String>get(Constant.HOME_NEWS_LIST_URL)
                .params("limit", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });


        recyclerviewList.setLayoutManager(new LinearLayoutManager(mActivity));

        footer = LayoutInflater.from(mActivity).inflate(R.layout.layout_footer, recyclerviewList, false);
        headerNavigator = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_navigator, recyclerviewList, false);
        headerVp = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_viewpager, recyclerviewList, false);
        View empty = LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, recyclerviewList, false);
        viewpagerHome = headerVp.findViewById(R.id.viewpager_home);
        grid_home_navigator = headerNavigator.findViewById(R.id.grid_home_navigator);

//        List<Integer> list = new ArrayList<>();
//        list.add(R.mipmap.ic_test_0);
//        list.add(R.mipmap.ic_test_1);
//        list.add(R.mipmap.ic_test_2);
//        list.add(R.mipmap.ic_test_3);
//        list.add(R.mipmap.ic_test_4);
//        list.add(R.mipmap.ic_test_5);
//        list.add(R.mipmap.ic_test_6);
//
//        List<String> titles = new ArrayList<>();
//        titles.add("图片1");
//        titles.add("图片2");
//        titles.add("图片3");
//        titles.add("图片4");
//        titles.add("图片5");
//        titles.add("图片6");
//        titles.add("图片7");


        //加載首頁那个导航图
        initGridHomeNavigator();
        //加载首页轮播图
        initLunBoShow();
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
            List<String> titles = new ArrayList<>();
            List<String> imags = new ArrayList<>();

            String image_src = null;
            String title = null;


            for (int i = 0; i < lunboData.size(); i++) {
                image_src = lunboData.get(i).getImage_src();  //图片
                title = lunboData.get(i).getTitle();        //title

                //轮播图跳转的url
                image_url = lunboData.get(i).getImage_url();
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
                            intent.putExtra("url", image_url);
                            startActivity(intent);
                        }
                    })
                    .start();
        }


    }

    private void parseData(String body) {
        final HomeNewListBean homeNewListBean = GsonUtil.parseJsonWithGson(body, HomeNewListBean.class);
        dataList = homeNewListBean.getData();

        homeItemListAdapter1 = new HomeItemListAdapter1();
        homeItemListAdapter1.setNewData(dataList);
        homeItemListAdapter1.addHeaderView(headerNavigator);
        homeItemListAdapter1.addHeaderView(headerVp, 1);
        homeItemListAdapter1.addFooterView(footer);

        //上拉加载更多哦
        homeItemListAdapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerviewList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if ((mCurrentCounter >= dataList.size())) {
                            homeItemListAdapter1.loadMoreEnd();
                        } else {
                            boolean isErr = false;
                            if (isErr) {
                                //成功获取数据
                                homeItemListAdapter1.addData(dataList);
                                mCurrentCounter = homeItemListAdapter1.getData().size();
                                //主动调用加载完成 停止加载
                                homeItemListAdapter1.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                asyncShowToast("加载失败");
                                homeItemListAdapter1.loadMoreFail();
                            }
                        }
                    }
                }, 1000);

            }
        }, recyclerviewList);
        //下来刷新
        homeItemListAdapter1.setUpFetchEnable(true);
        homeItemListAdapter1.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                startUpFetch();
            }
        });

        //设置加载出来看的动画
        homeItemListAdapter1.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        recyclerviewList.setAdapter(homeItemListAdapter1);


        //对列表设置点击事件
        homeItemListAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String url = homeNewListBean.getData().get(position).getUrl();
                final Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });
    }

    private void startUpFetch() {

        count++;

        homeItemListAdapter1.setUpFetching(true);

        recyclerviewList.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeItemListAdapter1.addData(0, dataList);
                homeItemListAdapter1.setUpFetching(false);
                if (count > 5) {
                    homeItemListAdapter1.setUpFetchEnable(false);

                }
            }
        }, 300);
    }


    private void initGridHomeNavigator() {
        final BianMinGridAdapter homegridNavigator = new BianMinGridAdapter(mActivity, images, iconName);
        grid_home_navigator.setAdapter(homegridNavigator);
        grid_home_navigator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
//                        toActivity(LocationServiceActivity.class);

                        Log.e(TAG, "...................本地服务怎么找不到");
                        intent = new Intent(mActivity, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.BenDiService);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳转优惠
//                        toActivity(SalerActivity.class);
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
    public void initViews(View view) {

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
    protected void initListeners() {
        llHomePasteArtical.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_paste_artical:
                //粘贴板有数据并且是文本
                if (mClipboardManager.hasPrimaryClip() && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    final ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
                    final CharSequence text = item.getText();
                    Intent intent = new Intent(mActivity, ProtocolActivity.class);
                    intent.putExtra("url", text);
                    startActivity(intent);
                } else {
                    asyncShowToast("没有找到粘贴的内容");
                }
                break;
        }
    }


}
