package com.qunxianghui.gxh.fragments.mineFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.User;
import com.qunxianghui.gxh.bean.mine.MineUserBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CooperationCallActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.InviteFrientActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MemberUpActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MessageGatherActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MineIssueActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MineMessageActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MyCollectActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.SettingActivity;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.rl_preson_data) RelativeLayout rlPresonData;
    @BindView(R.id.rl_message_gather) RelativeLayout rlMessageGather;
    @BindView(R.id.rl_mine_message) RelativeLayout rlMineMessage;
    @BindView(R.id.rl_mine_collect) RelativeLayout rlMineCollect;
    @BindView(R.id.mine_fabu) RelativeLayout mineFabu;

    @BindView(R.id.company_set) RelativeLayout companySet;
    @BindView(R.id.hezuo_call) RelativeLayout hezuoCall;
    @BindView(R.id.write_advertise) RelativeLayout writeAdvertise;
    @BindView(R.id.rl_invite_friend) RelativeLayout rlInviteFriend;
    @BindView(R.id.tv_mine_set) TextView tvMineSet;
    @BindView(R.id.rl_up_step) RelativeLayout rlUpStep;

    //头像
    @BindView(R.id.iv_head) ImageView mIvHead;
    //会员类型
    @BindView(R.id.tv_member_type) TextView mTvMemberType;
    //用户名
    @BindView(R.id.mine_quickly_login) TextView mineQuicklyLogin;

    private UserDao userDao;
    private int userSize;
    private MineUserBean mMineUserBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initDatas() {


        if (userSize > 0) {

            final ArrayList<User> userList = userDao.dbQueryAll();
            for (int i = 0; i < userSize; i++) {
                final User user = userList.get(i);
                mineQuicklyLogin.setText(user.getUsername());
            }
        }



    }


    /**==================获取用户信息(资料)=====================*/
    private void fillUserData() {


        OkGo.<String>post(Constant.CATCH_USERDATA_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                if (HttpStatusUtil.getStatus(response.body().toString())) {
                    Logger.d("onSuccess-->:" + response.body().toString());
                    parseUserData(response.body());
                    return;
                }
                                        toActivity(LoginActivity.class);
                Logger.d("onSuccess-->:" + response.body().toString());
            }
        });

    }

    private void parseUserData(String body) {
        mMineUserBean = GsonUtil.parseJsonWithGson(body, MineUserBean.class);
        final MineUserBean.DataBean data = mMineUserBean.getData();
        mineQuicklyLogin.setText(data.getNick());
        mTvMemberType.setText(data.getLevel_info().getName());

        GlideApp.with(getActivity()).load(data.getAvatar()).
                placeholder(R.mipmap.user_moren).
                error(R.mipmap.user_moren).
                circleCrop().
                into(mIvHead);
    }

    @Override
    public void initViews(View view) {
        userDao = new UserDao(mActivity);
        userSize = userDao.dbGetUserSize();
    }


    @Override
    public void onResume() {
        super.onResume();
        fillUserData();

    }

    @OnClick({R.id.rl_preson_data, R.id.rl_message_gather, R.id.rl_mine_message, R.id.rl_mine_collect, R.id.mine_fabu, R.id.company_set, R.id.hezuo_call, R.id.tv_mine_set, R.id.rl_up_step, R.id
            .write_advertise, R.id.rl_invite_friend, R.id.mine_quickly_login})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_preson_data:
                Bundle bundle = new Bundle();
                bundle.putSerializable(PersonDataActivity.MINEI_USER_DATA,mMineUserBean);
                toActivity(PersonDataActivity.class,bundle);
                break;
            case R.id.rl_message_gather:
                toActivity(MessageGatherActivity.class);

                break;
            case R.id.rl_mine_message:
                toActivity(MineMessageActivity.class);
                break;
            case R.id.rl_mine_collect:
                toActivity(MyCollectActivity.class);
                break;
            case R.id.mine_fabu:
                toActivity(MineIssueActivity.class);
                break;
            case R.id.rl_up_step:
                toActivity(MemberUpActivity.class);
                break;
            case R.id.company_set:
                toActivity(CompanySetActivity.class);
                break;
            case R.id.hezuo_call:
                toActivity(CooperationCallActivity.class);
                break;
            case R.id.write_advertise:
                toActivity(AddAdverActivity.class);
                break;
            case R.id.rl_invite_friend:
                toActivity(InviteFrientActivity.class);

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
