package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
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
import com.qunxianghui.gxh.ui.activity.PublishActivity;
import com.qunxianghui.gxh.ui.dialog.CommentDialog;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LocationDetailFragment extends BaseFragment implements View.OnClickListener, NineGridTest2Adapter.CircleOnClickListener {

    @BindView(R.id.recyclerView_location)
    XRecyclerView mRecyclerView;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    Unbinder unbinder;
    private List<TestMode.DataBean.ListBean> locationBean = new ArrayList<>();
    private int mSkip = 0;
    private NineGridTest2Adapter mAdapter;
    private CommentDialog commentDialog;
    private int mCateId;
    private Dialog mShareDialog;
    private TextView mTv_inform_harass;
    private TextView mTv_inform_tort;
    private TextView mTv_inform_sex;
    private int mUuid;
    public int mMemberId;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_location;
    }

    @Override
    public void initViews(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new NineGridTest2Adapter(mActivity, locationBean);
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        if (getArguments() != null)
            mCateId = getArguments().getInt("channel_id");
        requestLocalServiceData();


    }

    @Override
    public void onStart() {
        super.onStart();
        mSkip = 0;
        requestLocalServiceData();
    }

    private void requestLocalServiceData() {
        if (mMemberId != 0) {
            OkGo.<TestMode>post(Constant.LOCATION_NEWS_LIST_URL)
                    .params("user_id", mMemberId)
                    .execute(new JsonCallback<TestMode>() {
                        @Override
                        public void onSuccess(Response<TestMode> response) {
                            parseData(response.body());
                        }
                    });
            return;
        }
        OkGo.<TestMode>get(Constant.LOCATION_NEWS_LIST_URL)
                .params("cate_id", mCateId)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<TestMode>() {
                    @SuppressLint("UseSparseArrays")
                    @Override
                    public void onSuccess(Response<TestMode> response) {
                        parseData(response.body());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCateId == 0 && LocationActivity.sLocationCanChange) {
            LocationActivity.sLocationCanChange = false;
            mSkip = 0;
            initData();
        }
    }

    @SuppressLint("UseSparseArrays")
    private void parseData(TestMode testMode) {
        if (testMode.getCode() == 0) {
            if (mSkip == 0) {
                locationBean.clear();
                mAdapter.mTextStateList = new SparseArray<>();
                mRecyclerView.refreshComplete();
                mRecyclerView.setLoadingMoreEnabled(true);
            }
            llEmpty.setVisibility(View.GONE);
            List<TestMode.DataBean.ListBean> list = testMode.getData().getList();
            locationBean.addAll(list);
            if (list.isEmpty()){
                llEmpty.setVisibility(View.VISIBLE);
            }
            if (list.size() < 10) {
                mRecyclerView.setLoadingMoreEnabled(false);
            }
        } else {
            asyncShowToast(testMode.getMessage());
            mRecyclerView.setLoadingMoreEnabled(false);
        }
        if (mRecyclerView.getEmptyView() == null)
            mRecyclerView.setEmptyView(LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, mRecyclerView, false));
        mRecyclerView.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListeners() {
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                requestLocalServiceData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                requestLocalServiceData();
            }
        });

        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                if (clickContent != null && commentDialog != null) {
                    int etTop = getLocationOnScreen(commentDialog.et_content);//dialog top值
                    int tvContentTop = getLocationOnScreen(clickContent);// textview top值
                    int scrollY = tvContentTop - etTop + clickContent.getHeight();
                    mRecyclerView.smoothScrollBy(0, scrollY);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alertbottom_up_pic:
                toActivity(PublishActivity.class);
                break;
            case R.id.tv_inform_sex:
                String TvInformSex = mTv_inform_sex.getText().toString().trim();
                RestInformData(TvInformSex);
                break;
            case R.id.tv_inform_harass:
                String TvInformHarass = mTv_inform_harass.getText().toString().trim();
                RestInformData(TvInformHarass);
                break;
            case R.id.tv_inform_tort:
                String TvInformTort = mTv_inform_tort.getText().toString().trim();
                RestInformData(TvInformTort);
                break;
            case R.id.tv_bottom_ainform_cancle:
                mShareDialog.dismiss();
                break;
        }
    }

    /*举报信息*/
    private void RestInformData(String informData) {
        OkGo.<CommonBean>post(Constant.ADD_REPORT_URL)
                .params("content", informData)
                .params("model", "Posts")
                .params("data_uuid", mUuid)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        String msg = response.body().message;
                        if (code == 200) {
                            asyncShowToast("举报成功");
                            mShareDialog.dismiss();
                        } else {
                            asyncShowToast(msg);
                        }
                    }
                });
    }

    @Override
    public void onPicClick(int position, int picpostion) {
        List<String> imageList = locationBean.get(position).getImages();
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
    public void onCommentClick(final int position, String content, View itemView) {
        this.clickContent = itemView;
        Log.v("xxx-yyy", position + "");
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {

            @Override
            public void sendComment(String inputText) {
                final int uuid = locationBean.get(position).getUuid();
                if (locationBean.get(position).getComment_res().size() <= 0) {
                    locationBean.get(position).setComment_res(new ArrayList<CommentBean>());
                }
                List<CommentBean> commentBeanList = locationBean.get(position).getComment_res();
                CommentBean comment = new CommentBean();
                UserUtil user = UserUtil.getInstance();
                comment.setContent(inputText);
                comment.setUuid(user.id);
                comment.setMember_name(user.mNick);
                commentBeanList.add(comment);
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemChanged(position);
                OkGo.<ReplyCommentResponseBean>post(Constant.ISSURE_DISUSS_URL)
                        .params("uuid", uuid)
                        .params("content", comment.getContent())
                        .execute(new JsonCallback<ReplyCommentResponseBean>() {
                            @Override
                            public void onSuccess(Response<ReplyCommentResponseBean> response) {
                                ReplyCommentResponseBean responseBean = response.body();
                                if (responseBean.getCode() == 0) {
                                    commentDialog.dismiss();
                                    asyncShowToast(responseBean.getMsg());
//                                    mRecyclerView.refresh();
                                } else {
                                    asyncShowToast(responseBean.getMsg());
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
        Log.i("fanbo", position + "1");
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        if (locationBean.get(position).getClick_like() != null && locationBean.get(position).getClick_like().toString().length() == 0) {
            if (locationBean.get(position).getClick_like().size() <= 0) {
                locationBean.get(position).setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
            }
            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = locationBean.get(position).getClick_like();
            likeBeanList.add(like);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", locationBean.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = locationBean.get(position).getClick_like();
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
                    .params("data_uuid", locationBean.get(position).getUuid())
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(Response<CommonBean> response) {
                            UserUtil user = UserUtil.getInstance();
                            TestMode.DataBean.ListBean.ClickLikeBean clickLikeBean = new TestMode.DataBean.ListBean.ClickLikeBean();
                            clickLikeBean.setMember_name(user.mNick);
                            if ("点赞成功".equals(response.body().message)) {
                                locationBean.get(position).getTem().add(clickLikeBean);
                                locationBean.get(position).setLike_info_res("true");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("点赞成功");
                            } else if ("取消点赞成功".equals(response.body().message)) {
                                List<TestMode.DataBean.ListBean.ClickLikeBean> list = locationBean.get(position).getTem();
                                for (int i = 0; i < locationBean.get(position).getTem().size(); i++) {
                                    TestMode.DataBean.ListBean.ClickLikeBean tem = locationBean.get(position).getTem().get(i);
                                    if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                        if (locationBean.get(position).getClick_like().size() == 1 && locationBean.get(position).getTem().size() == 1) {
                                            locationBean.get(position).setClick_like("");
                                        }
                                        locationBean.get(position).getTem().remove(tem);
                                        break;
                                    }
                                }
                                locationBean.get(position).setLike_info_res("");
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
        showBottomDialog();
        mUuid = locationBean.get(position).getUuid();
    }

    /*弹出底部弹出框*/
    private void showBottomDialog() {
        if (mShareDialog == null) {
            mShareDialog = new Dialog(mActivity, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View alertView = LayoutInflater.from(mActivity).inflate(R.layout.bottom_alertdialog_inform, null);
            //初始化控件
            mTv_inform_sex = alertView.findViewById(R.id.tv_inform_sex);
            mTv_inform_harass = alertView.findViewById(R.id.tv_inform_harass);
            mTv_inform_tort = alertView.findViewById(R.id.tv_inform_tort);
            TextView mTv_bottom_ainform_cancle = alertView.findViewById(R.id.tv_bottom_ainform_cancle);

            mTv_bottom_ainform_cancle.setOnClickListener(this);
            mTv_inform_tort.setOnClickListener(this);
            mTv_inform_harass.setOnClickListener(this);
            mTv_inform_sex.setOnClickListener(this);
            //将布局设置给dialog
            mShareDialog.setContentView(alertView);
            //获取当前activity所在的窗体
            Window dialogWindow = mShareDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        mShareDialog.show();
    }

    /**
     * 头像点击跳转用户详情页
     *
     * @param position
     */
    @Override
    public void headImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", locationBean.get(position).getMember_id());
        startActivity(intent);
    }

    private View clickContent;

    @Override
    public void commentRecall(final int outPosition, final int position, final CommentBean commentBean, TextView tvContent) {
        this.clickContent = tvContent;
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                OkGo.<ReplyCommentResponseBean>post(Constant.REPAY_COMMENT_URL)
                        .params("comment_id", commentBean.getId())
                        .params("content", inputText)
                        .params("uuid", commentBean.getData_uuid())
                        .params("pid", commentBean.getPid())
                        .execute(new JsonCallback<ReplyCommentResponseBean>() {
                            @Override
                            public void onSuccess(Response<ReplyCommentResponseBean> response) {
                                ReplyCommentResponseBean commentResponseBean = response.body();
                                if (commentResponseBean.getCode() == 0) {
                                    commentDialog.dismiss();
                                    asyncShowToast(commentResponseBean.getMsg());
                                    List<CommentBean> commentBeanList = locationBean.get(outPosition).getComment_res();
                                    ReplyCommentResponseBean.DataBean dataBean = commentResponseBean.getData();
                                    if (dataBean != null) {
                                        CommentBean comOneResBean = dataBean.getCom_one_res();
                                        if (comOneResBean != null) {
                                            commentBeanList.add(comOneResBean);
                                            mAdapter.notifyDataSetChanged();
                                            OkGo.<ReplyCommentResponseBean>post(Constant.ISSURE_DISUSS_URL)
                                                    .params("uuid", commentBean.getUuid())
                                                    .params("content", comOneResBean.getContent())
                                                    .execute(new JsonCallback<ReplyCommentResponseBean>() {
                                                        @Override
                                                        public void onSuccess(Response<ReplyCommentResponseBean> response) {
                                                            ReplyCommentResponseBean responseBean = response.body();
                                                            if (responseBean.getCode() == 0) {
                                                                asyncShowToast(responseBean.getMsg());
                                                                mRecyclerView.refresh();
                                                            } else {
                                                                asyncShowToast(responseBean.getMsg());
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
                            public void onError(Response<ReplyCommentResponseBean> response) {
                                super.onError(response);
                                asyncShowToast(response.message());
                            }
                        });
            }
        });
        commentDialog.show(getChildFragmentManager(), "comment");
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
