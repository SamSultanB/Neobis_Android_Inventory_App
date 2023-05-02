package com.example.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inventory.model.Item


@Dao
@androidx.room.TypeConverters(TypeConverter::class)
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<Item>>
}