package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.List;

public class MineIssurePostAdapter extends BaseRecycleViewAdapter<MineIssurePostBean.DataBean.ListBean> {

    public MineIssurePostAdapter(Context context, List<MineIssurePostBean.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineIssurePostBean.DataBean.ListBean listBean) {
        final List<String> images = (List<String>) listBean.getImages();
        final MyGridView myGridView = holder.getView(R.id.layout_nine_grid_mineissue_post);
        holder.setText(R.id.tv_mine_issue_post_name, listBean.getMember_name());
        holder.setText(R.id.tv_mine_issue_post_content, listBean.getContent());
        holder.setText(R.id.tv_mine_issue_post_issuetime, listBean.getCtime());

        //设置九宫哥
        myGridView.setAdapter(new LocationGridAdapter(mContext,images));

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_issue_post;
    }
}
