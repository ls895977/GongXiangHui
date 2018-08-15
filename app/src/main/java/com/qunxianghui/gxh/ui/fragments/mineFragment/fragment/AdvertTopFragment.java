package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
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
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialActivity;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.AdvertChooseTypeDialog;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTopFragment extends BaseFragment implements View.OnClickListener, AdvertChoosePicDialog.ImgPickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.circleIndicatorView)
    CircleIndicatorView mCircleIndicatorView;

    private AdvertPagerAdapter mPagerAdapter;
    private AdvertChooseTypeDialog mChooseType;
    private AdvertChoosePicDialog mChoosePic;
    private List<View> mViewList = new ArrayList<>();
    private List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_top;
    }

    @Override
    public void initData() {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 1)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
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
        switch (view.getId()) {
            case R.id.ll_company:
                toActivityWithResult(EnterpriseMaterialActivity.class, 0x0011);
                break;
            case R.id.ll_common:
                asyncShowToast("通用素材");
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
        switch (view.getId()) {
            case R.id.btnPhoto:
                takePhoto();
                break;
            case R.id.btnPick:
                pickImg();
                break;
            case R.id.btnPicFromLocal:
                break;
            case R.id.btnCommon:
                break;
        }
    }

    private void takePhoto() {
        setWidth();
        Intent intent = new Intent(getContext(), ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, 0x0011);
    }

    private void pickImg() {
        setWidth();
        Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
        startActivityForResult(intent1, 0x0011);
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
                int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
                mViewList.remove(index);
                mList.remove(index);
                mPagerAdapter.notifyDataSetChanged();
                changeCircleView();
                break;
            case R.id.iv_add_big_img:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(this);
                }
                mChoosePic.showLocalView().show();
                break;
            case R.id.tv_choose_type:
                if (mChooseType == null && getContext() != null) {
                    mChooseType = new AdvertChooseTypeDialog(getContext(), mViewList, mVp);
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
                mChoosePic.hideLocalView().show();
                break;
        }
    }

    private void addPage(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert data) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_tonglan, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("通栏广告");
        view.findViewById(R.id.rl_add_img).setVisibility(View.GONE);
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.iv_add_big_img).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_type).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_activity_link).setOnClickListener(this);
        ImageView bigImg = view.findViewById(R.id.ivAd);
        bigImg.setOnClickListener(this);
        ((AppCompatCheckBox) view.findViewById(R.id.cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(this);
        if (data != null) {
            Glide.with(AdvertTopFragment.this).load(data.images)
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(bigImg);
            switch (data.settings.operate) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
        mViewList.add(view);
        mList.add(new EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<ImageItem> mImages;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null && requestCode == 0x0011) {
            mImages = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (mImages != null && mImages.size() > 0) {
                ImagePicker.getInstance().getImageLoader().displayImage(getActivity(), mImages.get(0).path, getCurrentImageView(), 0, 0);
            }
        }
    }

    private ImageView getCurrentImageView() {
        View view = mViewList.get(mVp.getCurrentItem());
        if (mChoosePic.mIsBigImg) {
            return view.findViewById(R.id.iv_add_big_img);
        } else {
            return view.findViewById(R.id.ivAd);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
