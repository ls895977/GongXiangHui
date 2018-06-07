package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.bean.mine.MineMessageCommentBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/14 0014.
 */


public class MineMessageFragment extends BaseFragment {


    @BindView(R.id.xrecycler_mineMessage)
    XRecyclerView xrecyclerMineMessage;


    @Override
    protected void onLoadData() {
        OkGo.<String>post(Constant.DISCUSS_MINE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                parsePaiHangData(response.body());


            }
        });

    }

    private void parsePaiHangData(String body) {
        final MineMessageCommentBean mineMessageCommentBean = GsonUtils.jsonFromJson(body, MineMessageCommentBean.class);
        if (mineMessageCommentBean.getCode() == 0) {
            final List<MineMessageCommentBean.DataBean> dataList = mineMessageCommentBean.getData();
            final MineMessageAdapter mineMessageAdapter = new MineMessageAdapter(mActivity, dataList);
            mineMessageAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(mActivity, "点击了:" + position, Toast.LENGTH_SHORT).show();
                }
            });

            xrecyclerMineMessage.setAdapter(mineMessageAdapter);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minemessage;
    }

    @Override
    public void initDatas() {


    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineMessage.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initListeners() {
        xrecyclerMineMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerMineMessage.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerMineMessage.refreshComplete();
            }
        });
    }


}
