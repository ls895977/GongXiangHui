package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueGoodSelectAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MyIssueGoodSelectBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LocalServiceDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 精选
 */
public class MyIssueGoodSelectFragment extends BaseFragment implements Observer, MyIssueGoodSelectAdapter.Callback {

    @BindView(R.id.xrecler_myissue_goodselect)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_goodselect_delete)
    Button btnDelete;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.tv_myissue_goodselect_empty_des)
    TextView mTvMyissueGoodselectEmptyDes;
    Unbinder unbinder;

    private int mSkip = 0;
    private List<MyIssueGoodSelectBean.DataBean> mList = new ArrayList<>();
    private MyIssueGoodSelectAdapter mAdapter;
    private String data_id = "";
    private int member_id = -1;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_myissue_goodselect;
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL, false));

        if (getContext() instanceof PersonDetailActivity) {
            member_id = ((PersonDetailActivity) getContext()).member_id;
        }
    }

    /*多条删除*/
    private void deleteGoodSelectData() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                if (TextUtils.isEmpty(data_id)) {
                    data_id = data_id + mList.get(i).getId();
                } else {
                    data_id = data_id + "," + mList.get(i).getId();
                }
            }
        }
        if (TextUtils.isEmpty(data_id)) return;
        OkGo.<CommonBean>post(Constant.CANCEL_ISSUE_URL)
                .params("id", data_id)
                .params("type", "3")
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        CommonBean body = response.body();
                        asyncShowToast(body.message);
                        if (body.code == 200) {
                            Iterator<MyIssueGoodSelectBean.DataBean> iterator = mList.iterator();
                            while (iterator.hasNext()) {
                                MyIssueGoodSelectBean.DataBean next = iterator.next();
                                if (next.isChecked()) {
                                    iterator.remove();
                                }
                            }
                        }
                        data_id = "";
                        mAdapter.isShow = false;
                        mAdapter.notifyDataSetChanged();
                        btnDelete.setVisibility(View.GONE);
                        EventManager.getInstance().publishMessage("init");
                    }
                });
    }


    @Override
    public void initData() {
        if (member_id == -1) {
            mTvMyissueGoodselectEmptyDes.setText("您还没发布哦～");
            OkGo.<MyIssueGoodSelectBean>post(Constant.MYISSURE_GOOD_SELECT_URL)
                    .params("limit", 10)
                    .params("skip", mSkip)
                    .execute(new JsonCallback<MyIssueGoodSelectBean>() {
                        @Override
                        public void onSuccess(Response<MyIssueGoodSelectBean> response) {
                            parseData(response.body());
                        }
                    });
        } else {
            mTvMyissueGoodselectEmptyDes.setText("他还没发布哦～");
            OkGo.<MyIssueGoodSelectBean>post(Constant.MYISSURE_GOOD_SELECT_URL)
                    .params("limit", 10)
                    .params("skip", mSkip)
                    .params("member_id", member_id)
                    .execute(new JsonCallback<MyIssueGoodSelectBean>() {
                        @Override
                        public void onSuccess(Response<MyIssueGoodSelectBean> response) {
                            parseData(response.body());
                        }
                    });
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "goodselect".equals(o)) {
            if (mList.size() > 0) {
                mAdapter.isShow = true;
                mAdapter.notifyDataSetChanged();
                btnDelete.setVisibility(View.VISIBLE);
            }

        }
        if (o instanceof String && "goodselect_c".equals(o)) {
            if (mList.size() > 0) {
                mAdapter.isShow = false;
                mAdapter.notifyDataSetChanged();
                btnDelete.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

    private void parseData(MyIssueGoodSelectBean data) {
        if (mIsRefresh) {
            mIsRefresh = false;
            mList.clear();
        }
        if (data.getData() != null) {

            mList.addAll(data.getData());
            mSkip = mList.size();
            if (data.getCode() == 200) {

                if (mIsFirst) {
                    mIsFirst = false;
                    mAdapter = new MyIssueGoodSelectAdapter(getContext(), mList);
                    mAdapter.setCallback(this);
                    mRv.setAdapter(mAdapter);
                }
            }
            mRv.refreshComplete();
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(mSkip, data.getData().size());
        } else {
            if (mIsFirst)
                llEmpty.setVisibility(View.VISIBLE);
            mRv.setLoadingMoreEnabled(false);
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGoodSelectData();
            }
        });
    }

    @Override
    public void callback(int id) {

    }

    @Override
    public void onItemClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("goodselect", mList.get(position));
        bundle.putInt("position",2);
        Intent intent = new Intent(getContext(), LocalServiceDetailActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);

    }
}

