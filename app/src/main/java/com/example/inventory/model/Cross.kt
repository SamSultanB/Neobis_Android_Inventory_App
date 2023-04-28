package com.example.inventory.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
data class Cross(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var price: Int,
    var brand: String,
    var quantity: Int,
    var archived: Boolean
)
