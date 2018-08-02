package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.utils.DataCleanManager;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_setting_quit)
    TextView mTvSettingQuit;
    @BindView(R.id.tv_mine_set_cache)
    TextView tvMineSetCache;
    @BindView(R.id.rl_set_cache)
    RelativeLayout rlSetCache;
    @BindView(R.id.switchButton_mine_set)
    SwitchButton switchButtonMineSet;
    @BindView(R.id.tv_mine_set_version)
    TextView tvMineSetVersion;
    @BindView(R.id.rl_set_banben_new)
    RelativeLayout rlSetBanbenNew;
    private Dialog loadingDialog;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    loadingDialog.dismiss();
                    tvMineSetCache.setText("0.0KB");
                    break;
                case 0x02:
                    loadingDialog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        //获得应用内部缓存(/data/data/com.example.androidclearcache/cache)

        final File file = new File(this.getCacheDir().getPath());
        try {
            tvMineSetCache.setText(DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        new TitleBuilder(this).setTitleText("设置").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                DataCleanManager.cleanInternalCache(getApplicationContext());
            }
        });
    }

    @Override
    protected void initListeners() {
        mTvSettingQuit.setOnClickListener(this);
        rlSetCache.setOnClickListener(this);
        switchButtonMineSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String s;
                if (isChecked) {
                    s = "选中做的处理";

                } else {
                    s = "关闭";
                }
                asyncShowToast(s);

            }
        });
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.tv_setting_quit:
                //*此按钮可点击时已经是登录状态*/
                new AlertDialog.Builder(mContext).setTitle("是否退出登录").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* 清楚登录信息*/
                        UMShareAPI.get(mContext).deleteOauth(SettingActivity.this, SHARE_MEDIA.QQ, null);
                        deleteDatabase("SqliteTest.db");
                        ExitUserLogin();
                    }
                }).setNeutralButton("否", null).show();
                break;
            case R.id.rl_set_cache:
                final Message msg = new Message();
                loadingDialog = createLoadingDialog(SettingActivity.this, "清理中...");
                loadingDialog.show();
                try {
//                    DataCleanManager.cleanInternalCache(getApplicationContext());
                    DataCleanManager.deleteFile_Dir(getCacheDir());
                    msg.what = 0x01;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = 0x02;
                }
                handler.sendMessageDelayed(msg, 1000);
                break;
        }

    }

    /**
     * 用户登出
     */

    private void ExitUserLogin() {
        OkGo.<String>post(Constant.LOGIN_OUT_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseLoginOutData(response.body());
            }
        });
    }

    private void parseLoginOutData(String body) {
        try {
            final JSONObject jsonObject = new JSONObject(body);
            final int code = jsonObject.getInt("code");
            if (code == 0) {
                LoginMsgHelper.exitLogin();
                asyncShowToast("退出登录成功");
                toActivity(MainActivity.class);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //自定义的清理对话框
    private static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);//得到加载view
        LinearLayout layout = v.findViewById(R.id.dialog_view);//加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = v.findViewById(R.id.dialog_img);
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        //加载动画
        final Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        //使用imageView显示动画
        spaceshipImage.startAnimation(animation);
        tipTextView.setText(msg);  //设置加载信息
        final Dialog loadingDialog = new Dialog(context);
        loadingDialog.setCancelable(true);  //不可以用返回键 取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); //设置布局
        return loadingDialog;
    }

}
