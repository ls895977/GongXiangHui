package com.qunxianghui.gxh.ui.fragments.mineFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.UserInfo;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanyCardActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.JoinCallActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MemberUpActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MineIssueActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MineMessageActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyCollectActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyFansActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyFollowActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.SettingActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.udesk.PreferenceHelper;
import cn.udesk.UdeskSDKManager;
import cn.udesk.config.UdeskConfig;
//import cn.udesk.PreferenceHelper;
//import cn.udesk.UdeskSDKManager;
//import cn.udesk.config.UdeskConfig;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.mine_user_name)
    TextView mMineUserName;
    @BindView(R.id.tv_mine_company_name)
    TextView mTvMineCompanyName;
    @BindView(R.id.tv_member_type)
    TextView mTvMemberType;
    @BindView(R.id.tv_mine_collect)
    TextView mTvMineCollect;
    @BindView(R.id.tv_mine_follow)
    TextView mTvMineFollow;
    @BindView(R.id.tv_mine_fans)
    TextView mTvMineFans;
    @BindView(R.id.tv_minemesssage_count)
    TextView tvMineMesssageCount;
    @BindView(R.id.ll_mine_services_center)
    RelativeLayout llMineServicesCenter;

    private UserInfo.DataBean mUserInfo;
    private boolean mIsFirst = true;
    private Unbinder mUnbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor() {
        Window window = mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }

    @Override
    protected void setStatusBarTextColor() {
        StatusBarColorUtil.setStatusTextColor(false, mActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.sMsgCount == 0) {
            tvMineMesssageCount.setVisibility(View.GONE);
        } else {
            tvMineMesssageCount.setVisibility(View.VISIBLE);
            tvMineMesssageCount.setText(String.valueOf(MainActivity.sMsgCount));
        }
//
//
///*        默认系统字段是Udesk已定义好的字段，开发者可以直接传入这些用户信息，供客服查看。*/
//        String sdktoken = "用户唯一的标识";
//        Map<String, String> info = new HashMap<String, String>();
////   sdktoken 必填
//        info.put(UdeskConst.UdeskUserInfo.USER_SDK_TOKEN, sdktoken);
//        //以下信息是可选
//        info.put(UdeskConst.UdeskUserInfo.NICK_NAME,"昵称");
//        info.put(UdeskConst.UdeskUserInfo.EMAIL,"0631@163.com");
//        info.put(UdeskConst.UdeskUserInfo.CELLPHONE,"15651818750");
//        info.put(UdeskConst.UdeskUserInfo.DESCRIPTION,"描述信息");
//
////        只设置用户基本信息的配置
//        UdeskConfig.Builder builder = new UdeskConfig.Builder();
//        builder.setDefualtUserInfo(info);
//        UdeskSDKManager.getInstance().entryChat(mActivity, builder.build(), sdktoken);
    }

    @Override
    public void onStart() {
        super.onStart();
        fillUserData();
    }

    private void fillUserData() {
        OkGo.<UserInfo>post(Constant.CATCH_USERDATA_URL).
                execute(new JsonCallback<UserInfo>() {
                    @Override
                    public void onSuccess(Response<UserInfo> response) {
                        parseUserData(response.body());
                    }
                });
    }

    private void parseUserData(UserInfo userInfo) {
        if (userInfo.code == 0) {
            mUserInfo = userInfo.data;
//            String mMobile = mUserInfo.agency_info.mobile;
            if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                mTvMineCompanyName.setText(mUserInfo.company_info.company_name);
            } else {
                mTvMineCompanyName.setText("");
            }
            Glide.with(getContext()).load(mUserInfo.avatar).apply(new RequestOptions()
                    .placeholder(R.mipmap.user_moren).error(R.mipmap.user_moren).centerCrop().circleCrop()).into(mIvUserAvatar);
            mMineUserName.setText(mUserInfo.nick);
            mTvMemberType.setText(mUserInfo.level_info.name);
            mTvMineCollect.setText(mUserInfo.collect_cnt);
            mTvMineFollow.setText(mUserInfo.follow_cnt);
            mTvMineFans.setText(mUserInfo.be_follow_cnt);

            SharedPreferences spConpanyname = mActivity.getSharedPreferences("companymessage", 0);
            SharedPreferences.Editor editor = spConpanyname.edit();
            editor.putString("avatar", mUserInfo.avatar).putString("member_expire_time", mUserInfo.expire_time).apply();

        }
    }

    @OnClick({R.id.rl_company_card, R.id.rl_mine_message, R.id.mine_fabu, R.id.hezuo_call, R.id.rl_up_step, R.id.write_advertise, R.id.ll_mine_post,
            R.id.ll_mine_fans, R.id.ll_mine_set, R.id.rl_mine_person_data, R.id.ll_mine_mycollect, R.id.ll_mine_services_center})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_company_card:
                if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userinfo", mUserInfo);
                    toActivity(CompanyCardActivity.class, bundle);
                } else {
                    asyncShowToast("请升级企业会员后再试！");
                }
                break;
            case R.id.rl_mine_message:
                if (isLogin()) {
                    intent = new Intent(getContext(), MineMessageActivity.class);
                    intent.putExtra("isHasMsg", MainActivity.sMsgCount != 0);
                    startActivity(intent);
                }
                break;
            case R.id.mine_fabu:
                if (isLogin())
                    toActivity(MineIssueActivity.class);
                break;
            case R.id.rl_up_step:
                toActivity(MemberUpActivity.class);
                break;
            case R.id.rl_mine_person_data:
                Bundle bundle = new Bundle();
                bundle.putSerializable("userinfo", mUserInfo);
                toActivity(PersonDataActivity.class, bundle);
                break;
            case R.id.hezuo_call:
//                requestCall(mMobile);
                toActivity(JoinCallActivity.class);
                break;
            case R.id.write_advertise:
                intent = new Intent(mActivity, AdvertTemplateActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_set:
                toActivity(SettingActivity.class);

                break;
            case R.id.ll_mine_post:
                if (isLogin())
                    toActivity(MyFollowActivity.class);
                break;
            case R.id.ll_mine_fans:
                if (isLogin())
                    toActivity(MyFansActivity.class);
                break;
            case R.id.ll_mine_mycollect:
                if (isLogin())
                    toActivity(MyCollectActivity.class);
                break;
            case R.id.ll_mine_services_center:
                CollectServiceCenter();
                break;
        }
    }

    /*联系客服*/
    private void CollectServiceCenter() {
        String sdkToken = PreferenceHelper.readString(mActivity.getApplicationContext(), "init_base_name", "sdktoken");
        if (TextUtils.isEmpty(sdkToken)) {
            sdkToken = UUID.randomUUID().toString();
        }
        //咨询会话
        UdeskSDKManager.getInstance().entryChat(mActivity.getApplicationContext(), UdeskConfig.createDefualt(), sdkToken);

    }

    private boolean isLogin() {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return false;
        }
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}

