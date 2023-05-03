package com.example.inventory.presenter

import android.content.Context
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.view.ViewMain

class PresenterMainImpl(private val context: Context): PresenterMain {
    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var viewMain: ViewMain? = null

    override fun getAllItems(){
        viewMain?.showAllItems(repository.items)
    }

    override fun attachView(viewMain: ViewMain) {
        this.viewMain = viewMain
    }
}