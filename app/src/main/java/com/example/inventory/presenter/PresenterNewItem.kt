package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.ViewNewItem

interface PresenterNewItem {

    fun attachView(view: ViewNewItem)

    fun addItem(item: Item)

}