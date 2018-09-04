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

    public boolean isShow = false;
    public Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }


    public MineCollectVideoAdapter(Context context, List<MineCollectVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MineCollectVideoBean.DataBean dataBean) {
        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);

        final String picurl = dataBean.getPicurl();
        final String title = dataBean.getInfo().getTitle();
        holder.setText(R.id.tv_mycollect_videotitle, title);
        Glide.with(mContext).load(picurl).apply(new RequestOptions()
                .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(videoImag);

        if (isShow) {
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
        }
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
        return R.layout.item_mine_collect_video;
    }

    public interface Callback{
        void callback(int id);
    }

}

