package com.qunxianghui.gxh.fragments.mineFragment.fragment;

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
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssurePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;

import org.json.JSONObject;

import java.util.ArrayList;
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
    private int count = 0;
    private List<MineIssurePostBean.DataBean.ListBean> dataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private MineIssurePostAdapter mineIssurePostAdapter;
    private boolean mIsRefresh = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issure;
    }

    @Override
    public void initDatas() {
        RequestMyIssurePost();
    }

    /**
     * 网络请求我发布的帖子
     */
    private void RequestMyIssurePost() {
        OkGo.<String>post(Constant.GET_ISSURE_POST_URL)
                .params("limit", 10)
                .params("skip", count)
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
            if (mIsRefresh) {
                mIsRefresh = false;
                dataList.clear();
            }
            dataList.addAll(mineIssurePostBean.getData().getList());

            count = dataList.size();
            if (mineIssurePostBean.getCode() == 0) {
                if (mIsFirst) {
                    mIsFirst = false;
                    mineIssurePostAdapter = new MineIssurePostAdapter(mActivity, dataList);
                    mineIssurePostAdapter.setPostOnClickListener(this);
                    recyclerMineissuePost.setAdapter(mineIssurePostAdapter);
                }
                recyclerMineissuePost.refreshComplete();
                mineIssurePostAdapter.notifyDataSetChanged();
                mineIssurePostAdapter.notifyItemRangeChanged(count, mineIssurePostBean.getData().getList().size());
            }

        } else {
            asyncShowToast("数据出错了  请重新加载");
        }

    }

    @Override
    public void initViews(View view) {
        recyclerMineissuePost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        recyclerMineissuePost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                RequestMyIssurePost();
            }

            @Override
            public void onLoadMore() {
                RequestMyIssurePost();
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
     *
     * @param position
     */
    @Override
    public void onCollectionItemClick(final int position) {
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", dataList.get(position - 1).getUuid()).execute(new DialogCallback<String>((getActivity())) {
            @Override
            public void onSuccess(Response<String> response) {
                MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                if (myCollectBean.getCode() == 0) {
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                    dataList.get(position).setCollect("true");
                } else if (myCollectBean.getCode() == 202) {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                    dataList.get(position).setCollect("");
                }
                mineIssurePostAdapter.notifyDataSetChanged();
                mineIssurePostAdapter.notifyItemChanged(position);
            }
        });

    }

    /**
     * 点赞
     *
     * @param position
     */
    @Override
    public void onLaunLikeClick(int position) {
        if (dataList.get(position).getClick_like() != null && dataList.get(position).getClick_like().toString().length() == 0) {
            if (dataList.get(position).getClick_like().size() <= 0) {
                dataList.get(position).setClick_like(new ArrayList<MineIssurePostBean.DataBean.ListBean.ClickLikeBean>());
            }

            MineIssurePostBean.DataBean.ListBean.ClickLikeBean like = new MineIssurePostBean.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<MineIssurePostBean.DataBean.ListBean.ClickLikeBean> likeBeanList = dataList.get(position).getClick_like();
            likeBeanList.add(like);
            mineIssurePostAdapter.notifyDataSetChanged();
            mineIssurePostAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    MineIssurePostBean.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), MineIssurePostBean.DataBean.ListBean.ClickLikeBean.class);
                    Toast.makeText(getActivity(), response.body(), Toast.LENGTH_LONG).show();
                }
            });
        } else {

        }

    }

    /* 图片点击*/
    @Override
    public void onPicClick(int position, int picpostion) {
        final List<String> imageList = (List<String>) dataList.get(position).getImages();
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

    /*删除帖子*/
    @Override
    public void deletePost(int position) {

        OkGo.<String>post(Constant.DELETE_POST_URL)
                .params("uuid", dataList.get(position).getUuid())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseDeletePostAData(response.body());

                    }
                });

    }

    private void parseDeletePostAData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code==0){
                asyncShowToast("删除成功");



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
