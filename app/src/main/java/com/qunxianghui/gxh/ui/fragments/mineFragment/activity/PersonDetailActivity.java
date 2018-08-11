package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.UserDetailInforBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineCommonFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.PersonDetailPostFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.PersonDetailVideoFragment;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.RoundImageView;

import org.json.JSONObject;

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
    private String[] titles = new String[]{"资讯", "视频", "帖子"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    public int member_id;
    private String follow;
    private UserDetailInforBean.DataBean dataList;

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
        int width = displayMetrics.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rlPersondetailBg.getLayoutParams();
        params.height = height * 1 / 3;
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
        OkGo.<String>get(Constant.GET_USER_DETAIL_URL)
                .params("member_id", member_id).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseUserDetailInfo(response.body());
            }
        });

    }

    //解析用户的详情资料
    private void parseUserDetailInfo(String body) {
        UserDetailInforBean userDetailInforBean = GsonUtils.jsonFromJson(body, UserDetailInforBean.class);
        dataList = userDetailInforBean.getData();
        follow = dataList.getFollow();
        if (follow.toString().equals("")) {
            tvPersonDetailAttention.setText("关注");
        } else {
            tvPersonDetailAttention.setText("已关注");
        }

        if (userDetailInforBean.getCode() == 0) {
            tvPersonDetailName.setText(dataList.getNick());

            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(dataList.getMember_avatar()).apply(options).into(ivPersonDetailHead);

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
        OkGo.<String>post(Constant.ATTENTION_URL).params("be_member_id", member_id).execute(new StringCallback() {
            @Override
            public void onSuccess(final Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code == 0) {
                        asyncShowToast("关注成功");
                        tvPersonDetailAttention.setText("已关注");
                        dataList.setFollow("true");
                    } else if (code == 202) {
                        asyncShowToast("取消关注成功");
                        tvPersonDetailAttention.setText("关注");
                        dataList.setFollow("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
