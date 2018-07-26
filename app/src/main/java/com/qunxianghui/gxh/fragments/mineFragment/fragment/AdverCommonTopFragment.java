package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdverCommonTopFragment extends BaseFragment implements View.OnClickListener {

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
    private int count = 0; //页面展示的数据，无实际作用
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private GuidViewPagerAdapter guidTopViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advercommon_top;
    }

    @Override
    public void initDatas() {
        guidTopViewPagerAdapter = new GuidViewPagerAdapter(viewList);
        vpAdvercommonTop.setAdapter(guidTopViewPagerAdapter);
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
}
