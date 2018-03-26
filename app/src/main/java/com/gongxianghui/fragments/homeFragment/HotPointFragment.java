package com.gongxianghui.fragments.homeFragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.gongxianghui.R;
import com.gongxianghui.activity.BianMinActiviry;
import com.gongxianghui.activity.ScanActivity;
import com.gongxianghui.adapter.homeAdapter.HomeItemListAdapter;
import com.gongxianghui.adapter.homeAdapter.HomeSalerListAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.homeFragment.activity.BaoLiaoActivity;
import com.gongxianghui.fragments.homeFragment.activity.HomeVideoActivity;
import com.gongxianghui.fragments.homeFragment.activity.LiftStyleActivity;
import com.gongxianghui.fragments.homeFragment.activity.SalerActivity;
import com.gongxianghui.fragments.homeFragment.activity.SearchActivity;
import com.gongxianghui.widget.GloriousRecyclerView;
import com.gongxianghui.widget.ImageViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotPointFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.recyclerview_list)
    GloriousRecyclerView recyclerviewList;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<String> mDatas;
    Unbinder unbinder;
    private RadioButton viewById;
    private RadioButton rbHomeAir;
    private RadioButton rbHomeVideo;
    private RadioButton rbHomeLifeStyle;
    private RadioButton rbHomeSaler;
    private RadioButton rbHomeBianmin;
    private RadioGroup rgMain;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add(i, i + 1 + "");
        }
        recyclerviewList.setLayoutManager(new LinearLayoutManager(mActivity));
        HomeItemListAdapter adapter = new HomeItemListAdapter(mActivity, mDatas);
        View footer = LayoutInflater.from(mActivity).inflate(R.layout.layout_footer, recyclerviewList, false);
        View headerNavigator = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_navigator, recyclerviewList, false);
        View headerVp = LayoutInflater.from(mActivity).inflate(R.layout.layout_header_viewpager, recyclerviewList, false);
        View empty = LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, recyclerviewList, false);
        recyclerviewList.setAdapter(adapter);
        recyclerviewList.addHeaderView(headerNavigator);
        recyclerviewList.addHeaderView2(headerVp);
        recyclerviewList.addFooterView(footer);
        recyclerviewList.setEmptyView(empty);


//        viewpagerHome.setPages(new CBViewHolderCreator<ImageViewHolder>() {
//
//            @Override
//            public ImageViewHolder createHolder() {
//                return new ImageViewHolder();
//            }
//        }, localImages)
//                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}) //设置两个点作为指示器
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器的方向水平
//                .startTurning(2000)
//                .setCanLoop(true);


//找控件
        rgMain = (RadioGroup) headerNavigator.findViewById(R.id.rg_home_main);
        rbHomeAir = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeVideo = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeLifeStyle = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeSaler = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeBianmin = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rgMain.setOnCheckedChangeListener(this);
    }

    @Override
    public void initViews(View view) {

        PermissionGen.needPermission(HotPointFragment.this, 105,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
        );

        // loadTestDatas();


    }

//    @Override
//    protected void initListeners() {
//        rbHomeAir.setOnClickListener(this);
//        rbHomeVideo.setOnClickListener(this);
//        rbHomeLifeStyle.setOnClickListener(this);
//        rbHomeSaler.setOnClickListener(this);
//        rbHomeBianmin.setOnClickListener(this);
//
//
//    }


//
//    private void loadTestDatas() {
//        //本地图片集合
//        for (int position = 0; position < 7; position++)
//            localImages.add(getResId("ic_test_" + position, R.mipmap.class));
//    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home_air:
                break;
            case R.id.rb_home_video:
                toActivity(HomeVideoActivity.class);
                break;
            case R.id.rb_home_life_style:

                toActivity(LiftStyleActivity.class);
                break;
            case R.id.rb_home_saler:
                toActivity(SalerActivity.class);
                break;
            case R.id.rb_home_bianmin:
                toActivity(BianMinActiviry.class);
        }
    }
}
