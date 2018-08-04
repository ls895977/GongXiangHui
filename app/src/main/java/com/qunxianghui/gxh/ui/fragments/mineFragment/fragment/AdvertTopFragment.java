package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.AdvertPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.ui.dialog.AdvertChoosePicDialog;
import com.qunxianghui.gxh.ui.dialog.TongLanChooseTypeDialog;
import com.qunxianghui.gxh.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdvertTopFragment extends BaseFragment implements View.OnClickListener, AdvertChoosePicDialog.ImgPickListener {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.circleIndicatorView)
    CircleIndicatorView mCircleIndicatorView;

    private AdvertPagerAdapter mPagerAdapter;
    private TongLanChooseTypeDialog mChooseType;
    private AdvertChoosePicDialog mChoosePic;
    private List<View> mViewList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert_top;
    }

    @Override
    public void initData() {
        mPagerAdapter = new AdvertPagerAdapter(mViewList);
        mVp.setAdapter(mPagerAdapter);
    }

    @Override
    protected void initListeners() {
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeCircleView();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.ll_company, R.id.ll_common, R.id.ll_common_advert, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_company:
                asyncShowToast("通用模版");
                break;
            case R.id.ll_common:
                asyncShowToast("通用素材");
                break;
            case R.id.ll_common_advert:
                addPage();
                break;
            case R.id.ll_video:
                asyncShowToast("教学视频");
                break;
        }
    }

    @Override
    public void pickListener(View view) {
        switch (view.getId()) {
            case R.id.btnPhoto:
                break;
            case R.id.btnPick:
                break;
            case R.id.btnPicFromLocal:
                break;
            case R.id.btnCommon:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                int index = (int) mViewList.get(mVp.getCurrentItem()).getTag();
                mViewList.remove(index);
                mPagerAdapter.notifyDataSetChanged();
                changeCircleView();
                break;
            case R.id.iv_add_big_img:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                }
                mChoosePic.showLocalView().show();
                break;
            case R.id.tv_choose_type:
                if (mChooseType == null && getContext() != null) {
                    mChooseType = new TongLanChooseTypeDialog(getContext(), mViewList, mVp);
                }
                mChooseType.show();
                break;
            case R.id.tv_choose_activity_link:
                //todo 跳转选择链接
                asyncShowToast("跳转选择链接");
                break;
            case R.id.ivAd:
                if (mChoosePic == null && getContext() != null) {
                    mChoosePic = new AdvertChoosePicDialog(getContext());
                }
                mChoosePic.hideLocalView().show();
                break;
        }
    }

    private void addPage() {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.ad_item_tonglan, mVp, false);
        ((TextView) view.findViewById(R.id.tv_title)).setText("通栏广告");
        view.findViewById(R.id.rl_add_img).setVisibility(View.GONE);
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        view.findViewById(R.id.iv_add_big_img).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_type).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_activity_link).setOnClickListener(this);
        view.findViewById(R.id.ivAd).setOnClickListener(this);
        ((AppCompatCheckBox) view.findViewById(R.id.cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        ((SwitchButton) view.findViewById(R.id.sw)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mViewList.add(view);
        mPagerAdapter.notifyDataSetChanged();
        mVp.setCurrentItem(mPagerAdapter.getCount() - 1, false);
        changeCircleView();
    }

    private void changeCircleView() {
        mCircleIndicatorView.setCircleCount(mPagerAdapter.getCount());
        mCircleIndicatorView.setCircleSelectedPosition(mVp.getCurrentItem());
        mCircleIndicatorView.setSelectedCircleRadius(8);
        mCircleIndicatorView.setCircleUnSelectedColor(Color.parseColor("#BDBDBD"));
        mCircleIndicatorView.drawCircleView();
    }

}
