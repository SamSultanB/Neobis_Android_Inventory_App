package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.ViewMain


interface PresenterMain {

    fun getAllItems()

    fun attachView(viewMain: ViewMain)

}