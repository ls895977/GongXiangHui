package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageSystemAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineMessageSystemBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineMessageSystemFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_message_system)
    RecyclerView recyclerMineMessageSystem;
    Unbinder unbinder;

    @Override
    protected void onLoadData() {
        OkGo.<String>post(Constant.DISCUSS_MINE_SSYSTEM_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                parsePaiHangData(response.body());


            }
        });

    }

    private void parsePaiHangData(String body) {

        final MineMessageSystemBean mineMessageSystemBean = GsonUtils.jsonFromJson(body, MineMessageSystemBean.class);
        if (mineMessageSystemBean.getCode()==0){
            final List<MineMessageSystemBean.DataBean> dataList = mineMessageSystemBean.getData();
            final MineMessageSystemAdapter mineMessageSystemAdapter = new MineMessageSystemAdapter(mActivity, dataList);
            recyclerMineMessageSystem.setAdapter(mineMessageSystemAdapter);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_message_system;
    }

    @Override
    public void initDatas() {

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
