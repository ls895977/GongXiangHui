package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeAirListAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeAirBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.SPUtils;

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
    @BindView(R.id.tv_homeair_des)
    TextView mTvHomeairDes;
    @BindView(R.id.iv_homeair_airbg)
    ImageView ivHomeairAirbg;

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
        mHomeAirLocation.setText(SPUtils.getLocation("currcity"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currcity = SPUtils.getLocation("currcity");
        mHomeAirLocation.setText(TextUtils.isEmpty(currcity) ? SPUtils.getLocation("X-cityName") : currcity);
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
        OkGo.<HomeAirBean>post(Constant.HOME_AIRLIST_URL)
                .headers("X-cityId", cityId)
                .headers("X-areaId", areaId)
                .execute(new JsonCallback<HomeAirBean>() {
                    @Override
                    public void onSuccess(Response<HomeAirBean> response) {
                        HomeAirBean homeAirBean = response.body();
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
        String notice = data.getForecast().get(0).getNight().getNotice();
        String bgImage = data.getForecast().get(0).getNight().getBgImage();
        mTvHomeairTopBigDegree.setText(String.format(data.getWendu()));
        mTvHomeairMiddleAirdetail.setText(String.format("%s|%s%s", weather, windDirection, windPower));
        mTvHomeairBottomDayDetail.setText(dateTime);
        mTvHomeairDes.setText(notice);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.homeair_sun);
        options.error(R.mipmap.homeair_rain);
        Glide.with(mContext).load(bgImage).apply(options).into(ivHomeairAirbg);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    mHomeAirLocation.setText(SPUtils.getLocation("currcity"));
                }
                break;
        }
    }

    @OnClick({R.id.iv_home_air_back, R.id.home_air_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_air_back:
                finish();
                break;
            case R.id.home_air_location:
                startActivityForResult(new Intent(mContext, LocationActivity.class), CITY_SELECT_RESULT_FRAG);
                break;
        }
    }

}
