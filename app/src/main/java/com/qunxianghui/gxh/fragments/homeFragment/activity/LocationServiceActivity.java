package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.CarBrandAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.CarListAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.CarQueryAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.CarSeriesItemAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.CarBean;
import com.qunxianghui.gxh.bean.home.CarSeriesBean;
import com.qunxianghui.gxh.bean.home.MainPageBean;
import com.qunxianghui.gxh.bean.home.QueryBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.OkHttpUtil;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.widget.TitleBuilder;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class LocationServiceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_location_service_all)
    LinearLayout llLocationServiceAll;
    @BindView(R.id.ll_location_service_locition)
    LinearLayout llLocationServiceLocition;
    @BindView(R.id.ll_location_service_industry)
    LinearLayout llLocationServiceIndustry;
    @BindView(R.id.xrecycler_location_service)
    XRecyclerView xrecyclerLocationService;
    @BindView(R.id.lv_rentcar_brand)
    ListView lvRentcarBrand;
    @BindView(R.id.lv_carrent_series)
    ListView lvCarrentSeries;
    @BindView(R.id.ll_whole_brand)
    LinearLayout llWholeBrand;
    private List<MainPageBean.DataBean.CarListBean> carListDatas;
    private CarListAdapter carListAdapter;
    private boolean IsBrandShow = true;
    private List<CarBean.DataBean> brandData;
    private CarBrandAdapter carBrandAdapter;
    private CarSeriesItemAdapter carSeriesItemAdapter;
    /*参数构建*/
    private ParamsBuilder params = new ParamsBuilder();
    private CarQueryAdapter carQueryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_service;
    }

    @Override
    protected void initViews() {
        xrecyclerLocationService.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        xrecyclerLocationService.setLoadingMoreEnabled(false);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("本地服务");

        OkGo.<String>get(Constant.API_MAIN_PAGE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(String body) {
        MainPageBean mainPageBean = GsonUtil.parseJsonWithGson(body, MainPageBean.class);
        MainPageBean.DataBean dataList = mainPageBean.getData();
        final List<MainPageBean.DataBean.CarListBean> carList = dataList.getCarList();

        final CarListAdapter carListAdapter = new CarListAdapter(mContext, carList);
        carListAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //跳转消息详情页面
                final Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("carid", carList.get(position - 1).getId());
                startActivity(intent);

            }
        });
        xrecyclerLocationService.setAdapter(carListAdapter);
    }


    @Override
    protected void initListeners() {
        llLocationServiceAll.setOnClickListener(this);
        llLocationServiceLocition.setOnClickListener(this);
        llLocationServiceIndustry.setOnClickListener(this);
        xrecyclerLocationService.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerLocationService.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerLocationService.refreshComplete();
            }
        });
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
            case R.id.ll_location_service_all:
                asyncShowToast("点击了全部");

                break;
            case R.id.ll_location_service_locition:
                asyncShowToast("点击了地区");


                break;
            case R.id.ll_location_service_industry:

                if (llLocationServiceAll.getVisibility()==View.VISIBLE){
                    IsBrandShow=true;
                }
                if (IsBrandShow) {
                    IsBrandShow = !IsBrandShow;
                    llWholeBrand.setVisibility(View.VISIBLE);

                    /**
                     * 解析品牌
                     */
                    if (carBrandAdapter == null) {
                        OkGo.<String>get(Constant.API_CAR_BRAND).execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                resolve(response.body());
                            }
                        });
                    }

                } else {
                    llWholeBrand.setVisibility(View.INVISIBLE);
                    IsBrandShow = !IsBrandShow;
                }
                break;
        }

    }

    private void resolve(String body) {
        final CarBean carBean = GsonUtil.parseJsonWithGson(body, CarBean.class);
        brandData = carBean.getData();
        //根据自己的需要是否删除第一个
        brandData.remove(0);
        carBrandAdapter = new CarBrandAdapter(brandData, this);
        lvRentcarBrand.setAdapter(carBrandAdapter);

        lvRentcarBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              params.setBrand(String.valueOf(brandData.get(position).getId()));
                OkGo.<String>get(Constant.API_CAR_SERIES).
                        params("brandId",String.valueOf(brandData.get(position).getId())).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseSeriesData(response.body());
                    }
                });

            }
        });
    }

    private void parseSeriesData(String body) {
        final CarSeriesBean carSeriesBean = GsonUtil.parseJsonWithGson(body, CarSeriesBean.class);
        final List<CarSeriesBean.DataBean> data = carSeriesBean.getData();
        if (carSeriesItemAdapter == null) {
            carSeriesItemAdapter = new CarSeriesItemAdapter(this, data);
            lvCarrentSeries.setAdapter(carSeriesItemAdapter);
            lvCarrentSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //請求数据 舒心车辆信息 每次点击都刷新
                    params.setPageSize(String.valueOf(data.get(position).getId()));
                    doCarQuery(params.buildUrl());


                }
            });
        }else {
            carSeriesItemAdapter.reset(data);
        }


    }

    private void doCarQuery(String url) {

        OkGo.<String> get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final QueryBean queryBean = GsonUtil.parseJsonWithGson(response.body(), QueryBean.class);
                if (queryBean.getErrno()==0){
                    final List<QueryBean.DataBean> data = queryBean.getData();
                    refreshRecycleView(data);

                }else {
                    Log.e(TAG, queryBean.getMessage());
                    asyncShowToast(queryBean.getMessage());
                }
            }
        });

    }

    /**
     * 刷新数据
     * @param data
     */
    private void refreshRecycleView(List<QueryBean.DataBean> data) {
        if(data==null||data.size()==0){
            asyncShowToast("暂无数据");
            return;
        }else {
            llWholeBrand.setVisibility(View.GONE);
        }
        if (carQueryAdapter==null) {
            carQueryAdapter = new CarQueryAdapter(mContext, data);
            carQueryAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                }
            });
        }else {
            carQueryAdapter.refresh(data);
        }
        xrecyclerLocationService.refreshComplete();


    }

    private class ParamsBuilder {
        /*品牌*/
        private String brand = "";
        /*车系*/
        private String series = "";
        /*车型*/
        private String model = "";
        /*用车城市ID*/
        private String city = "";
        /*品牌,车系,车型,车名模糊搜索*/
        private String text = "";
        /*价格 格式如: 200~500*/
        private String price = "";
        /*使用类型:个人,商务和婚庆 1,2,4,*/
        private String useType = "";
        /*使用时间.格式如: 2017-05-17 08:00~2017-05-17 15:00*/
        private String span = "";
        /*经纬度,格式:经度,纬度*/
        private String loc = "";
        /*座位数,取值2,3,4,5,6,7.见参数说明*/
        private String seatNum = "";
        /*	排序条件(非筛选)
        * 参数由排序字符连接而成,每两个字符为一组,组数根据需求指定
        * 第一字符(排序字段)取值为
        * 1=price     2=create_time(记录创建时间,即新车旧车)
        * 3=接单数量   4=年款
        * 5=距离(loc参数必填)
        * 第二字符,排序规则 a.升序(从小到大)  d.降序
        * 示例:1a2d表示返回记录按价格从低到高,创建时间从迟到早排列(2排序规则)
        * 4d1a3d表示按年款从新到旧,价格从低到高,接单数量从高到低的顺序排列(3组排序规则)
        * */
        private String orderBy = "";
        /*分页页码,必须参数*/
        private String pageNum = "0";
        /*分页条数,必须参数*/
        private String pageSize = "20";

        public ParamsBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public ParamsBuilder setSeries(String series) {
            this.series = series;
            return this;
        }

        public ParamsBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public ParamsBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public ParamsBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public ParamsBuilder setPrice(String price) {
            this.price = price;
            return this;
        }

        public ParamsBuilder setUseType(String useType) {
            this.useType = useType;
            return this;
        }

        public ParamsBuilder setSpan(String span) {
            this.span = span;
            return this;
        }

        public ParamsBuilder setLoc(String loc) {
            this.loc = loc;
            return this;
        }

        public ParamsBuilder setSeatNum(String seatNum) {
            this.seatNum = seatNum;
            return this;
        }

        public ParamsBuilder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public ParamsBuilder setPageNum(String pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public ParamsBuilder setPageSize(String pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public void reset() {
            brand = "";
            series = "";
            model = "";
            city = "";
            text = "";
            price = "";
            useType = "";
            span = "";
            loc = "";
            seatNum = "";
            orderBy = "";
            pageNum = "0";
            pageSize = "20";
        }

        public String buildUrl() {
            return OkHttpUtil.obtainGetUrl(Constant.API_CAR_QUERY,
                    "brand", brand,
                    "series", series,
                    "model", model,
                    "city", city,
                    "text", text,
                    "price", price,
                    "useType", useType,
                    "span", span,
                    "loc", loc,
                    "seatNum", seatNum,
                    "orderBy", orderBy,
                    "pageNum", pageNum,
                    "pageSize", pageSize);
        }
    }

}
