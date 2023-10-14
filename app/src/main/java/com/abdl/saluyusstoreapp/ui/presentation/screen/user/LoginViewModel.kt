package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdl.saluyusstoreapp.data.model.UserLoginResponse
import com.abdl.saluyusstoreapp.data.repository.UserRepository
import com.abdl.saluyusstoreapp.ui.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val repo: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UserLoginResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<UserLoginResponse>>
        get() = _uiState

    fun login(username: String, password: String){
        viewModelScope.launch {
            try {
                repo.login(username, password)
                    .collect { loginResponse ->
                        _uiState.value = UiState.Success(loginResponse)
                    }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == 401) {
                    // Handle HTTP 401 Unauthorized response
                    _uiState.value = UiState.Error("Invalid username or password")
                } else {
                    // Handle other errors
                    _uiState.value = UiState.Error(e.message.toString())
                }
            }
        }
    }

    companion object {
        val TAG = LoginViewModel::class.simpleName
    }
}