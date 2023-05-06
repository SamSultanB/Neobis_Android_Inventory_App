package com.example.inventory.presenter

import com.example.inventory.model.Item
import com.example.inventory.view.ViewArchive

interface PresenterArchive {

    fun getAllArchived()

    fun update(item: Item)

    fun delete(item: Item)

    fun attachView(viewArchive: ViewArchive)

}