package com.qunxianghui.gxh.fragments.locationFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.fragments.locationFragment.model.NineGridTestModel;
import com.qunxianghui.gxh.fragments.locationFragment.view.NineGridTestLayout;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMY on 2016/8/6
 */
public class NineGridTest2Adapter extends RecyclerView.Adapter<NineGridTest2Adapter.ViewHolder> {
    private int collectFlag = 0;
    private Context mContext;
    private List<NineGridTestModel> mList;
    protected LayoutInflater inflater;
    private List<TestMode.DataBean.ListBean> dataBeanList;
    private List<String> imageList;

    public NineGridTest2Adapter(Context context, List<TestMode.DataBean.ListBean> dataBeanList) {
        mContext = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<NineGridTestModel> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.item_bbs_nine_grid, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {



        holder.tv_location_person_name.setText(dataBeanList.get(position).getMember_name());
        holder.tv_location_person_content.setText(dataBeanList.get(position).getContent());
        holder.tv_location_issure_name.setText(dataBeanList.get(position).getCtime());

        imageList = dataBeanList.get(position).getImages();


        GlideApp.with(mContext).load(dataBeanList.get(position).getMember_avatar())

                .centerCrop().placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_location_person_head);


    //设置宫格数据

       holder.gridLayout.setAdapter(new LocationGridAdapter(mContext, imageList,dataBeanList));

        //收藏
        holder.ll_location_style_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了收藏", Toast.LENGTH_SHORT).show();
                collectFlag = (collectFlag == 0 ? 1 : 0);
                holder.iv_location_style_collect.setBackgroundResource(collectFlag == 0 ? R.drawable.collect : R.drawable.collect_normal);
                Toast.makeText(mContext, collectFlag == 0 ? "收藏成功" : "取消收藏成功", Toast.LENGTH_SHORT).show();
            }
        });

        //点赞
        holder.tv_location_style_pointgood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了点赞", Toast.LENGTH_SHORT).show();

            }
        });

        //举报
        holder.tv_location_circle_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toInformActivity();
            }
        });

        //点击评论
        holder.tv_location_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll_location_discuss_commit.setVisibility(View.VISIBLE);
            }
        });

        //点击了提交
        holder.tv_location_discuss_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                holder.ll_location_discuss_commit.setVisibility(View.GONE);
            }
        });

    }

    private void toInformActivity() {
        Intent intent = new Intent(mContext, InFormActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return getListSize(mList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        GridView gridLayout;
        TextView tv_location_style_collect;
        TextView tv_location_style_pointgood;
        TextView tv_location_circle_inform;
        TextView tv_location_person_name;
        TextView tv_location_person_content;
        TextView tv_location_issure_name;
        TextView tv_location_comment;
        ImageView iv_location_style_collect;
        RoundImageView iv_location_person_head;
        LinearLayout ll_location_style_collect;
        LinearLayout ll_location_discuss_commit;
        TextView tv_location_discuss_commit;


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

        }
    }

    private int getListSize(List<NineGridTestModel> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }
}
