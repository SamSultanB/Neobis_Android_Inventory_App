package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.View


interface Presenter {

    fun getAllItems()

    fun addItem(item: Item)

    fun attachView(view: View)

}