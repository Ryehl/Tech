package com.wd.tech.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.enums.DetectFaceOrientPriority;
import com.arcsoft.face.enums.DetectMode;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.BitMapUtils;
import com.wd.mylibrary.utils.Nv21Utils;
import com.wd.tech.R;
import com.wd.tech.beans.RealmFaceInfoBean;
import com.wd.tech.diyview.MySurface;
import com.wd.tech.presenters.ActFaceRecognitionPresenter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * 人脸识别
 *
 * @author Xaoyv
 * data 2020年11月24日 08点06分
 */
public class FaceRecognitionActivity extends BaseActivity<ActFaceRecognitionPresenter> {

    private final String TAG = this.getClass().getName();
    private Button btn_pz;
    private FaceEngine faceEngine;
    private Camera mCamera;
    private MySurface ms;
    private List<FaceInfo> list = new ArrayList<>();
    private FaceFeature faceFeature;

    @Override
    public void initView() {
        //find view by id
        btn_pz = findViewById(R.id.facereco_btn_pz);
    }

    @Override
    public void initData() {
        initCamera();
        btn_pz.setOnClickListener(v -> {
            //拍照进行识别
            getCamera().takePicture(() -> {
            }, null, (data, camera) -> {
                //获取到的图像信息
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap == null) {
                    Toast.makeText(this, "获取图像失败，请重试", Toast.LENGTH_SHORT).show();
                    return;
                }
                //将要存储的路径
                String path = Environment.getExternalStorageDirectory() + "/DCIM/Camera/" + System.currentTimeMillis() + ".jpg";
                //存储文件
                BitMapUtils.saveImg2Sdcard(bitmap, Bitmap.CompressFormat.JPEG, path);

                //进行人脸识别
                try {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    width = width / 4 * 4;
                    height = height / 4 * 4;
                    byte[] nv21 = Nv21Utils.getNV21(width, height, bitmap);
                    int i = faceEngine.detectFaces(nv21, width, height, FaceEngine.CP_PAF_NV21, list);
                    if (i == 0) {
                        if (list.size() != 0) {
                            //图片识别成功
                            FaceInfo faceInfo = list.get(0);
                            faceFeature = new FaceFeature();
                            int code = faceEngine.extractFaceFeature(nv21, width, height, FaceEngine.CP_PAF_NV21, faceInfo, faceFeature);
                            if (code != 0) {
                                Toast.makeText(this, "识别失败，错误码:" + code, Toast.LENGTH_SHORT).show();
                            } else {
                                //存储到数据库
                                pre.getCouldFaceId();
                            }
                            //对比
//                            FaceSimilar faceSimilar = new FaceSimilar();
//                            faceEngine.compareFaceFeature(faceFeature, faceFeature, faceSimilar);
//                            Log.d(TAG, "onClick: " + faceSimilar.getScore());
                        }
                    } else {
                        Toast.makeText(this, "识别失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //重新加载相机
            //mCamera.release();
            mCamera.stopPreview();
            mCamera.startPreview();
        });
    }

    /**
     * 加载相机
     */
    private void initCamera() {
        faceEngine = new FaceEngine();
        int initCode = faceEngine.init(this,
                DetectMode.ASF_DETECT_MODE_VIDEO,
                DetectFaceOrientPriority.ASF_OP_ALL_OUT,
                32,
                1,
                FaceEngine.ASF_FACE_RECOGNITION | FaceEngine.ASF_FACE_DETECT | FaceEngine.ASF_AGE | FaceEngine.ASF_FACE3DANGLE | FaceEngine.ASF_GENDER | FaceEngine.ASF_LIVENESS);
        if (initCode != 0)
            Log.e(TAG, "init: " + initCode);
        else
            Log.e(TAG, "init: success");
        if (isCameraCanUse()) {
            mCamera = getCamera();
            ms = new MySurface(FaceRecognitionActivity.this, mCamera);
            FrameLayout preview = findViewById(R.id.facereco_frame_show);
            preview.addView(ms);
        } else {
            reqPer();
        }
    }

    private void reqPer() {
        requestPermissions(new String[]{
                Manifest.permission.CAMERA
        }, 0);
    }

    //打开前置摄像头
    private Camera getCamera() {
        if (mCamera == null)
            mCamera = Camera.open(1);
        return mCamera;
    }

    /**
     * 判断相机是否可用
     *
     * @return camera can use?
     */
    private boolean isCameraCanUse() {
        PackageManager pm = getPackageManager();
        return PackageManager.PERMISSION_GRANTED == pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_face_recognition;
    }

    @Override
    public ActFaceRecognitionPresenter initPresenter() {
        return new ActFaceRecognitionPresenter();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (isCameraCanUse())
            redo();
    }

    /**
     * 重新加载相机s
     */
    private void redo() {
        mCamera = getCamera();
        ms = new MySurface(FaceRecognitionActivity.this, mCamera);
        FrameLayout preview = findViewById(R.id.facereco_frame_show);
        preview.addView(ms);
    }

    /**
     * 保存到服务器
     *
     * @param faceId face id
     */
    public void save2realm(String faceId) {
        RealmFaceInfoBean bean = new RealmFaceInfoBean();
        Realm realm = Realm.getDefaultInstance();
        //为bean赋值
        bean.id = realm.where(RealmFaceInfoBean.class).findAll().size();
        bean.faceId = faceId;
        bean.faceInfo = faceFeature.getFeatureData();
        //执行事务
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bean);
        realm.commitTransaction();

        finish();
        Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCamera != null)
            mCamera.stopPreview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera != null)
            mCamera.startPreview();
    }
}
