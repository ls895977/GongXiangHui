package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyClollectPostBean;




import java.util.List;


public class MineCollectPostAdapter extends BaseRecycleViewAdapter <MyClollectPostBean.DataBean>{


    public MineCollectPostAdapter(Context context, List<MyClollectPostBean.DataBean> datas) {
        super(context, datas);
    }



    @Override
    protected void convert(MyViewHolder holder, int position, MyClollectPostBean.DataBean dataBean) {
        final String title = dataBean.getInfo().getTitle();

        holder.setText(R.id.tv_mycollect_post_title,title);

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mycollect_post;
    }
}
