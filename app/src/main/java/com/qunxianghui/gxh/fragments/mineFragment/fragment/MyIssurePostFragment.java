package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
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
    private List<MineIssurePostBean.DataBean.ListBean> dataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private MineIssurePostAdapter mineIssurePostAdapter;
    private boolean mIsRefresh = false;
    private int commentPosition;
    private EditText IssuePostCommentEdit;
    private LinearLayout IssuePostCommentView;
    private TextView IssuePostCommentSend;
    private int scrollOffsetY = 0;

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
        SoftKeyView();
    }

    /**
     * 软键盘的顶起
     */
    private void SoftKeyView() {
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Logger.i("xxx-yyy jump :" + commentPosition);
                //View item = recyclerView.getChildAt(commentPosition + 1);
                View item = recyclerMineissuePost.getLayoutManager().findViewByPosition(commentPosition + 1);
                int offset = 5;
                int keyboardoffset = 80;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) IssuePostCommentView.getLayoutParams();
                layoutParams.bottomMargin = height - offset;
                IssuePostCommentView.setLayoutParams(layoutParams);

                if (item != null) {
                    int[] location = new int[2];
                    item.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    Logger.v("xxx-yyy item " + item);
                    Logger.v("xxx-yyy item height :", item.getMeasuredHeight());
                    Logger.v("xxx-yyy y :" + y);
                    recyclerMineissuePost.scrollBy(0, (y + item.getMeasuredHeight()) - (recyclerMineissuePost.getMeasuredHeight() - height) + keyboardoffset);
                } else {
                    Logger.i("xxx-yyy" + " item is null");
                }
            }

            @Override
            public void keyBoardHide(int height) {
                IssuePostCommentView.setVisibility(View.INVISIBLE);
//                Toast.makeText(getActivity(), "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
//                ViewGroup.LayoutParams layout = mRootView.getLayoutParams();
//                layout.height = layout.height + height;
//                mRootView.setLayoutParams(layout);
            }
        });

        recyclerMineissuePost.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0:
                        System.out.println("recyclerview已经停止滚动");
                        break;
                    case 1:
                        if (getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                                || getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    hideSoftKeyboard(IssuePostCommentEdit, getActivity());
                                }
                            }, 10);
                        }
                        break;
                    case 2:
                        //System.out.println("recyclerview正在依靠惯性滚动");
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollOffsetY = scrollOffsetY + dy;
            }
        });
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

    /**
     * 点击了评论
     *
     * @param position
     * @param content
     */
    @Override
    public void onCommentClick(final int position, String content) {
        commentPosition = position;
        IssuePostCommentView.setVisibility(View.VISIBLE);
        IssuePostCommentEdit.setFocusable(true);
        IssuePostCommentEdit.setFocusableInTouchMode(true);
        IssuePostCommentEdit.requestFocus();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        IssuePostCommentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*数据的提交*/

                String IssuePostCommentContent = IssuePostCommentEdit.getText().toString().trim();
                if (TextUtils.isEmpty(IssuePostCommentContent)) {
                    asyncShowToast("请输入评论的内容");
                } else {
                    int uuid = dataList.get(position).getUuid();
                    if (dataList.get(position).getComment_res().size() <= 0) {
                        dataList.get(position).setComment_res(new ArrayList<CommentBean>());

                    }
                    List<CommentBean> commentBeanList = (List<CommentBean>) dataList.get(position).getComment_res();
                    CommentBean comment = new CommentBean();
                    UserUtil user = UserUtil.getInstance();
                    comment.setContent(IssuePostCommentEdit.getText().toString());
                    comment.setUuid(user.id);
                    comment.setMember_name(user.mNick);
                    commentBeanList.add(comment);
                    mineIssurePostAdapter.notifyDataSetChanged();
                    mineIssurePostAdapter.notifyItemChanged(position);
                    OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                            .params("uuid", uuid)
                            .params("content", IssuePostCommentEdit.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                    if (comment.getCode() == 0) {
                                        IssuePostCommentEdit.setText("");
                                        hideSoftKeyboard(IssuePostCommentEdit, getActivity());
                                    }
                                }
                            });
                }

            }
        });
    }

    private void hideSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

    }

    private void parseDeletePostAData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
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
