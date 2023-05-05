package com.example.inventory.dataBase

import com.example.inventory.model.Item

class Repository(private val itemDao: ItemDao) {

    val items = itemDao.getAllItems()

    val archivedItems = itemDao.getAllArchived()

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.update(item)
    }

    suspend fun delete(item: Item){
        itemDao.delete(item)
    }

}