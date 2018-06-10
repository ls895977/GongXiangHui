package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

public class MyFocusAdapter extends BaseRecycleViewAdapter<MyFocusBean.DataBean> {

    public MyFocusAdapter(Context context, List<MyFocusBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MyFocusBean.DataBean dataBean) {
        final ImageView headImave = holder.getView(R.id.iv_mine_focus_head);

        holder.setText(R.id.tv_mine_focus_title,dataBean.getMember_name());
        GlideApp.with(mContext).load(dataBean.getMember_avatar())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(headImave);

    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_myfocus_item;
    }
}
