package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MybaoliaoPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.BaoliaoDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class PersonDetailBaoLiaoFragment extends BaseFragment  {
    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    @BindView(R.id.ll_empty)
    View mEmptyView;

    private MybaoliaoPostAdapter myCollectPostAdapter;
    private List<BaoliaoBean.DataBean> dataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private PersonDetailActivity mPersonDetailActivity;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initData() {
        mPersonDetailActivity = (PersonDetailActivity) getActivity();
        LoadMycolectNews();
    }

    private void LoadMycolectNews() {
        OkGo.<BaoliaoBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                .params("member_id", mPersonDetailActivity.member_id)
                .params("limit", 12)
                .params("skip", count)
                .execute(new JsonCallback<BaoliaoBean>() {
                    @Override
                    public void onSuccess(Response<BaoliaoBean> response) {
                        parseCollectPostData(response.body());
                    }
                });
    }
    /**
     * 解析我的收藏列表
     *
     * @param
     */
    private void parseCollectPostData(BaoliaoBean myCollectPostBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }

        dataList.addAll(myCollectPostBean.getData());
        count = dataList.size();
        if (myCollectPostBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myCollectPostAdapter = new MybaoliaoPostAdapter(mActivity, dataList);
                xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);
                myCollectPostAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("baoliao",dataList.get(position-1));
                        toActivity(BaoliaoDetailActivity.class,bundle);
                    }
                });
                if (dataList.isEmpty()) {
                    mEmptyView.setVisibility(View.VISIBLE);
                }
            }
            xrecycler_mine_collect_news.refreshComplete();
            myCollectPostAdapter.notifyDataSetChanged();
            myCollectPostAdapter.notifyItemRangeChanged(count, myCollectPostBean.getData().size());
        } else{
            ToastUtils.showShort(myCollectPostBean.getMessage());
        }

    }

    @Override
    public void initViews(View view) {
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecycler_mine_collect_news.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                LoadMycolectNews();
            }

            @Override
            public void onLoadMore() {
                LoadMycolectNews();
            }
        });
    }

    @Override
    protected void onLoadData() {

    }

}
