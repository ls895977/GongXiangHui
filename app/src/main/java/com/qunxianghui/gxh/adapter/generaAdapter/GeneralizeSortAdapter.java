package com.qunxianghui.gxh.adapter.generaAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

public class GeneralizeSortAdapter extends BaseRecycleViewAdapter<EmployeePaiHangBean.DataBean> {

    private RequestOptions mOptions = new RequestOptions();

    @SuppressLint("CheckResult")
    public GeneralizeSortAdapter(Context context, List<EmployeePaiHangBean.DataBean> datas) {
        super(context, datas);
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(MyViewHolder holder, int position, EmployeePaiHangBean.DataBean dataBean) {
        RoundImageView generalizePersonHead = holder.getView(R.id.iv_generalize_person_head);
        TextView mTvCount = holder.getView(R.id.tv_generalize_person_count);
        holder.setText(R.id.iv_generalize_person_name, dataBean.getMember_name());
        String textSource = "<font color='#D81717'>%s</font><font color='#999999'>次</font>";
        mTvCount.setText(Html.fromHtml(String.format(textSource, dataBean.getCnt())));

        Glide.with(mContext)
                .load(dataBean.getMember_avatar())
                .apply(mOptions)
                .into(generalizePersonHead);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_generalize_sort;
    }
}
