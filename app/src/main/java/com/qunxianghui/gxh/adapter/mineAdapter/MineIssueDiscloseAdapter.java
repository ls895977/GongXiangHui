package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;

import java.util.List;

public class MineIssueDiscloseAdapter extends BaseRecycleViewAdapter<BaoliaoBean.DataBean> {

    public boolean isShow = false;
    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }


    public MineIssueDiscloseAdapter(Context context, List<BaoliaoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final BaoliaoBean.DataBean dataBean) {
        List<String> images = dataBean.getImages();
        ImageView mIssureDiscloseHead = holder.getView(R.id.iv_mine_myissuredisclose_head);
        holder.setText(R.id.tv_mine_issure_title, dataBean.getTitle());
        holder.setText(R.id.tv_mineissue_disclose_item_time, dataBean.getCtime());
        holder.setText(R.id.tv_mineissue_disclose_item_status, dataBean.getStatus());

        if (images.size() >= 1)
            Glide.with(mContext).load(images.get(0)).apply(new RequestOptions()
                    .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(mIssureDiscloseHead);

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
                    if (mCallback != null) mCallback.callback(dataBean);
                } else {
                    checkBox.performClick();
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_issue_disclose_item;
    }

    public interface Callback {
        void callback(BaoliaoBean.DataBean dataBean);
    }
}
