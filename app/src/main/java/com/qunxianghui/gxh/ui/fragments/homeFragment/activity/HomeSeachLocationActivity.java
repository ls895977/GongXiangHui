package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.SearchListAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class HomeSeachLocationActivity extends BaseActivity {
    private List<String> list = new ArrayList<>();

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_setaddress)
    EditText etSetaddress;
    @BindView(R.id.tv_setaddress_search)
    TextView tvSetaddressSearch;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.lv_seerchaddress)
    ListView lvSeerchaddress;
    private SearchListAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_seach_location;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        list.add("看着飞舞的尘埃   掉下来");
        list.add("没人发现它存在");
        list.add("多自由自在");
        list.add("可世界都爱热热闹闹");
        list.add("容不下   我百无聊赖");
        list.add("不应该   一个人 发呆");
        list.add("只有我   守着安静的沙漠");
        list.add("等待着花开");
        list.add("只有我   看着别人的快乐");
// 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
        adapter = new SearchListAdapter(list, mContext, new SearchListAdapter.FilterListener() {
            @Override
            public void getFilterData(List<String> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                Log.e(TAG, "接口回调成功");
                Log.e(TAG, list.toString());
                setItemClick(list);

            }
        });
        lvSeerchaddress.setAdapter(adapter);
    }

    //给listView添加点击事件
    private void setItemClick(final List<String> list) {
        lvSeerchaddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                asyncShowToast(list.get(position));
            }
        });

    }

    @Override
    protected void initListeners() {
        //没有进行搜索时 也要对listView的item进行单机监听
        setItemClick(list);
        //对编辑框添加文本改变监听 搜索的具体功能在这里实现 重写onTextChange方法
        etSetaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//如果adapter不为空的话就根据编辑框的内容来过滤数据
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
