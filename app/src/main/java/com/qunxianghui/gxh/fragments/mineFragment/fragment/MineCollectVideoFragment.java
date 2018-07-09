package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectVideoFragment extends BaseFragment implements MineCollectVideoAdapter.MyCollectVideoClickListener {
    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView xrecyclerMycollectVideo;
    Unbinder unbinder;
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private List<MineCollectVideoBean.DataBean> dataList = new ArrayList<>();
    private MineCollectVideoAdapter mineCollectVideoAdapter;

    @Override
    protected void onLoadData() {
        RequestMineCollectVideo();
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_collect_video;
    }
    @Override
    public void initDatas() {
    }

    /**
     * 请求我收藏的视频
     */
    private void RequestMineCollectVideo() {
        OkGo.<String>post(Constant.GET_COLLECT_VIDEO_URL)
                .params("limit", 5)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.d("我爆料的视频+++" + response.body().toString());
                        ParseMineCollectVideo(response.body());
                    }
                });
    }

    private void ParseMineCollectVideo(String body) {
        final MineCollectVideoBean mineCollectVideoBean = GsonUtils.jsonFromJson(body, MineCollectVideoBean.class);

        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(mineCollectVideoBean.getData());
        count = dataList.size();
        if (mineCollectVideoBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mineCollectVideoAdapter = new MineCollectVideoAdapter(mActivity, dataList);
                mineCollectVideoAdapter.setMyCollectVideoClickListener(this);
                xrecyclerMycollectVideo.setAdapter(mineCollectVideoAdapter);
                mineCollectVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        int data_uuid = dataList.get(position - 1).getData_uuid();
                        SkipMycollectVideoDetail(data_uuid);
                    }
                });
            }
            xrecyclerMycollectVideo.refreshComplete();
            mineCollectVideoAdapter.notifyDataSetChanged();
            mineCollectVideoAdapter.notifyItemRangeChanged(count, mineCollectVideoBean.getData().size());
        }
    }

    /**
     * 跳转我的收藏的视频详情页
     *
     * @param data_uuid
     */
    private void SkipMycollectVideoDetail(int data_uuid) {
        OkGo.<String>post(Constant.GET_NEWS_CONTENT_DETAIL_URL).params("id", data_uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ParseMyCollectVideoDetail(response.body());
                    }
                });
    }

    /**
     * 解析我的收藏视频详情
     *
     * @param body
     */
    private void ParseMyCollectVideoDetail(String body) {
        MyCollectVideoDetailBean myCollectVideoDetailBean = GsonUtils.jsonFromJson(body, MyCollectVideoDetailBean.class);
        int code = myCollectVideoDetailBean.getCode();
        if (code == 0) {
            String url = myCollectVideoDetailBean.getData().getUrl();
            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerMycollectVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                RequestMineCollectVideo();
            }

            @Override
            public void onLoadMore() {
                RequestMineCollectVideo();
            }
        });
    }

    @Override
    public void initViews(View view) {
        xrecyclerMycollectVideo.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void attentionClick(final int position) {
        OkGo.<String>post(Constant.ATTENTION_URL).params("be_member_id", dataList.get(position).getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());

                            final int code = jsonObject.getInt("code");
                            if (code == 0) {
                                asyncShowToast("关注成功");
                                dataList.get(position).getMember().setFollow("true");
                            } else if (code == 202) {
                                asyncShowToast("取消关注成功");
                                dataList.get(position).getMember().setFollow("");
                            }else if (code==101){
                                asyncShowToast("请不要自己关注自己");
                            }
                            mineCollectVideoAdapter.notifyItemChanged(position);
                            mineCollectVideoAdapter.notifyDataSetChanged();
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
        Logger.d("视频汇的关注position" + position);
    }
    @Override
    public void videoHeadImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", dataList.get(position).getMember_id());
        startActivity(intent);
    }
}
