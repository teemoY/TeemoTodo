package com.teemo.teemo.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
object ImageUtils {

    fun base64ToBitmap(base64: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val bitmapArray: ByteArray = Base64.decode(base64, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

}