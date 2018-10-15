package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.view.View;
import android.view.ViewTreeObserver;
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
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.ReplyCommentResponseBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.bean.mine.MessageDetailBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.dialog.CommentDialog;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.CommentItemAdapter;
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

public class MessageDetailActivity extends BaseActivity {

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
//                    listener.onPicClick(getAdapterPosition() - 1, 0);
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
//                    listener.onPicClick(getAdapterPosition() - 1, p);
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
//                        listener.onPraiseClick(getAdapterPosition() - 1);
                        break;
                    case 1:
//                        listener.onCollectionClick(getAdapterPosition() - 1);
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

}
