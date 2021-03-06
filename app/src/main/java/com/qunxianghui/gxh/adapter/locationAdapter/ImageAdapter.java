package com.qunxianghui.gxh.adapter.locationAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.qunxianghui.gxh.R;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private List<String> imageUrls;
    private AppCompatActivity activity;
    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public ImageAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(container.getContext());
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        ViewGroup.MarginLayoutParams layout = new ViewGroup.MarginLayoutParams(width, height);
        photoView.setLayoutParams(layout);
        if (url.toString().endsWith(".gif")){
            Glide.with(activity).asGif().load(url).apply(new RequestOptions().error(R.mipmap.default_img)).transition(new DrawableTransitionOptions().crossFade()).into(photoView);
        }else {
            Glide.with(activity).load(url).apply(new RequestOptions().error(R.mipmap.default_img)).transition(new DrawableTransitionOptions().crossFade()).into(photoView);
        }

        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemClick.PicLongClick(position, url);
                return true;
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    public interface OnItemClick {
        void PicLongClick(int position, String url);
    }
}
