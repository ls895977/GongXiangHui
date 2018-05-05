package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.BlueToothDeviceAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlueToothTim extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_open_bluetooth)
    Button btOpenBluetooth;
    @BindView(R.id.bt_search_bluetooth)
    Button btSearchBluetooth;
    @BindView(R.id.bt_bluetppth_send_message)
    Button btBluetppthSendMessage;
    @BindView(R.id.lv_blue_tooth)
    ListView lvBlueTooth;
    @BindView(R.id.text_state)
    TextView textState;
    @BindView(R.id.tv_blue_text_msgtext_msg)
    TextView tvBlueTextMsgtextMsg;
    private BluetoothAdapter bTAdapter;
    private BlueToothDeviceAdapter bluetoothAdapter;
    private static final UUID BT_UUID = UUID.fromString("02001101-0001-1000-8080-00805F9BA9BA");

    private final int BUFFER_SIZE = 1024;
    private ConnectThread connectThread;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bluetooth_tim;
    }

    @Override
    protected void initViews() {
        bTAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    protected void initDatas() {
        bTAdapter.startDiscovery();


//bTAdapter.cancelDiscovery();
        // 对应 开始搜索 搜索结束  搜索结束

//        BluetoothAdapter.ACTION_DISCOVERY_STARTED、
//        BluetoothDevice.ACTION_FOUND、
//        BluetoothAdapter.ACTION_DISCOVERY_FINISHED。
        //蓝牙需要的广播
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    //避免重复添加已经绑定过的设备
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        bluetoothAdapter.add(device);
                        bluetoothAdapter.notifyDataSetChanged();
                    }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    asyncShowToast("开始搜索");
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    asyncShowToast("搜索完毕");
                }
            }
        };
        //获取配对过的蓝牙设备
        final Set<BluetoothDevice> pairedDevices = bTAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                //添加数据
                bluetoothAdapter.add(device);

            }
        }

        bluetoothAdapter = new BlueToothDeviceAdapter(getApplicationContext(), R.layout.bluetooth_device_list_item);
        lvBlueTooth.setAdapter(bluetoothAdapter);
        lvBlueTooth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bTAdapter.isDiscovering()) {
                    bTAdapter.cancelDiscovery();
                }
                final BluetoothDevice device = bluetoothAdapter.getItem(position);
                //链接设备
                connectDevice(device);
            }
        });

    }

    private void connectDevice(BluetoothDevice device) {
        textState.setText("连接中");
        try {
            final BluetoothSocket socket = device.createRfcommSocketToServiceRecord(BT_UUID);
            //启动连接线程
            connectThread = new ConnectThread(socket, true);
            connectThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btOpenBluetooth.setOnClickListener(this);
        btSearchBluetooth.setOnClickListener(this);
        btBluetppthSendMessage.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open_bluetooth:
                openBlueTooth();
                break;
            case R.id.bt_search_bluetooth:
                searchDevices();
                break;
            case R.id.bt_bluetppth_send_message:
                break;
        }

    }
    private void searchDevices() {
        if (bTAdapter.isDiscovering()) {
            bTAdapter.cancelDiscovery();
        }
        getBoundedDevices();
        bTAdapter.startDiscovery();

    }

    /**
     * 获取已经配对过的设备
     */
    private void getBoundedDevices() {
        //获取已经配对过的设备
        final Set<BluetoothDevice> pairedDevices = bTAdapter.getBondedDevices();
        //将其
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                bluetoothAdapter.add(device);

            }
        }


    }

    private void openBlueTooth() {
        if (bTAdapter == null) {
            asyncShowToast("当前设备不支持蓝牙功能");
        }
        if (!bTAdapter.isEnabled()) {
            bTAdapter.enable();
        }
        //开启被其他蓝牙设备发现的功能
        if (bTAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            //设置为一直开启
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
            startActivity(intent);
        }
    }

    private class ConnectThread extends Thread {
        private BluetoothSocket socket;
        private boolean activeConnect;
        InputStream inputStream;
        OutputStream outputStream;

        public ConnectThread(BluetoothSocket socket, boolean activeConnect) {
            this.socket = socket;
            this.activeConnect = activeConnect;
        }

        @Override
        public void run() {
            super.run();
            //如果是自动连接 则调用连接方法
            try {
                if (activeConnect) {
                    socket.connect();
                }
                textState.post(new Runnable() {
                    @Override
                    public void run() {
                        textState.setText("链接成功");
                    }
                });
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytes;
                while (true) {
                    //读取数据
                    bytes = inputStream.read(buffer);
                    if (bytes > 0) {
                        final byte[] data = new byte[bytes];
                        System.arraycopy(buffer, 0, data, 0, bytes);
                        tvBlueTextMsgtextMsg.post(new Runnable() {
                            @Override
                            public void run() {
                                tvBlueTextMsgtextMsg.setText("获取到数据：" + new String(data));
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                textState.post(new Runnable() {
                    @Override
                    public void run() {
                        textState.setText("链接失败");
                    }
                });
            }

        }
    }
}
