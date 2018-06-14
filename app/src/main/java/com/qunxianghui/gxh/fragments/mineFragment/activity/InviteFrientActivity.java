package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
import com.qunxianghui.gxh.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();

    @BindView(R.id.bt_invite_click)
    Button btInviteClick;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        btInviteClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //条件选择器
              new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                  @Override
                  public void onOptionsSelect(int options1, int options2, int options3, View v) {

                  }
              });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



