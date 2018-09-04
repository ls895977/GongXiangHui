package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
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

public class MineCollectVideoFragment extends BaseFragment implements Observer {

    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView mRv;
    @BindView(R.id.bt_mycollect_delete)
    Button btnDelete;

    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private List<MineCollectVideoBean.DataBean> dataList = new ArrayList<>();
    private MineCollectVideoAdapter mAdapter;
    private String data_id = "";

    @Override
    protected void onLoadData() {
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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_collect_video;
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
                mAdapter = new MineCollectVideoAdapter(mActivity, dataList);
                mRv.setAdapter(mAdapter);
                mAdapter.setCallback(new MineCollectVideoAdapter.Callback() {
                    @Override
                    public void callback(int id) {
                        skipMycollectVideoDetail(id);
                    }
                });
            }
            mRv.refreshComplete();
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(count, mineCollectVideoBean.getData().size());
        }
    }

    /**
     * 跳转我的收藏的视频详情页
     *
     * @param data_uuid
     */
    private void skipMycollectVideoDetail(int data_uuid) {
        OkGo.<MyCollectVideoDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", data_uuid)
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
                            intent.putExtra("position", 4);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                onLoadData();
            }

            @Override
            public void onLoadMore() {
                onLoadData();
            }
        });
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).isChecked()) {
                        if (TextUtils.isEmpty(data_id)) {
                            data_id = data_id + dataList.get(i).getInfo().getUuid();
                        } else {
                            data_id = data_id + "," + dataList.get(i).getInfo().getUuid();
                        }
                    }
                }
                if (TextUtils.isEmpty(data_id)) return;
                OkGo.<CommonBean>post(Constant.CANCEL_COLLECT_URL)
                        .params("uuid", data_id)
                        .execute(new JsonCallback<CommonBean>() {
                            @Override
                            public void onSuccess(Response<CommonBean> response) {
                                CommonBean body = response.body();
                                if (body.code == 200) {
                                    Iterator<MineCollectVideoBean.DataBean> iterator = dataList.iterator();
                                    while (iterator.hasNext()) {
                                        MineCollectVideoBean.DataBean next = iterator.next();
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
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "video".equals(o)) {
            Constant.MyCollectIsShow = true;
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "video_c".equals(o)) {
            Constant.MyCollectIsShow = false;
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }
}
