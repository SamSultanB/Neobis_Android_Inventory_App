package com.example.inventory.view

import com.example.inventory.model.Item


interface View {

    fun showAllItems(items : List<Item>)

    fun showError(error: String)

}