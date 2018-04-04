package com.gongxianghui.fragments.homeFragment.activity;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.gongxianghui.adapter.homeAdapter.SimpleTextAdapter;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.utils.GsonUtil;
import com.gongxianghui.utils.SPUtils;
import com.gongxianghui.utils.StatusBarUtil;
import com.gongxianghui.widget.SpaceSize;
import com.gongxianghui.widget.SpacesItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {

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
    private String searchText;
    private List<String> historyDatas = new LinkedList<>();
    private Gson gson = new Gson();
    private SimpleTextAdapter historyAdapter;

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

        rvSearchGuess.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rvSearchGuess.addItemDecoration(new SpacesItemDecoration(new SpaceSize(20, 12, 20, 12)));
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSearchHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    /**
     * 加载历史记录
     */
    @Override
    protected void initDatas() {
        initHistories();
    }

    private void initHistories() {
        final String histories = SPUtils.getString(mContext, "histories", "");
        LinkedList<String> datas = (LinkedList<String>) GsonUtil.parseJsonToList(histories, new TypeToken<LinkedList<String>>() {

        }.getType());


        if (datas != null) {
            historyDatas = datas;
        }

        historyDatas.add("凯迪拉克");
        historyDatas.add("赵龙涛测试数据");
        if (historyDatas != null && historyDatas.size() != 0) {
            historyAdapter = new SimpleTextAdapter(mContext, historyDatas);
            historyAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    searchText = historyDatas.get(position);
                    refreshSearchText();
                }
            });
            rvSearchHistory.setAdapter(historyAdapter);
//             ivClearHistory.setVisibility(View.VISIBLE);
        }

    }

    private void refreshSearchText() {
        etSearch.setText(searchText);
        /**
         * 移动到末尾
         */
        etSearch.setSelection(etSearch.getText().length());
        doSearch();
    }

    @Override
    protected void initListeners() {
        ivClearHistory.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
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
            case R.id.iv_clear_history:
                SPUtils.saveString(mContext, "histories", "");
                v.setVisibility(View.GONE);
                updateHistories(null, true);
                break;
            case R.id.tv_cancel:
                if (tvCancel.getText().equals("取消")) {
                    finish();
                } else {
                    if (tvCancel.getText().equals("搜索")) {
                        doSearch();
                    }
                }
                break;
            case R.id.et_search:
                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(s)) {
                            tvCancel.setText("搜索");
                            doQueryResult(s.toString());
                            llResult.setVisibility(View.VISIBLE);
                            llHomeSearch.setVisibility(View.INVISIBLE);
                        } else {

                            tvCancel.setText("取消");
                            llResult.setVisibility(View.INVISIBLE);
                            llHomeSearch.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                /**
                 * 软键盘回车事件
                 */

                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            doSearch();
                        }
                        return false;
                    }
                });
                break;
        }
    }

    /**
     * 请求搜索
     *
     * @param text
     */
    private void doQueryResult(String text) {
        Toast.makeText(mContext, "请求搜索的操作", Toast.LENGTH_SHORT).show();
    }

    /**
     * 搜索操作
     */
    private void doSearch() {

        searchText = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(searchText)) {
            updateHistories(searchText, false);
        }

    }

    private void updateHistories(String item, boolean clear) {
        if (clear) {
            historyDatas.clear();

        } else {
            if (TextUtils.isEmpty(item)) {
                ((LinkedList<String>) historyDatas).addFirst(item);
                final String value = gson.toJson(historyDatas);
                Log.i(TAG, "updateHistories: " + value);
                SPUtils.saveString(mContext, "histories", value);
            }
        }
        historyAdapter.notifyDataSetChanged();

    }


}
