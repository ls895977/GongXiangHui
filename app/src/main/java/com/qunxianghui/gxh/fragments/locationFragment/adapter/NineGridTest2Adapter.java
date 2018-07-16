package com.qunxianghui.gxh.fragments.locationFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.UserUtil;
import com.qunxianghui.gxh.widget.BigListView;
import com.qunxianghui.gxh.widget.RoundImageView;
import com.qunxianghui.gxh.widget.SnsPopupWindow;
import com.qunxianghui.gxh.widget.TagGroup;

import java.util.List;

/**
 * Created by HMY on 2016/8/6
 */
public class NineGridTest2Adapter extends RecyclerView.Adapter<NineGridTest2Adapter.ViewHolder> {
    private int collectFlag = 0;
    private Context mContext;

    protected LayoutInflater inflater;
    private List<TestMode.DataBean.ListBean> dataBeanList;
    private CircleOnClickListener listener;
    public CommentItemAdapter commentItemAdapter;
    private LocationFragment context;
    private int model_id;

    public NineGridTest2Adapter(Context context, List<TestMode.DataBean.ListBean> dataBeanList) {
        mContext = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickLitener(CircleOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.item_bbs_nine_grid, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        model_id = dataBeanList.get(position).getId();
        holder.tv_location_person_name.setText(dataBeanList.get(position).getMember_name());
        holder.tv_location_person_content.setText(dataBeanList.get(position).getContent());
        holder.tv_location_issure_name.setText(dataBeanList.get(position).getCtime());

        UserUtil user = UserUtil.getInstance();
        final TestMode.DataBean.ListBean listBean = dataBeanList.get(position);
        if (!TextUtils.isEmpty(user.mNick) && user.mNick.equalsIgnoreCase(listBean.getMember_name())) {
            holder.deleteBtn.setVisibility(View.VISIBLE);
        } else {
            holder.deleteBtn.setVisibility(View.GONE);
        }

        //holder.img.setVisibility(View.INVISIBLE);
        final List<String> imageList = dataBeanList.get(position).getImages();
        GlideApp.with(mContext).load(dataBeanList.get(position).getMember_avatar())
                .centerCrop()
                .placeholder(R.mipmap.icon_headimage)
                .error(R.mipmap.icon_headimage)
                .into(holder.iv_location_person_head);
        if (imageList.size() == 1) {
            holder.gridLayout.setVisibility(View.GONE);
            holder.img.setVisibility(View.VISIBLE);
            GlideApp.with(mContext).load(imageList.get(0))
                    .centerCrop()
                    .placeholder(R.mipmap.default_img)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    //Toast.makeText(mContext,"test : " + position,Toast.LENGTH_LONG).show();
                    listener.onPicClick(position, p);
                }


            });
        }
        if (dataBeanList.get(position).getComment_res().size() != 0) {
            holder.digCommentBody.setVisibility(View.VISIBLE);
            commentItemAdapter = new CommentItemAdapter(mContext, dataBeanList.get(position).getComment_res(), holder.comment_list);
            holder.comment_list.setAdapter(commentItemAdapter);
        } else {
            holder.digCommentBody.setVisibility(View.GONE);
        }
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deletePost(position);
            }
        });

        //收藏
