package com.qunxianghui.gxh.adapter.homeAdapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.SearchBean;

import java.util.List;

/**
 * @author 小强
 * @time 2018/5/29  11:28
 * @desc
 */
public class SearchFragmentAdapter extends BaseMultiItemQuickAdapter<SearchBean.DataBean.ListBean, BaseViewHolder> {

    public static final int TYPE_0 = 0;//没有图片
    public static final int TYPE_1 = 1;//一张图片
    public static final int TYPE_2 = 2;//二张图片
    public static final int TYPE_3 = 3;//三张图片
    private RequestOptions mOptions;

    public SearchFragmentAdapter(List<SearchBean.DataBean.ListBean> data) {
        super(data);
        addItemType(TYPE_0, R.layout.item_text_text);
        addItemType(TYPE_1, R.layout.item_left_img);
        addItemType(TYPE_2, R.layout.item_three_img);
        addItemType(TYPE_3, R.layout.item_three_img);
        mOptions = new RequestOptions()
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.DataBean.ListBean item) {
        int itemViewType = helper.getItemViewType();
        int type = Integer.valueOf(item.getType());
        String content;
        String source = item.getSource();
        String ctime = item.getCtime();
        int viewCnt = item.getView_cnt();
        List<String> images = item.getImages();
        switch (type) {
            case TYPE_0:
            case TYPE_1:
                content = item.getTitle();
                switch (itemViewType) {
                    case TYPE_0:
                        helper.setText(R.id.tv_homelistItem_text_content, content);
                        helper.setText(R.id.tv_recycler_bottom_science, source);
                        helper.setText(R.id.tv_recycler_bottom_science2, String.valueOf(viewCnt));
                        helper.setText(R.id.tv_recycler_bottom_science3, ctime);
                        helper.getView(R.id.tv_recycler_bottom_science).setVisibility(View.VISIBLE);
                        helper.getView(R.id.tv_recycler_bottom_science2).setVisibility(View.VISIBLE);
                        break;
                    case TYPE_1:
                        helper.setText(R.id.tv_item_right_image_title, content);
                        helper.setText(R.id.tv_right_iamge_bottom_science, source);
                        helper.setText(R.id.tv_right_iamge_bottom_science2, String.valueOf(viewCnt));
                        helper.setText(R.id.tv_right_iamge_bottom_science3, ctime);
                        ImageView imageView = helper.getView(R.id.iv_item_right_iamge);
                        helper.getView(R.id.tv_right_iamge_bottom_science).setVisibility(View.VISIBLE);
                        helper.getView(R.id.tv_right_iamge_bottom_science2).setVisibility(View.VISIBLE);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into(imageView);
                        break;
                    case TYPE_2:
                        helper.setText(R.id.tv_item_three_title, content);
                        ImageView imageView1 = helper.getView(R.id.iv_itemthree_imgfirst);
                        ImageView imageView2 = helper.getView(R.id.iv_itemthree_second);
                        helper.getView(R.id.iv_itemthree_third).setVisibility(View.INVISIBLE);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into(imageView1);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into(imageView2);
                        break;
                    case TYPE_3:
                        helper.setText(R.id.tv_item_three_title, content);
                        helper.getView(R.id.iv_itemthree_third).setVisibility(View.VISIBLE);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_imgfirst));
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_second));
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_third));
                        break;
                }
                break;
            case TYPE_2:
                content = item.getContent();
                switch (itemViewType) {
                    case TYPE_0:
                        helper.setText(R.id.tv_homelistItem_text_content, content);
                        helper.setText(R.id.tv_recycler_bottom_science3, ctime);
                        helper.getView(R.id.tv_recycler_bottom_science).setVisibility(View.GONE);
                        helper.getView(R.id.tv_recycler_bottom_science2).setVisibility(View.GONE);
                        break;
                    case TYPE_1:
                        helper.setText(R.id.tv_item_right_image_title, content);
                        helper.setText(R.id.tv_right_iamge_bottom_science3, ctime);
                        helper.getView(R.id.tv_right_iamge_bottom_science).setVisibility(View.GONE);
                        helper.getView(R.id.tv_right_iamge_bottom_science2).setVisibility(View.GONE);
                        ImageView imageView = helper.getView(R.id.iv_item_right_iamge);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into(imageView);
                        break;
                    case TYPE_2:
                        helper.setText(R.id.tv_item_three_title, content);
                        ImageView imageView1 = helper.getView(R.id.iv_itemthree_imgfirst);
                        ImageView imageView2 = helper.getView(R.id.iv_itemthree_second);
                        helper.getView(R.id.iv_itemthree_third).setVisibility(View.INVISIBLE);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into(imageView1);
                        Glide.with(mContext).load(images.get(1)).apply(mOptions).into(imageView2);
                        break;
                    case TYPE_3:
                        helper.setText(R.id.tv_item_three_title, content);
                        helper.getView(R.id.iv_itemthree_third).setVisibility(View.VISIBLE);
                        Glide.with(mContext).load(images.get(0)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_imgfirst));
                        Glide.with(mContext).load(images.get(1)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_second));
                        Glide.with(mContext).load(images.get(2)).apply(mOptions).into((ImageView) helper.getView(R.id.iv_itemthree_third));
                        break;
                }
                break;
        }
    }
}
