package com.example.inventory.presenter

import android.content.Context
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.model.Item
import com.example.inventory.view.ViewDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PresenterDetailsImpl(private val context: Context): PresenterDetails {

    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var viewDetails: ViewDetails? = null

    override fun attachView(viewDetails: ViewDetails) {
        this.viewDetails = viewDetails
    }

    override fun update(item: Item){
        GlobalScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
        viewDetails?.showSuccess("Item is updated")
    }
}