package com.qunxianghui.gxh.ui.fragments.locationFragment.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.OtherAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoChannelBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.GestureDetectorActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.HomeVideoActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.DragGrid;
import com.qunxianghui.gxh.widget.OtherGridView;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;

public class LocalServiceChannelActivity extends GestureDetectorActivity implements AdapterView.OnItemClickListener {
    public static final String VIDEO_USER_CHANNEL = "user_channel";//订阅列表
    private HomeVideoChannelBean.DataBean mDatas;
    /**
     * 用户栏目
     */
    private DragGrid userGridView; // GridView
    DragAdapter userAdapter; // 适配器
    ArrayList<ChannelItem> userChannelList = new ArrayList<>();
    /**
     * 其它栏目
     */
    private OtherGridView otherGridView; // GridView
    OtherAdapter otherAdapter; // 适配器
    ArrayList<ChannelItem> otherChannelList = new ArrayList<>(); // 数据源
    boolean isMove = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_service;
    }

    @Override
    protected void initViews() {
        userGridView = findViewById(R.id.userGridView);
        otherGridView = findViewById(R.id.otherGridView);
    }

    @Override
    protected void initData() {
        new TitleBuilder(LocalServiceChannelActivity.this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAdapter != null && userAdapter.isListChanged()) {
                    setResult(HomeVideoActivity.VIDEO_CHANNELRESULT);
                    finish();
                } else {
                    finish();
                }
            }
        }).setTitleText("频道管理");

        //获取全部频道
        OkGo.<String>post(Constant.EDIT_LOCAL_POST_SUB_URL)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                getAllVideoData(response.body());
            }
        });
        otherGridView.setOnItemClickListener(this);
        userGridView.setOnItemClickListener(this);
    }

    private void getAllVideoData(String body) {
        HomeVideoChannelBean channelBean = GsonUtils.jsonFromJson(body, HomeVideoChannelBean.class);
        if (channelBean.data != null) {
            if (channelBean.data.added != null) {
                userChannelList = channelBean.data.added;
            }
            if (channelBean.data.others != null) {
                otherChannelList = channelBean.data.others;
            }
        }
        userAdapter = new DragAdapter(this, userChannelList);
        otherAdapter = new OtherAdapter(this, otherChannelList);
        userGridView.setAdapter(userAdapter);
        otherGridView.setAdapter(otherAdapter);
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        //如果点击的时候，之前动画还没结束，那么就让点击事件无效
        if (isMove) {
            return;
        }
        switch (parent.getId()) {
            case R.id.userGridView:
                //position为 0 的不进行任何操作
                if (position == 0) return;
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    TextView newTextView = view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                    otherAdapter.setVisible(false);
                    //频道列表（用户订阅的频道）
                    OkGo.<String>post(Constant.DELETE_LOCAL_POST_SUB_URL)
                            .params("posts_id", channel.getId())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Logger.d("onSuccess-->:" + response.body().toString());

                                    //添加到最后一个
                                    otherAdapter.addItem(channel);
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            try {
                                                int[] endLocation = new int[2];
                                                //获取终点的坐标
                                                otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                                MoveAnim(moveImageView, startLocation, endLocation, channel, userGridView);
                                                userAdapter.setRemove(position);
                                            } catch (Exception localException) {
                                            }
                                        }
                                    }, 50L);

                                }
                            });
                }
                break;
            case R.id.otherGridView:
                // 其它GridView
                final ImageView moveImageView1 = getView(view);
                if (moveImageView1 != null) {
                    TextView newTextView = view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
                    userAdapter.setVisible(false);
                    //频道列表（用户订阅的频道）
                    OkGo.<String>post(Constant.ADD_LOCAL_POST_SUB_URL)
                            .params("posts_id", channel.getId())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    //添加到最后一个
                                    userAdapter.addItem(channel);
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            try {
                                                int[] endLocation = new int[2];
                                                //获取终点的坐标
                                                userGridView.getChildAt(userGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                                MoveAnim(moveImageView1, startLocation, endLocation, channel, otherGridView);
                                                otherAdapter.setRemove(position);
                                            } catch (Exception localException) {
                                            }
                                        }
                                    }, 50L);

                                }
                            });
                }
                break;
            default:
                break;
        }
    }

    /**
     * 点击ITEM移动动画
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final ChannelItem moveChannel, final GridView clickGridView) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(startLocation[0], endLocation[0], startLocation[1], endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    otherAdapter.setVisible(true);
                    otherAdapter.notifyDataSetChanged();
                    userAdapter.remove();
                } else {
                    userAdapter.setVisible(true);
                    userAdapter.notifyDataSetChanged();
                    otherAdapter.remove();
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /**
     * 获取点击的Item的对应View，
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }

    @Override
    public void onBackPressed() {
//        saveChannel();
        if (userAdapter != null && userAdapter.isListChanged()) {
            setResult(LocationFragment.POST_CHANNELRESULT);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
