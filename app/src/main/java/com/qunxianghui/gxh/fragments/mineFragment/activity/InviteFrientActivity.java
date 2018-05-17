package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.TestRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {
    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.invite_recycler_view)
    RecyclerView inviteRecyclerView;
    private List<HomeNewListBean.DataBean> data;
    private TestRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {
        inviteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        inviteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //首页新闻数据
        OkGo.<String>get(Constant.HOME_NEWS_LIST_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });


    }

    private void parseData(String body) {
        final HomeNewListBean homeNewListBean = GsonUtil.parseJsonWithGson(body, HomeNewListBean.class);
        data = homeNewListBean.getData();
        if (adapter==null){
            adapter = new TestRecyclerAdapter(data, mContext);
            inviteRecyclerView.setAdapter(adapter);
            adapter.setOnMyItemClickListener(new TestRecyclerAdapter.OnMyItemClickListener() {
                @Override
                public void myClick(View v, int pos) {
                    Toast.makeText(mContext, "onClick" + pos, Toast.LENGTH_SHORT).show();
                    System.out.println("onClick---" + pos);
                    adapter.addItem(pos);
                }

                @Override
                public void mLongClick(View v, int pos) {
                    Toast.makeText(mContext, "onLongClick" + pos, Toast.LENGTH_SHORT).show();
                    System.out.println("onClick---" + pos);
                    adapter.removeData(pos);

                }
            });
        }

    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 30; i++) {
            mDatas.add("条目---" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



