package com.example.inventory.view

import androidx.lifecycle.LiveData
import com.example.inventory.model.Item

interface ViewArchive {

    fun showAllArchived(items: LiveData<List<Item>>)

    fun searchItem(query: String, items: LiveData<List<Item>>)

    fun showSuccess(successMessage: String)

    fun showError(errorMessage: String)
}