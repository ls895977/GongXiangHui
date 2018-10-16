package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.ReplyCommentResponseBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.bean.mine.MessageDetailBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.ui.dialog.CommentDialog;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.CommentItemAdapter;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.UserUtil;
import com.qunxianghui.gxh.widget.BigListView;
import com.qunxianghui.gxh.widget.RoundImageView;
import com.qunxianghui.gxh.widget.SnsPopupWindow;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.layout_nine_grid)
    GridView gridLayout;
    @BindView(R.id.tv_location_person_name)
    TextView tv_location_person_name;
    @BindView(R.id.tv_location_person_content)
    TextView tv_location_person_content;
    @BindView(R.id.tv_location_issure_name)
    TextView tv_location_issure_name;
    @BindView(R.id.tv_show_or_hide)
    TextView tvShoworHide;
    //    @BindView(R.id.tv_location_comment)
//    TextView tv_location_comment;
    @BindView(R.id.snsBtn)
    ImageView snsBtn;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.iv_location_person_head)
    RoundImageView iv_location_person_head;
    @BindView(R.id.digCommentBody)
    LinearLayout digCommentBody;
    @BindView(R.id.tv_location_discuss_commit)
    TextView tv_location_discuss_commit;
    @BindView(R.id.comment_edit)
    EditText comment_edit;
    @BindView(R.id.comment_list)
    BigListView comment_list;
    @BindView(R.id.click_like_user)
    TextView clickusertext;
    @BindView(R.id.ll_show_comment)
    LinearLayout llShowComment;
    @BindView(R.id.tv_showText)
    TextView tvShowText;
    @BindView(R.id.iv_show_icon)
    ImageView ivShow;

    private final int MAX_LINE_COUNT = 7;//最大显示行数
    private final int STATE_UNKNOW = -1;//未知状态
    private final int STATE_NOT_OVERFLOW = 1;//文本行数小于最大可显示行数
    private final int STATE_COLLAPSED = 2;//折叠状态
    private final int STATE_EXPANDED = 3;//展开状态
    private SnsPopupWindow snsPopupWindow;
    private int mState;
    private boolean mFlag = false;
    private TestMode.DataBean.ListBean dataBean;
    private CommentDialog commentDialog;
    private Dialog mReportDialog;
    private TextView mTv_inform_tort;
    private TextView mTv_inform_sex;
    private TextView mTv_inform_harass;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setTitleText("详情").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        requestMineMessageData();
    }

    private void requestMineMessageData() {
        OkGo.<MessageDetailBean>get(Constant.MINE_LOCAL_DETAIL_URL)
                .params("uuid", getIntent().getIntExtra("detail_uuid", 0))
                .execute(new JsonCallback<MessageDetailBean>() {
                    @Override
                    public void onSuccess(Response<MessageDetailBean> response) {
                        if (response.body().code == 200) {
                            dataBean = response.body().data;
                            setView();
                        }
                    }
                });
    }

    protected void setView() {
        snsPopupWindow = new SnsPopupWindow(MessageDetailActivity.this);

        mState = STATE_UNKNOW;
        if (mState == STATE_UNKNOW) {
            tv_location_person_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回掉会调用多次，获取玩行数后记得注销监听
                    tv_location_person_content.getViewTreeObserver().removeOnPreDrawListener(this);
                    //如果内容显示的行数大于限定显示行数
                    if (tv_location_person_content.getLineCount() > MAX_LINE_COUNT) {
                        tv_location_person_content.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                        tvShoworHide.setVisibility(View.VISIBLE);
                        tvShoworHide.setText("全文");//设置其文字为全文
                        mState = STATE_COLLAPSED;
                    } else {
                        tvShoworHide.setVisibility(View.GONE);//显示全文隐藏
                        //让其不能超过限定的行数
                        mState = STATE_NOT_OVERFLOW;
                    }
                    return true;
                }
            });
        } else {
            //            如果之前已经初始化过了，则使用保存的状态，无需在获取一次
            switch (mState) {
                case STATE_NOT_OVERFLOW:
                    tvShoworHide.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    tv_location_person_content.setMaxLines(MAX_LINE_COUNT);
                    tvShoworHide.setVisibility(View.VISIBLE);
                    tvShoworHide.setText("全文");
                    break;
                case STATE_EXPANDED:
                    tv_location_person_content.setMaxLines(Integer.MAX_VALUE);
                    tvShoworHide.setVisibility(View.VISIBLE);
                    tvShoworHide.setText("收起");
                    break;
            }
        }

        tv_location_person_name.setText(dataBean.getMember_name());
        tv_location_person_content.setText(dataBean.getContent());
        tv_location_issure_name.setText(dataBean.getCtime());
        List<String> imageList = dataBean.getImages();
        Glide.with(mContext)
                .load(dataBean.getMember_avatar())
                .apply(new RequestOptions().placeholder(R.mipmap.user_moren).error(R.mipmap.user_moren).centerCrop())
                .into(iv_location_person_head);
        if (imageList.size() == 1) {
            gridLayout.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(imageList.get(0))
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img)
                            .error(R.mipmap.default_img).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPicClick(0);
                }
            });
        } else {
            gridLayout.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            LocationGridAdapter adapter = new LocationGridAdapter(mContext, imageList);
            gridLayout.setAdapter(adapter);
            adapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
                @Override
                public void onClick(View v, int p) {
                    onPicClick(p);
                }
            });
        }

        setCommentView();

        //点击了提交
        tv_location_discuss_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        snsPopupWindow.setClick_like(dataBean.getLike_info_res());
        snsPopupWindow.setCollect(dataBean.getCollect());
        snsPopupWindow.initItemData();

        snsPopupWindow.setmItemClickListener(new SnsPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(ActionItem item, int p) {
                switch (p) {
                    case 0://点赞
                        if (dataBean.getClick_like() != null && dataBean.getClick_like().toString().length() == 0) {
                            if (dataBean.getClick_like().size() <= 0) {
                                dataBean.setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
                            }
                            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
                            UserUtil user = UserUtil.getInstance();
                            like.setMember_name(user.mNick);
                            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = dataBean.getClick_like();
                            likeBeanList.add(like);
                            OkGo.<String>post(Constant.LIKE_URL)
                                    .params("data_uuid", dataBean.getUuid()).execute(new DialogCallback<String>(MessageDetailActivity.this) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                                    UserUtil user = UserUtil.getInstance();
                                    like.setMember_name(user.mNick);
                                    List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = dataBean.getClick_like();
                                    likeBeanList.add(like);
                                    setLikeView();
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    asyncShowToast("登陆账号异常");
                                }
                            });
                        } else {
                            OkGo.<CommonBean>post(Constant.LIKE_URL)
                                    .params("data_uuid", dataBean.getUuid())
                                    .execute(new JsonCallback<CommonBean>() {
                                        @Override
                                        public void onSuccess(Response<CommonBean> response) {
                                            UserUtil user = UserUtil.getInstance();
                                            TestMode.DataBean.ListBean.ClickLikeBean clickLikeBean = new TestMode.DataBean.ListBean.ClickLikeBean();
                                            clickLikeBean.setMember_name(user.mNick);
                                            if ("点赞成功".equals(response.body().message)) {
                                                dataBean.getTem().add(clickLikeBean);
                                                dataBean.setLike_info_res("true");
                                                asyncShowToast(response.body().message);
                                            } else if ("取消点赞成功".equals(response.body().message)) {
                                                List<TestMode.DataBean.ListBean.ClickLikeBean> list = dataBean.getTem();
                                                for (int i = 0; i < dataBean.getTem().size(); i++) {
                                                    TestMode.DataBean.ListBean.ClickLikeBean tem = dataBean.getTem().get(i);
                                                    if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                                        if (dataBean.getClick_like().size() == 1 && dataBean.getTem().size() == 1) {
                                                            dataBean.setClick_like("");
                                                        }
                                                        dataBean.getTem().remove(tem);
                                                        break;
                                                    }
                                                }
                                                dataBean.setLike_info_res("");
                                                asyncShowToast(response.body().message);
                                            }
                                            setLikeView();
                                        }

                                        @Override
                                        public void onError(Response<CommonBean> response) {
                                            super.onError(response);
                                            asyncShowToast("登陆账号异常");
                                        }
                                    });
                        }
                        break;
                    case 1:
                        showBottomDialog();
                        break;
                    case 2://评论
                        commentDialog = new CommentDialog("请输入评论内容", new CommentDialog.SendListener() {
                            @Override
                            public void sendComment(String inputText) {
                                final int uuid = dataBean.getUuid();
                                if (dataBean.getComment_res().size() <= 0)
                                    dataBean.setComment_res(new ArrayList<CommentBean>());
                                List<CommentBean> commentBeanList = dataBean.getComment_res();
                                CommentBean comment = new CommentBean();
                                UserUtil user = UserUtil.getInstance();
                                comment.setContent(inputText);
                                comment.setUuid(user.id);
                                comment.setMember_name(user.mNick);
                                commentBeanList.add(comment);
                                setCommentView();
                                OkGo.<ReplyCommentResponseBean>post(Constant.ISSURE_DISUSS_URL)
                                        .params("uuid", uuid)
                                        .params("content", comment.getContent())
                                        .execute(new JsonCallback<ReplyCommentResponseBean>() {
                                            @Override
                                            public void onSuccess(Response<ReplyCommentResponseBean> response) {
                                                ReplyCommentResponseBean responseBean = response.body();
                                                if (responseBean.getCode() == 0) {
                                                    commentDialog.dismiss();
                                                    List<CommentBean> comment_res = dataBean.getComment_res();
                                                    comment_res.get(comment_res.size() - 1).setId(responseBean.getData().getCom_one_res().getId());
                                                    asyncShowToast(responseBean.getMsg());
                                                } else {
                                                    asyncShowToast(responseBean.getMsg());
                                                }
                                            }
                                        });
                            }
                        });
                        commentDialog.show(getSupportFragmentManager(), "comment");
                        break;
                }
            }
        });
        iv_location_person_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener != null) {
//                    listener.headImageClick(getAdapterPosition() - 1);
//                }
            }
        });
        final SnsPopupWindow finalSnsPopupWindow = snsPopupWindow;
        snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalSnsPopupWindow.showPopupWindow(v, dataBean, mContext);
            }
        });
        setLikeView();
    }

    private void setLikeView() {
        List<TestMode.DataBean.ListBean.ClickLikeBean> tem = dataBean.getTem();
        if (!tem.isEmpty()) {
            digCommentBody.setVisibility(View.VISIBLE);
            clickusertext.setVisibility(View.VISIBLE);
            String content;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0, length = tem.size() - 1; i <= length; i++) {
                TestMode.DataBean.ListBean.ClickLikeBean like = tem.get(i);
                if (i == length) {
                    content = like.getMember_name() + " ";
                } else {
                    content = like.getMember_name() + ",";
                }
                stringBuilder.append(content);
            }
            clickusertext.setText(stringBuilder);

        } else {
            clickusertext.setVisibility(View.GONE);
        }
    }

    private void setCommentView() {
        int size = dataBean.getComment_res().size();
        if (size != 0) {
            List<CommentBean> commentBeans = new ArrayList<>();
            digCommentBody.setVisibility(View.VISIBLE);
            final CommentItemAdapter commentItemAdapter = new CommentItemAdapter(mContext, commentBeans, comment_list);
            commentItemAdapter.setCommentRecallListener(new CommentItemAdapter.CommentRecallListener() {
                @Override
                public void recommentContentListener(int position, final CommentBean commentBean, TextView topLocation) {

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
                                                List<CommentBean> commentBeanList = dataBean.getComment_res();
                                                ReplyCommentResponseBean.DataBean dataBean = commentResponseBean.getData();
                                                if (dataBean != null) {
                                                    CommentBean comOneResBean = dataBean.getCom_one_res();
                                                    if (comOneResBean != null) {
                                                        commentBeanList.add(comOneResBean);
                                                        setCommentView();
                                                        OkGo.<ReplyCommentResponseBean>post(Constant.ISSURE_DISUSS_URL)
                                                                .params("uuid", commentBean.getUuid())
                                                                .params("content", comOneResBean.getContent())
                                                                .execute(new JsonCallback<ReplyCommentResponseBean>() {
                                                                    @Override
                                                                    public void onSuccess(Response<ReplyCommentResponseBean> response) {
                                                                        ReplyCommentResponseBean responseBean = response.body();
                                                                        if (responseBean.getCode() == 0) {
                                                                            asyncShowToast(responseBean.getMsg());
//                                                                            mRecyclerView.refresh();
                                                                        } else {
                                                                            asyncShowToast(responseBean.getMsg());
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onError(Response<ReplyCommentResponseBean> response) {
                                                                    }
                                                                });
                                                    }
                                                }
                                            } else {
                                                asyncShowToast(commentResponseBean.getMsg());
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
                    commentDialog.show(getSupportFragmentManager(), "comment");
                }
            });
            comment_list.setAdapter(commentItemAdapter);
            if (size > 7) {
                llShowComment.setVisibility(View.VISIBLE);
                llShowComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mFlag) {
                            commentItemAdapter.refreshData(dataBean.getComment_res());
                            //llShowComment.setVisibility(View.GONE);
                            tvShowText.setText("收起");
                            mFlag = true;
                            ivShow.setImageResource(R.mipmap.ic_up);
                        } else {
                            commentItemAdapter.refreshData(dataBean.getComment_res().subList(0, 7));
                            tvShowText.setText("展开");
                            mFlag = false;
                            ivShow.setImageResource(R.mipmap.ic_down);
                        }
                    }
                });
                commentItemAdapter.refreshData(dataBean.getComment_res().subList(0, 7));
            } else {
                llShowComment.setVisibility(View.GONE);
                commentItemAdapter.refreshData(dataBean.getComment_res());
            }
        } else {
            digCommentBody.setVisibility(View.GONE);
        }
        tvShoworHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState == STATE_COLLAPSED || mState == STATE_UNKNOW) {
                    tv_location_person_content.setMaxLines(Integer.MAX_VALUE);
                    tvShoworHide.setText("收起");
                    mState = STATE_EXPANDED;
//                    notifyDataSetChanged();
                } else if (mState == STATE_EXPANDED) {
                    tv_location_person_content.setMaxLines(MAX_LINE_COUNT);
                    tvShoworHide.setText("全文");
                    mState = STATE_COLLAPSED;
//                    notifyDataSetChanged();
                }
            }
        });
    }

    public void onPicClick(int picpostion) {
        List<String> imageList = dataBean.getImages();
        ArrayList<String> arrayList = new ArrayList<>(imageList);
        Intent intent = new Intent(MessageDetailActivity.this, PhotoBrowserActivity.class);
        intent.putStringArrayListExtra("url", arrayList);
        intent.putExtra("position", picpostion);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_pop_in, R.anim.pop_out);
    }

    /*弹出底部弹出框*/
    private void showBottomDialog() {
        if (mReportDialog == null) {
            mReportDialog = new Dialog(MessageDetailActivity.this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View alertView = LayoutInflater.from(MessageDetailActivity.this).inflate(R.layout.bottom_alertdialog_inform, null);
            //初始化控件
            mTv_inform_tort = alertView.findViewById(R.id.tv_inform_tort);
            mTv_inform_harass = alertView.findViewById(R.id.tv_inform_harass);
            mTv_inform_sex = alertView.findViewById(R.id.tv_inform_sex);

            mTv_inform_tort.setOnClickListener(this);
            mTv_inform_harass.setOnClickListener(this);
            mTv_inform_sex.setOnClickListener(this);
            alertView.findViewById(R.id.tv_bottom_ainform_cancle).setOnClickListener(this);
            //将布局设置给dialog
            mReportDialog.setContentView(alertView);
            //获取当前activity所在的窗体
            Window dialogWindow = mReportDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        mReportDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_inform_sex:
                String TvInformSex = mTv_inform_sex.getText().toString().trim();
                restInformData(TvInformSex);
                break;
            case R.id.tv_inform_harass:
                String TvInformHarass = mTv_inform_harass.getText().toString().trim();
                restInformData(TvInformHarass);
                break;
            case R.id.tv_inform_tort:
                String TvInformTort = mTv_inform_tort.getText().toString().trim();
                restInformData(TvInformTort);
                break;
            case R.id.tv_bottom_ainform_cancle:
                mReportDialog.dismiss();
                break;
        }
    }

    /*举报信息*/
    private void restInformData(String informData) {
        OkGo.<CommonBean>post(Constant.ADD_REPORT_URL)
                .params("content", informData)
                .params("model", "Posts")
                .params("data_uuid", dataBean.getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        String msg = response.body().message;
                        if (code == 200) {
                            asyncShowToast("举报成功");
                            mReportDialog.dismiss();
                        } else {
                            asyncShowToast(msg);
                        }
                    }
                });
    }
}
