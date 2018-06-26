package com.qunxianghui.gxh.adapter.mineAdapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectPostBean;
import com.qunxianghui.gxh.bean.mine.MyClollectPostBean;
import com.qunxianghui.gxh.widget.MyGridView;


import java.util.List;


public class MineCollectPostAdapter extends BaseRecycleViewAdapter<MineCollectPostBean.DataBean> {
private MycollectPostListener mycollectPostListener;

    public void setMycollectPostListener(MycollectPostListener mycollectPostListener) {
        this.mycollectPostListener = mycollectPostListener;
    }

    public MineCollectPostAdapter(Context context, List<MineCollectPostBean.DataBean> datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, final int position, MineCollectPostBean.DataBean dataBean) {

        final MyGridView myGrid = holder.getView(R.id.gv_mine_grid);
        final List<String> images = dataBean.getImages();
        final String newctime = dataBean.getNewctime();

        final String content = dataBean.getInfo().getContent();
        holder.setText(R.id.tv_minecollect_post_tiem, newctime);
        holder.setText(R.id.tv_mycollect_post_title, content);
        //九宫格图片
        final LocationGridAdapter mycollectPostGridAdapter = new LocationGridAdapter(mContext, images);
        myGrid.setAdapter(mycollectPostGridAdapter);
        mycollectPostGridAdapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
            @Override
            public void onClick(View v, int p) {
                mycollectPostListener.onPicClick(position,p);
            }
        });


        TextView tv_collect_cancel = holder.getView(R.id.tv_collect_post_cancel);

        /**
         * 取消收藏
         */
        tv_collect_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mycollectPostListener.cancelCollect(position);

            }
        });

    }



    @Override
    protected int getItemView() {
        return R.layout.item_mycollect_post;
    }

    public interface MycollectPostListener{
        /* 取消收藏*/


        void cancelCollect(int position);
        /* 图片点击*/
        void onPicClick(int position, int picpostion);
    }


}
