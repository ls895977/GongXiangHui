package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;

import java.util.List;

public class MineIssueDiscloseAdapter extends BaseRecycleViewAdapter<MyIssueDiscloseBean.DataBean>{
    public MineIssueDiscloseAdapter(Context context, List<MyIssueDiscloseBean.DataBean> datas) {
        super(context, datas);
    }
    @Override
    protected void convert(MyViewHolder holder, int position, MyIssueDiscloseBean.DataBean dataBean) {
        final List<String> images = dataBean.getImages();
        ImageView mIssureDiscloseHead = holder.getView(R.id.iv_mine_myissuredisclose_head);
        holder.setText(R.id.tv_mine_issure_title,dataBean.getTitle());
        holder.setText(R.id.tv_mineissue_disclose_item_time,dataBean.getCtime());

        if (images.size() !=0) {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(images.get(0)).apply(options).into(mIssureDiscloseHead);
        }
    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_issue_disclose_item;
    }
}
