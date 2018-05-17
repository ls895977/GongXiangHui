package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.AdapterVideoList;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.VideoConstant;
import com.qunxianghui.gxh.utils.GsonUtil;

import cn.jzvd.JZVideoPlayer;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity {
    ListView listView;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener sensorEventListener;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initViews() {

        listView = findViewById(R.id.listview);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JZVideoPlayer.JZAutoFullscreenListener();
    }

    @Override
    protected void initDatas() {
        //首页视频数据
        OkGo.<String>get(Constant.HOME_VIDEO_LIST_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });

    }

    private void parseData(String body) {
        final HomeVideoListBean homeVideoListBean = GsonUtil.parseJsonWithGson(body, HomeVideoListBean.class);
        final HomeVideoListBean.DataBean videoData = homeVideoListBean.getData();

        listView.setAdapter(new AdapterVideoList(mContext,videoData.getList()));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                JZVideoPlayer.onScrollReleaseAllVideos(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
