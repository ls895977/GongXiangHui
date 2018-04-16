package com.qunxianghui.gxh.fragments.locationFragment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;

    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {

        GlideApp
                .with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {

        GlideApp
                .with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @Override
    protected void onClickImage(int i, String url, List<String> urlList) {
        Toast.makeText(mContext, "点击了图片" + url, Toast.LENGTH_SHORT).show();
    }
}
