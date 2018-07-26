package com.lzy.testproject.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by LiZhiyu on 2018/7/25.
 */
public class Utils {

    /**
     * 把布局保存为图片
     */
    public static void saveViewAsBitmap(View mContainer, Context context) {
        mContainer.setDrawingCacheEnabled(true);
        mContainer.buildDrawingCache();
        Bitmap bitmap = mContainer.getDrawingCache();
        File file = Utils.saveBitmap(context, bitmap);
        mContainer.destroyDrawingCache();

        //通知更新相册，显示图片到相册中
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
    }

    /**
     * 保存图片到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static File saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = "/sdcard";
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + "/pic/";
        }
        try {
            filePic = new File(savePath + UUID.randomUUID() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePic;
    }
}
