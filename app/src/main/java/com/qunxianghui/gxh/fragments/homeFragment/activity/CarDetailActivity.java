package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.CarDetailAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.CarDetailBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.OkHttpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class CarDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.vp_car_pic)
    ViewPager vpCarPic;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    private CarDetailBean.DataBean carDetailBean;
    private String[] mImageViews;
    private int preSelectedPoint;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    if (vpCarPic != null) {
                        int currentItem = vpCarPic.getCurrentItem();
                        vpCarPic.setCurrentItem(currentItem + 1);
                    }

                    handler.sendEmptyMessageDelayed(0, 3000);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_detail;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        final int carid = getIntent().getIntExtra("carid", 0);

        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CAR_DETAIL, "carIdList", carid + "");
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                /** 请求完优先判断*/
                CarDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), CarDetailBean.class);
                setViewInfo(detailBean);
            }
        });

    }

    private void setViewInfo(CarDetailBean detailBean) {
        carDetailBean = detailBean.getData().get(0);
        mImageViews = carDetailBean.getImgs().split(",");
        //设置adapter

        CarDetailAdapter carDetailAdapter = new CarDetailAdapter(mContext, mImageViews);
        vpCarPic.setAdapter(carDetailAdapter);
        if (vpCarPic != null) {
            preSelectedPoint = vpCarPic.getCurrentItem();
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(0, 3000);
        }


        vpCarPic.setOnPageChangeListener(this);
//设置ViwpPager的默认项  设置为长度的100倍  这样子开始就能往左滑
        vpCarPic.setCurrentItem((mImageViews.length) * 100);


        tvCarName.setText(carDetailBean.getCarName());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
