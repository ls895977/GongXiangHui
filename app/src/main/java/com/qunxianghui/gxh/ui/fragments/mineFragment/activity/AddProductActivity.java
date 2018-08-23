package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.video2pic.FullyGridLayoutManager;
import com.qunxianghui.gxh.widget.video2pic.GridImageSelfAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddProductActivity extends BaseActivity {

    @BindView(R.id.tv_add_product_save)
    TextView mTvAddProductSave;
    @BindView(R.id.et_add_product_title)
    EditText mEtAddAProductTitle;
    @BindView(R.id.et_add_product__introduce)
    EditText mEtAddProductIntroduce;
    @BindView(R.id.rv)
    RecyclerView mRecyclerViewAddProductPic;
    @BindView(R.id.rl_add_product_edit)
    RelativeLayout rlAddProductEdit;

    private int themeId;
    private GridImageSelfAdapter mGridProductmageAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 3;
    private List<String> upLoadPics = new ArrayList<>();
    private String mTitle;
    private String mDescribe;
    private String[] mImage_arrays;
    private int mViewTag;
    private int mAboutusId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_cardadd_product;
    }

    @Override
    protected void initViews() {
        //获取传过来的值
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mViewTag = intent.getIntExtra("viewTag", 0);

        mAboutusId = intent.getIntExtra("aboutus_id", 0);
        mDescribe = intent.getStringExtra("describe");
        mImage_arrays = intent.getStringArrayExtra("image_array");
        //图片视频库的处理
        themeId = R.style.picture_default_style;
        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(AddProductActivity.this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerViewAddProductPic.setLayoutManager(fullyGridLayoutManager);
        mGridProductmageAdapter = new GridImageSelfAdapter(mContext, onAddPicClickListener);
        mGridProductmageAdapter.setList(selectList);
        mGridProductmageAdapter.setSelectMax(maxSelectNum);
        mRecyclerViewAddProductPic.setAdapter(mGridProductmageAdapter);

        mGridProductmageAdapter.setOnItemClickListener(new GridImageSelfAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(AddProductActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                    }
                }
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(AddProductActivity.this);
                } else {
                    Toast.makeText(AddProductActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void initData() {
        mEtAddAProductTitle.setText(mTitle);
        mEtAddProductIntroduce.setText(mDescribe);
        if (mViewTag == 1) {
            mTvAddProductSave.setVisibility(View.GONE);
            rlAddProductEdit.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.iv_add_product_back, R.id.tv_add_product_save, R.id.tv_add_product_delete, R.id.tv_add_product_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_product_back:
                finish();
                break;
            case R.id.tv_add_product_save:
                asyncShowToast("保存");
                SavaCompanyCardProductData();
                break;
            case R.id.tv_add_product_delete:
                deleteCompanyCardAdavance();
                break;
            case R.id.tv_add_product_complete:
                String mEditImageUrl = Utils.listToString(upLoadPics);
                String mProductTitle = mEtAddAProductTitle.getText().toString().trim();
                String mProductIntroduce = mEtAddProductIntroduce.getText().toString().trim();
                if (TextUtils.isEmpty(mEditImageUrl) || TextUtils.isEmpty(mProductTitle) || TextUtils.isEmpty(mProductIntroduce)) {
                    asyncShowToast("请检查一下,还有哪里没有填写");
                } else {
                    editCompanyCardAdavance(mProductTitle, mProductIntroduce, mEditImageUrl);
                }
                break;
        }
    }

    /*修改公司产品*/
    private void editCompanyCardAdavance(String mProductTitle, String mProductIntroduce, String mEditImageUrl) {
        OkGo.<String>post(Constant.ADD_COMPANY_CENTER_ADVANCE).
                params("title", mProductTitle).
                params("aboutus_id", mAboutusId).
                params("describe", mProductIntroduce).
                params("image", mEditImageUrl).
                params("datatype", 2).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                asyncShowToast("修改成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("修改企业产品失败了" + response.message());
                    }
                });
    }

    /*删除公司产品*/
    private void deleteCompanyCardAdavance() {
        OkGo.<String>post(Constant.DELETE_COMPANY_CENTER_ADVANCE).
                params("aboutus_id", mAboutusId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        asyncShowToast("删除成功");
                        finish();
                    } else {
                        asyncShowToast("删除失败" + response.message());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //保存数据
    private void SavaCompanyCardProductData() {
        String maddProductTitle = mEtAddAProductTitle.getText().toString().trim();
        String mEtAddAProductIntroduce = mEtAddProductIntroduce.getText().toString().trim();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = upLoadPics.size(); i < length; i++) {
            if (i != upLoadPics.size() - 1) {
                stringBuilder.append(upLoadPics.get(i) + ",");
            } else {
                stringBuilder.append(upLoadPics.get(i));
            }
        }
        if (TextUtils.isEmpty(maddProductTitle) || TextUtils.isEmpty(mEtAddAProductIntroduce)) {
            asyncShowToast("产品或产品介绍不能为空");
        } else {
            asyncShowToast("上传成功");
            OkGo.<String>post(Constant.ADD_COMPANY_CENTER_ADVANCE)
                    .params("title", maddProductTitle)
                    .params("describe", mEtAddAProductIntroduce)
                    .params("image", stringBuilder.toString())
                    .params("datatype", 2)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            parseAddProductData(response.body());
                        }
                    });
        }

    }

    /*解析发布的内容*/
    private void parseAddProductData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                asyncShowToast("上传成功");
                Logger.d("意见发布" + body.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    private GridImageSelfAdapter.onAddPicClickListener onAddPicClickListener = new GridImageSelfAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(AddProductActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(3)
                    .previewImage(true)// 是否可预览图片 true or false
                    .imageSpanCount(4)// 每行显示个数 int
                    .compress(true)// 是否压缩 true or false
                    .isDragFrame(true)// 是否可拖动裁剪框(固定)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    mGridProductmageAdapter.setList(selectList);
                    mGridProductmageAdapter.notifyDataSetChanged();
                    for (int i = 0, length = selectList.size(); i < length; i++) {
                        String path = selectList.get(i).getPath();
                        upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(path));

                    }
                    break;
            }
        }
    }

    /*上传图片*/
    private void upLoadPic(String urls) {
        OkGo.<String>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", urls)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if (uploadImage.code.equals("0")) {
                            upLoadPics.add(uploadImage.data.file);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }
}
