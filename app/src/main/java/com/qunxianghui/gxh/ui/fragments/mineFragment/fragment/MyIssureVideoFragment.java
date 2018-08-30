package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class MyIssureVideoFragment extends BaseFragment implements MineIssueVideoAdapter.MyIssueVideoClikListener {

    @BindView(R.id.recycler_mine_issue_video)
    XRecyclerView mRv;

    private int mSkip = 0;
    private List<MineIssueVideoBean.DataBean> mList = new ArrayList<>();
    private MineIssueVideoAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issue_video;
    }

    @Override
    public void initViews(View view) {
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL, false));
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
                mSkip += 10;
                initData();
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                int uuid = mList.get(position - 1).getUuid();
                SkipMyIssueVideoDetail(uuid, position);
            }
        });
    }

    @Override
    public void initData() {
        OkGo.<MineIssueVideoBean>post(Constant.GET_ISSURE_VIDEO_URL)
                .params("limit", 10)
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
                            intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            startActivity(intent);
                        }
                    }
                });
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
        mAdapter.notifyDataSetChanged();
        builder.setNegativeButton("取消", null);
        builder.show();
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

}
