package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.CommonAdaverGridAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisConmmengtActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rb_adver_bottom)
    RadioButton rbAdverBottom;
    @BindView(R.id.rb_adver_top)
    RadioButton rbAdverTop;
    @BindView(R.id.rb_adver_video_poster)
    RadioButton rbAdverVideoPoster;
    @BindView(R.id.rg_advercommon_main)
    RadioGroup rgAdverCommonMain;
    @BindView(R.id.recycler_commonadver_bottom)
    RecyclerView recyclerCommonadverBottom;
    //广告底部导航的坐标匹配
    private int[] images = {R.mipmap.icon_adver_company_material, R.mipmap.icon_adver_common_material, R.mipmap.icon_adver_bigpic, R.mipmap.icon_adver_card,
            R.mipmap.icon_adver_banner, R.mipmap.icon_adver_scan, R.mipmap.icon_adver_qq, R.mipmap.icon_adver_shop
            , R.mipmap.icon_adver_image_text, R.mipmap.icon_adver_education};

    private String[] iconName = {"企业素材", "通用素材", "大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "店铺广告", "图文广告", "教学视频"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advercomment;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int adverTag = intent.getIntExtra("adverTag", 0);
        if (adverTag == 1) {
            rbAdverVideoPoster.setVisibility(View.VISIBLE);
        }
        recyclerCommonadverBottom.setLayoutManager(new GridLayoutManager(mContext, 5));
    }

    @Override
    protected void initDatas() {
        CommonAdaverGridAdapter commonBottomAdverAdapter = new CommonAdaverGridAdapter(mContext, images, iconName);

        recyclerCommonadverBottom.setAdapter(commonBottomAdverAdapter);
        commonBottomAdverAdapter.setmOnItemClickListener(new CommonAdaverGridAdapter.OnItemClickListener() {
            @Override
            public void onpicItemClick(int position) {
                switch (position) {
                    case 0:
                        asyncShowToast("企业素材");
                        break;

                    case 1:
                        asyncShowToast("通用素材");
                        break;

                    case 2:
                        asyncShowToast("大图通栏");
                        break;
                    case 3:
                        asyncShowToast("名片广告");
                        break;
                    case 4:
                        asyncShowToast("通栏广告");
                        break;
                    case 5:
                        asyncShowToast("二维码广告");
                        break;
                    case 6:
                        asyncShowToast("QQ广告");
                        break;
                    case 7:
                        asyncShowToast("店铺广告");

                        break;
                    case 8:
                        asyncShowToast("图文广告");
                        break;
                    case 9:
                        asyncShowToast("教学视频");

                        break;

                }
            }
        });

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        rgAdverCommonMain.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_adver_top:
                break;
            case R.id.rb_adver_bottom:
                asyncShowToast("点击了底部");
                break;
        }
    }
}
