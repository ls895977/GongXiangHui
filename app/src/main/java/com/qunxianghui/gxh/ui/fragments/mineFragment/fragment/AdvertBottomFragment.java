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
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvertBottomAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.activity.EducationActivity;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialActivity;
import com.qunxianghui.gxh.ui.activity.GeneralMaterialActivity;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.AdvertChooseTypeDialog;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AdvertBottomFragment extends BaseFragment implements View.OnClickListener
        , CompoundButton.OnCheckedChangeListener, AdvertChoosePicDialog.ImgPickListener, AdvertChooseTypeDialog.Callback {

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
    private boolean mIsHasBigPage;
    private boolean mIsBigImg;
    private boolean mIsShopImg;
    private boolean mIsGoodsImg;
    private boolean mIsBottomClick;
    private AdvertPagerAdapter mPagerAdapter;
    private AdvertChoosePicDialog mChoosePic;
    private AdvertChooseTypeDialog mChooseType;
    public static List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_bottom;
    }

    @Override
    public void initViews(View view) {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
        mList = new ArrayList<>();
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 2)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
                            for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert datum : body.data) {
                                addData(datum);
                            }
                            mVp.setCurrentItem(0, false);
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

    private void addData(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert datum) {
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

    @Override
    protected void initListeners() {
        AdvertBottomAdapter commonBottomAdverAdapter = new AdvertBottomAdapter(mActivity, images, iconName);
        mRv.setAdapter(commonBottomAdverAdapter);
        commonBottomAdverAdapter.setmOnItemClickListener(new AdvertBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        if (mList.isEmpty()) {
                            asyncShowToast("请先添加广告模版");
                            return;
                        }
                        mIsBottomClick = true;
                        Intent intent = new Intent();
                        intent.putExtra("type", mList.get(mVp.getCurrentItem()).ad_type);
                        intent.putExtra("isMultiSelect", true);
                        intent.setClass(mActivity, EnterpriseMaterialActivity.class);
                        startActivityForResult(intent, 0x0011);
                        break;
                    case 1:
                        if (mList.isEmpty()) {
                            asyncShowToast("请先添加广告模版");
                            return;
                        }
                        mIsBottomClick = true;
                        intent = new Intent(mActivity, GeneralMaterialActivity.class);
                        intent.putExtra("isMultiSelect", true);
                        intent.putExtra("type", 3);
                        startActivityForResult(intent, 0x0011);
                        break;
                    case 9:
                        toActivity(EducationActivity.class);
                        break;
                    default:
                        if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false) && mViewList.size() >= 2) {
                            asyncShowToast("注册会员最多只可添加2个模版哦!");
                            return;
                        }
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
                delete();
                break;
            case R.id.iv_add_big_img:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(this);
                }
                mIsBigImg = true;
                if (mList.get(mVp.getCurrentItem()).ad_type == 3) {
                    mChoosePic.showCommonView().show();
                } else {
                    mChoosePic.hideCommonView().show();
                }
                break;
            case R.id.tv_choose_type:
                if (mChooseType == null && getContext() != null) {
                    mChooseType = new AdvertChooseTypeDialog(getContext(), mViewList, mVp);
                    mChooseType.SetCallback(this);
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
                    mChoosePic.setImgPickListener(this);
                }
                mIsBigImg = false;
                mChoosePic.hideCommonView().show();
                break;
        }
    }

    @Override
    public void pickListener(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnPhoto:
                setWidth();
                intent = new Intent(mActivity, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.btnPick:
                setWidth();
                intent = new Intent(mActivity, ImageGridActivity.class);
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.btnPicFromLocal:
                mIsBottomClick = false;
                intent = new Intent(mActivity, EnterpriseMaterialActivity.class);
                intent.putExtra("type", mList.get(mVp.getCurrentItem()).ad_type);
                intent.putExtra("isMultiSelect", false);
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.btnCommon:
                mIsBottomClick = false;
                intent = new Intent(mActivity, GeneralMaterialActivity.class);
                intent.putExtra("type", 3);
                intent.putExtra("isMultiSelect", false);
                startActivityForResult(intent, 0x0011);
                break;
        }
    }

    private void setWidth() {
        float density = getResources().getDisplayMetrics().density;
        if (mList.get(mVp.getCurrentItem()).ad_type == 1) {
            AdvertTemplateActivity.sImagePicker.setFocusWidth((int) (density * 360));   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            AdvertTemplateActivity.sImagePicker.setFocusHeight((int) (density * 150));  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
            AdvertTemplateActivity.sImagePicker.setOutPutX((int) (density * 360));//保存文件的宽度。单位像素
            AdvertTemplateActivity.sImagePicker.setOutPutY((int) (density * 150));//保存文件的高度。单位像素
            return;
        }
        if (mIsBigImg) {
            AdvertTemplateActivity.sImagePicker.setFocusWidth((int) (density * 360));   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            AdvertTemplateActivity.sImagePicker.setOutPutX((int) (density * 360));//保存文件的宽度。单位像素
        } else {
            AdvertTemplateActivity.sImagePicker.setFocusWidth((int) (density * 90));   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            AdvertTemplateActivity.sImagePicker.setOutPutX((int) (density * 90));//保存文件的宽度。单位像素
        }
        AdvertTemplateActivity.sImagePicker.setFocusHeight((int) (density * 90));  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        AdvertTemplateActivity.sImagePicker.setOutPutY((int) (density * 90));//保存文件的高度。单位像素
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mList.get(mVp.getCurrentItem()).is_slide = isChecked ? 1 : 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<ImageItem> mImages;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null && requestCode == 0x0011) {
            mImages = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (mImages != null && mImages.size() > 0) {
                String path = mImages.get(0).path;
                ImagePicker.getInstance().getImageLoader().displayImage(getActivity(), path, getCurrentImageView(path), 0, 0);
                if (mIsShopImg || mIsGoodsImg) {
                    mIsShopImg = false;
                    mIsGoodsImg = false;
                    ((ViewPager) mViewList.get(mVp.getCurrentItem()).findViewById(R.id.viewPager)).getAdapter().notifyDataSetChanged();
                }
            }
            //企业素材
        } else if (resultCode == 0x0022) {
            if (mIsBottomClick) {
                for (int i = 0; i < EnterpriseMateriaItemFragment.mList.size(); i++) {
                    if (mList.size() >= 10) {
                        asyncShowToast("亲，最多只可添加10个模版哦!");
                        EnterpriseMateriaItemFragment.clearData();
                        return;
                    }
                    EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(i);
                    if (mIsHasBigPage && companyAdvert.ad_type == 1) {
                        for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert : mList) {
                            if (advert.ad_type == 1) {
                                delete();
                                mIsHasBigPage = false;
                                break;
                            }
                        }
                    }
                    companyAdvert.id = 0;
                    companyAdvert.position = 2;
                    if (companyAdvert.ad_type != 1)
                        companyAdvert.is_slide = 1;
                    companyAdvert.settings = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings();
                    companyAdvert.settings.is_link = 0;
                    addData(companyAdvert);
                }
            } else {
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(0);
                Glide.with(AdvertBottomFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(getCurrentImageView(companyAdvert.images));
                if (mIsShopImg || mIsGoodsImg) {
                    mIsShopImg = false;
                    mIsGoodsImg = false;
                    ((ViewPager) mViewList.get(mVp.getCurrentItem()).findViewById(R.id.viewPager)).getAdapter().notifyDataSetChanged();
                }
            }
            EnterpriseMateriaItemFragment.clearData();
            //通用素材
        } else if (resultCode == 0x0033) {
            if (mIsBottomClick) {
                for (int i = 0; i < GeneralMateriaItemFragment.mList.size(); i++) {
                    if (mList.size() >= 10) {
                        asyncShowToast("亲，最多只可添加10个模版哦!");
                        GeneralMateriaItemFragment.clearData();
                        return;
                    }
                    EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = GeneralMateriaItemFragment.mList.get(i);
                    if (mIsHasBigPage && companyAdvert.ad_type == 1) {
                        asyncShowToast("亲，大图通栏广告只可添加一个～～");
                        break;
                    }
                    companyAdvert.id = 0;
                    if (companyAdvert.ad_type != 1)
                        companyAdvert.is_slide = 1;
                    companyAdvert.ad_type = 3;
                    companyAdvert.position = 2;
                    addData(companyAdvert);
                }
            } else {
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = GeneralMateriaItemFragment.mList.get(0);
                Glide.with(AdvertBottomFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(getCurrentImageView(companyAdvert.images));
            }
            GeneralMateriaItemFragment.clearData();
        }
    }

    private ImageView getCurrentImageView(String path) {
        View view = mViewList.get(mVp.getCurrentItem());
        EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = mList.get(mVp.getCurrentItem());
        if (mIsBigImg) {
            companyAdvert.images = path;
            return view.findViewById(R.id.iv_add_big_img);
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings settings = companyAdvert.settings;
            if (settings == null) {
                settings = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings();
            }
            if (companyAdvert.ad_type == 1 || companyAdvert.ad_type == 3) {
                settings.pgn_url = path;
            } else if (mIsShopImg) {
                companyAdvert.images = path;
                Glide.with(AdvertBottomFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(((ImageView) view.findViewById(R.id.ivShop)));
                ((ViewPager) mViewList.get(mVp.getCurrentItem()).findViewById(R.id.viewPager)).getAdapter().notifyDataSetChanged();
                return view.findViewById(R.id.ivShop);
            } else if (mIsGoodsImg) {
                companyAdvert.settings.store_url = path;
                Glide.with(AdvertBottomFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(((ImageView) view.findViewById(R.id.ivAd)));
                ((ViewPager) mViewList.get(mVp.getCurrentItem()).findViewById(R.id.viewPager)).getAdapter().notifyDataSetChanged();
                return view.findViewById(R.id.ivAd);
            } else {
                companyAdvert.images = path;
            }
            return view.findViewById(R.id.ivAd);
        }
    }

    private EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings getCurrentSettings() {
        if (mList.get(mVp.getCurrentItem()).settings == null) {
            mList.get(mVp.getCurrentItem()).settings = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings();
        }
        return mList.get(mVp.getCurrentItem()).settings;
    }

    private void setLink(boolean isChecked) {
        EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings settings = getCurrentSettings();
        EditText etLink = mViewList.get(mVp.getCurrentItem()).findViewById(R.id.et_link);
        if (isChecked) {
            settings.is_link = 1;
            mList.get(mVp.getCurrentItem()).link = AdvertTemplateActivity.mLinkUrl;
            etLink.setText(AdvertTemplateActivity.mLinkUrl);
        } else {
            settings.is_link = 0;
            mList.get(mVp.getCurrentItem()).link = "";
            etLink.setText("");
        }
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
        View rlLink = view.findViewById(R.id.rl_link);
        EditText etLink = view.findViewById(R.id.et_link);
        etLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).link = s.toString();
            }
        });
        EditText etOther = view.findViewById(R.id.et_other);
        etOther.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (getCurrentSettings().operate == 2) {
                    getCurrentSettings().mobile = s.toString();
                } else if (getCurrentSettings().operate == 3) {
                    getCurrentSettings().qq = s.toString();
                }
            }
        });
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setChecked(false);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(bigImg);
            if (companyAdvert.settings != null) {
                switch (companyAdvert.settings.operate) {
                    case 1:
                        tvType.setText("跳转链接");
//                        if (companyAdvert.settings.is_link == 1)
                        etLink.setText(companyAdvert.link);
                        cB.setChecked(companyAdvert.settings.is_link != 0);
                        break;
                    case 2:
                        tvType.setText("拨打电话");
                        rlLink.setVisibility(View.GONE);
                        etOther.setVisibility(View.VISIBLE);
                        etOther.setText(companyAdvert.settings.mobile);
                        break;
                    case 3:
                        tvType.setText("联系QQ");
                        rlLink.setVisibility(View.GONE);
                        etOther.setVisibility(View.VISIBLE);
                        etOther.setText(companyAdvert.settings.qq);
                        break;
                }
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.is_slide = 0;
            advert.ad_type = 1;
            advert.position = 2;
            mList.add(advert);
        }
        cB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setLink(isChecked);
            }
        });
        addPage(view);
    }

    private void addCardPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_card, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("名片广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        EditText etTitle = view.findViewById(R.id.etName);
        EditText etMobile = view.findViewById(R.id.etPhone);
        EditText etIntro = view.findViewById(R.id.etIntro);
        etTitle.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().slogan = s.toString();
            }
        });
        etMobile.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().mobile = s.toString();
            }
        });
        etIntro.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().intro = s.toString();
            }
        });
        ImageView adImg = view.findViewById(R.id.ivAd);
        adImg.setOnClickListener(this);
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(adImg);
            if (companyAdvert.settings != null) {
                etTitle.setText(companyAdvert.settings.slogan);
                etMobile.setText(companyAdvert.settings.mobile);
                etIntro.setText(companyAdvert.settings.intro);
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 2;
            advert.position = 2;
            mList.add(advert);
        }
        addPage(view);
    }

    private void addTongLangPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_tonglan, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("通栏广告");
        View rlAddImg = view.findViewById(R.id.rl_add_img);
        rlAddImg.setVisibility(View.GONE);
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.iv_add_big_img).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_type).setOnClickListener(this);
        TextView tvChooseActivityLink = view.findViewById(R.id.tv_choose_activity_link);
        tvChooseActivityLink.setOnClickListener(this);
        View rlLink = view.findViewById(R.id.rl_link);
        ImageView ivImg = view.findViewById(R.id.ivAd);
        ivImg.setOnClickListener(this);
        ImageView bigImg = view.findViewById(R.id.iv_add_big_img);
        bigImg.setOnClickListener(this);
        AppCompatCheckBox cB = view.findViewById(R.id.cb);
        EditText etLink = view.findViewById(R.id.et_link);
        etLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).link = s.toString();
            }
        });
        EditText etPhone = view.findViewById(R.id.et_phone);
        etPhone.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().mobile = s.toString();
            }
        });
        SwitchButton sw = view.findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            tvChooseActivityLink.setVisibility(View.GONE);
            etPhone.setVisibility(View.GONE);
            TextView tvType = view.findViewById(R.id.tv_choose_type);
            sw.setChecked(companyAdvert.is_slide == 1);
            rlLink.setVisibility(View.GONE);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images)
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(bigImg);
//            1-跳转链接 2-拨打电话 3-联系QQ 4-展示海报 5-展示二维码
            if (companyAdvert.settings != null) {
                switch (companyAdvert.settings.operate) {
                    case 1:
                        tvType.setText("跳转链接");
                        rlLink.setVisibility(View.VISIBLE);
                        view.findViewById(R.id.rl_link).setVisibility(View.VISIBLE);
                        etLink.setText(companyAdvert.link);
                        if (companyAdvert.settings.is_link == 1) {
                            cB.setChecked(true);
                        }
                        break;
                    case 2:
                        tvType.setText("拨打电话");
                        etPhone.setVisibility(View.VISIBLE);
                        etPhone.setText(companyAdvert.settings.mobile);
                        break;
                    case 3:
                        tvType.setText("跳转活动");
                        tvChooseActivityLink.setVisibility(View.VISIBLE);
                        tvChooseActivityLink.setText(companyAdvert.link);
                        break;
                    case 4:
                        tvType.setText("展示海报");
                        rlAddImg.setVisibility(View.VISIBLE);
                        Glide.with(AdvertBottomFragment.this).load(companyAdvert.settings.pgn_url)
                                .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivImg);
                        break;
                    case 5:
                        tvType.setText("展示二维码");
                        rlAddImg.setVisibility(View.VISIBLE);
                        Glide.with(AdvertBottomFragment.this).load(companyAdvert.settings.pgn_url)
                                .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivImg);
                        break;
                }
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert1 = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            companyAdvert1.ad_type = 3;
            companyAdvert1.position = 2;
            mList.add(companyAdvert1);
        }
        cB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setLink(isChecked);
            }
        });
        addPage(view);
    }

    private void addQrCodePage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qrcode, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("二维码广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        EditText etTitle = view.findViewById(R.id.etWechatId);
        EditText etDes = view.findViewById(R.id.etDes);
        ImageView adImg = view.findViewById(R.id.ivAd);
        adImg.setOnClickListener(this);
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(adImg);
            if (companyAdvert.settings != null) {
                etDes.setText(companyAdvert.settings.intro);
                etTitle.setText(companyAdvert.settings.slogan);
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 4;
            advert.position = 2;
            mList.add(advert);
        }
        etTitle.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().slogan = s.toString();
            }
        });
        etDes.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().intro = s.toString();
            }
        });
        addPage(view);
    }

    private void addQQPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_qq, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("QQ广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        EditText etName = view.findViewById(R.id.etNickName);
        EditText etQQ = view.findViewById(R.id.etQQ);
        EditText etDes = view.findViewById(R.id.etDes);
        etName.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().slogan = s.toString();
            }
        });
        etQQ.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().qq = s.toString();
            }
        });
        etDes.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().intro = s.toString();
            }
        });
        ImageView adImg = view.findViewById(R.id.ivAd);
        adImg.setOnClickListener(this);
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(adImg);
            if (companyAdvert.settings != null) {
                etName.setText(companyAdvert.settings.slogan);
                etQQ.setText(companyAdvert.settings.qq);
                etDes.setText(companyAdvert.settings.intro);
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 5;
            advert.position = 2;
            mList.add(advert);
        }
        addPage(view);
    }

    private void addStorePage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_store, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("店铺广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        View item1 = inflater.inflate(R.layout.ad_item_store_child, mVp, false);
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
        ImageView ivGoods = item1.findViewById(R.id.ivAd);
        EditText etGoodsName = item1.findViewById(R.id.etNickName);
        EditText etGoodsPrice = item1.findViewById(R.id.etPrice);
        EditText etGoodsLink = item1.findViewById(R.id.etLink);
        etGoodsName.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().product_name = s.toString();
            }
        });
        etGoodsPrice.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().product_price = s.toString();
            }
        });
        etGoodsLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().product_url = s.toString();
            }
        });
        View item2 = inflater.inflate(R.layout.ad_item_store_child, mVp, false);
        item2.findViewById(R.id.etLink).setVisibility(View.INVISIBLE);
        item2.findViewById(R.id.rl_add_img).setVisibility(View.GONE);
        item2.findViewById(R.id.rl_add_shop_img).setVisibility(View.VISIBLE);
        ImageView ivShop = item2.findViewById(R.id.ivShop);
        ivShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsShopImg = true;
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(AdvertBottomFragment.this);
                }
                mIsBigImg = false;
                mChoosePic.hideCommonView().show();
            }
        });
        ivGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsGoodsImg = true;
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(AdvertBottomFragment.this);
                }
                mIsBigImg = false;
                mChoosePic.hideCommonView().show();
            }
        });

        EditText etQQ = item2.findViewById(R.id.etNickName);
        EditText etMobile = item2.findViewById(R.id.etPrice);
        etQQ.setHint("店铺或公司名称");
        etMobile.setHint("联系电话");
        etQQ.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().store_name = s.toString();
            }
        });
        etMobile.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().mobile = s.toString();
            }
        });
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
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
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivShop);
            if (companyAdvert.settings != null) {
                etGoodsName.setText(companyAdvert.settings.product_name);
                etGoodsPrice.setText(companyAdvert.settings.product_price);
                etGoodsLink.setText(companyAdvert.settings.product_url);
                etQQ.setText(companyAdvert.settings.store_name);
                etMobile.setText(companyAdvert.settings.mobile);
                Glide.with(AdvertBottomFragment.this).load(companyAdvert.settings.store_url).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivGoods);
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 7;
            advert.position = 2;
            mList.add(advert);
        }
        addPage(view);
    }

    private void addGraphicPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_graphic, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("图文广告");
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        EditText etSlogan = view.findViewById(R.id.etServiceName);
        EditText etLink = view.findViewById(R.id.etUrl);
        EditText etDes = view.findViewById(R.id.etDes);
        etSlogan.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().slogan = s.toString();
            }
        });
        etLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).link = s.toString();
            }
        });
        etDes.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getCurrentSettings().intro = s.toString();
            }
        });
        ImageView adImg = view.findViewById(R.id.ivAd);
        adImg.setOnClickListener(this);
        SwitchButton sB = view.findViewById(R.id.sw);
        sB.setOnCheckedChangeListener(this);
        if (companyAdvert != null) {
            mList.add(companyAdvert);
            sB.setChecked(companyAdvert.is_slide == 1);
            Glide.with(AdvertBottomFragment.this).load(companyAdvert.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(adImg);
            if (companyAdvert.settings != null) {
                etSlogan.setText(companyAdvert.settings.slogan);
                etLink.setText(companyAdvert.link);
                etDes.setText(companyAdvert.settings.intro);
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert advert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            advert.ad_type = 8;
            advert.position = 2;
            mList.add(advert);
        }
        addPage(view);
    }

    private void addPage(View view) {
        mViewList.add(view);
        mPagerAdapter.notifyDataSetChanged();
        mVp.setCurrentItem(mPagerAdapter.getCount() - 1, false);
        changeCircleView();
    }

    private void delete() {
        int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
        if ("大图通栏".equals(((TextView) mViewList.get(index).findViewById(R.id.tv_title)).getText().toString())) {
            mIsHasBigPage = false;
        }
        if (mList.get(mVp.getCurrentItem()).id == 0) {
            removeView();
            return;
        }
        OkGo.<CommonBean>get(Constant.DELETE_AD)
                .params("id", mList.get(mVp.getCurrentItem()).id)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        if (response != null && response.body() != null && response.body().code == 200) {
                            removeView();
                        } else {
                            asyncShowToast("删除失败");
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        asyncShowToast("删除失败");
                    }
                });
    }

    private void removeView() {
        int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
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

    @Override
    public void callback(int type) {
        getCurrentSettings().operate = type;
    }

}
