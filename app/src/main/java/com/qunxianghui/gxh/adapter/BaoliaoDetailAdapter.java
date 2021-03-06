package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.BaoliaoDetailContentBean;

import java.util.List;

public class BaoliaoDetailAdapter extends BaseRecycleViewAdapter<BaoliaoDetailContentBean> {

    public boolean isShow = false;

    public BaoliaoDetailAdapter(Context context, List<BaoliaoDetailContentBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final BaoliaoDetailContentBean dataBean) {

        if (!TextUtils.isEmpty(dataBean.getText())) {
            holder.setText(R.id.tv_baoliao_content, dataBean.getText());
        }


        LinearLayout llImagLayout = holder.getView(R.id.ll_img_layout);
        String img = dataBean.getImg();
        Log.e("TAG_詳情adapter","img="+img);
        if (!TextUtils.isEmpty(img)) {
            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.default_img)
                    .error(R.mipmap.default_img)
                    .centerCrop();

            String[] imgSplits = new String[1];
            if(img.contains(",")) {
                imgSplits = img.split(",");
            } else{
                imgSplits[0] = img;
            }
            for (int i = 0; i < imgSplits.length; i++) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 560);
                layoutParams.setMargins(0, 20, 0, 20);
                imageView.setLayoutParams(layoutParams);
                Glide.with(mContext).load(imgSplits[i]).apply(requestOptions).into(imageView);
                llImagLayout.addView(imageView);
            }
        }
    }

    @Override
    protected int getItemView() {
        return R.layout.item_baoliao_detail;
    }
}
