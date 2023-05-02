package com.example.inventory.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.inventory.dataBase.TypeConverter

@Entity(tableName = "items")
@androidx.room.TypeConverters(TypeConverter::class)
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var price: Int,
    var brand: String,
    var quantity: Int,
//    var image: Bitmap,
    var archived: Boolean
)
