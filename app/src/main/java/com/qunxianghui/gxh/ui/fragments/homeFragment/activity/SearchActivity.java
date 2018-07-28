package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.PagerAdapter;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.FireSearchAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.SimpleTextAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.GuessBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.fragments.homeFragment.fragments.SearchFragment;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.JsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.widget.SpaceSize;
import com.qunxianghui.gxh.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.rv_search_result)
    RecyclerView rvSearchResult;
    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.rv_search_guess)
    RecyclerView rvSearchGuess;
    @BindView(R.id.iv_clear_history)
    ImageView ivClearHistory;
    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.iv_home_search_back)
    ImageView ivHomeSearchBack;


    private String searchText = "";
    private List<String> historyDatas = new ArrayList<>();
    private SimpleTextAdapter historyAdapter;
    private String trim;


    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        //解决嵌套滑动的问题
        rvSearchGuess.setNestedScrollingEnabled(false);
        rvSearchHistory.setNestedScrollingEnabled(false);
        rvSearchGuess.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvSearchGuess.addItemDecoration(new SpacesItemDecoration(new SpaceSize(20, 12, 20, 12)));
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSearchHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }


    @Override
    protected void initData() {
        //猜你想要的数据
        OkGo.<String>get(Constant.SEARCH_GUESS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final GuessBean guessBean = GsonUtil.parseJsonWithGson(response.body(), GuessBean.class);
                if (guessBean.getCode() == 0) {
                    final List<GuessBean.DataBean> data = guessBean.getData();
                    initFireRecycle(data);
                }
            }
        });
        initHistories();
    }
    /**
     * ==================猜你想要的数据=====================
     */
    private void initFireRecycle(final List<GuessBean.DataBean> guessBean) {
        final FireSearchAdapter adapter = new FireSearchAdapter(mContext, guessBean);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                searchText = guessBean.get(position).getTitle();
                refreshSearchText();
                tvCancel.performClick();
            }
        });
        rvSearchGuess.setAdapter(adapter);
    }
    /*加载历史记录*/
    private void initHistories() {
        String histories = SPUtils.getString(mContext, SpConstant.HISTORIES, "");
        List<String> datas = JsonUtil.fromJsonList(histories, String.class);
        if (datas != null) {
            historyDatas = datas;
        } else {
            ivClearHistory.setVisibility(View.GONE);
        }

        if (historyDatas != null) {
            historyAdapter = new SimpleTextAdapter(mContext, historyDatas);
            historyAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    searchText = historyDatas.get(position);
                    refreshSearchText();
                    tvCancel.performClick();
                }
            });
            rvSearchHistory.setAdapter(historyAdapter);
        }
    }

    /**
     * ==================点击猜你想要是的进行搜索=====================
     */
    private void refreshSearchText() {
        etSearch.setText(searchText);
        etSearch.setSelection(etSearch.getText().length());

//        OkGo.<String>post(Constant.SEARCH_AUTO_COMPLETE).
//                params("keywords",trim).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//
//                if (mViewpager != null) {
//                    setupViewPager(trim);
//                }
//            }
//        });
    }

    @Override
    protected void initListeners() {
        ivClearHistory.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        ivHomeSearchBack.setOnClickListener(this);
        etSearch.setOnEditorActionListener(this);
        etSearch.addTextChangedListener(getTextChanged);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空历史
            case R.id.iv_clear_history:
                updateHistories("", true);
                v.setVisibility(View.GONE);
                break;
            //搜索按钮
            case R.id.tv_cancel:
                trim = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtils.showShortToast(this, "搜索内容不能为空");
                } else {
                    if (mViewpager != null) {
                        setupViewPager(trim);
                    }
                }
                break;
            case R.id.iv_home_search_back:
                finish();
                break;
        }
    }

    /**
     * 刷新历史记录
     */
    private void updateHistories(String item, boolean clear) {
        if (clear) {
            //清空历史数据
            historyDatas.clear();
            SPUtils.removePreference(mContext, SpConstant.HISTORIES);

        } else {
            saveHistory(item);
        }
        historyAdapter.notifyDataSetChanged();
    }

    /**
     * ==================保存历史=====================
     */
    private void saveHistory(String item) {
        if (historyDatas.size() == 0) {
            historyDatas.add(item);
            SPUtils.saveString(mContext, SpConstant.HISTORIES, item);
        } else {

            if (!historyDatas.contains(item)) {
                historyDatas.add(item);
                Logger.d("saveHistory-->:" + item);
                SPUtils.saveString(mContext, SpConstant.HISTORIES, historyDatas.toString());
            }
        }
    }

    /**
     * ==================软键盘搜索按钮监听=====================
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        String trim = etSearch.getText().toString().trim();

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (TextUtils.isEmpty(trim)) {
                ToastUtils.showShortToast(this, "搜索内容不能为空");
            } else {

                if (mViewpager != null) {
                    setupViewPager(trim);
                }
            }
            return true;
        }
        return false;
    }


    /**
     * ==================初始化fragment=====================
     */
    private void setupViewPager(String data) {
        //添加历史记录
        searchText = data;
        refreshSearchText();
        updateHistories(searchText, false);
        mNestedScrollView.setVisibility(View.GONE);
        mLlSearch.setVisibility(View.VISIBLE);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SearchFragment.newInstance(data, 0), "本地资讯");
        adapter.addFragment(SearchFragment.newInstance(data, 1), "全网资讯");
        adapter.addFragment(SearchFragment.newInstance(data, 2), "本地圈");

        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewpager);
    }

    /**
     * ==================监听切换搜索页面=====================
     */
    TextWatcher getTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s)) {
                mNestedScrollView.setVisibility(View.VISIBLE);
                mLlSearch.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}