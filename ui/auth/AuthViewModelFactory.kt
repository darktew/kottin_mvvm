package com.example.kotlin_mvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_mvvm.responsitories.UserRepository

@Suppress("UNCHECKED_CAST")
class  AuthViewModelFactory(
    private val repository : UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}