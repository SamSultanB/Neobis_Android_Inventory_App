package com.example.inventory.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.inventory.dataBase.TypeConverter
import kotlinx.parcelize.Parcelize

@Entity(tableName = "items")
@androidx.room.TypeConverters(TypeConverter::class)
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var price: Double,
    var brand: String,
    var quantity: Int,
    var image: Bitmap,
    var archived: Boolean
) : Parcelable
