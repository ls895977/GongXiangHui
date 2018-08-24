package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineCollectVideoFragment extends BaseFragment implements MineCollectVideoAdapter.MyCollectVideoClickListener {

    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView xrecyclerMycollectVideo;

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
    public void initData() {
    }

    /**
     * 请求我收藏的视频
     */
    private void RequestMineCollectVideo() {
        OkGo.<MineCollectVideoBean>post(Constant.GET_COLLECT_VIDEO_URL)
                .params("limit", 12)
                .params("skip", count)
                .execute(new JsonCallback<MineCollectVideoBean>() {
                    @Override
                    public void onSuccess(Response<MineCollectVideoBean> response) {
                        Logger.d("我爆料的视频+++" + response.body().toString());
                        ParseMineCollectVideo(response.body());
                    }
                });
    }

    private void ParseMineCollectVideo(MineCollectVideoBean mineCollectVideoBean) {
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
                        SkipMycollectVideoDetail(data_uuid, position);
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
    private void SkipMycollectVideoDetail(int data_uuid, final int position) {
        OkGo.<MyCollectVideoDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", data_uuid)
                .execute(new JsonCallback<MyCollectVideoDetailBean>() {
                    @Override
                    public void onSuccess(Response<MyCollectVideoDetailBean> response) {
                        MyCollectVideoDetailBean myCollectVideoDetailBean = response.body();
                        int code = myCollectVideoDetailBean.getCode();
                        if (code == 200) {
                            int uuid = myCollectVideoDetailBean.getData().getDetail().getUuid();
                            String url = myCollectVideoDetailBean.getData().getRand_data().get(position).getUrl();
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", url);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            intent.putExtra("position", 4);
                            startActivity(intent);
                        }
                    }
                });
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
    public void attentionClick(final int position) {
        OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                .params("be_member_id", dataList.get(position).getId())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        final int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("关注成功");
                            dataList.get(position).getMember().setFollow("true");
                        } else if (code == 202) {
                            asyncShowToast("取消关注");
                            dataList.get(position).getMember().setFollow("");
                        } else if (code == 101) {
                            asyncShowToast("请不要自己关注自己");
                        }
                        mineCollectVideoAdapter.notifyItemChanged(position);
                        mineCollectVideoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        Logger.e("视频关注" + response.body().toString());
                    }
                });
    }

    @Override
    public void videoHeadImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", dataList.get(position).getMember_id());
        startActivity(intent);
    }
}
