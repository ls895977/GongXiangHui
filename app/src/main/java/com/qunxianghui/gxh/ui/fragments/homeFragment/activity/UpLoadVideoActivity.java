package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.common.TextSelectBean;

import java.util.ArrayList;

import butterknife.BindView;

public class UpLoadVideoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_video_thumb)
    ImageView ivVideoThumb;
    @BindView(R.id.ll_uploadvideo_back)
    LinearLayout llUploadvideoBack;
    @BindView(R.id.bt_upload_select)
    Button btUploadSelect;

    private ArrayList<TextSelectBean> cardItem = new ArrayList<>();
    private OptionsPickerView pvCustomOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_video;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        ArrayList<Parcelable> videodata = intent.getParcelableArrayListExtra("videodata");
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        llUploadvideoBack.setOnClickListener(this);
        btUploadSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_uploadvideo_back:
                finish();
                break;
            case R.id.bt_upload_select:
                videoSelect();
                break;
        }
    }

    private void videoSelect() {
        pvCustomOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                btUploadSelect.setText(tx);

            }
        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.returnData();
                        pvCustomOptions.dismiss();
                    }
                });
                tvAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCardData();
                        pvCustomOptions.setPicker(cardItem);
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.dismiss();
                    }
                });
            }
        }).isDialog(true)
                .build();
        pvCustomOptions.show();
        pvCustomOptions.setPicker(cardItem);//添加数据
    }
    private void getCardData() {
        for (int i = 0; i < 5; i++) {
            cardItem.add(new TextSelectBean (i, "No.ABC12345 " + i));
        }
        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getItemText().length() > 6) {
                String str_item = cardItem.get(i).getItemText().substring(0, 6) + "...";
                cardItem.get(i).setItemText(str_item);
            }
        }
    }
}
