package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.List;


public class MineIssueDiscloseAdapter extends BaseRecycleViewAdapter<MyIssueDiscloseBean.DataBean>{


    public MineIssueDiscloseAdapter(Context context, List<MyIssueDiscloseBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MyIssueDiscloseBean.DataBean dataBean) {

        final List<String> images = dataBean.getImages();
        final MyGridView gridLayout = holder.getView(R.id.mygrid_issure_disclose_img);
        holder.setText(R.id.tv_mine_issure_title,dataBean.getTitle());
        holder.setText(R.id.tv_mineissue_disclose_item_time,dataBean.getCtime());
        holder.setText(R.id.tv_mineissue_item_status,dataBean.getStatus());


        //设置九宫格图片
        //设置宫格数据

              gridLayout.setAdapter(new LocationGridAdapter(mContext,images));

    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_issue_disclose_item;
    }
}
