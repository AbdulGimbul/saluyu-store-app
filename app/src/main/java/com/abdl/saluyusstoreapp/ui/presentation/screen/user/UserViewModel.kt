package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdl.saluyusstoreapp.data.model.response.DataUser
import com.abdl.saluyusstoreapp.data.model.response.UserResponse
import com.abdl.saluyusstoreapp.data.repository.UserRepository
import com.abdl.saluyusstoreapp.di.LoginSession
import com.abdl.saluyusstoreapp.ui.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository,
    private val loginSession: LoginSession,
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean> = loginSession.isUserLoggedIn().stateIn(
        viewModelScope, SharingStarted.Eagerly, false
    )

    private val _uiStateLogin = MutableStateFlow<UiState<DataUser>>(UiState.Idle)
    val uiStateLogin: StateFlow<UiState<DataUser>> = _uiStateLogin

    private val _uiStateRegis = MutableStateFlow<UiState<DataUser>>(UiState.Idle)
    val uiStateRegis: StateFlow<UiState<DataUser>> = _uiStateRegis

    private val _uiStateGetUser = MutableStateFlow<UiState<DataUser>>(UiState.Loading)
    val uiStateGetUser: StateFlow<UiState<DataUser>> = _uiStateGetUser

    private val _uiStateUpdateUser = MutableStateFlow<UiState<DataUser>>(UiState.Idle)
    val uiStateUpdateUser: StateFlow<UiState<DataUser>> = _uiStateUpdateUser

    init {
        resetUiState()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _uiStateLogin.value = UiState.Loading
                repo.login(username, password)
                    .collect { loginResponse ->
                        if (loginResponse.message == "Success") {
                            _uiStateLogin.value = UiState.Success(loginResponse.data!!)
                            loginSession.setUserData(
                                isUserLoggedIn = true,
                                idUser = loginResponse.data.userId.toString(),
                                username = loginResponse.data.username.toString(),
                                token = loginResponse.data.userToken.toString(),
                            )
                        }
                    }
            } catch (e: Exception) {
                handleApiError(_uiStateLogin, e)
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _uiStateRegis.value = UiState.Loading
                repo.register(username, email, password)
                    .collect { regisResponse ->
                        if (regisResponse.message == "Success") {
                            _uiStateRegis.value = UiState.Success(regisResponse.data!!)
                        }
                    }
            } catch (e: Exception) {
                handleApiError(_uiStateRegis, e)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val idUser = loginSession.getUserId().first()
            try {
                _uiStateGetUser.value = UiState.Loading
                repo.getUser(userId = idUser)
                    .collect { userResponse ->
                        if (userResponse.message == "Success") {
                            _uiStateGetUser.value = UiState.Success(userResponse.data!!)
                        }
                    }
            } catch (e: Exception) {
                handleApiError(_uiStateGetUser, e)
            }
        }
    }

    fun updateUser(
        userId: String,
        fullname: String,
        username: String,
        email: String,
        phoneNumber: String,
        address: String,
    ) {
        viewModelScope.launch {
            try {
                _uiStateUpdateUser.value = UiState.Loading
                repo.update(userId, fullname, username, email, phoneNumber, address)
                    .collect { updateResponse ->
                        if (updateResponse.message == "Success") {
                            _uiStateUpdateUser.value = UiState.Success(updateResponse.data!!)
                        }
                    }
            } catch (e: Exception) {
                handleApiError(_uiStateUpdateUser, e)
            }
        }
    }

    fun resetUiState() {
        _uiStateRegis.value = UiState.Idle
        _uiStateLogin.value = UiState.Idle
        _uiStateGetUser.value = UiState.Idle
        _uiStateUpdateUser.value = UiState.Idle
    }

    fun logout() {
        viewModelScope.launch {
            loginSession.clearData()
            repo.logout()
        }
    }

    private fun handleApiError(uiState: MutableStateFlow<UiState<DataUser>>, e: Throwable) {
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    400 -> {
                        // Handle HTTP 400 Bad Request response
                        val errorResponse = e.response()?.errorBody()?.string()
                        val errorMessage = JSONObject(errorResponse.toString()).getString("message")
                        uiState.value = UiState.Error(errorMessage)
                    }
                    401 -> {
                        // Handle HTTP 401 Unauthorized response
                        uiState.value = UiState.Error("Invalid username or password")
                    }
                    403 -> {
                        // Handle HTTP 403 Unauthorized response
                        val errorResponse = e.response()?.errorBody()?.string()
                        val errorMessage = JSONObject(errorResponse.toString()).getString("message")
                        uiState.value = UiState.Error(errorMessage)
                    }
                    else -> {
                        // Handle other HTTP errors
                        uiState.value = UiState.Error(e.message.toString())
                    }
                }
            }
            is SocketTimeoutException -> {
                // Handle SocketTimeoutException
                uiState.value = UiState.Error("Network timeout")
            }
            else -> {
                // Handle other errors
                uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    companion object {
        val TAG = UserViewModel::class.simpleName
    }
}