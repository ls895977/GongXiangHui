package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectPostBean;
import com.qunxianghui.gxh.bean.mine.MyClollectPostBean;
import com.qunxianghui.gxh.widget.MyGridView;


import java.util.List;


public class MineCollectPostAdapter extends BaseRecycleViewAdapter<MineCollectPostBean.DataBean> {


    public MineCollectPostAdapter(Context context, List<MineCollectPostBean.DataBean> datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, int position, MineCollectPostBean.DataBean dataBean) {

        final MyGridView myGrid = holder.getView(R.id.gv_mine_grid);
        final List<String> images = dataBean.getImages();
        final String newctime = dataBean.getNewctime();

        final String content = dataBean.getInfo().getContent();


        holder.setText(R.id.tv_minecollect_post_tiem, newctime);
        holder.setText(R.id.tv_mycollect_post_title, content);
        //九宫格图片
        myGrid.setAdapter(new LocationGridAdapter(mContext,images));

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mycollect_post;
    }
}
