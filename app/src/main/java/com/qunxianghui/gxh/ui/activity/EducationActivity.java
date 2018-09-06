package com.qunxianghui.gxh.ui.activity;

import android.content.Intent;

import com.bumptech.glide.Glide;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class EducationActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_education;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int tag = intent.getIntExtra("tag", 0);
        if (tag==2){
            mVideoplayer.setUp(Constant.EDUCATION_VIDEO2_URL, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "教学视频");
            Glide.with(this).load(Constant.EDUCATION_VIDEO_PIC).into(mVideoplayer.thumbImageView);
        }else {
            mVideoplayer.setUp(Constant.EDUCATION_VIDEO_URL, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "教学视频");
            Glide.with(this).load(Constant.EDUCATION_VIDEO_PIC).into(mVideoplayer.thumbImageView);
        }

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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
