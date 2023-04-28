package com.example.inventory.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventory.model.Cross

@Database(entities = [Cross::class], version = 1,  exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun crossDao(): CrossDao
}