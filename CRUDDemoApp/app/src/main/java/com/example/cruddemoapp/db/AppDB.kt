package com.example.cruddemoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null
        fun getInstance(context: Context) : AppDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "crud_demo_database"
                    ).build()
                }
                return instance
            }
        }
    }
}