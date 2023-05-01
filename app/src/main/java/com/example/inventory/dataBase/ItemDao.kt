package com.example.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inventory.model.Item


@Dao
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>
}