package com.example.inventory.presenter.presenterImpl

import android.content.Context
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.model.Item
import com.example.inventory.presenter.PresenterMain
import com.example.inventory.view.ViewMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PresenterMainImpl(private val context: Context): PresenterMain {
    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var viewMain: ViewMain? = null

    override fun getAllItems(){
        viewMain?.showAllItems(repository.items)
    }

    override fun searchItem(query: String) {
        viewMain?.searchItem(query, repository.items)
    }

    override fun update(item: Item) {
        GlobalScope.launch(Dispatchers.IO){
            repository.updateItem(item)
        }
        viewMain?.showSuccess("Successfully archived")
    }

    override fun delete(item: Item) {
        GlobalScope.launch(Dispatchers.IO){
            repository.delete(item)
        }
        viewMain?.showSuccess("Item deleted successfully!")
    }

    override fun attachView(viewMain: ViewMain) {
        this.viewMain = viewMain
    }
}
