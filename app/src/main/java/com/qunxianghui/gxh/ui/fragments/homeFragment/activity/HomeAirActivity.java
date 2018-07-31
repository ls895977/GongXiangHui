package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
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
import butterknife.OnClick;

public class HomeAirActivity extends BaseActivity {

    @BindView(R.id.tv_homeair_top_big_degree)
    TextView mTvHomeairTopBigDegree;
    @BindView(R.id.tv_homeair_middle_airdetail)
    TextView mTvHomeairMiddleAirdetail;
    @BindView(R.id.tv_homeair_bottom_day_detail)
    TextView mTvHomeairBottomDayDetail;
    @BindView(R.id.home_air_location)
    TextView mHomeAirLocation;
    @BindView(R.id.xrecycler)
    XRecyclerView mXrecycler;

    private String cityId;
    private String areaId;
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_airs;
    }

    @Override
    protected void initViews() {
        mXrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mHomeAirLocation.setText(getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
    }

    @Override
    protected void initData() {
        SharedPreferences spLocation = getSharedPreferences("location", MODE_PRIVATE);
        cityId = spLocation.getString("X-cityId", "");
        areaId = spLocation.getString("X-areaId", "");
        requestAirList();
    }

    @Override
    protected void initListeners() {
        mXrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXrecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXrecycler.refreshComplete();
            }
        });
    }

    private void requestAirList() {
        OkGo.<String>post(Constant.HOME_AIRLIST_URL)
                .headers("X-cityId", cityId)
                .headers("X-areaId", areaId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        HomeAirBean homeAirBean = GsonUtil.parseJsonWithGson(response.body(), HomeAirBean.class);
                        HomeAirBean.DataBean data = homeAirBean.getData();
                        setTopAirDetail(data);
                        final List<HomeAirBean.DataBean.ForecastBean> forecast = data.getForecast();
                        mXrecycler.setAdapter(new HomeAirListAdapter(mContext, forecast));
                    }
                });
    }

    private void setTopAirDetail(HomeAirBean.DataBean data) {
        String weather = data.getForecast().get(0).getDay().getWeather();
        String windPower = data.getForecast().get(0).getDay().getWindPower();
        String windDirection = data.getForecast().get(0).getDay().getWindDirection();
        String dateTime = data.getForecast().get(0).getDate();
        mTvHomeairTopBigDegree.setText(String.format("%s°C", data.getWendu()));
        mTvHomeairMiddleAirdetail.setText(String.format("%s|%s%s", weather, windDirection, windPower));
        mTvHomeairBottomDayDetail.setText(dateTime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    mHomeAirLocation.setText(getSharedPreferences("location", MODE_PRIVATE).getString("currcity", ""));
                }
                break;
        }
    }

    @OnClick({R.id.iv_home_air_back, R.id.ll_homeair_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_air_back:
                finish();
                break;
            case R.id.ll_homeair_location:
                startActivityForResult(new Intent(mContext, AbleNewSearchActivity.class), CITY_SELECT_RESULT_FRAG);
                break;
        }
    }
}
