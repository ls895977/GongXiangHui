package com.qunxianghui.gxh.fragments.homeFragment.activity;


import android.content.Intent;
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

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.FireSearchAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.QueryResultAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.SimpleTextAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.FireSearchBean;
import com.qunxianghui.gxh.bean.home.QueryBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.widget.SpaceSize;
import com.qunxianghui.gxh.widget.SpacesItemDecoration;
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
    private String searchText="";
    private List<String> historyDatas = new LinkedList<>();
    private Gson gson = new Gson();
    private SimpleTextAdapter historyAdapter;
    private List<QueryBean.DataBean> queryResult;
    private QueryResultAdapter resultAdapter;

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


    @Override
    protected void initDatas() {
        OkGo.<String>get(Constant.API_FIRE_SEARCH)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final FireSearchBean searchBean = GsonUtil.parseJsonWithGson(response.body(), FireSearchBean.class);
                        if(searchBean.getErrno()==0){
                            final List<FireSearchBean.DataBean> data = searchBean.getData();
                            initFireRecycle(data);
                        }
                    }
                });

        initHistories();
    }

    private void initFireRecycle(final List<FireSearchBean.DataBean> searchBean) {
        final FireSearchAdapter adapter = new FireSearchAdapter(mContext, searchBean);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
              searchText= searchBean.get(position).getName();
                refreshSearchText();
            }
        });
        rvSearchGuess.setAdapter(adapter);


    }

    /*加载历史记录*/
    private void initHistories() {
        final String histories = SPUtils.getString(mContext, "histories", "");
        LinkedList<String> datas = (LinkedList<String>) GsonUtil.parseJsonToList(histories, new TypeToken<LinkedList<String>>() {


        }.getType());

        if (datas != null) {
            historyDatas = datas;
            historyDatas.add("凯迪拉克");
            historyDatas.add("赵龙涛测试数据");
        }

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


        OkGo.<String>get(Constant.API_CAR_QUERY)
                .params("text",text)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        final QueryBean queryBean = GsonUtil.parseJsonWithGson(response.body(), QueryBean.class);
                        if(queryBean.getErrno()==0){
                            queryResult = queryBean.getData();
                            if(queryResult.size()!=0){
                                showResultView();
                            }else {
                                Toast.makeText(mContext, "没有找到您想要的内容", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    /**
     * 显示搜索结果列表
     */
    private void showResultView() {
        if(resultAdapter!=null){
            resultAdapter.refresh(queryResult);
            resultAdapter.notifyDataSetChanged();
            return;
        }
        handleListView();
    }


    /**
     * 搜索结果列表
     */
    private void handleListView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearchResult.setLayoutManager(manager);
        resultAdapter = new QueryResultAdapter(mContext, queryResult);
        resultAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                final QueryBean.DataBean dataBean = queryResult.get(position);
                /**
                 * 跳转详情页面
                 */
                final Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("carid", dataBean.getId());
                startActivity(intent);
            }
        });
        rvSearchResult.setAdapter(resultAdapter);
    }

    /**
     * 搜索操作
     */
    private void doSearch() {

        searchText = etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(searchText)) {
        updateHistories(searchText, false);
    }

}

    /**
     * 刷新历史记录
     * @param item
     * @param clear
     */
    private void updateHistories(String item, boolean clear) {
        if (clear) {
            historyDatas.clear();

        } else {
            if (!TextUtils.isEmpty(item)) {
                ((LinkedList<String>) historyDatas).addFirst(item);
                final String value = gson.toJson(historyDatas);
                Log.i(TAG, "updateHistories: " + value);
                SPUtils.saveString(mContext, "histories", value);
            }
        }
        historyAdapter.notifyDataSetChanged();

    }


}
