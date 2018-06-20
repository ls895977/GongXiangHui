package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.LinearLayout;
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
import com.qunxianghui.gxh.fragments.homeFragment.search.model.CityEntity;
import com.qunxianghui.gxh.fragments.homeFragment.search.utils.JsonReadUtil;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.widget.LetterListView;
import com.qunxianghui.gxh.widget.ViewBinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbleNewSearchActivity extends BaseActivity implements AbsListView.OnScrollListener, AMapLocationListener {

    @BindView(R.id.simple_expandable_listview)
    ExpandableListView simpleExpandableListview;
    @BindView(R.id.liv_letters)
    LetterIndexView livLetters;
    @BindView(R.id.tv_hint)
    TextView tvHint;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsearch_able;
    }

    @Override
    protected void initViews() {
        setSystemBarTransparent();
        handler = new Handler();
        simpleExpandableListview.setGroupIndicator(null);
    }

    @Override
    protected void initDatas() {
//        initTotalCityList();
        //totalCityLettersLv.setOnTouchingLetterChangedListener(new LetterListViewListener());
        OkGo.<String>post(Constant.CITY_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body().toString();
                Log.i(TAG, "--->" + json);
//                CityInfos bean = GsonUtil.parseJsonWithGson(json, CityInfos.class);
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
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header, null);
        simpleExpandableListview.addHeaderView(headerView);
    }


    @Override
    protected void initListeners() {
        //对搜索的数据设置点击事件
    }


    //文件名称
    private final static String CityFileName = "allcity.json";

