package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.ViewMain


interface PresenterMain {

    fun getAllItems()

    fun searchItem(query: String)

    fun update(item: Item)

    fun delete(item: Item)

    fun attachView(viewMain: ViewMain)

}