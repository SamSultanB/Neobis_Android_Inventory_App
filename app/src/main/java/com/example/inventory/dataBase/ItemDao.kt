package com.example.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.inventory.model.Item


@Dao
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM items WHERE archived=1")
    fun getAllItems(): LiveData<List<Item>>
}