package com.qunxianghui.gxh.adapter.homeAdapter;


import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.greendao.MyDaoHelper;
import com.qunxianghui.gxh.greendao.NewsEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeItemListAdapter extends BaseQuickAdapter<HomeNewListBean, BaseViewHolder> {

    private List<String> images;
    private RequestOptions options;

    @SuppressLint("CheckResult")
    public HomeItemListAdapter() {
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
                .registerItemType(1, R.layout.item_left_img)
                .registerItemType(2, R.layout.item_left_img);
        options = new RequestOptions();
        options.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
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
                baseViewHolder.setText(R.id.tv_homelistItem_text_content, title)
                        .setText(R.id.tv_recycler_bottom_science, source)
                        .setText(R.id.tv_recycler_bottom_science2, String.valueOf(view_cnt))
                        .setText(R.id.tv_recycler_bottom_science3, ctime);
                if (dataBean.isReaded){
                    TextView tv = baseViewHolder.getView(R.id.tv_homelistItem_text_content);
                    tv.setTextColor(tv.getResources().getColor(R.color.gray17));
                }
                break;
            case 1:
                //一张图片
                baseViewHolder.setText(R.id.tv_item_right_image_title, title)
                        .setText(R.id.tv_right_iamge_bottom_science, source)
                        .setText(R.id.tv_right_iamge_bottom_science2, String.valueOf(view_cnt))
                        .setText(R.id.tv_right_iamge_bottom_science3, ctime);

                imageView = baseViewHolder.getView(R.id.iv_item_right_iamge);
                Glide.with(mContext).load(images.get(0)).apply(options).into(imageView);
                if (dataBean.isReaded){
                    TextView tv = baseViewHolder.getView(R.id.tv_item_right_image_title);
                    tv.setTextColor(tv.getResources().getColor(R.color.gray17));
                }
                break;
            case 2:
                baseViewHolder.setText(R.id.tv_item_right_image_title, title)
                        .setText(R.id.tv_right_iamge_bottom_science, source)
                        .setText(R.id.tv_right_iamge_bottom_science2, String.valueOf(view_cnt))
                        .setText(R.id.tv_right_iamge_bottom_science3, ctime);

                imageView = baseViewHolder.getView(R.id.iv_item_right_iamge);
                Glide.with(mContext).load(images.get(0)).apply(options).into(imageView);

                if (dataBean.isReaded){
                    TextView tv = baseViewHolder.getView(R.id.tv_item_right_image_title);
                    tv.setTextColor(tv.getResources().getColor(R.color.gray17));
                }
                break;
        }
    }


}
