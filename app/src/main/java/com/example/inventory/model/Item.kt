package com.example.inventory.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
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
