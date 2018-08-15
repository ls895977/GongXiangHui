package com.qunxianghui.gxh.adapter.generaAdapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

public class GeneralizeSortAdapter extends BaseQuickAdapter<EmployeePaiHangBean.EmployeePaiHang.DataBean, BaseViewHolder> {

    private RequestOptions mOptions = new RequestOptions();

    @SuppressLint("CheckResult")

    public GeneralizeSortAdapter(@Nullable List<EmployeePaiHangBean.EmployeePaiHang.DataBean> data) {
        super(R.layout.item_generalize_sort, data);
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(BaseViewHolder helper, EmployeePaiHangBean.EmployeePaiHang.DataBean item) {
        TextView mTvRank = helper.getView(R.id.tv_rank);
        mTvRank.setText("");
        mTvRank.setBackground(null);
        if (helper.getAdapterPosition() == 1) {
            mTvRank.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.genera_company_sort_one));
        } else if (helper.getAdapterPosition() == 2) {
            mTvRank.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.genera_company_sort_two));
        } else if (helper.getAdapterPosition() == 3) {
            mTvRank.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.genera_company_sort_three));
        } else {
            mTvRank.setText(String.format("%s.", helper.getAdapterPosition()));
        }
        RoundImageView generalizePersonHead = helper.getView(R.id.iv_generalize_person_head);
        TextView mTvCount = helper.getView(R.id.tv_generalize_person_count);
        helper.setText(R.id.iv_generalize_person_name, item.member_name);
        String textSource = "<font color='#D81717'>%s</font><font color='#999999'>次</font>";
        mTvCount.setText(Html.fromHtml(String.format(textSource, item.cnt)));
        Glide.with(mContext)
                .load(item.member_avatar)
                .apply(mOptions)
                .into(generalizePersonHead);
    }
}


