package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvertBottomAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.AdvertChooseTypeDialog;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AdvertBottomFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.circleIndicatorView)
    CircleIndicatorView mCircleIndicatorView;

    //广告底部导航的坐标匹配
    private int[] images = {R.mipmap.icon_advert_company_material, R.mipmap.icon_advert_common_material, R.mipmap.icon_advert_bigpic, R.mipmap.icon_advert_card,
            R.mipmap.icon_advert_banner, R.mipmap.icon_advert_scan, R.mipmap.icon_advert_qq, R.mipmap.icon_advert_shop
            , R.mipmap.icon_advert_image_text, R.mipmap.icon_advert_education};

    private String[] iconName = {"企业素材", "通用素材", "大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "店铺广告", "图文广告", "教学视频"};

    private List<View> mViewList = new ArrayList<>();
    private AdvertPagerAdapter mPagerAdapter;
    private AdvertChoosePicDialog mChoosePic;
    private AdvertChooseTypeDialog mChooseType;
    private boolean mIsHasBigpage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_bottom;
    }

    @Override
    public void initViews(View view) {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
        addBigPage();
    }

    @Override
    protected void initListeners() {
        AdvertBottomAdapter commonBottomAdverAdapter = new AdvertBottomAdapter(mActivity, images, iconName);
        mRv.setAdapter(commonBottomAdverAdapter);
        commonBottomAdverAdapter.setmOnItemClickListener(new AdvertBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        asyncShowToast("企业素材");
                        break;
                    case 1:
                        asyncShowToast("通用素材");
                        break;
                    case 9:
                        asyncShowToast("教学视频");
                        break;
                    default:
                        if (mViewList.size() >= 10) {
                            asyncShowToast("亲，最多只可添加10个模版哦!");
                            return;
                        }
                        switch (position) {
                            case 2:
                                addBigPage();
                                break;
                            case 3:
                                addCardPage();
                                break;
                            case 4:
                                addTongLangPage();
                                break;
                            case 5:
                                addQrCodePage();
                                break;
                            case 6:
                                addQQPage();
                                break;
                            case 7:
                                addStorePage();
                                break;
                            case 8:
                                addGraphicPage();
                                break;
                        }
                        break;
                }
            }
        });
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeCircleView();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                remove();
                break;
            case R.id.iv_add_big_img:
                break;
            case R.id.tv_choose_type:
                if (mChooseType == null && getContext() != null) {
                    mChooseType = new AdvertChooseTypeDialog(getContext(), mViewList, mVp);
                }
                if (mViewList.get(mVp.getCurrentItem()).findViewById(R.id.tv_choose_activity_link) == null) {
                    mChooseType.setBigPageType();
                } else {
                    mChooseType.setTongLangPageType();
                }
                mChooseType.show();
                break;
            case R.id.tv_choose_activity_link:
                //todo 跳转选择链接
                asyncShowToast("跳转选择链接");
                break;
            case R.id.ivAd:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                }
                mChoosePic.hideLocalView().show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    private void addBigPage() {
        if (mIsHasBigpage) {
            asyncShowToast("亲，大图通栏广告只可添加一个～～");
            return;
        }
        mIsHasBigpage = true;
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_big, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("大图通栏");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.iv_add_big_img).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_type).setOnClickListener(this);
        ((AppCompatCheckBox) view.findViewById(R.id.cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addCardPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_card, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("名片广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addTongLangPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_tonglan, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("通栏广告");
        view.findViewById(R.id.rl_add_img).setVisibility(View.GONE);
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.iv_add_big_img).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_type).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_activity_link).setOnClickListener(this);
        view.findViewById(R.id.ivAd).setOnClickListener(this);
        ((AppCompatCheckBox) view.findViewById(R.id.cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addQrCodePage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qrcode, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("二维码广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.ivAd).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addQQPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qq, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("QQ广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addStorePage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_store, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("店铺广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        View item1 = inflater.inflate(R.layout.ad_item_store_child, mVp, false);
        View item2 = inflater.inflate(R.layout.ad_item_store_child, mVp, false);
        View llGoods = view.findViewById(R.id.llgoods);
        final View llShops = view.findViewById(R.id.llshop);
        final View goodsBottom = view.findViewById(R.id.goods_bto);
        final View shopsBottom = view.findViewById(R.id.shop_bto);
        final ViewPager vp = view.findViewById(R.id.viewPager);
        llGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBottom.setVisibility(View.VISIBLE);
                shopsBottom.setVisibility(View.INVISIBLE);
                vp.setCurrentItem(0);
            }
        });
        llShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBottom.setVisibility(View.INVISIBLE);
                shopsBottom.setVisibility(View.VISIBLE);
                vp.setCurrentItem(1);
            }
        });
        ArrayList<View> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        vp.setAdapter(new AdvertPagerAdapter(list));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    goodsBottom.setVisibility(View.VISIBLE);
                    shopsBottom.setVisibility(View.INVISIBLE);
                } else {
                    goodsBottom.setVisibility(View.INVISIBLE);
                    shopsBottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addPage(view);
    }

    private void addGraphicPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_graphic, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("图文广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addPage(View view) {
        mViewList.add(view);
        mPagerAdapter.notifyDataSetChanged();
        mVp.setCurrentItem(mPagerAdapter.getCount() - 1, false);
        changeCircleView();
    }

    private void remove() {
        int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
        if ("大图通栏".equals(((TextView) mViewList.get(index).findViewById(R.id.tv_title)).getText().toString())) {
            mIsHasBigpage = false;
        }
        mViewList.remove(index);
        mPagerAdapter.notifyDataSetChanged();
        changeCircleView();
    }

    private void changeCircleView() {
        mCircleIndicatorView.setCircleCount(mPagerAdapter.getCount());
        mCircleIndicatorView.setCircleSelectedPosition(mVp.getCurrentItem());
        mCircleIndicatorView.setSelectedCircleRadius(8);
        mCircleIndicatorView.setCircleUnSelectedColor(Color.parseColor("#BDBDBD"));
        mCircleIndicatorView.drawCircleView();
    }

}
