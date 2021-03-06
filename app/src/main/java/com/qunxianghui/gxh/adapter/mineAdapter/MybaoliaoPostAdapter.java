package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MybaoliaoPostAdapter extends BaseRecycleViewAdapter<BaoliaoBean.DataBean> {
    public boolean isShow = false;

    public MybaoliaoPostAdapter(Context context, List<BaoliaoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final BaoliaoBean.DataBean dataBean) {
        ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);

        List<String> images = dataBean.getImages();

        if (dataBean != null) {
            String time = dataBean.getCtime();
            String title = dataBean.getTitle();
            holder.setText(R.id.tv_mine_mycollect_from, time);
            holder.setText(R.id.tv_mine_mycollect_title, title);
            holder.setText(R.id.tv_mine_mycollect_status, dataBean.status);
        }
        holder.getView(R.id.ch_delete).setVisibility(isShow ? View.VISIBLE : View.GONE);
        final CheckBox checkBox = holder.getView(R.id.ch_delete);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBean.setChecked(!dataBean.isChecked());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow){
                    checkBox.setChecked(!checkBox.isChecked());
                    dataBean.setChecked(!dataBean.isChecked());
                }else {
                    mOnAdapterClick.onClick(position);
                }

            }
        });
        if (images.size() >= 1) {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(images.get(0)).apply(options).into(collectHeadImag);

        }

        if (isShow) {
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
//            holder.itemView.setClickable(false);
        } else {
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
//            holder.itemView.setClickable(true);
        }
    }

    public static boolean isInMainThread() {
        Looper myLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        Log.i("MyCollectPostAdapter", "isInMainThread myLooper=" + myLooper + ";mainLooper=" + mainLooper);
        return myLooper == mainLooper;
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_mycollect_post;
    }

    public interface OnAdapterClick{
        void onClick(int position);
    }
    private OnAdapterClick mOnAdapterClick;
    public void setOnAdapterClick(OnAdapterClick mOnAdapterClick){
        this.mOnAdapterClick = mOnAdapterClick;
    }

}
