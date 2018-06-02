package com.qunxianghui.gxh.fragments.homeFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.ScanActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.db.ChannelManage;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ChannelActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.NewSearchActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.SearchActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.ColumnHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    @BindView(R.id.ib_home_camera)
    TextView ibHomeCamera;
    @BindView(R.id.ib_home_search)
    ImageButton ibHomeSearch;
    @BindView(R.id.ib_home_scan)
    ImageButton ibHomeScan;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    //TabLayout标签
    private ColumnHorizontalScrollView mColumnHorizontalScrollView; // 自定义HorizontalScrollView
    private LinearLayout mRadioGroup_content; // 每个标题

    private LinearLayout ll_more_columns; // 右边+号的父布局
    private ImageView button_more_columns; // 标题右边的+号

    private RelativeLayout rl_column; // +号左边的布局：包括HorizontalScrollView和左右阴影部分
    public ImageView shade_left; // 左阴影部分
    public ImageView shade_right; // 右阴影部分
    private int columnSelectIndex = 0; // 当前选中的栏目索引
    private int mItemWidth = 0; // Item宽度：每个标题的宽度
    private int mScreenWidth = 0; // 屏幕宽度
    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    private int REQUEST_CODE_SCAN = 111;
    // tab集合：HorizontalScrollView的数据源
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();

    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        mScreenWidth = Utils.getWindowsWidth(mActivity);
        mItemWidth = mScreenWidth / 7; // 一个Item宽度为屏幕的1/7
        return R.layout.home_layout;
    }

    @Override
    public void initDatas() {

        //频道列表（用户订阅的频道）
        OkGo.<String>post(Constant.CHANNEL_GETLIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body().toString();
                if (HttpStatusUtil.getStatus(json)) {
                    ChannelGetallBean listData = getListData(json);
                } else {
                    ToastUtils.showShortToast(getContext(), HttpStatusUtil.getStatusMsg(json));
                }
            }
        });



        // + 号监听
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Logger.d("onClick-->:" + userChannelList.toString());
                Intent intent_channel = new Intent(mActivity.getApplicationContext(), ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ChannelActivity.USER_CHANNEL,(userChannelList));
                intent_channel.putExtras(bundle);
                startActivityForResult(intent_channel, CHANNELREQUEST);
            }
        });
        setChangelView();

    }

    /** ==================频道列表（用户订阅的频道）===================== */
    private ChannelGetallBean getListData(String body) {
//        Logger.d("getAllData-->: " + body);
        final ChannelGetallBean bean = GsonUtil.parseJsonWithGson(body, ChannelGetallBean.class);
        if (null != bean) {
            List<ChannelGetallBean.DataBean> datas = bean.getData();

            for (int i = 0; i < datas.size(); i++) {
                ChannelGetallBean.DataBean dataBean = datas.get(i);
                ChannelItem item = new ChannelItem(dataBean.getChannel_id(), dataBean.getChannel_name(),i , 1);
                userChannelList.add(item);
            }
        }
        return bean;
    }


    @Override
    public void initViews(View view) {

        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) mActivity.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) mActivity.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) mActivity.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) mActivity.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) mActivity.findViewById(R.id.button_more_columns);
        shade_left = (ImageView) mActivity.findViewById(R.id.shade_left);
        shade_right = (ImageView) mActivity.findViewById(R.id.shade_right);
        mViewPager = (ViewPager) mActivity.findViewById(R.id.home_view_pager);

    }

    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {

        fragments.clear();//清空
        int count = userChannelList.size();

        for (int i = 0; i < count; i++) {
            HotPointFragment newfragment = new HotPointFragment();
            fragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }

    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(mActivity, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(mActivity);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }

            // 单击监听
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(mActivity.getApplicationContext(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(MyApplication.getApp().getSQLHelper()).getUserChannel());

    }

    @Override
    protected void initListeners() {
        ibHomeCamera.setOnClickListener(this);
        ibHomeSearch.setOnClickListener(this);
        ibHomeScan.setOnClickListener(this);
        tvHomeLocation.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_home_camera:            //爆料
                toActivity(BaoLiaoActivity.class);
                break;
            case R.id.ib_home_scan:            //扫描二维码

                toActivity(ScanActivity.class);
                break;
            case R.id.ib_home_search:          //搜索
                toActivity(SearchActivity.class);
                break;
            case R.id.tv_home_location:

                toActivity(LocationActivity.class);

//                Intent intent = new Intent(mActivity, NewSearchActivity.class);
//                startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    setChangelView();
                }
                break;

            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                    }
                    final String cityinfo = data.getStringExtra("cityinfo");
                    if (cityinfo == null) {
                        return;
                    }
                    tvHomeLocation.setText(cityinfo);
                    break;

                }
                super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
