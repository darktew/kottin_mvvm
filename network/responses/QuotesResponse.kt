package com.example.kotlin_mvvm.network.responses

import com.example.kotlin_mvvm.database.entities.Quotes

class QuotesResponse(
    val isSuccessful : Boolean,
    val quotes: List<Quotes>
)