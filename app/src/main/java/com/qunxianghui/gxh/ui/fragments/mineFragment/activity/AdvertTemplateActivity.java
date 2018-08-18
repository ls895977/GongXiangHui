package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.AdvertBottomFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.AdvertTiePianFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.AdvertTopFragment;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertTemplateActivity extends BaseActivity {

    @BindView(R.id.segment_tab)
    SegmentTabLayout mSegmentTab;
    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.ll_bg_load)
    View mLoadView;

    public static ImagePicker sImagePicker;
    private String[] mTitleTwo = {"底部", "顶部"};
    private String[] mTitles = {"底部", "顶部", "贴片"};
    private List<Fragment> mFragments = new ArrayList<>();
    private PostRequest<CommonBean> mPost;
    private List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advert_template;
    }

    @Override
    protected void initViews() {
        sImagePicker = ImagePicker.getInstance();
        sImagePicker.setImageLoader(new NewGlideImageLoader());   //设置图片加载器
        sImagePicker.setShowCamera(true);                      //显示拍照按钮
        sImagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        sImagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        sImagePicker.setMultiMode(false);
        sImagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        sImagePicker.setShowCamera(false);


        mSegmentTab.setTabData(mTitles);
        Intent intent = getIntent();
        int adverTag = intent.getIntExtra("adverTag", 0);
        mFragments.add(new AdvertBottomFragment());
        mFragments.add(new AdvertTopFragment());
        mFragments.add(new AdvertTiePianFragment());
        mVp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), mFragments));
        mVp.setOffscreenPageLimit(mSegmentTab.getTabCount() - 1);
    }

    @Override
    protected void initListeners() {
        mSegmentTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                upLoadData();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sImagePicker = null;
    }

    private void upLoadData() {
        for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert : AdvertBottomFragment.mList) {
            if (TextUtils.isEmpty(companyAdvert.images)) {
                asyncShowToast("请完善底部广告相关信息");
                return;
            }
        }
//        for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert : AdvertTopFragment.mList) {
//            if (TextUtils.isEmpty(companyAdvert.images)) {
//                asyncShowToast("请完善顶部广告相关信息");
//                return;
//            }
//        }
        if (AdvertTiePianFragment.mAdvertBean.id != 0 && TextUtils.isEmpty(AdvertTiePianFragment.mAdvertBean.images)) {
            asyncShowToast("请完善贴片广告相关信息");
            return;
        }
        mPost = OkGo.post(Constant.EDIT_AD);
        mLoadView.setVisibility(View.VISIBLE);
        mList = new ArrayList<>();
        mList.addAll(AdvertBottomFragment.mList);
