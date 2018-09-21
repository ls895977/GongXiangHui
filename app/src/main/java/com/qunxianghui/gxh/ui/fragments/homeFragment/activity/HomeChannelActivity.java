package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.ChannelAdapter;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.interfaces.OnChannelListener;
import com.qunxianghui.gxh.listener.ItemDragHelperCallBack;
import com.qunxianghui.gxh.observer.NewChannelEvent;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeChannelActivity extends AppCompatActivity implements OnChannelListener {

    RecyclerView mRecyclerView;

    private ArrayList<ChannelItem> mData = new ArrayList<>();
    private ArrayList<ChannelItem> mSelectedData;
    private ArrayList<ChannelItem> mUnSelectedData = new ArrayList<>();
    private ChannelAdapter mAdapter;
    private String firstAddChannelName = "";
    //是否需要更新 主页频道信息
    private boolean isUpdate = false;
    private OnChannelListener onChannelListener;

    void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.MIUISetStatusBarLightMode(this, true);
        StatusBarUtil.FlymeSetStatusBarLightMode(this, true);
        setContentView(R.layout.dialog_channel);
        findViewById(R.id.icon_collapse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdate) {
                    NewChannelEvent event = new NewChannelEvent(mAdapter.getData());
                    Hawk.put("USER_CHANNEL", event.mList);
                    EventBus.getDefault().post(event);
                }
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);
        OkGo.<ChannelGetallBean>post(Constant.CHANNEL_GETALL)
                .execute(new JsonCallback<ChannelGetallBean>() {
                    @Override
                    public void onSuccess(Response<ChannelGetallBean> response) {
                        getAllData(response.body());
                    }
                });
    }

    private void getAllData(ChannelGetallBean bean) {
        if (bean != null && bean.getCode() == 0) {
            mSelectedData = (ArrayList<ChannelItem>) getIntent().getSerializableExtra("dataSelected");
            List<ChannelGetallBean.DataBean> datas = bean.getData();
            for (int i = 0; i < datas.size(); i++) {
                ChannelGetallBean.DataBean dataBean = datas.get(i);
                ChannelItem item = new ChannelItem(dataBean.getChannel_id(), dataBean.getChannel_name(), i, 1);
                mUnSelectedData.add(item);
                for (ChannelItem channelItem : mSelectedData) {
                    if (channelItem.channelName.equals(item.channelName)) {
                        mUnSelectedData.remove(item);
                        break;
                    }
                }
            }
            processLogic();
        } else if (bean != null && bean.getCode() == 1000){
            SPUtils.removePreference(SpConstant.ACCESS_TOKEN);
            OkGo.getInstance().getCommonHeaders().remove("X-accesstoken");
            OkGo.<ChannelGetallBean>post(Constant.CHANNEL_GETALL)
                    .execute(new JsonCallback<ChannelGetallBean>() {
                        @Override
                        public void onSuccess(Response<ChannelGetallBean> response) {
                            getAllData(response.body());
                        }
                    });
        }
    }

    private void processLogic() {
        ChannelItem channel = new ChannelItem();
        channel.viewType = ChannelItem.TYPE_MY;
        channel.channelName = "我的频道";
        mData.add(channel);
        setDataType(mSelectedData, ChannelItem.TYPE_MY_CHANNEL);
        mData.addAll(mSelectedData);

        ChannelItem moreChannel = new ChannelItem();
        moreChannel.viewType = ChannelItem.TYPE_OTHER;
        moreChannel.channelName = "频道推荐";
        mData.add(moreChannel);
        setDataType(mUnSelectedData, ChannelItem.TYPE_OTHER_CHANNEL);
        mData.addAll(mUnSelectedData);

        ItemDragHelperCallBack callback = new ItemDragHelperCallBack(this);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
        mAdapter = new ChannelAdapter(mData, helper);
        final GridLayoutManager manager = new GridLayoutManager(HomeChannelActivity.this, 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                if (itemViewType == ChannelItem.TYPE_MY_CHANNEL || itemViewType == ChannelItem.TYPE_OTHER_CHANNEL)
                    return 1;
                else
                    return 4;
            }
        });
        mAdapter.onChannelListener(this);
    }


    private void setDataType(List<ChannelItem> data, int type) {
        for (ChannelItem datum : data) {
            datum.viewType = type;
        }
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        if (starPos < 0 || endPos < 0) return;
        if (mData.get(endPos).channelName.equals("热门") || mData.get(endPos).channelName.equals("本地"))
            return;
        if (onChannelListener != null)
            onChannelListener.onItemMove(starPos - 1, endPos - 1);
        onMove(starPos, endPos, false);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        onMove(starPos, endPos, false);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        onMove(starPos, endPos, true);
    }

    @Override
    public void onFinish(String selectedChannelName) {
//        EventBus.getDefault().post(SelectChannelEvent(selectedChannelName));
//        dismiss();
    }

    private void onMove(int starPos, int endPos, boolean isAdd) {
        isUpdate = true;
        ChannelItem startChannel = mData.get(starPos);
        //先删除之前的位置
        mData.remove(starPos);
        //添加到现在的位置
        mData.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
        if (isAdd) {
            if (TextUtils.isEmpty(firstAddChannelName)) {
                firstAddChannelName = startChannel.channelName;
            }
        } else {
            if (startChannel.channelName.equals(firstAddChannelName)) {
                firstAddChannelName = "";
            }
        }
    }


}
