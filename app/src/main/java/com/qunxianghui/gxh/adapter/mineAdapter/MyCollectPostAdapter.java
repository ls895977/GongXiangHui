package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyCollectPostAdapter extends BaseRecycleViewAdapter<MyCollectPostBean.DataBean> {

    public boolean isShow = false;
    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public MyCollectPostAdapter(Context context, List<MyCollectPostBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MyCollectPostBean.DataBean dataBean) {
        ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);
        List<String> images = dataBean.getImages();

        if (dataBean.getInfo() != null) {
            String source = dataBean.getInfo().getSource();
            String title = dataBean.getInfo().getTitle();
            holder.setText(R.id.tv_mine_mycollect_from, source);
            holder.setText(R.id.tv_mine_mycollect_title, title);
        }
        if (images.size() >= 1) {
            Glide.with(mContext).load(images.get(0)).apply(new RequestOptions()
                    .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(collectHeadImag);
        }
        holder.getView(R.id.ch_delete).setVisibility(isShow ? View.VISIBLE : View.GONE);
        final CheckBox checkBox = holder.getView(R.id.ch_delete);
        checkBox.setChecked(dataBean.isChecked());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(!dataBean.isChecked());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    if (mCallback != null) mCallback.callback(dataBean.getData_uuid());
                } else {
                    checkBox.performClick();
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_mycollect_post;
    }

    public interface Callback {
        void callback(int id);
    }

}
