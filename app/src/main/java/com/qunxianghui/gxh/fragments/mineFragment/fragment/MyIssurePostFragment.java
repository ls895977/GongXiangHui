package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
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
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
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
public class MyIssurePostFragment extends BaseFragment implements MineIssurePostAdapter.MyPostOnClickListener, View.OnClickListener {
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
    private EditText inputPostComment;
    private TextView tv_mypost_submit;
    private PopupWindow myIssuepostPopWindow;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
                        Logger.d("我发布的帖子+++++" + response.body().toString());
                        parseIssuePostData(response.body());
                    }
                });
    }

    private void parseIssuePostData(String body) {
        final TestMode mineIssurePostBean= GsonUtils.jsonFromJson(body, TestMode.class);
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
                mineIssurePostAdapter.notifyItemRangeChanged(count, mineIssurePostBean.getData().getList().size());
            }
        } else {
            asyncShowToast("数据出错了  请重新加载");
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineissuePost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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
                    dataList.get(position - 1).setCollect("true");
                } else if (myCollectBean.getCode() == 202) {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                    dataList.get(position - 1).setCollect("");
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
    public void onLaunLikeClick(final int position) {
        if (!LoginMsgHelper.isLogin(getContext())) {
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

                    //Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                    //Handler haner = new Handler()
                }
            });
        } else {
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    //TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    if (like.getMessage().equalsIgnoreCase("点赞成功")) {
                        dataList.get(position).getTem().add(like);
                        dataList.get(position).setLike_info_res("true");
                        mineIssurePostAdapter.notifyDataSetChanged();
                        mineIssurePostAdapter.notifyItemChanged(position);
                        asyncShowToast("点赞成功");
                    } else if (like.getMessage().equalsIgnoreCase("取消点赞成功")) {
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
                        asyncShowToast("取消点赞成功");
                    }
                }
            });
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
    public void deletePost(final int position) {
        OkGo.<String>post(Constant.DELETE_POST_URL)
                .params("uuid", dataList.get(position).getUuid())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseDeletePostAData(response.body(), position);

                    }
                });
    }

    /**
     * 点击了评论
     *
     * @param position
     * @param content
     */
    @Override
    public void onCommentClick(final int position, String content) {
        showMyIssuePopupWindow(position, content);

    }

    @SuppressLint("WrongConstant")
    private void showMyIssuePopupWindow(final int position, String content) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.comment_popupwindow, null);
        inputPostComment = view.findViewById(R.id.et_discuss);
        tv_mypost_submit = view.findViewById(R.id.tv_confirm);
        myIssuepostPopWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, false);
        myIssuepostPopWindow.setTouchable(true);
        myIssuepostPopWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    myIssuepostPopWindow.dismiss();
                }
                return false;
            }
        });
        myIssuepostPopWindow.setFocusable(true);
        //设置点击窗口外边窗口消失
        myIssuepostPopWindow.setOutsideTouchable(true);
        //设置弹出窗体时需要软键盘
        myIssuepostPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        myIssuepostPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ColorDrawable cd = new ColorDrawable(0x000000);
        myIssuepostPopWindow.setBackgroundDrawable(cd);
        myIssuepostPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        myIssuepostPopWindow.update();

        tv_mypost_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MyissuePostComment = inputPostComment.getText().toString().trim();
                if (TextUtils.isEmpty(MyissuePostComment)) {
                    asyncShowToast("请输入评论内容");
                } else {
                    int uuid = dataList.get(position).getUuid();
                    if (dataList.get(position).getComment_res().size() <= 0) {
                        dataList.get(position).setComment_res(new ArrayList<CommentBean>());
                    }
                    List<CommentBean> commentBeanList = (List<CommentBean>) dataList.get(position).getComment_res();
                    CommentBean comment = new CommentBean();
                    UserUtil user = UserUtil.getInstance();
                    comment.setContent(MyissuePostComment);
                    comment.setUuid(user.id);
                    comment.setMember_name(user.mNick);
                    commentBeanList.add(comment);
                    mineIssurePostAdapter.notifyDataSetChanged();
                    mineIssurePostAdapter.notifyItemChanged(position);
                    OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                            .params("uuid", uuid)
                            .params("content", MyissuePostComment)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                    if (comment.getCode() == 0) {
                                        inputPostComment.setText("");
                                        myIssuepostPopWindow.dismiss();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void parseDeletePostAData(String body, int position) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                dataList.remove(position);
                mineIssurePostAdapter.notifyDataSetChanged();
                asyncShowToast("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
