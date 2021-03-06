package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueLocalServiceAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;
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

public class PersonLocalServiceFragment extends BaseFragment implements Observer, MyIssueLocalServiceAdapter.Callback {

    @BindView(R.id.xrecycler_persondetail_post)
    XRecyclerView xrecyclerPersondetailPost;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.bt_myissue_localservice_delete)
    Button btMyissueLocalserviceDelete;
    @BindView(R.id.tv_myissue_empty_des)
    TextView mTvMyissueEmptyDes;

    private List<MineIssueLocalServiceBean.DataBean> mList = new ArrayList<>();
    private int mSkip = 0;
    private MyIssueLocalServiceAdapter mAdapter;
    private String data_id = "";
    private int mMemberId = 0;

    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;

    @Override
    protected void onLoadData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_persondetail_post;
    }

    @Override
    public void initData() {
        if (getActivity() instanceof PersonDetailActivity) {
            mTvMyissueEmptyDes.setText("他还没发布哦～");
            mMemberId = ((PersonDetailActivity) getActivity()).member_id;
            requestData();
        } else {
            mTvMyissueEmptyDes.setText("您还没发布哦～");
            OkGo.<MineIssueLocalServiceBean>post(Constant.MYISSURE_LOCAL_SERVICE_URL)
                    .params("limit", 10)
                    .params("skip", mSkip)
                    .execute(new JsonCallback<MineIssueLocalServiceBean>() {
                        @Override
                        public void onSuccess(Response<MineIssueLocalServiceBean> response) {
                            parseData(response.body());
                        }

                    });
        }
    }

    private void requestData() {
        OkGo.<MineIssueLocalServiceBean>post(Constant.MYISSURE_LOCAL_SERVICE_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .params("member_id", mMemberId)
                .execute(new JsonCallback<MineIssueLocalServiceBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueLocalServiceBean> response) {
                        parseData(response.body());
                    }

                    @Override
                    public void onError(Response<MineIssueLocalServiceBean> response) {
                        super.onError(response);
                        asyncShowToast(response.body().getMsg());
                    }
                });
    }

    private void parseData(MineIssueLocalServiceBean data) {
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
                    mAdapter = new MyIssueLocalServiceAdapter(getContext(), mList);
                    mAdapter.setCallback(this);
                    xrecyclerPersondetailPost.setAdapter(mAdapter);
//                    mAdapter.setCallback(new MyIssueLocalServiceAdapter.Callback() {
//                    @Override
//                    public void callback(int id) {
//
//                    }
//                });
                }
            }
            xrecyclerPersondetailPost.refreshComplete();
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(mSkip, data.getData().size());
        } else {
            if (mIsFirst)
                llEmpty.setVisibility(View.VISIBLE);
            xrecyclerPersondetailPost.setLoadingMoreEnabled(false);
        }
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        xrecyclerPersondetailPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        /**
         * 下拉刷新和上拉加载
         */
        xrecyclerPersondetailPost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                requestData();
            }

        });
        btMyissueLocalserviceDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteServiceData();
            }
        });

    }

    private void deleteServiceData() {
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
                .params("type", "2")
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        CommonBean body = response.body();
                        asyncShowToast(body.message);
                        if (body.code == 200) {
                            Iterator<MineIssueLocalServiceBean.DataBean> iterator = mList.iterator();
                            while (iterator.hasNext()) {
                                MineIssueLocalServiceBean.DataBean next = iterator.next();
                                if (next.isChecked()) {
                                    iterator.remove();
                                }
                            }
                        }
                        data_id = "";
                        mAdapter.isShow = false;
                        mAdapter.notifyDataSetChanged();
                        btMyissueLocalserviceDelete.setVisibility(View.GONE);
                        EventManager.getInstance().publishMessage("init");
                    }
                });
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "localser".equals(o)) {
            if (mList.size() > 0) {
                mAdapter.isShow = true;
                mAdapter.notifyDataSetChanged();
                btMyissueLocalserviceDelete.setVisibility(View.VISIBLE);
            }

        }
        if (o instanceof String && "localser_c".equals(o)) {
            if (mList.size() > 0) {
                mAdapter.isShow = false;
                mAdapter.notifyDataSetChanged();
                btMyissueLocalserviceDelete.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

    @Override
    public void callback(int id) {

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("localservice", mList.get(position));
        bundle.putInt("position",1);
        Intent intent = new Intent(getContext(), LocalServiceDetailActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
