package com.qunxianghui.gxh.video;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/**
 * 计算滑动，自动播放的帮助类
 * Created by guoshuyu on 2017/11/2.
 */

public class ScrollCalculatorHelper {

    private int firstVisible = 0;
    private int lastVisible = 0;
    private int visibleCount = 0;
    private int playId;
    private int rangeTop;
    private int rangeBottom;
    private PlayRunnable runnable;

    private Handler playHandler = new Handler();

    public ScrollCalculatorHelper(int playId, int rangeTop, int rangeBottom) {
        this.playId = playId;
        this.rangeTop = rangeTop;
        this.rangeBottom = rangeBottom;
    }

    public void onScrollStateChanged(RecyclerView view, int scrollState) {
        switch (scrollState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                playVideo(view);
                break;
        }
    }

    public void onScroll(RecyclerView view, int firstVisibleItem, int lastVisibleItem, int visibleItemCount) {
//        if (firstVisible == firstVisibleItem) {
//            return;
//        }
        firstVisible = firstVisibleItem;
        lastVisible = lastVisibleItem;
        visibleCount = visibleItemCount;
    }


    void playVideo(RecyclerView view) {

        if (view == null) {
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        GSYBaseVideoPlayer gsyBaseVideoPlayer = null;

        boolean needPlay = false;
        for (int i = 0; i < visibleCount; i++) {
            if (layoutManager.getChildAt(i) != null && layoutManager.getChildAt(i).findViewById(playId) != null) {
                GSYBaseVideoPlayer player = (GSYBaseVideoPlayer) layoutManager.getChildAt(i).findViewById(playId);
                Rect rect = new Rect();
                player.getLocalVisibleRect(rect);
                int height = player.getHeight();
                //说明第一个完全可视
                if (rect.top == 0 && rect.bottom == height) {
                    gsyBaseVideoPlayer = player;
                    if ((player.getCurrentPlayer().getCurrentState() == GSYBaseVideoPlayer.CURRENT_STATE_NORMAL
                            || player.getCurrentPlayer().getCurrentState() == GSYBaseVideoPlayer.CURRENT_STATE_ERROR)) {
                        needPlay = true;
                    }
                    break;
                }

            }
        }
        Log.e("TAG_播放","gsyBaseVideoPlayer="+(gsyBaseVideoPlayer != null)+"===;needPlay="+needPlay);
//        /**
//         * 自動播放
//         * 列表自动播放下一个视频时needPlay默认全部true，
//         * 手势滑动停止后自动播放 注释掉needPlay =true;
//         */
//        needPlay =true;
        if (gsyBaseVideoPlayer != null && needPlay) {
            Log.e("TAG_播放","runnable="+(runnable != null));
            if (runnable != null) {
                GSYBaseVideoPlayer tmpPlayer = runnable.gsyBaseVideoPlayer;
                playHandler.removeCallbacks(runnable);
                runnable = null;
                if (tmpPlayer == gsyBaseVideoPlayer) {
                    return;
                }
            }else {
                runnable = new PlayRunnable(gsyBaseVideoPlayer);
                //降低频率
                playHandler.postDelayed(runnable, 400);
            }
        }


    }

    private class PlayRunnable implements Runnable {

        GSYBaseVideoPlayer gsyBaseVideoPlayer;

        public PlayRunnable(GSYBaseVideoPlayer gsyBaseVideoPlayer) {
            this.gsyBaseVideoPlayer = gsyBaseVideoPlayer;
        }

        @Override
        public void run() {
            boolean inPosition = false;
            //如果未播放，需要播放
            if (gsyBaseVideoPlayer != null) {
                int[] screenPosition = new int[2];
                gsyBaseVideoPlayer.getLocationOnScreen(screenPosition);
                int halfHeight = gsyBaseVideoPlayer.getHeight() / 2;
                int rangePosition = screenPosition[1] + halfHeight;
                //中心点在播放区域内
                Log.e("TAG_偏移量上","rangePosition="+rangePosition+"===;rangeTop"+rangeTop);
                Log.e("TAG_偏移量下","rangePosition="+rangePosition+"===;rangeBottom"+rangeBottom);
                if (rangePosition >= rangeTop && rangePosition <= rangeBottom) {
                    inPosition = true;
                }
                if (inPosition) {
                    startPlayLogic(gsyBaseVideoPlayer, gsyBaseVideoPlayer.getContext());
                    //gsyBaseVideoPlayer.startPlayLogic();
                }
            }
        }
    }


    /***************************************自动播放的点击播放确认******************************************/
    public void startPlayLogic(GSYBaseVideoPlayer gsyBaseVideoPlayer, Context context) {
        if (!CommonUtil.isWifiConnected(context)) {
            //这里判断是否wifi
            showWifiDialog(gsyBaseVideoPlayer, context);
            return;
        }
        gsyBaseVideoPlayer.startPlayLogic();
    }

    private void showWifiDialog(final GSYBaseVideoPlayer gsyBaseVideoPlayer, Context context) {
        if (!NetworkUtils.isAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(com.shuyu.gsyvideoplayer.R.string.no_net), Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getResources().getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi));
        builder.setPositiveButton(context.getResources().getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                gsyBaseVideoPlayer.startPlayLogic();
            }
        });
        builder.setNegativeButton(context.getResources().getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
