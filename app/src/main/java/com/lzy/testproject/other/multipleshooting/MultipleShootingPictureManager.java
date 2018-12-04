package com.lzy.testproject.other.multipleshooting;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.lzy.testproject.utils.ImageFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiZhiyu on 2018/12/3.
 */
public class MultipleShootingPictureManager {
    private int maxSize = 100;//压缩到maxSize之下，kb
    private boolean compress = true;//是否压缩
    private String outPath;
    private boolean isDelete = false;//压缩后是否删除照片源文件
    private Activity context;
    private OnListener onListener;
    private long startTime;
    private String orderBy = MediaStore.Images.Media.DISPLAY_NAME;
    ;
    private Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private DisposableObserver<String> disposableObserver;

    public MultipleShootingPictureManager(Activity context) {
        this.context = context;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            outPath = Environment.getExternalStorageDirectory().getPath();
        } else {
            outPath = context.getCacheDir().getPath();
        }
    }

    public void getPictureData(List<String> path) {
        if (path == null)
            return;
        disposableObserver = Observable.create((ObservableOnSubscribe<String>) e -> {
            Cursor cursor = context.getContentResolver().query(uri, null, null,
                    null, orderBy);
            if (null == cursor) {
                e.onComplete();
                return;
            }
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                ///storage/emulated/0/DCIM/Camera/IMG_20181203_110548.jpg
                if (string != null && string.length() > 30 && string.contains("_") && string.contains("IMG")) {
                    String substring = string.substring(string.length() - 19, string.length() - 4);
                    Pattern pattern = Pattern.compile("\\d{8}_\\d{6}");//20181203_110548(时间)
                    Matcher matcher = pattern.matcher(substring);
                    if (matcher.matches()) {
                        long time = new SimpleDateFormat("yyyyMMdd_HHmmss").parse(substring).getTime();
                        //判断是此次拍照
                        if (time > startTime && time < new Date().getTime()) {
                            String out = outPath + File.separator + substring + ".jpg";
                            if (compress) {
                                try {
                                    ImageFactory.compressAndGenImage(string, out, maxSize, isDelete);
                                    e.onNext(out);
                                } catch (Exception e1) {
                                    e.onComplete();
                                }
                            } else {
                                //不压缩直接返回
                                e.onNext(string);
                            }
                        }
                    }
                }
            }
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        path.add(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if (onListener != null) {
                            onListener.onResult(path);
                        }
                    }
                });
    }

    public void cancel() {
        if (disposableObserver != null && !disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
    }

    public void request(int requestCode) {
        startTime = new Date().getTime();
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        context.startActivityForResult(intent, requestCode);
    }

    public MultipleShootingPictureManager setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public MultipleShootingPictureManager setCompress(boolean compress) {
        this.compress = compress;
        return this;
    }

    public MultipleShootingPictureManager setOutPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public MultipleShootingPictureManager setDelete(boolean delete) {
        isDelete = delete;
        return this;
    }

    public MultipleShootingPictureManager setOnListener(OnListener onListener) {
        this.onListener = onListener;
        return this;
    }

    interface OnListener {
        void onResult(List<String> list);
    }
}
