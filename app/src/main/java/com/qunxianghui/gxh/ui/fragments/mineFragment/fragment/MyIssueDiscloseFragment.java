package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueDiscloseAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssueDiscloseFragment extends BaseFragment implements Observer {

    @BindView(R.id.recycler_mineissue_disclose)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_delete)
    Button btMyissueDelete;
    Unbinder unbinder;
    private int mSkip = 0;
    private List<MyIssueDiscloseBean.DataBean> mList = new ArrayList<>();
    private MineIssueDiscloseAdapter mAdapter;
    private String data_id = "";
    private PersonDetailActivity personDetailActivity;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_issue_disclose;
    }

    @Override
    public void initViews(View view) {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MineIssueDiscloseAdapter(getContext(), mList);
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
        personDetailActivity = (PersonDetailActivity) getActivity();
        OkGo.<MyIssueDiscloseBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .params("member_id", personDetailActivity.member_id)
                .execute(new JsonCallback<MyIssueDiscloseBean>() {
                    @Override
                    public void onSuccess(Response<MyIssueDiscloseBean> response) {
                        parseData(response.body());
                    }
                });
    }

    @Override
    protected void initListeners() {
        btMyissueDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDiscloseData();
            }
        });
    }

    /*多条删除*/
    private void deleteDiscloseData() {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked() == true) {
                //这边获取选中的数据id
                if (data_id.equals("")) {
                    //这边获取选中的数据id
                    data_id = data_id + mList.get(i).getId();
                } else {
                    data_id = data_id + "," + mList.get(i).getId();
                }
                RequestDeleteData();

            }
        }
    }

    private void RequestDeleteData() {
        OkGo.<String>post(Constant.CANCEL_COLLECT_URL)
                .params("uuid", data_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                ToastUtils.showLong("删除成功");
                                for (int j = 0; j < mList.size(); j++) {
                                    if (mList.get(j).isChecked() == true) {
                                        mList.remove(j);
                                    }
                                }
                                mAdapter.isShow = false;
                                mAdapter.notifyDataSetChanged();
                                btMyissueDelete.setVisibility(View.GONE);
                                EventManager.getInstance().publishMessage("init");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void parseData(MyIssueDiscloseBean data) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "news".equals(o)) {
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btMyissueDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "news_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btMyissueDelete.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
