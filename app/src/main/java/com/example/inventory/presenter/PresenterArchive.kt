package com.example.inventory.presenter

import com.example.inventory.view.ViewArchive

interface PresenterArchive {

    fun getAllArchived()

    fun attachView(viewArchive: ViewArchive)

}