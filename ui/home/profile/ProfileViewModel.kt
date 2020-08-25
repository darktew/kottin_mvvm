package com.example.kotlin_mvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.kotlin_mvvm.responsitories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()


}