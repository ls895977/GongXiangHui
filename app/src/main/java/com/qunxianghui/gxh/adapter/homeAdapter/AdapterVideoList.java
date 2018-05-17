package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class AdapterVideoList extends BaseAdapter {
    public static final String TAG = "GongXiangHui";
    private
    Context context;
//    String[] videoUrls;
//    String[] videoTitles;
//   String[] videoThumbs;
    private List<HomeVideoListBean.DataBean.ListBean>videoData;

//    public AdapterVideoList(Context context, String[] videoUrls, String[] videoTitles, String[] videoThumbs) {
//        this.context = context;
//        this.videoUrls = videoUrls;
//        this.videoTitles = videoTitles;
//        this.videoThumbs = videoThumbs;
//    }


    public AdapterVideoList(Context context, List<HomeVideoListBean.DataBean.ListBean> videoData) {
        this.context = context;
        this.videoData = videoData;
    }

    @Override
    public int getCount() {

        return videoData.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final HomeVideoListBean.DataBean.ListBean listData = videoData.get(position);

        final String videpTitle = listData.getTitle();
        final String videoDetailUrl = listData.getUrl();
        final String video_url = listData.getVideo_url();
        final String picurl = listData.getPicurl();
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_videoview, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.jzVideoPlayer = convertView.findViewById(R.id.videoplayer);
        viewHolder.jzVideoPlayer.setUp(
                video_url, JZVideoPlayer.SCREEN_WINDOW_LIST, videpTitle
        );
        Glide.with(convertView.getContext()).load(picurl).into(viewHolder.jzVideoPlayer.thumbImageView);
        viewHolder.jzVideoPlayer.positionInList = position;


        return convertView;
    }

    class ViewHolder {
        JZVideoPlayerStandard jzVideoPlayer;
    }
}
