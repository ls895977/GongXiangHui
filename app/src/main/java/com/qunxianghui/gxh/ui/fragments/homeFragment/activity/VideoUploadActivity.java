package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeVideoSortBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoUploadActivity extends BaseActivity {

    @BindView(R.id.video_player)
    JZVideoPlayerStandard mVideoplayer;
    @BindView(R.id.tv_type)
    TextView mTvVideoTypeChoice;
    @BindView(R.id.edit_UpdataVideo_title)
    EditText mEditUpdataVideoTitle;
    @BindView(R.id.edit_UpdataVideo_Content)
    EditText mEditUpdataVideoContent;

    private String mVideoPath;
    private List<String> mStrings;
    private OptionsPickerView mChangeSex;
    private List<HomeVideoSortBean.DataBean> mVideoSortList;
    private String mCate_name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_upload;
    }

    @Override
    protected void initViews() {
        mVideoPath = getIntent().getStringExtra("videoPath");
        mVideoplayer.setUp(mVideoPath, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(R.mipmap.default_img)
                                .placeholder(R.mipmap.default_img))
                .load(mVideoPath)
                .into(mVideoplayer.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @OnClick({R.id.tv_UpdataVideo_UpLoad, R.id.tv_UpdataVideo_Cancel, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_UpdataVideo_UpLoad:
                if ("视频分类".equals(mTvVideoTypeChoice.getText().toString())) {
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
            case R.id.tv_type:
                chooseUserSex();
                break;
        }
    }
    private void chooseUserSex() {
        OkGo.<String>post(Constant.UPLOAD_VIDEO_ADD_SORT_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ParseVideoSortData(response.body());

                    }
                });
    }

    private void ParseVideoSortData(String body) {
        HomeVideoSortBean homeVideoSortBean = GsonUtils.jsonFromJson(body, HomeVideoSortBean.class);
        int code = homeVideoSortBean.getCode();
        mVideoSortList = homeVideoSortBean.getData();
        if (code == 200) {
            if (mChangeSex == null) {
                mStrings = new ArrayList<>();
                for (int i=0;i<mVideoSortList.size();i++){
                    mCate_name = mVideoSortList.get(i).getCate_name();
                    mStrings.add(mCate_name);

                }
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

}
