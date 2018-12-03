package com.lzy.testproject.other.multipleshooting;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

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
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiZhiyu on 2018/12/3.
 */
public class MultipleShootingPictureManager {
    private int maxSize = 100;//压缩到maxSize之下，kb
    private boolean compress = true;//是否压缩
    private String outPath;
    private boolean isDelete = false;//是否删除照片源文件
    private Activity context;
    private OnListener onListener;
    private long startTime;
    private MultipleShootingPictureManager manager;

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
        final String orderBy = MediaStore.Images.Media.DISPLAY_NAME;
        final Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Observable.create((ObservableOnSubscribe<String>) e -> {
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
                            try {
                                ImageFactory.compressAndGenImage(string, out, 100, true);
                                e.onNext(out);
                            } catch (Exception e1) {
                                e.onComplete();
                            }

                        }
                    }
                }
            }
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        path.add(value);
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

    public MultipleShootingPictureManager setContext(Activity context) {
        this.context = context;
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
