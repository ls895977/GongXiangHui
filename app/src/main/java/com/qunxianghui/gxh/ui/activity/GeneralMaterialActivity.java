package com.qunxianghui.gxh.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.EnterpriseMateriaItemFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.EnterpriseMaterialFragment;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GeneralMaterialActivity extends BaseActivity {

    @BindView(R.id.segment_tab)
    SegmentTabLayout mSegmentTab;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.vp)
    NoScrollViewPager mVp;

    public static int sType;
    public static boolean sIsMultiSelect;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advert_template;
    }

    @Override
    protected void initViews() {
        sType = getIntent().getIntExtra("type", 1);
        sIsMultiSelect = getIntent().getBooleanExtra("isMultiSelect", false);
        mTvSave.setText("确定");
        mSegmentTab.setTabData(new String[]{"底部", "顶部"});
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new EnterpriseMaterialFragment());
        Bundle bundle = new Bundle();
        Fragment fragment = new EnterpriseMateriaItemFragment();
        bundle.putInt("type", 3);
        bundle.putInt("position", 1);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        mVp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    protected void initListeners() {
        mSegmentTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_save:
                if (EnterpriseMateriaItemFragment.mList.isEmpty()) {
                    asyncShowToast("请至少选择一个对应素材!");
                    return;
                }
                setResult(0x0022);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EnterpriseMateriaItemFragment.mList.clear();
        EnterpriseMateriaItemFragment.mList = null;
    }

}
