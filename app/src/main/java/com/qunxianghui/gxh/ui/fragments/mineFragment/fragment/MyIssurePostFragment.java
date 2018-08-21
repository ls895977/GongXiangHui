package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssurePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.ReplyCommentResponseBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.ui.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.ui.dialog.CommentDialog;
import com.qunxianghui.gxh.ui.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;
import com.tencent.mm.opensdk.utils.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class MyIssurePostFragment extends BaseFragment implements MineIssurePostAdapter.CircleOnClickListener, View.OnClickListener {
    @BindView(R.id.recycler_mineissue_post)
    XRecyclerView recyclerMineissuePost;
    Unbinder unbinder;
    private int count = 0;
    private List<TestMode.DataBean.ListBean> dataList = new ArrayList<TestMode.DataBean.ListBean>();
    private boolean mIsFirst = true;
    private MineIssurePostAdapter mineIssurePostAdapter;
    private boolean mIsRefresh = false;
    private EditText IssuePostCommentEdit;
    private LinearLayout IssuePostCommentView;
    private TextView IssuePostCommentSend;
    private int commentPosition;
    private CommentDialog commentDialog;
    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_mine_issure;
    }
    @Override
    public void initData() {
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
                        Logger.d("我发布的帖子+++++" + response.body().toString());
                        parseIssuePostData(response.body());
                    }
                });
    }

    private void parseIssuePostData(String body) {
        final TestMode mineIssurePostBean = GsonUtils.jsonFromJson(body, TestMode.class);
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
                    mineIssurePostAdapter.setListener(this);
                    recyclerMineissuePost.setAdapter(mineIssurePostAdapter);
                }
                recyclerMineissuePost.refreshComplete();
                mineIssurePostAdapter.notifyItemRangeChanged(count, mineIssurePostBean.getData().getList().size());
            }
        } else {
            asyncShowToast("数据出错了  请重新加载");
        }
    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerMineissuePost.setLayoutManager(linearLayoutManager);
        IssuePostCommentEdit = view.findViewById(R.id.issuepost_comment_edit);         //底部输入框
        IssuePostCommentView = view.findViewById(R.id.issuepost_send_comment_view); //底部根布局
        IssuePostCommentSend = view.findViewById(R.id.issuepost_comment_to_send);  //底部提交
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        IssuePostCommentSend.setOnClickListener(this);
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

        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                if (tvContent!=null && commentDialog!=null){
                    int etTop = getLocationOnScreen(commentDialog.et_content);//dialog top值
                    int tvContentTop = getLocationOnScreen(tvContent);// textview top值
                    int scrollY = tvContentTop-etTop+tvContent.getHeight();
                    recyclerMineissuePost.smoothScrollBy(0,scrollY);
                }

            }
            @Override
            public void keyBoardHide(int height) {
                if (commentDialog != null) {
                    commentDialog.dismiss();
                }
            }
        });
    }

    private int getLocationOnScreen(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        return locations[1];
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

    /**
     * 点击了评论
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
                final int uuid = dataList.get(position).getUuid();
                if (dataList.get(position).getComment_res().size() <= 0) {
                    dataList.get(position).setComment_res(new ArrayList<CommentBean>());
                }
                List<CommentBean> commentBeanList = dataList.get(position).getComment_res();
                CommentBean comment = new CommentBean();
                UserUtil user = UserUtil.getInstance();
                comment.setContent(inputText);
                comment.setUuid(user.id);
                comment.setMember_name(user.mNick);
                commentBeanList.add(comment);
                mineIssurePostAdapter.notifyDataSetChanged();
                //  mAdapter.notifyItemChanged(position);
                OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                        .params("uuid", uuid)
                        .params("content", comment.getContent())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                ReplyCommentResponseBean responseBean = GsonUtils.jsonFromJson(response.body(), ReplyCommentResponseBean.class);
                                if (responseBean.getCode() == 0) {
                                    commentDialog.dismiss();
                                    asyncShowToast(responseBean.getMsg());
                                    recyclerMineissuePost.refresh();
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
        if (dataList.get(position).getClick_like() != null && dataList.get(position).getClick_like().toString().length() == 0) {
            if (dataList.get(position).getClick_like().size() <= 0) {
                dataList.get(position).setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
            }
            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = dataList.get(position).getClick_like();
            likeBeanList.add(like);
            mineIssurePostAdapter.notifyDataSetChanged();
            mineIssurePostAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = dataList.get(position).getClick_like();
                    likeBeanList.add(like);
                    mineIssurePostAdapter.notifyDataSetChanged();
                    mineIssurePostAdapter.notifyItemChanged(position);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    asyncShowToast("登陆账号异常");
                }
            });
        } else {
            OkGo.<CommonBean>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid())
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(Response<CommonBean> response) {
                            UserUtil user = UserUtil.getInstance();
                            TestMode.DataBean.ListBean.ClickLikeBean clickLikeBean = new TestMode.DataBean.ListBean.ClickLikeBean();
                            clickLikeBean.setMember_name(user.mNick);
                            if ("点赞成功".equals(response.body().msg)) {
                                dataList.get(position).getTem().add(clickLikeBean);
                                dataList.get(position).setLike_info_res("true");
                                mineIssurePostAdapter.notifyDataSetChanged();
                                mineIssurePostAdapter.notifyItemChanged(position);
                                asyncShowToast("点赞成功");
                            } else if ("取消点赞成功".equals(response.body().msg)) {
                                List<TestMode.DataBean.ListBean.ClickLikeBean> list = dataList.get(position).getTem();
                                for (int i = 0; i < dataList.get(position).getTem().size(); i++) {
                                    TestMode.DataBean.ListBean.ClickLikeBean tem = dataList.get(position).getTem().get(i);
                                    if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                        if (dataList.get(position).getClick_like().size() == 1 && dataList.get(position).getTem().size() == 1) {
                                            dataList.get(position).setClick_like("");
                                        }
                                        dataList.get(position).getTem().remove(tem);
                                        break;
                                    }
                                }
                                dataList.get(position).setLike_info_res("");
                                mineIssurePostAdapter.notifyDataSetChanged();
                                mineIssurePostAdapter.notifyItemChanged(position);
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
    public void onCollectionClick(int position) {
        toActivity(InFormActivity.class);

    }

    @Override
    public void headImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", dataList.get(position).getMember_id());
        startActivity(intent);

    }
    private  TextView tvContent;
    @Override
    public void commentRecall(final int position, final CommentBean commentBean, TextView tvContent) {
        this.tvContent = tvContent;
        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                OkGo.<String>post(Constant.REPAY_COMMENT_URL)
                        .params("comment_id", commentBean.getId())
                        .params("content", inputText)
                        .params("uuid", commentBean.getData_uuid())
                        .params("pid", commentBean.getPid())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                ReplyCommentResponseBean commentResponseBean = GsonUtils.jsonFromJson(response.body(), ReplyCommentResponseBean.class);
                                if (commentResponseBean.getCode() == 0) {
                                    commentDialog.dismiss();
                                    asyncShowToast(commentResponseBean.getMsg());
                                    List<CommentBean> commentBeanList = dataList.get(position).getComment_res();
                                    CommentBean comment = new CommentBean();
                                    ReplyCommentResponseBean.DataBean dataBean = commentResponseBean.getData();
                                    if (dataBean!=null){
                                        ReplyCommentResponseBean.DataBean.ComOneResBean comOneResBean = dataBean.getCom_one_res();
                                        if (comOneResBean!=null){
                                            comment.setContent(comOneResBean.getContent());
                                            comment.setUuid(comOneResBean.getData_uuid());
                                            comment.setMember_name(comOneResBean.getMember_name());
                                            commentBeanList.add(comment);
                                            mineIssurePostAdapter.notifyDataSetChanged();
                                            OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                                                    .params("uuid", commentBean.getUuid())
                                                    .params("content", comOneResBean.getContent())
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onSuccess(Response<String> response) {
                                                            ReplyCommentResponseBean responseBean = GsonUtils.jsonFromJson(response.body(), ReplyCommentResponseBean.class);
                                                            if (responseBean.getCode() == 0) {
                                                                commentDialog.dismiss();
                                                                asyncShowToast(responseBean.getMsg());
                                                                recyclerMineissuePost.refresh();
                                                            } else {
                                                                asyncShowToast(response.message());
                                                            }
                                                        }
                                                    });

                                        }
                                    }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