//        holder.ll_location_style_collect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "点击了收藏", Toast.LENGTH_SHORT).show();
//                collectFlag = (collectFlag == 0 ? 1 : 0);
//                holder.iv_location_style_collect.setBackgroundResource(collectFlag == 0 ? R.drawable.collect : R.drawable.collect_normal);
//                Toast.makeText(mContext, collectFlag == 0 ? "收藏成功" : "取消收藏成功", Toast.LENGTH_SHORT).show();
//            }
//        });


        //举报
        holder.tv_location_circle_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInformActivity();
            }
        });
        //点击了提交
        holder.tv_location_discuss_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = holder.comment_edit.getText().toString();
                if (listener != null) {
                    listener.onCommentClick(position, content);
                }
                //holder.ll_location_discuss_commit.setVisibility(View.GONE);
            }
        });
        final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
        snsPopupWindow.setClick_like((String) dataBeanList.get(position).getLike_info_res());
        snsPopupWindow.setCollect(dataBeanList.get(position).getCollect());
        snsPopupWindow.initItemData();

        snsPopupWindow.setmItemClickListener(new SnsPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(ActionItem item, int p) {
                switch (p) {
                    case 0://点赞
                        //Toast.makeText(mContext, "点击了点赞", Toast.LENGTH_SHORT).show();
                        listener.onLaunClick(position);
                        break;
                    case 1:
                        //Toast.makeText(mContext, "点击了收藏", Toast.LENGTH_SHORT).show();
                        listener.onCollectionClick(position);
                        break;

                    case 2://评论
                        if (listener != null) {
                            listener.onCommentClick(position, "");
                        }
                        //holder.ll_location_discuss_commit.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        /**
         * 点击本地圈头像的跳转
         */
        holder.iv_location_person_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.headImageClick(position);
            }
        });

        //弹窗
        holder.snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出popupwindow
                snsPopupWindow.showPopupWindow(v, dataBeanList.get(position), mContext);
            }
        });

        //点赞用户
        if (dataBeanList.get(position).getTem().size() != 0) {
            holder.digCommentBody.setVisibility(View.VISIBLE);
            holder.clickusertext.setVisibility(View.VISIBLE);
            String content = "";
            for (int i = 0; i < dataBeanList.get(position).getTem().size(); i++) {
                TestMode.DataBean.ListBean.ClickLikeBean like = dataBeanList.get(position).getTem().get(i);
                content = content + like.getMember_name() + " ";
            }
            holder.clickusertext.setText(content);
        } else {
            holder.clickusertext.setVisibility(View.GONE);
        }

    }

    private void toInformActivity() {
        Intent intent = new Intent(mContext, InFormActivity.class);
        intent.putExtra("model_id", model_id);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SnsPopupWindow snsPopupWindow;
        GridView gridLayout;
        TextView tv_location_style_collect;
        TextView tv_location_style_pointgood;
        TextView tv_location_circle_inform;
        TextView tv_location_person_name;
        TextView tv_location_person_content;
        TextView tv_location_issure_name, deleteBtn;
        TextView tv_location_comment;
        ImageView iv_location_style_collect, snsBtn, img;
        RoundImageView iv_location_person_head;
        LinearLayout ll_location_style_collect;
        LinearLayout ll_location_discuss_commit, digCommentBody, mLayoutLike;
        TextView tv_location_discuss_commit;
        EditText comment_edit;
        BigListView comment_list;
        TagGroup mTagGroup;
        TextView clickusertext;

        public ViewHolder(View itemView) {
            super(itemView);
            gridLayout = itemView.findViewById(R.id.layout_nine_grid);
            tv_location_style_collect = itemView.findViewById(R.id.tv_location_style_collect);
            tv_location_style_pointgood = itemView.findViewById(R.id.tv_location_style_pointgood);
            iv_location_style_collect = itemView.findViewById(R.id.iv_location_style_collect);
            ll_location_style_collect = itemView.findViewById(R.id.ll_location_style_collect);
            ll_location_discuss_commit = itemView.findViewById(R.id.ll_location_discuss_commit);
            tv_location_circle_inform = itemView.findViewById(R.id.tv_location_circle_inform);
            tv_location_person_name = itemView.findViewById(R.id.tv_location_person_name);
            tv_location_person_content = itemView.findViewById(R.id.tv_location_person_content);
            tv_location_issure_name = itemView.findViewById(R.id.tv_location_issure_name);
            iv_location_person_head = itemView.findViewById(R.id.iv_location_person_head);
            tv_location_comment = itemView.findViewById(R.id.tv_location_comment);
            tv_location_discuss_commit = itemView.findViewById(R.id.tv_location_discuss_commit);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            snsBtn = itemView.findViewById(R.id.snsBtn);
            img = itemView.findViewById(R.id.img);
            snsPopupWindow = new SnsPopupWindow(itemView.getContext());
            comment_edit = itemView.findViewById(R.id.comment_edit);
            digCommentBody = itemView.findViewById(R.id.digCommentBody);
            //mLayoutLike = itemView.findViewById(R.id.mLayoutLike);
            //mTagGroup = itemView.findViewById(R.id.mTagGroup);
            clickusertext = itemView.findViewById(R.id.click_like_user);
            comment_list = itemView.findViewById(R.id.comment_list);
        }
    }

    public interface CircleOnClickListener {
        /* 图片点击 */
        void onPicClick(int position, int picpostion);

        /* 评论点击 */
        void onCommentClick(int position, String content);

        /* 点赞 */
        void onLaunClick(int position);

        /* 收藏*/
        void onCollectionClick(int position);

        /* 头像点击*/
        void headImageClick(int position);

        /*删除*/
        void deletePost(int position);


    }


}
