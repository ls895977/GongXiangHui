package com.qunxianghui.gxh.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.widget.RoundImageView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerItemNormalHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";
    protected Context context = null;
    @BindView(R.id.video_item_player)
    public SampleCoverVideo gsyVideoPlayer;
    @BindView(R.id.ll_homevideo_list_item)
    public LinearLayout mLLHomeVideoList;
    public @BindView(R.id.round_item_collect_video_personhead)
    RoundImageView roundCollectVideoPersonhead;
    public @BindView(R.id.tv_mycollect_video_attention)
    TextView tvVideoAttention;
    public @BindView(R.id.tv_item_collect_video_personname)
    TextView tvItemCollectCideoPersonname;
    public @BindView(R.id.tv_mycollect_video_paster)
    TextView tvVideoPaster;
    public @BindView(R.id.tv_like)
    TextView tvLike;
    public @BindView(R.id.tv_comment)
    TextView tvComment;
    public @BindView(R.id.iv_item_collect_video_like)
    ImageView ivItemCollectCideoLike;
    public @BindView(R.id.rl_videolist_item_click)
    RelativeLayout rl_videolist_item_click;

    public ImageView imageView;
    GSYVideoOptionBuilder gsyVideoOptionBuilder;

    public RecyclerItemNormalHolder(Context context, View v) {
        super(v);
        this.context = context;
        ButterKnife.bind(this, v);
        imageView = new ImageView(context);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
    }

    public void onBind(final int position, HomeVideoListBean.DataBean.ListBean videoModel) {
        String video_url = videoModel.getVideo_url();
        //listVideoUtil.setCachePath(new File(FileUtils.getPath()));
//        listVideoUtil.startPlay(video_url);
//        //增加封面
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        ENPlayView viewById = gsyVideoPlayer.findViewById(R.id.start);
//        viewById.setBackgroundResource(R.mipmap.gsy_start);
        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }

        //防止错位，离开释放
        //gsyVideoPlayer.initUIState();
        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setThumbImageView(imageView)
                .setUrl(video_url)
                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setVideoTitle(videoModel.getTitle())
                .setCacheWithPlay(true)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(position)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }

                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                        gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                    }
                }).build(gsyVideoPlayer);


        //增加title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        gsyVideoPlayer.setTvTitle(videoModel.getTitle());

        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(gsyVideoPlayer);
            }
        });
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }

}