package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    RelativeLayout rlMineDataSex;
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

    private String[] sexArray = new String[]{"男", "女"};
    private List<String> upLoadPics = new ArrayList<>();
    private ImagePicker imagePicker;
    public static final String NICK = "nick";
    public static final String AVATAR = "avatar";
    public static final String MOBILE = "mobile";
    public static final String ADDRESS = "address";
    public static final String SEX = "sex";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_DUTY = "duty";
    public static final String USER_INTRODUCTION = "self_introduction";
    public static final String USER_COMPANY = "company_name";

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
        String avatar = getIntent().getStringExtra(AVATAR);
        int sex = getIntent().getIntExtra(SEX, -1);
        if (!TextUtils.isEmpty(avatar)) {
            //头像
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.user_moren);
            options.error(R.mipmap.user_moren);
            options.circleCrop();
            options.centerCrop();
            Glide.with(mContext).load(avatar).apply(options).into(ivPersonDataImg);
        }
        etPersonDataPhone.setText(getIntent().getStringExtra(MOBILE));
        etPersonDataAdress.setText(getIntent().getStringExtra(ADDRESS));
        etPersonDataNickName.setText(getIntent().getStringExtra(NICK));
        etPersonDataUserName.setText(getIntent().getStringExtra(USER_NAME));

        etPersonDataIntroduce.setText(getIntent().getStringExtra(USER_INTRODUCTION));
        etPersonDataCompany.setText(getIntent().getStringExtra(USER_COMPANY));
        etPersonDataJob.setText(getIntent().getStringExtra(USER_DUTY));
        etPersonDataEmail.setText(getIntent().getStringExtra(USER_EMAIL));


        mEtPersonDataSex.setText(getIntent().getIntExtra(SEX, -1) == 0 ? "女" : (sex == 1 ? "男" : ""));
    }

    @OnClick({R.id.iv_person_data_back, R.id.iv_person_data_img, R.id.rl_mineData_sex, R.id.tv_person_data_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_person_data_back:
                finish();
                break;
            case R.id.iv_person_data_img:
                openPhoto();
                break;
            case R.id.rl_mineData_sex:
                showSexDialog();
                break;
            case R.id.tv_person_data_save:
                saveInfo(view);
                break;
        }
    }

    public void saveInfo(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        final String nickName = etPersonDataNickName.getText().toString().trim();
        final String mUserName = etPersonDataUserName.getText().toString().trim();
        final String mUserEmail = etPersonDataEmail.getText().toString().trim();
        final String mUserCompany = etPersonDataCompany.getText().toString().trim();
        final String mUserDuty = etPersonDataJob.getText().toString().trim();
        final String mUserAddress = etPersonDataAdress.getText().toString().trim();
        final String mUserIntroduce = etPersonDataIntroduce.getText().toString().trim();
        final String mMobile = etPersonDataPhone.getText().toString().trim();


        String mSex = mEtPersonDataSex.getText().toString().trim();
        final String sex = "女".equals(mSex) ? "0" : "1";
        if (TextUtils.isEmpty(nickName) && TextUtils.isEmpty(mSex) && TextUtils.isEmpty(mUserAddress)) {
            Toast.makeText(mContext, "请在检查一下 是否还有没有写的", Toast.LENGTH_SHORT).show();
            return;
        }
        String imageUrl = Utils.listToString(upLoadPics);
        OkGo.<String>post(Constant.EDIT_PERSON_DATA).
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
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 100) {
                                Toast.makeText(mContext, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } else if (code == 200) {
                                Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                asyncShowToast("保存失败");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("保存失败->" + response.body());
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

                //                //头像
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.user_moren);
                options.error(R.mipmap.user_moren);
                options.circleCrop();
                Glide.with(mContext).load(imageUri).apply(options).into(ivPersonDataImg);

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
        OkGo.<CommonResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
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

}

