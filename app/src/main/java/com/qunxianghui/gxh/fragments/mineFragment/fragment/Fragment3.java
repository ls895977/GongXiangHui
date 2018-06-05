package com.qunxianghui.gxh.fragments.mineFragment.fragment;


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

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment3 extends BaseFragment implements View.OnClickListener {
    private String[] AddressList = new String[]{"显示位置", "顶部广告", "底部广告", "中部广告"};
    @BindView(R.id.tv_fragment3_showAddress)
    TextView tvFragment3ShowAddress;
    @BindView(R.id.rl_fragment3_showAddress)
    RelativeLayout rlFragment3ShowAddress;
    @BindView(R.id.switchButton_mine_fragment3)
    SwitchButton switchButtonMineFragment3;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise3;
    }

    @Override
    public void initDatas() {
        switchButtonMineFragment3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String s;
                if (isChecked) {
                    s = "选中";
                } else {
                    s = "未选中";
                }
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initListeners() {
        rlFragment3ShowAddress.setOnClickListener(this);


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
            case R.id.rl_fragment3_showAddress:
                showAddAddressDialog();
                break;
        }
    }

    private void showAddAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setSingleChoiceItems(AddressList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //wich是被选中的
                tvFragment3ShowAddress.setText(AddressList[which]);
                dialog.dismiss();

            }
        });
        builder.show();
    }
}
