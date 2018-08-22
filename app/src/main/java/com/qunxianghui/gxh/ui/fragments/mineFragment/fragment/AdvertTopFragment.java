package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
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
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialActivity;
import com.qunxianghui.gxh.ui.activity.GeneralMaterialActivity;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.AdvertChooseTypeDialog;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTopFragment extends BaseFragment implements View.OnClickListener,
        AdvertChoosePicDialog.ImgPickListener, CompoundButton.OnCheckedChangeListener, AdvertChooseTypeDialog.Callback {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.circleIndicatorView)
    CircleIndicatorView mCircleIndicatorView;

    private AdvertPagerAdapter mPagerAdapter;
    private AdvertChooseTypeDialog mChooseType;
    private AdvertChoosePicDialog mChoosePic;
    private boolean mIsBottomClick;
    private List<View> mViewList = new ArrayList<>();
    public static List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_top;
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 1)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
                            mList = body.data;
                            for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert datum : body.data) {
                                addPage(datum);
                            }
                        } else {
                            addPage(null);
                        }
                    }

                    @Override
                    public void onError(Response<PersonalAds> response) {
                        super.onError(response);
                        addPage(null);
                    }
                });
    }

    @Override
    protected void initListeners() {
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

    @OnClick({R.id.ll_company, R.id.ll_common, R.id.ll_common_advert, R.id.ll_video})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_company:
                mIsBottomClick = true;
                intent = new Intent(mActivity, EnterpriseMaterialActivity.class);
                intent.putExtra("type", 9);
                intent.putExtra("isMultiSelect", true);
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.ll_common:
                mIsBottomClick = true;
                intent = new Intent(mActivity, GeneralMaterialActivity.class);
                intent.putExtra("isMultiSelect", true);
                intent.putExtra("type", 3);
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.ll_common_advert:
                if (mViewList.size() >= 10) {
                    asyncShowToast("亲，最多只可添加10个模版哦!");
                    return;
                }
                addPage(null);
                break;
            case R.id.ll_video:
                asyncShowToast("教学视频");
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
                intent.putExtra("type", 9);
                intent.putExtra("isMultiSelect", false);
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.btnCommon:
                mIsBottomClick = false;
                intent = new Intent(mActivity, GeneralMaterialActivity.class);
                intent.putExtra("isMultiSelect", false);
                intent.putExtra("type", 3);
                startActivityForResult(intent, 0x0011);
                break;
        }
    }

    private void setWidth() {
        float density = getResources().getDisplayMetrics().density;
        if (mChoosePic.mIsBigImg) {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                if (mViewList.size() <= 1) {
                    asyncShowToast("亲，至少有一个模版哦！");
                    return;
                }
                delete();
                break;
            case R.id.iv_add_big_img:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(this);
                }
                mChoosePic.showCommonView().show();
                break;
            case R.id.tv_choose_type:
                if (mChooseType == null && getContext() != null) {
                    mChooseType = new AdvertChooseTypeDialog(getContext(), mViewList, mVp);
                    mChooseType.SetCallback(this);
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
                mChoosePic.hideCommonView().show();
                break;
        }
    }

    private void addPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert data) {
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
        ImageView ivImg = view.findViewById(R.id.ivAd);
        ivImg.setOnClickListener(this);
        ImageView bigImg = view.findViewById(R.id.iv_add_big_img);
        bigImg.setOnClickListener(this);
        AppCompatCheckBox cB = view.findViewById(R.id.cb);
        cB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings settings = mList.get(mVp.getCurrentItem()).settings;
                EditText etLink = mViewList.get(mVp.getCurrentItem()).findViewById(R.id.et_link);
                if (isChecked) {
                    settings.is_link = 1;
                    etLink.setText(AdvertTemplateActivity.mLinkUrl);
                    settings.link = AdvertTemplateActivity.mLinkUrl;
                } else {
                    settings.is_link = 0;
                    etLink.setText("");
                    settings.link = "";
                }
            }
        });
        EditText etLink = view.findViewById(R.id.et_link);
        etLink.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).settings.link = s.toString();
            }
        });
        EditText etPhone = view.findViewById(R.id.et_phone);
        etPhone.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mList.get(mVp.getCurrentItem()).settings.mobile = s.toString();
            }
        });
        SwitchButton sw = view.findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);
        if (data != null) {
            tvChooseActivityLink.setVisibility(View.GONE);
            rlAddImg.setVisibility(View.GONE);
            etPhone.setVisibility(View.GONE);
            TextView tvType = view.findViewById(R.id.tv_choose_type);
            sw.setChecked(data.status == 1);
            Glide.with(AdvertTopFragment.this).load(data.images)
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(bigImg);
//            1-跳转链接 2-拨打电话 3-联系QQ 4-展示海报 5-展示二维码
            switch (data.settings.operate) {
                case 1:
                    tvType.setText("跳转链接");
                    view.findViewById(R.id.rl_link).setVisibility(View.VISIBLE);
                    etLink.setText(data.settings.link);
                    if (data.settings.is_link == 1) {
                        cB.setChecked(true);
                    }
                    break;
                case 2:
                    tvType.setText("拨打电话");
                    etPhone.setVisibility(View.VISIBLE);
                    etPhone.setText(data.settings.mobile);
                    break;
                case 3:
                    tvType.setText("跳转活动");
                    tvChooseActivityLink.setVisibility(View.VISIBLE);
                    tvChooseActivityLink.setText(data.settings.link);
                    break;
                case 4:
                case 5:
                    tvType.setText(data.settings.operate == 4 ? "展示海报" : "展示二维码");
                    rlAddImg.setVisibility(View.VISIBLE);
                    Glide.with(AdvertTopFragment.this).load(data.settings.pgn_url)
                            .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivImg);
                    break;
            }
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
            companyAdvert.ad_type = 3;
            companyAdvert.position = 1;
            mList.add(companyAdvert);
        }
        mViewList.add(view);
        mPagerAdapter.notifyDataSetChanged();
        mVp.setCurrentItem(mPagerAdapter.getCount() - 1, false);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mList.get(mVp.getCurrentItem()).status = isChecked ? 1 : 0;
    }

    @Override
    public void callback(int type) {
        mList.get(mVp.getCurrentItem()).settings.operate = type;
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
            }
        } else if (resultCode == 0x0022) {
            if (mIsBottomClick) {
                for (int i = 0; i < EnterpriseMateriaItemFragment.mList.size(); i++) {
                    if (mList.size() >= 10) {
                        asyncShowToast("亲，最多只可添加10个模版哦!");
                        EnterpriseMateriaItemFragment.clearData();
                        return;
                    }
                    EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(i);
                    companyAdvert.id = 0;
                    mList.add(companyAdvert);
                    addPage(companyAdvert);
                }
            } else {
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(0);
                Glide.with(AdvertTopFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(getCurrentImageView(companyAdvert.images));
            }
            EnterpriseMateriaItemFragment.clearData();
        } else if (resultCode == 0x0033) {
            if (mIsBottomClick) {
                for (int i = 0; i < GeneralMateriaItemFragment.mList.size(); i++) {
                    if (mList.size() >= 10) {
                        asyncShowToast("亲，最多只可添加10个模版哦!");
                        GeneralMateriaItemFragment.clearData();
                        return;
                    }
                    EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(i);
                    companyAdvert.id = 0;
                    mList.add(companyAdvert);
                    addPage(companyAdvert);
                }
            } else {
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = EnterpriseMateriaItemFragment.mList.get(0);
                Glide.with(AdvertTopFragment.this).load(companyAdvert.images)
                        .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img))
                        .into(getCurrentImageView(companyAdvert.images));
            }
            GeneralMateriaItemFragment.clearData();
        }
    }

    private ImageView getCurrentImageView(String path) {
        View view = mViewList.get(mVp.getCurrentItem());
        if (mChoosePic.mIsBigImg) {
            mList.get(mVp.getCurrentItem()).images = path;
            return view.findViewById(R.id.iv_add_big_img);
        } else {
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings settings = mList.get(mVp.getCurrentItem()).settings;
            if (settings == null) {
                settings = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert.Settings();
            }
            settings.pgn_url = path;
            return view.findViewById(R.id.ivAd);
        }
    }

    private void delete() {
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
        int index = getCurrentIndex();
        mViewList.remove(index);
        mList.remove(index);
        mPagerAdapter.notifyDataSetChanged();
        changeCircleView();
        asyncShowToast("删除成功");
    }

    private int getCurrentIndex() {
        return (int) mViewList.get(mVp.getCurrentItem()).getTag();
    }

}
