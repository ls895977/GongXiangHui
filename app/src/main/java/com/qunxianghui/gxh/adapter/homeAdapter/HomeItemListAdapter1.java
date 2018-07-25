package com.qunxianghui.gxh.adapter.homeAdapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeItemListAdapter1 extends BaseQuickAdapter<HomeNewListBean, BaseViewHolder> {
    private List<String> images;
    private RequestOptions options;

    public HomeItemListAdapter1() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeNewListBean>() {
            @Override
            protected int getItemType(HomeNewListBean entity) {
                //根据你的实体类来判断布局类型
                return entity.getItemType();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(0, R.layout.item_text_text)
                .registerItemType(1, R.layout.item_right_img)
                .registerItemType(2, R.layout.item_three_img);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeNewListBean dataBean) {
        ImageView imageView = null;
        final String title = dataBean.getTitle();
        final String source = dataBean.getSource();
        final int view_cnt = dataBean.getView_cnt();
        final String ctime = dataBean.getCtime();
        images = dataBean.getImages();

        switch (dataBean.getItemType()) {
            case 0:
                //没有图片
                baseViewHolder.setText(R.id.tv_homelistItem_text_content, title);
                baseViewHolder.setText(R.id.tv_recycler_bottom_science, source);
                baseViewHolder.setText(R.id.tv_recycler_bottom_science2, String.valueOf(view_cnt));
                baseViewHolder.setText(R.id.tv_recycler_bottom_science3, ctime);
                break;
            case 1:
                //一张图片
                baseViewHolder.setText(R.id.tv_item_right_image_title, title);
                baseViewHolder.setText(R.id.tv_right_iamge_bottom_science, source);
                baseViewHolder.setText(R.id.tv_right_iamge_bottom_science2, String.valueOf(view_cnt));
                baseViewHolder.setText(R.id.tv_right_iamge_bottom_science3, ctime);
                imageView = baseViewHolder.getView(R.id.iv_item_right_iamge);

                options = new RequestOptions();
                options.placeholder(R.mipmap.default_img);
                options.error(R.mipmap.default_img);
                options.centerCrop();
                Glide.with(mContext).load(images.get(0)).apply(options).into(imageView);

                break;
            case 2:
                //三张图片的
                baseViewHolder.setText(R.id.tv_item_three_title, title);
                ImageView imageView1 = baseViewHolder.getView(R.id.iv_itemthree_imgfirst);
                ImageView imageView2 = baseViewHolder.getView(R.id.iv_itemthree_second);
                ImageView imageView3 = baseViewHolder.getView(R.id.iv_itemthree_third);
                if (images.size() < 3) {
                    options = new RequestOptions();
                    options.placeholder(R.mipmap.default_img);
                    options.error(R.mipmap.default_img);
                    options.centerCrop();
                    Glide.with(mContext).load(images.get(0)).apply(options).into(imageView1);
                    Glide.with(mContext).load(images.get(1)).apply(options).into(imageView2);


                } else {
                    options = new RequestOptions();
                    options.placeholder(R.mipmap.default_img);
                    options.error(R.mipmap.default_img);
                    options.centerCrop();
                    Glide.with(mContext).load(images.get(0)).apply(options).into(imageView1);
                    Glide.with(mContext).load(images.get(1)).apply(options).into(imageView2);
                    Glide.with(mContext).load(images.get(2)).apply(options).into(imageView3);

                }

                break;
        }
    }


}
