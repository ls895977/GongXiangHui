package com.gongxianghui.fragments.mineFragment.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.bean.CardBean;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
;
/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class PersonDataActivity extends BaseActivity implements View.OnClickListener {
    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "gxh" + File.separator + "icon";
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private ArrayList<ArrayList<String>> options1Items = new ArrayList<>();
    private String[] type = {"A1", "A2", "A3", "B1", "B2", "C1", "C2"};

    private OptionsPickerView pvCustomOptions;
    private File cropfile;


    private ArrayList<CardBean> cardItem = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(mContext, "头像上传成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(mContext, "头像上传失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
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



    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initViews() {
//        getCardData();
//        initCustomOptionPicker();

    }

    @Override
    protected void initDatas() {

        ivPersonDataBack.setOnClickListener(this);
        ivPersonDataImg.setOnClickListener(this);

    }


//    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
//        /**
//         * @description
//         *
//         * 注意事项：
//         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
//         * 具体可参考demo 里面的两个自定义layout布局。
//         */
//
//        new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = cardItem.get(options1).getPickerViewText();
//                etPersonDataSex.setText(tx);
//
//            }
//        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
//            @Override
//            public void customLayout(View v) {
//                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//                ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
//                tvSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pvCustomOptions.returnData();
//                        pvCustomOptions.dismiss();
//                    }
//                });
//
//                ivCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pvCustomOptions.dismiss();
//
//                    }
//                });
//            }
//
//
//        })
//                .setOutSideCancelable(false)
//                .isDialog(true)
//                .build();
//        pvCustomOptions.setPicker(cardItem); //添加数据
//
//    }
//    private void getCardData() {
//        for (int i = 0; i < type.length; i++) {
//            cardItem.add(new CardBean(i, type[i]));
//        }
//    }

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
                DialogtoUpPic();
                break;
                case R.id.et_person_data_sex:
                    pvCustomOptions.show();

                    break;
        }

    }


    private void DialogtoUpPic() {
        new AlertDialog.Builder(mContext)
                .setTitle("选择图片")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        Log.e("file", tempFile.toString());
                        startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    }
                })
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                    }
                }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case PHOTO_REQUEST_TAKEPHOTO:
                if(data!=null){
                    startPhotoZoom(Uri.fromFile(tempFile), 150);
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 150);
                break;
            case PHOTO_REQUEST_CUT:
                Log.e("zoom", "begin2");
                if (data != null)
                    setPicToView(data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPicToView(Intent data) {

    }

    private void startPhotoZoom(Uri uri, int i) {
        Log.e("zoom", "begin");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        cropfile = new File(file.getPath(), System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropfile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        Log.e("zoom", "begin1");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);

    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}

