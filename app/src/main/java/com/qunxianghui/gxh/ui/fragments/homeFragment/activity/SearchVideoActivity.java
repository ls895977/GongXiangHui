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
import android.view.WindowManager;
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
import com.qunxianghui.gxh.adapter.homeAdapter.FireSearchVideoAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.SimpleTextAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.fragments.homeFragment.fragments.SearchVideoFragment;
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

public class SearchVideoActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    @BindView(R.id.iv_video_search_back)
    ImageView ivVideoSearchBack;
    @BindView(R.id.et_video_search)
    EditText etVideoSearch;
    @BindView(R.id.tv_video_search)
    TextView tvVideoSearch;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_video_clear_history)
    ImageView ivVideoClearHistory;
    @BindView(R.id.rv_video_search_history)
    RecyclerView rvVideoSearchHistory;
    @BindView(R.id.rv_video_search_guess)
    RecyclerView rvVideoSearchGuess;
    @BindView(R.id.ll_home_video_search)
    LinearLayout llHomeVideoSearch;
    @BindView(R.id.nested_video_scroll_view)
    NestedScrollView nestedVideoScrollView;
    @BindView(R.id.home_video_top_bar)
    LinearLayout homeVideoTopBar;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    private String searchText = "";
    private List<String> historyDatas = new ArrayList<>();
    private SimpleTextAdapter historyAdapter;
    private String trim;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_video_search;
    }

    @Override
    protected void initViews() {
        viewpager.setOffscreenPageLimit(2);
        //解决嵌套滑动的问题
        rvVideoSearchGuess.setNestedScrollingEnabled(false);
        rvVideoSearchHistory.setNestedScrollingEnabled(false);
        rvVideoSearchGuess.addItemDecoration(new SpacesItemDecoration(new SpaceSize(20, 12, 20, 12)));
        rvVideoSearchHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        StatusBarUtil.setViewTopPadding(this, R.id.home_video_top_bar);
    }


    @Override
    protected void initData() {
        //猜你想要的数据
        OkGo.<HomeVideoSearchBean>get(Constant.SEARCH_VIDEO_GUESS_URL)
                .execute(new JsonCallback<HomeVideoSearchBean>() {
                    @Override
                    public void onSuccess(Response<HomeVideoSearchBean> response) {
                        HomeVideoSearchBean guessVideoBean = response.body();
                        if (guessVideoBean.getCode() == 200) {
                            List<HomeVideoSearchBean.DataBean> videoData = guessVideoBean.getData();
                            initFireRecycle(videoData);
                        }
                    }
                });
        initHistories();
    }

    /**
     * ==================猜你想要的数据=====================
     */
    private void initFireRecycle(final List<HomeVideoSearchBean.DataBean> guessVideoBean) {
        FireSearchVideoAdapter adapter = new FireSearchVideoAdapter(mContext, guessVideoBean);
        rvVideoSearchGuess.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                searchText = guessVideoBean.get(position).getTitle();
                refreshSearchText();
                tvVideoSearch.performClick();
            }
        });
    }

    /*加载历史记录*/
    private void initHistories() {
        String histories = SPUtils.getString(SpConstant.HISTORIES, "");
        if (!TextUtils.isEmpty(histories)) {
            historyDatas = new ArrayList<>(Arrays.asList(histories.substring(1, histories.length() - 1).split(",")));
            historyAdapter = new SimpleTextAdapter(mContext, historyDatas);
            historyAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    searchText = historyDatas.get(position);
                    refreshSearchText();
                    tvVideoSearch.performClick();
                }
            });
            rvVideoSearchHistory.setAdapter(historyAdapter);
        } else {
            ivVideoClearHistory.setVisibility(View.GONE);
        }
    }

    /**
     * ==================点击猜你想要是的进行搜索=====================
     */
    private void refreshSearchText() {
        etVideoSearch.setText(searchText);
        etVideoSearch.setSelection(etVideoSearch.getText().length());

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
        ivVideoClearHistory.setOnClickListener(this);
        etVideoSearch.setOnClickListener(this);
        tvVideoSearch.setOnClickListener(this);
        ivVideoSearchBack.setOnClickListener(this);
        etVideoSearch.setOnEditorActionListener(this);
        etVideoSearch.addTextChangedListener(getTextChanged);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空历史
            case R.id.iv_video_clear_history:
                updateHistories("", true);
                v.setVisibility(View.GONE);

                break;
            //搜索按钮
            case R.id.tv_video_search:
                trim = etVideoSearch.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtils.showShortToast(this, "搜索内容不能为空");
                } else if (viewpager != null) {
                    setupViewPager(trim);
                }
                break;
            case R.id.iv_video_search_back:
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
        if (historyDatas.size() == 0) {
            historyDatas.add(item);
            SPUtils.saveString(SpConstant.HISTORIES, item);
        } else {
            if (!historyDatas.contains(item)) {
                historyDatas.add(item);
                SPUtils.saveString(SpConstant.HISTORIES, historyDatas.toString());
            }
        }
    }

    /**
     * ==================软键盘搜索按钮监听=====================
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String trim = etVideoSearch.getText().toString().trim();
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

    /**
     * ==================初始化fragment=====================
     */
    private void setupViewPager(String data) {
        //添加历史记录
        searchText = data;
        refreshSearchText();
        updateHistories(searchText, false);
        nestedVideoScrollView.setVisibility(View.GONE);
        llSearch.setVisibility(View.VISIBLE);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SearchVideoFragment.newInstance(data));
        viewpager.setAdapter(adapter);

    }

    /**
     * ==================监听切换搜索页面=====================
     */
    TextWatcher getTextChanged = new NewTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
                nestedVideoScrollView.setVisibility(View.VISIBLE);
                llSearch.setVisibility(View.GONE);
            }
        }
    };

}