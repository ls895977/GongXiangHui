package com.gongxianghui.adapter.homeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.gongxianghui.R;
import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.gongxianghui.interfaces.FilterListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class SearchAdapter extends BaseRecycleViewAdapter implements Filterable {
    private List<String> list = new ArrayList<String>();

    private FilterListener listener = null;// 接口对象
    private MyFilter filter = null;// 创建MyFilter对象

    public SearchAdapter(Context context, List datas, List<String> list, FilterListener listener) {
        super(context, datas);
        this.list = list;
        this.listener = listener;
    }

    public SearchAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
        holder.setText(R.id.tv_serch_des,list.get(position));
    }


    @Override
    protected int getItemView() {
        return R.layout.item_search;
    }



    @Override
    public Filter getFilter() {
        //如果MyFilter对象为空，那么久重新创建一个
        if(filter==null){
            filter=new MyFilter(list);
        }
        return filter;
    }

    class MyFilter extends Filter {
        //创建集合保存原始数据
        private List<String> original = new ArrayList<String>();

        public MyFilter(List<String> list) {
            this.original = list
            ;
        }

        /**
         * 该方法返回过滤后的数据
         *
         * @param constraint
         * @return
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            /**
             * 没有搜索内容的话就还是给results赋值原始数据的值和大小
             * 执行了搜索的话，根据搜索的规则过滤即可，最后把过滤后的数据的值和大小赋值给results
             */

            if (TextUtils.isEmpty(constraint)) {
                results.values = original;
                results.count = original.size();
            } else {
                //创建集合保存过滤后的数据
                List<String> mList = new ArrayList<String>();
                //遍历原始数据集合，根据搜索的规则过滤数据
                for (String s : original) {
                    //这里就是过滤数据的具体实现【规律有很多，大家可以自己决定怎么实现】
                    if (s.trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                        // 规则匹配的话就往集合中添加该数据
                        mList.add(s);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            //返回FilterResults对象
            return results;
        }

        /**
         * 该方法用来刷新用户界面 根据过滤后的数据重新展示列表
         *
         * @param constraint
         * @param results
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//回去过滤后的数据
            list = (List<String>) results.values;
            // 如果接口对象不为空，那么调用接口中的方法获取过滤后的数据，具体的实现在new这个接口的时候重写的方法里执行
            if (listener != null) {
                listener.getFilterData(list);
            }
            //刷新数据源显示
            notifyDataSetChanged();
        }
    }
}
