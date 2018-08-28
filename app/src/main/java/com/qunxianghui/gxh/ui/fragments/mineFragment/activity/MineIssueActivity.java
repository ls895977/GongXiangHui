package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueDiscloseFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueGoodSelectFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueLocalServiceFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssurePostFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssureVideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class MineIssueActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.mine_MyIssureTablayout_common)
    TabLayout mineMyIssureTablayoutCommon;
    @BindView(R.id.mine_MyIssure_viewpager)
    ViewPager mineMyIssureViewpager;
    @BindView(R.id.iv_myissue_back)
    ImageView ivMyissueBack;
    @BindView(R.id.tv_mineissue_edit)
    TextView tvMineissueEdit;
    @BindView(R.id.tv_myissue_cancel)
    TextView tvMyissueCancel;
    private String[] titles = new String[]{"爆料", "视频", "本地圈", "本地服务", "精选"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    private int position;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_issue;
    }

    @Override
    protected void initViews() {
        //设置tablayout的一个显示方式
        mineMyIssureTablayoutCommon.setTabMode(TabLayout.MODE_FIXED);
        for (String tab : titles) {
            mineMyIssureTablayoutCommon.addTab(mineMyIssureTablayoutCommon.newTab().setText(tab));
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initData() {
        super.initData();
        fragments.add(new MyIssueDiscloseFragment());
        fragments.add(new MyIssureVideoFragment());
        fragments.add(new MyIssurePostFragment());
        fragments.add(new MyIssueLocalServiceFragment());
        fragments.add(new MyIssueGoodSelectFragment());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMyIssureViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMyIssureViewpager.setOffscreenPageLimit(fragments.size());
        mineMyIssureTablayoutCommon.setupWithViewPager(mineMyIssureViewpager);

    }

    @Override
    protected void initListeners() {
        ivMyissueBack.setOnClickListener(this);

        tvMineissueEdit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mineissue_edit:
                MineIssueEditDataState();
                break;
            case R.id.iv_myissue_back:
                break;
        }
    }

    /*我的发布的编辑状态*/
    private void MineIssueEditDataState() {
        if (mineMyIssureViewpager.getCurrentItem() == 0) {
            EventManager.getInstance().publishMessage("baoliao");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 1) {
            EventManager.getInstance().publishMessage("video");
        }

        if (mineMyIssureViewpager.getCurrentItem() == 2) {
            EventManager.getInstance().publishMessage("localcircle");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 3) {
            EventManager.getInstance().publishMessage("localservice");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 4) {
            EventManager.getInstance().publishMessage("goodselect");
        }
        tvMyissueCancel.setVisibility(View.VISIBLE);
        ivMyissueBack.setVisibility(View.GONE);
    }
}
