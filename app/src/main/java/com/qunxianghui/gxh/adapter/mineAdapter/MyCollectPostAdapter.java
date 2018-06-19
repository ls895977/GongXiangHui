package com.qunxianghui.gxh.adapter.mineAdapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 我收藏贴子的适配器
 */
public class MyCollectPostAdapter extends BaseRecycleViewAdapter<MyCollectPostBean.DataBean> {

    private int data_uuid;
    private CollectOnClickListener collectOnClickListener;

    public void setCollectOnClickListener(CollectOnClickListener collectOnClickListener) {
        this.collectOnClickListener = collectOnClickListener;
    }

    private android.os.Handler handler = new android.os.Handler();

    public MyCollectPostAdapter(Context context, List<MyCollectPostBean.DataBean> datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, final int position, final MyCollectPostBean.DataBean dataBean) {
        final ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);
        final TextView mTvNewsCollectCancle = holder.getView(R.id.tv_mine_mycollect_cancle_collect);
        final List<String> images = dataBean.getImages();
        final String source = dataBean.getInfo().getSource();
        final String title = dataBean.getInfo().getTitle();
        final String ctime = dataBean.getCtime();
        data_uuid = dataBean.getData_uuid();


        holder.setText(R.id.tv_mine_mycollect_from, source);
        holder.setText(R.id.tv_mine_mycollect_time, ctime);
        holder.setText(R.id.tv_mine_mycollect_title, title);
        if (images.size() >= 1) {
            GlideApp.with(mContext).load(images.get(0))
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_test_1)
                    .into(collectHeadImag);
        }

/**
 * 点击取消收藏
 */
        mTvNewsCollectCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        collectOnClickListener.cancelNewsCollect(position);
                    }
                });
            }
        });

    }


    public static boolean isInMainThread() {
        Looper myLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        Log.i("MyCollectPostAdapter", "isInMainThread myLooper=" + myLooper + ";mainLooper=" + mainLooper);
        return myLooper == mainLooper;
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_mycollect_post;
    }


    public interface CollectOnClickListener {
        void cancelNewsCollect(int position);
    }

}
