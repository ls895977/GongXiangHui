package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends BaseAdapter implements Filterable {

    private List<String> list = new ArrayList<String>();
    private Context context;
    private FilterListener listener = null;
    private MyFilter filter;

    public SearchListAdapter(List<String> list, Context context, FilterListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_list_item, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.tv_search_list_des = convertView.findViewById(R.id.tv_search_des);
            convertView.setTag(myViewHolder);
        }
        myViewHolder = (MyViewHolder) convertView.getTag();
        myViewHolder.tv_search_list_des.setText(list.get(position));
        return convertView;
    }

    /**
     * 自定义MyAdapter类实现；了Filterable接口 重写了该方法
     */
    @Override
    public Filter getFilter() {
        //如果Filter对象为空 那么重新创建一个
if(filter==null) {
    filter = new MyFilter(list);
}
        return filter;
    }

    //创建内部类MyFilter 继承Filter类 并重写相关方法 实现数据的过滤
    class MyFilter extends Filter {
        //创建集合保存原始数据
        private List<String> original = new ArrayList<String>();

        public MyFilter(List<String> list) {
            this.original = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //创建filterResult对象
            final FilterResults results = new FilterResults();
            /**
             * 没有搜索内容的话就还是给results赋值原始数据的值和大小
             * 执行了搜索的话，根据搜索的规则过滤即可，最后把过滤后的数据的值和大小赋值给results
             *
             */
            if (TextUtils.isEmpty(constraint)) {
                results.values = original;
                results.count = original.size();
            } else {
                //创建集合保存过滤后的数据‘
                List<String> mList = new ArrayList<String>();
                //遍历原始数据的集合 根据搜索的规则去过滤数据
                for (String s : original) {
                    //过滤数据的具体实现  根据需求修改
                    if (s.trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                        // 规则匹配的话就往集合中添加该数据
                        mList.add(s);

                    }

                }

                results.values = mList;
                results.count = mList.size();
            }

            return results;
        }

        //该方法用来刷新用户界面 根据过滤后的数据重新展示
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //获取过滤后的数据’
            list = (List<String>) results.values;
            // 如果接口对象不为空，那么调用接口中的方法获取过滤后的数据，具体的实现在new这个接口的时候重写的方法里执行
            if (listener != null) {
                listener.getFilterData(list);

            }
            //刷新数据展示
            notifyDataSetChanged();
        }
    }


    class MyViewHolder {
        TextView tv_search_list_des;
    }

    public interface FilterListener {
        void getFilterData(List<String> list);// 获取过滤后的数据
    }
}
