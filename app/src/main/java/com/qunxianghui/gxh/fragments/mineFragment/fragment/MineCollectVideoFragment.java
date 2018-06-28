package com.qunxianghui.gxh.fragments.mineFragment.fragment;

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
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectVideoFragment extends BaseFragment {
    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView xrecyclerMycollectVideo;
    Unbinder unbinder;
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private List<MineCollectVideoBean.DataBean> dataList=new ArrayList<>();
    private MineCollectVideoAdapter mineCollectVideoAdapter;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_collect_video;
    }

    @Override
    public void initDatas() {
     RequestMineCollectVideo();

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

        if (mIsRefresh){
            mIsRefresh=false;
            dataList.clear();

        }
        dataList.addAll(mineCollectVideoBean.getData());
        count=dataList.size();
        if (mineCollectVideoBean.getCode() == 0) {
        if (mIsFirst){
            mIsFirst=false;
            mineCollectVideoAdapter = new MineCollectVideoAdapter(mActivity, dataList);
            xrecyclerMycollectVideo.setAdapter(mineCollectVideoAdapter);
            mineCollectVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    asyncShowToast("点击了");
                }
            });
        }
            xrecyclerMycollectVideo.refreshComplete();
            mineCollectVideoAdapter.notifyDataSetChanged();
            mineCollectVideoAdapter.notifyItemRangeChanged(count,mineCollectVideoBean.getData().size());


        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerMycollectVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
             mIsRefresh=true;
             count=0;
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
}
