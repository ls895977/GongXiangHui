package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
public class UpLoadVideoActivity extends BaseActivity {
    @BindView(R.id.bt_upload_video)
    Button btUploadVideo;
    @BindView(R.id.iv_video_image)
    ImageView ivVideoImage;
    private static final int REQ_GET_VIDEO = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_video;
    }
    @Override
    protected void initViews() {
    }
    @Override
    protected void initDatas() {
        btUploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                intent.putExtra(Intent.CATEGORY_OPENABLE, true);
                startActivityForResult(intent, REQ_GET_VIDEO);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initListeners() {
        super.initListeners();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_GET_VIDEO && resultCode == RESULT_OK) {
            String path = getImagePath(this, data.getData());
            if (!TextUtils.isEmpty(path)) {
                ivVideoImage.setImageBitmap(getVideoFirstFrame(path));
            }
        }
    }

    /**
     * 获取视频文件首帧
     *
     * @param filePath
     * @return
     */
    private Bitmap getVideoFirstFrame(String filePath) {
        Bitmap bitmap;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(filePath);
        bitmap = retriever.getFrameAtTime();
        retriever.release();
        return bitmap;
    }
    /**
     * 获取图片路径
     * @param context
     * @param uri
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getImagePath(Context context, Uri uri) {
        if (uri == null)
            return null;
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat)
            return getImagePathForNewSdk(context, uri);
        else
            return getImagePathForOldSdk(context, uri);
    }
    private String getImagePathForOldSdk(Context context, Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            String ImagePath = cursor.getString(columnIndex);
            cursor.close();
            return ImagePath;
        }
        return uri.getPath();
    }

    /**
     * 获取图片路径（新sdk）
     *
     * @param uri
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getImagePathForNewSdk(Context context, Uri uri) {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            final String docId = DocumentsContract.getDocumentId(uri);
            final String[] split = docId.split(":");
            Uri contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            final String selection = "_id=?";
            final String type = split[0];
            final String[] selectionArgs = new String[]{split[1]};
            Cursor cursor = null;

            if ("primary".equalsIgnoreCase(type))//小米兼容方案
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            try {
                cursor = context.getContentResolver().query(contentUri, new String[]{MediaStore.Images.Media.DATA}, selection, selectionArgs,
                        null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    return cursor.getString(index);
                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }
            return null;
        } else if (uri.toString().startsWith("content://")) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            int indexColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(indexColumn);
            cursor.close();
            return path;
        } else {
            String scheme = uri.getScheme();
            if (scheme.equals("file"))
                return uri.getPath();
        }
        return null;
    }
}
