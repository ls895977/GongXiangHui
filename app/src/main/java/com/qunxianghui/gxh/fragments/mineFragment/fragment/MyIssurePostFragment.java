package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssurePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MyIssurePostFragment extends BaseFragment implements MineIssurePostAdapter.MyPostOnClickListener {
    @BindView(R.id.recycler_mineissue_post)
    XRecyclerView recyclerMineissuePost;
    Unbinder unbinder;
    private List<MineIssurePostBean.DataBean.ListBean> dataList;

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
            dataList = mineIssurePostBean.getData().getList();
            for (int i = 0; i< dataList.size(); i++){
           MineIssurePostBean.DataBean.ListBean listBean = dataList.get(i);
                if (listBean.getClick_like().size()>0){
                    final MineIssurePostAdapter mineIssurePostAdapter = new MineIssurePostAdapter(mActivity, dataList);
                    mineIssurePostAdapter.setPostOnClickListener(this);
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
    protected void initListeners() {
        super.initListeners();
        recyclerMineissuePost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyclerMineissuePost.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                recyclerMineissuePost.refreshComplete();
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
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 收藏
     * @param position
     */
    @Override
    public void onCollectionItemClick(int position) {
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>((getActivity())) {
            @Override
            public void onSuccess(Response<String> response) {
                MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                if (myCollectBean.getCode() == 0) {
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                }else if (myCollectBean.getCode() == 202) {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 点赞
     * @param position
     */
    @Override
    public void onLaunLikeClick(int position) {
        if (dataList.get(position).getClick_like() != null && dataList.get(position).getClick_like().toString().length() ==0 ){
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                }
            });
        }else {
            OkGo.<String>post(Constant.UNLIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
