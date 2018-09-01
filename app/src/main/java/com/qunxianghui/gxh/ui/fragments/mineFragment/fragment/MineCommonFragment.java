package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MyColleNewsDetailBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment implements Observer {

    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    @BindView(R.id.bt_mycollect_delete)
    Button btnDelete;

    private MyCollectPostAdapter myCollectPostAdapter;
    private List<MyCollectPostBean.DataBean> dataList = new ArrayList<>();
    private Handler handler = new Handler();
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private String data_id = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initData() {
        LoadMycolectNews();
    }

    private void LoadMycolectNews() {
        OkGo.<MyCollectPostBean>post(Constant.GET_COLLECT_NEWS_URL)
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
                        if (!myCollectPostAdapter.isShow) {
                            int id = dataList.get(position - 1).getData_uuid();
                            SkipMycollectNewsDetail(id, position);
                        }

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
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", Constant.HOME_NEWS_DETAIL_URL);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            startActivity(intent);
                        }

                    }
                });
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).isChecked()) {
                        if (TextUtils.isEmpty(data_id)) {
                            data_id = data_id + dataList.get(i).getInfo().getUuid();
                        } else {
                            data_id = data_id + "," + dataList.get(i).getInfo().getUuid();
                        }
                    }
                }
                if (TextUtils.isEmpty(data_id)) return;
                OkGo.<CommonBean>post(Constant.CANCEL_COLLECT_URL)
                        .params("uuid", data_id)
                        .execute(new JsonCallback<CommonBean>() {
                            @Override
                            public void onSuccess(Response<CommonBean> response) {
                                CommonBean body = response.body();
                                asyncShowToast(body.message);
                                if (body.code == 200) {
                                    Iterator<MyCollectPostBean.DataBean> iterator = dataList.iterator();
                                    while (iterator.hasNext()) {
                                        MyCollectPostBean.DataBean next = iterator.next();
                                        if (next.isChecked()) {
                                            iterator.remove();
                                        }
                                    }
                                }
                                data_id = "";
                                myCollectPostAdapter.isShow = false;
                                myCollectPostAdapter.notifyDataSetChanged();
                                btnDelete.setVisibility(View.GONE);
                                EventManager.getInstance().publishMessage("init");
                            }
                        });
            }

        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "news".equals(o)) {
            myCollectPostAdapter.isShow = true;
            myCollectPostAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "news_c".equals(o)) {
            myCollectPostAdapter.isShow = false;
            myCollectPostAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }
}
