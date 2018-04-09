package com.gongxianghui.adapter.homeAdapter;

import android.content.Context;

import com.gongxianghui.R;
import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.gongxianghui.bean.home.MainPageBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class MarqueenMessageAdapter extends BaseRecycleViewAdapter <MainPageBean.DataBean.MessageBean> {

    public MarqueenMessageAdapter(Context context, List<MainPageBean.DataBean.MessageBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MainPageBean.DataBean.MessageBean messageBean) {
        holder.setText(R.id.tv_marquee_message,messageBean.getTitle());
    }

    @Override
    protected int getItemView() {
        return 0;
    }
}
