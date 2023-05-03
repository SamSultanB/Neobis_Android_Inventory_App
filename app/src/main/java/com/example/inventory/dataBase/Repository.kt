package com.example.inventory.dataBase

import com.example.inventory.model.Item

class Repository(private val itemDao: ItemDao) {

    val items = itemDao.getAllItems()

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.update(item)
    }

}