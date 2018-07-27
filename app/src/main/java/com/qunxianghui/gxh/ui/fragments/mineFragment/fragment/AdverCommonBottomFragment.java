package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.CommonAdaverGridAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdverCommonBottomFragment extends BaseFragment {
    @BindView(R.id.recycler_commonadver_bottom)
    RecyclerView recyclerCommonadverBottom;
    Unbinder unbinder;
    @BindView(R.id.vp_advercommon_bottom)
    ViewPager vpAdvercommonBottom;
    private int count = 0; //页面展示的数据，无实际作用
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private GuidViewPagerAdapter guidViewPagerAdapter;
    //广告底部导航的坐标匹配
    private int[] images = {R.mipmap.icon_adver_company_material, R.mipmap.icon_adver_common_material, R.mipmap.icon_adver_bigpic, R.mipmap.icon_adver_card,
            R.mipmap.icon_adver_banner, R.mipmap.icon_adver_scan, R.mipmap.icon_adver_qq, R.mipmap.icon_adver_shop
            , R.mipmap.icon_adver_image_text, R.mipmap.icon_adver_education};

    private String[] iconName = {"企业素材", "通用素材", "大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "店铺广告", "图文广告", "教学视频"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advercommon_bottom;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        recyclerCommonadverBottom.setLayoutManager(new GridLayoutManager(mActivity, 5));

        guidViewPagerAdapter = new GuidViewPagerAdapter(viewList);
        vpAdvercommonBottom.setAdapter(guidViewPagerAdapter);
    }

    @Override
    public void initDatas() {
        CommonAdaverGridAdapter commonBottomAdverAdapter = new CommonAdaverGridAdapter(mActivity, images, iconName);

        recyclerCommonadverBottom.setAdapter(commonBottomAdverAdapter);
        commonBottomAdverAdapter.setmOnItemClickListener(new CommonAdaverGridAdapter.OnItemClickListener() {
            @Override
            public void onpicItemClick(int position) {
                switch (position) {
                    case 0:
                        asyncShowToast("企业素材");

                        //添加页面
                        String text = "页面" + count;
                        count++;
                        addPage();
                        break;

                    case 1:
                        asyncShowToast("通用素材");
                        break;

                    case 2:
                        asyncShowToast("大图通栏");
                        break;
                    case 3:
                        asyncShowToast("名片广告");
                        break;
                    case 4:
                        asyncShowToast("通栏广告");
                        break;
                    case 5:
                        asyncShowToast("二维码广告");
                        break;
                    case 6:
                        asyncShowToast("QQ广告");
                        break;
                    case 7:
                        asyncShowToast("店铺广告");

                        break;
                    case 8:
                        asyncShowToast("图文广告");
                        break;
                    case 9:
                        asyncShowToast("教学视频");

                        break;

                }
            }
        });

    }

    private void addPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);//获取LayoutInflater的实例
        View view = inflater.inflate(R.layout.guidslide_item, null);//调用LayoutInflater实例的inflate()方法来加载页面的布局

        viewList.add(view);
        guidViewPagerAdapter.notifyDataSetChanged();
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
}
