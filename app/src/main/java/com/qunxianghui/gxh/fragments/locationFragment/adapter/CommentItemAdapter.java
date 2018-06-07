package com.qunxianghui.gxh.fragments.locationFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.widget.BigListView;

import java.util.List;

public class CommentItemAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<CommentBean> mList;
    private ListView listView;

    public CommentItemAdapter(Context context, List<CommentBean> mList,ListView listView) {
        this.context = context;
        this.mList = mList;
        this.listView=listView;

        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 删除item
     */
    public void deleteItemView(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void refreshData(List<CommentBean> cBeans) {
        this.mList = cBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_comment, parent, false);
            holder.name=convertView.findViewById(R.id.name);
            holder.content=convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        showView(holder, position, parent);

        return convertView;
    }

    private void showView(ViewHolder holder, int position, ViewGroup parent) {

        holder.name.setText(mList.get(position).getMember_name());
        holder.content.setText(mList.get(position).getContent());

    }


    public static class ViewHolder {
        TextView name,content;


    }


}
