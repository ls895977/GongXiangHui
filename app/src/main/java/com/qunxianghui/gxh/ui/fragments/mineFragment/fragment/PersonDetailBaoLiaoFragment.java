package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyColleNewsDetailBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class PersonDetailBaoLiaoFragment extends BaseFragment  {
    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    Unbinder unbinder;

    private MyCollectPostAdapter myCollectPostAdapter;
    private List<MyCollectPostBean.DataBean> dataList = new ArrayList<>();
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
        OkGo.<MyCollectPostBean>post(Constant.HOME_NEWS_LIST_URL)
                .params("user_id", mPersonDetailActivity.member_id)
                .params("limit", 12)
                .params("skip", count)
                .execute(new JsonCallback<MyCollectPostBean>() {
                    @Override
                    public void onSuccess(Response<MyCollectPostBean> response) {
                        parseCollectPostData(response.body());
                    }
                });
    }
    /**
     * 解析我的收藏列表
     *
     * @param
     */
    private void parseCollectPostData(MyCollectPostBean myCollectPostBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myCollectPostBean.getData());
        count = dataList.size();
        if (myCollectPostBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myCollectPostAdapter = new MyCollectPostAdapter(mActivity, dataList);
                xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);
                myCollectPostAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        int id = dataList.get(position).getData_uuid();
                        SkipMycollectNewsDetail(id, position);
                    }
                });
            }

        }
        xrecycler_mine_collect_news.refreshComplete();
        myCollectPostAdapter.notifyDataSetChanged();
        myCollectPostAdapter.notifyItemRangeChanged(count, myCollectPostBean.getData().size());
    }

    /**
     * 跳转新闻详情页
     *
     * @param id
     */
    private void SkipMycollectNewsDetail(final int id, final int position) {
        OkGo.<MyColleNewsDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", id)
                .execute(new JsonCallback<MyColleNewsDetailBean>() {
                    @Override
                    public void onSuccess(Response<MyColleNewsDetailBean> response) {
                        MyColleNewsDetailBean myColleNewsDetailBean = response.body();
                        int code = myColleNewsDetailBean.getCode();
                        if (code == 200) {
                            int uuid = myColleNewsDetailBean.getData().getDetail().getUuid();
                            String url = myColleNewsDetailBean.getData().getRand_data().get(position).getUrl();
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", url);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            startActivity(intent);
                        }

                    }
                });
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
