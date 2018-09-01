package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyIssueGoodSelectBean;

import java.util.List;

public class MyIssueGoodSelectAdapter extends BaseRecycleViewAdapter<MyIssueGoodSelectBean.DataBean> {
    public boolean isShow = false;
    public MyIssueGoodSelectAdapter(Context context, List<MyIssueGoodSelectBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final MyIssueGoodSelectBean.DataBean dataBean) {
        holder.setText(R.id.tv_myissue_title, dataBean.getTitle());
        holder.setText(R.id.tv_myissue_doller,"￥"+dataBean.getPrice());
        ImageView mMyIssueGoodSelect = holder.getView(R.id.iv_myissue_goodselect_pic);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.icon_guid_three);
        options.error(R.mipmap.icon_guid_three);
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getImages()).apply(options).into(mMyIssueGoodSelect);

       if (isShow) {
            //videoImag.setClickable(false);
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            //videoImag.setClickable(true);
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
        return R.layout.item_myissue_goodselect;
    }
}
