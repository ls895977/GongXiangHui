package com.qunxianghui.gxh.adapter.mineAdapter;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;
        import com.qunxianghui.gxh.R;
        import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
        import com.qunxianghui.gxh.bean.mine.MineFansBean;

        import java.util.List;

public class MyFansAdapter extends BaseRecycleViewAdapter<MineFansBean.DataBean> {
    private myFansItemClickListener mMyFansItemClickListener;
    public void setMyFansItemClickListener(myFansItemClickListener myFansItemClickListener) {
        mMyFansItemClickListener = myFansItemClickListener;
    }

    public MyFansAdapter(Context context, List<MineFansBean.DataBean> datas) {
        super(context, datas);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(MyViewHolder holder, final int position, MineFansBean.DataBean dataBean) {
        final ImageView headImave = holder.getView(R.id.iv_mine_fans_head);
        final ImageView mMyFansLevelType = holder.getView(R.id.iv_myfans_leveltype);
        final TextView mMyFansFollow = holder.getView(R.id.tv_myfocus_follow);
        holder.setText(R.id.tv_mine_fans_title, dataBean.getMember_name());
        int follow_type = dataBean.getFollow_type();
        if (follow_type == 0) {
            mMyFansFollow.setText("关注");
        } else {
            mMyFansFollow.setText("互相关注");
        }
        String level_type = dataBean.getLevel_type();
        if (level_type.equals("1")) {
            mMyFansLevelType.setImageResource(R.mipmap.icon_fans_company);

        } else {
            mMyFansLevelType.setImageResource(R.mipmap.icon_fans_regist);

        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.placeholder(R.mipmap.default_img);
        Glide.with(mContext).load(dataBean.getMember_avatar()).apply(options).into(headImave);

        mMyFansFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyFansItemClickListener.FansClick(position);
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_myfans_item;
    }

    public interface myFansItemClickListener {
        void FansClick(int position);
    }
}
