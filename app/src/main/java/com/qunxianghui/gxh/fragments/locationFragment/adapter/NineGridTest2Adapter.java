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
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.fragments.locationFragment.model.NineGridTestModel;
import com.qunxianghui.gxh.fragments.locationFragment.view.NineGridTestLayout;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;
import com.qunxianghui.gxh.widget.SnsPopupWindow;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMY on 2016/8/6
 */
public class NineGridTest2Adapter extends RecyclerView.Adapter<NineGridTest2Adapter.ViewHolder> {
    private Context mContext;
    protected LayoutInflater inflater;
    private List<TestMode.DataBean.ListBean> dataBeanList;

    public NineGridTest2Adapter(Context context, List<TestMode.DataBean.ListBean> dataBeanList) {
        mContext = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
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
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.img);
        } else {
            holder.gridLayout.setVisibility(View.VISIBLE);
            holder.img.setVisibility(View.GONE);
            holder.gridLayout.setAdapter(new LocationGridAdapter(mContext, imageList));
        }


        //举报
        holder.tv_location_circle_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toInformActivity();
            }
        });


        final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
        //弹窗
        holder.snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出popupwindow
                snsPopupWindow.showPopupWindow(v);
            }
        });

        //
        snsPopupWindow.setmItemClickListener(new SnsPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(mContext, "点击了1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "点击了2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mContext, "点击了评论", Toast.LENGTH_SHORT).show();
                        ClickCommenDiscuss();
                        break;
                }
            }
        });


    }

    /**
     * 点击了评论
     */
    private void ClickCommenDiscuss() {



    }

    private void toInformActivity() {
        Intent intent = new Intent(mContext, InFormActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SnsPopupWindow snsPopupWindow;
        GridView gridLayout;

        TextView tv_location_circle_inform;
        TextView tv_location_person_name;
        TextView tv_location_person_content;
        TextView tv_location_issure_name;
        ImageView snsBtn, img;
        RoundImageView iv_location_person_head;
        public ViewHolder(View itemView) {
            super(itemView);
            gridLayout = itemView.findViewById(R.id.layout_nine_grid);
            tv_location_person_content = itemView.findViewById(R.id.tv_location_person_content);
            tv_location_issure_name = itemView.findViewById(R.id.tv_location_issure_name);
            iv_location_person_head = itemView.findViewById(R.id.iv_location_person_head);
            tv_location_circle_inform = itemView.findViewById(R.id.tv_location_circle_inform);
            tv_location_person_name = itemView.findViewById(R.id.tv_location_person_name);
            snsBtn = itemView.findViewById(R.id.snsBtn);
            img = itemView.findViewById(R.id.img);
            snsPopupWindow = new SnsPopupWindow(itemView.getContext());


        }
    }


}
