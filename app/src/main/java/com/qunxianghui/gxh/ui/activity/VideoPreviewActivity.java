package com.qunxianghui.gxh.ui.activity;

import android.content.Intent;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.VideoUploadActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoPreviewActivity extends BaseActivity {

    @BindView(R.id.video_player)
    JZVideoPlayerStandard mVideoplayer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_preview;
    }

    @Override
    protected void initViews() {
        mVideoplayer.setUp(getIntent().getStringExtra("videoPath"), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
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

    @OnClick({R.id.back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_ok:
                Intent intent = new Intent(VideoPreviewActivity.this, VideoUploadActivity.class);
                intent.putExtra("videoPath", getIntent().getStringExtra("videoPath"));
                VideoPreviewActivity.this.startActivity(intent);
                finish();
                break;
        }
    }
}
