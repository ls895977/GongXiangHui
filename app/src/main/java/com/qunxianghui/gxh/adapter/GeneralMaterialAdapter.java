package com.qunxianghui.gxh.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;

public class GeneralMaterialAdapter extends BaseQuickAdapter<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert, BaseViewHolder> {

    private RequestOptions mOptions;
    /**
     * 0为贴片 1为大图通栏 3为通栏 其他统一
     */
    private int mType;

    @SuppressLint("CheckResult")
    public GeneralMaterialAdapter(int layoutResId, int type) {
        super(layoutResId);
        this.mType = type;
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder helper, EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert item) {
        ImageView iv;
        switch (mType) {
            case 0:
                iv = helper.getView(R.id.iv_tiepian);
                helper.setVisible(R.id.iv_select_tiepian, item.isSelect);
                break;
            case 1:
                iv = helper.getView(R.id.iv_big);
                helper.setVisible(R.id.iv_select_big, item.isSelect);
                break;
            case 3:
                iv = helper.getView(R.id.iv_tonglan);
                helper.setVisible(R.id.iv_select_tonglang, item.isSelect);
                break;
            default:
                iv = helper.getView(R.id.iv_other);
                Glide.with(mContext).load(item.images).apply(new RequestOptions().placeholder(R.mipmap.default_img)
                        .error(R.mipmap.default_img)).into(iv);
                helper.setVisible(R.id.iv_select_other, item.isSelect);
                return;
        }
        Glide.with(mContext).load(item.images).apply(mOptions).into(iv);
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(View view) {
        switch (mType) {
            case 0:
                view.findViewById(R.id.rl_tiepian).setVisibility(View.VISIBLE);
                break;
            case 1:
                view.findViewById(R.id.rl_big).setVisibility(View.VISIBLE);
                break;
            case 3:
                view.findViewById(R.id.rl_tonglang).setVisibility(View.VISIBLE);
                break;
            default:
                view.findViewById(R.id.rl_other).setVisibility(View.VISIBLE);
                break;
        }
        return super.createBaseViewHolder(view);
    }
}
