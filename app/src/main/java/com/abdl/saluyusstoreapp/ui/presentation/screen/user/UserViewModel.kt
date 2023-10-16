package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdl.saluyusstoreapp.data.model.response.DataUserLogin
import com.abdl.saluyusstoreapp.data.model.response.DataUserRegis
import com.abdl.saluyusstoreapp.data.repository.UserRepository
import com.abdl.saluyusstoreapp.ui.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class UserViewModel(
    private val repo: UserRepository,
) : ViewModel() {

    private val _uiStateLogin = MutableStateFlow<UiState<DataUserLogin>>(UiState.Loading)
    val uiStateLogin: StateFlow<UiState<DataUserLogin>>
        get() = _uiStateLogin
    private val _uiStateRegis = MutableStateFlow<UiState<DataUserRegis>>(UiState.Loading)
    val uiStateRegis: StateFlow<UiState<DataUserRegis>>
        get() = _uiStateRegis

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                repo.login(username, password)
                    .collect { loginResponse ->
                        if (loginResponse.message == "Success") {
                            _uiStateLogin.value = UiState.Success(loginResponse.data!!)
                        }
                    }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == 401) {
                    // Handle HTTP 401 Unauthorized response
                    _uiStateLogin.value = UiState.Error("Invalid username or password")
                } else {
                    // Handle other errors
                    _uiStateLogin.value = UiState.Error(e.message.toString())
                }
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                repo.register(username, email, password)
                    .collect { regisResponse ->
                        if (regisResponse.message == "Success") {
                            _uiStateRegis.value = UiState.Success(regisResponse.data!!)
                        }
                    }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == 400) {
                    // Handle HTTP 400 Bad Request response
                    val errorResponse = e.response()?.errorBody()?.string()
                    val jsonObject = JSONObject(errorResponse)
                    val errorMessage = jsonObject.getString("message")
                    _uiStateRegis.value = UiState.Error(errorMessage)
                } else if (e is HttpException && e.code() == 401) {
                    // Handle HTTP 401 Unauthorized response
                    _uiStateRegis.value = UiState.Error("Invalid username or password")
                } else {
                    // Handle other errors
                    _uiStateRegis.value = UiState.Error(e.message.toString())
                }
            }
        }
    }

    fun resetUiState() {
        _uiStateRegis.value = UiState.Loading
        _uiStateLogin.value = UiState.Loading
    }

    companion object {
        val TAG = UserViewModel::class.simpleName
    }
}