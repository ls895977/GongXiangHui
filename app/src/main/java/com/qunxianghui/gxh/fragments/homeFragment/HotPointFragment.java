package com.qunxianghui.gxh.fragments.homeFragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.BianMinServiceActivity;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinGridAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeSeachLocationActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.HomeVideoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.LocationServiceActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.NewSearchActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.SalerActivity;
import com.qunxianghui.gxh.utils.GlideImageLoader;
import com.qunxianghui.gxh.widget.GloriousRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotPointFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.recyclerview_list)
    GloriousRecyclerView recyclerviewList;
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

    @Override
    public int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        //随机数用来标记item界面的类型
        Random random = new Random();
        for (int i = 0; i < icons.length; i++) {
            MoreTypeBean more = new MoreTypeBean();
            more.pic = icons[i];
            more.type = random.nextInt(3);
            mDatas.add(more);
        }
        recyclerviewList.setLayoutManager(new LinearLayoutManager(mActivity));
        HomeItemListAdapter adapter = new HomeItemListAdapter(mDatas);
        View footer = LayoutInflater.from(mActivity).inflate(R.layout.layout_footer, recyclerviewList, false);
        View headerNavigator = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_navigator, recyclerviewList, false);
        View headerVp = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_viewpager, recyclerviewList, false);
        View empty = LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, recyclerviewList, false);
        adapter.setOnItemClickListener(new HomeItemListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

                Toast.makeText(mActivity, "点击了：" + position + "行", Toast.LENGTH_SHORT).show();
                toActivity(NewsDetailActivity.class);
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(mActivity, "长按点击了：" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerviewList.setAdapter(adapter);
        recyclerviewList.addHeaderView(headerNavigator);
        recyclerviewList.addHeaderView2(headerVp);
        recyclerviewList.addFooterView(footer);
        recyclerviewList.setEmptyView(empty);

//找控件
        viewpagerHome = headerVp.findViewById(R.id.viewpager_home);

        grid_home_navigator = headerNavigator.findViewById(R.id.grid_home_navigator);

        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.ic_test_0);
        list.add(R.mipmap.ic_test_1);
        list.add(R.mipmap.ic_test_2);
        list.add(R.mipmap.ic_test_3);
        list.add(R.mipmap.ic_test_4);
        list.add(R.mipmap.ic_test_5);
        list.add(R.mipmap.ic_test_6);
        List<String> titles = new ArrayList<>();
        titles.add("图片1");
        titles.add("图片2");
        titles.add("图片3");
        titles.add("图片4");
        titles.add("图片5");
        titles.add("图片6");
        titles.add("图片7");
        viewpagerHome.setImages(list).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setBannerTitles(titles)
                .setDelayTime(3000)
                .setBannerAnimation(Transformer.Tablet)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(mActivity, "点击了" + position + 1, Toast.LENGTH_SHORT).show();
                    }
                })
                .start();

        //加載首頁那个导航图
        initGridHomeNavigator();
    }
    private void initGridHomeNavigator() {
        final BianMinGridAdapter homegridNavigator = new BianMinGridAdapter(mActivity, images, iconName);
        grid_home_navigator.setAdapter(homegridNavigator);
        grid_home_navigator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                switch (position) {
                    case 0:
                        //跳转天气
                        toActivity(NewSearchActivity.class);
                        break;
                    case 1:
                        //跳转视频
                        toActivity(HomeVideoActivity.class);
                        break;
                    case 2:
                        //跳转生活圈
                      toActivity(LocationServiceActivity.class);

//                        Log.e(TAG,"...................本地服务怎么找不到");
//                        intent = new Intent(mActivity, ProtocolActivity.class);
//                        intent.putExtra("title", iconName[position]);
//                        intent.putExtra("url", Constant.BenDiService);
//                        startActivity(intent);
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
