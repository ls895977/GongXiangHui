package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueGoodSelectAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueGoodSelectBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssueGoodSelectFragment extends BaseFragment {
    @BindView(R.id.xrecler_myissue_goodselect)
    XRecyclerView mXreclerMyissueGoodselect;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_myissue_goodselect;
    }

    @Override
    public void initViews(View view) {
        mXreclerMyissueGoodselect.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {
        RequestMyIssueGoodSelectData();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mXreclerMyissueGoodselect.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXreclerMyissueGoodselect.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXreclerMyissueGoodselect.refreshComplete();
            }
        });
    }

    /*请求我发布的精选的数据*/
    private void RequestMyIssueGoodSelectData() {
        OkGo.<MyIssueGoodSelectBean>post(Constant.MYISSURE_GOOD_SELECT_URL)
                .execute(new JsonCallback<MyIssueGoodSelectBean>() {
                    @Override
                    public void onSuccess(Response<MyIssueGoodSelectBean> response) {
                        parseMyIssueGoodSelectData(response.body());
                    }
                });
    }

    private void parseMyIssueGoodSelectData(MyIssueGoodSelectBean myIssueGoodSelectBean) {
        int code = myIssueGoodSelectBean.getCode();
        List<MyIssueGoodSelectBean.DataBean> goodSelectData = myIssueGoodSelectBean.getData();
        if (code == 200) {
            MyIssueGoodSelectAdapter myIssueGoodSelectAdapter = new MyIssueGoodSelectAdapter(mActivity, goodSelectData);
            mXreclerMyissueGoodselect.setAdapter(myIssueGoodSelectAdapter);

        }
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
