package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.UserDetailInfoBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineCommonFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.PersonDetailPostFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.PersonDetailVideoFragment;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_person_detail_back)
    ImageView ivPersonDetailBack;
    @BindView(R.id.iv_person_detail_head)
    RoundImageView ivPersonDetailHead;
    @BindView(R.id.tv_person_detail_name)
    TextView tvPersonDetailName;
    @BindView(R.id.tv_person_detail_attention)
    TextView tvPersonDetailAttention;
    @BindView(R.id.mine_tablayout_person_detail)
    TabLayout mineTablayoutPersonDetail;
    @BindView(R.id.mine_person_detail_viewpager)
    ViewPager minePersonDetailViewpager;
    @BindView(R.id.rl_persondetail_bg)
    RelativeLayout rlPersondetailBg;
    @BindView(R.id.tv_persondetail_introduce)
    TextView tvPersondetailIntroduce;
    @BindView(R.id.tv_persondetail_follow)
    TextView tvPersondetailFollow;
    @BindView(R.id.tv_persondetail_fans)
    TextView tvPersondetailFans;

    private String[] titles = new String[]{"资讯", "视频", "帖子"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    public int member_id;
    private String follow;
    private UserDetailInfoBean.DataBean dataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initViews() {
        //设置tablayout的一个显示方式
        mineTablayoutPersonDetail.setTabMode(TabLayout.MODE_FIXED);
        for (String tab : titles) {
            mineTablayoutPersonDetail.addTab(mineTablayoutPersonDetail.newTab().setText(tab));
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rlPersondetailBg.getLayoutParams();
        params.height = height / 3;
        rlPersondetailBg.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        final Intent intent = getIntent();
        member_id = intent.getIntExtra("member_id", 1);
        FetchPersonData();
        Bundle bundle = new Bundle();
        bundle.putInt("member_id", member_id);
        MineCommonFragment mineCommonFragment = new MineCommonFragment();
        mineCommonFragment.setArguments(bundle);
        fragments.add(mineCommonFragment);
        fragments.add(new PersonDetailVideoFragment());
        fragments.add(new PersonDetailPostFragment());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        minePersonDetailViewpager.setAdapter(mineTabViewPagerAdapter);
        minePersonDetailViewpager.setOffscreenPageLimit(2);
        mineTablayoutPersonDetail.setupWithViewPager(minePersonDetailViewpager);
    }

    /**
     * 获取用户详情资料
     */
    private void FetchPersonData() {
        OkGo.<UserDetailInfoBean>get(Constant.GET_USER_DETAIL_URL)
                .params("member_id", member_id)
                .execute(new JsonCallback<UserDetailInfoBean>() {
                    @Override
                    public void onSuccess(Response<UserDetailInfoBean> response) {
                        parseUserDetailInfo(response.body());
                    }
                });
    }

    //解析用户的详情资料
    private void parseUserDetailInfo(UserDetailInfoBean userDetailInfoBean) {
        if (userDetailInfoBean.getCode() == 200) {
            dataList = userDetailInfoBean.getData();
            follow = dataList.getFollow();
            if (TextUtils.isEmpty(follow)) {
                tvPersonDetailAttention.setText("关注");
            } else {
                tvPersonDetailAttention.setText("已关注");
            }
            tvPersonDetailName.setText(dataList.getNick());
            tvPersondetailIntroduce.setText(dataList.getSelf_introduction());
            tvPersondetailFollow.setText(String.valueOf("关注 " + dataList.getFollow_num()));
            tvPersondetailFans.setText(String.valueOf(" 粉丝 " + dataList.getFans_num()));
            Glide.with(mContext).load(dataList.getMember_avatar())
                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(ivPersonDetailHead);

        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivPersonDetailBack.setOnClickListener(this);
        tvPersonDetailAttention.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_person_detail_back:
                finish();
                break;
            case R.id.tv_person_detail_attention:
                acctionPerson();
                break;
        }
    }

    private void acctionPerson() {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            finish();
        } else {
            OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                    .params("be_member_id", member_id)
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(final Response<CommonBean> response) {
                            int code = response.body().code;
                            if (code == 0) {
                                asyncShowToast("关注成功");
                                tvPersonDetailAttention.setText("已关注");
                                dataList.setFollow("true");
                            } else if (code == 202) {
                                asyncShowToast("取消关注成功");
                                tvPersonDetailAttention.setText("关注");
                                dataList.setFollow("");
                            } else if (code == 101) {
                                asyncShowToast("请不要自己关注自己");
                            }
                        }
                    });
        }

    }

}
