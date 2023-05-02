package com.example.inventory.presenter

import android.content.ClipData
import android.content.Context
import android.provider.Settings.Global
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.model.Item
import com.example.inventory.view.View
import kotlinx.coroutines.*

class PresenterImpl(private val context: Context): Presenter {
    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var view: View?=null

    override fun getAllItems(){
        view?.showAllItems(repository.items)
    }

    override fun addItem(item: Item) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    override fun attachView(view: View) {
        this.view = view
    }
}