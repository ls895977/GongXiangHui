package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageFollewAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineMessageCommentBean;
import com.qunxianghui.gxh.bean.mine.MineMessageFollowBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineMessageFollowFragment extends BaseFragment {
    @BindView(R.id.xrecycler_mineFollowMessage)
    XRecyclerView xrecyclerMineFollowMessage;
    Unbinder unbinder;


    private int count=0;
    private boolean mIsRefresh=false;
    private boolean mIsFirst=true;
    private List<MineMessageFollowBean.DataBean> dataList=new ArrayList<>();
    private MineMessageFollewAdapter mineMessageFollewAdapter;

    @Override
    protected void onLoadData() {

        RequestMyMessageFollow();

    }

    /**
     * 请求我的跟帖
     */
    private void RequestMyMessageFollow() {
        OkGo.<String>post(Constant.DISCUSS_MINE_FOLLOW_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        parsePaiHangData(response.body());


                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_message_follow;
    }

    @Override
    public void initDatas() {



    }

    private void parsePaiHangData(String body) {
        final MineMessageFollowBean mineMessageFollowBean = GsonUtils.jsonFromJson(body, MineMessageFollowBean.class);
        if (mIsRefresh){
           mIsRefresh=false;
            dataList.clear();

        }

        dataList.addAll(mineMessageFollowBean.getData());
        count=dataList.size();


        if (mineMessageFollowBean.getCode() == 0) {
            if (mIsFirst){
                mIsFirst=false;
                mineMessageFollewAdapter = new MineMessageFollewAdapter(mActivity, dataList);
                xrecyclerMineFollowMessage.setAdapter(mineMessageFollewAdapter);

            }

            xrecyclerMineFollowMessage.refreshComplete();
            mineMessageFollewAdapter.notifyDataSetChanged();

            mineMessageFollewAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(mActivity, "点击了:" + position, Toast.LENGTH_SHORT).show();
                }
            });

            mineMessageFollewAdapter.notifyItemChanged(count,mineMessageFollowBean.getData().size());

        }



    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineFollowMessage.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        xrecyclerMineFollowMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                count=0;
                mIsRefresh=true;
                RequestMyMessageFollow();
            }

            @Override
            public void onLoadMore() {
                RequestMyMessageFollow();
            }
        });
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
