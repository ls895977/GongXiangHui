package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MybaoliaoPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.activity.BaoliaoDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class PersonDetailBaoLiaoFragment extends BaseFragment implements Observer {

    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    @BindView(R.id.ll_empty)
    LinearLayout mEmptyView;
    @BindView(R.id.bt_mycollect_delete)
    Button btMycollectDelete;
    Unbinder unbinder;
    @BindView(R.id.tv_myissue_baoliao)
    TextView tvMyissueBaoliao;

    private MybaoliaoPostAdapter myCollectPostAdapter;
    private List<BaoliaoBean.DataBean> dataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private PersonDetailActivity mPersonDetailActivity;
    private String data_id = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initData() {
        if (getActivity() instanceof PersonDetailActivity) {
            mPersonDetailActivity = (PersonDetailActivity) getActivity();
        }
        LoadMycolectNews();
    }

    private void LoadMycolectNews() {
        Log.e("TAG_爆料", "skip=" + count);
        if (mPersonDetailActivity == null) {
            tvMyissueBaoliao.setText("您还没发布哦～");
            OkGo.<BaoliaoBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                    .params("limit", 12)
                    .params("skip", count)
                    .execute(new JsonCallback<BaoliaoBean>() {
                        @Override
                        public void onSuccess(Response<BaoliaoBean> response) {
                            Log.e("TAG_爆料1", "onSuccess" + response.toString());
                            parseCollectPostData(response.body());
                        }

                        @Override
                        public void onError(Response<BaoliaoBean> response) {
                            super.onError(response);
                            Log.e("TAG_爆料1", "onError=" + response.code() + "====" + response.body());
                        }
                    });
        } else {
            tvMyissueBaoliao.setText("他还没发布哦～");
            Log.e("TAG_爆料", "member_id=" + mPersonDetailActivity.member_id);
            OkGo.<BaoliaoBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                    .params("member_id", mPersonDetailActivity.member_id)
                    .params("limit", 12)
                    .params("skip", count)
                    .execute(new JsonCallback<BaoliaoBean>() {
                        @Override
                        public void onSuccess(Response<BaoliaoBean> response) {
                            Log.e("TAG_爆料", "onSuccess" + response.toString());
                            parseCollectPostData(response.body());
                        }

                        @Override
                        public void onError(Response<BaoliaoBean> response) {
                            super.onError(response);
                            Log.e("TAG_爆料", "onError" + response.toString());
                        }
                    });
        }
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
        if (myCollectPostBean.getData() != null) {
            dataList.addAll(myCollectPostBean.getData());
            count = dataList.size();
            Log.e("TAG_爆料", "code" + myCollectPostBean.getCode());
            if (myCollectPostBean.getCode() == 0) {
                if (mIsFirst) {
                    mIsFirst = false;
                    myCollectPostAdapter = new MybaoliaoPostAdapter(mActivity, dataList);
                    myCollectPostAdapter.setOnAdapterClick(new MybaoliaoPostAdapter.OnAdapterClick() {
                        @Override
                        public void onClick(int position) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("baoliao", dataList.get(position));
                            Intent intent = new Intent(getContext(), BaoliaoDetailActivity.class);
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
//                        toActivity(BaoliaoDetailActivity.class, bundle);
                        }
                    });

                    xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);
                }
                if (dataList.isEmpty() || dataList.size() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }
                xrecycler_mine_collect_news.refreshComplete();
                myCollectPostAdapter.notifyDataSetChanged();
                myCollectPostAdapter.notifyItemRangeChanged(count, myCollectPostBean.getData().size());
            } else {
                ToastUtils.showShort(myCollectPostBean.getMessage());
            }
        }
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
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

        btMycollectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBaoLiaoData();
            }
        });
    }

    /*多条删除爆料的数据*/
    private void deleteBaoLiaoData() {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isChecked()) {
                if (TextUtils.isEmpty(data_id)) {
                    data_id = data_id + dataList.get(i).getId();
                } else {
                    data_id = data_id + "," + dataList.get(i).getId();
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
                            Iterator<BaoliaoBean.DataBean> iterator = dataList.iterator();
                            while (iterator.hasNext()) {
                                BaoliaoBean.DataBean next = iterator.next();
                                if (next.isChecked()) {
                                    iterator.remove();
                                }
                            }
                        }
                        data_id = "";
                        myCollectPostAdapter.isShow = false;
                        myCollectPostAdapter.notifyDataSetChanged();
                        btMycollectDelete.setVisibility(View.GONE);
                        EventManager.getInstance().publishMessage("init");
                    }
                });
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "baoliao".equals(o)) {
            myCollectPostAdapter.isShow = true;
            myCollectPostAdapter.notifyDataSetChanged();
            if (dataList.size() > 0) {
                btMycollectDelete.setVisibility(View.VISIBLE);
            }

        }
        if (o instanceof String && "baoliao_c".equals(o)) {
            myCollectPostAdapter.isShow = false;
            myCollectPostAdapter.notifyDataSetChanged();
            btMycollectDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().deleteObserver(this);
    }
}
