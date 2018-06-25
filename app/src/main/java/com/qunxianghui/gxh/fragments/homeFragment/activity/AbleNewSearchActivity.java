package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.czy.letterindex.IndexControl;
import com.czy.letterindex.LetterIndexView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MyExpandableAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.DataCityInfo;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbleNewSearchActivity extends BaseActivity implements AbsListView.OnScrollListener, View.OnClickListener, AMapLocationListener {

    @BindView(R.id.simple_expandable_listview)
    ExpandableListView simpleExpandableListview;
    @BindView(R.id.liv_letters)
    LetterIndexView livLetters;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_homeactivity_curr_location)
    TextView tvHomeactivityCurrLocation;
    @BindView(R.id.tv_homeactivity_setcurr_location)
    TextView tvHomeactivitySetcurrLocation;
    @BindView(R.id.iv_blelocation_back)
    ImageView ivBlelocationBack;
    @BindView(R.id.tv_bletop_location)
    TextView tvBletopLocation;
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsearch_able;
    }

    @Override
    protected void initViews() {
        setSystemBarTransparent();

        simpleExpandableListview.setGroupIndicator(null);

        RequestAbleLocation();

    }

    private void RequestAbleLocation() {
        //定位
        mlocationClient = new AMapLocationClient(mContext);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
//                //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

    @Override
    protected void initDatas() {

        OkGo.<String>post(Constant.CITY_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body().toString();
                Log.i(TAG, "--->" + json);

                DataCityInfo bean = GsonUtil.parseJsonWithGson(json, DataCityInfo.class);
                if (bean.getCode() == 0) {
                    setItems(bean);
                    DataCityInfo.DataBean dataBean = bean.getData();

                    Map<String, Integer> letterMap = new HashMap<>();
                    int index = 0;
                    letterMap.put("A", index);//0-2
                    index = index + dataBean.getA().size();
                    letterMap.put("B", index);//2-2
                    index = index + dataBean.getB().size();
                    letterMap.put("C", index);//4-2
                    index = index + dataBean.getC().size();
                    letterMap.put("D", index);
                    index = index + dataBean.getD().size();
                    letterMap.put("E", index);
                    index = index + dataBean.getE().size();
                    letterMap.put("F", index);
                    index = index + dataBean.getF().size();
                    letterMap.put("G", index);
                    index = index + dataBean.getG().size();
                    letterMap.put("H", index);
                    index = index + dataBean.getH().size();
                    letterMap.put("J", index);
                    index = index + dataBean.getJ().size();
                    letterMap.put("K", index);
                    index = index + dataBean.getK().size();
                    letterMap.put("L", index);
                    index = index + dataBean.getL().size();
                    letterMap.put("M", index);
                    index = index + dataBean.getM().size();
                    letterMap.put("N", index);
                    index = index + dataBean.getN().size();
                    letterMap.put("P", index);
                    index = index + dataBean.getP().size();
                    letterMap.put("Q", index);
                    index = index + dataBean.getQ().size();
                    letterMap.put("R", index);
                    index = index + dataBean.getR().size();
                    letterMap.put("S", index);
                    index = index + dataBean.getS().size();
                    letterMap.put("T", index);
                    index = index + dataBean.getT().size();
                    letterMap.put("W", index);
                    index = index + dataBean.getF().size();
                    letterMap.put("X", index);
                    index = index + dataBean.getX().size();
                    letterMap.put("Y", index);
                    index = index + dataBean.getY().size();
                    letterMap.put("Z", index);

                    new IndexControl(simpleExpandableListview, livLetters, tvHint, letterMap);
                }
            }
        });
    }

    private void setItems(DataCityInfo bean) {
        MyExpandableAdapter adapter = new MyExpandableAdapter(bean);
        simpleExpandableListview.setAdapter(adapter);

    }


    @Override
    protected void initListeners() {
        tvHomeactivitySetcurrLocation.setOnClickListener(this);
        ivBlelocationBack.setOnClickListener(this);

    }


    /**
     * 设置沉浸式状态栏
     */
    private void setSystemBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 LOLLIPOP解决方案
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4 KITKAT解决方案
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }


    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvHomeactivityCurrLocation.setText(aMapLocation.getDistrict());
                        tvBletopLocation.setText("当前位置"+ aMapLocation.getDistrict());
                    }
                });
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "locations Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
        mlocationClient.stopLocation();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_blelocation_back:
                finish();
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
