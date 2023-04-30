package com.example.inventory.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.inventory.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository
     init {
         val itemDao = AppDatabase.getDatabase(application).crossDao()
         repository =  Repository(itemDao)
     }

    fun addItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun getAllItems(): LiveData<List<Item>>{
        return repository.getAllItems()
    }
}