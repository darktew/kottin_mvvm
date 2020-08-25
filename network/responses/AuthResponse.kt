package com.example.kotlin_mvvm.network.responses

import com.example.kotlin_mvvm.database.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)