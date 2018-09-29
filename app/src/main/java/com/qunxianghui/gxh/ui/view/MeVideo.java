package com.qunxianghui.gxh.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by gs on 2018/9/27.
 */

public class MeVideo extends JZVideoPlayerStandard {

    private MeVideo.PlayCompleteLister playCompleteLister;
    private String playUrl = "";
    public void setVideoListClickListener(MeVideo.PlayCompleteLister playCompleteLister) {
        this.playCompleteLister = playCompleteLister;
    }

    public MeVideo(Context context) {
        super(context);
    }

    public MeVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void init(Context context) {
        super.init(context);

    }

    //onState 代表了播放器引擎的回调，播放视频各个过程的状态的回调
    @Override
    public void onStateNormal() {
        super.onStateNormal();
        Log.e("TAG_进度条","onStateNormal="+currentState);
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        Log.e("TAG_进度条","onStatePreparing="+currentState);
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        Log.e("TAG_进度条","onStatePlaying="+currentState);
        playUrl = getCurrentUrl().toString();
        Log.e("TAG_进度条","onStatePlaying="+playUrl);
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        Log.e("TAG_进度条","onStatePause="+currentState);
    }

    @Override
    public void onStateError() {
        super.onStateError();
        Log.e("TAG_进度条","onStateError="+currentState);
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        Log.e("TAG_进度条","onStateAutoComplete="+currentState);
        playCompleteLister.playCompleteState(playUrl);
    }

    public interface PlayCompleteLister{
        public void playCompleteState(String playUrl);
    }
}