//    @BindView(R.id.total_city_letters_lv)
//    LetterListView totalCityLettersLv;
//    @BindView(R.id.no_search_result_tv)
//    TextView noSearchResultTv;


    private Handler handler;
    private TextView overlay; // 对话框首字母TextView
    private OverlayThread overlayThread;
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    protected List<CityEntity> searchCityList = new ArrayList<>();
    protected List<CityEntity> hotCityList = new ArrayList<>();
    protected List<CityEntity> totalCityList = new ArrayList<>();
    protected List<CityEntity> curCityList = new ArrayList<>();
    private SearchCityListAdapter searchCityListAdapter;
    private String locationCity, curSelCity;
    private boolean isScroll = false;
    private boolean mReady = false;
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private TextView curCityNameTv;


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


    /**
     * 隐藏软键盘
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 初始化全部城市列表
     */
    private void initTotalCityList() {
        hotCityList.clear();
        totalCityList.clear();
        curCityList.clear();
        String cityListJson = JsonReadUtil.getJsonStr(this, CityFileName);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(cityListJson);
            JSONArray array = jsonObject.getJSONArray("City");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String key = object.getString("key");
                String pinyin = object.getString("full");
                String first = object.getString("first");
                String cityCode = object.getString("code");
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(name);
                cityEntity.setKey(key);
                cityEntity.setPinyin(pinyin);
                cityEntity.setFirst(first);
                cityEntity.setCityCode(cityCode);
                if (key.equals("热门")) {
                    hotCityList.add(cityEntity);
                } else {
                    if (!cityEntity.getKey().equals("0") && !cityEntity.getKey().equals("1")) {
                        curCityList.add(cityEntity);
                    }
                    totalCityList.add(cityEntity);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        } else {
            isScroll = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (!isScroll) {
            return;
        }
        if (mReady) {
            final String key = getAlpha(totalCityList.get(firstVisibleItem).getKey());
            overlay.setText("key");
            overlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟让overlay为不可见
            handler.postDelayed(overlayThread, 700);

        }
    }


    //高德定位的方法
    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {


        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        curCityNameTv.setText(aMapLocation.getDistrict());

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

    private class LetterListViewListener implements
            LetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            isScroll = false;
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
//                totalCityLv.setSelection(position);
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟让overlay为不可见
                handler.postDelayed(overlayThread, 700);
            }
        }
    }

    /**
     * 设置overlay不可见
     */
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }


    }


    ///////////////////////////各大Adapter集合////////////////////////////

    /**
     * 搜索城市列表适配器
     */

    private class SearchCityListAdapter extends BaseAdapter {
        private Context context;
        private List<CityEntity> cityEntities;
        private LayoutInflater inflater;

        public SearchCityListAdapter(Context context, List<CityEntity> cityEntities) {
            this.context = context;
            this.cityEntities = cityEntities;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return cityEntities == null ? 0 : cityEntities.size();
        }

        @Override
        public Object getItem(int position) {
            return cityEntities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SearchCityHolder searchCityHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.city_list_item_layout, null);
                searchCityHolder = new SearchCityHolder();
                searchCityHolder.cityKeyTv = convertView.findViewById(R.id.city_key_tv);
                searchCityHolder.cityNameTv = convertView.findViewById(R.id.city_name_tv);
                ViewBinder.bind(searchCityHolder, convertView);
                convertView.setTag(searchCityHolder);
            } else {
                searchCityHolder = (SearchCityHolder) convertView.getTag();

            }
            final CityEntity cityEntity = cityEntities.get(position);
            searchCityHolder.cityKeyTv.setVisibility(View.GONE);
            searchCityHolder.cityNameTv.setText(cityEntity.getName());


            return convertView;
        }


        private class SearchCityHolder {
            TextView cityKeyTv;
            TextView cityNameTv;


        }
    }

    /**
     * 总城市适配器
     */

    private class CityListAdapter extends BaseAdapter {
        private Context context;

        private List<CityEntity> totalCityList;
        private List<CityEntity> hotCityList;
        private LayoutInflater inflater;
        final int VIEW_TYPE = 3;
        private SearchCityHolder searchCityHolder;

        public CityListAdapter(Context context, List<CityEntity> totalCityList, List<CityEntity> hotCityList) {
            this.context = context;
            this.totalCityList = totalCityList;
            this.hotCityList = hotCityList;
            inflater = LayoutInflater.from(context);
            alphaIndexer = new HashMap<>();
            for (int i = 0; i < totalCityList.size(); i++) {
                // 当前汉语拼音首字母
                String currentStr = totalCityList.get(i).getKey();
                String previewStr = (i - 1) >= 0 ? totalCityList.get(i - 1).getKey() : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = getAlpha(currentStr);
                    alphaIndexer.put(name, i);
                }

            }
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE;
        }


        @Override
        public int getItemViewType(int position) {
            return position < 2 ? position : 2;
        }

        @Override
        public int getCount() {
            return totalCityList == null ? 0 : totalCityList.size();
        }

        @Override
        public Object getItem(int position) {
            return totalCityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int viewType = getItemViewType(position);
            if (viewType == 0) {
                //定位
                convertView = inflater.inflate(R.layout.select_city_location_item, null);
                LinearLayout noLocationLl = convertView.findViewById(R.id.cur_city_no_data_ll);
                TextView getLocationTv = convertView.findViewById(R.id.cur_city_re_get_location_tv);
                curCityNameTv = convertView.findViewById(R.id.cur_city_name_tv);

                if (TextUtils.isEmpty(locationCity)) {
                    noLocationLl.setVisibility(View.VISIBLE);
                    curCityNameTv.setVisibility(View.GONE);
                    getLocationTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //获取定位

                        }
                    });
                } else {
                    noLocationLl.setVisibility(View.GONE);
                    curCityNameTv.setVisibility(View.VISIBLE);

                    curCityNameTv.setText(locationCity);
                    curCityNameTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!locationCity.equals(curSelCity)) {
                                //设置城市代码
                                String cityCode = "";
                                for (CityEntity cityEntity : curCityList) {
                                    if (cityEntity.getName().equals(locationCity)) {
                                        cityCode = cityEntity.getCityCode();
                                        break;
                                    }
                                }
                                showSetCityDialog(locationCity, cityCode);
                            } else {
                                asyncShowToast("当前定位城市" + curCityNameTv.getText().toString());
                            }
                        }
                    });
                }

            } else if (viewType == 1) {
                //热门城市
                convertView = inflater.inflate(R.layout.recent_city_item, null);
                GridView hotCityGv = convertView.findViewById(R.id.recent_city_gv);
                hotCityGv.setAdapter(new HotCityListAdapter(context, this.hotCityList));
                hotCityGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        CityEntity cityEntity = hotCityList.get(position);
                        showSetCityDialog(cityEntity.getName(), cityEntity.getCityCode());
                    }
                });
            } else {
                if (convertView == null) {
                    searchCityHolder = new SearchCityHolder();
                    convertView = inflater.inflate(R.layout.city_list_item_layout, null);
                    searchCityHolder.cityKeyTv = convertView.findViewById(R.id.city_key_tv);
                    searchCityHolder.cityNameTv = convertView.findViewById(R.id.city_name_tv);
                    ViewBinder.bind(searchCityHolder, convertView);
                    convertView.setTag(searchCityHolder);
                } else {
                    searchCityHolder = (SearchCityHolder) convertView.getTag();
                }
                CityEntity cityEntity = totalCityList.get(position);
                searchCityHolder.cityKeyTv.setVisibility(View.VISIBLE);
                searchCityHolder.cityKeyTv.setText(getAlpha(cityEntity.getKey()));
                searchCityHolder.cityNameTv.setText(cityEntity.getName());

                if (position >= 1) {
                    CityEntity preCity = totalCityList.get(position - 1);
                    if (preCity.getKey().equals(cityEntity.getKey())) {
                        searchCityHolder.cityKeyTv.setVisibility(View.GONE);
                    } else {
                        searchCityHolder.cityKeyTv.setVisibility(View.VISIBLE);
                    }
                }

            }

            return convertView;
        }

        private class SearchCityHolder {
            TextView cityKeyTv;
            TextView cityNameTv;


        }

    }


    /**
     * 热门城市适配器
     */


    private class HotCityListAdapter extends BaseAdapter {
        private Context context;
        private List<CityEntity> cityEntities;
        private LayoutInflater inflater;

        public HotCityListAdapter(Context context, List<CityEntity> cityEntities) {
            this.context = context;
            this.cityEntities = cityEntities;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return cityEntities == null ? 0 : cityEntities.size();
        }

        @Override
        public Object getItem(int position) {
            return cityEntities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HotCityViewHolder holder;
            if (null == convertView) {
                holder = new HotCityViewHolder();
                convertView = inflater.inflate(R.layout.city_list_grid_item_layout, null);
                holder.city_list_grid_item_name_tv = convertView.findViewById(R.id.city_list_grid_item_name_tv);
                ViewBinder.bind(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (HotCityViewHolder) convertView.getTag();
            }
            CityEntity cityEntity = cityEntities.get(position);
            holder.city_list_grid_item_name_tv.setText(cityEntity.getName());

            return convertView;
        }

        private class HotCityViewHolder {

            TextView city_list_grid_item_name_tv;
        }
    }


    /**
     * 展示设置城市对话框
     */
    private void showSetCityDialog(final String curCity, String cityCode) {
        if (curCity.equals(curSelCity)) {
            asyncShowToast("当前定位城市" + curCity);
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否设置 " + curCity + " 为您的当前城市？"); //设置内容

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //选中之后做你的方法
                curCityNameTv.setText(curCity);
                final Intent intent = new Intent();
                intent.putExtra("cityinfo", curCity);
                setResult(RESULT_OK, intent);

                finish();

            }
        });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();

    }

    /**
     * 获得首字母
     */

    private String getAlpha(String key) {
        if (key.equals("0")) {
            return "定位";
        } else if (key.equals("1")) {
            return "热门";
        } else {
            return key;
        }
    }


}
