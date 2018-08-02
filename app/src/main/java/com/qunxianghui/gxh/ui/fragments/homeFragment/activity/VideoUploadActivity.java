package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoUploadActivity extends BaseActivity {

    @BindView(R.id.image_UpdataVideo_Icon)
    ImageView mImageUpdataVideoIcon;
    @BindView(R.id.tv_video_type_choice)
    TextView mTvVideoTypeChoice;
    @BindView(R.id.edit_UpdataVideo_title)
    EditText mEditUpdataVideoTitle;
    @BindView(R.id.edit_UpdataVideo_Content)
    EditText mEditUpdataVideoContent;

    private String mVideoPath;
    private List<String> mStrings;
    private OptionsPickerView mChangeSex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_upload;
    }

    @Override
    protected void initViews() {
        mVideoPath = getIntent().getStringExtra("videoPath");
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(R.mipmap.default_img)
                                .placeholder(R.mipmap.default_img))
                .load(mVideoPath)
                .into(mImageUpdataVideoIcon);
    }

    @OnClick({R.id.tv_UpdataVideo_UpLoad, R.id.tv_UpdataVideo_Cancel, R.id.tv_video_type_choice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_UpdataVideo_UpLoad:
                if ("请选择".equals(mTvVideoTypeChoice.getText().toString())) {
                    asyncShowToast("请先选择视频分类");
                    return;
                }
                if (TextUtils.isEmpty(mEditUpdataVideoTitle.getText().toString().trim())) {
                    asyncShowToast("请填写视频标题");
                    return;
                }
                asyncShowToast("上传");
                break;
            case R.id.tv_UpdataVideo_Cancel:
                finish();
                break;
            case R.id.tv_video_type_choice:
                chooseUserSex();
                break;
        }
    }

    private void chooseUserSex() {
        if (mChangeSex == null) {
            mStrings = new ArrayList<>();
            mStrings.add("搞笑");
            mStrings.add("体育");
            mStrings.add("汽车");
            mStrings.add("娱乐");
            mStrings.add("财经");
            mStrings.add("科技");
            mChangeSex = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mTvVideoTypeChoice.setText(mStrings.get(options1));
                }
            }).setCancelColor(Color.parseColor("#676767"))
                    .setSubmitColor(Color.parseColor("#D81717"))
                    .build();
            mChangeSex.setNPicker(mStrings, null, null);
        }
        mChangeSex.show();
    }

}
