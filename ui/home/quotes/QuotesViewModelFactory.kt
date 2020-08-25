package com.example.kotlin_mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_mvvm.responsitories.QuoteRepository

@Suppress("UNCHECKED_CAST")
class  QuotesViewModelFactory(
    private val repository : QuoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }

}