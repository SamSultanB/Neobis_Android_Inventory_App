package com.example.inventory.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.model.Item

@Database(entities = [Item::class], version = 1,  exportSchema = false)
@androidx.room.TypeConverters(TypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase ?= null
        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dataBase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}