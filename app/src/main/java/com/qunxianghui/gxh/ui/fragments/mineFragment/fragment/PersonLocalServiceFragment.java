package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

public class PersonLocalServiceFragment extends BaseFragment implements Observer {

    @BindView(R.id.xrecycler_persondetail_post)
    XRecyclerView xrecyclerPersondetailPost;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.bt_myissue_localservice_delete)
    Button btMyissueLocalserviceDelete;
    private List<MineIssueLocalServiceBean.DataBean> mList = new ArrayList<>();
    private int mSkip = 0;
    private MyIssueLocalServiceAdapter mAdapter;
    private String data_id = "";
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
            PersonDetailActivity personDetailActivity = (PersonDetailActivity) getActivity();
            OkGo.<MineIssueLocalServiceBean>post(Constant.MYISSURE_LOCAL_SERVICE_URL)
                    .params("limit", 10)
                    .params("skip", mSkip)
                    .params("member_id", personDetailActivity.member_id)
                    .execute(new JsonCallback<MineIssueLocalServiceBean>() {
                        @Override
                        public void onSuccess(Response<MineIssueLocalServiceBean> response) {
                            parseData(response.body());
                        }
                    });
        } else {
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

    private void parseData(MineIssueLocalServiceBean data) {
        xrecyclerPersondetailPost.refreshComplete();
        xrecyclerPersondetailPost.loadMoreComplete();
        if (data.getCode() == 200) {
            if (mSkip == 0) {
                mList.clear();
            }
            if (data.getData().size() == 0) {
                llEmpty.setVisibility(View.VISIBLE);
            }
            mList.addAll(data.getData());
        }
        if (mSkip == 0 && mList.isEmpty()) {
            llEmpty.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        xrecyclerPersondetailPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyIssueLocalServiceAdapter(getContext(), mList);
        mAdapter.setCallback(new MyIssueLocalServiceAdapter.Callback() {
            @Override
            public void callback(int id) {

            }
        });
        xrecyclerPersondetailPost.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {
        /**
         * 下拉刷新和上拉加载
         */
        xrecyclerPersondetailPost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //xrecyclerPersondetailPost.refreshComplete();
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                //xrecyclerPersondetailPost.refreshComplete();
                mSkip += 10;
                initData();
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
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btMyissueLocalserviceDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "localser_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btMyissueLocalserviceDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

}
