package com.qunxianghui.gxh.fragments.mineFragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AdvertisConmmengtActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MemberUpActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MessageGatherActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MineIssueActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MineMessageActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.MyCollectActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.SettingActivity;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.HttpStatusUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragment extends BaseFragment {
    private static MineFragment mineFragment;
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
    @BindView(R.id.company_set)
    RelativeLayout companySet;
    @BindView(R.id.hezuo_call)
    RelativeLayout hezuoCall;
    @BindView(R.id.write_advertise)
    RelativeLayout writeAdvertise;
    @BindView(R.id.tv_mine_set)
    TextView tvMineSet;
    @BindView(R.id.rl_up_step)
    RelativeLayout rlUpStep;
    //头像
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    //会员类型
    @BindView(R.id.tv_member_type)
    TextView mTvMemberType;
    //用户名
    @BindView(R.id.mine_quickly_login)
    TextView mineQuicklyLogin;
    @BindView(R.id.tv_mine_addlike_count)
    TextView tvMineAddlikeCount;
    Unbinder unbinder;
    @BindView(R.id.tv_mine_post_count)
    TextView tvMinePostCount;
    @BindView(R.id.tv_mine_follow_post_count)
    TextView tvMineFollowPostCount;
    @BindView(R.id.ll_mine_post)
    LinearLayout llMinePost;
    @BindView(R.id.ll_mine_fllow_post)
    LinearLayout llMineFllowPost;
    private UserDao userDao;
    private int userSize;
    private String mAvatar;//头像
    private String mNick;//名称
    private String mLevelName;//等级
    private String mMobile;//手机
    private String mAddress;//地址
    private int mSex;//性别
    private int like_cnt;
    private int posts_cnt;
    private int comment_cnt;
    private Object companyInfo;
    private String expires_time;
    private String companyName;


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

    private void fillUserData() {
        OkGo.<String>post(Constant.CATCH_USERDATA_URL).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body().toString())) {
                            parseUserData(response.body());
                            return;
                        }
                        Logger.d("onSuccess-->:" + response.body().toString());
                    }
                });
    }

    private void parseUserData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONObject data = jsonObject.getJSONObject("data");
//            JSONObject accessTokenInfo = jsonObject.getJSONObject("accessTokenInfo");
//            expires_time = accessTokenInfo.getString("expires_time");

            mNick = data.getString("nick");
            mAvatar = data.getString("avatar");
            mMobile = data.getString("mobile");
            mAddress = data.getString("address");
            mSex = data.getInt("sex");
            like_cnt = data.getInt("like_cnt");
            posts_cnt = data.getInt("posts_cnt");
            comment_cnt = data.getInt("comment_cnt");
            mLevelName = data.getJSONObject("level_info").getString("name");
            companyName = data.getJSONObject("company_info").getString("company_name");

            mTvMemberType.setText(mLevelName);
            mineQuicklyLogin.setText(mNick);
            tvMineAddlikeCount.setText(String.valueOf(like_cnt));
            tvMinePostCount.setText(String.valueOf(posts_cnt));
            tvMineFollowPostCount.setText(String.valueOf(comment_cnt));

            GlideApp.with(getActivity()).load(mAvatar).
                    placeholder(R.mipmap.user_moren).
                    error(R.mipmap.user_moren).
                    circleCrop().
                    into(mIvHead);


            companyInfo = new JSONTokener(data.getString("company_info")).nextValue();

            if (companyInfo instanceof JSONArray) {
                Logger.d("fillUserData-->数组:");
            } else if (companyInfo instanceof Object) {
                Logger.d("fillUserData-->对象:");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
/**
 * 保存自己的公司名称
 */

        SharedPreferences spConpanyname = mActivity.getSharedPreferences("conpanyname", 0);
        SharedPreferences.Editor editor = spConpanyname.edit();
        editor.putString("selfcompayname", companyName);
        editor.commit();


    }

    @Override
    public void initViews(View view) {
        userDao = new UserDao(mActivity);
        userSize = userDao.dbGetUserSize();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        fillUserData();
//        toCollectView();
    }

    private void toCollectView() {
    }

    @OnClick({R.id.rl_preson_data, R.id.rl_message_gather, R.id.rl_mine_message, R.id.rl_mine_collect, R.id.mine_fabu, R.id.company_set, R.id.hezuo_call, R.id.tv_mine_set, R.id.rl_up_step, R.id.write_advertise,
            R.id.mine_quickly_login, R.id.ll_mine_post, R.id.ll_mine_fllow_post})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_preson_data:
                Bundle bundle = new Bundle();
                bundle.putString(PersonDataActivity.AVATAR, mAvatar);
                bundle.putString(PersonDataActivity.NICK, mNick);
                bundle.putString(PersonDataActivity.MOBILE, mMobile);
                bundle.putString(PersonDataActivity.ADDRESS, mAddress);
                bundle.putInt(PersonDataActivity.SEX, mSex);
                toActivity(PersonDataActivity.class, bundle);
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
                requestCall();
                break;
            case R.id.write_advertise:
                AdvertisConmmengtActivity.sIsFromNews = false;
                toActivity(AdvertisConmmengtActivity.class);
                break;

            case R.id.mine_quickly_login:
                toActivity(LoginActivity.class);
                break;
            case R.id.tv_mine_set:
                toActivity(SettingActivity.class);
                break;
            case R.id.ll_mine_post:
                asyncShowToast("点击了帖子");
                intent = new Intent(mActivity, MineIssueActivity.class);
                intent.putExtra("index", 2);
                startActivity(intent);
                break;
            case R.id.ll_mine_fllow_post:
                asyncShowToast("点击了跟帖");
                intent = new Intent(mActivity, MineMessageActivity.class);
                intent.putExtra("index", 1);
                startActivity(intent);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MineFragment getInstance() {
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        return mineFragment;
    }


}
