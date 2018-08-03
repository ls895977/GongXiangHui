package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFocusAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFollowActivity extends BaseActivity {
    @BindView(R.id.recycler_mine_attention)
    XRecyclerView mRecyclerMineAttention;

    private List<MyFocusBean.DataBean> dataList = new ArrayList<>();

    private boolean mIsFirst = true;
    private int count = 0;
    private MyFocusAdapter myFocusAdapter;
    private boolean mIsRefresh = false;

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
        OkGo.<String>post(Constant.MYFOCUS_URL)
                .params("limit", 10)
                .params("skip", count).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseFocusData(response.body());
                    }
                });
    }

    private void parseFocusData(String body) {
        final MyFocusBean myFocusBean = GsonUtils.jsonFromJson(body, MyFocusBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myFocusBean.getData());
        count = dataList.size();
        if (myFocusBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myFocusAdapter = new MyFocusAdapter(mContext, dataList);
                mRecyclerMineAttention.setAdapter(myFocusAdapter);
            }
            mRecyclerMineAttention.refreshComplete();
            myFocusAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View v, int position) {
                    asyncShowToast("点击了" + position);
                    Intent intent = new Intent(mContext, PersonDetailActivity.class);
                    intent.putExtra("member_id", dataList.get(position - 1).getBe_member_id());

                    startActivity(intent);

                }
            });
            myFocusAdapter.notifyItemChanged(count, myFocusBean.getData().size());


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
