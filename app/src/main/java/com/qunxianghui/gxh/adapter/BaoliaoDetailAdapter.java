package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;

import java.util.List;

public class BaoliaoDetailAdapter extends BaseRecycleViewAdapter<BaoliaoBean.DataBean> {

    public boolean isShow = false;
    public BaoliaoDetailAdapter(Context context, List<BaoliaoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final BaoliaoBean.DataBean dataBean) {

        if (!TextUtils.isEmpty(dataBean.getContent())) {
            holder.setText(R.id.tv_baoliao_content,dataBean.getContent());
        }


        LinearLayout llImagLayout = holder.getView(R.id.ll_img_layout);
        if(dataBean.getImages()!=null && !dataBean.getImages().isEmpty()) {
            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.default_img)
                    .error(R.mipmap.default_img)
                    .centerCrop();
            for (int i = 0; i < dataBean.getImages().size(); i++) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 560);
                layoutParams.setMargins(0,20,0,20);
                imageView.setLayoutParams(layoutParams);
                Glide.with(mContext).load(dataBean.getImages().get(i)).apply(requestOptions).into(imageView);
                llImagLayout.addView(imageView);
            }
        }
    }

    @Override
    protected int getItemView() {
        return R.layout.item_baoliao_detail;
    }
}
