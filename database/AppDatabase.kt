package com.example.kotlin_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_mvvm.database.Dao.QuoteDao
import com.example.kotlin_mvvm.database.Dao.UserDao
import com.example.kotlin_mvvm.database.entities.Quotes
import com.example.kotlin_mvvm.database.entities.User

@Database(
    entities = [User::class,Quotes::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuotesDao() : QuoteDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.db"
        ).build()
    }
}