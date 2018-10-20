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
    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public MyIssueGoodSelectAdapter(Context context, List<MyIssueGoodSelectBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MyIssueGoodSelectBean.DataBean dataBean) {
        holder.setText(R.id.tv_myissue_title, dataBean.getTitle());
        holder.setText(R.id.tv_myissue_doller, "￥" + dataBean.getPrice());
        ImageView mMyIssueGoodSelect = holder.getView(R.id.iv_myissue_goodselect_pic);
        Glide.with(mContext).load(dataBean.getImages()).apply(new RequestOptions()
                .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(mMyIssueGoodSelect);
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
                    if (mCallback != null) mCallback.callback(dataBean.getId());
                    mCallback.onItemClickListener(position);
                } else {
                    checkBox.performClick();
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_myissue_goodselect;
    }

    public interface Callback {
        void callback(int id);
        void onItemClickListener(int position);

    }

}
