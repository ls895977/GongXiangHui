package com.gongxianghui.fragments.mineFragment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class PersonDataActivity extends BaseActivity implements View.OnClickListener {

    private  String[] sexArray=new String[]{"男","女"}; //性别选择
    @BindView(R.id.et_person_data_nickName)
    EditText etPersonDataNickName;
    @BindView(R.id.et_person_data_sex)
    EditText etPersonDataSex;
    @BindView(R.id.et_person_data_phone)
    EditText etPersonDataPhone;
    @BindView(R.id.et_person_data_address)
    EditText etPersonDataAddress;
    @BindView(R.id.iv_person_data_back)
    ImageView ivPersonDataBack;
    @BindView(R.id.tv_person_data_save)
    TextView tvPersonDataSave;
    @BindView(R.id.iv_person_data_img)
    ImageView ivPersonDataImg;
    @BindView(R.id.rl_mineData_sex)
    RelativeLayout rlMineDataSex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {

        ivPersonDataBack.setOnClickListener(this);
        ivPersonDataImg.setOnClickListener(this);
        rlMineDataSex.setOnClickListener(this);
        etPersonDataSex.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_person_data_back:
                finish();
                break;
            case R.id.iv_person_data_img:

                break;
            case R.id.rl_mineData_sex:
                showSexDialog();

                break;
            case R.id.et_person_data_sex:
                showSexDialog();
                break;
        }

    }

    private void showSexDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//自定义对话框
        builder.setSingleChoiceItems(sexArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //witch是被选中的位置
                etPersonDataSex.setText(sexArray[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }


}

