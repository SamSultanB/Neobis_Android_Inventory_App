package com.example.inventory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.tabs.TabLayout.TabGravity

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var price: Int,
    var brand: String,
    var quantity: Int,
    var archived: Boolean
)
