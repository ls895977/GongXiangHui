package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<String> list=new ArrayList<>();
private Context context;
private LayoutInflater layoutInflater;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.xrecyc_adapter, parent, false);
        final HoldView holdView = new HoldView(view);

        return holdView;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final String s = list.get(position);
        ((HoldView) holder).textView.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class HoldView extends  RecyclerView.ViewHolder{
        private TextView textView;
        public HoldView(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.xrecyc_text);
        }
    }
}
