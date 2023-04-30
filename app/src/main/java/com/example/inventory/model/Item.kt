package com.example.inventory.model

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.inventory.dataBase.TypeConverter
import com.google.android.material.tabs.TabLayout.TabGravity

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var price: Int,
    var brand: String,
    var quantity: Int,
    var image: Bitmap,
    var archived: Boolean
)
