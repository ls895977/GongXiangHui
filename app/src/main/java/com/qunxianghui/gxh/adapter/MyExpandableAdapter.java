package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.DataCityInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private List<DataCityInfo.DataBean.CityBean> header; // header titles
    private HashMap<DataCityInfo.DataBean.CityBean, List<DataCityInfo.DataBean.CityBean.AreasBean>> child;

//	public MyExpandableAdapter(List<String> listDataHeader, HashMap<String, List<String>> listChildData) {
//		this.header = listDataHeader;
//		this.child = listChildData;
//	}

    public MyExpandableAdapter(DataCityInfo bean) {
        header = new ArrayList<>();

        //HashMap
        child = new HashMap<>();

        // 添加header
        DataCityInfo.DataBean dataBean = bean.getData();
        header.addAll(dataBean.getA());
        header.addAll(dataBean.getB());
        header.addAll(dataBean.getC());
        header.addAll(dataBean.getD());
        header.addAll(dataBean.getE());
        header.addAll(dataBean.getF());
        header.addAll(dataBean.getG());
        header.addAll(dataBean.getH());
        header.addAll(dataBean.getJ());
        header.addAll(dataBean.getK());
        header.addAll(dataBean.getL());
        header.addAll(dataBean.getM());
        header.addAll(dataBean.getN());
        header.addAll(dataBean.getP());
        header.addAll(dataBean.getQ());
        header.addAll(dataBean.getR());
        header.addAll(dataBean.getS());
        header.addAll(dataBean.getT());
        header.addAll(dataBean.getW());
        header.addAll(dataBean.getX());
        header.addAll(dataBean.getY());
        header.addAll(dataBean.getZ());
        for (int i = 0; i < header.size(); i++) {
            DataCityInfo.DataBean.CityBean key = header.get(i);
            List<DataCityInfo.DataBean.CityBean.AreasBean> areas = key.getAreas();
            child.put(key, areas);
        }

    }


    @Override
    public int getGroupCount() {
        return this.header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // return children count
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // Get header position
        return this.header.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // This will return the child
        return this.child.get(this.header.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        // Getting header title
        DataCityInfo.DataBean.CityBean cityBean = (DataCityInfo.DataBean.CityBean) getGroup(groupPosition);
        String headerTitle = cityBean.getCity();

        // Inflating header layout and setting text
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header);
        header_text.setText(headerTitle);
        TextView index_tv = (TextView) convertView.findViewById(R.id.index_tv);
        if (groupPosition == 0){
            index_tv.setVisibility(View.VISIBLE);
            index_tv.setText(cityBean.getPinyin());
        }



        // If group is expanded then change the text into bold and change the
        // icon
        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Getting child text


        DataCityInfo.DataBean.CityBean.AreasBean areasBean
                =
                (DataCityInfo.DataBean.CityBean.AreasBean) getChild(groupPosition, childPosition);
        final String childText = areasBean.getAreaname();

        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childs, parent, false);
        }

        TextView child_text = (TextView) convertView.findViewById(R.id.child);

        child_text.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
