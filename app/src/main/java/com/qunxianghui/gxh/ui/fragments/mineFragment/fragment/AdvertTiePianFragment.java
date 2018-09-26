package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
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
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.activity.EducationActivity;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialTiePianActivity;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.TiePianChooseDialog;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTiePianFragment extends BaseFragment implements AdvertChoosePicDialog.ImgPickListener, TiePianChooseDialog.ChooseTimeListener {

    @BindView(R.id.fl_layout)
    View mFlLayout;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ivAd)
    ImageView mIvAd;
    @BindView(R.id.tv_TiePian_Time)
    TextView mTvTiePianTime;
    @BindView(R.id.tv_TiePian_ShowType)
    TextView mTvTiePianShowType;
    @BindView(R.id.ll_link)
    View mLlLink;
    @BindView(R.id.etName)
    EditText mEtName;
    @BindView(R.id.cbUseSpace)
    AppCompatCheckBox mCbUseSpace;
    @BindView(R.id.et_other)
    EditText mEtOther;
    @BindView(R.id.sw)
    SwitchButton mSw;

    private TiePianChooseDialog mChooseType;
    private AdvertChoosePicDialog mChoosePic;
    private boolean mIsChooseTime;
    public static EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert mAdvertBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_tiepian;
    }

    @Override
    public void initViews(View view) {
        mTvTitle.setText("贴片广告");
    }

    @Override
    public void initData() {
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 4)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
                            mAdvertBean = body.data.get(0);
                            Glide.with(AdvertTiePianFragment.this).load(mAdvertBean.images)
                                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(mIvAd);
                            mTvTiePianTime.setText(mAdvertBean.settings.time);
                            //1-跳转链接 2-拨打电话 3-联系QQ
                            switch (mAdvertBean.settings.linktype) {
                                case 1:
                                    mLlLink.setVisibility(View.VISIBLE);
                                    mEtOther.setVisibility(View.GONE);
                                    mTvTiePianShowType.setText("跳转链接");
                                    if (mAdvertBean.settings.is_link == 1) {
                                        mEtName.setText(mAdvertBean.link);
                                        mCbUseSpace.setChecked(true);
                                    }
                                    break;
                                case 2:
                                    mLlLink.setVisibility(View.GONE);
                                    mEtOther.setVisibility(View.VISIBLE);
                                    mTvTiePianShowType.setText("拨打电话");
                                    mEtOther.setText(mAdvertBean.link);
                                    break;
                                case 3:
                                    mLlLink.setVisibility(View.GONE);
                                    mEtOther.setVisibility(View.VISIBLE);
                                    mTvTiePianShowType.setText("联系QQ");
                                    mEtOther.setText(mAdvertBean.link);
                                    break;
                            }
                        } else {
                            mAdvertBean = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
                            mAdvertBean.ad_type = 6;
                            mAdvertBean.settings.time = "3S";
                            mTvTiePianTime.setText("3S");
                            mAdvertBean.settings.linktype = 1;
                            mTvTiePianShowType.setText("跳转链接");
                        }
                    }

                    @Override
                    public void onError(Response<PersonalAds> response) {
                        super.onError(response);
                        mAdvertBean = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
                        mAdvertBean.ad_type = 6;
                    }
                });
    }

    @Override
    protected void initListeners() {
        mCbUseSpace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAdvertBean.settings.is_link = 1;
                    mEtName.setText(AdvertTemplateActivity.mLinkUrl);
                    mAdvertBean.link = AdvertTemplateActivity.mLinkUrl;
                } else {
                    mAdvertBean.settings.is_link = 0;
                    mEtName.setText("");
                    mAdvertBean.link = "";
                }
            }
        });

        mSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAdvertBean.is_slide = isChecked ? 1 : 0;
            }
        });

        mEtName.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mAdvertBean.link = s.toString();
            }
        });

        mEtOther.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mAdvertBean.link = s.toString();
            }
        });
    }

    @Override
    public void pickListener(View view) {
        switch (view.getId()) {
            case R.id.btnPhoto:
                setWidth();
                Intent intent = new Intent(getContext(), ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, 0x0011);
                break;
            case R.id.btnPick:
                setWidth();
                Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                startActivityForResult(intent1, 0x0011);
                break;
            case R.id.btnPicFromLocal:
                toActivityWithResult(EnterpriseMaterialTiePianActivity.class, 0x0011);
                break;
        }
    }

    private void setWidth() {
        float density = getResources().getDisplayMetrics().density;
        AdvertTemplateActivity.sImagePicker.setFocusWidth((int) (density * 360));   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        AdvertTemplateActivity.sImagePicker.setFocusHeight((int) (density * 210));  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        AdvertTemplateActivity.sImagePicker.setOutPutX((int) (density * 360));//保存文件的宽度。单位像素
        AdvertTemplateActivity.sImagePicker.setOutPutY((int) (density * 210));//保存文件的高度。单位像素
    }

    @OnClick({R.id.iv_delete, R.id.ivAd, R.id.tv_TiePian_Time, R.id.tv_TiePian_ShowType, R.id.ll_company, R.id.ll_common_advert, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                delete();
                break;
            case R.id.ivAd:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(this);
                    mChoosePic.hideCommonView();
                }
                mChoosePic.show();
                break;
            case R.id.tv_TiePian_Time:
                showChooseDialog(true);
                break;
            case R.id.tv_TiePian_ShowType:
                showChooseDialog(false);
                break;
            case R.id.ll_company:
                toActivityWithResult(EnterpriseMaterialTiePianActivity.class, 0x0011);
                break;
            case R.id.ll_common_advert:
                if (mFlLayout.getVisibility() == View.VISIBLE) {
                    asyncShowToast("贴片广告只可添加一个!");
                    return;
                }
                mAdvertBean.settings.linktype = 1;
                mTvTiePianShowType.setText("跳转链接");
                mFlLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_video:
                Intent intent = new Intent(mActivity, EducationActivity.class);
                intent.putExtra("tag", 2);
                startActivity(intent);
                break;
        }
    }

    private void delete() {
        if (mAdvertBean.id == 0) {
            clearView();
            return;
        }
        OkGo.<CommonBean>get(Constant.DELETE_AD)
                .params("id", mAdvertBean.id)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        if (response != null && response.body() != null && response.body().code == 200) {
                            clearView();
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

    private void clearView() {
        mTvTiePianShowType.setText("");
        mLlLink.setVisibility(View.VISIBLE);
        mEtName.setText("");
        mEtOther.setVisibility(View.GONE);
        mCbUseSpace.setChecked(false);
        mSw.setChecked(true);
        mAdvertBean.settings.time = "3S";
        mTvTiePianTime.setText("3S");
        mIvAd.setImageResource(R.mipmap.admodle_guige);
        mAdvertBean = new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert();
        mAdvertBean.position = 0;
        mAdvertBean.ad_type = 6;
        mFlLayout.setVisibility(View.INVISIBLE);
        asyncShowToast("删除成功");
    }

    private void showChooseDialog(boolean isChooseTime) {
        mIsChooseTime = isChooseTime;
        if (mChooseType == null && getContext() != null) {
            mChooseType = new TiePianChooseDialog(getContext());
            mChooseType.setChooseTimeListener(this);
        }
        mChooseType.setIsChooseTime(isChooseTime).show();
    }

    @Override
    public void chooseTime(View view) {
        switch (view.getId()) {
            case R.id.tv_3:
                if (mIsChooseTime) {
                    mAdvertBean.settings.time = "3S";
                    mTvTiePianTime.setText("3S");
                } else {
                    mAdvertBean.settings.linktype = 1;
                    mTvTiePianShowType.setText("跳转链接");
                    mEtOther.setText("");
                    mEtOther.setHint("请输入您的链接地址");
                    mLlLink.setVisibility(View.VISIBLE);
                    mEtOther.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_4:
                if (mIsChooseTime) {
                    mAdvertBean.settings.time = "4S";
                    mTvTiePianTime.setText("4S");
                } else {
                    mAdvertBean.settings.linktype = 2;
                    mEtOther.setText("");
                    mEtOther.setHint("请输入您的联系电话");
                    mTvTiePianShowType.setText("拨打电话");
                    mLlLink.setVisibility(View.GONE);
                    mEtOther.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_5:
                if (mIsChooseTime) {
                    mAdvertBean.settings.time = "5S";
                    mTvTiePianTime.setText("5S");
                } else {
                    mAdvertBean.settings.linktype = 3;
                    mEtOther.setText("");
                    mEtOther.setHint("请输入您的QQ号码");
                    mTvTiePianShowType.setText("联系QQ");
                    mLlLink.setVisibility(View.GONE);
                    mEtOther.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<ImageItem> mImages;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null && requestCode == 0x0011) {
            mImages = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (mImages != null && mImages.size() > 0) {
                ImagePicker.getInstance().getImageLoader().displayImage(getActivity(), mImages.get(0).path, mIvAd, 0, 0);
                mAdvertBean.images = mImages.get(0).path;
            }
        } else if (resultCode == 0x0022) {
            if (mFlLayout.getVisibility() == View.INVISIBLE) {
                mFlLayout.setVisibility(View.VISIBLE);
                mAdvertBean.settings.time = "3S";
                mAdvertBean.settings.linktype = 1;
                mTvTiePianShowType.setText("跳转链接");
            }
            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert info = (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert) data.getSerializableExtra("info");
            Glide.with(mActivity).load(info.images).apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(mIvAd);
            mAdvertBean.images = info.images;
        }
    }

}
