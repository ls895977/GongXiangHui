package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvertAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class AdvertBottomFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    Unbinder unbinder;
    @BindView(R.id.vp)
    ViewPager mVp;

    private int count = 0; //页面展示的数据，无实际作用
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private AdvertPagerAdapter guidViewPagerAdapter;
    //广告底部导航的坐标匹配
    private int[] images = {R.mipmap.icon_advert_company_material, R.mipmap.icon_advert_common_material, R.mipmap.icon_advert_bigpic, R.mipmap.icon_advert_card,
            R.mipmap.icon_advert_banner, R.mipmap.icon_advert_scan, R.mipmap.icon_advert_qq, R.mipmap.icon_advert_shop
            , R.mipmap.icon_advert_image_text, R.mipmap.icon_advert_education};

    private String[] iconName = {"企业素材", "通用素材", "大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "店铺广告", "图文广告", "教学视频"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_bottom;
    }

    @Override
    public void initViews(View view) {
        guidViewPagerAdapter = new AdvertPagerAdapter(viewList);
        mVp.setAdapter(guidViewPagerAdapter);
    }

    @Override
    public void initData() {
        AdvertAdapter commonBottomAdverAdapter = new AdvertAdapter(mActivity, images, iconName);
        mRv.setAdapter(commonBottomAdverAdapter);
        commonBottomAdverAdapter.setmOnItemClickListener(new AdvertAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
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
}
