package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageCommentAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.mine.MineMessageBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MineMessageActivity extends BaseActivity {

    @BindView(R.id.tv_early)
    TextView mTvEarly;
    @BindView(R.id.rv_msg)
    XRecyclerView mRvMsg;

    public static boolean mIsHasMsg;
    private MineMessageCommentAdapter mAdapter;
    private int mSkip = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setTitleText("我的消息").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRvMsg.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MineMessageCommentAdapter(this, new ArrayList<MineMessageBean.DataBean>());
        mAdapter.setCallback(new MineMessageCommentAdapter.Callback() {
            @Override
            public void callback(int type, MineMessageBean.DataBean dataBean) {
                Intent intent;
                switch (type) {
                    case 1:
                        intent = new Intent(MineMessageActivity.this, MessageDetailActivity.class);
                        intent.putExtra("detail_uuid", dataBean.detail.uuid);
                        break;
                    case 2:
                        intent = new Intent(MineMessageActivity.this, NewsDetailActivity.class);
                        intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                        intent.putExtra("uuid", dataBean.detail.uuid);
                        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                        break;
                    default:
                        intent = new Intent(MineMessageActivity.this, NewsDetailActivity.class);
                        intent.putExtra("url", Constant.HOME_NEWS_DETAIL_URL);
                        intent.putExtra("uuid", dataBean.detail.uuid);
                        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                        intent.putExtra("id", dataBean.id);
                        intent.putExtra("title", dataBean.detail.title);
                        intent.putExtra("descrip", dataBean.detail.content);
                        ArrayList<String> list = new ArrayList<>();
                        list.add(dataBean.detail.images);
                        intent.putStringArrayListExtra("images", list);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void loadMore() {
                mRvMsg.setLoadingMoreEnabled(true);
                requestMineMessageData();
            }
        });
        mRvMsg.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mIsHasMsg = getIntent().getBooleanExtra("isHasMsg", false);
        if (mIsHasMsg) {
            mSkip = 0;
            requestMineMessageData();
            mRvMsg.setLoadingMoreEnabled(false);
        } else {
            mTvEarly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListeners() {
        mTvEarly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSkip = 1;
                mTvEarly.setVisibility(View.GONE);
                requestMineMessageData();
            }
        });
        mRvMsg.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRvMsg.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if (mIsHasMsg) return;
                requestMineMessageData();
            }
        });
    }

    /*请求我的消息*/
    private void requestMineMessageData() {
        OkGo.<CommonResponse<List<MineMessageBean.DataBean>>>get(Constant.MINE_NEWMESSAGE_LIST_URL)
                .params("limit", 12)
                .params("skip", mSkip)
                .execute(new JsonCallback<CommonResponse<List<MineMessageBean.DataBean>>>() {
                    @Override
                    public void onSuccess(Response<CommonResponse<List<MineMessageBean.DataBean>>> response) {
                        super.onSuccess(response);
                        if (response.body().code == 200) {
                            List<MineMessageBean.DataBean> data = response.body().data;
                            if (mIsHasMsg) {
                                mSkip = mSkip + data.size() + 1;
                            } else {
                                if (data == null || data.size() < 12)
                                    mRvMsg.setLoadingMoreEnabled(false);
                                else
                                    mSkip = mSkip + 12;
                            }
                            mAdapter.add(data);
                        } else {
                            mRvMsg.setLoadingMoreEnabled(false);
                        }
                        mRvMsg.loadMoreComplete();
                    }
                });
    }

}
