package com.qunxianghui.gxh.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.TestRecyclerviewAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.TestModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewTestActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<TestModel> mDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_test;
    }

    @Override
    protected void initViews() {
        super.initViews();

        mDatas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            TestModel testModel = new TestModel();
            testModel.setTitle("我是第" + i + "条标题");
            testModel.setContent("第" + i + "条内容");
            mDatas.add(testModel);

        }
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        TestRecyclerviewAdapter adapter = new TestRecyclerviewAdapter(R.layout.item_recyclerview_test, mDatas);
        /*给recyclerview设置适配器*/
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                asyncShowToast("点击;额" + position + "个图片");

            }
        });
        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_img) {
                    asyncShowToast("长按了" + (position + 1) + "picture");
                } else if (view.getId() == R.id.tv_title) {
                    asyncShowToast("长按了" + position + "标题");
                }
                TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.tv_title);
                Log.d("长按了的图片", "当前图片对应的title" + tv_title.getText());
                return false;
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                asyncShowToast("长按了" + (position + 1) + "条数目");
                return false;
            }
        });

        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        //设置重复执行动画
        adapter.isFirstOnly(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
