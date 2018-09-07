package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.UploadVideo;
import com.qunxianghui.gxh.bean.home.HomeVideoSortBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class VideoUploadActivity extends BaseActivity {

    @BindView(R.id.tv_UpdataVideo_UpLoad)
    View mUpload;
    @BindView(R.id.video_player)
    JZVideoPlayerStandard mVideoplayer;
    @BindView(R.id.tv_type)
    TextView mTvVideoTypeChoice;
    @BindView(R.id.edit_UpdataVideo_title)
    EditText mEditUpdateVideoTitle;
    @BindView(R.id.edit_UpdataVideo_Content)
    EditText mEditUpdateVideoContent;
    @BindView(R.id.ll_bg_load)
    View mLoadView;
    @BindView(R.id.tv_load)
    TextView mTvLoad;

    private String mVideoPath;
    private OptionsPickerView mChooseType;
    public boolean mIsUploadIng;
    private int mTypeId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_upload;
    }

    @Override
    protected void initViews() {
        mTvLoad.setText("上传中...");
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
    protected void initListeners() {
        mEditUpdateVideoTitle.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if ("视频分类".equals(mTvVideoTypeChoice.getText().toString()) || TextUtils.isEmpty(s)) {
                    mUpload.setEnabled(false);
                } else {
                    mUpload.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        if (mIsUploadIng) {
            asyncShowToast("上传中，请稍等...");
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
                    asyncShowToast("您尚未选择分类！");
                    return;
                }
                if (TextUtils.isEmpty(mEditUpdateVideoTitle.getText().toString().trim())) {
                    asyncShowToast("您尚未填写视频标题！");
                    return;
                }

                if (Utils.isTwoClick()) {
                    uploadVideo();
                }

                break;
            case R.id.tv_UpdataVideo_Cancel:
                onBackPressed();
                break;
            case R.id.tv_type:
                hideKeyboard(view);
                chooseVideoType();
                break;
        }
    }

    private void uploadVideo() {
        mLoadView.setVisibility(View.VISIBLE);
        mVideoplayer.thumbImageView.buildDrawingCache();
        Bitmap bitmap = mVideoplayer.thumbImageView.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        File file = new File(getCacheDir() + "gongxianghui.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArray, 0, byteArray.length);
            fos.flush();

            Luban.with(VideoUploadActivity.this) // 初始化
                    .load(file) // 要压缩的图片
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File newFile) {
                            String newPath = newFile.getAbsolutePath();
//                            Log.d("lubanLog", "new/" + "第" + 0 + "个图片的大小为：" + newFile.length() / 1024 + "KB");
//                            Log.d("lubanLog", "new/" + "第" + 0 + "个图片的路径为：" + newPath);
                            uploadVideo(newFile, mVideoPath, mEditUpdateVideoTitle.getText().toString(),
                                    mEditUpdateVideoContent.getText().toString(), mTypeId);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    }).launch();
        } catch (IOException e) {
            mLoadView.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    private void chooseVideoType() {
        if (mChooseType != null) {
            mChooseType.show();
        } else {
            OkGo.<HomeVideoSortBean>post(Constant.UPLOAD_VIDEO_ADD_SORT_URL)
                    .execute(new JsonCallback<HomeVideoSortBean>() {
                        @Override
                        public void onSuccess(Response<HomeVideoSortBean> response) {
                            parseVideoSortData(response.body());
                        }
                    });
        }
    }

    private void parseVideoSortData(HomeVideoSortBean homeVideoSortBean) {
        int code = homeVideoSortBean.getCode();
        final List<HomeVideoSortBean.DataBean> videoSortBeanData = homeVideoSortBean.getData();
        if (code == 200) {
            final List<String> strings = new ArrayList<>();
            for (HomeVideoSortBean.DataBean dataBean : videoSortBeanData) {
                strings.add(dataBean.getCate_name());
            }
            mChooseType = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mTypeId = videoSortBeanData.get(options1).getId();
                    mTvVideoTypeChoice.setText(strings.get(options1));
                    if (TextUtils.isEmpty(mEditUpdateVideoTitle.getText().toString().trim())) {
                        mUpload.setEnabled(false);
                    } else {
                        mUpload.setEnabled(true);
                    }
                }
            }).setCancelColor(Color.parseColor("#676767"))
                    .setSubmitColor(Color.parseColor("#D81717"))
                    .build();
            mChooseType.setNPicker(strings, null, null);
            mChooseType.show();
        } else if (code == 300) {
            toActivity(LoginActivity.class);
        }
    }

    private void uploadVideo(File file, String videoPath, String title, String description, int videoId) {
        mIsUploadIng = true;
        asyncShowToast("上传中...");
        OkGo.<UploadVideo>post(Constant.UPLOAD_VIDEO_URL)
                .params("file", new File(videoPath))
                .params("thumb", file)
                .params("title", title)
                .params("description", description)
                .params("video_id", videoId)
                .execute(new JsonCallback<UploadVideo>() {
                    @Override
                    public void onSuccess(Response<UploadVideo> response) {
                        mIsUploadIng = false;
                        mLoadView.setVisibility(View.GONE);
                        UploadVideo uploadVideo = response.body();
                        if (uploadVideo != null && "0".equals(uploadVideo.code)) {
                            asyncShowToast("视频发布成功！");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<UploadVideo> response) {
                        mIsUploadIng = false;
                        mLoadView.setVisibility(View.GONE);
                        asyncShowToast("上传失败...");
                    }
                });
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
