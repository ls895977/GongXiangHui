package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.mine.MineMessageBean;

import java.util.List;

public class MineMessageCommentAdapter extends BaseQuickAdapter<MineMessageBean.DataBean, BaseViewHolder> {
    private final RequestOptions mOptions;
    public MineMessageCommentAdapter(@Nullable List<MineMessageBean.DataBean> data) {
        super(data);

        setMultiTypeDelegate(new MultiTypeDelegate<MineMessageBean.DataBean>() {
            @Override
            protected int getItemType(MineMessageBean.DataBean dataBean) {
                return dataBean.getType();
            }
        });
        getMultiTypeDelegate().registerItemType(0, R.layout.item_minemessage_addlike)
                .registerItemType(1, R.layout.item_minemessage_addcomment)
                .registerItemType(2, R.layout.item_minemessage_addcomment)
                .registerItemType(3, R.layout.item_minemessage_addcomment)
                .registerItemType(4, R.layout.item_minemessage_addcomment)
                .registerItemType(5, R.layout.item_minemessage_addcomment)
                .registerItemType(6, R.layout.item_minemessage_addcomment)
                .registerItemType(7, R.layout.item_minemessage_addcomment);
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MineMessageBean.DataBean item) {
        if (item != null) {
            ImageView imageHead = null;
            switch (item.getType()) {
                case 0:
                    baseViewHolder.setText(R.id.tv_minemessage_like_time, item.getTime());
                    imageHead = baseViewHolder.getView(R.id.iv_minemessage_head);
                    Glide.with(mContext).load(item.getMember_avatar()).apply(mOptions).into(imageHead);
                    break;
                case 1:
                    baseViewHolder.setText(R.id.tv_minemessage_comment_time, item.getTime());
                    baseViewHolder.setText(R.id.tv_minemessage_title, item.getDetail().getTitle());
                    Glide.with(mContext).load(item.getMember_avatar()).apply(mOptions).into(imageHead);
                    break;
            }
        }
    }


}
