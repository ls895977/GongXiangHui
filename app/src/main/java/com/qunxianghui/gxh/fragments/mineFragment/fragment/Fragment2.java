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

public class Fragment2 extends BaseFragment implements View.OnClickListener {



    private String[] AddressList = new String[]{"显示位置", "顶部广告", "底部广告", "中部广告"};

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise2;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initListeners() {

    }


    @Override
    protected void onLoadData() {

    }



    @Override
    public void onClick(View v) {


    }

    private void showAddress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setSingleChoiceItems(AddressList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();

            }
        });
        builder.show();

    }
}
