package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFocusAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.UserDetailInforBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCollectPostFrament;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCollectVideoFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCommonFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MyIssurePostFragment;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailActivity extends BaseActivity  implements View.OnClickListener{
    @BindView(R.id.iv_person_detail_back)
    ImageView ivPersonDetailBack;
    @BindView(R.id.iv_person_detail_head)
    RoundImageView ivPersonDetailHead;
    @BindView(R.id.tv_person_detail_name)
    TextView tvPersonDetailName;
    @BindView(R.id.tv_person_detail_setep)
    TextView tvPersonDetailSetep;
    @BindView(R.id.tv_person_detail_attention)
    TextView tvPersonDetailAttention;
    @BindView(R.id.mine_tablayout_person_detail)
    TabLayout mineTablayoutPersonDetail;
    @BindView(R.id.mine_person_detail_viewpager)
    ViewPager minePersonDetailViewpager;
    private String[] titles = new String[]{"资讯", "视频", "帖子"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    public int member_id;
    private String follow;

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
    }

    @Override
    protected void initDatas() {
        final Intent intent = getIntent();
        member_id = intent.getIntExtra("member_id", 1);

        FetchPersonData();


        fragments.add(new MineCommonFragment());
        fragments.add(new MineCollectVideoFragment());
        fragments.add(new MyIssurePostFragment());

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        minePersonDetailViewpager.setAdapter(mineTabViewPagerAdapter);
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
        final UserDetailInforBean.DataBean dataList = userDetailInforBean.getData();
        follow = dataList.getFollow();
        if (userDetailInforBean.getCode() == 0) {
            tvPersonDetailName.setText(dataList.getNick());
            tvPersonDetailSetep.setText(dataList.getLevel_info().getName());
            GlideApp.with(mContext).load(dataList.getMember_avatar()).centerCrop()
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivPersonDetailHead);
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivPersonDetailBack.setOnClickListener(this);
        tvPersonDetailAttention.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_person_detail_back:
                finish();
                break;
            case R.id.tv_person_detail_attention:
                acctionPerson();

                break;
        }
    }

    private void acctionPerson() {
   OkGo.<String> post(Constant.ATTENTION_URL).params("be_member_id",member_id).execute(new StringCallback() {
       @Override
       public void onSuccess(final Response<String> response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                 if (follow.equals(null)){
                     asyncShowToast("关注成功");
                     tvPersonDetailAttention.setText("已关注");

                 }else if (follow.equals("")){
                     asyncShowToast("取消关注成功");
                     tvPersonDetailAttention.setText("关注");

                 }

                }
            });
       }
   });
    }
}
