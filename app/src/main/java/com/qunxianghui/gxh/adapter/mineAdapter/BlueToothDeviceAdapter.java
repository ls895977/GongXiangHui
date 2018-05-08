package com.qunxianghui.gxh.adapter.mineAdapter;



import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.List;


public class BlueToothDeviceAdapter extends BaseAdapter {
    private Context context;
private List<BluetoothDevice> mList;

private LayoutInflater layoutInflater;

    public BlueToothDeviceAdapter(Context context, List<BluetoothDevice> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater=LayoutInflater.from(context);
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
        MyViewHolder myViewHolder = new MyViewHolder();
        if (convertView==null){
            //首先为view绑定布局
           convertView= layoutInflater.inflate(R.layout.bluetooth_device_list_item,null);
            myViewHolder.name = convertView.findViewById(R.id.bluetoothname);
            myViewHolder.uuid=convertView.findViewById(R.id.uuid);
            myViewHolder.status=convertView.findViewById(R.id.status);
            convertView.setTag(myViewHolder);

        }else {
           myViewHolder= (MyViewHolder) convertView.getTag();
        }
        final BluetoothDevice device = mList.get(position);
        myViewHolder.name.setText(device.getName());
        myViewHolder.uuid.setText(device.getAddress());


        return convertView;
    }

    class  MyViewHolder{
        private TextView name,uuid,status;
    }
}
