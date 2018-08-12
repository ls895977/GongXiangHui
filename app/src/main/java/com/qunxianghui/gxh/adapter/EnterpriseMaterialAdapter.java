package com.qunxianghui.gxh.adapter;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;

public class EnterpriseMaterialAdapter extends BaseQuickAdapter<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert, BaseViewHolder> {

    private RequestOptions mOptions;

    @SuppressLint("CheckResult")
    public EnterpriseMaterialAdapter(int layoutResId) {
        super(layoutResId);
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder helper, EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert item) {
        ImageView iv = helper.getView(R.id.iv);
        Glide.with(mContext).load(item.images).apply(mOptions).into(iv);
        helper.setVisible(R.id.iv_select, item.isSelect);
    }
}
