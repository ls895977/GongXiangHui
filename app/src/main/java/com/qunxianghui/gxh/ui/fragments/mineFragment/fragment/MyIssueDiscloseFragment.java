package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueDiscloseAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.activity.BaoliaoDetailActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * 爆料
 */
public class MyIssueDiscloseFragment extends BaseFragment implements Observer {

    @BindView(R.id.recycler_mineissue_disclose)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_delete)
    Button btMyissueDelete;

    private int mSkip = 0;
    private List<BaoliaoBean.DataBean> mList = new ArrayList<>();
    private MineIssueDiscloseAdapter mAdapter;
    private String data_id = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issue_disclose;
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MineIssueDiscloseAdapter(getContext(), mList);
        mAdapter.setCallback(new MineIssueDiscloseAdapter.Callback() {
            @Override
            public void callback(BaoliaoBean.DataBean dataBean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("baoliao", dataBean);
                toActivity(BaoliaoDetailActivity.class, bundle);
            }
        });
        mRv.setAdapter(mAdapter);
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                initData();
            }
        });
    }

    @Override
    public void initData() {
        OkGo.<BaoliaoBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<BaoliaoBean>() {
                    @Override
                    public void onSuccess(Response<BaoliaoBean> response) {
                        parseData(response.body());
                    }
                });
    }


    @Override
    protected void initListeners() {
        btMyissueDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        .params("type", "1")
                        .execute(new JsonCallback<CommonBean>() {
                            @Override
                            public void onSuccess(Response<CommonBean> response) {
                                CommonBean body = response.body();
                                asyncShowToast(body.message);
                                if (body.code == 200) {
                                    Iterator<BaoliaoBean.DataBean> iterator = mList.iterator();
                                    while (iterator.hasNext()) {
                                        BaoliaoBean.DataBean next = iterator.next();
                                        if (next.isChecked()) {
                                            iterator.remove();
                                        }
                                    }
                                }
                                data_id = "";
                                mAdapter.isShow = false;
                                mAdapter.notifyDataSetChanged();
                                btMyissueDelete.setVisibility(View.GONE);
                                EventManager.getInstance().publishMessage("init");
                            }
                        });
            }
        });
    }

    private void parseData(BaoliaoBean data) {
        if (data.getCode() == 0) {
            if (mSkip == 0) {
                mList.clear();
                mRv.setLoadingMoreEnabled(true);
            }
            if (data.getData().size() < 10) {
                mRv.setLoadingMoreEnabled(false);
            }
            mList.addAll(data.getData());
            mRv.refreshComplete();
        } else {
            mRv.setLoadingMoreEnabled(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "baoliao".equals(o)) {
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btMyissueDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "baoliao_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btMyissueDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

}
