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

public class AbleNewSearchActivity extends BaseActivity implements AbsListView.OnScrollListener{

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

        simpleExpandableListview.setGroupIndicator(null);
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
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header, null);
        simpleExpandableListview.addHeaderView(headerView);
    }


    @Override
    protected void initListeners() {

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








}
