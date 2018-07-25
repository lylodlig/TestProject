package com.lzy.testproject.ui.bigpicture

import android.app.Activity
import android.os.Bundle
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_big.*


//加载大图片
class BigActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big)

//        try {
//            val inputStream = assets.open("test.jpg")
//
//            //获得图片的宽、高
//            val tmpOptions = BitmapFactory.Options()
//            tmpOptions.inJustDecodeBounds = true
//            BitmapFactory.decodeStream(inputStream, null, tmpOptions)
//            val width = tmpOptions.outWidth
//            val height = tmpOptions.outHeight
//
//            //设置显示图片的中心区域
//            val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
//            val options = BitmapFactory.Options()
//            options.inPreferredConfig = Bitmap.Config.RGB_565
//            val bitmap = bitmapRegionDecoder.decodeRegion(Rect(100,  100, width / 2 + 100, height / 2 + 100), options)
//            iv1.setImageBitmap(bitmap)
//
//
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }


        val inputStream = assets.open("tes1.png")
        iv1.setInputStream(inputStream)
    }
}
