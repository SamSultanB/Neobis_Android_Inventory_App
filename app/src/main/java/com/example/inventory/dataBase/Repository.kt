package com.example.inventory.dataBase

import com.example.inventory.model.Item

class Repository(private val itemDao: ItemDao) {


    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

    suspend fun getAllItems(): List<Item>{
        return itemDao.getAllItems()
    }

}