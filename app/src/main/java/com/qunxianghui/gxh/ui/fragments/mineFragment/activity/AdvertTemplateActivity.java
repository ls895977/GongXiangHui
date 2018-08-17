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

    public static ImagePicker sImagePicker;
    private String[] mTitleTwo = {"底部", "顶部"};
    private String[] mTitles = {"底部", "顶部", "贴片"};
    private List<Fragment> mFragments = new ArrayList<>();

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
//                asyncShowToast("保存");
                upLoadPic(AdvertTiePianFragment.mAdvertBean.images, "0");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sImagePicker = null;
    }

    private void upLoadPic(String urls, final String index) {
        OkGo.<String>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", urls)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if ("0".equals(uploadImage.code)) {
                            AdvertTiePianFragment.mAdvertBean.images = uploadImage.data.file;
                            uploadInfo(AdvertTiePianFragment.mAdvertBean, index);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        mLoadView.setVisibility(View.GONE);
                    }
                });
    }

    private void uploadInfo(EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert, String index) {
        PostRequest<CommonBean> post = OkGo.post(Constant.EDIT_AD);
        post.params("ad[" + index + "][images]", companyAdvert.images);
        post.params("ad[" + index + "][ad_type]", companyAdvert.ad_type);
        post.params("ad[" + index + "][position]", companyAdvert.position);
        if (companyAdvert.id != 0) {
            post.params("ad[" + index + "][id]", companyAdvert.position);
        }
        //贴片广告
        if (companyAdvert.ad_type == 6) {
            if (companyAdvert.settings.time != 0) {
                post.params("ad[" + index + "][settings][time]", companyAdvert.settings.time);
            }
            if (companyAdvert.settings.operate != 0) {
                post.params("ad[" + index + "][settings][operate]", companyAdvert.settings.operate);
                switch (companyAdvert.settings.operate) {
                    case 1:
                        if (!TextUtils.isEmpty(companyAdvert.settings.link)) {
                            post.params("ad[" + index + "][settings][link]", companyAdvert.settings.link);
                        }
                        post.params("ad[" + index + "][settings][is_link]", companyAdvert.settings.is_link);
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(companyAdvert.settings.mobile)) {
                            post.params("ad[" + index + "][settings][mobile]", companyAdvert.settings.mobile);
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(companyAdvert.settings.qq)) {
                            post.params("ad[" + index + "][settings][qq]", companyAdvert.settings.qq);
                        }
                        break;
                }
                post.params("ad[" + index + "][is_slide]", companyAdvert.status);
            }


            //底部广告
        } else if (companyAdvert.position == 2) {


            //顶部广告
        } else if (companyAdvert.position == 1) {

        }

        post.execute(new JsonCallback<CommonBean>() {
            @Override
            public void onSuccess(Response<CommonBean> response) {

            }
        });
    }
}
