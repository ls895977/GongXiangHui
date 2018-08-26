package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.PagerAdapter;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.FireSearchAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.SimpleTextAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.GuessBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.fragments.homeFragment.fragments.SearchFragment;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.widget.SpaceSize;
import com.qunxianghui.gxh.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
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
    LinearLayout topBar;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.rv_search_guess)
    RecyclerView rvSearchGuess;
    @BindView(R.id.iv_clear_history)
    ImageView ivClearHistory;
    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;
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
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mViewpager.setOffscreenPageLimit(2);
        //解决嵌套滑动的问题
        rvSearchGuess.setNestedScrollingEnabled(false);
        rvSearchHistory.setNestedScrollingEnabled(false);
        rvSearchGuess.addItemDecoration(new SpacesItemDecoration(new SpaceSize(20, 12, 20, 12)));
        rvSearchHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initData() {
        //猜你想要的数据
        OkGo.<GuessBean>get(Constant.SEARCH_GUESS_URL)
                .execute(new JsonCallback<GuessBean>() {
                    @Override
                    public void onSuccess(Response<GuessBean> response) {
                        GuessBean guessBean = response.body();
                        if (guessBean.getCode() == 0) {
                            List<GuessBean.DataBean> data = guessBean.getData();
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
        if (guessBean != null) {
            FireSearchAdapter adapter = new FireSearchAdapter(mContext, guessBean);
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
    }

    /*加载历史记录*/
    private void initHistories() {
        String histories = SPUtils.getString(SpConstant.HISTORIES, "");
        if (!TextUtils.isEmpty(histories)) {
            historyDatas = new ArrayList<>(Arrays.asList(histories.substring(1, histories.length() - 1).split(",")));
        } else {
            historyDatas = new ArrayList<>();
            ivClearHistory.setVisibility(View.GONE);
        }
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
                } else if (mViewpager != null) {
                    setupViewPager(trim);
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
            SPUtils.removePreference(SpConstant.HISTORIES);
        } else {
            saveHistory(item);
        }
        historyAdapter.notifyDataSetChanged();
    }

    /**
     * ==================保存历史=====================
     */
    private void saveHistory(String item) {
        if (historyDatas.isEmpty()) {
            historyDatas.add(item);
            SPUtils.saveString(SpConstant.HISTORIES, historyDatas.toString());
        } else if (!historyDatas.contains(item)) {
            historyDatas.add(item);
            SPUtils.saveString(SpConstant.HISTORIES, historyDatas.toString());
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
                setupViewPager(trim);
            }
            return true;
        }
        return false;
    }

    private void setupViewPager(String data) {
        //添加历史记录
        searchText = data;
        refreshSearchText();
        updateHistories(searchText, false);
        mNestedScrollView.setVisibility(View.GONE);
        mLlSearch.setVisibility(View.VISIBLE);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SearchFragment.newInstance(data, 1));
        mViewpager.setAdapter(adapter);

    }

    TextWatcher getTextChanged = new NewTextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
                mNestedScrollView.setVisibility(View.VISIBLE);
                mLlSearch.setVisibility(View.GONE);
            }
        }
    };

}