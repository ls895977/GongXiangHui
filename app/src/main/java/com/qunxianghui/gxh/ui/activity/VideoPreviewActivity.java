package com.qunxianghui.gxh.ui.activity;

import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPreviewActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard mVideoplayer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_preview;
    }

    @Override
    protected void initViews() {
        mVideoplayer.setUp(getIntent().getStringExtra("videoPath"), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @OnClick({R.id.back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_ok:
                break;
        }
    }
}
