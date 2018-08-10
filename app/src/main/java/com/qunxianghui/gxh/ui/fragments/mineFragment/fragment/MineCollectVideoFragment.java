package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.qunxianghui.gxh.observer.EventObserver;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectVideoFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.xrecycler_mycollect_video)
    XRecyclerView xrecyclerMycollectVideo;
    Unbinder unbinder;
    @BindView(R.id.bt_mycollect_delete)
    Button btMycollectDelete;
    private List<MineCollectVideoBean.DataBean> mDataList;
    private MineCollectVideoAdapter mMineCollectVideoAdapter;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_mine_collect_video;
    }

    @Override
    public void initData() {
        super.initData();
        OkGo.<String>post(Constant.GET_COLLECT_VIDEO_URL)
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
        if (mineCollectVideoBean.getCode() == 0) {
            mDataList = mineCollectVideoBean.getData();

            mMineCollectVideoAdapter = new MineCollectVideoAdapter(mActivity, mDataList);
            xrecyclerMycollectVideo.setAdapter(mMineCollectVideoAdapter);
            mMineCollectVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    asyncShowToast("点击了");
                }
            });
        }
    }

    @Override
    protected void initListeners() {
        btMycollectDelete.setOnClickListener(this);
        xrecyclerMycollectVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerMycollectVideo.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerMycollectVideo.refreshComplete();
            }
        });
    }

    @Override
    public void initViews(View view) {
        xrecyclerMycollectVideo.setLayoutManager(new GridLayoutManager(mActivity, 2));
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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_mycollect_delete:
                for (MineCollectVideoBean.DataBean dataBean : mDataList) {
                    if (dataBean.isChecked()) {
                        deleteCollected(dataBean);
                    }
                }
                break;
        }
    }

    private void deleteCollected(final MineCollectVideoBean.DataBean dataBean) {
        OkGo.<String>post(Constant.CANCEL_COLLECT_URL).params("uuid",
                dataBean.getInfo().getUuid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                mDataList.remove(dataBean);
                                mMineCollectVideoAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        asyncShowToast("删除失败");
                    }
                });
    }
    private class EditEvent extends EventObserver {
        @Override
        public void update(Object object) {
            //true表示可编辑状态
            mMineCollectVideoAdapter.setIsCheckBoxVisible((boolean) object);
            mMineCollectVideoAdapter.notifyDataSetChanged();
        }
    }
}