//        list.addAll(AdvertTopFragment.mList);
        if (!TextUtils.isEmpty(AdvertTiePianFragment.mAdvertBean.images))
            mList.add(AdvertTiePianFragment.mAdvertBean);
        for (int i = 0; i < mList.size(); i++) {
            upLoadPic(mList.get(i), i);
        }
    }

    private void upLoadPic(final EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert, final int index) {
        if (companyAdvert.images.startsWith("http")) {
            if (index == mList.size() - 1) {
                execute();
            }
            return;
        }
        OkGo.<String>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(companyAdvert.images))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if ("0".equals(uploadImage.code)) {
                            companyAdvert.images = uploadImage.data.file;
                            if (index == mList.size() - 1) {
                                execute();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mLoadView.setVisibility(View.GONE);
                    }
                });
    }

    private void execute() {
        for (int i = 0; i < mList.size(); i++) {
            uploadInfo(mList.get(i), i + "");
        }
        mPost.execute(new JsonCallback<CommonBean>() {
            @Override
            public void onSuccess(Response<CommonBean> response) {
                if (response.body().code == 200) {
                    asyncShowToast("保存成功");
                    finish();
                } else {
                    asyncShowToast("保存失败");
                }
            }

            @Override
            public void onError(Response<CommonBean> response) {
                super.onError(response);
                mLoadView.setVisibility(View.GONE);
                asyncShowToast("保存失败");
            }
        });
    }

    private void uploadInfo(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert, String index) {
        mPost.params("ad[" + index + "][images]", companyAdvert.images);
        mPost.params("ad[" + index + "][ad_type]", companyAdvert.ad_type);
        mPost.params("ad[" + index + "][position]", companyAdvert.position);
        if (companyAdvert.id != 0) {
            mPost.params("ad[" + index + "][id]", companyAdvert.position);
        }
        //贴片广告
        if (companyAdvert.ad_type == 6) {
            if (!TextUtils.isEmpty(companyAdvert.settings.time)) {
                mPost.params("ad[" + index + "][settings][time]", companyAdvert.settings.time);
            }

            if (companyAdvert.settings.operate != 0) {
                mPost.params("ad[" + index + "][settings][operate]", companyAdvert.settings.operate);
                switch (companyAdvert.settings.operate) {
                    case 1:
                        if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                            mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                        }
                        mPost.params("ad[" + index + "][settings][is_link]", companyAdvert.settings.is_link);
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                            mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(companyAdvert.settings.qq)) {
                            mPost.params("ad[" + index + "][settings][qq]", companyAdvert.settings.qq);
                        }
                        break;
                }
            }
            //底部广告
        } else if (companyAdvert.position == 2) {
            switch (companyAdvert.ad_type) {
                case 1:
                    if (companyAdvert.settings.operate != 0) {
                        mPost.params("ad[" + index + "][settings][operate]", companyAdvert.settings.operate);
                        switch (companyAdvert.settings.operate) {
                            case 1:
                                if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                                    mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                                }
                                mPost.params("ad[" + index + "][settings][is_link]", companyAdvert.settings.is_link);
                                break;
                            case 2:
                                if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                                    mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                                }
                                break;
                            case 3:
                                if (!TextUtils.isEmpty(companyAdvert.settings.qq)) {
                                    mPost.params("ad[" + index + "][settings][qq]", companyAdvert.settings.qq);
                                }
                                break;
                        }
                    }
                    break;
                case 2:
                    if (!TextUtils.isEmpty(companyAdvert.settings.slogan)) {
                        mPost.params("ad[" + index + "][settings][slogan]", companyAdvert.settings.slogan);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                        mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.intro)) {
                        mPost.params("ad[" + index + "][settings][intro]", companyAdvert.settings.intro);
                    }
                    break;
                case 3:
                    if (companyAdvert.settings.operate != 0) {
                        mPost.params("ad[" + index + "][settings][operate]", companyAdvert.settings.operate);
                        switch (companyAdvert.settings.operate) {
                            case 1:
                                if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                                    mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                                }
                                mPost.params("ad[" + index + "][settings][is_link]", companyAdvert.settings.is_link);
                                break;
                            case 2:
                                if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                                    mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                                }
                                break;
                            case 3:
                                if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                                    mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                                }
                                break;
                            case 4:
                            case 5:
                                if (!TextUtils.isEmpty(companyAdvert.settings.pgn_url)) {
                                    mPost.params("ad[" + index + "][settings][pgn_url]", companyAdvert.settings.pgn_url);
                                }
                                break;
                        }
                    }
                    break;
                case 4:
                    if (!TextUtils.isEmpty(companyAdvert.settings.slogan)) {
                        mPost.params("ad[" + index + "][settings][slogan]", companyAdvert.settings.slogan);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.intro)) {
                        mPost.params("ad[" + index + "][settings][intro]", companyAdvert.settings.intro);
                    }
                    break;
                case 5:
                    if (!TextUtils.isEmpty(companyAdvert.settings.name)) {
                        mPost.params("ad[" + index + "][settings][name]", companyAdvert.settings.name);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.qq)) {
                        mPost.params("ad[" + index + "][settings][qq]", companyAdvert.settings.qq);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.intro)) {
                        mPost.params("ad[" + index + "][settings][intro]", companyAdvert.settings.intro);
                    }
                    break;
                case 7:
                    if (!TextUtils.isEmpty(companyAdvert.settings.product_name)) {
                        mPost.params("ad[" + index + "][settings][product_name]", companyAdvert.settings.product_name);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.product_price)) {
                        mPost.params("ad[" + index + "][settings][product_price]", companyAdvert.settings.product_price);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.product_url)) {
                        mPost.params("ad[" + index + "][settings][product_url]", companyAdvert.settings.product_url);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.qq)) {
                        mPost.params("ad[" + index + "][settings][qq]", companyAdvert.settings.qq);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                        mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.store_url)) {
                        mPost.params("ad[" + index + "][settings][store_url]", companyAdvert.settings.store_url);
                    }
                    break;
                case 8:
                    if (!TextUtils.isEmpty(companyAdvert.settings.slogan)) {
                        mPost.params("ad[" + index + "][settings][slogan]", companyAdvert.settings.slogan);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                        mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                    }
                    if (!TextUtils.isEmpty(companyAdvert.settings.intro)) {
                        mPost.params("ad[" + index + "][settings][intro]", companyAdvert.settings.intro);
                    }
                    break;
            }
            //顶部广告
        } else if (companyAdvert.position == 1) {
            if (companyAdvert.settings.operate != 0) {
                mPost.params("ad[" + index + "][settings][operate]", companyAdvert.settings.operate);
                switch (companyAdvert.settings.operate) {
                    case 1:
                        if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                            mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                        }
                        mPost.params("ad[" + index + "][settings][is_link]", companyAdvert.settings.is_link);
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                            mPost.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                            mPost.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                        }
                        break;
                    case 4:
                    case 5:
                        if (!TextUtils.isEmpty(companyAdvert.settings.pgn_url)) {
                            mPost.params("ad[" + index + "][settings][pgn_url]", companyAdvert.settings.pgn_url);
                        }
                        break;
                }
            }
        }
        mPost.params("ad[" + index + "][is_slide]", companyAdvert.status);
    }
}
