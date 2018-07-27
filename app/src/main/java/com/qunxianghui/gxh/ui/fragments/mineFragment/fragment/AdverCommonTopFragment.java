package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdverCommonTopFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_advercommon_top)
    ViewPager vpAdvercommonTop;
    @BindView(R.id.ll_advercommon_top_company_material)
    LinearLayout llAdvercommonTopCompanyMaterial;
    @BindView(R.id.ll_advercommon_top_common_material)
    LinearLayout llAdvercommonTopCommonMaterial;
    @BindView(R.id.ll_advercommon_top_common_adver)
    LinearLayout llAdvercommonTopCommonAdver;
    @BindView(R.id.ll_advercommon_top_eduvideo)
    LinearLayout llAdvercommonTopEduvideo;
    Unbinder unbinder;
    @BindView(R.id.layout_adver_guidpoint)
    LinearLayout layoutAdverGuidpoint;
    @BindView(R.id.framlayout_adver_guidpoint)
    FrameLayout framlayoutAdverGuidpoint;
    private int count = 0; //页面展示的数据，无实际作用
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private GuidViewPagerAdapter guidTopViewPagerAdapter;
    private List<ImageView> list_pointView = new ArrayList<ImageView>();
    private ImageView colorPoint;
    private LinearLayout.LayoutParams lpColorPoint;
    private int pointSpacing;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advercommon_top;
    }

    @Override
    public void initDatas() {
        guidTopViewPagerAdapter = new GuidViewPagerAdapter(viewList);

        vpAdvercommonTop.setAdapter(guidTopViewPagerAdapter);
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList != null) {
                ImageView point = new ImageView(mActivity);
                //设置暗点
                point.setBackgroundResource(R.mipmap.home_video_location);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 0, 10, 0);
                point.setLayoutParams(lp);
                list_pointView.add(point);
                layoutAdverGuidpoint.addView(point);

                //添加选中的点
                colorPoint = new ImageView(mActivity);
                //设置亮点
                colorPoint.setBackgroundResource(R.mipmap.icon_close);
                lpColorPoint = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                colorPoint.setLayoutParams(lpColorPoint);
                framlayoutAdverGuidpoint.addView(colorPoint);
                framlayoutAdverGuidpoint.post(new Runnable() {
                    @Override
                    public void run() {
                        //待布局绘制完毕  设置选中白点 的初始化位置
                        FrameLayout.LayoutParams framLayoutParams = (FrameLayout.LayoutParams) colorPoint.getLayoutParams();
                        framLayoutParams.leftMargin = list_pointView.get(0).getLeft();
                        colorPoint.setLayoutParams(framLayoutParams);
                    }
                });

                layoutAdverGuidpoint.post(new Runnable() {
                    @Override
                    public void run() {
                        // 获取引导的之间的间隔
                        pointSpacing = layoutAdverGuidpoint.getChildAt(1).getLeft() - layoutAdverGuidpoint.getChildAt(0).getLeft();
                    }
                });
            }

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
    protected void initListeners() {
        super.initListeners();
        llAdvercommonTopCompanyMaterial.setOnClickListener(this);
        llAdvercommonTopCommonMaterial.setOnClickListener(this);
        llAdvercommonTopCommonAdver.setOnClickListener(this);
        llAdvercommonTopEduvideo.setOnClickListener(this);
        vpAdvercommonTop.setOnPageChangeListener(this);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_advercommon_top_company_material:
                asyncShowToast("企业素材");
                //添加页面
                String text = "页面" + count;
                count++;
                addPage(text);

                break;
            case R.id.ll_advercommon_top_common_material:
                asyncShowToast("通用素材");
                break;
            case R.id.ll_advercommon_top_common_adver:
                asyncShowToast("通栏广告");
                break;
            case R.id.ll_advercommon_top_eduvideo:
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        FrameLayout.LayoutParams framLayoutParams = (FrameLayout.LayoutParams) colorPoint.getLayoutParams();
        //根据滑动动态设置左边距
        framLayoutParams.leftMargin = (int) (list_pointView.get(position).getLeft() + pointSpacing * positionOffset);
        colorPoint.setLayoutParams(framLayoutParams);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
