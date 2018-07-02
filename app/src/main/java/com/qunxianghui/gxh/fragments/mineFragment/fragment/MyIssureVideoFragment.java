package com.qunxianghui.gxh.fragments.mineFragment.fragment;

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
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONObject;

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
    public void initDatas() {

        RequestMyIssueVideo();


    }

    /**
     * 请求我的发布中的视频
     */

    private void RequestMyIssueVideo() {
        OkGo.<String>post(Constant.GET_ISSURE_VIDEO_URL)
                .params("limit", 5)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        com.orhanobut.logger.Logger.d("我爆料的视频+++" + response.body().toString());
                        ParseMineIssueVideo(response.body());

                    }
                });
    }

    private void ParseMineIssueVideo(String body) {
        final MineIssueVideoBean mineIssueVideoBean = GsonUtils.jsonFromJson(body, MineIssueVideoBean.class);

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
                        intent.putExtra("url", dataList.get(position).getVideo_url());
                        intent.putExtra("title", dataList.get(position).getTitle());
                        startActivity(intent);
                    }
                });
            }
            recyclerMineIssueVideo.refreshComplete();
            mineIssueVideoAdapter.notifyDataSetChanged();
            mineIssueVideoAdapter.notifyItemChanged(count, dataList.size());

        }
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
    protected void onLoadData() {

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
        OkGo.<String>post(Constant.DELETE_MYISSUE_URL)
                .params("uuid", dataList.get(position).getUuid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code==0){
                                asyncShowToast("删除成功");
                                mineIssueVideoAdapter.notifyItemChanged(position);
                                mineIssueVideoAdapter.notifyDataSetChanged();
                            }else {
                                asyncShowToast("删除失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }


                    }
                });

        mineIssueVideoAdapter.notifyDataSetChanged();
        mineIssueVideoAdapter.notifyItemChanged(position);


    }


}
