package com.qunxianghui.gxh.adapter.locationAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private List<String> imageUrls;
    private AppCompatActivity activity;

    public ImageAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
        photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        ViewGroup.MarginLayoutParams layout = new ViewGroup.MarginLayoutParams(width,height);

        layout.setMargins(0,100,0,100);

//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(photoView.getLayoutParams());
//        int offset = 100;
//        lp.setMargins(0, offset, 0, offset);
//        photoView.setLayoutParams(lp);
        photoView.setLayoutParams(layout);

        GlideApp.with(activity)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
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
}
