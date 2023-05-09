package com.example.inventory.presenter.presenterImpl

import android.content.Context
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.model.Item
import com.example.inventory.presenter.PresenterNewItem
import com.example.inventory.view.ViewNewItem
import kotlinx.coroutines.*

class PresenterNewItemImpl(private val context: Context): PresenterNewItem {
    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var viewNewItem: ViewNewItem? = null

    override fun attachView(viewNewItem: ViewNewItem) {
        this.viewNewItem = viewNewItem
    }

    override fun addItem(item: Item) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
        viewNewItem?.showSuccess("Item is added")
    }


}