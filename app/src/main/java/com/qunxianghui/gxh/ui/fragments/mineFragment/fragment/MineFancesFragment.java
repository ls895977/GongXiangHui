package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFansAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineFansBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class MineFancesFragment extends BaseFragment {


    @BindView(R.id.recycler_mine_fances)
    XRecyclerView recyclerMineFances;
    Unbinder unbinder;
    private List<MineFansBean.DataBean> dataList = new ArrayList<>();
    private int count = 0;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private MyFansAdapter myFansAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_myfances;
    }


    @Override
    public void initData() {
        OkGo.<MineFansBean>post(Constant.MYFANS_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new JsonCallback<MineFansBean>() {
                    @Override
                    public void onSuccess(Response<MineFansBean> response) {
                        parseFocusData(response.body());
                    }
                });
    }

    private void parseFocusData(MineFansBean mineFansBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
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

            myFansAdapter.notifyItemChanged(count, mineFansBean.getData().size());
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineFances.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineFances.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
    }

}
