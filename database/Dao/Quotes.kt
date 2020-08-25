package com.example.kotlin_mvvm.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_mvvm.database.entities.Quotes

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes : List<Quotes>)

    @Query("SELECT * FROM Quotes")
    fun getQuotes() : LiveData<List<Quotes>>
}