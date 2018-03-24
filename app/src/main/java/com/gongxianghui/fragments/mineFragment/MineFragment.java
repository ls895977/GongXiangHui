package com.gongxianghui.fragments.mineFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.mineFragment.activity.LoginActivity;
import com.gongxianghui.fragments.mineFragment.activity.MessageGatherActivity;
import com.gongxianghui.fragments.mineFragment.activity.MyCollectActivity;
import com.gongxianghui.fragments.mineFragment.activity.PersonDataActivity;
import com.gongxianghui.fragments.mineFragment.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.rl_preson_data)
    RelativeLayout rlPresonData;
    @BindView(R.id.rl_message_gather)
    RelativeLayout rlMessageGather;
    @BindView(R.id.rl_mine_message)
    RelativeLayout rlMineMessage;
    @BindView(R.id.rl_mine_collect)
    RelativeLayout rlMineCollect;
    @BindView(R.id.mine_fabu)
    RelativeLayout mineFabu;
    @BindView(R.id.rl_up_step)
    RelativeLayout rlUpStep;
    @BindView(R.id.company_set)
    RelativeLayout companySet;
    @BindView(R.id.hezuo_call)
    RelativeLayout hezuoCall;
    @BindView(R.id.write_advertise)
    RelativeLayout writeAdvertise;
    @BindView(R.id.rl_invite_friend)
    RelativeLayout rlInviteFriend;
    Unbinder unbinder;
    @BindView(R.id.mine_quickly_login)
    TextView mineQuicklyLogin;
    @BindView(R.id.tv_mine_set)
    TextView tvMineSet;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

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

    @OnClick({R.id.rl_preson_data, R.id.rl_message_gather, R.id.rl_mine_message, R.id.rl_mine_collect,
            R.id.mine_fabu, R.id.rl_up_step, R.id.company_set, R.id.hezuo_call, R.id.tv_mine_set,
            R.id.write_advertise, R.id.rl_invite_friend, R.id.mine_quickly_login})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_preson_data:
                toActivity(PersonDataActivity.class);
                break;
            case R.id.rl_message_gather:
                toActivity(MessageGatherActivity.class);

                break;
            case R.id.rl_mine_message:

                break;
            case R.id.rl_mine_collect:
                toActivity(MyCollectActivity.class,true);
                break;
            case R.id.mine_fabu:
                break;
            case R.id.rl_up_step:
                break;
            case R.id.company_set:
                break;
            case R.id.hezuo_call:
                break;
            case R.id.write_advertise:
                break;
            case R.id.rl_invite_friend:
                break;
            case R.id.mine_quickly_login:
                toActivity(LoginActivity.class);
                break;
            case R.id.tv_mine_set:
                toActivity(SettingActivity.class);
                break;

        }
    }


}
