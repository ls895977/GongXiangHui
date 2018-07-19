package com.qunxianghui.gxh.fragments.locationFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.NewsFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.ChannelGetallBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.fragments.homeFragment.activity.AbleNewSearchActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ChannelActivity;
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
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {


    @BindView(R.id.loaction_top_nav)
    RelativeLayout loactionTopNav;
    @BindView(R.id.mRadioGroup_content)
    LinearLayout mRadioGroupContent;
    @BindView(R.id.mColumnHorizontalScrollView)
    ColumnHorizontalScrollView mColumnHorizontalScrollView;
    @BindView(R.id.shade_left)
    ImageView shadeLeft;

    @BindView(R.id.rl_column)
    RelativeLayout rlColumn;
    @BindView(R.id.button_more_columns)
    ImageView buttonMoreColumns;
    @BindView(R.id.ll_more_columns)
    LinearLayout llMoreColumns;
    @BindView(R.id.local_view_pager)
    ViewPager LocalViewPager;
    @BindView(R.id.location_line_layout)
    LinearLayout locationLineLayout;
    @BindView(R.id.loactionn_fragment_relative_layout)
    LinearLayout loactionnFragmentRelativeLayout;
    Unbinder unbinder;
    @BindView(R.id.tv_localcircle_location)
    TextView tvLocalcircleLocation;
    @BindView(R.id.shade_right)
    ImageView shadeRight;
    Unbinder unbinder1;
    private int mScreenWidth;
    private int mItemWidth;//Item宽度：每个标题的宽度
    // tab集合：HorizontalScrollView的数据源
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private int columnSelectIndex = 0; // 当前选中的栏目索引
    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;

    @Override
    public int getLayoutId() {
        mScreenWidth = Utils.getWindowsWidth(mActivity);
        // 一个Item宽度为屏幕的1/7
        mItemWidth = mScreenWidth / 7;
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    @Override
    public void initDatas() {
        //频道列表（用户订阅的频道）
        OkGo.<String>post(Constant.CHANNEL_GETLIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                if (HttpStatusUtil.getStatus(json)) {
                    setChannelData(json);
                    setChangelView();
                } else {
                    ToastUtils.showShortToast(getContext(), HttpStatusUtil.getStatusMsg(json));
                }
            }
        });

        // + 号监听
        buttonMoreColumns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logger.d("onClick-->:" + userChannelList.toString());
                Intent intent_channel = new Intent(mActivity.getApplicationContext(), ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ChannelActivity.USER_CHANNEL, (userChannelList));
                intent_channel.putExtras(bundle);
                startActivityForResult(intent_channel, CHANNELREQUEST);
            }
        });
    }

    private void setChangelView() {
        initTabColumn();
        initFragment();
    }

    private void initFragment() {
        fragments.clear();//清空
        int count = userChannelList.size();
        LocationDetailFragment newFragment;
        Bundle bundle;
        for (int i = 0; i < count; i++) {
            newFragment = new LocationDetailFragment();
            bundle = new Bundle();
            bundle.putInt("channel_id", userChannelList.get(i).getId());
            newFragment.setArguments(bundle);
            fragments.add(newFragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragments);
        LocalViewPager.setAdapter(mAdapetr);
        LocalViewPager.setOffscreenPageLimit(fragments.size());
        LocalViewPager.addOnPageChangeListener(pageListener);
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
            LocalViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroupContent.getChildCount(); i++) {
            View checkView = mRadioGroupContent.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j < mRadioGroupContent.getChildCount(); j++) {
            View checkView = mRadioGroupContent.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    private void initTabColumn() {
        mRadioGroupContent.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(mActivity, mScreenWidth, mRadioGroupContent, shadeLeft, shadeRight, llMoreColumns, rlColumn);
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
                    for (int i = 0; i < mRadioGroupContent.getChildCount(); i++) {
                        View localView = mRadioGroupContent.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            LocalViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(mActivity.getApplicationContext(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroupContent.addView(columnTextView, i, params);
        }
    }

    private void setChannelData(String body) {
        ChannelGetallBean bean = GsonUtil.parseJsonWithGson(body, ChannelGetallBean.class);
        if (null != bean) {
            List<ChannelGetallBean.DataBean> datas = bean.getData();
            for (int i = 0; i < datas.size(); i++) {
                ChannelGetallBean.DataBean dataBean = datas.get(i);
                ChannelItem item = new ChannelItem(dataBean.getChannel_id(), dataBean.getChannel_name(), i, 1);
                userChannelList.add(item);
            }
        }
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    protected void initListeners() {
        tvLocalcircleLocation.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_localcircle_location:
                intent = new Intent(mActivity, AbleNewSearchActivity.class);
                startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    userChannelList = DragAdapter.channelList;
                    setChangelView();
                }
                break;
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                    tvLocalcircleLocation.setText(city);
                }
                break;
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

