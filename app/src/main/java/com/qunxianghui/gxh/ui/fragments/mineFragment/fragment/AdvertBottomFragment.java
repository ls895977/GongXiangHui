package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvertBottomAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialActivity;
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
    private boolean mIsHasBigPage;
    private List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_bottom;
    }

    @Override
    public void initViews(View view) {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
//        addBigPage();
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 2)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
                            for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert datum : body.data) {
                                switch (datum.ad_type) {
                                    case 1:
                                        addBigPage(datum);
                                        break;
                                    case 2:
                                        addCardPage(datum);
                                        break;
                                    case 3:
                                        addTongLangPage(datum);
                                        break;
                                    case 4:
                                        addQrCodePage(datum);
                                        break;
                                    case 5:
                                        addQQPage(datum);
                                        break;
                                    case 7:
                                        addStorePage(datum);
                                        break;
                                    case 8:
                                        addGraphicPage(datum);
                                        break;
                                }
                            }
                        } else {
                            addBigPage(null);
                        }
                    }

                    @Override
                    public void onError(Response<PersonalAds> response) {
                        super.onError(response);
                        addBigPage(null);
                    }
                });
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
                        Intent intent = new Intent();
                        intent.putExtra("type", mList.get(mVp.getCurrentItem()).ad_type);
                        intent.putExtra("isMultiSelect", true);
                        intent.setClass(mActivity, EnterpriseMaterialActivity.class);
                        startActivityForResult(intent, 0x0011);
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
                                addBigPage(null);
                                break;
                            case 3:
                                addCardPage(null);
                                break;
                            case 4:
                                addTongLangPage(null);
                                break;
                            case 5:
                                addQrCodePage(null);
                                break;
                            case 6:
                                addQQPage(null);
                                break;
                            case 7:
                                addStorePage(null);
                                break;
                            case 8:
                                addGraphicPage(null);
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

    private void addBigPage(final EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        if (mIsHasBigPage) {
            asyncShowToast("亲，大图通栏广告只可添加一个～～");
            return;
        }
        mIsHasBigPage = true;
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_big, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("大图通栏");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ImageView bigImg = view.findViewById(R.id.iv_add_big_img);
        bigImg.setOnClickListener(this);
        TextView tvType = view.findViewById(R.id.tv_choose_type);
        tvType.setOnClickListener(this);
        AppCompatCheckBox cB = view.findViewById(R.id.cb);
        EditText etLink = view.findViewById(R.id.et_link);
        etLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).settings.link = s.toString();
            }
        });
        EditText etOther = view.findViewById(R.id.et_other);
        etOther.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (companyAdvert.settings.operate == 2) {
                    mList.get(mVp.getCurrentItem()).settings.mobile = s.toString();
                } else if (companyAdvert.settings.operate == 3) {
                    mList.get(mVp.getCurrentItem()).settings.qq = s.toString();
                }
            }
        });
        cB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(mVp.getCurrentItem()).settings.is_link = isChecked ? 1 : 0;
            }
        });

        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            Glide.with(mActivity).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(bigImg);
            switch (companyAdvert.settings.operate) {
                case 1:
                    tvType.setText("跳转链接");
                    etLink.setText(companyAdvert.settings.link);
                    break;
                case 2:
                    tvType.setText("拨打电话");
                    etOther.setText(companyAdvert.settings.mobile);
                    break;
                case 3:
                    tvType.setText("联系QQ");
                    etOther.setText(companyAdvert.settings.qq);
                    break;
            }
            mList.add(companyAdvert);
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 1;
            advert.position = 2;
            mList.add(advert);
        }
        addPage(view);
    }

    private void addCardPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_card, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("名片广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addTongLangPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
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

    private void addQrCodePage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qrcode, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("二维码广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.ivAd).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addQQPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qq, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("QQ广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        addPage(view);
    }

    private void addStorePage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
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

    private void addGraphicPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
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
            mIsHasBigPage = false;
        }
        mViewList.remove(index);
        mList.remove(index);
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
