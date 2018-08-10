package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;

import java.util.List;

public class MineCollectVideoAdapter extends BaseRecycleViewAdapter<MineCollectVideoBean.DataBean> {
    private boolean isMultiSelect = false;

    public MineCollectVideoAdapter(Context context, List<MineCollectVideoBean.DataBean> datas) {
        super(context, datas);
    }

    public void setIsCheckBoxVisible(boolean isMultiSelect){
        this.isMultiSelect=isMultiSelect;
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final MineCollectVideoBean.DataBean dataBean) {

        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);
        CheckBox mCheckBox = holder.getView(R.id.cb_item_mycollectvideo);
        final String picurl = dataBean.getPicurl();
        final String title = dataBean.getInfo().getTitle();
        holder.setText(R.id.tv_mycollect_video_title, title);

        /**
         * 加载视频第一张默认图
         */

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(picurl).apply(options)
                .into(videoImag);

        if (isMultiSelect) {
            mCheckBox.setVisibility(View.VISIBLE);
        } else {
            mCheckBox.setVisibility(View.GONE);
        }
        mCheckBox.setSelected(dataBean.isChecked());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataBean.setChecked(isChecked);
            }
        });
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }
}
