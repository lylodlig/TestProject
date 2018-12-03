package com.lzy.testproject.other.multipleshooting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.TimeUtils;
import com.leeiidesu.permission.PermissionHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.lzy.testproject.R;
import com.lzy.testproject.utils.ImageFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

//连续拍照
public class MultipleShootingActivity extends AppCompatActivity {

    private MultipleShootingPictureManager pictureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_shooting);
        PermissionHelper.with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).request();
    }

    public void bt(View view) {
        pictureManager = new MultipleShootingPictureManager(this);
        pictureManager.setCompress(true)
                .setDelete(true)
                .setOnListener(list -> {
                    for (int i = 0; i < list.size(); i++) {
                        Log.i("lzy", "bt: " + list.get(i));
                    }
                })
                .request(123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            List<String> list = new ArrayList<>();
            pictureManager.getPictureData(list);
        }

    }
}
