package com.example.inventory.presenter

import com.example.inventory.model.Item


interface Presenter {

    fun getAllItems()

    fun addItem(item: Item)

}