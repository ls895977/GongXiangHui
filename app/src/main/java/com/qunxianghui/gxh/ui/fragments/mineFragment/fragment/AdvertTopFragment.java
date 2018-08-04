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

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.TongLanChooseTypeDialog;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTopFragment extends BaseFragment implements View.OnClickListener, AdvertChoosePicDialog.ImgPickListener {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.circleIndicatorView)
    CircleIndicatorView mCircleIndicatorView;

    private AdvertPagerAdapter mPagerAdapter;
    private TongLanChooseTypeDialog mChooseType;
    private AdvertChoosePicDialog mChoosePic;
    private List<View> mViewList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_top;
    }

    @Override
    public void initData() {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
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
                asyncShowToast("通用模版");
                break;
            case R.id.ll_common:
                asyncShowToast("通用素材");
                break;
            case R.id.ll_common_advert:
                addPage();
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
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent = new Intent(getContext(), ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, 0x0011);
            case R.id.btnPick:
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                startActivityForResult(intent1, 0x0012);
                break;
            case R.id.btnPicFromLocal:
                break;
            case R.id.btnCommon:
                break;
        }
    }

    private ImageView getCurrentBigView() {
        View view = mViewList.get(mVp.getCurrentItem());
        return view.findViewById(R.id.iv_add_big_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
                mViewList.remove(index);
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
                    mChooseType = new TongLanChooseTypeDialog(getContext(), mViewList, mVp);
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

    private void addPage() {
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

        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
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

    /**
     * 上传发布的内容
     */

//    private void fetchPublishConentData() {
//        String faBuContent = etBaoliaoFabuContent.getText().toString();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0, length = upLoadPics.size(); i < length; i++) {
//            if (i != upLoadPics.size() - 1) {
//                stringBuilder.append(upLoadPics.get(i) + ",");
//            } else {
//                stringBuilder.append(upLoadPics.get(i));
//            }
//        }
//        OkGo.<String>post(Constant.PUBLISH_ARTICLE)
//                .params("content", faBuContent)
//                .params("images", stringBuilder.toString())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        parseBaoLiaoData(response.body());
//                    }
//                });
//    }

    //上传图片
//    private void upLoadPic(String urls, final boolean isUpdate) {
//        OkGo.<String>post(Constant.UP_LOAD_PIC)
//                .params("base64", urls)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
//                        if (uploadImage.code.equals("0")) {
//                            upLoadPics.add(uploadImage.data.file);
//                            if (isUpdate) {
//                                fetchPublishConentData();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        llPublichLoad.setVisibility(View.GONE);
//                    }
//                });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
//            //添加图片返回
//            if (data != null && requestCode == 0x0011) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images != null) {
//                }
//            }
//        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == 0x0012) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                }
//            }
//        }
//
//    }

}
