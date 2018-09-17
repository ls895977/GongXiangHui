package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePicker2Adapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_PREVIEW;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;

public class QuestonPostActivity extends BaseActivity implements ImagePicker2Adapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    @BindView(R.id.tv_question_exit)
    TextView tvQuestionExit;
    @BindView(R.id.tv_question_warn_text_error)
    TextView tvQuestionWarnTextError;
    @BindView(R.id.et_setting_questiontitle)
    EditText etSettingQuestiontitle;
    @BindView(R.id.rv_setting_question)
    RecyclerView rvSettingQuestion;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_setting_quest_commit)
    TextView tvSettingQuestCommit;
    @BindView(R.id.tv_question_post_back)
    ImageView tvQuestionPostBack;

    private ArrayList<ImageItem> mImages; //当前选择的所有图片
    private ImagePicker2Adapter mAdapter;
    private Dialog mSelectPhoto;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_post;
    }

    @Override
    protected void initViews() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(3);
        imagePicker.setMultiMode(true);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(500);
        imagePicker.setOutPutY(500);

        mImages = new ArrayList<>();
        mAdapter = new ImagePicker2Adapter(this, mImages, 3);
        mAdapter.setOnItemClickListener(this);
        rvSettingQuestion.setAdapter(mAdapter);

        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent = new Intent(QuestonPostActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent1 = new Intent(QuestonPostActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    @Override
    protected void initListeners() {
        tvQuestionPostBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                mSelectPhoto.show();
                break;
            default:
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_question_post_back:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<ImageItem> images;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    mImages.addAll(images);
                    mAdapter.setImages(mImages);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    mImages.clear();
                    mImages.addAll(images);
                    mAdapter.setImages(mImages);
                }
            }
        }
    }
}
