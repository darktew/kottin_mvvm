package com.example.kotlin_mvvm.responsitories

import com.example.kotlin_mvvm.database.AppDatabase
import com.example.kotlin_mvvm.database.entities.User
import com.example.kotlin_mvvm.network.MyApi
import com.example.kotlin_mvvm.network.SafeApiRequest
import com.example.kotlin_mvvm.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(
        name: String, email: String, password: String
    ) : AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()



}