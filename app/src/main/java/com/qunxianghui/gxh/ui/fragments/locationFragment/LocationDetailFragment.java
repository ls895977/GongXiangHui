package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.ui.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.ui.activity.PublishActivity;
import com.qunxianghui.gxh.ui.dialog.CommentDialog;
import com.qunxianghui.gxh.ui.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LocationDetailFragment extends BaseFragment implements View.OnClickListener, NineGridTest2Adapter.CircleOnClickListener {

    @BindView(R.id.recyclerView_location)
    XRecyclerView recyclerView;
    private List<TestMode.DataBean.ListBean> localDataList = new ArrayList<>();
    private int commentPosition;
    private int scrollOffsetY = 0;
    private int count = 0;
    NineGridTest2Adapter mAdapter;
    private CommentDialog commentDialog;
    private int mCateId;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_location;
    }

    @Override
    public void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new NineGridTest2Adapter(mActivity, localDataList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mCateId = getArguments().getInt("channel_id");
        RequestLocalServiceData();
    }

    private void RequestLocalServiceData() {
        OkGo.<String>get(Constant.LOCATION_NEWS_LIST_URL)
                .params("cate_id", mCateId)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(count == 0){
                            localDataList.clear();
                        }
                        TestMode locationListBean = GsonUtils.jsonFromJson(response.body(), TestMode.class);
                        localDataList.addAll(locationListBean.getData().getList());
                        count = localDataList.size();
                        if (locationListBean.getCode() == 0) {
                            recyclerView.refreshComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    @Override
    protected void initListeners() {
        mAdapter.setListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });

        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                // Log.i("fanbo", height + "    " + 1);
//                commentPosition+=1;//头部是下拉刷新，所以需要加1
//                Logger.i("xxx-yyy jump :" + commentPosition);

//                if (item != null) {
//                    recyclerView.smoothScrollBy(0, item.getBottom()-commentView.getTop());
//                } else {
//                    Logger.i("xxx-yyy" + " item is null");
//                }
//                TaskUtil.getInstance().runOnMainThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        keyShow = true;
//                        scrollOffsetY = 0;
//                    }
//                },500);
            }

            @Override
            public void keyBoardHide(int height) {
                if (commentDialog != null) {
                    commentDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alertbottom_up_pic:
                toActivity(PublishActivity.class);
                break;
        }
    }

    @Override
    public void onPicClick(int position, int picpostion) {
        List<String> imageList = localDataList.get(position).getImages();
        ArrayList<String> arrayList = new ArrayList<>(imageList);
        Intent intent = new Intent(getActivity(), PhotoBrowserActivity.class);
        intent.putStringArrayListExtra("url", arrayList);
        intent.putExtra("position", picpostion);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_pop_in, R.anim.pop_out);
    }
    
    /**
     * 评论的点击
     *
     * @param position
     * @param content
     */
    @Override
    public void onCommentClick(final int position, String content) {
        Log.v("xxx-yyy", position + "");
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        commentPosition = position;
        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                final int uuid = localDataList.get(position).getUuid();
                if (localDataList.get(position).getComment_res().size() <= 0) {
                    localDataList.get(position).setComment_res(new ArrayList<CommentBean>());
                }
                List<CommentBean> commentBeanList = localDataList.get(position).getComment_res();
                CommentBean comment = new CommentBean();
                UserUtil user = UserUtil.getInstance();
                comment.setContent(inputText);
                comment.setUuid(user.id);
                comment.setMember_name(user.mNick);
                commentBeanList.add(comment);
                mAdapter.notifyDataSetChanged();
              //  mAdapter.notifyItemChanged(position);
                OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                        .params("uuid", uuid)
                        .params("content", comment.getContent())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                if (comment.getCode() == 0) {
                                    commentDialog.dismiss();
                                } else {
                                    asyncShowToast(response.message());
                                }
                            }
                        });
            }
        });
        commentDialog.show(getChildFragmentManager(), "comment");
    }


    //接口回调之 点赞
    @Override
    public void onPraiseClick(final int position) {
        Log.i("fanbo",position+"1");
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        if (localDataList.get(position).getClick_like() != null && localDataList.get(position).getClick_like().toString().length() == 0) {
            if (localDataList.get(position).getClick_like().size() <= 0) {
                localDataList.get(position).setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
            }
            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = localDataList.get(position).getClick_like();
            likeBeanList.add(like);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", localDataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = localDataList.get(position).getClick_like();
                    likeBeanList.add(like);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.notifyItemChanged(position);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    asyncShowToast("登陆账号异常");
                }
            });
        } else {
            OkGo.<CommonBean>post(Constant.LIKE_URL)
                    .params("data_uuid", localDataList.get(position).getUuid())
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(Response<CommonBean> response) {
                            UserUtil user = UserUtil.getInstance();
                            TestMode.DataBean.ListBean.ClickLikeBean clickLikeBean = new TestMode.DataBean.ListBean.ClickLikeBean();
                            clickLikeBean.setMember_name(user.mNick);
                            if ("点赞成功".equals(response.body().message)) {
                                localDataList.get(position).getTem().add(clickLikeBean);
                                localDataList.get(position).setLike_info_res("true");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("点赞成功");
                            } else if ("取消点赞成功".equals(response.body().message)) {
                                List<TestMode.DataBean.ListBean.ClickLikeBean> list = localDataList.get(position).getTem();
                                for (int i = 0; i < localDataList.get(position).getTem().size(); i++) {
                                    TestMode.DataBean.ListBean.ClickLikeBean tem = localDataList.get(position).getTem().get(i);
                                    if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                        if (localDataList.get(position).getClick_like().size() == 1 && localDataList.get(position).getTem().size() == 1) {
                                            localDataList.get(position).setClick_like("");
                                        }
                                        localDataList.get(position).getTem().remove(tem);
                                        break;
                                    }
                                }
                                localDataList.get(position).setLike_info_res("");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("取消点赞");
                            }
                        }

                        @Override
                        public void onError(Response<CommonBean> response) {
                            super.onError(response);
                            asyncShowToast("登陆账号异常");
                        }
                    });
        }
    }

    @Override
    public void onCollectionClick(final int position) {
        toActivity(InFormActivity.class);
    }

    /**
     * 头像点击跳转用户详情页
     *
     * @param position
     */
    @Override
    public void headImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", localDataList.get(position).getMember_id());
        startActivity(intent);
    }

    @Override
    public void commentRecall(final int position, final CommentBean commentBean) {
        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                OkGo.<String>post(Constant.REPAY_COMMENT_URL).params("comment_id", commentBean.getComment_id())
                        .params("content", inputText)
                        .params("uuid", commentBean.getUuid())
                        .params("pid", commentBean.getPid())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                if (comment.getCode() == 0) {
                                    commentDialog.dismiss();
                                } else {
                                    asyncShowToast(response.message());
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                asyncShowToast(response.message());
                            }
                        });
            }
        });
        commentDialog.show(getChildFragmentManager(), "comment");
    }
}
