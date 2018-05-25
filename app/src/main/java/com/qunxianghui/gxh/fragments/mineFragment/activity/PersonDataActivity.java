package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.REGutil;

import butterknife.BindView;

;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class PersonDataActivity extends BaseActivity implements View.OnClickListener {

    public static final String NICK = "nick";
    public static final String AVATAR = "avatar";
    public static final String MOBILE = "mobile";
    public static final String ADDRESS = "address";
    public static final String SEX = "sex";

    private String[] sexArray = new String[]{"男", "女"}; //性别选择

    @BindView(R.id.et_person_data_nickName) EditText etPersonDataNickName;
    @BindView(R.id.tv_person_data_sex) EditText mEtPersonDataSex;
    @BindView(R.id.et_person_data_phone) EditText etPersonDataPhone;
    @BindView(R.id.et_person_data_address) EditText etPersonDataAddress;
    @BindView(R.id.iv_person_data_back) ImageView ivPersonDataBack;
    @BindView(R.id.tv_person_data_save) TextView tvPersonDataSave;
    @BindView(R.id.iv_person_data_img) ImageView ivPersonDataImg;
    @BindView(R.id.rl_mineData_sex) RelativeLayout rlMineDataSex;

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
    protected void initDatas() {

        //设置回显
        setData();

        ivPersonDataBack.setOnClickListener(this);
        ivPersonDataImg.setOnClickListener(this);
        rlMineDataSex.setOnClickListener(this);
        mEtPersonDataSex.setOnClickListener(this);
        tvPersonDataSave.setOnClickListener(this);

    }

    /** ==================设置个人资料回显===================== */
    private void setData() {

        String nick = getIntent().getStringExtra(NICK);
        String avatar = getIntent().getStringExtra(AVATAR);
        String mobile = getIntent().getStringExtra(MOBILE);
        String address = getIntent().getStringExtra(ADDRESS);
        int sex = getIntent().getIntExtra(SEX, -1);

        if (!TextUtils.isEmpty(avatar)) {
            //头像
            GlideApp.with(this).load(avatar).
                    placeholder(R.mipmap.user_moren).
                    error(R.mipmap.user_moren).
                    circleCrop().
                    into(ivPersonDataImg);
        }

        if (!TextUtils.isEmpty(mobile)) {
            etPersonDataPhone.setText(String.valueOf(mobile));

        }
        if (!TextUtils.isEmpty(address)) {
            etPersonDataAddress.setText(String.valueOf(address));

        }
        if (!TextUtils.isEmpty(nick)) {
            etPersonDataNickName.setText(nick);
            etPersonDataNickName.setSelection(nick.length());
        }

        if (sex == 1) {
            mEtPersonDataSex.setText("男");
        } else if (sex == 1) {
            mEtPersonDataSex.setText("女");
        } else {
            mEtPersonDataSex.setText("");
        }

    }


    @Override
    public void onClick(View v) {

        final String mNiceName = etPersonDataNickName.getText().toString().trim();
        final String mPhone = etPersonDataPhone.getText().toString().trim();
        final String mAdress = etPersonDataAddress.getText().toString().trim();
        final String mSex = mEtPersonDataSex.getText().toString().trim();
        final String sex;
        if (mSex.equals("男")) {
            sex = "1";
        } else {
            sex = "0";
        }

        switch (v.getId()) {
            case R.id.iv_person_data_back:
                finish();
                break;
            case R.id.iv_person_data_img:
                openPhoto();

                break;
            case R.id.rl_mineData_sex:
                showSexDialog();
                break;
            case R.id.tv_person_data_sex:
                showSexDialog();
                break;
            case R.id.tv_person_data_save:
                if (TextUtils.isEmpty(mNiceName) && TextUtils.isEmpty(mPhone) && TextUtils.isEmpty(mSex) && TextUtils.isEmpty(mAdress)) {
                    Toast.makeText(mContext, "请在检查一下 是否还有没有写的", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!REGutil.checkCellphone(mPhone)) {
                    Toast.makeText(mContext, "手机格式错误了，请检查重试", Toast.LENGTH_SHORT).show();
                } else {

                    OkGo.<String>post(Constant.EDIT_PERSON_DATA).
                            params("nick", mNiceName).
                            params("sex", sex).
                            params("address", mAdress).
                            execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Logger.d("保存成功-->:" + response.body().toString());
                                    Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    Logger.e("保存失败->" + response.body().toString());

                                }
                            });
                }
                break;

        }

    }


    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {


            @Override
            public void onPickImage(Uri imageUri) {
            }

            //剪裁图片回调
            @Override
            public void onCropImage(Uri imageUri) {

                //                //头像
                GlideApp.with(PersonDataActivity.this).load(imageUri).
                        placeholder(R.mipmap.user_moren).
                        error(R.mipmap.user_moren).
                        circleCrop().
                        into(ivPersonDataImg);


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

