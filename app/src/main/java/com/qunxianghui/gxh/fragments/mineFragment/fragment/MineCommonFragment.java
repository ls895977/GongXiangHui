package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
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
import com.qunxianghui.gxh.adapter.mineAdapter.MyCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.CollectBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment implements MyCollectPostAdapter.CollectOnClickListener {


    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    Unbinder unbinder;
    private List<CollectBean.ListBean> data;
    private MyCollectPostAdapter myCollectPostAdapter;
    private List<MyCollectPostBean.DataBean> dataList = new ArrayList<>();

    private Handler handler = new Handler();
    private int data_uuid;
    private boolean mIsFirst = true;
    private int count;
    private int mMemberId;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initDatas() {
        if (getArguments() != null) {
            mMemberId = getArguments().getInt("member_id");
        }
        LoadMycolectNews();
    }

    private void LoadMycolectNews() {
        OkGo.<String>post(Constant.GET_COLLECT_NEWS_URL)
                .params("limit", 5)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseCollectPostData(response.body());
                    }
                });
    }

    /**
     * 解析我的收藏列表
     *
     * @param body
     */
    private void parseCollectPostData(String body) {
        final MyCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MyCollectPostBean.class);
        dataList.addAll(myCollectPostBean.getData());
        count=dataList.size();
        for (int i = 0; i < dataList.size(); i++) {
            data_uuid = dataList.get(i).getData_uuid();
        }
        if (myCollectPostBean.getCode() == 0) {
            if (mIsFirst) {
                    mIsFirst = false;
                    myCollectPostAdapter = new MyCollectPostAdapter(mActivity, dataList);
                    myCollectPostAdapter.setCollectOnClickListener(this);
                    xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);

            }
                xrecycler_mine_collect_news.refreshComplete();
                myCollectPostAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        asyncShowToast("这里实现跳转详情的动作 目前没有字段不会跳");

                    }
                });
                myCollectPostAdapter.notifyItemRangeChanged(count,myCollectPostBean.getData().size());

        }
    }


    @Override
    public void initViews(View view) {
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecycler_mine_collect_news.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                dataList.clear();
                count = 0;
                LoadMycolectNews();


            }

            @Override
            public void onLoadMore() {

                LoadMycolectNews();

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

    @Override
    public void cancelNewsCollect(int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条消息吗?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CancelNewsData();

            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /**
     * 取消收藏
     */
    private void CancelNewsData() {

        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", data_uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mActivity, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                com.orhanobut.logger.Logger.e("取消收藏+" + response.body().toString());
                            }
                        });
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                        Toast.makeText(mActivity, "取消收藏失败", Toast.LENGTH_SHORT).show();


                    }
                });
    }


}
