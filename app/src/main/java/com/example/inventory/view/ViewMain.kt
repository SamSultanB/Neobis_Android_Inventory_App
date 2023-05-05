package com.example.inventory.view

import androidx.lifecycle.LiveData
import com.example.inventory.model.Item


interface ViewMain {

    fun showAllItems(items: LiveData<List<Item>>)

    fun showError(error: String)

    fun showSuccess(message: String)

}