package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.ProvinceBean;

import java.util.List;

public class ProvinceAdapter extends BaseRecycleViewAdapter <ProvinceBean.DataBean>{
    public ProvinceAdapter(Context context, List<ProvinceBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, ProvinceBean.DataBean dataBean) {
        holder.setText(R.id.tv_common_companyset,dataBean.getProvincename());

    }

    @Override
    protected int getItemView() {
        return R.layout.item_provice_text;
    }
}
