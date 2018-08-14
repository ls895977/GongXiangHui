package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.CollectBean;
import com.qunxianghui.gxh.bean.mine.MyCollectNewsDetailBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.observer.EventObserver;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment implements MyCollectPostAdapter.CollectOnClickListener, View.OnClickListener {
    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    @BindView(R.id.bt_mycollect_delete)
    Button btMycollectDelete;
    Unbinder unbinder;
    private List<CollectBean.DataBean> data;
    private MyCollectPostAdapter myCollectPostAdapter;
    private List<MyCollectPostBean.DataBean> dataList = new ArrayList<>();
    private final HashMap<Integer, Boolean> map = new HashMap<>();
    private Handler handler = new Handler();
    private boolean mIsFirst = true;
    private int count;
    private boolean mIsRefresh = false;
    private int mMemberId;
    EditEvent ee = new EditEvent();
    private View view;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initData() {
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
        MyCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MyCollectPostBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(myCollectPostBean.getData());
        count = dataList.size();
        if (myCollectPostBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                myCollectPostAdapter = new MyCollectPostAdapter(mActivity, dataList);
                myCollectPostAdapter.setCollectOnClickListener(this);
                xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);
                myCollectPostAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {

                    private int data_uuid;

                    @Override
                    public void onItemClick(View v, int position) {
                        data_uuid = dataList.get(position - 1).getData_uuid();
                        SkipMycollectNewsDetail(data_uuid);
                    }
                });
            }
            xrecycler_mine_collect_news.refreshComplete();
            myCollectPostAdapter.notifyDataSetChanged();
            myCollectPostAdapter.notifyItemRangeChanged(count, myCollectPostBean.getData().size());
        }
    }

    /**
     * 跳转新闻详情页
     *
     * @param data_uuid
     */
    private void SkipMycollectNewsDetail(int data_uuid) {
        OkGo.<String>post(Constant.GET_NEWS_CONTENT_DETAIL_URL).params("id", data_uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ParseMyCollectNewsDetail(response.body());
                    }
                });
    }

    /**
     * 解析我的收藏资讯新闻
     *
     * @param body
     */
    private void ParseMyCollectNewsDetail(String body) {
        MyCollectNewsDetailBean myCollectNewsDetailBean = GsonUtils.jsonFromJson(body, MyCollectNewsDetailBean.class);
        int code = myCollectNewsDetailBean.getCode();
        if (code == 200) {
            String url = myCollectNewsDetailBean.getData().getUrl();
            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(ee);
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecycler_mine_collect_news.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
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
        EventManager.getInstance().deleteObserver(ee);
    }

    @Override
    public void cancelNewsCollect(final int position) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setTitle("删除提示");
//        builder.setMessage("您确定要删除该条消息吗?");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                CancelNewsData(position);
//
//            }
//        });
//        builder.setNeutralButton("取消", null);
//        builder.show();
}
    /*checkbox选择框的选择*/

//    /**
//     * 取消收藏
//     */
//    private void CancelNewsData(final int position) {
//        OkGo.<String>post(Constant.ADD_COLLECT_URL)
//                .params("uuid", dataList.get(position).getInfo().getUuid())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(final Response<String> response) {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(mActivity, "取消收藏成功", Toast.LENGTH_SHORT).show();
//                                Logger.e("取消收藏+" + response.body().toString());
//                                dataList.remove(position);
//                                myCollectPostAdapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Toast.makeText(mActivity, "取消收藏失败", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }

    @OnClick(R.id.bt_mycollect_delete)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_mycollect_delete:
                for (MyCollectPostBean.DataBean dataBean : dataList) {
                    if (dataBean.isChecked()) {

                        deleteCollected(dataBean);
                    }


                }

                break;
        }
    }

    private void deleteCollected(final MyCollectPostBean.DataBean dataBean) {
        OkGo.<String>post(Constant.CANCEL_COLLECT_URL).params("uuid",
                dataBean.getInfo().getUuid())
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        dataList.remove(dataBean);
                        myCollectPostAdapter.notifyDataSetChanged();
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
            myCollectPostAdapter.setIsCheckBoxVisible((boolean) object);
            myCollectPostAdapter.notifyDataSetChanged();
        }
    }

}
