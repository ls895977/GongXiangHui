package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.List;

import butterknife.BindView;

public class PersonBestServiceFragment extends BaseFragment {

    @BindView(R.id.xrecycler_persondetail_post)
    XRecyclerView xrecyclerPersondetailPost;
    @BindView(R.id.ll_empty)
    View mEmptyView;

    private List<TestMode.DataBean.ListBean> postList;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_persondetail_post;
    }

    @Override
    public void initData() {
        PersonDetailActivity personDetailActivity = (PersonDetailActivity) getActivity();
        /**
         * 获取帖子列表
         */
        OkGo.<TestMode>post(Constant.LOCATION_NEWS_LIST_URL)
                .params("user_id", personDetailActivity.member_id)
                .execute(new JsonCallback<TestMode>() {
                    @Override
                    public void onSuccess(Response<TestMode> response) {
                        parsePersonDetailPostData(response.body());
                    }
                });
    }

    private void parsePersonDetailPostData(TestMode testMode) {
        if (testMode.getCode() == 0) {
            postList = testMode.getData().getList();
            final NineGridTest2Adapter persondetailPostAdapter = new NineGridTest2Adapter(mActivity, postList);
            xrecyclerPersondetailPost.setAdapter(persondetailPostAdapter);
        }
    }

    @Override
    public void initViews(View view) {
        xrecyclerPersondetailPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        /**
         * 下拉刷新和上拉加载
         */
        xrecyclerPersondetailPost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerPersondetailPost.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerPersondetailPost.refreshComplete();
            }
        });
    }
}
