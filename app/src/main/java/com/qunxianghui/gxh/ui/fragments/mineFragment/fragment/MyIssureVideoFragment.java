package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssureVideoFragment extends BaseFragment implements MineIssueVideoAdapter.MyIssueVideoClikListener {
    @BindView(R.id.recycler_mine_issue_video)
    XRecyclerView recyclerMineIssueVideo;
    Unbinder unbinder;
    private Handler handler = new Handler();
    private int count = 0;
    private boolean mIsFirst = true;
    private boolean mIsRefreshing = false;
    private List<MineIssueVideoBean.DataBean> dataList = new ArrayList<>();
    private MineIssueVideoAdapter mineIssueVideoAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issue_video;
    }

    @Override
    public void initData() {
        RequestMyIssueVideo();
    }

    /**
     * 请求我的发布中的视频
     */
    private void RequestMyIssueVideo() {
        OkGo.<MineIssueVideoBean>post(Constant.GET_ISSURE_VIDEO_URL)
                .params("limit", 5)
                .params("skip", count)
                .execute(new JsonCallback<MineIssueVideoBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueVideoBean> response) {
                        ParseMineIssueVideo(response.body());
                    }
                });
    }

    private void ParseMineIssueVideo(MineIssueVideoBean mineIssueVideoBean) {
        if (mIsRefreshing) {
            mIsRefreshing = false;
            dataList.clear();
        }
        dataList.addAll(mineIssueVideoBean.getData());
        count = dataList.size();
        if (mineIssueVideoBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mineIssueVideoAdapter = new MineIssueVideoAdapter(mActivity, dataList);
                mineIssueVideoAdapter.setMyIssueVideoClikListener(this);
                recyclerMineIssueVideo.setAdapter(mineIssueVideoAdapter);
                mineIssueVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(mActivity, ProtocolActivity.class);
                        int uuid = dataList.get(position - 1).getUuid();
                        SkipMyIssueVideoDetail(uuid, position);
                    }
                });
            }
            recyclerMineIssueVideo.refreshComplete();
            mineIssueVideoAdapter.notifyDataSetChanged();
            mineIssueVideoAdapter.notifyItemChanged(count, dataList.size());
        }
    }

    /**
     * 跳转我的发布的视频详情页
     *
     * @param uuid
     */
    private void SkipMyIssueVideoDetail(int uuid, final int position) {
        OkGo.<MyCollectVideoDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", uuid)
                .execute(new JsonCallback<MyCollectVideoDetailBean>() {
                    @Override
                    public void onSuccess(Response<MyCollectVideoDetailBean> response) {
                        MyCollectVideoDetailBean myCollectVideoDetailBean = response.body();
                        int code = myCollectVideoDetailBean.getCode();
                        if (code == 0) {
                            String url = myCollectVideoDetailBean.getData().getRand_data().get(position).getUrl();
                            int uuid = myCollectVideoDetailBean.getData().getDetail().getUuid();
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", url);
                            intent.putExtra("uuid", uuid);
                            startActivity(intent);
                        }
                    }
                });
    }


    @Override
    public void initViews(View view) {
        recyclerMineIssueVideo.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineIssueVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefreshing = true;
                RequestMyIssueVideo();
            }

            @Override
            public void onLoadMore() {
                RequestMyIssueVideo();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*接口回调  删除视频的操作*/

    @Override
    public void deleVideoItem(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条消息吗?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteVideo(position);
            }

        });
        mineIssueVideoAdapter.notifyDataSetChanged();
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /*请求接口删除*/
    private void DeleteVideo(final int position) {
        OkGo.<CommonBean>post(Constant.DELETE_MYISSUE_URL)
                .params("uuid", dataList.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("删除成功");
                            dataList.remove(position);
                            mineIssueVideoAdapter.notifyDataSetChanged();
                        } else {
                            asyncShowToast("删除失败");
                        }
                    }
                });
    }

}
