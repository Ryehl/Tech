package com.wd.tech.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.GlideEngine;
import com.wd.tech.R;
import com.wd.tech.adapters.RecyUploadImagesAdap;
import com.wd.tech.diyview.BottomSelectImage;
import com.wd.tech.presenters.ActPublishPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * 发表社区，多图上传
 *
 * @author Xaoyv
 * data 2020年11月18日 08点51分
 */
public class PublishActivity extends BaseActivity<ActPublishPresenter> {

    //展示要上传的图片
    private RecyclerView recy;
    //输入的社区消息
    private EditText et_content;
    //按钮上传图片
    private Button btn_upload;
    //取消上传
    private TextView tv_exit;
    //适配器
    private RecyUploadImagesAdap adap;
    //要展示的图片的集合
    private ArrayList<String> images_list;
    private BottomSheetDialog bsd;

    @Override
    public void initView() {
        //find view by id
        recy = findViewById(R.id.publish_recy);
        et_content = findViewById(R.id.publish_content);
        btn_upload = findViewById(R.id.publish_btn_upload);
        tv_exit = findViewById(R.id.publish_tv_exit);
    }

    @Override
    public void initData() {
        initAdap();

        //set listener
        tv_exit.setOnClickListener(v -> {
            images_list = null;
            finish();
        });
        btn_upload.setOnClickListener(v -> {
            String content = et_content.getText().toString().trim();
            if (content.length() <= 0) {
                Toast.makeText(this, "请输入文字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (images_list == null)
                images_list = new ArrayList<>();
            pre.uploadImages(content, images_list);
        });
    }

    public void uploadImagesMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initAdap() {
        if (images_list == null)
            images_list = new ArrayList<>();
        //设置最大图片
        adap = new RecyUploadImagesAdap(9);
        recy.setLayoutManager(new GridLayoutManager(this, 3));
        recy.setAdapter(adap);
        adap.setListener(new RecyUploadImagesAdap.CallbackListener() {
            @Override
            public void add() {
                //弹框
                bsd = new BottomSheetDialog(PublishActivity.this);
                bsd.setCancelable(true);
                BottomSelectImage view = new BottomSelectImage(PublishActivity.this);
                bsd.setContentView(view);
                view.setListener(new BottomSelectImage.BottomDialogListener() {
                    @Override
                    public void camera() {
                        //打开相机
                        takingPictures(PublishActivity.this);
                    }

                    @Override
                    public void gallery() {
                        //打开图库
                        openGallery(9 - images_list.size());
                    }
                });
                bsd.show();
            }

            @Override
            public void delete(int position) {
                //删除
                images_list.remove(position);
                //重新设置集合
                adap.setImageList(images_list);
            }

            @Override
            public void item(int position, ArrayList<String> mList) {
                //查看
            }
        });
    }

    /**
     * 相机拍照
     */
    public static void takingPictures(AppCompatActivity activity) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.REQUEST_CAMERA);
    }

    /**
     * 打开图库
     *
     * @param maxSize 9
     */
    public void openGallery(int maxSize) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSize)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .imageEngine(GlideEngine.createGlideEngine())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isPreviewVideo(true)// 是否可预览视频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .isEnableCrop(true)// 是否裁剪 true or false
                .isGif(true)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                //.selectionData(images_list)// 是否传入已选图片 List<LocalMedia> list
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            showSelectPic(selectList);
        }
    }

    /**
     * 展示图片
     *
     * @param selectList 图片的集合
     */
    private void showSelectPic(List<LocalMedia> selectList) {
        for (int i = 0; i < selectList.size(); i++) {
            String path;
            //判断是否10.0以上
            if (Build.VERSION.SDK_INT >= 29) {
                path = selectList.get(i).getAndroidQToPath();
            } else {
                path = selectList.get(i).getPath();
            }
            images_list.add(path);
        }
        adap.setImageList(images_list);
        bsd.cancel();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_publish;
    }

    @Override
    public ActPublishPresenter initPresenter() {
        return new ActPublishPresenter();
    }
}
