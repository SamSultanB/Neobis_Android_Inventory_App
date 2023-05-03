package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.ViewDetails

interface PresenterDetails {

    fun attachView(viewDetails: ViewDetails)

    fun update(item: Item)

}