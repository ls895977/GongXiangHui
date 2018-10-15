package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MineMessageActivity extends BaseActivity {

    @BindView(R.id.rv_msg)
    XRecyclerView mRvMsg;

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
        mRvMsg.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        requestMineMessageData();
    }

    @Override
    protected void initListeners() {
        mRvMsg.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRvMsg.setLoadingMoreEnabled(true);
                mRvMsg.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mSkip += 12;
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
                            if (data == null || data.size() < 12)
                                mRvMsg.setLoadingMoreEnabled(false);
                            mAdapter.add(data);
                        } else {
                            mRvMsg.setLoadingMoreEnabled(false);
                        }
                        mRvMsg.loadMoreComplete();
                    }
                });
    }

}
