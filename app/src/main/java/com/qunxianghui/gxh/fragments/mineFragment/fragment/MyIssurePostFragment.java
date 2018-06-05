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
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssurePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MyIssurePostFragment extends BaseFragment {
    @BindView(R.id.recycler_mineissue_post)
    RecyclerView recyclerMineissuePost;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issure;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.GET_ISSURE_POST_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.e("我发布的帖子+++++" + response.body().toString());

                        parseIssuePostData(response.body());


                    }
                });

    }

    private void parseIssuePostData(String body) {

        final MineIssurePostBean mineIssurePostBean = GsonUtils.jsonFromJson(body, MineIssurePostBean.class);

        if (mineIssurePostBean.getCode() == 0) {
            final List<MineIssurePostBean.DataBean.ListBean> dataList = mineIssurePostBean.getData().getList();
            for (int i=0;i<dataList.size();i++){
                final MineIssurePostBean.DataBean.ListBean listBean = dataList.get(i);
                if (listBean.getClick_like().size()>0){
                    final MineIssurePostAdapter mineIssurePostAdapter = new MineIssurePostAdapter(mActivity, dataList);
                    recyclerMineissuePost.setAdapter(mineIssurePostAdapter);
                }

            }


        }else {
            asyncShowToast("数据出错了  请重新加载");
        }

    }

    @Override
    public void initViews(View view) {
        recyclerMineissuePost.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));

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
