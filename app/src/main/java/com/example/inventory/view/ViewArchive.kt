package com.example.inventory.view

import androidx.lifecycle.LiveData
import com.example.inventory.model.Item

interface ViewArchive {
    fun showAllArchived(items: LiveData<List<Item>>)
    fun showError(errorMessage: String)
}