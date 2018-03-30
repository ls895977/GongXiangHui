package com.gongxianghui.fragments.homeFragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.gongxianghui.R;
import com.gongxianghui.activity.BianMinActiviry;
import com.gongxianghui.adapter.homeAdapter.HomeItemListAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.homeFragment.activity.HomeAddTabActivity;
import com.gongxianghui.fragments.homeFragment.activity.HomeSeachLocationActivity;
import com.gongxianghui.fragments.homeFragment.activity.HomeVideoActivity;
import com.gongxianghui.fragments.homeFragment.activity.LiftStyleActivity;
import com.gongxianghui.fragments.homeFragment.activity.SalerActivity;
import com.gongxianghui.utils.GlideImageLoader;
import com.gongxianghui.widget.GloriousRecyclerView;

import com.youth.banner.Banner;

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

   private Banner viewpagerHome;

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


//找控件
        rgMain = (RadioGroup) headerNavigator.findViewById(R.id.rg_home_main);
        viewpagerHome = headerVp.findViewById(R.id.viewpager_home);
        rbHomeAir = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeVideo = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeLifeStyle = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeSaler = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rbHomeBianmin = (RadioButton) headerNavigator.findViewById(R.id.rb_home_air);
        rgMain.setOnCheckedChangeListener(this);


        List<Integer> list=new ArrayList<>();

        list.add(R.mipmap.ic_test_0);
        list.add(R.mipmap.ic_test_1);
        list.add(R.mipmap.ic_test_2);
        list.add(R.mipmap.ic_test_3);
        list.add(R.mipmap.ic_test_4);
        list.add(R.mipmap.ic_test_5);
        list.add(R.mipmap.ic_test_6);
        viewpagerHome.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void initViews(View view) {

        PermissionGen.needPermission(HotPointFragment.this, 105,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
        );




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
                toActivity(HomeSeachLocationActivity.class);
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
