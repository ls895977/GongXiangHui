package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectPostFrament extends BaseFragment implements MineCollectPostAdapter.MycollectPostListener {

    @BindView(R.id.recycler_mycollect_post)
    XRecyclerView recyclerMycollectPost;

    Unbinder unbinder;
    private List<MineCollectPostBean.DataBean> dataList = new ArrayList<>();
    private boolean mIsRefresh = false;
    private boolean mIsFirst = true;
    private int count = 0;
    private MineCollectPostAdapter mineCollectPostAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.mine_collect_post;
    }

    @Override
    public void initDatas() {
        RequestMyCollectPost();
    }

    private void RequestMyCollectPost() {
        OkGo.<String>post(Constant.GET_COLLECT_POST_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseCollectPostDaTA(response.body());
                    }
                });
    }

    private void parseCollectPostDaTA(String body) {
        Logger.d("我收藏帖子的内容+++" + body.toString());
        final MineCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MineCollectPostBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myCollectPostBean.getData());
        count = dataList.size();
        if (myCollectPostBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mineCollectPostAdapter = new MineCollectPostAdapter(mActivity, dataList);
                recyclerMycollectPost.setAdapter(mineCollectPostAdapter);
                mineCollectPostAdapter.setMycollectPostListener(this);
                asyncShowToast("请求成功");
            }
            recyclerMycollectPost.refreshComplete();
            mineCollectPostAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMycollectPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMycollectPost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                RequestMyCollectPost();
            }

            @Override
            public void onLoadMore() {
                RequestMyCollectPost();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void cancelCollect(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条消息吗?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CancelCollectPostData(position);

            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }


    @Override
    public void onPicClick(int position, int picpostion) {
        List<String> imageList = dataList.get(position).getImages();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String data : imageList) {
            arrayList.add(data);
        }
        Intent intent = new Intent(getActivity(), PhotoBrowserActivity.class);
        intent.putStringArrayListExtra("url", arrayList);
        intent.putExtra("position", picpostion);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_pop_in, R.anim.pop_out);

    }

    private void CancelCollectPostData(final int position) {
        Toast.makeText(mActivity, "取消成功", Toast.LENGTH_SHORT).show();
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", dataList.get(position).getData_uuid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        Toast.makeText(mActivity, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        com.orhanobut.logger.Logger.e("取消收藏+" + response.body().toString());
                        dataList.remove(position);
                        mineCollectPostAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                        Toast.makeText(mActivity, "取消收藏失败", Toast.LENGTH_SHORT).show();


                    }
                });
    }
}
