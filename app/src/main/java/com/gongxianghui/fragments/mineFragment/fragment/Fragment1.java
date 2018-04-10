package com.gongxianghui.fragments.mineFragment.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;
import com.kyleduo.switchbutton.SwitchButton;

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
    private String[] AddressList = new String[]{"显示位置", "顶部广告", "底部广告", "中部广告"};
    private String[] addAttrList = new String[]{"点击属性", "属性1", "属性2", "属性3"};
    @BindView(R.id.rl_mine_addAddress)
    RelativeLayout rlMineAddAddress;
    @BindView(R.id.rl_mine_click_attr)
    RelativeLayout rlMineClickAttr;
    Unbinder unbinder;
    @BindView(R.id.switchButton)
    SwitchButton mSwitchButton;


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


    }

    @Override
    protected void initListeners() {
        rlMineAddAddress.setOnClickListener(this);
        rlMineClickAttr.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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
        }

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
}


