package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectPostFrament extends BaseFragment {
    @BindView(R.id.recycler_mycollect_post)
    RecyclerView recyclerMycollectPost;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.mine_collect_post;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.MY_ISSURE_POST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseCollectPostDaTA(response.body());
            }
        });

    }

    private void parseCollectPostDaTA(String body) {
        Logger.d("我收藏帖子的内容+++"+body.toString());
//        final MyCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MyCollectPostBean.class);
//        if (myCollectPostBean.getCode()==0){
//            final List<MyCollectPostBean.DataBean> dataList = myCollectPostBean.getData();
//            final MineCollectPostAdapter mineCollectPostAdapter = new MineCollectPostAdapter(mActivity, dataList);
//            recyclerMycollectPost.setAdapter(mineCollectPostAdapter);
//asyncShowToast("请求成功");
//        }
    }

    @Override
    public void initViews(View view) {
        recyclerMycollectPost.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
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
