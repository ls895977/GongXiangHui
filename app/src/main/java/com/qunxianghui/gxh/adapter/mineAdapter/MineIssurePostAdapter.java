package com.qunxianghui.gxh.adapter.mineAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.CommentItemAdapter;
import com.qunxianghui.gxh.widget.BigListView;
import com.qunxianghui.gxh.widget.RoundImageView;
import com.qunxianghui.gxh.widget.SnsPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MineIssurePostAdapter extends RecyclerView.Adapter<MineIssurePostAdapter.myViewHolder> implements CommentItemAdapter.CommentRecallListener {

    private Context mContext;
    protected LayoutInflater inflater;
    private List<TestMode.DataBean.ListBean> dataBeanList;
    private CircleOnClickListener listener;
    private final int MAX_LINE_COUNT = 6;//最大显示行数
    private final int STATE_UNKNOW = -1;//未知状态
    private final int STATE_NOT_OVERFLOW = 1;//文本行数小于最大可显示行数
    private final int STATE_COLLAPSED = 2;//折叠状态
    private final int STATE_EXPANDED = 3;//展开状态
    private SparseArray<Integer> mTextStateList; //保存文本状态集合
    private boolean flag = false;
    public boolean isShow = false;

    @SuppressLint("UseSparseArrays")
    public MineIssurePostAdapter(Context context, List<TestMode.DataBean.ListBean> dataBeanList) {
        mContext = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
        mTextStateList = new SparseArray<>();
    }

    public CircleOnClickListener getListener() {
        return listener;
    }

    public void setListener(CircleOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.item_bbs_nine_grid, parent, false);
        return new myViewHolder(convertView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        final int state = mTextStateList.get(position, STATE_UNKNOW);
        if (state == STATE_UNKNOW) {
            holder.tv_location_person_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回掉会调用多次，获取玩行数后记得注销监听
                    holder.tv_location_person_content.getViewTreeObserver().removeOnPreDrawListener(this);
                    //如果内容显示的行数大于限定显示行数
                    if (holder.tv_location_person_content.getLineCount() > MAX_LINE_COUNT) {
                        holder.tv_location_person_content.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                        holder.tvShoworHide.setVisibility(View.VISIBLE);
                        holder.tvShoworHide.setText("全文");//设置其文字为全文
                        mTextStateList.put(position, STATE_COLLAPSED);
                    } else {
                        holder.tvShoworHide.setVisibility(View.GONE);//显示全文隐藏
                        mTextStateList.put(position, STATE_NOT_OVERFLOW);//让其不能超过限定的行数
                    }
                    return true;
                }
            });
        } else {
            //            如果之前已经初始化过了，则使用保存的状态，无需在获取一次
            switch (state) {
                case STATE_NOT_OVERFLOW:
                    holder.tvShoworHide.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    holder.tv_location_person_content.setMaxLines(MAX_LINE_COUNT);
                    holder.tvShoworHide.setVisibility(View.VISIBLE);
                    holder.tvShoworHide.setText("全文");
                    break;
                case STATE_EXPANDED:
                    holder.tv_location_person_content.setMaxLines(Integer.MAX_VALUE);
                    holder.tvShoworHide.setVisibility(View.VISIBLE);
                    holder.tvShoworHide.setText("收起");
                    break;
            }
        }
        holder.tv_location_person_name.setText(dataBeanList.get(position).getMember_name());
        holder.tv_location_person_content.setText(dataBeanList.get(position).getContent());
        holder.tv_location_issure_name.setText(dataBeanList.get(position).getCtime());
        final List<String> imageList = dataBeanList.get(position).getImages();
        Glide.with(mContext)
                .load(dataBeanList.get(position).getMember_avatar())
                .apply(new RequestOptions().placeholder(R.mipmap.user_moren).error(R.mipmap.user_moren).centerCrop())
                .into(holder.iv_location_person_head);
        holder.checkbox.setVisibility(isShow ? View.VISIBLE : View.GONE);
        holder.checkbox.setChecked(dataBeanList.get(position).isChecked());
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBeanList.get(position).setChecked(!dataBeanList.get(position).isChecked());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.checkbox.performClick();
                }
            }
        });

        if (imageList.size() == 1) {
            holder.gridLayout.setVisibility(View.GONE);
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(imageList.get(0))
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop())
                    .into(holder.img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShow) {
                        holder.checkbox.performClick();
                        return;
                    }
                    listener.onPicClick(position, 0);
                }
            });
        } else {
            holder.gridLayout.setVisibility(View.VISIBLE);
            holder.img.setVisibility(View.GONE);
            LocationGridAdapter adapter = new LocationGridAdapter(mContext, imageList);
            holder.gridLayout.setAdapter(adapter);
            adapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
                @Override
                public void onClick(View v, int p) {
                    if (isShow) {
                        holder.checkbox.performClick();
                        return;
                    }
                    listener.onPicClick(position, p);
                }
            });
        }
        final int size = dataBeanList.get(position).getComment_res().size();
        if (size != 0) {
            List<CommentBean> commentBeans = new ArrayList<>();
            holder.digCommentBody.setVisibility(View.VISIBLE);
            final CommentItemAdapter commentItemAdapter = new CommentItemAdapter(mContext, commentBeans, holder.comment_list);
            commentItemAdapter.setCommentRecallListener(this);
            holder.comment_list.setAdapter(commentItemAdapter);
            if (size > 7) {
                holder.llShowComment.setVisibility(View.VISIBLE);
                holder.llShowComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isShow) {
                            holder.checkbox.performClick();
                            return;
                        }
                        if (!flag) {
                            commentItemAdapter.refreshData(dataBeanList.get(position).getComment_res());
                            //holder.llShowComment.setVisibility(View.GONE);
                            holder.tvShowText.setText("收起");
                            flag = true;
                            holder.ivShow.setImageResource(R.mipmap.ic_up);
                        } else {
                            commentItemAdapter.refreshData(dataBeanList.get(position).getComment_res().subList(0, 7));
                            holder.tvShowText.setText("展开");
                            flag = false;
                            holder.ivShow.setImageResource(R.mipmap.ic_down);
                        }
                    }
                });
                commentItemAdapter.refreshData(dataBeanList.get(position).getComment_res().subList(0, 7));
            } else {
                holder.llShowComment.setVisibility(View.GONE);
                commentItemAdapter.refreshData(dataBeanList.get(position).getComment_res());
            }
        } else {
            holder.digCommentBody.setVisibility(View.GONE);
        }
        holder.tvShoworHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.checkbox.performClick();
                    return;
                }
                if (state == STATE_COLLAPSED) {
                    holder.tv_location_person_content.setMaxLines(Integer.MAX_VALUE);
                    holder.tvShoworHide.setText("收起");
                    mTextStateList.put(position, STATE_EXPANDED);
                    notifyDataSetChanged();
                } else if (state == STATE_EXPANDED) {
                    holder.tv_location_person_content.setMaxLines(MAX_LINE_COUNT);
                    holder.tvShoworHide.setText("全文");
                    mTextStateList.put(position, STATE_COLLAPSED);
                    notifyDataSetChanged();
                }
            }
        });

        //点击了提交
        holder.tv_location_discuss_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.checkbox.performClick();
                    return;
                }
                String content = holder.comment_edit.getText().toString();
                if (listener != null) {
                    listener.onCommentClick(position, content);
                }
            }
        });
        final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
        snsPopupWindow.setClick_like(dataBeanList.get(position).getLike_info_res());
        snsPopupWindow.setCollect(dataBeanList.get(position).getCollect());
        snsPopupWindow.initItemData();
        snsPopupWindow.setmItemClickListener(new SnsPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(ActionItem item, int p) {
                switch (p) {
                    case 0://点赞
                        listener.onPraiseClick(position);
                        break;
                    case 1:
                        listener.onCollectionClick(position);
                        break;
                    case 2://评论
                        if (listener != null) {
                            listener.onCommentClick(position, "");
                        }
                        break;
                }
            }
        });
        holder.iv_location_person_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.checkbox.performClick();
                    return;
                }
                listener.headImageClick(position);
            }
        });
        holder.snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.checkbox.performClick();
                    return;
                }
                snsPopupWindow.showPopupWindow(v, dataBeanList.get(position), mContext);
            }
        });
        if (dataBeanList.get(position).getTem().size() != 0) {
            holder.digCommentBody.setVisibility(View.VISIBLE);
            holder.clickusertext.setVisibility(View.VISIBLE);
            String content;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < dataBeanList.get(position).getTem().size(); i++) {
                TestMode.DataBean.ListBean.ClickLikeBean like = dataBeanList.get(position).getTem().get(i);
                content = like.getMember_name() + " ";
                stringBuilder.append(content);
            }
            holder.clickusertext.setText(stringBuilder);
        } else {
            holder.clickusertext.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    /*回复评论的接口回调*/
    @Override
    public void recommentContentListener(int position, CommentBean commentBean, TextView tvContent) {
        listener.commentRecall(position, commentBean, tvContent);
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        SnsPopupWindow snsPopupWindow;
        GridView gridLayout;
        TextView tv_location_person_name;
        TextView tv_location_person_content;
        TextView tv_location_issure_name, tvShoworHide;
        TextView tv_location_comment;
        ImageView snsBtn, img;
        RoundImageView iv_location_person_head;
        LinearLayout digCommentBody;
        TextView tv_location_discuss_commit;
        EditText comment_edit;
        BigListView comment_list;
        TextView clickusertext;
        LinearLayout llShowComment;
        TextView tvShowText;
        ImageView ivShow;
        CheckBox checkbox;

        myViewHolder(View itemView) {
            super(itemView);
            gridLayout = itemView.findViewById(R.id.layout_nine_grid);
            tv_location_person_name = itemView.findViewById(R.id.tv_location_person_name);
            tv_location_person_content = itemView.findViewById(R.id.tv_location_person_content);
            tv_location_issure_name = itemView.findViewById(R.id.tv_location_issure_name);
            iv_location_person_head = itemView.findViewById(R.id.iv_location_person_head);
            tv_location_comment = itemView.findViewById(R.id.tv_location_comment);
            tv_location_discuss_commit = itemView.findViewById(R.id.tv_location_discuss_commit);
            tvShoworHide = itemView.findViewById(R.id.tv_show_or_hide);
            snsBtn = itemView.findViewById(R.id.snsBtn);
            img = itemView.findViewById(R.id.img);
            snsPopupWindow = new SnsPopupWindow(itemView.getContext());
            comment_edit = itemView.findViewById(R.id.comment_edit);
            digCommentBody = itemView.findViewById(R.id.digCommentBody);
            clickusertext = itemView.findViewById(R.id.click_like_user);
            comment_list = itemView.findViewById(R.id.comment_list);
            llShowComment = itemView.findViewById(R.id.ll_show_comment);
            tvShowText = itemView.findViewById(R.id.tv_showText);
            ivShow = itemView.findViewById(R.id.iv_show_icon);
            checkbox = itemView.findViewById(R.id.ch_issue_delete);
        }
    }

    public interface CircleOnClickListener {
        /* 图片点击 */
        void onPicClick(int position, int picpostion);

        /* 评论点击 */
        void onCommentClick(int position, String content);

        /* 点赞 */
        void onPraiseClick(int position);

        /* 收藏*/
        void onCollectionClick(int position);

        /* 头像点击*/
        void headImageClick(int position);

        /*回复评论*/
        void commentRecall(int position, CommentBean commentBean, TextView topLocation);

    }

}
