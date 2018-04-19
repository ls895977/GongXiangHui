package com.qunxianghui.gxh.fragments.locationFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.fragments.locationFragment.model.NineGridTestModel;
import com.qunxianghui.gxh.fragments.locationFragment.view.NineGridTestLayout;


import java.util.List;

/**
 * Created by HMY on 2016/8/6
 */
public class NineGridTest2Adapter extends RecyclerView.Adapter<NineGridTest2Adapter.ViewHolder> {
    private int collectFlag = 0;
    private Context mContext;
    private List<NineGridTestModel> mList;
    protected LayoutInflater inflater;

    public NineGridTest2Adapter(Context context) {
        mContext = context;
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
        holder.layout.setIsShowAll(mList.get(position).isShowAll);
        holder.layout.setUrlList(mList.get(position).urlList);

        //收藏
        holder.ll_location_style_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了收藏", Toast.LENGTH_SHORT).show();
                collectFlag=(collectFlag==0?1:0);
                holder.iv_location_style_collect.setBackgroundResource(collectFlag==0?R.drawable.collect:R.drawable.collect_normal);
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
    }

    @Override
    public int getItemCount() {
        return getListSize(mList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NineGridTestLayout layout;
        TextView tv_location_style_collect;
        TextView tv_location_style_pointgood;
        ImageView iv_location_style_collect;
        LinearLayout ll_location_style_collect;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_nine_grid);
            tv_location_style_collect = itemView.findViewById(R.id.tv_location_style_collect);
            tv_location_style_pointgood = itemView.findViewById(R.id.tv_location_style_pointgood);
            iv_location_style_collect = itemView.findViewById(R.id.iv_location_style_collect);
            ll_location_style_collect = itemView.findViewById(R.id.ll_location_style_collect);
        }
    }

    private int getListSize(List<NineGridTestModel> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }
}
