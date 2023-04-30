package com.example.inventory.dataBase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class TypeConverter {

    @androidx.room.TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray{
        val output = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
        return output.toByteArray()
    }

    @androidx.room.TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap{
        return BitmapFactory.decodeByteArray(byteArray, 0,  byteArray.size)
    }

}