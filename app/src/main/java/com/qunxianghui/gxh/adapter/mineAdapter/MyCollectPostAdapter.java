package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyCollectPostAdapter extends BaseRecycleViewAdapter<MyCollectPostBean.DataBean> {
    private int data_uuid;
    private android.os.Handler handler = new android.os.Handler();
    public MyCollectPostAdapter(Context context, List<MyCollectPostBean.DataBean> datas) {
        super(context, datas);
    }
    public boolean isShow = false;
    @Override
    protected void convert(MyViewHolder holder, final int position, final MyCollectPostBean.DataBean dataBean) {
        ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);
        List<String> images = dataBean.getImages();
        data_uuid = dataBean.getData_uuid();

        if (dataBean.getInfo() != null) {
            String source = dataBean.getInfo().getSource();
            String title = dataBean.getInfo().getTitle();
            holder.setText(R.id.tv_mine_mycollect_from, source);
            holder.setText(R.id.tv_mine_mycollect_title, title);
        }
        if (images.size() >= 1) {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(images.get(0)).apply(options).into(collectHeadImag);

        }
        if (isShow) {
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
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




}
