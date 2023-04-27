package com.example.inventory

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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
