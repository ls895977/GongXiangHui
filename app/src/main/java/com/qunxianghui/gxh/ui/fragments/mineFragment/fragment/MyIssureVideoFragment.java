package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 视频
 */
public class MyIssureVideoFragment extends BaseFragment implements Observer {

    @BindView(R.id.recycler_mine_issue_video)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_video_delete)
    Button btnDelete;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    Unbinder unbinder;
    private int mSkip = 0;
    private List<MineIssueVideoBean.DataBean> mList = new ArrayList<>();
    private MineIssueVideoAdapter mAdapter;
    private String data_id = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issue_video;
    }

    @Override
    public void initViews(View view) {

        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        mAdapter = new MineIssueVideoAdapter(getContext(), mList);
        mRv.setAdapter(mAdapter);
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 12;
                initData();
            }
        });
        mAdapter.setCallback(new MineIssueVideoAdapter.Callback() {
            @Override
            public void callback(int id) {
                skipMyIssueVideoDetail(id);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChecked()) {
                        if (TextUtils.isEmpty(data_id)) {
                            data_id = data_id + mList.get(i).getInfo().getUuid();
                        } else {
                            data_id = data_id + "," + mList.get(i).getInfo().getUuid();
                        }
                    }
                }
                OkGo.<CommonBean>post(Constant.CANCEL_ISSUE_URL)
                        .params("uuid", data_id)
                        .execute(new JsonCallback<CommonBean>() {
                            @Override
                            public void onSuccess(Response<CommonBean> response) {
                                CommonBean body = response.body();
                                asyncShowToast(body.message);
                                if (body.code == 200) {
                                    Iterator<MineIssueVideoBean.DataBean> iterator = mList.iterator();
                                    while (iterator.hasNext()) {
                                        MineIssueVideoBean.DataBean next = iterator.next();
                                        if (next.isChecked()) {
                                            iterator.remove();
                                        }
                                    }
                                }
                                data_id = "";
                                mAdapter.isShow = false;
                                mAdapter.notifyDataSetChanged();
                                btnDelete.setVisibility(View.GONE);
                                EventManager.getInstance().publishMessage("init");
                            }
                        });
            }

        });
    }

    @Override
    public void initData() {
        RequestMineIssueVideoData();
    }
    private void RequestMineIssueVideoData() {
        OkGo.<MineIssueVideoBean>post(Constant.GET_ISSURE_VIDEO_URL)
                .params("limit", 12)
                .params("skip", mSkip)
                .execute(new JsonCallback<MineIssueVideoBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueVideoBean> response) {
                        parseData(response.body());
                    }
                });
    }
    private void parseData(MineIssueVideoBean data) {
        if (data.getCode() == 0) {
            if (mSkip == 0) {
                mList.clear();
                mRv.setLoadingMoreEnabled(true);
            }
            if (data.getData().size() < 12) {
                mRv.setLoadingMoreEnabled(false);
            }
            mList.addAll(data.getData());
            mRv.refreshComplete();

            if (mList.isEmpty()) {
                llEmpty.setVisibility(View.VISIBLE);
            }
        } else {
            mRv.setLoadingMoreEnabled(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 跳转我的发布的视频详情页
     *
     * @param uuid
     */
    private void skipMyIssueVideoDetail(int uuid) {
        OkGo.<MyCollectVideoDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", uuid)
                .execute(new JsonCallback<MyCollectVideoDetailBean>() {
                    @Override
                    public void onSuccess(Response<MyCollectVideoDetailBean> response) {
                        MyCollectVideoDetailBean myCollectVideoDetailBean = response.body();
                        int code = myCollectVideoDetailBean.getCode();
                        if (code == 200) {
                            int uuid = myCollectVideoDetailBean.getData().getDetail().getUuid();
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "issue_video".equals(o)) {
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "issue_video_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }
    /*请求接口删除*/
    private void DeleteVideo(final int position) {
        OkGo.<CommonBean>post(Constant.DELETE_MYISSUE_URL)
                .params("uuid", mList.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("删除成功");
                            mList.remove(position);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            asyncShowToast("删除失败");
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
