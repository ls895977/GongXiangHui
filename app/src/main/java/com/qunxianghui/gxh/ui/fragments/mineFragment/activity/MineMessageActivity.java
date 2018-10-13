package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageCommentAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.mine.MineMessageBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.LogUtil;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MineMessageActivity extends BaseActivity {
    @BindView(R.id.recycler_minemessage)
    RecyclerView recyclerMinemessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        super.initViews();
        new TitleBuilder(this).setTitleText("我的消息").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerMinemessage.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void initData() {
        RequestMineMessageData();
    }

    /*请求我的消息*/
    private void RequestMineMessageData() {
        OkGo.<CommonResponse<List<MineMessageBean.DataBean>>>post(Constant.MINE_NEWMESSAGE_LIST_URL)
                .params("limit", 12)
                .params("skip", 0)
                .execute(new JsonCallback<CommonResponse<List<MineMessageBean.DataBean>>>() {
                    @Override
                    public void onSuccess(Response<CommonResponse<List<MineMessageBean.DataBean>>> response) {
                        super.onSuccess(response);
                        if (response.body().code ==200) {
                            setData(response);
                        }
                    }
                });
    }
    private void setData(Response<CommonResponse<List<MineMessageBean.DataBean>>> response) {
        LogUtil.printJson("输出消息列表", String.valueOf(response.body().data),"消息");
        List<MineMessageBean.DataBean> messageData = response.body().data;
        MineMessageCommentAdapter mineMessageCommentAdapter = new MineMessageCommentAdapter(messageData);
        recyclerMinemessage.setAdapter(mineMessageCommentAdapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
