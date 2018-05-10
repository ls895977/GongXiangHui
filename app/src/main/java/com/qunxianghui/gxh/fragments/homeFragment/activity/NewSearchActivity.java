package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.homeFragment.search.model.CityEntity;
import com.qunxianghui.gxh.fragments.homeFragment.search.utils.JsonReadUtil;
import com.qunxianghui.gxh.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.utils.ScreenUtils;
import com.qunxianghui.gxh.widget.LetterListView;
import com.qunxianghui.gxh.widget.ViewBinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSearchActivity extends BaseActivity implements AbsListView.OnScrollListener, AMapLocationListener {

    //文件名称
    private final static String CityFileName = "allcity.json";

    @BindView(R.id.search_locate_content_et)
    EditText searchLocateContentEt;
    @BindView(R.id.tool_bar_fl)
    FrameLayout mToolbar;
    @BindView(R.id.total_city_lv)
    ListView totalCityLv;
    @BindView(R.id.total_city_letters_lv)
    LetterListView totalCityLettersLv;
    @BindView(R.id.search_city_lv)
    ListView searchCityLv;
    @BindView(R.id.no_search_result_tv)
    TextView noSearchResultTv;
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
    private CityListAdapter cityListAdapter;
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private TextView curCityNameTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsearch;
    }

    @Override
    protected void initViews() {

        //默认软键盘不弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setSystemBarTransparent();

        ViewBinder.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int top = ScreenUtils.getSystemBarHeight();
            mToolbar.setPadding(0, top, 0, 0);
        }
        handler = new Handler();
        overlayThread = new OverlayThread();
        searchCityListAdapter = new SearchCityListAdapter(mContext, searchCityList);
        searchCityLv.setAdapter(searchCityListAdapter);
        locationCity = "杭州";
        curSelCity = locationCity;

        /**
         * 动态手机权限
         */
//        if (PermissionsUtil.hasPermission(mContext, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//
//        } else {
            PermissionsUtil.requestPermission(NewSearchActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    //定位
                    mlocationClient = new AMapLocationClient(NewSearchActivity.this);
                    //初始化定位参数
                    mLocationOption = new AMapLocationClientOption();
                    //设置返回地址信息，默认为true
                    mLocationOption.setNeedAddress(true);
                    //设置定位监听
                    mlocationClient.setLocationListener(NewSearchActivity.this);
                    //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
                    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    //设置定位间隔,单位毫秒,默认为2000ms
                    mLocationOption.setInterval(2000);
                    //设置定位参数
                    mlocationClient.setLocationOption(mLocationOption);
                    mlocationClient.startLocation();
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    Toast.makeText(mContext, "你拒绝了定位权限，感觉去设置中心去设置吧", Toast.LENGTH_SHORT).show();

                }
            }, new String[]{Manifest.permission.ACCESS_FINE_LOCATION});

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
    protected void initDatas() {
        initTotalCityList();
        cityListAdapter = new CityListAdapter(this, totalCityList, hotCityList);
        totalCityLv.setAdapter(cityListAdapter);
        totalCityLv.setOnScrollListener(this);
        totalCityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 1) {
                    CityEntity cityEntity = totalCityList.get(position);
                    showSetCityDialog(cityEntity.getName(), cityEntity.getCityCode());
                }
            }
        });
        totalCityLettersLv.setOnTouchingLetterChangedListener(new LetterListViewListener());
//        initOverlay();
    }


    @Override
    protected void initListeners() {
        //对搜索的数据设置点击事件

        searchCityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityEntity cityEntity = searchCityList.get(position);
                showSetCityDialog(cityEntity.getName(), cityEntity.getCityCode());
            }
        });

        //监听搜索字体变化
        searchLocateContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = searchLocateContentEt.getText().toString().trim();
                setSearchCityList(content);
            }
        });

        searchLocateContentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftInput(searchLocateContentEt.getWindowToken());
                    String content = searchLocateContentEt.getText().toString().trim().toLowerCase();
                    setSearchCityList(content);
                    return true;
                }
                return false;
            }
        });
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
     * 设置搜索数据展示
     *
     * @param content
     */
    private void setSearchCityList(String content) {
        searchCityList.clear();
        if (TextUtils.isEmpty(content)) {
            totalCityLv.setVisibility(View.VISIBLE);
            totalCityLettersLv.setVisibility(View.VISIBLE);
            searchCityLv.setVisibility(View.GONE);
            noSearchResultTv.setVisibility(View.GONE);
        } else {
            totalCityLv.setVisibility(View.GONE);
            totalCityLettersLv.setVisibility(View.GONE);
            for (int i = 0; i < curCityList.size(); i++) {
                CityEntity cityEntity = curCityList.get(i);
                if (cityEntity.getName().contains(content) || cityEntity.getPinyin().contains(content)
                        || cityEntity.getFirst().contains(content)) {
                    searchCityList.add(cityEntity);
                }
            }
            if (searchCityList.size() != 0) {
                noSearchResultTv.setVisibility(View.GONE);
                searchCityLv.setVisibility(View.VISIBLE);
            } else {
                noSearchResultTv.setVisibility(View.VISIBLE);
                searchCityLv.setVisibility(View.GONE);
            }
            searchCityListAdapter.notifyDataSetChanged();
        }
    }

//    /**
//     * 初始化汉语拼音首字母弹出提示框
//     */
//    private void initOverlay() {
//        mReady = true;
//        LayoutInflater inflater = LayoutInflater.from(this);
//        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
//        overlay.setVisibility(View.INVISIBLE);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_APPLICATION,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                PixelFormat.TRANSLUCENT);
//        WindowManager windowManager = (WindowManager) this
//                .getSystemService(Context.WINDOW_SERVICE);
//        windowManager.addView(overlay, lp);
//
//
//
//    }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

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
                Log.e("AmapError", "location Error, ErrCode:"
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
                totalCityLv.setSelection(position);
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
                intent.putExtra("cityinfo",curCity);
                setResult(RESULT_OK,intent);



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
