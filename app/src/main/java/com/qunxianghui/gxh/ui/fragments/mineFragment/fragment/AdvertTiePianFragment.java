package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_tiepian;
    }

    @Override
    public void initViews(View view) {
        mTvTitle.setText("贴片广告");
    }

    @Override
    protected void initListeners() {
        mCbUseSpace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
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
        AdvertTemplateActivity.sImagePicker.setFocusWidth((int) (density * 360));   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        AdvertTemplateActivity.sImagePicker.setFocusHeight((int) (density * 210));  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        AdvertTemplateActivity.sImagePicker.setOutPutX((int) (density * 360));//保存文件的宽度。单位像素
        AdvertTemplateActivity.sImagePicker.setOutPutY((int) (density * 210));//保存文件的高度。单位像素
    }

    @OnClick({R.id.iv_delete, R.id.ivAd, R.id.tv_TiePian_Time, R.id.tv_TiePian_ShowType, R.id.ll_company, R.id.ll_common_advert, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                mTvTiePianTime.setText("");
                mTvTiePianShowType.setText("");
                mLlLink.setVisibility(View.VISIBLE);
                mEtName.setText("");
                mEtOther.setVisibility(View.GONE);
                mCbUseSpace.setChecked(false);
                mSw.setChecked(false);
                mFlLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.ivAd:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                    mChoosePic.setImgPickListener(this);
                    mChoosePic.setCommonText("使用企业素材");
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
                asyncShowToast("企业");
                break;
            case R.id.ll_common_advert:
                mFlLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_video:
                asyncShowToast("教学视频");
                break;
        }
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
                    mTvTiePianTime.setText("3s");
                } else {
                    mTvTiePianShowType.setText("跳转链接");
                    mEtOther.setText("");
                    mEtOther.setHint("请输入您的链接地址");
                    mLlLink.setVisibility(View.VISIBLE);
                    mEtOther.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_4:
                if (mIsChooseTime) {
                    mTvTiePianTime.setText("4s");
                } else {
                    mEtOther.setText("");
                    mEtOther.setHint("请输入您的联系电话");
                    mTvTiePianShowType.setText("拨打电话");
                    mLlLink.setVisibility(View.GONE);
                    mEtOther.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_5:
                if (mIsChooseTime) {
                    mTvTiePianTime.setText("5s");
                } else {
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
            }
        }
    }
}