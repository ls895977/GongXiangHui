package com.qunxianghui.gxh.ui.fragments.mineFragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.User;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AdvertTemplateActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanyCardActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MemberUpActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MineIssueActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MineMessageActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyCollectActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyFansActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MyFollowActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.SettingActivity;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.SPUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.rl_mine_message)
    RelativeLayout rlMineCollect;
    @BindView(R.id.mine_fabu)
    RelativeLayout companySet;
    @BindView(R.id.hezuo_call)
    RelativeLayout hezuoCall;
    @BindView(R.id.write_advertise)
    RelativeLayout writeAdvertise;
    @BindView(R.id.rl_up_step)
    RelativeLayout rlUpStep;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_member_type)
    TextView mTvMemberType;
    @BindView(R.id.mine_quickly_login)
    TextView mineQuicklyLogin;
    @BindView(R.id.tv_mine_addlike_collect)
    TextView tvMineAddlikeCollect;
    @BindView(R.id.tv_mine_post_count)
    TextView tvMinePostCount;
    @BindView(R.id.tv_mine_follow_post_count)
    TextView tvMineFollowPostCount;
    @BindView(R.id.ll_mine_post)
    LinearLayout llMinePost;
    @BindView(R.id.ll_mine_fans)
    LinearLayout mLlMineFances;
    @BindView(R.id.ll_mine_set)
    RelativeLayout llMineSet;
    @BindView(R.id.tv_mine_company_name)
    TextView tvMineCompanyName;
    @BindView(R.id.rl_mine_person_data)
    RelativeLayout rlMinePersonData;
    @BindView(R.id.ll_mine_mycollect)
    LinearLayout llMineMycollect;
    @BindView(R.id.rl_company_card)
    RelativeLayout mrlCompanyCard;
    Unbinder unbinder1;
    private UserDao userDao;
    private int userSize;
    private String mAvatar;//头像
    private String mNick;//名称
    private String mLevelName;//等级
    private String mMobile;//手机
    private String mAddress;//地址
    private int mSex;//性别
    private int posts_cnt;
    private int comment_cnt;
    private Object companyInfo;
    private String companyName;
    private int code;
    private String mUsername;
    private String mEmail;
    private String mCompanyName;
    private String mDuty;
    private String mSelfIntroduction;
    private String mCollectCount;
    private String mFollow_cnt;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        if (mineQuicklyLogin == null) return;
        if (userSize > 0) {
            final ArrayList<User> userList = userDao.dbQueryAll();
            for (int i = 0; i < userSize; i++) {
                final User user = userList.get(i);
                mineQuicklyLogin.setText(user.getUsername());
            }
        }
    }

    private void parseUserData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONObject data = jsonObject.getJSONObject("data");
            code = jsonObject.getInt("code");
            if (code == 0) {
                mNick = data.getString("nick");
                mAvatar = data.getString("avatar");
                mMobile = data.getString("mobile");
                mAddress = data.getString("address");
                companyName = data.getString("company_name");
                mSex = data.getInt("sex");
                mUsername = data.getString("username");
                mCollectCount = data.getString("collect_cnt");
                mFollow_cnt = data.getString("follow_cnt");
                mDuty = data.getString("duty");
                mEmail = data.getString("email");
                mSelfIntroduction = data.getString("self_introduction");
                comment_cnt = data.getInt("comment_cnt");

                mLevelName = data.getJSONObject("level_info").getString("name");
                if (SPUtils.getBoolean(SpConstant.IS_COMPANY, false)) {
                    mCompanyName = data.getJSONObject("company_info").getString("company_name");
                    tvMineCompanyName.setText(mCompanyName);
                } else {
                    tvMineCompanyName.setText("");
                }
                companyInfo = new JSONTokener(data.getString("company_info")).nextValue();
                mTvMemberType.setText(mLevelName);
                mineQuicklyLogin.setText(mNick);
                tvMineAddlikeCollect.setText(mCollectCount);
                tvMinePostCount.setText(String.valueOf(posts_cnt));
                tvMineFollowPostCount.setText(String.valueOf(comment_cnt));
                tvMinePostCount.setText(mCollectCount);
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.user_moren);
                options.error(R.mipmap.user_moren);
                options.centerCrop();
                options.circleCrop();
                Glide.with(getActivity()).load(mAvatar).apply(options).into(mIvHead);

                /**
                 * 保存自己的公司名称
                 */
                SharedPreferences spConpanyname = mActivity.getSharedPreferences("companymessage", 0);
                SharedPreferences.Editor editor = spConpanyname.edit();
                editor.putString("avatar", mAvatar);
                editor.apply();
            }

            if (companyInfo instanceof JSONArray) {
                Logger.d("fillUserData-->数组:");
            } else if (companyInfo instanceof Object) {
                Logger.d("fillUserData-->对象:");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void fillUserData() {
        OkGo.<String>post(Constant.CATCH_USERDATA_URL).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body().toString())) {
                            parseUserData(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (code == 1000) {
                            asyncShowToast("您的账号在异地登录");
                        }
                    }
                });
    }

    @OnClick({R.id.rl_company_card, R.id.rl_mine_message, R.id.mine_fabu, R.id.hezuo_call, R.id.rl_up_step, R.id.write_advertise, R.id.ll_mine_post,
            R.id.ll_mine_fans, R.id.ll_mine_set, R.id.rl_mine_person_data, R.id.ll_mine_mycollect})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_company_card:
                if (SPUtils.getBoolean(SpConstant.IS_COMPANY, false)) {
                    toActivity(CompanyCardActivity.class);
                }else {
                    asyncShowToast("请升级企业会员后再试！");
                }

                break;
            case R.id.rl_mine_message:
                toActivity(MineMessageActivity.class);
                break;
            case R.id.mine_fabu:
                toActivity(MineIssueActivity.class);
                break;
            case R.id.rl_up_step:
                toActivity(MemberUpActivity.class);
                break;
            case R.id.rl_mine_person_data:
                Bundle bundle = new Bundle();
                bundle.putString(PersonDataActivity.AVATAR, mAvatar);
                bundle.putString(PersonDataActivity.NICK, mNick);
                bundle.putString(PersonDataActivity.MOBILE, mMobile);
                bundle.putString(PersonDataActivity.ADDRESS, mAddress);
                bundle.putInt(PersonDataActivity.SEX, mSex);
                bundle.putString(PersonDataActivity.USER_NAME, mUsername);
                bundle.putString(PersonDataActivity.USER_EMAIL, mEmail);
                bundle.putString(PersonDataActivity.USER_INTRODUCTION, mSelfIntroduction);
                bundle.putString(PersonDataActivity.USER_DUTY, mDuty);
                bundle.putString(PersonDataActivity.USER_COMPANY, companyName);
                toActivity(PersonDataActivity.class, bundle);
                break;
            case R.id.hezuo_call:
                requestCall();
                break;
            case R.id.write_advertise:
                intent = new Intent(mActivity, AdvertTemplateActivity.class);
                intent.putExtra("adverTag", 1);
                startActivity(intent);
                break;
            case R.id.ll_mine_set:
                toActivity(SettingActivity.class);
                break;
            case R.id.ll_mine_post:
                toActivity(MyFollowActivity.class);
                break;
            case R.id.ll_mine_fans:
                toActivity(MyFansActivity.class);
                break;
            case R.id.ll_mine_mycollect:
                toActivity(MyCollectActivity.class);
                break;
        }
    }

    private void requestCall() {
        if (PermissionsUtil.hasPermission(mActivity, new String[]{Manifest.permission.CALL_PHONE})) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4001884660"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4001884660"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    asyncShowToast("权限被禁止  设置权限后再试试.");
                }
            }, new String[]{Manifest.permission.CALL_PHONE});
        }
    }
}
