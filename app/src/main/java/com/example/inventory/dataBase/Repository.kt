package com.example.inventory.dataBase

import androidx.lifecycle.LiveData
import com.example.inventory.model.Item

class Repository(private val itemDao: ItemDao) {

    val items = itemDao.getAllItems()

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

//    fun getAllItems(): LiveData<List<Item>>{
//        return itemDao.getAllItems()
//    }

}