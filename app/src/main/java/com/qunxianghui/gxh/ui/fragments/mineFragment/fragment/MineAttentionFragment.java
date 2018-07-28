package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
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
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFocusAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineAttentionFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_attention)
    XRecyclerView recyclerMineAttention;
    Unbinder unbinder;
    private List<MyFocusBean.DataBean> dataList = new ArrayList<>();

    private boolean mIsFirst = true;
    private int count = 0;
    private MyFocusAdapter myFocusAdapter;
    private boolean mIsRefresh = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_myattention;
    }


    @Override
    public void initData() {
        RequestAttentionData();
    }

    private void RequestAttentionData() {

        OkGo.<String>post(Constant.MYFOCUS_URL)
                .params("limit", 10)
                .params("skip", count).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseFocusData(response.body());
                    }
                });
    }

    private void parseFocusData(String body) {
        final MyFocusBean myFocusBean = GsonUtils.jsonFromJson(body, MyFocusBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myFocusBean.getData());
        count = dataList.size();
        if (myFocusBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myFocusAdapter = new MyFocusAdapter(mActivity, dataList);
                recyclerMineAttention.setAdapter(myFocusAdapter);
            }
            recyclerMineAttention.refreshComplete();
            myFocusAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View v, int position) {
                    asyncShowToast("点击了" + position);
                    Intent intent = new Intent(mActivity, PersonDetailActivity.class);
                    intent.putExtra("member_id", dataList.get(position - 1).getBe_member_id());

                    startActivity(intent);

                }
            });
            myFocusAdapter.notifyItemChanged(count, myFocusBean.getData().size());


        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineAttention.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineAttention.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                RequestAttentionData();
            }

            @Override
            public void onLoadMore() {
                RequestAttentionData();
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
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}