package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Dialog mShareDialog;
    private TextView mTv_inform_harass;
    private TextView mTv_inform_tort;
    private TextView mTv_bottom_ainform_cancle;
    private TextView mTv_inform_sex;
    private int mUuid;

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
                        if (count == 0) {
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

    private boolean keyShow = false;

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
                count = count + 10;
                initData();
            }
        });


        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                if (clickContent != null && commentDialog != null) {
                    int etTop = getLocationOnScreen(commentDialog.et_content);//dialog top值
                    int tvContentTop = getLocationOnScreen(clickContent);// textview top值
                    int scrollY = tvContentTop - etTop + clickContent.getHeight();
                    recyclerView.smoothScrollBy(0, scrollY);
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
                asyncShowToast("点击举报了色情");
                String TvInformSex = mTv_inform_sex.getText().toString().trim();
                RestInformData(TvInformSex);
                break;

            case R.id.tv_inform_harass:
                asyncShowToast("点击举报了骚扰");
                String TvInformHarass = mTv_inform_harass.getText().toString().trim();
                RestInformData(TvInformHarass);


                break;
            case R.id.tv_inform_tort:
                asyncShowToast("点击举报了侵权");
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
        OkGo.<String>post(Constant.ADD_REPORT_URL)
                .params("content", informData)
                .params("model", "Posts")
                .params("data_uuid", mUuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if (code==200){
                                asyncShowToast("举报成功");
                                com.orhanobut.logger.Logger.d("举报信息+++++" + response.body().toString());
                                mShareDialog.dismiss();
                            }else {
                                asyncShowToast(msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
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
    public void onCommentClick(final int position, String content, View itemView) {
        this.clickContent = itemView;
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
                                ReplyCommentResponseBean responseBean = GsonUtils.jsonFromJson(response.body(), ReplyCommentResponseBean.class);
                                if (responseBean.getCode() == 0) {
                                    commentDialog.dismiss();
                                    asyncShowToast(responseBean.getMsg());
                                    recyclerView.refresh();
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
        Log.i("fanbo", position + "1");
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
                            if ("点赞成功".equals(response.body().msg)) {
                                localDataList.get(position).getTem().add(clickLikeBean);
                                localDataList.get(position).setLike_info_res("true");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("点赞成功");
                            } else if ("取消点赞成功".equals(response.body().msg)) {
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
        showBottomDialog();
        mUuid = localDataList.get(position).getUuid();

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
            mTv_bottom_ainform_cancle = alertView.findViewById(R.id.tv_bottom_ainform_cancle);

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
        intent.putExtra("member_id", localDataList.get(position).getMember_id());
        startActivity(intent);
    }

    private View clickContent;

    @Override
    public void commentRecall(final int position, final CommentBean commentBean, TextView tvContent) {
        this.clickContent = tvContent;
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
                                    List<CommentBean> commentBeanList = localDataList.get(position).getComment_res();
                                    CommentBean comment = new CommentBean();
                                    ReplyCommentResponseBean.DataBean dataBean = commentResponseBean.getData();
                                    if (dataBean != null) {
                                        ReplyCommentResponseBean.DataBean.ComOneResBean comOneResBean = dataBean.getCom_one_res();
                                        if (comOneResBean != null) {
                                            comment.setContent(comOneResBean.getContent());
                                            comment.setUuid(comOneResBean.getData_uuid());
                                            comment.setMember_name(comOneResBean.getMember_name());
                                            commentBeanList.add(comment);
                                            mAdapter.notifyDataSetChanged();
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
                                                                recyclerView.refresh();
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
}
