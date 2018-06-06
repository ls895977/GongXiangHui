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
import com.qunxianghui.gxh.adapter.mineAdapter.MyFocusAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineAttentionFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_attention)
    RecyclerView recyclerMineAttention;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_myattention;
    }


    @Override
    public void initDatas() {
        RequestAttentionData();


    }

    private void RequestAttentionData() {

        OkGo.<String>post(Constant.MYFOCUS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseFocusData(response.body());
            }
        });
    }

    private void parseFocusData(String body) {

        final MyFocusBean myFocusBean = GsonUtils.jsonFromJson(body, MyFocusBean.class);
        if (myFocusBean.getCode() == 0) {
            final List<MyFocusBean.DataBean> dataList = myFocusBean.getData();
            final MyFocusAdapter myFocusAdapter = new MyFocusAdapter(mActivity, dataList);
            recyclerMineAttention.setAdapter(myFocusAdapter);
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineAttention.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
