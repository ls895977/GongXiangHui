package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;

import java.util.List;

public class MineCollectVideoAdapter extends BaseRecycleViewAdapter<MineCollectVideoBean.DataBean> {


    public MineCollectVideoAdapter(Context context, List<MineCollectVideoBean.DataBean> datas) {
        super(context, datas);
    }
    public boolean isShow = false;
    @Override
    protected void convert(MyViewHolder holder, final int position, final MineCollectVideoBean.DataBean dataBean) {
        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);

        final String picurl = dataBean.getPicurl();
        final String title = dataBean.getInfo().getTitle();
        holder.setText(R.id.tv_mycollect_videotitle, title);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);

        /**
         * 加载视频第一张默认图
         */
        Glide.with(mContext).load(picurl).apply(options).into(videoImag);

        if (isShow) {
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
        }
        CheckBox checkBox = holder.getView(R.id.ch_delete);
        if (dataBean.isChecked() == true) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isChecked() == true) {
                    dataBean.setChecked(false);
                } else {
                    dataBean.setChecked(true);
                }
            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }


}

