package com.example.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inventory.model.Item


@Dao
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items WHERE archived=0")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE archived=1")
    fun getAllArchived(): LiveData<List<Item>>
}