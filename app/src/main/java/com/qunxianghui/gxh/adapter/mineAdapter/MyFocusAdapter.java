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

public myFocusItemClickListener mMyFocusItemClickListener;

    public void setMyFocusItemClickListener(myFocusItemClickListener myFocusItemClickListener) {
        mMyFocusItemClickListener = myFocusItemClickListener;
    }

    public MyFocusAdapter(Context context, List<MyFocusBean.DataBean> datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, final int position, MyFocusBean.DataBean dataBean) {
        final ImageView headImave = holder.getView(R.id.iv_mine_focus_head);
        TextView mMyFocusFollow = holder.getView(R.id.tv_myfocus_follow);
        final TextView mFocusLevelType = holder.getView(R.id.tv_myfocus_leveltype);

        int follow_type = dataBean.getFollow_type();
        if (follow_type == 0) {
            mMyFocusFollow.setText("已关注");
        } else {
            mMyFocusFollow.setText("互相关注");
        }
        String level_type = dataBean.getLevel_type();
        if (level_type.equals("1")) {
            mFocusLevelType.setText("企业会员");
        } else {
            mFocusLevelType.setText("注册会员");
        }

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

        mMyFocusFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyFocusItemClickListener.FocusClick(position);
            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_myfocus_item;
    }

    public  interface myFocusItemClickListener{
        void FocusClick(int position);
    }
}
