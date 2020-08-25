package com.example.kotlin_mvvm.responsitories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_mvvm.database.AppDatabase
import com.example.kotlin_mvvm.database.entities.Quotes
import com.example.kotlin_mvvm.network.MyApi
import com.example.kotlin_mvvm.network.SafeApiRequest
import com.example.kotlin_mvvm.preferences.PreferenceProvider
import com.example.kotlin_mvvm.utill.Coroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

class QuoteRepository (
    private val api : MyApi,
    private val db : AppDatabase,
    private val preferenceProvider: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes() : LiveData<List<Quotes>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuotesDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = preferenceProvider.getLastSavedAt()
        if(lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quotes>) {
        Coroutine.io {
            preferenceProvider.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuotesDao().saveAllQuotes(quotes)
        }
    }
}