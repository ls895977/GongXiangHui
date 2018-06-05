package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.kyleduo.switchbutton.SwitchButton;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_mine_addAddress)
    TextView tvMineAddAddress;
    @BindView(R.id.tv_mine_click_attr)
    TextView tvMineClickAttr;
    @BindView(R.id.iv_mine_addFragment1BigAdver)
    PhotoView ivMineAddFragment1BigAdver;
    private String[] AddressList = new String[]{"显示位置", "顶部广告", "底部广告", "中部广告"};
    private String[] addAttrList = new String[]{"点击属性", "属性1", "属性2", "属性3"};
    @BindView(R.id.rl_mine_addAddress)
    RelativeLayout rlMineAddAddress;
    @BindView(R.id.rl_mine_click_attr)
    RelativeLayout rlMineClickAttr;
    Unbinder unbinder;
    @BindView(R.id.switchButton)
    SwitchButton mSwitchButton;
    private ImagePicker imagePicker;


    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise1;
    }

    @Override
    public void initDatas() {
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String s;
                if (b) {
                    s = "选中";

                } else {
                    s = "未选中";
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initViews(View view) {
        imagePicker = new ImagePicker();
        imagePicker.setCropImage(true);
    }

    @Override
    protected void initListeners() {
        rlMineAddAddress.setOnClickListener(this);
        rlMineClickAttr.setOnClickListener(this);
        ivMineAddFragment1BigAdver.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_mine_addAddress:
                Toast.makeText(mActivity, "添加位置", Toast.LENGTH_SHORT).show();
                showAddAddressDialog();

                break;
            case R.id.rl_mine_click_attr:
                Toast.makeText(mActivity, "添加属性", Toast.LENGTH_SHORT).show();
                showAddClickAttr();
                break;
            case R.id.iv_mine_addFragment1BigAdver:
                openPhoto();

                break;
        }

    }

    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {
                ivMineAddFragment1BigAdver.setImageURI(imageUri);
                ivMineAddFragment1BigAdver.enable();


//        Glide.with(this)
//                .load(gif)
//                .crossFade()
//                .placeholder(R.mipmap.bbb)
//                .into(((PhotoView) findViewById(R.id.img1)));
            }

            //剪裁图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                ivMineAddFragment1BigAdver.setImageURI(imageUri);
                //      使用Glide加载的gif图片同样支持缩放功能
//        Glide.with(this)
//                .load(gif)
//                .crossFade()
//                .placeholder(R.mipmap.bbb)
//                .into(((PhotoView) findViewById(R.id.img1)));
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
                        .setCropShape(CropImageView.CropShape
                                .RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(DisplayUtil.dip2px(mActivity,1500), DisplayUtil.dip2px(mActivity,732)  )
                        // 宽高比
                        .setAspectRatio(2, 1);
            }

            //用户拒绝授权回调

            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
            }
        });

    }

    private void showAddClickAttr() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setSingleChoiceItems(addAttrList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //wich是被选中的
                tvMineClickAttr.setText(addAttrList[which]);
                dialog.dismiss();

            }
        });
        builder.show();
    }

    private void showAddAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setSingleChoiceItems(AddressList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //wich是被选中的
                tvMineAddAddress.setText(AddressList[which]);
                dialog.dismiss();

            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }
}


