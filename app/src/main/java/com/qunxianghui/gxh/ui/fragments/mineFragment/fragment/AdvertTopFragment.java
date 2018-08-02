package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTopFragment extends BaseFragment {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.layout_adver_guidpoint)
    LinearLayout mLayoutAdverGuidpoint;
    @BindView(R.id.framlayout_adver_guidpoint)
    FrameLayout mFramlayoutAdverGuidpoint;


    private int count = 0; //页面展示的数据，无实际作用
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private GuidViewPagerAdapter guidTopViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advercommon_top;
    }

    @Override
    public void initData() {
        guidTopViewPagerAdapter = new GuidViewPagerAdapter(viewList);
        mVp.setAdapter(guidTopViewPagerAdapter);
    }

    @OnClick({R.id.ll_company, R.id.ll_common, R.id.ll_common_advert, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_company:
                asyncShowToast("企业素材");
                //添加页面
                String text = "页面" + count;
                count++;
                addPage(text);
                break;
            case R.id.ll_common:
                asyncShowToast("通用素材");
                break;
            case R.id.ll_common_advert:
                asyncShowToast("通栏广告");
                break;
            case R.id.ll_video:
                asyncShowToast("教学视频");
                break;
        }
    }

    private void addPage(String text) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);//获取LayoutInflater的实例
        View view = inflater.inflate(R.layout.guidslide_item, null);//调用LayoutInflater实例的inflate()方法来加载页面的布局
        viewList.add(view);
        guidTopViewPagerAdapter.notifyDataSetChanged();
    }

}
