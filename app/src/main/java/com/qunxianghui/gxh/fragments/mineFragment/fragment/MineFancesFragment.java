package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFansAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineFansBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineFancesFragment extends BaseFragment {


    @BindView(R.id.recycler_mine_fances)
    XRecyclerView recyclerMineFances;
    Unbinder unbinder;
    private List<MineFansBean.DataBean> dataList = new ArrayList<>();
    private int count = 0;
    private boolean mIsFirst = true;
    private MyFansAdapter myFansAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_myfances;
    }


    @Override
    public void initDatas() {
        RequestMyFansData();


    }

    private void RequestMyFansData() {

        OkGo.<String>post(Constant.MYFANS_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseFocusData(response.body());
            }
        });
    }

    private void parseFocusData(String body) {

        final MineFansBean mineFansBean = GsonUtils.jsonFromJson(body, MineFansBean.class);
        dataList.addAll(mineFansBean.getData());
        count = dataList.size();
        if (mineFansBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myFansAdapter = new MyFansAdapter(mActivity, dataList);
                recyclerMineFances.setAdapter(myFansAdapter);

            }
            recyclerMineFances.refreshComplete();

            myFansAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mActivity, PersonDetailActivity.class);
                    intent.putExtra("member_id", dataList.get(position - 1).getMember_id());
                    startActivity(intent);
                }
            });

            myFansAdapter.notifyItemChanged(count,mineFansBean.getData().size());
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
    protected void onLoadData() {

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineFances.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=0;
                dataList.clear();
                RequestMyFansData();
            }

            @Override
            public void onLoadMore() {
                RequestMyFansData();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
