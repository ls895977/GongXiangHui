package com.qunxianghui.gxh.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.PagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.GeneralMaterial;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.GeneralMateriaItemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GeneralMaterialActivity extends BaseActivity {

    @BindView(R.id.tab)
    SlidingTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    public int sType;
    public static boolean sIsMultiSelect;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_general_material;
    }

    @Override
    protected void initViews() {
        GeneralMateriaItemFragment.mList = new ArrayList<>();
        sType = getIntent().getIntExtra("type", 1);
        sIsMultiSelect = getIntent().getBooleanExtra("isMultiSelect", false);
    }

    @Override
    protected void initData() {
        OkGo.<GeneralMaterial>get(Constant.GENERAL_AD)
                .execute(new JsonCallback<GeneralMaterial>() {
                    @Override
                    public void onSuccess(Response<GeneralMaterial> response) {
                        if (response.body() != null && response.body().code == 200 && !response.body().data.cate.isEmpty()) {
                            List<GeneralMaterial.GeneralMaterialBean.Cate> cate = response.body().data.cate;
                            Fragment fragment;
                            Bundle bundle;
                            String[] titles = new String[cate.size()];
                            PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), titles);
                            for (int i = 0; i < cate.size(); i++) {
                                fragment = new GeneralMateriaItemFragment();
                                bundle = new Bundle();
                                bundle.putInt("ad_type", cate.get(i).id);
                                bundle.putInt("type", sType);
                                fragment.setArguments(bundle);
                                titles[i] = cate.get(i).cate_name;
                                adapter.addFragment(fragment);
                            }
                            if (sIsMultiSelect) mVp.setOffscreenPageLimit(adapter.getCount() - 1);
                            mVp.setAdapter(adapter);
                            mTab.setViewPager(mVp);
                        }
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_confirm:
                if (GeneralMateriaItemFragment.mList.isEmpty()) {
                    asyncShowToast("请至少选择一个对应素材!");
                    return;
                }
                setResult(0x0033);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GeneralMateriaItemFragment.clearData();
    }

}
