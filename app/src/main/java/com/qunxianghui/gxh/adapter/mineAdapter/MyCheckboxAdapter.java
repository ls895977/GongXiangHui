package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2018/7/23.
 */

public class MyCheckboxAdapter extends RecyclerView.Adapter<MyCheckboxAdapter.MyCheckBoxViewHolder>{
    //这个是checkbox的Hashmap集合
    private final HashMap<Integer, Boolean> map;
    //这个是数据集合
    private final  ArrayList<String> list;

    public MyCheckboxAdapter() {
        map=new HashMap<>();
        list=new ArrayList<>();
        for (int i=0;i<30;i++){
            //添加30条数据
            list.add("这是条目"+i);
            map.put(i,false);
        }
    }

    /*全选*/

    public void All(){
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        boolean shoulddall=false;
        for (Map.Entry<Integer,Boolean> entry:entries){
            Boolean value = entry.getValue();
            if (!value){
                shoulddall=true;
                break;
            }
        }
        for (Map.Entry<Integer,Boolean> entry:entries){
            entry.setValue(shoulddall);
        }
        notifyDataSetChanged();
    }
    /*f反选*/
    public void neverall() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }
    /**
     * 单选
     *
     * @param postion
     */
    public void singlesel(int postion) {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(postion, true);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyCheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
        return new MyCheckBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCheckBoxViewHolder holder, final int position) {
//放入集合中的值
        holder.text.setText(list.get(position));
        holder.checkBox.setChecked(map.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(position,!map.get(position));
                //刷新适配器
                notifyDataSetChanged();

                //单选
//                singlesel(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyCheckBoxViewHolder extends RecyclerView.ViewHolder{
       private TextView text;
       private CheckBox checkBox;
        public MyCheckBoxViewHolder(View itemView) {
            super(itemView);
            text  = itemView.findViewById(R.id.txt);
            checkBox = itemView.findViewById(R.id.cbox);
        }
    }
}
