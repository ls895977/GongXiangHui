package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

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
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.OtherAdapter;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.widget.DragGrid;
import com.qunxianghui.gxh.widget.OtherGridView;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：对tab进行添加删除排序操作
 * ScrollView嵌套两个GridView
 * <p>
 * Created by Mjj on 2016/11/18.
 */

public class ChannelActivity extends GestureDetectorActivity implements AdapterView.OnItemClickListener {

    public static final String USER_CHANNEL = "user_channel";//订阅列表
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

    /**
     * 是否在移动，由于是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。
     */
    boolean isMove = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_channel;
    }

    /**
     * 初始化数据
     */
    protected void initData() {
        new TitleBuilder(ChannelActivity.this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAdapter.isListChanged()) {
//                    setResult(HomeFragment.CHANNELRESULT);
                    finish();
                } else {
                    finish();
                }
            }
        }).setTitleText("频道管理");
        ArrayList<ChannelItem> userChannelListData = (ArrayList<ChannelItem>) getIntent().getSerializableExtra(USER_CHANNEL);
        if (userChannelListData != null) {
            userChannelList = userChannelListData;
            userAdapter = new DragAdapter(this, userChannelListData);
            userGridView.setAdapter(userAdapter);
        }
        //获取全部频道
        OkGo.<ChannelGetallBean>post(Constant.CHANNEL_GETALL)
                .execute(new JsonCallback<ChannelGetallBean>() {
                    @Override
                    public void onSuccess(Response<ChannelGetallBean> response) {
                        getAllData(response.body());
                    }

                });
        otherGridView.setOnItemClickListener(this);
        userGridView.setOnItemClickListener(this);
    }

    private void getAllData(ChannelGetallBean bean) {
        if (null != bean) {
            List<ChannelGetallBean.DataBean> datas = bean.getData();
            for (int i = 0; i < datas.size(); i++) {
                ChannelGetallBean.DataBean dataBean = datas.get(i);
                ChannelItem item = new ChannelItem(dataBean.getChannel_id(), dataBean.getChannel_name(), i, 1);
                otherChannelList.add(item);
                for (ChannelItem channelItem : userChannelList) {
                    if (channelItem.channelName.equals(item.channelName)) {
                        otherChannelList.remove(item);
                        break;
                    }
                }
            }
            otherAdapter = new OtherAdapter(this, otherChannelList);
            otherGridView.setAdapter(otherAdapter);
        }
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initViews() {
        userGridView = findViewById(R.id.userGridView);
        otherGridView = findViewById(R.id.otherGridView);
    }

    /**
     * GRIDVIEW对应的ITEM点击监听接口
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        //如果点击的时候，之前动画还没结束，那么就让点击事件无效
        if (isMove) {
            return;
        }
        isMove = true;
        switch (parent.getId()) {
            case R.id.userGridView:
                //position为 0 的不进行任何操作
                if (position == 0 || position == 1) return;
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    TextView newTextView = view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                    otherAdapter.setVisible(false);
                    //频道列表（用户订阅的频道）
                    OkGo.<String>post(Constant.CHANNEL_DELETE_CHANNEL)
                            .params("channel_id", channel.id)
                            .execute(new JsonCallback<String>() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    //添加到最后一个
                                    otherAdapter.addItem(channel);
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            int[] endLocation = new int[2];
                                            //获取终点的坐标
                                            otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                            MoveAnim(moveImageView, startLocation, endLocation, channel, userGridView);
                                            userAdapter.setRemove(position);
                                        }
                                    }, 50L);
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    isMove = false;
                                }
                            });
                }
                break;
            case R.id.otherGridView:
                // 其它GridView
                final ImageView moveImageView1 = getView(view);
                if (moveImageView1 != null) {
                    TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
                    userAdapter.setVisible(false);
                    //频道列表（用户订阅的频道）
                    OkGo.<String>post(Constant.CHANNEL_ADD_CHANNEL)
                            .params("channel_id", channel.id)
                            .execute(new JsonCallback<String>() {
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

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    isMove = false;
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

    /**
     * 退出时候保存选择后数据库的设置
     */
    private void saveChannel() {
//        ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).deleteAllChannel();
//        ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).saveUserChannel(userAdapter.getChannnelLst());
//        ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).saveOtherChannel(otherAdapter.getChannnelLst());
    }

    @Override
    public void onBackPressed() {
//        saveChannel();
        if (userAdapter.isListChanged()) {
//            setResult(HomeFragment.CHANNELRESULT);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
