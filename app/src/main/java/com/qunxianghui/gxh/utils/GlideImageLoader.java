package com.qunxianghui.gxh.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.default_img);
        options.placeholder(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(options)
                .into(imageView);
    }

    public View getView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.item_banner, null);
    }

}
