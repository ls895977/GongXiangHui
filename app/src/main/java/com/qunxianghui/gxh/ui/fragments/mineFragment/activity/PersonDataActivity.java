package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.bean.mine.UserInfo;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.UserUtil;
import com.qunxianghui.gxh.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
public class PersonDataActivity extends BaseActivity {

    @BindView(R.id.et_person_data_nickName)
    EditText etPersonDataNickName;
    @BindView(R.id.tv_person_data_sex)
    TextView mEtPersonDataSex;
    @BindView(R.id.tv_person_data_phone)
    TextView etPersonDataPhone;
    @BindView(R.id.iv_person_data_back)
    ImageView ivPersonDataBack;
    @BindView(R.id.tv_person_data_save)
    TextView tvPersonDataSave;
    @BindView(R.id.iv_person_data_img)
    ImageView ivPersonDataImg;
    @BindView(R.id.rl_mineData_sex)
    LinearLayout rlMineDataSex;
    @BindView(R.id.et_person_data_username)
    EditText etPersonDataUserName;
    @BindView(R.id.et_person_data_email)
    EditText etPersonDataEmail;
    @BindView(R.id.et_person_data_company)
    EditText etPersonDataCompany;
    @BindView(R.id.et_person_data_job)
    EditText etPersonDataJob;
    @BindView(R.id.et_person_data_adress)
    EditText etPersonDataAdress;
    @BindView(R.id.et_person_data_introduce)
    EditText etPersonDataIntroduce;
    @BindView(R.id.iv_persondata_nick_delete)
    ImageView ivPersondataNickDelete;

    private String[] sexArray = new String[]{"男", "女"};
    private List<String> upLoadPics = new ArrayList<>();
    private ImagePicker imagePicker;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initViews() {
        imagePicker = new ImagePicker();
        imagePicker.setCropImage(true);
    }

    @Override
    protected void initData() {
        UserInfo.DataBean userInfo = (UserInfo.DataBean) getIntent().getSerializableExtra("userinfo");
        if (userInfo == null) {
            return;
        }
        String avatar = userInfo.avatar;
        if (!TextUtils.isEmpty(avatar)) {
            Glide.with(mContext).load(avatar).apply(new RequestOptions().placeholder(R.mipmap.user_moren)
                    .error(R.mipmap.user_moren).circleCrop().centerCrop()).into(ivPersonDataImg);
        }
        etPersonDataPhone.setText(userInfo.mobile);
        etPersonDataAdress.setText(userInfo.address);
        etPersonDataNickName.setText(userInfo.nick);
        etPersonDataUserName.setText(userInfo.username);
        etPersonDataIntroduce.setText(userInfo.self_introduction);
        etPersonDataCompany.setText(userInfo.company_name);
        etPersonDataJob.setText(userInfo.duty);
        etPersonDataEmail.setText(userInfo.email);
        mEtPersonDataSex.setText(userInfo.sex == 0 ? "女" : (userInfo.sex == 1 ? "男" : ""));
    }

    @OnClick({R.id.iv_person_data_back, R.id.rl_mineData_sex, R.id.tv_person_data_save, R.id.ll_person_data_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_person_data_back:
                finish();
                break;
            case R.id.rl_mineData_sex:
                showSexDialog();
                break;
            case R.id.tv_person_data_save:
                saveInfo(view);
                break;
            case R.id.ll_person_data_img:
                openPhoto();
                break;
        }
    }

    @Override
    protected void initListeners() {
        etPersonDataIntroduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int len = charSequence.length();
                if (len > 20) {
                    etPersonDataIntroduce.setText(charSequence.toString().substring(0, 20)); //设置EditText只显示前面6位字符
                    etPersonDataIntroduce.setSelection(20);//让光标移至末端
                    asyncShowToast("个人介绍不能大于20个字符");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (etPersonDataUserName.isClickable()&&etPersonDataNickName.getText().toString().length()>0) {
            ivPersondataNickDelete.setVisibility(View.VISIBLE);
        }
    }

    public void saveInfo(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        final String nickName = etPersonDataNickName.getText().toString().trim();
        String mUserName = etPersonDataUserName.getText().toString().trim();
        String mUserEmail = etPersonDataEmail.getText().toString().trim();
        String mUserCompany = etPersonDataCompany.getText().toString().trim();
        String mUserDuty = etPersonDataJob.getText().toString().trim();
        String mUserAddress = etPersonDataAdress.getText().toString().trim();
        String mUserIntroduce = etPersonDataIntroduce.getText().toString().trim();
        String mMobile = etPersonDataPhone.getText().toString().trim();
        String mSex = mEtPersonDataSex.getText().toString().trim();

        String sex = "女".equals(mSex) ? "0" : "1";
        if (TextUtils.isEmpty(nickName) && TextUtils.isEmpty(mSex) && TextUtils.isEmpty(mUserAddress)) {
            Toast.makeText(mContext, "请在检查一下 是否还有没有写的", Toast.LENGTH_SHORT).show();
            return;
        }
        String imageUrl = Utils.listToString(upLoadPics);
        OkGo.<CommonBean>post(Constant.EDIT_PERSON_DATA).
                params("nick", nickName).
                params("sex", sex).
                params("address", mUserAddress).
                params("avatar", imageUrl).
                params("username", mUserName).
                params("email", mUserEmail).
                params("company_name", mUserCompany).
                params("duty", mUserDuty).
                params("mobile", mMobile).
                params("self_introduction", mUserIntroduce).
                execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 100) {
                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_SHORT).show();
                        } else if (code == 200) {
                            Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                            //用户昵称被修改了
                            UserUtil.getInstance().mNick = nickName;
                            setResult(0x0077);
                            finish();
                        } else {
                            asyncShowToast("保存失败" + response.body().message);
                        }
                    }
                });
    }

    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {
            }

            //剪裁图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                final String url = String.valueOf(imageUri).replace("file://", "");
                upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(url));
                Glide.with(mContext).load(imageUri).apply(new RequestOptions().placeholder(R.mipmap.user_moren)
                        .error(R.mipmap.user_moren).circleCrop()).into(ivPersonDataImg);
            }

            //自定义剪裁
            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(150, 150)
                        // 宽高比
                        .setAspectRatio(1, 1);
            }

            //用户拒绝授权回调
            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
            }
        });
    }

    private void upLoadPic(String urls) {
        OkGo.<CommonResponse<ImageBean>>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", urls)
                .execute(new DialogCallback<CommonResponse<ImageBean>>(this) {
                    @Override
                    public void onSuccess(Response<CommonResponse<ImageBean>> response) {
                        if (response.body().code == 0) {
                            upLoadPics.add(response.body().data.getFile());
                            Toast.makeText(mContext, "上传图片成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showSexDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//自定义对话框
        builder.setSingleChoiceItems(sexArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //witch是被选中的位置
                mEtPersonDataSex.setText(sexArray[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imagePicker.onActivityResult(PersonDataActivity.this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

