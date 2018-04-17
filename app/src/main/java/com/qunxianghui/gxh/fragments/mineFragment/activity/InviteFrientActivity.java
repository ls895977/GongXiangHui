package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.TestRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {
    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.invite_recycler_view)
    RecyclerView inviteRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {
       inviteRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

       inviteRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        final TestRecyclerAdapter adapter= new TestRecyclerAdapter(mDatas, mContext);
        inviteRecyclerView.setAdapter(adapter);
        adapter.setOnMyItemClickListener(new TestRecyclerAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                Toast.makeText(mContext, "onClick"+pos, Toast.LENGTH_SHORT).show();
                System.out.println("onClick---"+pos);
                adapter.addItem(pos);
            }

            @Override
            public void mLongClick(View v, int pos) {
                Toast.makeText(mContext, "onLongClick"+pos, Toast.LENGTH_SHORT).show();
                System.out.println("onClick---"+pos);
                adapter.removeData(pos);

            }
        });

    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 30; i++) {
            mDatas.add("条目---" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



