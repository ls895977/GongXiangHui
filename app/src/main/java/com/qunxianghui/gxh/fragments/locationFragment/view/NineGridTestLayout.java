package com.qunxianghui.gxh.fragments.locationFragment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;

import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;
    private RequestOptions options;

    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {

        options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(url).apply(options).into(imageView);


        return false;
    }

    @Override
    protected void displayImage(final RatioImageView imageView, String url) {

        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(url).apply(options).into(imageView);


    }

    @Override
    protected void onClickImage(int positon, String url, List<String> urlList) {
        Toast.makeText(mContext, "点击了图片" +positon, Toast.LENGTH_SHORT).show();
    }
}
