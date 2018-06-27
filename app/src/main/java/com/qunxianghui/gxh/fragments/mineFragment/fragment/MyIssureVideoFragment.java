package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssureVideoFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_issue_video)
    XRecyclerView recyclerMineIssueVideo;
    Unbinder unbinder;

    private int count=0;
    private  boolean mIsFirst=true;
    private boolean mIsRefreshing=false;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issue_video;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.GET_ISSURE_VIDEO_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                com.orhanobut.logger.Logger.d("我爆料的视频+++"+response.body().toString());
                ParseMineIssueVideo(response.body());

            }
        });

    }

    private void ParseMineIssueVideo(String body) {
        final MineIssueVideoBean mineIssueVideoBean = GsonUtils.jsonFromJson(body, MineIssueVideoBean.class);

        if (mineIssueVideoBean.getCode()==0){
            final List<MineIssueVideoBean.DataBean> dataList = mineIssueVideoBean.getData();

            final MineIssueVideoAdapter mineIssueVideoAdapter = new MineIssueVideoAdapter(mActivity, dataList);
            recyclerMineIssueVideo.setAdapter(mineIssueVideoAdapter);
            mineIssueVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent=new Intent(mActivity, ProtocolActivity.class);
                    intent.putExtra("url",dataList.get(position).getVideo_url());
                    intent.putExtra("title",dataList.get(position).getTitle());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineIssueVideo.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
