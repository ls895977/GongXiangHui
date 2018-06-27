package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity implements View.OnClickListener, PersonDetailVideoAdapter.VideoListClickListener {

    @BindView(R.id.xrecycler_homevideo_list)
    XRecyclerView xrecyclerHomevideoList;
    @BindView(R.id.iv_home_video_back)
    ImageView ivHomeVideoBack;
    @BindView(R.id.tv_home_video_issue)
    TextView tvHomeVideoIssue;

    private PersonDetailVideoAdapter personDetailVideoAdapter;
    private String follow;
    private int count = 0;
    private boolean mIsFirst = true;
 private List<HomeVideoListBean.DataBean.ListBean> videoDataList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initViews() {
        xrecyclerHomevideoList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void initDatas() {

        RequestHomeVideoList();


    }

    private void RequestHomeVideoList() {
        OkGo.<String>post(Constant.HOME_VIDEO_LIST_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parsePersonDetailVideoData(response.body());

                    }
                });
    }

    /**
     * 解析首页视频列表
     *
     * @param body
     */
    private void parsePersonDetailVideoData(String body) {

        final HomeVideoListBean homeVideoListBean = GsonUtils.jsonFromJson(body, HomeVideoListBean.class);


        videoDataList.addAll(homeVideoListBean.getData().getList());
        count = videoDataList.size();
        if (homeVideoListBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                personDetailVideoAdapter = new PersonDetailVideoAdapter(mContext,videoDataList);
                personDetailVideoAdapter.setVideoListClickListener(this);

                xrecyclerHomevideoList.setAdapter(personDetailVideoAdapter);
            }
            xrecyclerHomevideoList.refreshComplete();

            personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);

                    intent.putExtra("url", videoDataList.get(position - 1).getUrl());
                    startActivity(intent);
                }
            });

            personDetailVideoAdapter.notifyItemRangeChanged(count, homeVideoListBean.getData().getList().size());


        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivHomeVideoBack.setOnClickListener(this);
        tvHomeVideoIssue.setOnClickListener(this);

        xrecyclerHomevideoList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                videoDataList.clear();
                count = 0;
                RequestHomeVideoList();


            }

            @Override
            public void onLoadMore() {

                RequestHomeVideoList();


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_video_back:
                finish();
                break;
            case R.id.tv_home_video_issue:
                issueHomeVideo();

                break;
        }

    }

    private void issueHomeVideo() {


    }


    /**
     * 视频列表的关注
     *
     * @param position
     */
    @Override
    public void attentionClick(int position) {
        OkGo.<String>post(Constant.ATTENTION_URL).params("be_member_id", videoDataList.get(position).getMember_id())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            final int code = jsonObject.getInt("code");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        asyncShowToast("关注成功");
                                        personDetailVideoAdapter.videoAttention.setText("已关注");

                                    } else if (code == 202) {
                                        asyncShowToast("取消关注成功");
                                        personDetailVideoAdapter.videoAttention.setText("关注");
                                    }

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("视频关注" + response.body().toString());
                    }
                });

        Logger.d("视频汇的关注position"+position);
    }

//    视频头像点击
    @Override
    public void videoHeadImageClick(int position) {
        Intent intent = new Intent(mContext, PersonDetailActivity.class);
        intent.putExtra("member_id", videoDataList.get(position).getMember_id());
        startActivity(intent);
    }
}
