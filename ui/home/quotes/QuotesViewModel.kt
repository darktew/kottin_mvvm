package com.example.kotlin_mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.kotlin_mvvm.responsitories.QuoteRepository
import com.example.kotlin_mvvm.utill.lazyDeferred

class QuotesViewModel(
    repository: QuoteRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}