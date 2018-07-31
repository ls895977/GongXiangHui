package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoUploadActivity extends BaseActivity {

    @BindView(R.id.image_UpdataVideo_Icon)
    ImageView mImageUpdataVideoIcon;
    @BindView(R.id.tv_video_type_choice)
    TextView mTvVideoTypeChoice;
    @BindView(R.id.edit_UpdataVideo_title)
    EditText mEditUpdataVideoTitle;
    @BindView(R.id.edit_UpdataVideo_Content)
    EditText mEditUpdataVideoContent;

    private String mVideoPath;
    private OptionsPickerView pvCustomOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_upload;
    }

    @Override
    protected void initViews() {
        mVideoPath = getIntent().getStringExtra("videoPath");
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(R.mipmap.default_img)
                                .placeholder(R.mipmap.default_img))
                .load(mVideoPath)
                .into(mImageUpdataVideoIcon);
    }

    @OnClick({R.id.tv_UpdataVideo_UpLoad, R.id.tv_UpdataVideo_Cancel, R.id.tv_video_type_choice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_UpdataVideo_UpLoad:

                break;
            case R.id.tv_UpdataVideo_Cancel:
                finish();
                break;
            case R.id.tv_video_type_choice:

                break;
        }
    }


//    private void videoSelect() {
//        pvCustomOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = cardItem.get(options1).getPickerViewText();
//                btUploadSelect.setText(tx);
//
//            }
//        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
//            @Override
//            public void customLayout(View v) {
//                TextView tvSubmit = v.findViewById(R.id.tv_finish);
//                TextView tvAdd = v.findViewById(R.id.tv_add);
//                ImageView ivCancel = v.findViewById(R.id.iv_cancel);
//
//                tvSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pvCustomOptions.returnData();
//                        pvCustomOptions.dismiss();
//                    }
//                });
//                tvAdd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getCardData();
//                        pvCustomOptions.setPicker(cardItem);
//                    }
//                });
//                ivCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pvCustomOptions.dismiss();
//                    }
//                });
//            }
//        }).isDialog(true)
//                .build();
//        pvCustomOptions.show();
//        pvCustomOptions.setPicker(cardItem);//添加数据
//    }
//
//    private void getCardData() {
//        for (int i = 0; i < 5; i++) {
//            cardItem.add(new TextSelectBean(i, "No.ABC12345 " + i));
//        }
//        for (int i = 0; i < cardItem.size(); i++) {
//            if (cardItem.get(i).getItemText().length() > 6) {
//                String str_item = cardItem.get(i).getItemText().substring(0, 6) + "...";
//                cardItem.get(i).setItemText(str_item);
//            }
//        }
//    }
}
