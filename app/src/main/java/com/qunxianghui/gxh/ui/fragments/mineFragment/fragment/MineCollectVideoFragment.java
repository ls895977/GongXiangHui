package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

public class MineCollectVideoFragment extends BaseFragment implements Observer{
    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView xrecyclerMycollectVideo;
    @BindView(R.id.bt_mycollect_delete)
    Button btnDelete;

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
        EventManager.getInstance().addObserver(this);
        xrecyclerMycollectVideo.setLayoutManager(new GridLayoutManager(mActivity,2, GridLayoutManager.VERTICAL, false));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <dataList.size() ; i++) {
                    if (dataList.get(i).isChecked() == true) {
                        //这边获取选中的数据id,
                    }
                }
            }
        });
    }


    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "video".equals(o)) {
            mineCollectVideoAdapter.isShow=true;
            mineCollectVideoAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "video_c".equals(o)) {
            mineCollectVideoAdapter.isShow=false;
            mineCollectVideoAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }
}
