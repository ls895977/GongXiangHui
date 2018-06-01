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
import com.qunxianghui.gxh.adapter.mineAdapter.MyFansAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineFansBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineFancesFragment extends BaseFragment {


    @BindView(R.id.recycler_mine_fances)
    RecyclerView recyclerMineFances;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_myfances;
    }


    @Override
    public void initDatas() {
        RequestAttentionData();


    }

    private void RequestAttentionData() {

        OkGo.<String>post(Constant.MYFANS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseFocusData(response.body());
            }
        });
    }

    private void parseFocusData(String body) {

        final MineFansBean mineFansBean = GsonUtils.jsonFromJson(body, MineFansBean.class);
        if (mineFansBean.getCode() == 0) {
            final List<MineFansBean.DataBean> dataList = mineFansBean.getData();
            final MyFansAdapter myFansAdapter = new MyFansAdapter(mActivity, dataList);
            recyclerMineFances.setAdapter(myFansAdapter);

        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineFances.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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
