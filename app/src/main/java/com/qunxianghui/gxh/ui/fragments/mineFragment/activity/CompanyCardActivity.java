package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.CompanyCardBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONObject;

import butterknife.BindView;

public class CompanyCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_companycard_back)
    ImageView mIvCompanycardBack;
    @BindView(R.id.iv_companycard_share)
    ImageView mIvCompanycardShare;
    @BindView(R.id.ll_companycard_diamond)
    LinearLayout mLlCompanycardDiamond;
    @BindView(R.id.rl_companycard_center_advance)
    RelativeLayout mRlCompanycardCenterAdvance;
    @BindView(R.id.ll_companycard_goodselect)
    LinearLayout mLlCompanycardGoodselect;
    @BindView(R.id.rl_companycard_company_product)
    RelativeLayout mRlCompanycardCompanyProduct;
    @BindView(R.id.tv_company_card_name)
    TextView mTvCompanyCardName;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.iv_companycard_editlocation)
    ImageView ivCompanycardEditlocation;
    @BindView(R.id.rl_mine_person_data)
    LinearLayout mLlMinePersonData;
    @BindView(R.id.tv_company_mobile)
    TextView tvCompanyMobile;
    @BindView(R.id.tv_company_card_username)
    TextView mTvCompanyCardUsername;
    @BindView(R.id.tv_company_email)
    TextView tvCompanyEmail;
    @BindView(R.id.tv_company_adress)
    TextView tvCompanyAdress;
    @BindView(R.id.tv_company_duty)
    TextView tvCompanyDuty;
    @BindView(R.id.rl_company_card_adress_edit)
    RelativeLayout rlCompanyCardAdressEdit;

    private Dialog mDialog;
    private UMWeb mWeb;
    private UMShareListener umShareListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_card;
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Constant.MINE_COMPANY_CARD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseCompanyCardData(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Logger.e("获取失败了" + response.body().toString());
            }
        });

    }

    private void parseCompanyCardData(String body) {
        CompanyCardBean companyCardBean = GsonUtils.jsonFromJson(body, CompanyCardBean.class);
        int code = companyCardBean.getCode();
        if (code == 200) {
            CompanyCardBean.DataBean data = companyCardBean.getData();
            tvCompanyMobile.setText(data.getMobile());
            mTvCompanyCardName.setText(data.getCompany_name());
            mTvCompanyCardUsername.setText(data.getUsername());
            tvCompanyEmail.setText(data.getEmail());
            tvCompanyAdress.setText(data.getAddress());
            tvCompanyDuty.setText(data.getDuty());
            Glide.with(mContext).load(data.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.default_img)
                    .error(R.mipmap.default_img).centerCrop().circleCrop()).into(mIvHead);
        }
    }

    @Override
    protected void initListeners() {
        mIvCompanycardBack.setOnClickListener(this);
        mIvCompanycardShare.setOnClickListener(this);
        mRlCompanycardCenterAdvance.setOnClickListener(this);
        mRlCompanycardCompanyProduct.setOnClickListener(this);
        rlCompanyCardAdressEdit.setOnClickListener(this);
        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(CompanyCardActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(CompanyCardActivity.this, platform + " 分享失败啦" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(CompanyCardActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_companycard_back:
                finish();
                break;
            case R.id.rl_companycard_company_product:
                toActivity(ComPanyProductActivity.class);
                break;
            case R.id.iv_companycard_share:
                requestCompanyCardInfo();
                break;
            case R.id.iv_companycard_editlocation:
                asyncShowToast("点击了编辑地址");
                break;
            case R.id.rl_companycard_center_advance:
                toActivity(ComPanyAdvanceActivity.class);
                break;
            case R.id.rl_company_card_adress_edit:
                Intent intent = getIntent();
                intent.setClass(CompanyCardActivity.this, PersonDataActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*分享我的企业名片*/
    private void requestCompanyCardInfo() {
        OkGo.<String>post(Constant.SHARE_COMPANY_CARD_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                JSONObject mCompanyCardData = jsonObject.getJSONObject("data");
                                String avatar = mCompanyCardData.getString("avatar");
                                String title = mCompanyCardData.getString("title");
                                String content = mCompanyCardData.getString("content");
                                String url = mCompanyCardData.getString("url");
                                shareCompanyCardInfo(avatar, title, content, url);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /*调用三方接口分享出去*/
    private void shareCompanyCardInfo(String avatar, String title, String content, final String url) {
        //以下代码是分享示例代码
        UMImage image = new UMImage(this, avatar);//分享图标
        //切记切记 这里分享的链接必须是http开头
        mWeb = new UMWeb(url);
        mWeb.setTitle(title);//标题
        mWeb.setThumb(image);  //缩略图
        mWeb.setDescription(content);//描述
        View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self, null);
        RelativeLayout rl_share_wx = view.findViewById(R.id.rl_share_wx);
        RelativeLayout rl_share_wxfriend = view.findViewById(R.id.rl_share_wxfriend);
        RelativeLayout rl_share_qq = view.findViewById(R.id.rl_share_qq);
        RelativeLayout rl_share_qqzone = view.findViewById(R.id.rl_share_qqzone);
        RelativeLayout rl_share_sina = view.findViewById(R.id.rl_share_sina);
        RelativeLayout rl_share_link = view.findViewById(R.id.rl_share_link);
        TextView share_cancel_btn = view.findViewById(R.id.share_cancel_btn);
        // 设置style 控制默认dialog带来的边距问题
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_share_wx:
                        new ShareAction(CompanyCardActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(CompanyCardActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(CompanyCardActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(CompanyCardActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(CompanyCardActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_link:
                        ClipContent(url);
                        break;
                    case R.id.share_cancel_btn:
                        mDialog.dismiss();
                        break;
                }
            }
        };
        rl_share_wx.setOnClickListener(listener);
        rl_share_wxfriend.setOnClickListener(listener);
        rl_share_qq.setOnClickListener(listener);
        rl_share_qqzone.setOnClickListener(listener);
        rl_share_sina.setOnClickListener(listener);
        rl_share_link.setOnClickListener(listener);
        share_cancel_btn.setOnClickListener(listener);

        //获取当前activity所在的窗体
        final Window dialogWindow = mDialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        mDialog.show();

    }

    private void ClipContent(String url) {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(url));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
    }
}
