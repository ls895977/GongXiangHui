package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;

import java.util.HashMap;
import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyCollectPostAdapter extends BaseRecycleViewAdapter<MyCollectPostBean.DataBean> {
    private HashMap<Integer, Integer> isCheckBoxVisible;// 用来记录是否显示checkBox
    private HashMap<Integer, Boolean> isChecked;// 用来记录是否被选中
    private boolean isMultiSelect = false;
    private CollectOnClickListener collectOnClickListener;

    public void setCollectOnClickListener(CollectOnClickListener collectOnClickListener) {
        this.collectOnClickListener = collectOnClickListener;
    }

    private android.os.Handler handler = new android.os.Handler();

    public MyCollectPostAdapter(Context context, List<MyCollectPostBean.DataBean> datas) {
        super(context, datas);
        isChecked=new HashMap<>();
    }

    public void setIsCheckBoxVisible(boolean isMultiSelect){
        this.isMultiSelect=isMultiSelect;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MyCollectPostBean.DataBean dataBean) {
        final ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);
        final TextView mTvNewsCollectCancle = holder.getView(R.id.tv_mine_mycollect_cancle_collect);
        CheckBox mCheckBox = holder.getView(R.id.cb_item_mycollectnews);
        final List<String> images = dataBean.getImages();
        final String source = dataBean.getInfo().getSource();
        final String title = dataBean.getInfo().getTitle();


        if(isMultiSelect){
            mCheckBox.setVisibility(View.VISIBLE);
        }else{
            mCheckBox.setVisibility(View.GONE);
        }

        mCheckBox.setSelected(dataBean.isChecked());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataBean.setChecked(isChecked);
            }
        });

        holder.setText(R.id.tv_mine_mycollect_from, source);
        holder.setText(R.id.tv_mine_mycollect_title, title);
        if (images.size() >= 1) {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(images.get(0)).apply(options).into(collectHeadImag);
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
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
