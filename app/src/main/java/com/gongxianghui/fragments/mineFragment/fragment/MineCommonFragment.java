package com.gongxianghui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.adapter.mineAdapter.MinCollectAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.bean.mine.CollectBean;
import com.gongxianghui.config.Constant;
import com.gongxianghui.utils.GsonUtil;
import com.gongxianghui.utils.OkHttpUtil;
import com.gongxianghui.widget.GloriousRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_common)
    GloriousRecyclerView recyclerMineCommon;
    Unbinder unbinder;

    private List<CollectBean.ListBean> data;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initDatas() {


    }

    @Override
    public void initViews(View view) {
        recyclerMineCommon.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        String url = OkHttpUtil.obtainGetUrl(Constant.URL);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                CollectBean collectBean = GsonUtil.parseJsonWithGson(body, CollectBean.class);
                data = collectBean.getList();
                MinCollectAdapter minCollectAdapter = new MinCollectAdapter(data, mActivity);
                recyclerMineCommon.setAdapter(minCollectAdapter);

            }


            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });


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
