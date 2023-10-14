package com.abdl.saluyusstoreapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdl.saluyusstoreapp.data.repository.UserRepository
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.LoginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repo: UserRepository) :
ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}