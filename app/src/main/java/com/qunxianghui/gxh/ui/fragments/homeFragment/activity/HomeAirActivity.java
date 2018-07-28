package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeAirListAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeAirBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;

import java.util.List;

import butterknife.BindView;

public class HomeAirActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xrecycler)
    XRecyclerView xrecycler;
    @BindView(R.id.tv_homeair_top_big_degree)
    TextView tvHomeairTopBigDegree;
    @BindView(R.id.tv_homeair_middle_airdetail)
    TextView tvHomeairMiddleAirdetail;
    @BindView(R.id.tv_homeair_bottom_day_detail)
    TextView tvHomeairBottomDayDetail;
    @BindView(R.id.iv_home_air_back)
    ImageView ivHomeAirBack;
    @BindView(R.id.ll_homeair_location)
    LinearLayout llHomeairLocation;
    @BindView(R.id.home_air_location)
    TextView homeAirLocation;
    private String cityId;
    private String areaId;
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_airs;
    }

    @Override
    protected void initViews() {
        xrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

//        SharedPreferences spLocation = getSharedPreferences("location", MODE_PRIVATE);
//        cityId = spLocation.getString("X-cityId", "");
//        areaId = spLocation.getString("X-areaId", "");
//        Log.e("cityID",cityId);
//        Log.e("areaId",areaId);
    }

    private void RequestAirList() {
        OkGo.<String>post(Constant.HOME_AIRLIST_URL)
                .headers("X-cityId", cityId)
                .headers("X-areaId", areaId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final HomeAirBean homeAirBean = GsonUtil.parseJsonWithGson(response.body(), HomeAirBean.class);
                        final HomeAirBean.DataBean data = homeAirBean.getData();
                        SetTopAirDetail(data);
                        final List<HomeAirBean.DataBean.ForecastBean> forecast = data.getForecast();
                        xrecycler.setAdapter(new HomeAirListAdapter(mContext, forecast));
                    }
                });

    }

    private void SetTopAirDetail(HomeAirBean.DataBean data) {
        final String wendu = data.getWendu();
        final String weather = data.getForecast().get(0).getDay().getWeather();
        final String windPower = data.getForecast().get(0).getDay().getWindPower();
        final String windDirection = data.getForecast().get(0).getDay().getWindDirection();
        final String dateTime = data.getForecast().get(0).getDate();
        tvHomeairTopBigDegree.setText(wendu + "°C");
        tvHomeairMiddleAirdetail.setText(weather + "|" + windDirection + windPower);
        tvHomeairBottomDayDetail.setText(dateTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        homeAirLocation.setText(getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
        SharedPreferences spLocation = getSharedPreferences("location", MODE_PRIVATE);
        cityId = spLocation.getString("X-cityId", "");
        areaId = spLocation.getString("X-areaId", "");
        RequestAirList();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecycler.refreshComplete();
            }
        });

        ivHomeAirBack.setOnClickListener(this);
        llHomeairLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_air_back:
                finish();
                break;
            case R.id.ll_homeair_location:
                Intent intent = new Intent(mContext, AbleNewSearchActivity.class);
                startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    homeAirLocation.setText(getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
                    break;

                }
        }
    }
}
