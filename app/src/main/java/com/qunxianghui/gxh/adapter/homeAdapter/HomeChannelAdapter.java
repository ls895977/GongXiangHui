package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class HomeChannelAdapter extends BaseAdapter {
    private Context context;
    private List<String> channelList;
    /**
     * 是否可见  在移动动画之前不可见  动画完毕后可见
     */
    private boolean isVisible = true;
    /**
     * 要删除的position
     */
    public int remove_position = -1;
    /**
     * 是否是用户频道
     */
    private boolean isUser = false;

    public HomeChannelAdapter(Context context, List<String> channelList, boolean isUser) {
        this.context = context;
        this.channelList = channelList;
        this.isUser = isUser;
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public Object getItem(int position) {
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(position);

        }
        return null;


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if ((convertView == null)) {
            viewHoler = new ViewHoler();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_mygridview_item, parent, false);
            viewHoler.icon_new = convertView.findViewById(R.id.icon_img);
            viewHoler.text_item = convertView.findViewById(R.id.text_item);

            convertView.setTag(viewHoler);

        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        String channel = (String) getItem(position);
        viewHoler.text_item.setText(channel);
        if (isUser) {
            if (position == 0 || (position == 1)) {
                viewHoler.text_item.setEnabled(false);
            }
        }
        if (!isVisible && (position == -1 + channelList.size())) {
            viewHoler.text_item.setText("");
            viewHoler.text_item.setSelected(true);
            viewHoler.text_item.setEnabled(true);

        }
        if (remove_position == position) {
            viewHoler.text_item.setText("");

        }

        return convertView;
    }

    /**
     * 获取频道列表
     */
    public List<String> getChannelList() {
        return channelList;
    }


    /**
     * 添加频道列表
     */
    public void addItem(String channel) {
        channelList.add(channel);
        notifyDataSetChanged();
    }

    /**
     * 设置删除的position
     */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }

    /**
     * 删除频道列表
     */
    public void remove() {
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

    /**
     * 设置频道列表
     */
    public void setListDate(List<String> list) {
        channelList = list;
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }


    public class ViewHoler {

        public TextView text_item;
        public ImageView icon_new;
    }
}
