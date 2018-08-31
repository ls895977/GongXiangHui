package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.TabEntity;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueDiscloseFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueGoodSelectFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssueLocalServiceFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssurePostFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssureVideoFragment;
import com.qunxianghui.gxh.ui.view.MyScrollViewPage;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class MineIssueActivity extends BaseActivity implements View.OnClickListener,Observer {

    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.mine_MyIssure_viewpager)
    MyScrollViewPage mineMyIssureViewpager;
    @BindView(R.id.iv_myissue_back)
    ImageView ivMyissueBack;
    @BindView(R.id.tv_myissue_cancel)
    TextView tvMyissueCancel;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;

    private String[] mTabTitles = new String[]{"爆料", "视频", "本地圈", "本地服务", "精选"};
    private List<Fragment> fragments = new ArrayList<>();
    public static boolean sIsDeletes;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_issue;
    }

    @Override
    protected void initViews() {
        EventManager.getInstance().addObserver(this);
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        for (String title : mTabTitles) {
            tabEntities.add(new TabEntity(title, 0, 0));
        }
        mTabLayout.setTabData(tabEntities);
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
        MineTabViewPagerAdapter mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, null);
        mineMyIssureViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMyIssureViewpager.setOffscreenPageLimit(fragments.size());
    }
    /**
     *
     * @param canClick
     */
    public void setTabLayoutCanClick(boolean canClick){
        LinearLayout tabStrip= (LinearLayout) mTabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if(tabView !=null){
                tabView.setClickable(canClick);
            }
        }
    }



    private void setViewPageSlide(boolean canChange) {
        mineMyIssureViewpager.setScanScroll(canChange);
        setTabLayoutCanClick(canChange);
    }


    @Override
    protected void initListeners() {
        ivMyissueBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mineMyIssureViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mineMyIssureViewpager.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }
        });
        tvMyissueCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
//                if ("编辑".equals(mTvEdit.getText().toString())) {
//                    mTvEdit.setText("删除");
//                    sIsDeletes = true;
//                } else {
//                    mTvEdit.setText("编辑");
//                    sIsDeletes = false;
//                }
                MineIssueEditDataState();
                break;
            case R.id.iv_myissue_back:
                finish();
                break;
            case R.id.tv_myissue_cancel:
                MineIssueCancelDataState();
                break;
        }
    }

    /*我的发布的删除状态*/
    private void MineIssueCancelDataState() {
        EventManager.getInstance().publishMessage(false);
//                isEdit = true;
        if (mineMyIssureViewpager.getCurrentItem() == 0) {
            EventManager.getInstance().publishMessage("baoliao_c");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 1) {
            EventManager.getInstance().publishMessage("issue_video_c");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 2) {
            EventManager.getInstance().publishMessage("localcircle_c");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 3) {
            EventManager.getInstance().publishMessage("localser_c");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 4) {
            EventManager.getInstance().publishMessage("goodselect_c");
        }
        ivMyissueBack.setVisibility(View.VISIBLE);
        tvMyissueCancel.setVisibility(View.GONE);
        setViewPageSlide(true);
    }

    /*我的发布的编辑状态*/
    private void MineIssueEditDataState() {
        EventManager.getInstance().publishMessage(false);
//                isEdit = true;
        if (mineMyIssureViewpager.getCurrentItem() == 0) {
            EventManager.getInstance().publishMessage("baoliao");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 1) {
            EventManager.getInstance().publishMessage("issue_video");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 2) {
            EventManager.getInstance().publishMessage("localcircle");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 3) {
            EventManager.getInstance().publishMessage("localser");
        }
        if (mineMyIssureViewpager.getCurrentItem() == 4) {
            EventManager.getInstance().publishMessage("goodselect");
        }
        ivMyissueBack.setVisibility(View.GONE);
        tvMyissueCancel.setVisibility(View.VISIBLE);
        setViewPageSlide(false);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "init".equals(o)) {
            tvMyissueCancel.setVisibility(View.GONE);
            ivMyissueBack.setVisibility(View.VISIBLE);
            setViewPageSlide(true);
        }
    }
}
