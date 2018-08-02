package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyFocusBean;

import java.util.List;

public class MyFocusAdapter extends BaseRecycleViewAdapter<MyFocusBean.DataBean> {


    public MyFocusAdapter(Context context, List<MyFocusBean.DataBean> datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, int position, MyFocusBean.DataBean dataBean) {
        final ImageView headImave = holder.getView(R.id.iv_mine_focus_head);

        TextView mMyFocusFollow = holder.getView(R.id.tv_myfocus_follow);
        holder.setText(R.id.tv_mine_focus_title, dataBean.getMember_name());

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getMember_avatar()).apply(options).into(headImave);

        mMyFocusFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了关注", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_myfocus_item;
    }
}
