package com.gongxianghui.fragments.homeFragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.gongxianghui.R;
import com.gongxianghui.activity.BianMinActiviry;
import com.gongxianghui.activity.ScanActivity;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.homeFragment.activity.SalerActivity;
import com.gongxianghui.widget.ImageViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @BindView(R.id.rb_home_air)
    RadioButton rbHomeAir;
    @BindView(R.id.rb_home_video)
    RadioButton rbHomeVideo;
    @BindView(R.id.rb_home_life_style)
    RadioButton rbHomeLifeStyle;
    @BindView(R.id.rb_home_saler)
    RadioButton rbHomeSaler;
    @BindView(R.id.rb_home_bianmin)
    RadioButton rbHomeBianmin;
    @BindView(R.id.ib_home_camera)
    ImageButton ibHomeCamera;
    @BindView(R.id.ib_home_search)
    ImageButton ibHomeSearch;
    @BindView(R.id.ib_home_scan)
    ImageButton ibHomeScan;
    @BindView(R.id.viewpager_home)
    ConvenientBanner viewpagerHome;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }



    @Override
    public void initDatas() {
        viewpagerHome.setPages(new CBViewHolderCreator<ImageViewHolder>() {

            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, localImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}) //设置两个点作为指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器的方向水平
                .startTurning(2000)
                .setCanLoop(true);

    }

    @Override
    public void initViews(View view) {

        PermissionGen.needPermission(HomeFragment.this, 105,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
        );
        initImageLoader();
        loadTestDatas();


    }

    @Override
    protected void initListeners() {
        rbHomeAir.setOnClickListener(this);
        rbHomeVideo.setOnClickListener(this);
        rbHomeLifeStyle.setOnClickListener(this);
        rbHomeSaler.setOnClickListener(this);
        rbHomeBianmin.setOnClickListener(this);
        ibHomeScan.setOnClickListener(this);
    }

    private void initImageLoader() {
    }

    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.mipmap.class));
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rb_home_air:
                break;
            case R.id.rb_home_video:
                break;
            case R.id.rb_home_life_style:
                break;
            case R.id.rb_home_saler:
                intent = new Intent(mActivity, SalerActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_home_bianmin:
                intent = new Intent(mActivity, BianMinActiviry.class);
                startActivity(intent);
                break;
            case R.id.ib_home_camera:            //爆料
                break;
            case R.id.ib_home_scan:            //扫描二维码
                intent = new Intent(mActivity, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_home_search:          //搜索
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.finish();
    }
}
