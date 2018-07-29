package com.qunxianghui.gxh.adapter.mineAdapter;


import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.generalize.GeneraPersonStaticBean;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyGeneralizePersonAdapter extends BaseQuickAdapter<GeneraPersonStaticBean.DataBean, BaseViewHolder> {

    private RequestOptions mOptions;

    @SuppressLint("CheckResult")
    public MyGeneralizePersonAdapter(List<GeneraPersonStaticBean.DataBean> list) {
        super(R.layout.item_generalize_person, list);
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder helper, GeneraPersonStaticBean.DataBean item) {
        List<String> images = item.images;
        helper.setText(R.id.tv_generalize_person_click, item.click_cnt)
                .setText(R.id.tv_generalize_person_time, item.ctime)
                .setText(R.id.tv_generalize_person_title, item.title)
                .setText(R.id.tv_generalize_person_seecount, item.view_cnt)
                .setText(R.id.tv_generalize_person_share, item.share_cnt);
        if (images.size() >= 1) {
            ImageView img = helper.getView(R.id.iv_generalize_person_head);
            Glide.with(mContext).load(images.get(0)).apply(mOptions).into(img);
        }
    }
}
