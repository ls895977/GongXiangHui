package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageSystemAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineMessageSystemBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineMessageSystemFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_message_system)
    XRecyclerView recyclerMineMessageSystem;
    Unbinder unbinder;

    private  int count=0;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private List<MineMessageSystemBean.DataBean> dataList=new ArrayList<>();
    private MineMessageSystemAdapter mineMessageSystemAdapter;


    @Override
    protected void onLoadData() {
        /*
        请求我的系统消息
         */
        RequestMymessageSystem();


    }

    private void RequestMymessageSystem() {
        OkGo.<String>post(Constant.DISCUSS_MINE_SSYSTEM_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parsePaiHangData(response.body());


                    }
                });
    }

    private void parsePaiHangData(String body) {

        final MineMessageSystemBean mineMessageSystemBean = GsonUtils.jsonFromJson(body, MineMessageSystemBean.class);

    if (mIsRefresh){
        mIsRefresh=false;
        dataList.clear();
    }
    dataList.addAll(mineMessageSystemBean.getData());
    count=dataList.size();

        if (mineMessageSystemBean.getCode()==0){
            if (mIsFirst){
                mIsFirst=false;
                mineMessageSystemAdapter = new MineMessageSystemAdapter(mActivity, dataList);
                recyclerMineMessageSystem.setAdapter(mineMessageSystemAdapter);
            }
            recyclerMineMessageSystem.refreshComplete();
            mineMessageSystemAdapter.notifyItemChanged(count,mineMessageSystemBean.getData().size());

            }



    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_message_system;
    }


    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineMessageSystem.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=0;
                mIsRefresh=true;
                RequestMymessageSystem();

            }

            @Override
            public void onLoadMore() {
                RequestMymessageSystem();

            }
        });
    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        recyclerMineMessageSystem.setLayoutManager(linearLayoutManager);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
