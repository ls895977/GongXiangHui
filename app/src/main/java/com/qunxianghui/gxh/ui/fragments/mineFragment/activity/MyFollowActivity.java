package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFocusAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFollowActivity extends BaseActivity implements MyFocusAdapter.myFocusItemClickListener {
    @BindView(R.id.recycler_mine_attention)
    XRecyclerView mRecyclerMineAttention;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private List<MyFocusBean.DataBean> dataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private int count = 0;
    private MyFocusAdapter myFocusAdapter;
    private boolean mIsRefresh = false;
    private Dialog mFollowDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfollow;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerMineAttention.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        super.initData();
        RequestAttentionData();
        new TitleBuilder(this).setTitleText("我的关注").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRecyclerMineAttention.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                RequestAttentionData();
            }

            @Override
            public void onLoadMore() {
                RequestAttentionData();
            }
        });
    }

    private void RequestAttentionData() {
        OkGo.<MyFocusBean>post(Constant.MYFOCUS_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new JsonCallback<MyFocusBean>() {
                    @Override
                    public void onSuccess(Response<MyFocusBean> response) {
                        parseFocusData(response.body());
                    }
                });
    }

    private void parseFocusData(MyFocusBean myFocusBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myFocusBean.getData());
        count = dataList.size();
        if (myFocusBean.getCode() == 200) {
            if (mIsFirst) {
                mIsFirst = false;
                myFocusAdapter = new MyFocusAdapter(mContext, dataList);
                myFocusAdapter.setMyFocusItemClickListener(this);
                mRecyclerMineAttention.setAdapter(myFocusAdapter);
            }

            if (dataList.isEmpty()) {
                llEmpty.setVisibility(View.VISIBLE);
            } else {
                llEmpty.setVisibility(View.GONE);
            }
        }
        mRecyclerMineAttention.refreshComplete();
        myFocusAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(mContext, PersonDetailActivity.class);
                intent.putExtra("member_id", dataList.get(position - 1).getBe_member_id());
                startActivity(intent);

            }
        });
        myFocusAdapter.notifyItemChanged(count, myFocusBean.getData().size());
    }

    /*关注的点击*/
    @Override
    public void FocusClick(final int position) {

        showBottomDialog(position);

    }

    /*弹出底部*/
    private void showBottomDialog(final int position) {
        if (mFollowDialog == null) {
            mFollowDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
            View alertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_alertdialog, null);
            TextView mFollowSuremessage = alertView.findViewById(R.id.tv_addAdver_share);
            mFollowSuremessage.setText("确定不再关注此人?");
            mFollowSuremessage.setTextSize(12);
            mFollowSuremessage.setTextColor(Color.GRAY);
            TextView mFollowsure = alertView.findViewById(R.id.tv_article_share);
            mFollowsure.setText("确定");
            mFollowsure.setTextColor(Color.BLACK);
            mFollowsure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int be_member_id = dataList.get(position).getBe_member_id();
                    OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                            .params("be_member_id", be_member_id)
                            .execute(new JsonCallback<CommonBean>() {
                                @Override
                                public void onSuccess(Response<CommonBean> response) {
                                    int code = response.body().code;
                                    if (code == 202) {
                                        asyncShowToast(response.body().message);
                                        dataList.get(position).setFollow_type(0);
                                        dataList.remove(position);
                                        myFocusAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                    mFollowDialog.dismiss();
                }
            });
            TextView mFollowCancel = alertView.findViewById(R.id.tv_bottom_alertdialog_cancle);
            mFollowCancel.setText("取消");
            mFollowCancel.setTextColor(Color.BLACK);
            mFollowCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFollowDialog.dismiss();
                }
            });

            mFollowDialog.setContentView(alertView);
            Window dialogWindow = mFollowDialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            dialogWindow.setAttributes(lp);
        }
        mFollowDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

