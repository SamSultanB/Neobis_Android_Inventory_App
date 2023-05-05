package com.example.inventory.presenter.presenterImpl

import android.content.Context
import com.example.inventory.dataBase.AppDatabase
import com.example.inventory.dataBase.Repository
import com.example.inventory.presenter.PresenterArchive
import com.example.inventory.view.ViewArchive
import com.example.inventory.view.ViewMain

class PresenterArchiveImpl(private val context: Context): PresenterArchive {

    private val db = AppDatabase.getDatabase(context).itemDao()
    private val repository = Repository(db)
    private var viewArchive: ViewArchive? = null

    override fun getAllArchived() {
        viewArchive?.showAllArchived(repository.archivedItems)
    }

    override fun attachView(viewArchive: ViewArchive) {
        this.viewArchive = viewArchive
    }
}