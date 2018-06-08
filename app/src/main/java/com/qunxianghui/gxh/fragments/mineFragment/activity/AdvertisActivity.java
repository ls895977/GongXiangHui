package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.recycler_addAdver_list)
    XRecyclerView recyclerAddAdverList;
    @BindView(R.id.tv_top_addAdaver)
    TextView tvTopAddAdaver;
    @BindView(R.id.iv_top_addAdverBack)
    ImageView ivTopAddAdverBack;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    private TabLayout.Tab six;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏整个Actionbar
        setContentView(R.layout.activity_advertise);
        ButterKnife.bind(this);

        initViews();
        initData();
        initListener();


    }

    private void initListener() {

        tvTopAddAdaver.setOnClickListener(this);
        ivTopAddAdverBack.setOnClickListener(this);
        recyclerAddAdverList.setLoadingMoreEnabled(true);

        recyclerAddAdverList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyclerAddAdverList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                recyclerAddAdverList.refreshComplete();
            }
        });
    }

    private void initData() {
        recyclerAddAdverList.setLayoutManager(new LinearLayoutManager(AdvertisActivity.this, LinearLayoutManager.VERTICAL, false));

    }

    private void initViews() {
        recyclerAddAdverList.setPullRefreshEnabled(true);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);
        //指定tab的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);
        five = tabLayout.getTabAt(4);
        six = tabLayout.getTabAt(5);

        //设置tab的图标
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.ic_launcher);
        five.setIcon(R.mipmap.ic_launcher);
        six.setIcon(R.mipmap.ic_launcher);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_top_addAdaver:
                recyclerChangeData();
                break;
            case R.id.iv_top_addAdverBack:
                finish();
                break;
        }
    }

    private void recyclerChangeData() {
        recyclerAddAdverList.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);

        tvTopAddAdaver.setText("保存");
        tvTopAddAdaver.setTextSize(15);
        tvTopAddAdaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdaverSelect();

            }
        });

    }

    private void saveAdaverSelect() {
        Toast.makeText(AdvertisActivity.this, "点击了保存", Toast.LENGTH_SHORT).show();

    }
}
